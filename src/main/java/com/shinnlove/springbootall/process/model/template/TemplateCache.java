/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.model.template;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.shinnlove.springbootall.process.handler.interfaces.ActionHandler;
import com.shinnlove.springbootall.process.model.status.StatusPair;

/**
 * Template cache for springboot to hold in memory.
 * 
 * @author Tony Zhao
 * @version $Id: TemplateCache.java, v 0.1 2022-01-29 3:00 PM Tony Zhao Exp $$
 */
public class TemplateCache implements Serializable {

    /** uuid */
    private static final long                       serialVersionUID = 4699755725421697179L;

    /** type => handlers accept, reject, cancel trigger handlers */
    private Map<String, List<ActionHandler>>        triggers         = new HashMap<>();

    /** destination status => handlers, initialize status and handlers */
    private Map<Integer, List<ActionHandler>>       initializers     = new HashMap<>();

    /** process status list */
    private StatusCache[]                           statusArray;

    /** action id => source + destination */
    private Map<Integer, StatusPair>                actionStatus     = new HashMap<>();

    /** destination => source => action */
    private Map<Integer, Map<Integer, ActionCache>> reverseFlow      = new HashMap<>();

    /**
     * Constructor for reflect.
     */
    public TemplateCache() {
    }

    /**
     * Constructor with all arguments.
     * 
     * @param triggers 
     * @param initializers
     * @param statusArray
     * @param actionStatus
     * @param reverseFlow
     */
    public TemplateCache(Map<String, List<ActionHandler>> triggers,
                         Map<Integer, List<ActionHandler>> initializers, StatusCache[] statusArray,
                         Map<Integer, StatusPair> actionStatus,
                         Map<Integer, Map<Integer, ActionCache>> reverseFlow) {
        this.triggers = triggers;
        this.initializers = initializers;
        this.statusArray = statusArray;
        this.actionStatus = actionStatus;
        this.reverseFlow = reverseFlow;
    }

    public Map<String, List<ActionHandler>> getTriggers() {
        return triggers;
    }

    public void setTriggers(Map<String, List<ActionHandler>> triggers) {
        this.triggers = triggers;
    }

    public Map<Integer, List<ActionHandler>> getInitializers() {
        return initializers;
    }

    public void setInitializers(Map<Integer, List<ActionHandler>> initializers) {
        this.initializers = initializers;
    }

    public StatusCache[] getStatusArray() {
        return statusArray;
    }

    public void setStatusArray(StatusCache[] statusArray) {
        this.statusArray = statusArray;
    }

    public Map<Integer, StatusPair> getActionStatus() {
        return actionStatus;
    }

    public void setActionStatus(Map<Integer, StatusPair> actionStatus) {
        this.actionStatus = actionStatus;
    }

    public Map<Integer, Map<Integer, ActionCache>> getReverseFlow() {
        return reverseFlow;
    }

    public void setReverseFlow(Map<Integer, Map<Integer, ActionCache>> reverseFlow) {
        this.reverseFlow = reverseFlow;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}