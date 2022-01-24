/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.enums;

/**
 * @author Tony Zhao
 * @version $Id: MissionProcessStatus.java, v 0.1 2022-01-11 5:29 PM Tony Zhao Exp $$
 */
public enum MissionProcessStatus {

    UNKNOWN(-1, "未知"),

    SCRATCH(1, "草稿"),

    IN_FORCE(2, "已生效"),

    BEGINNING(3, "已开始"),

    OVER(4, "已结束"),

    ;

    private int    code;
    private String desc;

    MissionProcessStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static String getDescByCode(Integer code) {
        for (MissionProcessStatus bean : MissionProcessStatus.values()) {
            if (bean.getCode() == code) {
                return bean.getDesc();
            }
        }
        return UNKNOWN.desc;
    }

}
