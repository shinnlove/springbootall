/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.enums;

/**
 * @author Tony Zhao
 * @version $Id: ReviseType.java, v 0.1 2022-05-12 10:38 AM Tony Zhao Exp $$
 */
public enum ReviseType {

    SELF_SERVICE(1, "自助改价"),

    PLATFORM(2,"后台改价"),

    UNKNOWN(-1,"未知"),

    ;

    /** code */
    private int    code;
    /** description */
    private String desc;

    ReviseType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static ReviseType getByCode(int code){
        for (ReviseType type : values()) {
            if (code == type.getCode()) {
                return type;
            }
        }
        return UNKNOWN;
    }

}