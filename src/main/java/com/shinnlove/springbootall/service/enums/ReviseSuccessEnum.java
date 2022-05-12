/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.enums;

/**
 * @author Tony Zhao
 * @version $Id: ReviseSuccessEnum.java, v 0.1 2022-05-12 10:50 AM Tony Zhao Exp $$
 */
public enum ReviseSuccessEnum {

    CANCEL(-1,"取消"),

    UNDERWAY(0,"进行中"),

    SUCCESS(1, "成功"),

    FAIL(2, "失败"),

    ;

    private Integer code;
    private String  desc;

    ReviseSuccessEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }


}