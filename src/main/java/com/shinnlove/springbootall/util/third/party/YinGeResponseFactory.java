/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.third.party;

import com.shinnlove.springbootall.util.third.party.dto.YinGeResponse;

/**
 * @author Tony Zhao
 * @version $Id: YinGeResponseFactory.java, v 0.1 2025-03-06 22:31 Tony Zhao Exp $$
 */
public class YinGeResponseFactory {

    public static <T> YinGeResponse<T> success(T data) {
        return new YinGeResponse<>(0, "", data, null);
    }

    public static <T> YinGeResponse<T> fail(int code, String msg) {
        return new YinGeResponse<>(code, msg, null, null);
    }

}