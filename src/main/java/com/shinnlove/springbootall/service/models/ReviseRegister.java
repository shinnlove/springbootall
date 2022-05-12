/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.models;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Tony Zhao
 * @version $Id: ReviseRegister.java, v 0.1 2022-05-12 10:37 AM Tony Zhao Exp $$
 */
public class ReviseRegister implements Serializable, Comparable<ReviseRegister> {

    private static final long serialVersionUID = -4279924441691842391L;

    /**
     * 注册登记id.
     */
    private Long              id;

    /**
     * 改价批次编号No.
     */
    private Long              reviseNo;

    /**
     * 改价父批次编号No.
     */
    private Long              reviseParentNo;

    /**
     * 改价类型 1-自助改价 2-后台改价
     */
    private Integer           reviseType;

    /**
     * 改价流程状态，参考不同流程模板的状态
     */
    private Integer           reviseStatus;

    /**
     * 查询冗余搜索用：改价是否成功，快速识别标志 -1-取消 0-进行中 1-成功 2-失败
     */
    private Integer           reviseSuccess;

    /**
     * 改价关联的订单id
     */
    private Integer           orderId;

    /**
     * 查询冗余搜索用: 改价关联的订单编号
     */
    private Long              orderNo;

    /**
     * 订单快照id
     */
    private Long              orderSnapshotId;

    /**
     * 改价发起人类型枚举 -1-系统 1-UP主 2-MCN机构 3-运营后台操作人员
     */
    private Integer           creatorType;

    /**
     * 改价发起人id，查询冗余用，如upperMid、accountId或agentId
     */
    private Long              creatorId;

    /**
     * 创建者昵称、账号名、人员域账号邮箱
     */
    private String            creatorName;

    /**
     * 创建时间
     */
    private Timestamp         ctime;

    /**
     * 更新时间
     */
    private Timestamp         mtime;

    /**
     * 备注
     */
    private String            remark;

    public ReviseRegister() {
    }

    public ReviseRegister(Long id, Long reviseNo, Long reviseParentNo, Integer reviseType,
                          Integer reviseStatus, Integer reviseSuccess, Integer orderId,
                          Long orderNo, Long orderSnapshotId, Integer creatorType, Long creatorId,
                          String creatorName, Timestamp ctime, Timestamp mtime, String remark) {
        this.id = id;
        this.reviseNo = reviseNo;
        this.reviseParentNo = reviseParentNo;
        this.reviseType = reviseType;
        this.reviseStatus = reviseStatus;
        this.reviseSuccess = reviseSuccess;
        this.orderId = orderId;
        this.orderNo = orderNo;
        this.orderSnapshotId = orderSnapshotId;
        this.creatorType = creatorType;
        this.creatorId = creatorId;
        this.creatorName = creatorName;
        this.ctime = ctime;
        this.mtime = mtime;
        this.remark = remark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReviseNo() {
        return reviseNo;
    }

    public void setReviseNo(Long reviseNo) {
        this.reviseNo = reviseNo;
    }

    public Long getReviseParentNo() {
        return reviseParentNo;
    }

    public void setReviseParentNo(Long reviseParentNo) {
        this.reviseParentNo = reviseParentNo;
    }

    public Integer getReviseType() {
        return reviseType;
    }

    public void setReviseType(Integer reviseType) {
        this.reviseType = reviseType;
    }

    public Integer getReviseStatus() {
        return reviseStatus;
    }

    public void setReviseStatus(Integer reviseStatus) {
        this.reviseStatus = reviseStatus;
    }

    public Integer getReviseSuccess() {
        return reviseSuccess;
    }

    public void setReviseSuccess(Integer reviseSuccess) {
        this.reviseSuccess = reviseSuccess;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Long getOrderSnapshotId() {
        return orderSnapshotId;
    }

    public void setOrderSnapshotId(Long orderSnapshotId) {
        this.orderSnapshotId = orderSnapshotId;
    }

    public Integer getCreatorType() {
        return creatorType;
    }

    public void setCreatorType(Integer creatorType) {
        this.creatorType = creatorType;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Timestamp getCtime() {
        return ctime;
    }

    public void setCtime(Timestamp ctime) {
        this.ctime = ctime;
    }

    public Timestamp getMtime() {
        return mtime;
    }

    public void setMtime(Timestamp mtime) {
        this.mtime = mtime;
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

    @Override
    public int compareTo(ReviseRegister o) {
        if (this.ctime.getTime() < o.getCtime().getTime()) {
            return -1;
        } else if (this.ctime.getTime() == o.getCtime().getTime()) {
            return 0;
        } else {
            return 1;
        }
    }

}