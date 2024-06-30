/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Tony Zhao
 * @version $Id: ValidStatusEnum.java, v 0.1 2024-06-30 19:55 Tony Zhao Exp $$
 */
@Getter
@AllArgsConstructor
public enum ValidStatusEnum {

    INVALID(0, "无效"),

    VALID( 1, "有效"),

    ;

    private final int       code;
    private final String    desc;

    /**
     * 根据 value 转换成当前枚举
     *
     * @param code value
     * @return 枚举
     */
    public static ValidStatusEnum getByCode(int code) {
        for (ValidStatusEnum item : values()) {
            if (item.getCode() == code) {
                return item;
            }
        }

        return null;
    }

    public static String getDescByValue(int code) {
        for (ValidStatusEnum item : values()) {
            if (item.getCode() == code) {
                return item.getDesc();
            }
        }

        return "";
    }

}
