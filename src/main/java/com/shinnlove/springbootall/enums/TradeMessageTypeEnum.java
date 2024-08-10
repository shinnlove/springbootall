/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Tony Zhao
 * @version $Id: TradeMessageTypeEnum.java, v 0.1 2024-07-30 11:24 Tony Zhao Exp $$
 */
@Getter
@AllArgsConstructor
public enum TradeMessageTypeEnum {

    UNKNOWN_TRADE_TYPE(0, "", "未知类型"),

    TRADE_TRADE_CREATE(1, "trade_TradeCreate", "交易创建"),

    TRADE_TRADE_BUYER_PAY(2, "trade_TradeBuyerPay", "卖家付款(即商家待发货)"),

    TRADE_TRADE_PAID(3, "trade_TradePaid", "交易支付"),

    TRADE_TRADE_SELLER_SHIP(4, "trade_TradeSellerShip", "卖家发货"),

    ;

    private final int       code;
    private final String    typeKey;
    private final String    desc;

    public static TradeMessageTypeEnum getByCode(int code) {
        for (TradeMessageTypeEnum item : values()) {
            if (item.getCode() == code) {
                return item;
            }
        }

        return UNKNOWN_TRADE_TYPE;
    }

    public static String getKeyByCode(int code) {
        for (TradeMessageTypeEnum item : values()) {
            if (item.getCode() == code) {
                return item.getDesc();
            }
        }

        return UNKNOWN_TRADE_TYPE.getTypeKey();
    }

}