/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.enums;

import java.util.Arrays;
import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: ReviseStatus.java, v 0.1 2022-05-12 10:36 AM Tony Zhao Exp $$
 */
public enum ReviseStatus {

    INIT(-1, "流程初始化"),

    AUDIT(1, "待政策审核"),

    AD_CONFIRM(2,"待客户确认"),

    UPPER_CONFIRM(3,"待UP主确认"),

    ACCEPTED(4,"改价项已完成"),

    REJECTED(5,"改价项已拒绝"),

    CANCELED(6,"改价项已取消"),

    ;

    private int    code;
    private String desc;

    ReviseStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static ReviseStatus getByCode(int code) {
        for (ReviseStatus value : ReviseStatus.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return INIT;
    }

    public static List<Integer> REVISING = Arrays.asList(AUDIT.code, AD_CONFIRM.code,
            UPPER_CONFIRM.code);

    public static List<Integer> TERMINATED = Arrays.asList(REJECTED.code, CANCELED.code);

    public static List<Integer> SELF_SERVICE_AD_SIDE_SEE_SOURCE_STATUS = Arrays.asList(INIT.code, AD_CONFIRM.code);

    public static List<Integer> SELF_SERVICE_UP_SIDE_SEE_SOURCE_STATUS = Arrays.asList(INIT.code, AD_CONFIRM.code);

    public static List<Integer> PLATFORM_AD_SIDE_SEE_SOURCE_STATUS = Arrays.asList(INIT.code, AD_CONFIRM.code,
            UPPER_CONFIRM.code);

    public static List<Integer> PLATFORM_UP_SIDE_SEE_SOURCE_STATUS = Arrays.asList(INIT.code, UPPER_CONFIRM.code);

}