/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.third.party.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 印鸽需要业务方返回的字段。
 *
 * @author Tony Zhao
 * @version $Id: YinGeResult.java, v 0.1 2025-03-04 10:46 Tony Zhao Exp $$
 */
@Data
@NoArgsConstructor
public class YinGeResult<T> {

    /** 错误码 */
    private int code;

    /** 消息 */
    private String msg;

    /** 错误信息 */
    private String error;

    /** 数据 */
    private T data;

    public YinGeResult(int code, String error) {
        this.code = code;
        this.error = error;
    }

    public YinGeResult(int code, String error, T data) {
        this.code = code;
        this.error = error;
        this.data = data;
    }

    public boolean isSuccessful() {
        return code == 0;
    }

}