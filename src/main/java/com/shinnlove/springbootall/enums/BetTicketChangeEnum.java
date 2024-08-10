/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Tony Zhao
 * @version $Id: BetTicketChangeEnum.java, v 0.1 2024-07-10 15:06 Tony Zhao Exp $$
 */
@Getter
@AllArgsConstructor
public enum BetTicketChangeEnum {

    ADD_BET_TICKET(1, "增加竞猜券"),

    REDUCE_BET_TICKET(2, "减少竞猜券"),

    ;

    private final int       code;
    private final String    desc;

    public static BetTicketChangeEnum getByCode(int code) {
        for (BetTicketChangeEnum item : values()) {
            if (item.getCode() == code) {
                return item;
            }
        }

        return null;
    }

    public static String getDescByValue(int code) {
        for (BetTicketChangeEnum item : values()) {
            if (item.getCode() == code) {
                return item.getDesc();
            }
        }

        return "";
    }

}