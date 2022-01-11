/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.core;


import com.shinnlove.springbootall.process.model.status.ProcessStatus;

import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: ProcessStatusMetadataCoreService.java, v 0.1 2021-07-06 4:10 PM Tony Zhao Exp $$
 */
public interface ProcessStatusMetadataCoreService {

    /**
     * Get status reflect of a process.
     *
     * @param templateId
     * @return
     */
    List<ProcessStatus> getProcessStatus(int templateId);

}
