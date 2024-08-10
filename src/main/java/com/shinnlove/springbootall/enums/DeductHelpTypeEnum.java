/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 帮助类型减免枚举类。
 */
@Getter
@AllArgsConstructor
public enum DeductHelpTypeEnum {

    UNKNOWN(0, "未知类型扣减"),

    SYSTEM_RANDOM_DEDUCT( 1, "系统随机扣减"),

    FRIEND_ASSIST_DEDUCT( 2, "好友助力扣减"),

    ;

    private final int       code;
    private final String    desc;

    /**
     * 根据 value 转换成当前枚举
     *
     * @param code value
     * @return 枚举
     */
    public static DeductHelpTypeEnum getByCode(int code) {
        for (DeductHelpTypeEnum item : values()) {
            if (item.getCode() == code) {
                return item;
            }
        }

        return null;
    }

    public static String getDescByValue(int code) {
        for (DeductHelpTypeEnum item : values()) {
            if (item.getCode() == code) {
                return item.getDesc();
            }
        }

        return "";
    }

}
