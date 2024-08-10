/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 选择支付状态。
 */
@Getter
@AllArgsConstructor
public enum SelectionPayStatusEnum {

    UNKNOWN(0, "未知状态"),

    READY_TO_PAY( 1, "随时可发起支付"),

    PAYING( 2, "支付中"),

    PAID(3, "已支付"),

    ;

    private final int       code;
    private final String    desc;

    /**
     * 根据 value 转换成当前枚举
     *
     * @param code value
     * @return 枚举
     */
    public static SelectionPayStatusEnum getByCode(int code) {
        for (SelectionPayStatusEnum item : values()) {
            if (item.getCode() == code) {
                return item;
            }
        }

        return null;
    }

    public static String getDescByValue(int code) {
        for (SelectionPayStatusEnum item : values()) {
            if (item.getCode() == code) {
                return item.getDesc();
            }
        }

        return "";
    }

}
