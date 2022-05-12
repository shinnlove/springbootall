/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.enums;

/**
 * @author Tony Zhao
 * @version $Id: RevisePolicyAuditStatus.java, v 0.1 2022-05-12 10:48 AM Tony Zhao Exp $$
 */
public enum RevisePolicyAuditStatus {

    ALL(-2, "全部"),

    CANCEL(-1, "已取消"),

    TO_BE_AUDIT(0, "待审核"),

    APPROVE(1, "通过"),

    REFUSE(2,"驳回");

    private int    code;
    private String desc;

    RevisePolicyAuditStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static RevisePolicyAuditStatus getByCode(int code) {
        for (RevisePolicyAuditStatus type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return CANCEL;
    }

}
