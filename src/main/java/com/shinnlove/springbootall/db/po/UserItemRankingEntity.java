/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.db.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Tony Zhao
 * @version $Id: UserItemRankingEntity.java, v 0.1 2024-04-19 10:29 Tony Zhao Exp $$
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserItemRankingEntity implements Serializable {
    private Long id;

    private String activityId;

    private Long guid;

    private Integer itemTypeCount;

    private Byte hasCollectAll;

    private Long collectAllTimestamp;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId == null ? null : activityId.trim();
    }

    public Long getGuid() {
        return guid;
    }

    public void setGuid(Long guid) {
        this.guid = guid;
    }

    public Integer getItemTypeCount() {
        return itemTypeCount;
    }

    public void setItemTypeCount(Integer itemTypeCount) {
        this.itemTypeCount = itemTypeCount;
    }

    public Byte getHasCollectAll() {
        return hasCollectAll;
    }

    public void setHasCollectAll(Byte hasCollectAll) {
        this.hasCollectAll = hasCollectAll;
    }

    public Long getCollectAllTimestamp() {
        return collectAllTimestamp;
    }

    public void setCollectAllTimestamp(Long collectAllTimestamp) {
        this.collectAllTimestamp = collectAllTimestamp;
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