/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.model.context;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * This is the context of whole process, add some business parameters here.
 *
 * @author Tony Zhao
 * @version $Id: ProcessContext.java, v 0.1 2021-07-06 4:14 PM Tony Zhao Exp $$
 */
public class ProcessContext<T> implements Serializable {
    private static final long   serialVersionUID  = 7468705719318252352L;

    /** process template id and action id */
    private int                 templateId        = -1;
    private int                 actionId          = -1;

    /** process No. and reference business No. */
    private long                processNo         = -1;
    private long                refUniqueNo       = -1;

    /** check source and destination status */
    private int                 sourceStatus      = -1;
    private int                 destinationStatus = -1;

    /** the generic parameter pass through a process to do business */
    private DataContext<T>      dataContext;

    /** a result map for storing handler's execute result */
    private Map<String, Object> result            = new HashMap<>();

    private Map<String, Class<?>> clazz             = new HashMap<>();

    /**
     * Constructor for reflect.
     */
    public ProcessContext() {
    }

    /**
     * Constructor for actionId, processNo, and generic data.
     *
     * @param actionId
     * @param processNo
     * @param dataContext
     */
    public ProcessContext(int actionId, long processNo, DataContext<T> dataContext) {
        this.actionId = actionId;
        this.processNo = processNo;
        this.dataContext = dataContext;
    }

    /**
     * Constructor for 2nd status machine to use.
     * 
     * @param templateId 
     * @param processNo
     * @param refUniqueNo
     * @param dataContext
     */
    public ProcessContext(int templateId, long processNo, long refUniqueNo,
                          DataContext<T> dataContext) {
        this.templateId = templateId;
        this.processNo = processNo;
        this.refUniqueNo = refUniqueNo;
        this.dataContext = dataContext;
    }

    /**
     * Constructors with five arguments.
     *
     * @param templateId
     * @param actionId
     * @param processNo
     * @param refUniqueNo
     * @param dataContext
     */
    public ProcessContext(int templateId, int actionId, long processNo, long refUniqueNo,
                          DataContext<T> dataContext) {
        this.templateId = templateId;
        this.actionId = actionId;
        this.processNo = processNo;
        this.refUniqueNo = refUniqueNo;
        this.dataContext = dataContext;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public long getProcessNo() {
        return processNo;
    }

    public void setProcessNo(long processNo) {
        this.processNo = processNo;
    }

    public long getRefUniqueNo() {
        return refUniqueNo;
    }

    public void setRefUniqueNo(long refUniqueNo) {
        this.refUniqueNo = refUniqueNo;
    }

    public int getSourceStatus() {
        return sourceStatus;
    }

    public void setSourceStatus(int sourceStatus) {
        this.sourceStatus = sourceStatus;
    }

    public int getDestinationStatus() {
        return destinationStatus;
    }

    public void setDestinationStatus(int destinationStatus) {
        this.destinationStatus = destinationStatus;
    }

    public DataContext<T> getDataContext() {
        return dataContext;
    }

    public void setDataContext(DataContext<T> dataContext) {
        this.dataContext = dataContext;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public Map<String, Class<?>> getClazz() {
        return clazz;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}