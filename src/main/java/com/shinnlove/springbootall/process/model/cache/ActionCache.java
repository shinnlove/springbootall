/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.model.cache;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.shinnlove.springbootall.process.handler.interfaces.ActionHandler;

/**
 * @author Tony Zhao
 * @version $Id: ActionCache.java, v 0.1 2022-01-29 5:31 PM Tony Zhao Exp $$
 */
public class ActionCache implements Serializable {

    private static final long    serialVersionUID = -3990743305894582911L;

    /** action id */
    private int                  actionId;

    /** action name */
    private String               name;

    /** action description */
    private String               desc;

    /** action source status, -1 represents no limitation */
    private int                  source           = -1;

    /** action destination status. */
    private int                  destination;

    /** action_id => sync execute in tx's handlers */
    private List<ActionHandler>  syncHandlers;

    /** action_id => async execute in tx's handlers */
    private List<ActionHandler>  asyncHandlers;

    /** handler class name => template id */
    private Map<String, Integer> prepareHandler;

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
     * Multiple constructors.
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

    /**
     * Constructor with all arguments.
     *
     * @param actionId
     * @param name
     * @param desc
     * @param source
     * @param destination
     * @param syncHandlers
     * @param asyncHandlers
     */
    public ActionCache(int actionId, String name, String desc, int source, int destination,
                       List<ActionHandler> syncHandlers, List<ActionHandler> asyncHandlers,
                       Map<String, Integer> prepareHandler) {
        this.actionId = actionId;
        this.name = name;
        this.desc = desc;
        this.source = source;
        this.destination = destination;
        this.syncHandlers = syncHandlers;
        this.asyncHandlers = asyncHandlers;
        this.prepareHandler = prepareHandler;
    }

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public Map<String, Integer> getPrepareHandler() {
        return prepareHandler;
    }

    public void setPrepareHandler(Map<String, Integer> prepareHandler) {
        this.prepareHandler = prepareHandler;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}