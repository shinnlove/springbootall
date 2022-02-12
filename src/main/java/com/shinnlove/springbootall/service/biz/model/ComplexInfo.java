/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.biz.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Tony Zhao
 * @version $Id: ComplexInfo.java, v 0.1 2022-02-12 10:31 PM Tony Zhao Exp $$
 */
public class ComplexInfo implements Serializable {

    private static final long serialVersionUID = 2162538554709463688L;

    private ReviseInfo        reviseInfo;

    private ApproveInfo       approveInfo;

    public ComplexInfo() {
    }

    public ComplexInfo(ReviseInfo reviseInfo, ApproveInfo approveInfo) {
        this.reviseInfo = reviseInfo;
        this.approveInfo = approveInfo;
    }

    public ReviseInfo getReviseInfo() {
        return reviseInfo;
    }

    public void setReviseInfo(ReviseInfo reviseInfo) {
        this.reviseInfo = reviseInfo;
    }

    public ApproveInfo getApproveInfo() {
        return approveInfo;
    }

    public void setApproveInfo(ApproveInfo approveInfo) {
        this.approveInfo = approveInfo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}