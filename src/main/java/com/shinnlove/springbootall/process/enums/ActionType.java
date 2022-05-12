/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.enums;

/**
 * Process action type enums for status machine.<br>
 *
 * @author Tony Zhao
 * @version $Id: ActionType.java, v 0.1 2021-07-28 4:49 PM Tony Zhao Exp $$
 */
public enum ActionType {

    AUDIT_ACCEPT_2_AD(30001, 30000, "审核同意到广告主确认"),

    AUDIT_ACCEPT_2_UP(30002, 30000, "审核同意到UP主确认"),

    AUDIT_ACCEPT_2_AC(30003, 30000, "审核同意到已完成"),

    AD_ACCEPT_2_UP(30004, 30000, "广告主同意到UP主确认"),

    AD_ACCEPT_2_AC(30005, 30000, "广告主同意到已完成"),

    UP_ACCEPT(30006, 30000, "UP主同意到已完成"),

    REVISE_AUDIT_REJECT(30007, 30000, "改价审核拒绝"),

    REVISE_AD_REJECT(30008, 30000, "改价投放端拒绝"),

    REVISE_UP_REJECT(30009, 30000, "改价接单端拒绝"),

    REVISE_CANCEL(30010, 30000, "改价已关闭"),

    ORDER_AUDIT_CONFIRM(30101, 30001, "订单金额改价项风控审核通过"),

    ORDER_REVISE_AC(30102, 30001, "订单金额改价项客户同意"),

    ORDER_AUDIT_REJECT(30103, 30001, "订单金额改价项拒绝"),

    ORDER_AD_REJECT(30104, 30001, "订单金额改价项客户拒绝"),

    ORDER_REVISE_REJECT(30105, 30001, "订单金额改价项被拒绝"),

    ORDER_REVISE_CANCEL(30106, 30001, "订单金额改价项取消"),

    FEE_AUDIT_ACCEPT(30201, 30002, "信息技术费改价项风控审核通过"),

    FEE_ADVERTISER_ACCEPT(30202, 30002, "信息技术费改价项客户同意"),

    FEE_AUDIT_REJECT(30203, 30002, "信息技术费改价项拒绝"),

    FEE_AD_REJECT(30204, 30002, "信息技术费改价项客户拒绝"),

    FEE_REVISE_REJECT(30205, 30002, "信息技术费改价项被拒绝"),

    FEE_REVISE_CANCEL(30206, 30002, "信息技术费改价项取消"),

    EXPENSE_AUDIT_ACCEPT(30301, 30003, "订单支出项审核同意"),

    EXPENSE_AUDIT_REJECT(30302, 30003, "订单支出项改价政策驳回"),

    EXPENSE_REVISE_REJECT(30303, 30003, "订单支出项改价被拒绝"),

    EXPENSE_REVISE_CANCEL(30304, 30003, "订单支出项改价关闭"),

    UPPER_AUDIT_ACCEPT(30401, 30004, "UP主收益项政审通过"),

    UPPER_REVISE_ACCEPT(30402, 30004, "UP主收益项UP主同意"),

    UPPER_AUDIT_REJECT(30403, 30004, "UP主收益项改价项政审拒绝"),

    UPPER_UP_REJECT(30404, 30004, "UP主收益项改价项UP主拒绝"),

    UPPER_REVISE_REJECT(30405, 30004, "UP主收益项改价项被拒绝"),

    UPPER_REVISE_CANCEL(30406, 30004, "UP主收益项改价项取消"),

    VENDOR_AUDIT_ACCEPT(30501, 30005, "服务商改价项审核同意"),

    VENDOR_AUDIT_REJECT(30502, 30005, "服务商改价项政审拒绝"),

    VENDOR_REVISE_REJECT(30503, 30005, "服务商改价项被拒绝"),

    VENDOR_REVISE_CANCEL(30504, 30005, "服务商改价项已取消"),

    ;

    private int    actionId;
    private int    templateId;
    private String desc;

    ActionType(int actionId, int templateId, String desc) {
        this.actionId = actionId;
        this.templateId = templateId;
        this.desc = desc;
    }

    public int getActionId() {
        return actionId;
    }

    public int getTemplateId() {
        return templateId;
    }

    public String getDesc() {
        return desc;
    }

    public static ActionType getByActionId(int actionId) {
        for (ActionType type : values()) {
            if (actionId == type.getActionId()) {
                return type;
            }
        }
        return null;
    }

}