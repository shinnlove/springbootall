/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.models;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 改价流程核心领域模型。
 *
 * @author Tony Zhao
 * @version $Id: ReviseAttitude.java, v 0.1 2022-05-12 10:34 AM Tony Zhao Exp $$
 */
public class ReviseAttitude implements Serializable {

    /** uuid */
    private static final long serialVersionUID = 7364205148351854701L;

    /** 订单id */
    private int               orderId;
    /** 改价流程编号(或父流程编号) */
    private long              refUniqueNo;

    /** 操作员类型 */
    private int               operatorType;
    /** 操作员id */
    private long              operatorId;
    /** 操作员名称 */
    private String            operator;

    /** 同意或拒绝 1-同意 0-拒绝 */
    private int               attitude;
    /** 备注 */
    private String            remark;

    /**
     * Constructor for reflect.
     */
    public ReviseAttitude() {
    }

    public ReviseAttitude(int orderId) {
        this.orderId = orderId;
    }

    public ReviseAttitude(int orderId, long refUniqueNo) {
        this.orderId = orderId;
        this.refUniqueNo = refUniqueNo;
    }

    /**
     * Constructor with all arguments.
     *
     * @param orderId
     * @param refUniqueNo
     * @param operatorType
     * @param operatorId
     * @param operator
     * @param attitude
     * @param remark
     */
    public ReviseAttitude(int orderId, long refUniqueNo, int operatorType, long operatorId,
                          String operator, int attitude, String remark) {
        this.orderId = orderId;
        this.refUniqueNo = refUniqueNo;
        this.operatorType = operatorType;
        this.operatorId = operatorId;
        this.operator = operator;
        this.attitude = attitude;
        this.remark = remark;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public long getRefUniqueNo() {
        return refUniqueNo;
    }

    public void setRefUniqueNo(long refUniqueNo) {
        this.refUniqueNo = refUniqueNo;
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

    public int getAttitude() {
        return attitude;
    }

    public void setAttitude(int attitude) {
        this.attitude = attitude;
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