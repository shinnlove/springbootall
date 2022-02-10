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
 * @version $Id: ReviseInfo.java, v 0.1 2022-02-10 4:14 PM Tony Zhao Exp $$
 */
public class ReviseInfo implements Serializable {

    private int    creatorId;
    private String creator;
    private String remark;

    public ReviseInfo() {
    }

    public ReviseInfo(int creatorId, String creator, String remark) {
        this.creatorId = creatorId;
        this.creator = creator;
        this.remark = remark;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}