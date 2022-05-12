/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.enums;

/**
 * @author Tony Zhao
 * @version $Id: ReviseItemType.java, v 0.1 2022-05-12 10:33 AM Tony Zhao Exp $$
 */
public enum ReviseItemType {

    NONE(-1, -1, "无", -1),

    REVISE_PARENT(0, 30000, "父流程改价项", 0),

    ORDER_PRICE(1, 30001, "订单金额", 2),

    SERVICE_FEE(2, 30002, "其他信息技术费", 2),

    ORDER_EXPENSE(3, 30003, "订单支出", 1),

    UPPER_PROFIT(4, 30004, "UP主订单收益", 3),

    SERVICE_PROVIDER_INCOME(5, 30005, "服务商订单收益", 4),

    ;

    private int     code;
    private int     templateId;
    private String  desc;
    private int     interestGroup;

    ReviseItemType(int code, int templateId, String desc, int interestGroup) {
        this.code = code;
        this.templateId = templateId;
        this.desc = desc;
        this.interestGroup = interestGroup;
    }

    public int getCode() {
        return code;
    }

    public int getTemplateId() {
        return templateId;
    }

    public String getDesc() {
        return desc;
    }

    public int getInterestGroup() {
        return interestGroup;
    }

    public static ReviseItemType getByCode(int code) {
        for (ReviseItemType type : values()) {
            if (code == type.code) {
                return type;
            }
        }
        return NONE;
    }

    public static ReviseItemType getByTemplateId(int templateId) {
        for (ReviseItemType type : values()) {
            if (templateId == type.templateId) {
                return type;
            }
        }
        return NONE;
    }

}