/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.core;


import com.shinnlove.springbootall.process.model.log.ProcessStatusLog;

import java.util.List;

/**
 * Core service mixed of universal process status and process log core service.
 *
 * @author Tony Zhao
 * @version $Id: ProcessStatusCoreService.java, v 0.1 2021-07-20 7:57 PM Tony Zhao Exp $$
 */
public interface ProcessStatusCoreService {

    /**
     * @param templateId
     * @param actionId
     * @param processNo
     * @param refUniqueNo
     * @param source
     * @param destination
     * @param operator
     * @param remark
     * @return
     */
    long createProcessWithStatus(int templateId, int actionId, long processNo, long refUniqueNo,
                                 int source, int destination, String operator, String remark);

    /**
     * Atomic proceed action of process to update given process No.'s status with logging in status log.
     *
     * @param templateId        the template id the process has
     * @param actionId          the action id the process proceed
     * @param processNo         process No.
     * @param source            source status
     * @param destination       destination status
     * @param operator          the operator who proceeds the process's status
     * @param remark            remark the proceed has been commented
     * @return                  database primary id of status log
     */
    long proceedProcessStatus(int templateId, int actionId, long processNo, int source,
                              int destination, String operator, String remark);

    /**
     * Get process status logs by given reference unique bussiness No.
     *
     * @param refUniqueNo
     * @return
     */
    List<ProcessStatusLog> getLogsByRefUniqueNo(long refUniqueNo);

}
