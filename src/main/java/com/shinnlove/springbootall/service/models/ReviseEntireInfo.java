/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.models;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Revise entire field model for self service revise and platform revise.
 *
 * @author Tony Zhao
 * @version $Id: ReviseEntireInfo.java, v 0.1 2022-02-28 2:38 PM Tony Zhao Exp $$
 */
public class ReviseEntireInfo implements Serializable {

    /** uuid */
    private static final long      serialVersionUID = 3111218655359642310L;

    /** 订单id */
    private Integer                orderId;

    /** 是否后台改价 */
    private boolean                isPlatform;

    /** 是否触发政审 */
    private boolean                needPolicyReview;

    /** 操作人类型 */
    private int                    operatorType;
    /** 操作人id */
    private long                   operatorId;
    /** 操作人名称 */
    private String                 operator;

    /** 改价备注 */
    private String                 remark;

    /** 具体改价项 */
    private List<SubmitReviseItem> reviseItems;

    public ReviseEntireInfo() {
    }

    public ReviseEntireInfo(Integer orderId) {
        this.orderId = orderId;
    }

    public ReviseEntireInfo(Integer orderId, List<SubmitReviseItem> reviseItems) {
        this.orderId = orderId;
        this.reviseItems = reviseItems;
    }

    public ReviseEntireInfo(Integer orderId, boolean isPlatform, boolean needPolicyReview,
                            int operatorType, long operatorId, String operator, String remark,
                            List<SubmitReviseItem> reviseItems) {
        this.orderId = orderId;
        this.isPlatform = isPlatform;
        this.needPolicyReview = needPolicyReview;
        this.operatorType = operatorType;
        this.operatorId = operatorId;
        this.operator = operator;
        this.remark = remark;
        this.reviseItems = reviseItems;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public boolean isPlatform() {
        return isPlatform;
    }

    public void setPlatform(boolean platform) {
        isPlatform = platform;
    }

    public boolean isNeedPolicyReview() {
        return needPolicyReview;
    }

    public void setNeedPolicyReview(boolean needPolicyReview) {
        this.needPolicyReview = needPolicyReview;
    }

    public int getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(int operatorType) {
        this.operatorType = operatorType;
    }

    public long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(long operatorId) {
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

    public List<SubmitReviseItem> getReviseItems() {
        return reviseItems;
    }

    public void setReviseItems(List<SubmitReviseItem> reviseItems) {
        this.reviseItems = reviseItems;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}