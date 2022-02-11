/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;

import com.shinnlove.springbootall.process.callback.ProcessCallback;
import com.shinnlove.springbootall.process.consts.MachineConstant;
import com.shinnlove.springbootall.process.core.ProcessBlockingCoreService;
import com.shinnlove.springbootall.process.core.ProcessStatusCoreService;
import com.shinnlove.springbootall.process.core.UniversalProcessCoreService;
import com.shinnlove.springbootall.process.future.FutureUtil;
import com.shinnlove.springbootall.process.handler.interfaces.ActionHandler;
import com.shinnlove.springbootall.process.model.action.ProcessAction;
import com.shinnlove.springbootall.process.model.blocking.ProcessBlocking;
import com.shinnlove.springbootall.process.model.context.DataContext;
import com.shinnlove.springbootall.process.model.context.ProcessContext;
import com.shinnlove.springbootall.process.model.process.UniversalProcess;
import com.shinnlove.springbootall.process.model.result.ProcessBatchResult;
import com.shinnlove.springbootall.process.model.status.StatusPair;
import com.shinnlove.springbootall.process.model.template.ProcessTemplate;
import com.shinnlove.springbootall.process.no.SnowflakeIdWorker;
import com.shinnlove.springbootall.process.service.ActionExecutor;
import com.shinnlove.springbootall.process.service.ProcessAssembleService;
import com.shinnlove.springbootall.process.service.StatusMachineService;
import com.shinnlove.springbootall.util.code.SystemResultCode;
import com.shinnlove.springbootall.util.common.AssertUtil;
import com.shinnlove.springbootall.util.exception.SystemException;
import com.shinnlove.springbootall.util.log.LoggerUtil;

import io.prometheus.client.Counter;

import javax.annotation.Resource;

/**
 * State machine service implementation for process and actions.<br>
 *
 *          根据processId二级索引使用悲观锁查询流程表并锁定该行for update<br>
 *          校验需推进主流程是否存在<br>
 *          如果流程存在，取出当前流程templateId、status状态(流程上状态是主状态、或流程日志表中最后一条日志的状态)<br>
 *          查询templateId的Map缓存、查询metadata缓存Map拿到当前process是否要勾兑parent process以及勾兑类型(协同或独占)、目标勾兑状态<br>
 *          查询缓存Map映射templateId => actionId => 对应的source和destination状态<br>
 *          校验当前流程主状态是否和actionId对应的source状态一致，不一致不允许执行当前actionId对应的所有handler<br>
 *          如果当前流程主状态满足actionId的起始状态、再以processId作为外键查询process blocking表找寻所有挂载到它的流程<br>
 *          找到当前流程所有状态目前挂载的blocking流程、根据表数据初始化类持有的数据结构<br>
 *          如果当前流程待变迁source状态下有blocking process、要确定该流程是否完结<br>
 *          依次查询待变迁状态上挂载的blocking process(无锁查询)是否都到完结态(在内存卡带中找不到下一个阶段)<br>
 *          如果有一条blocking流程状态未到完结态、则状态机不允许执行该actionId对应的handlers<br>
 *          如果没有blocking流程、或所有blocking流程都到完结态、则允许执行当前action<br>
 *          以流程链的方式执行action下对应所有handler<br>
 *          如果所有handler是以事务方式执行、只要有一个流程不成功、则事务整个回滚<br>
 *          如果所有handler执行成功、则状态变迁成功、记录一条status log、同时主流程的log状态变更<br>
 *          根据destination状态查询状态Map下一个状态、如果没有下一个状态、则代表当前流程结束<br>
 *          查看当前process的parent processId是否为-1、如果不为-1代表有父流程<br>
 *          如果需要勾兑父流程状态、则以parentId作为processId查询父流程for update<br>
 *          如果自身属于抢占式勾兑、校验父流程状态是否处于终态、不为终态直接勾兑父流程状态到目标状态(跳过handler)<br>
 *          如果自身属于非抢占式勾兑、查询父流程下所有除自身外的子流程<br>
 *          逐个校验每个子流程、如果所有状态都在映射Map里没有下一个状态、则勾兑父流程状态到目标状态(跳过handler)<br>
 *          只要有一个子流程不满足终态、放弃勾兑父流程状态<br>
 *          完成本次流程状态推进、事务结束<br>
 *
 *
 *          Process A' <= Sub Process A''
 *          Process B => B'
 *          Process C
 *
 *          新品上市
 *                 衣服A 裤子B
 *             男装
 *             女装
 *                 帽子C
 *
 *
 * @author Tony Zhao
 * @version $Id: StatusMachineService.java, v 0.1 2021-07-06 6:38 PM Tony Zhao Exp $$
 */
