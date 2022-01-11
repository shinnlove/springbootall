/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.core;


import com.shinnlove.springbootall.process.model.log.ProcessStatusLog;

import java.util.List;

/**
 * This service is only for record process state log and re-build context.
 *
 * @author Tony Zhao
 * @version $Id: ProcessStatusLogCoreService.java, v 0.1 2021-07-06 4:13 PM Tony Zhao Exp $$
 */
public interface ProcessStatusLogCoreService {

    /**
     * Insert one status log in database.
     *
     * @param statusLog
     * @return
     */
    @Deprecated
    long insertProcessLog(ProcessStatusLog statusLog);

    /**
     * Get process status log by process id.
     *
     * @param processNo     process unique No.
     * @return
     */
    @Deprecated
    List<ProcessStatusLog> getLogsByProcessNo(long processNo);

    List<ProcessStatusLog> getLogsByProcessNoAndDesStatus(List<Long> processNos,
                                                          Integer destinationStatus);

}
