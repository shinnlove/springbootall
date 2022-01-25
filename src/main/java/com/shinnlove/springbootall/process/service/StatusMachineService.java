/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.service;

import java.util.List;

import com.shinnlove.springbootall.process.callback.ProcessCallback;
import com.shinnlove.springbootall.process.model.blocking.ProcessBlocking;
import com.shinnlove.springbootall.process.model.context.DataContext;
import com.shinnlove.springbootall.process.model.result.ProcessBatchResult;

/**
 * State machine service interfaces for all methods.
 *
 * @author Tony Zhao
 * @version $Id: StatusMachineService.java, v 0.1 2021-07-06 3:55 PM Tony Zhao Exp $$
 */
public interface StatusMachineService {

    /**
     * The union standard entrance method of create or proceed a process with the given action id.<br><br>
     *
     * Special Warning:
     * The system will create a new process with No. returned if there is no process No. in it and the action id is the entrance one,
     * while declined to execute if action is not an entrance.
     * The process search by given process No. will be proceed to target action status if there has already exists this process.
     *
     * @param actionId          the given action id to proceed the whole process
     * @param refUniqueNo       the unique businessNo, required when created a new process.
     * @param dataContext       the data context for the process to execute
     * @return
     */
    long proceedProcess(final int actionId, long refUniqueNo, final DataContext dataContext);

    /**
     * The union standard entrance method of create or proceed a process with the given action id and callback method.
     *
     * @param actionId          the given action id to proceed the whole process
     * @param refUniqueNo       the unique businessNo, required when created a new process.
     * @param dataContext       the data context for the process to execute
     * @param callback          a process callback written by business code for outta business logic handle in status machine's transaction
     * @return
     */
    long proceedProcess(final int actionId, long refUniqueNo, final DataContext dataContext,
                        final ProcessCallback callback);

    /**
     * Execute Asynchronously.
     *
     * @see StatusMachineService#proceedProcess(int, long, com.bilibili.ad.missionbiz.process.model.context.DataContext)
     */
    long proceedProcessAsync(final int actionId, final long refUniqueNo,
                             final DataContext dataContext);

    /**
     * Execute Asynchronously with callback.
     *
     * @see StatusMachineService#proceedProcess(int, long, com.bilibili.ad.missionbiz.process.model.context.DataContext, com.bilibili.ad.missionbiz.process.callback.ProcessCallback)
     */
    long proceedProcessAsync(final int actionId, final long refUniqueNo,
                             final DataContext dataContext, final ProcessCallback callback);

    /**
     * Batch common action of proceed multiple process to specific status with given actionId.
     * Pay special attention that status machine could only proceed multiple processes with the same template id,
     * otherwise proceed would be interrupted!
     *
     * TODO: all processes are linear execute now, pls use asynchronous thread pool to execute a series of processes.
     *
     * @param refUniqueNos      reference unique business Nos that needs to be proceed
     * @param actionId          specific actionId to target status
     * @param dataContexts      the data context generated by business logic and passing through status machine service
     * @return                  batch process execute results.
     */
    <T> ProcessBatchResult batchProceedSameProcesses(List<Long> refUniqueNos, int actionId,
                                                     List<DataContext<T>> dataContexts);

    /**
     * hook a process(which frequently regarded as sub process) onto a main process targeting on a specific state,
     * all proceed actions after this state on the main process could not be proceed appropriately until blocking processes are all removed.
     *
     * @param mainProcessNo             main process No.
     * @param refStatus                 specific state
     * @param obstacleByProcessNo       obstacle process No. hook
     * @return
     */
    int hookBlockingProcess(long mainProcessNo, int refStatus, long obstacleByProcessNo);

    /**
     * unhook a blocking process from a main process, after which the state proceed of main process is unlimited.
     *
     * @param mainProcessNo             main process No.
     * @param obstacleByProcessNo       obstacle process No.
     * @return
     */
    int unhookBlockingProcess(long mainProcessNo, long obstacleByProcessNo);

    /**
     * Get all blocking processes by given main process No.
     *
     * @param mainProcessNo     specific main process No.
     * @return
     */
    List<ProcessBlocking> getBlockingProcessesByNo(long mainProcessNo);

}