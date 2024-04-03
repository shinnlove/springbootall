package com.shinnlove.springbootall.mybatis.generator.model;

import java.io.Serializable;
import java.util.Date;

public class LogReward implements Serializable {
    private Long id;

    private Long guid;

    private Long userId;

    private Byte rewardStatus;

    private Long rewardPackageId;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGuid() {
        return guid;
    }

    public void setGuid(Long guid) {
        this.guid = guid;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Byte getRewardStatus() {
        return rewardStatus;
    }

    public void setRewardStatus(Byte rewardStatus) {
        this.rewardStatus = rewardStatus;
    }

    public Long getRewardPackageId() {
        return rewardPackageId;
    }

    public void setRewardPackageId(Long rewardPackageId) {
        this.rewardPackageId = rewardPackageId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}