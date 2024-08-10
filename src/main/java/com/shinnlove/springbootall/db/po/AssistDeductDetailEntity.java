/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.db.po;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Tony Zhao
 * @version $Id: AssistDeductDetailEntity.java, v 0.1 2024-06-18 15:41 Tony Zhao Exp $$
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class AssistDeductDetailEntity implements Serializable {
    private Long id;

    private String activityId;

    private Long componentId;

    private Long selectId;

    private Integer helpType;

    private Long gatherGuid;

    private Long helpGuid;

    private Integer randomHelpPrice;

    private Integer realHelpPrice;

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

    public Long getSelectId() {
        return selectId;
    }

    public void setSelectId(Long selectId) {
        this.selectId = selectId;
    }

    public Integer getHelpType() {
        return helpType;
    }

    public void setHelpType(Integer helpType) {
        this.helpType = helpType;
    }

    public Long getGatherGuid() {
        return gatherGuid;
    }

    public void setGatherGuid(Long gatherGuid) {
        this.gatherGuid = gatherGuid;
    }

    public Long getHelpGuid() {
        return helpGuid;
    }

    public void setHelpGuid(Long helpGuid) {
        this.helpGuid = helpGuid;
    }

    public Integer getRandomHelpPrice() {
        return randomHelpPrice;
    }

    public void setRandomHelpPrice(Integer randomHelpPrice) {
        this.randomHelpPrice = randomHelpPrice;
    }

    public Integer getRealHelpPrice() {
        return realHelpPrice;
    }

    public void setRealHelpPrice(Integer realHelpPrice) {
        this.realHelpPrice = realHelpPrice;
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