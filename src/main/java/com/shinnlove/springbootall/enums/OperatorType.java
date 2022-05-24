/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.enums;

/**
 * 一个BiMap使用的枚举。
 *
 * @author Tony Zhao
 * @version $Id: OperatorType.java, v 0.1 2022-05-22 10:50 AM Tony Zhao Exp $$
 */
public enum OperatorType {

    ADVERTISER(0, "广告主"),

    UP(0, "up主"),

    ;

    OperatorType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int    code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}