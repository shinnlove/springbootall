/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.enums;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: UpperMissionStatus.java, v 0.1 2022-01-11 5:35 PM Tony Zhao Exp $$
 */
public enum UpperMissionStatus {

    UNKNOWN(-1,"未报名"),
    ENROLLED(1, "已报名"),
    UNDERWAY(2, "进行中"),
    COMPLETED(3, "已完成"),
    CANCELED(4, "已取消"),
    WAIT_PAYED(5, "待打款"),
    ;

    private Integer code;
    private String desc;

    UpperMissionStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static String getDescByCode(Integer code){
        for(UpperMissionStatus bean : UpperMissionStatus.values()){
            if (bean.code.equals(code)){
                return bean.getDesc();
            }
        }
        return UNKNOWN.desc;
    }

    public static final List<Integer> BEGINNING = Lists.newArrayList(
            UpperMissionStatus.UNDERWAY.getCode(),
            UpperMissionStatus.WAIT_PAYED.getCode()
    );

    public static List<Integer> afterJoin() {
        return Arrays.asList(UNDERWAY.code, COMPLETED.code, WAIT_PAYED.code);
    }

}