@Deprecated
@Service
public class StatusMachineServiceImpl implements StatusMachineService {

    /** logger */
    private static final Logger         logger = LoggerFactory
        .getLogger(StatusMachineServiceImpl.class);

    @Autowired
    @Qualifier("processPool")
    private ExecutorService             asyncExecutor;

    /** snowflake id generator */
    @Autowired
    private SnowflakeIdWorker           snowflakeIdWorker;

    /** transaction template */
    @Resource
    private TransactionTemplate         transactionTemplate;

    /** process template and status metadata autowired service */
    @Autowired
    private ProcessAssembleService      processAssembleService;

    /** universal process core service */
    @Autowired
    private UniversalProcessCoreService universalProcessCoreService;

    /** process status core service */
    @Autowired
    private ProcessStatusCoreService    processStatusCoreService;

    /** process blocking core service */
    @Autowired
    private ProcessBlockingCoreService  processBlockingCoreService;

    /** an executor to execute a couple of action's handlers */
    @Autowired
    private ActionExecutor              actionExecutor;

    @Autowired
    @Qualifier("statusMachineCounter")
    private Counter                     statusMachineCounter;

    @Override
    public long proceedProcess(int actionId, long refUniqueNo, DataContext dataContext) {
        return proceedProcess(actionId, refUniqueNo, dataContext, resp -> {
        });
    }

