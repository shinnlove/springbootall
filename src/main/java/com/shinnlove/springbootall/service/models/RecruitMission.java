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
 * @version $Id: RecruitMission.java, v 0.1 2022-01-11 5:27 PM Tony Zhao Exp $$
 */
public class RecruitMission implements Serializable {

    private static final long serialVersionUID = 4568092259555782886L;

    /**
     * id
     */
    private Long              id;

    /**
     * 任务编号
     */
    private Long              missionNo;

    /**
     * 任务类型: 1-B类任务 2-C类任务 3-D类任务
     */
    private Integer           missionType;

    /**
     * 任务状态: 1-草稿 2-已生效 3-已开始 4-已结束
     */
    private Integer           missionProcessStatus;

    /**
     * 标题
     */
    private String            title;

    /**
     * 副标题
     */
    private String            subTitle;

    /**
     * 补充描述
     */
    private String            missionDescription;

    /**
     * 奖金池类型: 1-总奖池 2-最高获得
     */
    private Integer           rewardType;

    /**
     * 奖金池
     */
    private BigDecimal        rewardAmount;

    /**
     * url
     */
    private String            indexUrl;

    /**
     * 上下架: 0-下架 1-上架
     */
    private Integer           onShelf;

    /**
     * 最后上架时间
     */
    private Timestamp         latestOnShelfTime;

    /**
     * 是否置顶: 0-否 1-是
     */
    private Integer           stickyTop;

    /**
     * 是否可分享: 0-否 1-是
     */
    private Integer           shareAvailable;

    /**
     * 排序
     */
    private Integer           sortOrder;

    /**
     * 任务开始时间
     */
    private Timestamp         startTime;

    /**
     * 任务结束时间
     */
    private Timestamp         endTime;

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
     * 最后操作人
     */
    private String            latestOperator;

    public RecruitMission() {
    }

    public RecruitMission(Long id, Long missionNo, Integer missionType,
                          Integer missionProcessStatus, String title, String subTitle,
                          String missionDescription, Integer rewardType, BigDecimal rewardAmount,
                          String indexUrl, Integer onShelf, Timestamp latestOnShelfTime,
                          Integer stickyTop, Integer shareAvailable, Integer sortOrder,
                          Timestamp startTime, Timestamp endTime, String remark, Timestamp ctime,
                          Timestamp mtime, Integer isDeleted, String latestOperator) {
        this.id = id;
        this.missionNo = missionNo;
        this.missionType = missionType;
        this.missionProcessStatus = missionProcessStatus;
        this.title = title;
        this.subTitle = subTitle;
        this.missionDescription = missionDescription;
        this.rewardType = rewardType;
        this.rewardAmount = rewardAmount;
        this.indexUrl = indexUrl;
        this.onShelf = onShelf;
        this.latestOnShelfTime = latestOnShelfTime;
        this.stickyTop = stickyTop;
        this.shareAvailable = shareAvailable;
        this.sortOrder = sortOrder;
        this.startTime = startTime;
        this.endTime = endTime;
        this.remark = remark;
        this.ctime = ctime;
        this.mtime = mtime;
        this.isDeleted = isDeleted;
        this.latestOperator = latestOperator;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMissionNo() {
        return missionNo;
    }

    public void setMissionNo(Long missionNo) {
        this.missionNo = missionNo;
    }

    public Integer getMissionType() {
        return missionType;
    }

    public void setMissionType(Integer missionType) {
        this.missionType = missionType;
    }

    public Integer getMissionProcessStatus() {
        return missionProcessStatus;
    }

    public void setMissionProcessStatus(Integer missionProcessStatus) {
        this.missionProcessStatus = missionProcessStatus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getMissionDescription() {
        return missionDescription;
    }

    public void setMissionDescription(String missionDescription) {
        this.missionDescription = missionDescription;
    }

    public Integer getRewardType() {
        return rewardType;
    }

    public void setRewardType(Integer rewardType) {
        this.rewardType = rewardType;
    }

    public BigDecimal getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(BigDecimal rewardAmount) {
        this.rewardAmount = rewardAmount;
    }

    public String getIndexUrl() {
        return indexUrl;
    }

    public void setIndexUrl(String indexUrl) {
        this.indexUrl = indexUrl;
    }

    public Integer getOnShelf() {
        return onShelf;
    }

    public void setOnShelf(Integer onShelf) {
        this.onShelf = onShelf;
    }

    public Timestamp getLatestOnShelfTime() {
        return latestOnShelfTime;
    }

    public void setLatestOnShelfTime(Timestamp latestOnShelfTime) {
        this.latestOnShelfTime = latestOnShelfTime;
    }

    public Integer getStickyTop() {
        return stickyTop;
    }

    public void setStickyTop(Integer stickyTop) {
        this.stickyTop = stickyTop;
    }

    public Integer getShareAvailable() {
        return shareAvailable;
    }

    public void setShareAvailable(Integer shareAvailable) {
        this.shareAvailable = shareAvailable;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
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

    public String getLatestOperator() {
        return latestOperator;
    }

    public void setLatestOperator(String latestOperator) {
        this.latestOperator = latestOperator;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}