/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.enums;

/**
 * @author Tony Zhao
 * @version $Id: ReviseAttitudeType.java, v 0.1 2022-05-12 10:44 AM Tony Zhao Exp $$
 */
public enum ReviseAttitudeType {

    UNKNOWN(-1, "未表态"),

    REFUSE(0,"拒绝"),

    APPROVE(1, "接受"),

    ;

    private int     code;
    private String  desc;

    ReviseAttitudeType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static ReviseAttitudeType getByCode(int code){
        for (ReviseAttitudeType type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return UNKNOWN;
    }

}