    @Override
    public long proceedProcess(int actionId, long refUniqueNo, DataContext dataContext,
                               ProcessCallback callback) {
        // 1st: use action id to fetch action relative template info
        ProcessTemplate template = processAssembleService.getTemplateInfoByActionId(actionId);
        AssertUtil.isNotNull(template);

        int templateId = template.getTemplateId();
        int needReconcile = template.getCompleteReconcileParent();
        int reconcileMode = template.getCoordinateMode();

        // for sampling
        statusMachineCounter.labels(String.valueOf(templateId), String.valueOf(actionId)).inc();

        // 2nd: get action info
        ProcessAction action = processAssembleService.getActionInfoById(actionId);
        AssertUtil.isNotNull(action);

        // 3rd: get action source and destination
        StatusPair sp = processAssembleService.getActionStatus(actionId);
        final int source = sp.getSourceStatus();
        final int destination = sp.getDestinationStatus();

        // 4th: prepare proceed context
        ProcessContext context = buildProContext(templateId, actionId, refUniqueNo, source,
            destination, dataContext);

        // 5th: prepare handlers
        List<ActionHandler> syncHandlers = processAssembleService.getActionExecutions(actionId,
            true);
        List<ActionHandler> asyncHandlers = processAssembleService.getActionExecutions(actionId,
            false);

        // fast query once to check if it's a new process
        UniversalProcess nProcess = universalProcessCoreService.getProcessByRefUniqueNo(refUniqueNo,
            false);
        if (nProcess == null && 1 == action.getProcessEntrance()) {
            // only entrance action could create a new process
            AssertUtil.largeThanValue(refUniqueNo, 0);

            // get id
            long processNo = snowflakeIdWorker.nextId();
            Long processId = transactionTemplate.execute(status -> {
                // firstly execute handler
                execute(context, syncHandlers);

                // secondly create new process
                long newProcessId = processStatusCoreService.createProcessWithStatus(templateId,
                    actionId, processNo, refUniqueNo, source, destination,
                    dataContext.getOperator(), dataContext.getRemark());

                // Warning: give callback a chance to execute outta business codes, callback must after execute!
                if (callback != null) {
                    callback.doCallback(context);
                }

                return newProcessId;
            });

            // then execute async handlers
            execute(context, asyncHandlers);

            return processNo;
        }

        // 6th: check current process info if process exists
        Integer result = transactionTemplate.execute(status -> {

            // need to lock current process
            UniversalProcess uProcess = universalProcessCoreService
                .getProcessByRefUniqueNo(refUniqueNo, true);
            AssertUtil.isNotNull(uProcess, SystemResultCode.PARAM_INVALID,
                MachineConstant.NO_PROCESS_IN_SYSTEM);

            long processNo = uProcess.getProcessNo();
            context.setProcessNo(processNo);

            int currentStatus = uProcess.getCurrentStatus();
            long parentProcessNo = uProcess.getParentProcessNo();

            // use status flow reflection to validate state flow correct
            checkSourceStatus(currentStatus, source);

            // check no blocking in way..
            checkProcessBlocking(processNo);

            // real execute action's handlers
            execute(context, syncHandlers);

            // service which operates the combination of status
            processStatusCoreService.proceedProcessStatus(templateId, actionId, processNo, source,
                destination, dataContext.getOperator(), dataContext.getRemark());

            // give a change for business codes to execute outta logic, callback must after execute!
            if (callback != null) {
                callback.doCallback(context);
            }

            // if status reached to end, then check reconcile mode
            if (parentProcessNo != -1 && needReconcile == 1) {
                // get parent process
                UniversalProcess parentProcess = universalProcessCoreService
                    .lockProcessByProcessNo(parentProcessNo);
                int pTemplateId = parentProcess.getTemplateId();
                int pActionId = -1;
                int pcStatus = parentProcess.getCurrentStatus();

                // get other sibling process
                List<UniversalProcess> childProcesses = universalProcessCoreService
                    .getProcessListByParentProcessNo(parentProcessNo);
                List<UniversalProcess> otherChildProcessList = childProcesses.stream()
                    .filter(t -> t.getProcessNo() != processNo).collect(Collectors.toList());

                // check reconcile flag
                boolean needUpdateParent = true;
                if (reconcileMode == 1) {
                    // cooperate mode:
                    // loop to check each child process status with no lock and update parent status if ok
                    for (UniversalProcess up : otherChildProcessList) {
                        int uptId = up.getTemplateId();
                        int upStatus = up.getCurrentStatus();
                        boolean isFinal = processAssembleService.isFinalStatus(uptId, upStatus);
                        if (!isFinal) {
                            needUpdateParent = false;
                        }
                    }
                }

                // need reconcile parent
                if (needUpdateParent) {
                    int pacStatus = processAssembleService.getACFinalStatus(pTemplateId);
                    processStatusCoreService.proceedProcessStatus(pTemplateId, pActionId,
                        parentProcessNo, pcStatus, pacStatus, MachineConstant.DEFAULT_OPERATOR,
                        MachineConstant.DEFAULT_REMARK);
                }

            } // if need reconcile

            return 1;

        }); // 6th execute tx

        // At last, execute async handlers outta transaction!
        execute(context, asyncHandlers);

        return result;
    }

    @Override
    public long proceedProcessAsync(final int actionId, final long refUniqueNo,
                                    final DataContext dataContext) {
        CompletableFuture<Long> future = CompletableFuture
            .supplyAsync(() -> proceedProcess(actionId, refUniqueNo, dataContext), asyncExecutor);
        return FutureUtil.getResult(future);
    }

    @Override
    public long proceedProcessAsync(final int actionId, final long refUniqueNo,
                                    final DataContext dataContext, final ProcessCallback callback) {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(
            () -> proceedProcess(actionId, refUniqueNo, dataContext, callback), asyncExecutor);
        return FutureUtil.getResult(future);
    }

