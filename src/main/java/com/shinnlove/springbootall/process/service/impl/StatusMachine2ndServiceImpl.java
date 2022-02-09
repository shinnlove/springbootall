/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.service.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;

import com.shinnlove.springbootall.process.callback.ProcessCallback;
import com.shinnlove.springbootall.process.consts.MachineConstant;
import com.shinnlove.springbootall.process.core.ProcessBlockingCoreService;
import com.shinnlove.springbootall.process.core.ProcessStatusCoreService;
import com.shinnlove.springbootall.process.core.UniversalProcessCoreService;
import com.shinnlove.springbootall.process.handler.interfaces.ActionHandler;
import com.shinnlove.springbootall.process.model.blocking.ProcessBlocking;
import com.shinnlove.springbootall.process.model.cache.ActionCache;
import com.shinnlove.springbootall.process.model.cache.TemplateCache;
import com.shinnlove.springbootall.process.model.cache.TemplateMetadata;
import com.shinnlove.springbootall.process.model.context.DataContext;
import com.shinnlove.springbootall.process.model.context.ProcessContext;
import com.shinnlove.springbootall.process.model.process.UniversalProcess;
import com.shinnlove.springbootall.process.no.SnowflakeIdWorker;
import com.shinnlove.springbootall.process.service.ActionExecutor;
import com.shinnlove.springbootall.process.service.ProcessMetadataService;
import com.shinnlove.springbootall.process.service.StatusMachine2ndService;
import com.shinnlove.springbootall.util.code.SystemResultCode;
import com.shinnlove.springbootall.util.common.AssertUtil;
import com.shinnlove.springbootall.util.exception.SystemException;
import com.shinnlove.springbootall.util.log.LoggerUtil;

import javax.annotation.Resource;

/**
 * @author Tony Zhao
 * @version $Id: StatusMachine2ndServiceImpl.java, v 0.1 2022-02-09 5:50 PM Tony Zhao Exp $$
 */
@Service
public class StatusMachine2ndServiceImpl implements StatusMachine2ndService {

    private static final Logger         logger = LoggerFactory
        .getLogger(StatusMachine2ndServiceImpl.class);

    /** thread executor */
    @Autowired
    @Qualifier("processPool")
    private ExecutorService             asyncExecutor;

    /** snowflake id generator */
    @Autowired
    private SnowflakeIdWorker           snowflakeIdWorker;

    /** transaction template */
    @Resource
    private TransactionTemplate         transactionTemplate;

    @Autowired
    private ProcessMetadataService      processMetadataService;

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

    @Override
    public long initProcess(int templateId, int destination, long refUniqueNo,
                            DataContext dataContext) {
        return initProcess(templateId, destination, refUniqueNo, dataContext, resp -> {
        });
    }

    @Override
    public long initProcess(int templateId, int destination, long refUniqueNo,
                            DataContext dataContext, ProcessCallback callback) {
        TemplateCache template = processMetadataService.getTemplateById(templateId);
        AssertUtil.isNotNull(template);

        Map<Integer, List<ActionHandler>> inits = template.getInitializers();
        if (CollectionUtils.isEmpty(inits) || !inits.containsKey(destination)) {
            return 0;
        }

        List<ActionHandler> handlers = inits.get(destination);
        final int source = -1;

        // 4th: prepare proceed context
        ProcessContext context = buildProContext(templateId, refUniqueNo, source, destination,
            dataContext);

        // fast query once to check if it's a new process
        UniversalProcess nProcess = universalProcessCoreService.getProcessByRefUniqueNo(refUniqueNo,
            false);
        AssertUtil.isNull(nProcess);
        AssertUtil.largeThanValue(refUniqueNo, 0);

        long processNo = snowflakeIdWorker.nextId();

        Long processId = transactionTemplate.execute(status -> {
            execute(context, handlers);

            // secondly create new process
            long newProcessId = processStatusCoreService.createProcessWithStatus(templateId, -1,
                processNo, refUniqueNo, source, destination, dataContext.getOperator(),
                dataContext.getRemark());

            // Warning: give callback a chance to execute outta business codes, callback must after execute!
            if (callback != null) {
                callback.doCallback(dataContext);
            }

            return newProcessId;
        });

        AssertUtil.largeThanValue(processId, 0);

        return processNo;
    }

    @Override
    public long proceedProcess(int actionId, long refUniqueNo, DataContext dataContext) {
        return proceedProcess(actionId, refUniqueNo, dataContext, resp -> {
        });
    }

    @Override
    public long proceedProcess(int actionId, long refUniqueNo, DataContext dataContext,
                               ProcessCallback callback) {

        TemplateCache template = processMetadataService.getTemplateByActionId(actionId);
        AssertUtil.isNotNull(template);

        TemplateMetadata metadata = template.getMetadata();

        int templateId = metadata.getId();
        int needReconcile = metadata.getCompleteReconcileParent();
        int reconcileMode = metadata.getCoordinateMode();

        Map<Integer, ActionCache> actionMap = template.getActions();
        ActionCache actionCache = actionMap.get(actionId);
        final int source = actionCache.getSource();
        final int destination = actionCache.getDestination();

        // prepare proceed context
        ProcessContext context = buildProContext(templateId, actionId, refUniqueNo, source,
            destination, dataContext);

        // prepare handlers
        List<ActionHandler> syncHandlers = processMetadataService.getExecutions(actionId, true);
        List<ActionHandler> asyncHandlers = processMetadataService.getExecutions(actionId, false);

        // fast query once to check if it's a new process
        UniversalProcess nProcess = universalProcessCoreService.getProcessByRefUniqueNo(refUniqueNo,
            false);
        AssertUtil.notNull(nProcess);

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
                callback.doCallback(dataContext);
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
                        // todo: need to consider
                        //                        boolean isFinal = processAssembleService.isFinalStatus(uptId, upStatus);
                        //                        if (!isFinal) {
                        //                            needUpdateParent = false;
                        //                        }
                    }
                }

                // need reconcile parent
                if (needUpdateParent) {
                    // todo: need to consider
                    //                    int pacStatus = processAssembleService.getACFinalStatus(pTemplateId);
                    int pacStatus = -1;
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

    private ProcessContext buildProContext(int templateId, long refUniqueNo, int source,
                                           int destination, DataContext dataContext) {
        ProcessContext context = new ProcessContext();
        context.setTemplateId(templateId);
        context.setRefUniqueNo(refUniqueNo);
        context.setSourceStatus(source);
        context.setDestinationStatus(destination);
        context.setDataContext(dataContext);
        return context;
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
                // todo: need to consider.
                //                if (!processAssembleService.isFinalStatus(btId, bStatus)) {
                //                    throw new SystemException(SystemResultCode.SYSTEM_ERROR,
                //                        MachineConstant.STATUS_HAS_BLOCKING_PROCESS);
                //                }
            }
        } // if blocking
    }

}