/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.third.party.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 印鸽基础校验签名。
 *
 * @author Tony Zhao
 * @version $Id: YinGeSignature.java, v 0.1 2025-03-04 17:24 Tony Zhao Exp $$
 */
@Data
public class YinGeSignature implements Serializable {

    public int timestamp;

    public String sign;

    public String resellerFlag;

    public int version;

    public int signType;

    private static final long serialVersionUID = 1L;

}