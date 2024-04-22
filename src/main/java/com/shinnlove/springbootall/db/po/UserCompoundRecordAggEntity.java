package com.shinnlove.springbootall.db.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserCompoundRecordAggEntity implements Serializable {

    private String activityId;

    private Long componentId;

    private Long guid;

    private Integer compoundNum;

    private static final long serialVersionUID = 1L;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
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

    public Integer getCompoundNum() {
        return compoundNum;
    }

    public void setCompoundNum(Integer compoundNum) {
        this.compoundNum = compoundNum;
    }
}