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
 * @version $Id: AssistDeductCodeEntity.java, v 0.1 2024-06-18 15:15 Tony Zhao Exp $$
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AssistDeductCodeEntity implements Serializable {
    private Long id;

    private String activityId;

    private Long componentId;

    private Long gatherGuid;

    private Long selectId;

    private Long itemId;

    private String assistCode;

    private Integer validStatus;

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

    public Long getGatherGuid() {
        return gatherGuid;
    }

    public void setGatherGuid(Long gatherGuid) {
        this.gatherGuid = gatherGuid;
    }

    public Long getSelectId() {
        return selectId;
    }

    public void setSelectId(Long selectId) {
        this.selectId = selectId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getAssistCode() {
        return assistCode;
    }

    public void setAssistCode(String assistCode) {
        this.assistCode = assistCode == null ? null : assistCode.trim();
    }

    public Integer getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(Integer validStatus) {
        this.validStatus = validStatus;
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