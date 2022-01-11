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

    SAVE_DRAFT(20101, 20001, "保存草稿"),

    PUBLISH(20102, 20001,"发布任务"),

    AUTO_ONLINE(20103, 20001,"自动上线"),

    AUTO_OFFLINE(20104, 20001,"自动下线"),

    TERMINATE_MISSION(20105, 20001,"终止任务"),

    CANCEL_MISSION(20106, 20001,"取消任务"),

    UPPER_SIGN_UP(30101, 30001, "UP主报名B/C活动"),

    OPERATOR_SELECTED(30102, 30001, "运营选中"),

    OPERATOR_CANCELED(30103, 30001, "运营取消"),

    MANUALLY_AC(30104, 30001, "手动完成"),

    OPERATOR_REVOKE(30105, 30001, "撤销资格"),

    UPPER_ENROLL(30201, 30002, "UP主报名D活动"),

    ENROLL_ATTACH_TAG(30202, 30002, "绑定标签参与"),

    DETACH_ALL_TAG(30203, 30002, "取消绑定标签"),

    ATTACH_TAG_ENROLL(30204, 30002, "标签报名"),

    FINISH_NO_REWARDS(30205, 30002, "完成活动"),

    FINISH_WITH_REWARDS(30206, 30002, "导入中奖名单"),

    FINANCIAL_PAID(30207, 30002, "财务已打款"),

    D_UPPER_CANCEL(30208, 30002, "终止任务"),

    ;

    ActionType(int actionId, int templateId, String desc) {
        this.actionId = actionId;
        this.templateId = templateId;
        this.desc = desc;
    }

    private int    actionId;
    private int    templateId;
    private String desc;

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