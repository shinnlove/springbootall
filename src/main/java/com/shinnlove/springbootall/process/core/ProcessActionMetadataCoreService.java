/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.core;


import com.shinnlove.springbootall.process.model.action.ProcessAction;

import java.util.List;

/**
 * The core service interface for process action metadata.
 *
 * @author Tony Zhao
 * @version $Id: ProcessActionMetadataCoreService.java, v 0.1 2021-07-26 10:32 PM Tony Zhao Exp $$
 */
public interface ProcessActionMetadataCoreService {

    /**
     * Get process actions by template id.
     *
     * @param templateId
     * @return
     */
    List<ProcessAction> getProcessActions(int templateId);

}

