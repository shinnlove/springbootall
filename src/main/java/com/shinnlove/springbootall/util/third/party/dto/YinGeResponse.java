/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.third.party.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tony Zhao
 * @version $Id: YinGeResponse.java, v 0.1 2025-03-06 19:51 Tony Zhao Exp $$
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YinGeResponse<T> {

    private int code;

    private String msg;

    private T data;

    private String error;

}