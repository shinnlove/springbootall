/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.core.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinnlove.springbootall.process.core.ProcessStatusCoreService;
import com.shinnlove.springbootall.process.core.ProcessStatusLogCoreService;
import com.shinnlove.springbootall.process.core.UniversalProcessCoreService;
import com.shinnlove.springbootall.process.model.log.ProcessStatusLog;
import com.shinnlove.springbootall.process.model.process.UniversalProcess;

/**
 * Implementation of core service mixed of universal process status and process log core service.
 *
 * @author Tony Zhao
 * @version $Id: ProcessStatusCoreServiceImpl.java, v 0.1 2021-07-20 8:00 PM Tony Zhao Exp $$
 */
@Service
public class ProcessStatusCoreServiceImpl implements ProcessStatusCoreService {

    @Autowired
    private UniversalProcessCoreService universalProcessCoreService;

    @Autowired
    private ProcessStatusLogCoreService processStatusLogCoreService;

    @Override
    public long createProcessWithStatus(int templateId, int actionId, long processNo,
                                        long refUniqueNo, int source, int destination,
                                        String operator, String remark) {
        // build model
        UniversalProcess uProcess = new UniversalProcess();
        uProcess.setProcessNo(processNo);
        uProcess.setTemplateId(templateId);
        uProcess.setRefUniqueNo(refUniqueNo);
        // todo: parent no -1 need to use template info
        uProcess.setCurrentStatus(destination);
        uProcess.setLatestOperator(operator);
        uProcess.setRemark(remark);

        long processId = universalProcessCoreService.addProcess(uProcess);

        ProcessStatusLog statusLog = new ProcessStatusLog(processNo, templateId, actionId, source,
            destination, remark, operator);
        processStatusLogCoreService.insertProcessLog(statusLog);

        return processId;
    }

    @Override
    public long proceedProcessStatus(int templateId, int actionId, long processNo, int source,
                                     int destination, String remark, String operator) {
        // update process status
        universalProcessCoreService.updateProcessStatus(processNo, source, destination);

        // add log after status updated
        ProcessStatusLog statusLog = new ProcessStatusLog(processNo, templateId, actionId, source,
            destination, remark, operator);
        return processStatusLogCoreService.insertProcessLog(statusLog);
    }

    @Override
    public List<ProcessStatusLog> getLogsByRefUniqueNo(long refUniqueNo) {
        // first use reference unique No. to query process info
        UniversalProcess process = universalProcessCoreService.getProcessByRefUniqueNo(refUniqueNo,
            false);
        if (process == null) {
            return Collections.emptyList();
        }

        // then use process No. to query log ids.
        return processStatusLogCoreService.getLogsByProcessNo(process.getProcessNo());
    }

}