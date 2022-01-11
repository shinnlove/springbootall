/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.tracking;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import com.shinnlove.springbootall.util.exception.SystemException;
import com.shinnlove.springbootall.util.log.LoggerUtil;

/**
 * The tracking util in aspect for collect service invoke tracking info.
 *
 * @author Tony Zhao
 * @version $Id: AspectTrackingUtil.java, v 0.1 2021-08-23 4:06 PM Tony Zhao Exp $$
 */
public class AspectTrackingUtil {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(AspectTrackingUtil.class);

    /**
     * Initialize and extract info when a method wrapper by aspectj weaver.
     *
     * @param jp            the invoke join point cut
     * @param invokeName    service invoke name, i.e. Common Service | gRPC Service | DAO Service etc.
     * @return
     */
    public static AspectTracking initTracking(ProceedingJoinPoint jp, String invokeName) {

        // generate tracking UUID
        String serviceTrackingUUID = TrackingUtil.getOrCreateUUID();

        // 打印SAL层公共日志所需的prefix
        String invokePrefix = "[" + invokeName + " Invoke]:";
        String trackingPrefix = "[" + invokeName + " Tracking UUID=" + serviceTrackingUUID + "]";

        // current thread info
        Thread current = Thread.currentThread();
        long threadId = current.getId();
        String threadName = current.getName();

        // full class name and method name
        String classNameWithPackage = jp.getTarget().getClass().getName();
        String simpleClassName = jp.getTarget().getClass().getSimpleName();
        Signature signature = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        String methodName = method.getName();

        // 方法参数名和参数类型
        String[] paramNames = ((CodeSignature) jp.getSignature()).getParameterNames();
        Object[] paramValues = jp.getArgs();

        // finally build aspect 
        AspectTracking trackingInfo = new AspectTracking(serviceTrackingUUID);

        trackingInfo.setInvokePrefix(invokePrefix);
        trackingInfo.setTrackingPrefix(trackingPrefix);

        trackingInfo.setThreadId(threadId);
        trackingInfo.setThreadName(threadName);

        trackingInfo.setClassNameWithPackage(classNameWithPackage);
        trackingInfo.setSimpleClassName(simpleClassName);
        trackingInfo.setMethodName(methodName);

        trackingInfo.setParamNames(paramNames);
        trackingInfo.setParamValues(paramValues);

        // start from now
        trackingInfo.setStartTime(System.currentTimeMillis());

        return trackingInfo;
    }

    public static String handleErrorMsg(AspectTracking trackingInfo, NullPointerException e) {
        String invokePrefix = trackingInfo.getInvokePrefix();
        String trackingPrefix = trackingInfo.getTrackingPrefix();

        String originMsg = e.getMessage();
        String errorInfo = invokePrefix + "模型转换NPE异常，" + trackingPrefix + ", originMsg="
                           + originMsg;

        return errorInfo;
    }

    public static String handleErrorMsg(AspectTracking trackingInfo, SystemException e) {
        String invokePrefix = trackingInfo.getInvokePrefix();
        String trackingPrefix = trackingInfo.getTrackingPrefix();

        String originMsg = e.getMessage();
        String digestMsg = e.getDigestStr();
        String codeMsg = e.getResultCode().getMessage();
        String errorInfo = invokePrefix + "出错，" + trackingPrefix + ", digestMsg=" + digestMsg
                           + ", originMsg=" + originMsg + ", codeMsg=" + codeMsg;

        return errorInfo;
    }

    public static String handleErrorMsg(AspectTracking trackingInfo, Throwable t) {
        String invokePrefix = trackingInfo.getInvokePrefix();
        String trackingPrefix = trackingInfo.getTrackingPrefix();

        String originStr = t.getMessage();
        String errorInfo = invokePrefix + "系统未知错误，" + trackingPrefix + ", ex=" + originStr;

        return errorInfo;
    }

    /**
     * Return aspect log with service invoke and parameters info.
     *
     * @param trackingInfo      the tracking info previous initialized in aspects.
     * @return
     */
    public static String finishInvokeLog(AspectTracking trackingInfo, int invokeFlag) {
        String invokeEndLog = getInvokeEndLog(trackingInfo, invokeFlag, false);
        String parametersLog = getParametersLog(trackingInfo);
        return invokeEndLog + "\n" + parametersLog;
    }

    /**
     * Print handler logs.
     * This is a method special write for mission handler chain.
     *
     * @param trackingInfo
     * @param invokeFlag
     * @param parameters
     * @param data
     * @return
     */
    public static String finishHandlerLog(AspectTracking trackingInfo, int invokeFlag,
                                          String parameters, String data) {
        String logInfo = AspectTrackingUtil.getInvokeEndLog(trackingInfo, invokeFlag, true);

        // finally...do the logging, first log method

        String append1 = "-----------ProcessActionHandlerAspect-------Result-------\n";
        String append2 = logInfo + "\n";
        String append3 = "-----------ProcessActionHandlerAspect-------Parameters-------\n";
        String append4 = parameters + "\n";
        String append5 = "-----------ProcessActionHandlerAspect-------dataContext-------\n";
        String append6 = data;

        // let all log print out in once together!!
        return append1 + append2 + append3 + append4 + append5 + append6;
    }

