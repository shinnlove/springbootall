/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.models;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Tony Zhao
 * @version $Id: UpperMissionBC.java, v 0.1 2022-01-11 5:33 PM Tony Zhao Exp $$
 */
public class UpperMissionBC implements Serializable {

    private static final long serialVersionUID = 5836621148938503659L;

    /**
     * id
     */
    private Long              id;

    /**
     * 任务id
     */
    private Long              missionId;

    /**
     * UP主mid
     */
    private Long              upperMid;

    /**
     * UP主参与任务状态
     */
    private Integer           joinStatus;

    /**
     * 被选中次数
     */
    private Integer           selectedTimes;

    /**
     * 手机号
     */
    private String            phoneNumber;

    /**
     * qq号码
     */
    private String            qq;

    /**
     * 微信号
     */
    private String            wechatNo;

    /**
     * 备注
     */
    private String            remark;

    /**
     * 创建时间
     */
    private Timestamp         ctime;

    /**
     * 更新时间
     */
    private Timestamp         mtime;

    /**
     * 软删除: 0 否 1是
     */
    private Integer           isDeleted;

    /**
     * 参与编号
     */
    private Long              enrollNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    public Long getUpperMid() {
        return upperMid;
    }

    public void setUpperMid(Long upperMid) {
        this.upperMid = upperMid;
    }

    public Integer getJoinStatus() {
        return joinStatus;
    }

    public void setJoinStatus(Integer joinStatus) {
        this.joinStatus = joinStatus;
    }

    public Integer getSelectedTimes() {
        return selectedTimes;
    }

    public void setSelectedTimes(Integer selectedTimes) {
        this.selectedTimes = selectedTimes;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWechatNo() {
        return wechatNo;
    }

    public void setWechatNo(String wechatNo) {
        this.wechatNo = wechatNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Long getEnrollNo() {
        return enrollNo;
    }

    public void setEnrollNo(Long enrollNo) {
        this.enrollNo = enrollNo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
