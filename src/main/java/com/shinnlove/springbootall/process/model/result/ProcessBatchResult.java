/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.model.result;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Process proceed batch handle result.
 *
 * @author Tony Zhao
 * @version $Id: ProcessBatchResult.java, v 0.1 2021-07-20 7:45 PM Tony Zhao Exp $$
 */
public class ProcessBatchResult implements Serializable {

    private static final long  serialVersionUID = -966590685417380437L;

    private int                total;
    private int                success;
    private int                fail;

    private Map<Long, Integer> executeResult;

    /**
     * Constructor for reflect.
     */
    public ProcessBatchResult() {
    }

    /**
     * Constructor with arguments.
     *
     * @param total
     * @param success
     * @param fail
     * @param executeResult
     */
    public ProcessBatchResult(int total, int success, int fail, Map<Long, Integer> executeResult) {
        this.total = total;
        this.success = success;
        this.fail = fail;
        this.executeResult = executeResult;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getFail() {
        return fail;
    }

    public void setFail(int fail) {
        this.fail = fail;
    }

    public Map<Long, Integer> getExecuteResult() {
        return executeResult;
    }

    public void setExecuteResult(Map<Long, Integer> executeResult) {
        this.executeResult = executeResult;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}