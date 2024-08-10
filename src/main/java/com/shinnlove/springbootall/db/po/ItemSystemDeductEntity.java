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
 * @version $Id: ItemSystemDeductEntity.java, v 0.1 2024-06-25 14:25 Tony Zhao Exp $$
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemSystemDeductEntity implements Serializable {
    private Long id;

    private String activityId;

    private Long componentId;

    private Long itemId;

    private Long guid;

    private Integer systemDeductPrice;

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

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getGuid() {
        return guid;
    }

    public void setGuid(Long guid) {
        this.guid = guid;
    }

    public Integer getSystemDeductPrice() {
        return systemDeductPrice;
    }

    public void setSystemDeductPrice(Integer systemDeductPrice) {
        this.systemDeductPrice = systemDeductPrice;
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