/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.model.template;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.shinnlove.springbootall.process.handler.interfaces.ActionHandler;

/**
 * @author Tony Zhao
 * @version $Id: ActionCache.java, v 0.1 2022-01-29 5:31 PM Tony Zhao Exp $$
 */
public class ActionCache implements Serializable {

    private static final long   serialVersionUID = 2364078968927300778L;

    /** action id */
    private int                 actionId;

    /** action source status, -1 represents no limitation */
    private int                 source           = -1;

    /** action destination status. */
    private int                 destination;

    /** action_id => sync execute in tx's handlers */
    private List<ActionHandler> syncHandlers;

    /** action_id => async execute in tx's handlers */
    private List<ActionHandler> asyncHandlers;

    /**
     * Constructor for reflect.
     */
    public ActionCache() {
    }

    public ActionCache(int actionId) {
        this.actionId = actionId;
    }

    /**
     * Constructor with three arguments.
     * 
     * @param actionId 
     * @param source
     * @param destination
     */
    public ActionCache(int actionId, int source, int destination) {
        this.actionId = actionId;
        this.source = source;
        this.destination = destination;
    }

    /**
     * Constructor with all arguments.
     * 
     * @param actionId 
     * @param source
     * @param destination
     * @param syncHandlers
     * @param asyncHandlers
     */
    public ActionCache(int actionId, int source, int destination, List<ActionHandler> syncHandlers,
                       List<ActionHandler> asyncHandlers) {
        this.actionId = actionId;
        this.source = source;
        this.destination = destination;
        this.syncHandlers = syncHandlers;
        this.asyncHandlers = asyncHandlers;
    }

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public List<ActionHandler> getSyncHandlers() {
        return syncHandlers;
    }

    public void setSyncHandlers(List<ActionHandler> syncHandlers) {
        this.syncHandlers = syncHandlers;
    }

    public List<ActionHandler> getAsyncHandlers() {
        return asyncHandlers;
    }

    public void setAsyncHandlers(List<ActionHandler> asyncHandlers) {
        this.asyncHandlers = asyncHandlers;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}