/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.dto;

/**
 * 服务结果工厂。
 *
 * @author Tony Zhao
 * @version $Id: ServiceResultFactory.java, v 0.1 2025-03-03 16:07 Tony Zhao Exp $$
 */
public class ServiceResultFactory {

    public static <T> ServiceResult<T> success(T data) {
        return new ServiceResult<>(true, null, data);
    }

    public static <T> ServiceResult<T> fail(String message) {
        return new ServiceResult<>(false, message, null);
    }

}