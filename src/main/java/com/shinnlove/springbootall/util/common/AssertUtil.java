/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.common;

import com.shinnlove.springbootall.util.code.SystemResultCode;
import com.shinnlove.springbootall.util.exception.SystemException;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: AssertUtil.java, v 0.1 2022-01-10 11:38 PM Tony Zhao Exp $$
 */
public class AssertUtil extends org.springframework.util.Assert {

    /**
     * Specific a number must larger than a given target number.
     *
     * @param checkNumber
     * @param compareNumber
     */
    public static void largeThanValue(long checkNumber, long compareNumber) {
        if (checkNumber <= compareNumber) {
            throw new SystemException(SystemResultCode.PARAM_INVALID,
                "业务值" + checkNumber + "不应小于指定目标值" + compareNumber);
        }
    }

    public static void smallerThanValue(long checkNumber, long compareNumber) {
        if (checkNumber >= compareNumber) {
            throw new SystemException(SystemResultCode.PARAM_INVALID,
                "业务值" + checkNumber + "不应大于指定目标值" + compareNumber);
        }
    }

    /**
     * 传入 bool 表达式，判断 对
     * 报错为默认 {@link SystemResultCode.SYSTEM_ERROR}
     * @param condition 需要被判断对 的表达式
     */
    public static void isTrue(boolean condition) {
        if (!condition) {
            throw new SystemException(SystemResultCode.SYSTEM_ERROR);
        }
    }

    /**
     * 传入 bool 表达式，判断 错
     * 报错为默认 {@link SystemResultCode.SYSTEM_ERROR}
     * @param condition 需要被判断错 的表达式
     */
    public static void isFalse(boolean condition) {
        isTrue(!condition);
    }

    /**
     * 传入 bool 表达式，判断 对
     * @param condition 需要被判断对 的表达式
     * @param code error code enum extend  SystemResultCode
     */
    public static void isTrue(boolean condition, SystemResultCode code) {
        if (!condition) {
            throw new SystemException(code);
        }
    }

    /**
     * 传入 bool 表达式，判断 错
     * @param condition 需要被判断错 的表达式
     * @param code error code enum extend  SystemResultCode
     */
    public static void isFalse(boolean condition, SystemResultCode code) {
        isTrue(!condition, code);
    }

    /**
     * 传入 bool 表达式，判断 对
     *
     * @param condition 需要被判断对 的表达式
     * @param code error code enum extend  SystemResultCode
     * @param customizeMessage other error detail message
     */
    public static void isTrue(boolean condition, SystemResultCode code, String customizeMessage) {
        if (!condition) {
            throw new SystemException(code, customizeMessage);
        }
    }

    /**
     * 传入 bool 表达式，判断 错
     *
     * @param condition 需要被判断错 的表达式
     * @param code error code enum extend  SystemResultCode
     * @param customizeMessage other error detail message
     */
    public static void isFalse(boolean condition, SystemResultCode code, String customizeMessage) {
        isTrue(!condition, code, customizeMessage);
    }

    /**
     * 传入 bool 表达式，判断 对
     *
     * @param condition 需要被判断对 的表达式
     * @param code error code enum extend  SystemResultCode
     * @param cause cause error
     * @param customizeMessage other error detail message
     */
    public static void isTrue(boolean condition, SystemResultCode code, SystemException cause,
                              String customizeMessage) {
        if (!condition) {
            throw new SystemException(code, cause, customizeMessage);
        }
    }

    /**
     * 传入 bool 表达式，判断 错
     *
     * @param condition 需要被判断错 的表达式
     * @param code error code enum extend  SystemResultCode
     * @param cause cause error
     * @param customizeMessage other error detail message
     */
    public static void isFalse(boolean condition, SystemResultCode code, SystemException cause,
                               String customizeMessage) {
        isTrue(!condition, code, cause, customizeMessage);
    }

    /**
     * 传入 对象，判断 非空
     * 报错为默认 {@link SystemResultCode.SYSTEM_ERROR}
     * @param object 需要被判断的对象
     */
    public static void isNotNull(Object object) {
        if (object == null) {
            throw new SystemException(SystemResultCode.SYSTEM_ERROR);
        }
    }

    /**
     * 传入 对象，判断 非空
     *
     * @param object 需要被判断的对象
     * @param code error code enum extend  SystemResultCode
     */
    public static void isNotNull(Object object, SystemResultCode code) {
        if (object == null) {
            throw new SystemException(code);
        }
    }

    /**
     * 传入 对象，判断 空
     * 报错为默认 {@link SystemResultCode.SYSTEM_ERROR}
     * @param object 需要被判断的对象
     */
    public static void isNull(Object object) {
        if (object != null) {
            throw new SystemException(SystemResultCode.SYSTEM_ERROR);
        }
    }

    /**
     * 传入 对象，判断 空
     *
     * @param object 需要被判断的对象
     * @param code error code enum extend  SystemResultCode
     */
    public static void isNull(Object object, SystemResultCode code) {
        if (object != null) {
            throw new SystemException(code);
        }
    }

    /**
     * 传入 对象 ，判断 非空
     *
     * @param object 需要被判断对 的表达式
     * @param code error code enum extend  SystemResultCode
     * @param customizeMessage other error detail message
     */
    public static void isNotNull(Object object, SystemResultCode code, String customizeMessage) {
        if (object == null) {
            throw new SystemException(code, customizeMessage);
        }
    }

    /**
     * 传入 对象 ，判断 空
     *
     * @param object 需要被判断对 的表达式
     * @param code error code enum extend  SystemResultCode
     * @param customizeMessage other error detail message
     */
    public static void isNull(Object object, SystemResultCode code, String customizeMessage) {
        if (object != null) {
            throw new SystemException(code, customizeMessage);
        }
    }

    /**
     * 传入 对象 ，判断 非空
     *
     * @param object 需要被判断对 的表达式
     * @param code error code enum extend  SystemResultCode
     * @param cause cause error
     * @param customizeMessage other error detail message
     */
    public static void isNotNull(Object object, SystemResultCode code, SystemException cause,
                                 String customizeMessage) {
        if (object == null) {
            throw new SystemException(code, cause, customizeMessage);
        }
    }

    /**
     * 传入 对象 ，判断 空
     *
     * @param object 需要被判断对 的表达式
     * @param code error code enum extend  SystemResultCode
     * @param cause cause error
     * @param customizeMessage other error detail message
     */
    public static void isNull(Object object, SystemResultCode code, SystemException cause,
                              String customizeMessage) {
        if (object != null) {
            throw new SystemException(code, cause, customizeMessage);
        }
    }

    /**
     * 传入列表，判断空。
     *
     * @param list
     * @param code
     * @param <T>
     */
    public static <T> void listNotEmpty(List<T> list, SystemResultCode code) {
        if (CollectionUtils.isEmpty(list)) {
            throw new SystemException(code, "列表不能为空");
        }
    }

    public static <T> void listNotEmpty(List<T> list, SystemResultCode code,
                                        String customizeMessage) {
        if (CollectionUtils.isEmpty(list)) {
            throw new SystemException(code, customizeMessage);
        }
    }

    public static <T> void listEmpty(List<T> list, SystemResultCode code, String customizeMessage) {
        if (!CollectionUtils.isEmpty(list)) {
            throw new SystemException(code, customizeMessage);
        }
    }

}