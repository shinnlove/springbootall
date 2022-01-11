/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.enums;

/**
 * @author Tony Zhao
 * @version $Id: ProcessType.java, v 0.1 2022-01-10 11:09 PM Tony Zhao Exp $$
 */
public enum ProcessType {

    UNKNOWN(-1,"未知"),

    RECRUIT_MISSION(1, "悬赏任务"),

    MISSION_JOIN_USER(2, "悬赏任务参与用户"),

    ;

    private Integer code;

    private String desc;

    ProcessType(Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static String getDescByCode(Integer code){
        for (ProcessType type : ProcessType.values()) {
            if (type.code.equals(code)){
                return type.getDesc();
            }
        }
        return UNKNOWN.desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
