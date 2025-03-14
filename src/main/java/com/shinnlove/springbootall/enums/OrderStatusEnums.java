/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Tony Zhao
 * @version $Id: OrderStatusEnums.java, v 0.1 2025-03-13 14:34 Tony Zhao Exp $$
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnums {

    UNKNOWN(-1, "未知状态"),

    UN_PAY(0, "未支付"),

    PAID(1, "已支付"),

    REFUND(2, "已退款"),

    ;

    public final int code;

    public final String desc;

    public static OrderStatusEnums getByCode(int code) {
        for (OrderStatusEnums value : values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return UNKNOWN;
    }

    public static String getDescByCode(int code) {
        for (OrderStatusEnums value : values()) {
            if (value.getCode() == code) {
                return value.getDesc();
            }
        }
        return UNKNOWN.getDesc();
    }

    public static String getNameByCode(int code) {
        for (OrderStatusEnums value : values()) {
            if (value.getCode() == code) {
                return value.name();
            }
        }
        return UNKNOWN.name();
    }

}
