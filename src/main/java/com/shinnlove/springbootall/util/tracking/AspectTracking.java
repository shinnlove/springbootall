/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.tracking;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The domain model holding aspect tracking info for request and thread context data.
 *
 * @author Tony Zhao
 * @version $Id: AspectTracking.java, v 0.1 2021-08-23 3:48 PM Tony Zhao Exp $$
 */
public class AspectTracking implements Serializable {

    /** uuid */
    private static final long serialVersionUID = -8201801875232225078L;

    /** tracking info */
    private String            serviceTrackingUUID;
    /** common service, grpc, http service etc. */
    private String            invokePrefix;
    private String            trackingPrefix;

    /** thread context */
    private long              threadId;
    private String            threadName;

    /** package, method info */
    private String            classNameWithPackage;
    private String            simpleClassName;
    private String            methodName;

    /** method parameters and values. */
    private String[]          paramNames;
    private Object[]          paramValues;

    /** invoke time */
    private long              startTime;
    private long              endTime;

    /** invoke record */
    private String            originErrorMsg   = "N/A";
    private String            errorInfo        = "N/A";

    /**
     * Constructor for reflect.
     */
    public AspectTracking() {
    }

    /**
     * Constructor with only one argument.
     *
     * @param serviceTrackingUUID
     */
    public AspectTracking(String serviceTrackingUUID) {
        this.serviceTrackingUUID = serviceTrackingUUID;
    }

    public String getServiceTrackingUUID() {
        return serviceTrackingUUID;
    }

    public void setServiceTrackingUUID(String serviceTrackingUUID) {
        this.serviceTrackingUUID = serviceTrackingUUID;
    }

    public String getInvokePrefix() {
        return invokePrefix;
    }

    public void setInvokePrefix(String invokePrefix) {
        this.invokePrefix = invokePrefix;
    }

    public String getTrackingPrefix() {
        return trackingPrefix;
    }

    public void setTrackingPrefix(String trackingPrefix) {
        this.trackingPrefix = trackingPrefix;
    }

    public long getThreadId() {
        return threadId;
    }

    public void setThreadId(long threadId) {
        this.threadId = threadId;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getClassNameWithPackage() {
        return classNameWithPackage;
    }

    public void setClassNameWithPackage(String classNameWithPackage) {
        this.classNameWithPackage = classNameWithPackage;
    }

    public String getSimpleClassName() {
        return simpleClassName;
    }

    public void setSimpleClassName(String simpleClassName) {
        this.simpleClassName = simpleClassName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String[] getParamNames() {
        return paramNames;
    }

    public void setParamNames(String[] paramNames) {
        this.paramNames = paramNames;
    }

    public Object[] getParamValues() {
        return paramValues;
    }

    public void setParamValues(Object[] paramValues) {
        this.paramValues = paramValues;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getOriginErrorMsg() {
        return originErrorMsg;
    }

    public void setOriginErrorMsg(String originErrorMsg) {
        this.originErrorMsg = originErrorMsg;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}