    @Override
    public <T> ProcessBatchResult batchProceedSameProcesses(List<Long> refUniqueNos, int actionId,
                                                            List<DataContext<T>> dataContexts) {
        int success = 0;
        int fail = 0;
        Map<Long, Integer> executeResult = new HashMap<>();

        // simple size validation
        int noSize = refUniqueNos.size();
        int contextSize = dataContexts.size();
        if (noSize != contextSize) {
            throw new SystemException(SystemResultCode.PARAM_INVALID,
                MachineConstant.INVALID_PARAMETERS);
        }

        // loop to try...catch execute
        for (int i = 0; i < noSize; i++) {
            long no = refUniqueNos.get(i);
            DataContext cData = dataContexts.get(i);
            try {
                success += proceedProcess(actionId, no, cData);
                executeResult.put(no, 1);
            } catch (Exception e) {
                // log and skip
                LoggerUtil.error(logger, e, e.getMessage());
                executeResult.put(no, 0);
                fail += 1;
            }
        } // for

        return new ProcessBatchResult(refUniqueNos.size(), success, fail, executeResult);
    }

    @Override
    public int hookBlockingProcess(long mainProcessNo, int refStatus, long obstacleByProcessNo) {

        Integer result = transactionTemplate.execute(new TransactionCallback<Integer>() {
            @Override
            public Integer doInTransaction(TransactionStatus status) {

                // first query exist blocking, reject if necessary

                // build blocking model
                ProcessBlocking pb = new ProcessBlocking();
                pb.setMainProcessNo(mainProcessNo);
                // targeting main status
                pb.setRefStatus(refStatus);
                // obstacle by
                pb.setObstacleByProcessNo(obstacleByProcessNo);

                return processBlockingCoreService.addBlockingProcess2Target(pb);
            }
        });

        return result;
    }

    @Override
    public int unhookBlockingProcess(long mainProcessNo, long obstacleByProcessNo) {
        return transactionTemplate.execute(status -> processBlockingCoreService
            .removeBlockingByMainAndObstacleNo(mainProcessNo, obstacleByProcessNo));
    }

    @Override
    public List<ProcessBlocking> getBlockingProcessesByNo(long mainProcessNo) {
        return processBlockingCoreService.getBlockingByProcessNo(mainProcessNo);
    }

    private void checkSourceStatus(int currentStatus, int source) {
        if (currentStatus != source) {
            throw new SystemException(SystemResultCode.PARAM_INVALID,
                MachineConstant.SOURCE_STATUS_INCORRECT);
        }
    }

    /**
     * Check there is no blocking issue or all process have reached final destination status.
     *
     * @param processNo     the specific check processNo.
     */
    private void checkProcessBlocking(long processNo) {
        // check there is no blocking issue or all process have reached final destination status
        List<ProcessBlocking> blockingList = processBlockingCoreService
            .getBlockingByProcessNo(processNo);
        if (!CollectionUtils.isEmpty(blockingList)) {
            for (ProcessBlocking b : blockingList) {
                long bProcessNo = b.getMainProcessNo();
                UniversalProcess bProcess = universalProcessCoreService.getProcessByNo(bProcessNo);
                int btId = bProcess.getTemplateId();
                int bStatus = bProcess.getCurrentStatus();
                if (!processAssembleService.isFinalStatus(btId, bStatus)) {
                    throw new SystemException(SystemResultCode.SYSTEM_ERROR,
                        MachineConstant.STATUS_HAS_BLOCKING_PROCESS);
                }
            }
        } // if blocking
    }

    private void execute(final ProcessContext context,
                         final List<ActionHandler> handlers) throws SystemException {
        // real execute action's handlers
        try {
            actionExecutor.proceed(context, handlers);
        } catch (Exception e) {
            LoggerUtil.error(logger, e, "Handler execution has error occurred, ", e.getMessage());
            // special warn: according to meeting discussion, the whole tx should be interrupted if one handler execute failed.
            throw new SystemException(SystemResultCode.SYSTEM_ERROR, e, e.getMessage());
        }
    }

    private ProcessContext buildProContext(int templateId, int actionId, long refUniqueNo,
                                           int source, int destination, DataContext dataContext) {
        ProcessContext context = new ProcessContext();
        context.setTemplateId(templateId);
        context.setActionId(actionId);
        context.setRefUniqueNo(refUniqueNo);
        context.setSourceStatus(source);
        context.setDestinationStatus(destination);
        context.setDataContext(dataContext);
        return context;
    }

}