/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.core;


import com.shinnlove.springbootall.process.model.template.ProcessTemplate;

/**
 * @author Tony Zhao
 * @version $Id: ProcessTemplateMetadataCoreService.java, v 0.1 2021-07-06 4:11 PM Tony Zhao Exp $$
 */
public interface ProcessTemplateMetadataCoreService {

    /**
     * Get template metadata from database.
     *
     * @param templateId
     * @return
     */
    @Deprecated
    ProcessTemplate getTemplateMetadata(int templateId);

}
