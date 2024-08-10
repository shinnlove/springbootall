/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.db.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Tony Zhao
 * @version $Id: UserBandLogEntity.java, v 0.1 2024-06-04 19:46 Tony Zhao Exp $$
 */
public class UserBandLogEntity implements Serializable {
    private Long id;

    private String activityId;

    private Long guid;

    private Byte band;

    private String used;

    private String rewards;

    private Date createdAt;

    private Date updatedAt;

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

    public Byte getBand() {
        return band;
    }

    public void setBand(Byte band) {
        this.band = band;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used == null ? null : used.trim();
    }

    public String getRewards() {
        return rewards;
    }

    public void setRewards(String rewards) {
        this.rewards = rewards == null ? null : rewards.trim();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}