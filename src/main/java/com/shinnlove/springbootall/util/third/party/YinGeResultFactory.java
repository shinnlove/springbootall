/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.third.party;

import com.shinnlove.springbootall.util.third.party.dto.YinGeResult;

/**
 * 印鸽响应结果通用类。
 *
 * @author Tony Zhao
 * @version $Id: YinGeResultFactory.java, v 0.1 2025-03-04 19:31 Tony Zhao Exp $$
 */
public class YinGeResultFactory {

    public static <T> YinGeResult<T> success(T data) {
        return new YinGeResult<>(0, "", data);
    }

    public static <T> YinGeResult<T> fail(int code, String message) {
        return new YinGeResult<>(code, message, null);
    }

}