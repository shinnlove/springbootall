/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.model.cache;

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
    private static final long                       serialVersionUID = 1067695181772489507L;

    /** template self metadata */
    private TemplateMetadata                        metadata;

    /** process status array, quick check table. */
    private StatusCache[]                           statusArray;

    /** destination status => handlers, initialize status and handlers */
    private Map<Integer, List<ActionHandler>>       initializers     = new HashMap<>();

    /** search actions by action id. actionId => action */
    private Map<Integer, ActionCache>               actions          = new HashMap<>();

    /** type => handlers accept, reject, cancel trigger handlers */
    private Map<String, List<ActionHandler>>        triggers         = new HashMap<>();

    /** compatible mode: dst search. action id => source => (source + destination) */
    private Map<Integer, Map<Integer, StatusPair>>  dstTable         = new HashMap<>();

    /** quick search nearest actions by given dst. destination => source => action */
    private Map<Integer, Map<Integer, ActionCache>> actionTable      = new HashMap<>();

    /**
     * Constructor for reflect.
     */
    public TemplateCache() {
    }

    /**
     * Constructor with metadata.
     * 
     * @param metadata 
     */
    public TemplateCache(TemplateMetadata metadata) {
        this.metadata = metadata;
    }

    /**
     * Constructor with all arguments.
     *
     * @param metadata     
     * @param statusArray
     * @param initializers
     * @param actions
     * @param triggers
     * @param dstTable
     * @param actionTable
     */
    public TemplateCache(TemplateMetadata metadata, StatusCache[] statusArray,
                         Map<Integer, List<ActionHandler>> initializers,
                         Map<Integer, ActionCache> actions,
                         Map<String, List<ActionHandler>> triggers,
                         Map<Integer, Map<Integer, StatusPair>> dstTable,
                         Map<Integer, Map<Integer, ActionCache>> actionTable) {
        this.metadata = metadata;
        this.statusArray = statusArray;
        this.initializers = initializers;
        this.actions = actions;
        this.triggers = triggers;
        this.dstTable = dstTable;
        this.actionTable = actionTable;
    }

    public TemplateMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(TemplateMetadata metadata) {
        this.metadata = metadata;
    }

    public StatusCache[] getStatusArray() {
        return statusArray;
    }

    public void setStatusArray(StatusCache[] statusArray) {
        this.statusArray = statusArray;
    }

    public Map<Integer, List<ActionHandler>> getInitializers() {
        return initializers;
    }

    public void setInitializers(Map<Integer, List<ActionHandler>> initializers) {
        this.initializers = initializers;
    }

    public Map<Integer, ActionCache> getActions() {
        return actions;
    }

    public void setActions(Map<Integer, ActionCache> actions) {
        this.actions = actions;
    }

    public Map<String, List<ActionHandler>> getTriggers() {
        return triggers;
    }

    public void setTriggers(Map<String, List<ActionHandler>> triggers) {
        this.triggers = triggers;
    }

    public Map<Integer, Map<Integer, StatusPair>> getDstTable() {
        return dstTable;
    }

    public void setDstTable(Map<Integer, Map<Integer, StatusPair>> dstTable) {
        this.dstTable = dstTable;
    }

    public Map<Integer, Map<Integer, ActionCache>> getActionTable() {
        return actionTable;
    }

    public void setActionTable(Map<Integer, Map<Integer, ActionCache>> actionTable) {
        this.actionTable = actionTable;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}