/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Tony Zhao
 * @version $Id: ReviseItem.java, v 0.1 2022-05-12 10:57 AM Tony Zhao Exp $$
 */
public class ReviseItem implements Serializable {

    private static final long serialVersionUID = -243404323711271178L;

    /**
     * id
     */
    private Long              id;

    /**
     * 改价父批次编号No
     */
    private Long              reviseParentNo;

    /**
     * 改价批次编号No.
     */
    private Long              reviseNo;

    /**
     * 改价类型枚举（1-订单价格 2-其他信息技术服务费 3-订单支出 4-UP主订单收益 5-服务商订单收益）
     */
    private Integer           itemType;

    /** 改价类型枚举描述（1-订单价格 2-其他信息技术服务费 3-订单支出 4-UP主订单收益 5-服务商订单收益） */
    private String            itemTypeDesc;

    /**
     * 改价前的价格
     */
    private BigDecimal        beforeValue;

    /**
     * 改价后的价格
     */
    private BigDecimal        afterValue;

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

    public ReviseItem() {
    }

    public ReviseItem(Long id, Long reviseParentNo, Long reviseNo, Integer itemType,
                      String itemTypeDesc, BigDecimal beforeValue, BigDecimal afterValue,
                      Timestamp ctime, Timestamp mtime, String remark) {
        this.id = id;
        this.reviseParentNo = reviseParentNo;
        this.reviseNo = reviseNo;
        this.itemType = itemType;
        this.itemTypeDesc = itemTypeDesc;
        this.beforeValue = beforeValue;
        this.afterValue = afterValue;
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

    public Long getReviseParentNo() {
        return reviseParentNo;
    }

    public void setReviseParentNo(Long reviseParentNo) {
        this.reviseParentNo = reviseParentNo;
    }

    public Long getReviseNo() {
        return reviseNo;
    }

    public void setReviseNo(Long reviseNo) {
        this.reviseNo = reviseNo;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public String getItemTypeDesc() {
        return itemTypeDesc;
    }

    public void setItemTypeDesc(String itemTypeDesc) {
        this.itemTypeDesc = itemTypeDesc;
    }

    public BigDecimal getBeforeValue() {
        return beforeValue;
    }

    public void setBeforeValue(BigDecimal beforeValue) {
        this.beforeValue = beforeValue;
    }

    public BigDecimal getAfterValue() {
        return afterValue;
    }

    public void setAfterValue(BigDecimal afterValue) {
        this.afterValue = afterValue;
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

}