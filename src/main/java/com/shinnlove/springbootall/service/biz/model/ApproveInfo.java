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
 * @version $Id: ApproveInfo.java, v 0.1 2022-02-10 5:19 PM Tony Zhao Exp $$
 */
public class ApproveInfo implements Serializable {

    private static final long serialVersionUID = -5166282496875773268L;

    private int               approve;

    private int               operatorId;
    private String            operator;
    private String            remark;

    public ApproveInfo() {
    }

    public ApproveInfo(int approve, int operatorId, String operator, String remark) {
        this.approve = approve;
        this.operatorId = operatorId;
        this.operator = operator;
        this.remark = remark;
    }

    public int getApprove() {
        return approve;
    }

    public void setApprove(int approve) {
        this.approve = approve;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
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