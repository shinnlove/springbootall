package com.shinnlove.springbootall.db.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserFragmentCollectEntity implements Serializable {
    private Long id;

    private String activityId;

    private Long componentId;

    private Long guid;

    private Integer fragmentId;

    private Integer fragmentUsed;

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

    public Integer getFragmentId() {
        return fragmentId;
    }

    public void setFragmentId(Integer fragmentId) {
        this.fragmentId = fragmentId;
    }

    public Integer getFragmentUsed() {
        return fragmentUsed;
    }

    public void setFragmentUsed(Integer fragmentUsed) {
        this.fragmentUsed = fragmentUsed;
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