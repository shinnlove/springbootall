/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Tony Zhao
 * @version $Id: GildSelectedEnum.java, v 0.1 2025-03-30 22:54 Tony Zhao Exp $$
 */
@Getter
@AllArgsConstructor
public enum GildSelectedEnum {

    NO_LIMITATION(-1, "无限制"),

    /**
     * 未赠送黄金
     */
    NO_GILD(0, "未抽中黄金"),

    /**
     * 赠送黄金
     */
    GILD_SELECTED(1, "抽中黄金");

    private Integer code;
    private String desc;

    public static GildSelectedEnum getByName(String name) {
        for (GildSelectedEnum value : values()) {
            if (value.name().equals(name)) {
                return value;
            }
        }
        return NO_GILD;
    }

    public static GildSelectedEnum getByCode(Integer code) {
        for (GildSelectedEnum value : values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return NO_GILD;
    }

    public static String getDescByCode(Integer code) {
        for (GildSelectedEnum value : values()) {
            if (value.getCode().equals(code)) {
                return value.getDesc();
            }
        }
        return NO_GILD.getDesc();
    }

}