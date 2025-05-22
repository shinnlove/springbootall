/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Tony Zhao
 * @version $Id: ExpressCompanyEnum.java, v 0.1 2025-03-13 14:36 Tony Zhao Exp $$
 */
@Getter
@AllArgsConstructor
public enum ExpressCompanyEnum {

    UNKNOWN("UNKNOWN", "未知公司"),

    YTO("YTO", "圆通速递"),

    EMS("EMS", "EMS"),

    POSTB("POSTB", "邮政小包"),

    SF("SF", "顺丰"),

    YD("YD", "韵达"),

    ZTO("ZTO", "中通"),

    STO("STO", "申通"),

    HTKY("HTKY", "百世汇通"),

    TTKDEX("TTKDEX", "天天快递"),

    DEBANG("DEBANG", "德邦快递"),

    UC("UC", "优速快递"),

    FW("FW", "丰网"),

    ;

    /** 物流公司编码 */
    private String code;

    /** 物流公司名字 */
    private String expressCompanyName;

    public static String getExpressCompanyNameByCode(String code) {
        for (ExpressCompanyEnum expressCompanyEnum : ExpressCompanyEnum.values()) {
            if (expressCompanyEnum.getCode().equals(code)) {
                return expressCompanyEnum.getExpressCompanyName();
            }
        }
        return UNKNOWN.getExpressCompanyName();
    }

    public static ExpressCompanyEnum getExpressCompanyByCode(String code) {
        for (ExpressCompanyEnum expressCompanyEnum : ExpressCompanyEnum.values()) {
            if (expressCompanyEnum.getCode().equals(code)) {
                return expressCompanyEnum;
            }
        }
        return UNKNOWN;
    }

}