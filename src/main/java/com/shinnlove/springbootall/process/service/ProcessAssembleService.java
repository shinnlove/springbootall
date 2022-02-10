/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.service;

import java.io.InputStream;
import java.util.List;

import com.shinnlove.springbootall.process.handler.interfaces.ActionHandler;
import com.shinnlove.springbootall.process.model.action.ProcessAction;
import com.shinnlove.springbootall.process.model.status.StatusPair;
import com.shinnlove.springbootall.process.model.template.ProcessTemplate;

/**
 * This service is used for process machine to assemble different metadata process.
 *
 * @author Tony Zhao
 * @version $Id: ProcessAssembleService.java, v 0.1 2021-07-06 4:12 PM Tony Zhao Exp $$
 */
@Deprecated
public interface ProcessAssembleService {

    /**
     * an interface called by springboot callback to initialize all processes' template and status in mission system.
     * Special attention: this method could only be called by spring itself!
     *
     * @param stream        the input stream of a template xml file path and name under classpath.
     */
    @Deprecated
    void initializeProcessTemplateStatus(InputStream stream);

    /**
     * Fetch from cache service to get template info.
     *
     * @param actionId      the specific action id that reverse reflect to a template.
     * @return
     */
    ProcessTemplate getTemplateInfoByActionId(int actionId);

    /**
     * Fetch from cache service to get action info.
     *
     * @param actionId      the specific action id that reflects to an action.
     * @return
     */
    ProcessAction getActionInfoById(int actionId);

    /**
     * Fetch status flow map to check whether current process has proper source status to execute an action.
     *
     * @param actionId          the specific action id
     * @return
     */
    StatusPair getActionStatus(int actionId);

    /**
     * Fetch the execution handlers under one action and one template.
     *
     * @param actionId          the specific action id
     * @param sync              if true, return sync execute handlers, else return async handlers
     * @return
     */
    List<ActionHandler> getActionExecutions(int actionId, boolean sync);

    /**
     *  Check whether a status is final status of a process/template.
     *
     * @param templateId        the specific template id
     * @param sourceStatus      the status which is checked and passed through this method as source status.
     * @return
     */
    boolean isFinalStatus(int templateId, int sourceStatus);

    /**
     * Get normal accomplished final status with the given template id.
     *
     * @param templateId
     * @return
     */
    int getACFinalStatus(int templateId);

}
