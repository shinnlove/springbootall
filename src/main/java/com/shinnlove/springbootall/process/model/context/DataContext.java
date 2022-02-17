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

import com.shinnlove.springbootall.process.handler.interfaces.ActionHandler2nd;

/**
 * A context hold process entrance data to do business.<br>
 * The original purpose is to prepare the process data before status machine doing all business,
 * so that it could avoid couples of handlers duplicate querying business data back and forth.<br><br>
 *
 * First define your data class with several fields, i.e.: RecruitMissionData.java<br>
 * Then use this class to hold your generic data class, i.e.: DataContext<RecruitMissionData> dataContext,
 * And thus you could proceed a new process smoothly like this:<br><br>
 *
 * statusMachineService.proceedProcess(actionId, refUniqueNo, dataContext);<br><br>
 *
 * Wonderful and Congratulation :)<br><br>
 *
 * @author Tony Zhao
 * @version $Id: DataContext.java, v 0.1 2021-07-22 11:53 AM Tony Zhao Exp $$
 */
public class DataContext<T> implements Serializable {
    private static final long   serialVersionUID = -2019461634360608165L;

    /** generic data passed by business code */
    private T                   data;

    /** An generic object for business handler to put data in it. A list of handler execute result hold by context! */
    @Deprecated
    private Map<String, Object> handlerResult    = new HashMap<>();

    /** the process proceed operator and default is system. */
    private String              operator         = "Mission System";

    /** The remark each process need to comment. */
    private String              remark           = "System delegate to proceed the process";

    /**
     * Constructor for reflect.
     */
    public DataContext() {
    }

    /**
     * Constructor with data arguments.
     *
     * @param data
     */
    public DataContext(T data) {
        this.data = data;
    }

    /**
     * Constructor for all arguments.
     *
     * @param data
     * @param handlerResult
     * @param operator
     * @param remark
     */
    public DataContext(T data, Map<String, Object> handlerResult, String operator, String remark) {
        this.data = data;
        this.handlerResult = handlerResult;
        this.operator = operator;
        this.remark = remark;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * By given a handler name return the handler execute result.
     * Return null if current handler is pioneer of the given handler name!!
     *
     * @param clazz
     * @return
     */
    public Object getHandlerResultByName(Class<? extends ActionHandler2nd> clazz) {
        String className = clazz.getName();
        if (handlerResult.containsKey(className)) {
            return handlerResult.get(className);
        }
        return null;
    }

    /**
     * Store handler execute result to a target position.
     *
     * @param clazz         the given class type
     * @param result        the execute result
     */
    @Deprecated
    public void storeHandlerResult(Class<? extends ActionHandler2nd> clazz, Object result) {
        String className = clazz.getName();
        handlerResult.put(className, result);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}