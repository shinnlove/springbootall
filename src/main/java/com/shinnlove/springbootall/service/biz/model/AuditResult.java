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
 * @version $Id: AuditResult.java, v 0.1 2022-02-11 4:45 PM Tony Zhao Exp $$
 */
public class AuditResult implements Serializable {

    private static final long serialVersionUID = 6459697016273394434L;

    private long              operatorId;
    private String            name;

    private String            remark;

    public AuditResult() {
    }

    public AuditResult(long operatorId, String name, String remark) {
        this.operatorId = operatorId;
        this.name = name;
        this.remark = remark;
    }

    public long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(long operatorId) {
        this.operatorId = operatorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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