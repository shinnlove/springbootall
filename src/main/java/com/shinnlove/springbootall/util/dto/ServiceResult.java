/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.dto;

import lombok.Data;

/**
 * 服务通用类
 *
 * @author Tony Zhao
 * @version $Id: ServiceResult.java, v 0.1 2025-03-03 16:04 Tony Zhao Exp $$
 */
@Data
public class ServiceResult<T> {

    /** 是否成功 */
    private boolean success;

    /** 接口返回信息 */
    private String message;

    /** 数据信息 */
    private T data;

    public ServiceResult() {
    }

    public ServiceResult(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

}