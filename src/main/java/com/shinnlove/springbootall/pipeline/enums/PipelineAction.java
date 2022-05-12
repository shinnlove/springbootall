/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.pipeline.enums;

/**
 * @author Tony Zhao
 * @version $Id: PipelineAction.java, v 0.1 2022-05-10 5:23 PM Tony Zhao Exp $$
 */
public enum PipelineAction {

    UNKNOWN(-1, -1, "未知流程pipeline"),

    DRAFT_UPLOAD_HANDLE(40101, 40000, "上传终稿消息处理"),

    DRAFT_BUSINESS_FINAL_AUDIT_APPROVE(40102, 40000, "业务终审审核通过消息处理"),

    DRAFT_FINAL_DRAFT_AUDIT_REJECT(40103, 40000, "业务终审审核通过消息处理"),

    DRAFT_ARCHIVE_LOCK_HANDLE(40104, 40000, "终稿锁定消息处理"),

    DRAFT_DELETE_FINAL_DRAFT(40105, 40000, "删除终稿消息处理"),

    DRAFT_LAUNCH_VIDEO(40106, 40000, "视频上线消息处理"),

    ;

    private int    actionId;
    private int    templateId;
    private String desc;

    PipelineAction(int actionId, int templateId, String desc) {
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

    public static PipelineAction getByActionId(int actionId) {
        for (PipelineAction type : values()) {
            if (actionId == type.getActionId()) {
                return type;
            }
        }
        return UNKNOWN;
    }

}