    /**
     * Return the string for log print that an aspect's method invoke info.
     *
     * @param trackingInfo      the tracking info previous initialized in aspects.
     * @param invokeFlag        Invoke flag, 1 represents invoke success while 0 represents failed.
     * @param isHandler         indicate whether common service logging or handler service logging.
     * @return
     */
    private static String getInvokeEndLog(AspectTracking trackingInfo, int invokeFlag,
                                          boolean isHandler) {

        // finish time counting
        long endTime = System.currentTimeMillis();
        long startTime = trackingInfo.getStartTime();
        long duration = endTime - startTime;

        // fetch previous info
        String trackingPrefix = trackingInfo.getTrackingPrefix();
        String invokePrefix = trackingInfo.getInvokePrefix();
        if (isHandler) {
            String simpleClassName = trackingInfo.getSimpleClassName();
            invokePrefix = "[Process Handler =>" + simpleClassName + "<= Execute]:";
        }

        long threadId = trackingInfo.getThreadId();
        String threadName = trackingInfo.getThreadName();

        String classNameWithPackage = trackingInfo.getClassNameWithPackage();
        String methodName = trackingInfo.getMethodName();

        StringBuilder sb = new StringBuilder();
        sb.append(trackingPrefix).append("[Thread id=").append(threadId).append(", Thread Name=")
            .append(threadName).append("]").append("\n");
        sb.append(invokePrefix).append(classNameWithPackage).append(".").append(methodName);
        sb.append(", result=").append(invokeFlag).append(", duration=").append(duration)
            .append("ms.");

        return sb.toString();
    }

    /**
     * Return the parameters log string for aspect logging.
     *
     * @param trackingInfo      the tracking info previous initialized in aspects.
     * @return
     */
    private static String getParametersLog(AspectTracking trackingInfo) {
        // fetch previous info
        String[] paramNames = trackingInfo.getParamNames();
        Object[] paramValues = trackingInfo.getParamValues();

        // robust
        int len = 0;
        if (paramNames != null) {
            len = paramNames.length;
        }

        // 处理函数入参
        StringBuilder paramsSb = new StringBuilder();
        if (paramValues.length > 0 && len > 0) {
            paramsSb.append(" [Method args:");
            // 打印每个参数
            for (int i = 0; i < len - 1; i++) {
                paramsSb.append(paramNames[i] + "=" + paramValues[i]);
                paramsSb.append(", ");
            }
            paramsSb.append(paramNames[len - 1] + "=" + paramValues[len - 1]);
            paramsSb.append("]").append("\n");
        } else {
            // 直接append
            paramsSb.append("[Method no args]").append("\n");
        }

        return paramsSb.toString();
    }

    public static Object CatLog(AspectTracking tracking, AspectCallback callback) throws Throwable {
        Object tt = null;
        Transaction transaction = null;
        try {
            transaction = getCatTransaction(tracking);

            // invoke
            tt = callback.call();

            logInvokeResult(tt);

            if (transaction != null) {
                transaction.setStatus(Transaction.SUCCESS);
            }
        } catch (Throwable t) {
            if (transaction != null) {
                transaction.setStatus(t);
            }
            // special warning: don't log any warn/error log here
            throw t;
        } finally {
            if (transaction != null) {
                transaction.complete();
            }
        }
        return tt;
    }

    private static Transaction getCatTransaction(AspectTracking tracking) {
        String trackingUUID = tracking.getServiceTrackingUUID();
        String invokePrefix = tracking.getInvokePrefix();
        String className = tracking.getSimpleClassName();
        String methodName = tracking.getMethodName();
        String catTxName = invokePrefix + className + "." + methodName;
        Transaction transaction = null;
        try {
            transaction = Cat.newTransaction(className, catTxName);
            transaction.addData("tracking_uuid", trackingUUID);
            transaction.addData("transaction_class", className);
            transaction.addData("transaction_method", methodName);
        } catch (Throwable t) {
            LoggerUtil.error(logger, t, "Create CAT transaction error with ex=", t.getMessage());
        }
        return transaction;
    }

    private static void logInvokeResult(Object tt) {
        if (tt == null) {
            LoggerUtil.debug(logger, "Method result is null.");
            return;
        }

        try {
            String result = JSON.toJSONString(tt);
            LoggerUtil.debug(logger, "Result is ", result);
        } catch (Exception e) {
            LoggerUtil.warn(logger, e, "Result is ", tt);
        }
    }

}