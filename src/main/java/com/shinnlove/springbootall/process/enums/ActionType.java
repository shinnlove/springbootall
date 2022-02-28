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

    PARENT_AUDIT_ACCEPT(30001, 30000, "审核同意"),

    PARENT_ADVERTISER_ACCEPT(30002, 30000, "客户同意"),

    PARENT_UPPER_ACCEPT(30003, 30000, "UP主同意"),

    REVISE_REJECT(30004, 30000, "改价全部拒绝"),

    REVISE_CANCEL(30005, 30000, "改价全部取消"),

    ORDER_PENDING_CONFIRM(30101, 30001, "订单金额改价项风控审核通过待客户确认"),

    ORDER_REVISE_ADVERTISER_ACCEPT(30102, 30001, "订单金额改价项客户同意"),

    ORDER_REVISE_REJECT(30103, 30001, "订单金额改价项拒绝"),

    ORDER_REVISE_REJECT_2(30104, 30001, "订单金额改价项拒绝"),

    ORDER_REVISE_CANCEL(30105, 30001, "订单金额改价项取消"),

    ORDER_REVISE_CANCEL_2(30106, 30001, "订单金额改价项取消"),

    FEE_REVISE_AUDIT(30201, 30002, "信息技术费项触发阈值待风控审核"),

    FEE_ADVERTISER_ACCEPT(30202, 30002, "信息技术费客户同意"),

    FEE_REVISE_AC(30203, 30002, "信息技术费改价项完成"),

    FEE_REVISE_REJECT(30204, 30002, "信息技术费改价项拒绝"),

    FEE_REVISE_CANCEL(30205, 30002, "信息技术费改价项取消"),

    EXPENSE_REVISE_AUDIT(30301, 30003, "订单支出项触发阈值待风控审核"),

    EXPENSE_REVISE_AC(30302, 30003, "订单支出改价项完成"),

    EXPENSE_REVISE_REJECT(30303, 30003, "订单支出改价拒绝"),

    EXPENSE_REVISE_CANCEL(30304, 30003, "订单支出改价项关闭"),

    UPPER_REVISE_AUDIT(30401, 30004, "UP主收益项触发阈值待风控审核"),

    UPPER_PROFIT_ACCEPT(30402, 30004, "UP主收益项UP主同意"),

    UPPER_REVISE_AC(30403, 30004, "UP主收益项改价项完成"),

    UPPER_REVISE_REJECT(30404, 30004, "UP主收益项改价项拒绝"),

    UPPER_REVISE_CANCEL(30405, 30004, "UP主收益项改价项取消"),

    VENDOR_PENDING_AUDIT(30501, 30005, "服务商改价项触发阈值待风控审核"),

    VENDOR_REVISE_AC(30502, 30005, "服务商改价项已完成"),

    VENDOR_REVISE_REJECT(30503, 30005, "服务商改价项已拒绝"),

    VENDOR_REVISE_CANCEL(30504, 30005, "服务商改价已取消"),

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

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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