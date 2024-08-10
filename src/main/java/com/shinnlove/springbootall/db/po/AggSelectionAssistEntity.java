/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.db.po;

import lombok.*;

import java.io.Serializable;

/**
 * 聚合某个选择助力的信息。
 *
 * @author Tony Zhao
 * @version $Id: AggSelectionAssistEntity.java, v 0.1 2024-06-17 14:15 Tony Zhao Exp $$
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AggSelectionAssistEntity implements Serializable {

    private String activityId;

    private Long componentId;

    private Long selectId;

    private Long gatherGuid;

    private Integer totalRandomPrice;

    private Integer totalDeductPrice;

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

    public Long getSelectId() {
        return selectId;
    }

    public void setSelectId(Long selectId) {
        this.selectId = selectId;
    }

    public Long getGatherGuid() {
        return gatherGuid;
    }

    public void setGatherGuid(Long gatherGuid) {
        this.gatherGuid = gatherGuid;
    }

    public Integer getTotalRandomPrice() {
        return totalRandomPrice;
    }

    public void setTotalRandomPrice(Integer totalRandomPrice) {
        this.totalRandomPrice = totalRandomPrice;
    }

    public Integer getTotalDeductPrice() {
        return totalDeductPrice;
    }

    public void setTotalDeductPrice(Integer totalDeductPrice) {
        this.totalDeductPrice = totalDeductPrice;
    }

}