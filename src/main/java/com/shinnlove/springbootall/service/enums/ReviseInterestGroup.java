/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.enums;

import java.util.Arrays;
import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: ReviseInterestGroup.java, v 0.1 2022-05-12 10:44 AM Tony Zhao Exp $$
 */
public enum ReviseInterestGroup {

    PLATFORM(-1, "平台"),

    ALL(0, "所有"),

    OPERATING(1, "运营/平台"),

    ADVERTISER(2, "投放端:广告主/代理商"),

    UP(3,"接单端:UP主/MCN"),

    VENDOR(4, "服务商"),

    NONE_OP(5, "非运营"),

    ;

    private int    code;
    private String desc;

    ReviseInterestGroup(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static ReviseInterestGroup getByCode(int code){
        for (ReviseInterestGroup type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return PLATFORM;
    }

    public static List<Integer> VISIBLE = Arrays.asList(ADVERTISER.code, UP.code);

    public static List<Integer> PAY_LOG_VISIBLE = Arrays.asList(PLATFORM.code, OPERATING.code, ADVERTISER.code, ALL.code);

}