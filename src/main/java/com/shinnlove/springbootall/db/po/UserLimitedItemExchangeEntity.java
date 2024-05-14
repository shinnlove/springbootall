package com.shinnlove.springbootall.db.po;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

public class UserLimitedItemExchangeEntity implements Serializable {
    private Long id;

    private String activityId;

    private Long componentId;

    private Long guid;

    private Integer exchangeItemType;

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

    public Long getComponentId() {
        return componentId;
    }

    public void setComponentId(Long componentId) {
        this.componentId = componentId;
    }

    public Long getGuid() {
        return guid;
    }

    public void setGuid(Long guid) {
        this.guid = guid;
    }

    public Integer getExchangeItemType() {
        return exchangeItemType;
    }

    public void setExchangeItemType(Integer exchangeItemType) {
        this.exchangeItemType = exchangeItemType;
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

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}