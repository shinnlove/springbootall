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
 * @version $Id: SkuSelectionEntity.java, v 0.1 2024-06-12 17:15 Tony Zhao Exp $$
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SkuSelectionEntity implements Serializable {
    private Long id;

    private String activityId;

    private Long componentId;

    private Long guid;

    private Long itemId;

    private String itemNameSnapshot;

    private String itemDescSnapshot;

    private String itemImgUrlSnapshot;

    private Integer priceSnapshot;

    private Integer lowestPriceSnapshot;

    private Long ddl1stTime;

    private Long start2ndTime;

    private Long ddl2ndTime;

    private Integer payStatus;

    private Integer stage1SendStatus;

    private Integer stage4SendStatus;

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

    public Long getGuid() {
        return guid;
    }

    public void setGuid(Long guid) {
        this.guid = guid;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemNameSnapshot() {
        return itemNameSnapshot;
    }

    public void setItemNameSnapshot(String itemNameSnapshot) {
        this.itemNameSnapshot = itemNameSnapshot == null ? null : itemNameSnapshot.trim();
    }

    public String getItemDescSnapshot() {
        return itemDescSnapshot;
    }

    public void setItemDescSnapshot(String itemDescSnapshot) {
        this.itemDescSnapshot = itemDescSnapshot == null ? null : itemDescSnapshot.trim();
    }

    public String getItemImgUrlSnapshot() {
        return itemImgUrlSnapshot;
    }

    public void setItemImgUrlSnapshot(String itemImgUrlSnapshot) {
        this.itemImgUrlSnapshot = itemImgUrlSnapshot == null ? null : itemImgUrlSnapshot.trim();
    }

    public Integer getPriceSnapshot() {
        return priceSnapshot;
    }

    public void setPriceSnapshot(Integer priceSnapshot) {
        this.priceSnapshot = priceSnapshot;
    }

    public Integer getLowestPriceSnapshot() {
        return lowestPriceSnapshot;
    }

    public void setLowestPriceSnapshot(Integer lowestPriceSnapshot) {
        this.lowestPriceSnapshot = lowestPriceSnapshot;
    }

    public Long getDdl1stTime() {
        return ddl1stTime;
    }

    public void setDdl1stTime(Long ddl1stTime) {
        this.ddl1stTime = ddl1stTime;
    }

    public Long getStart2ndTime() {
        return start2ndTime;
    }

    public void setStart2ndTime(Long start2ndTime) {
        this.start2ndTime = start2ndTime;
    }

    public Long getDdl2ndTime() {
        return ddl2ndTime;
    }

    public void setDdl2ndTime(Long ddl2ndTime) {
        this.ddl2ndTime = ddl2ndTime;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getStage1SendStatus() {
        return stage1SendStatus;
    }

    public void setStage1SendStatus(Integer stage1SendStatus) {
        this.stage1SendStatus = stage1SendStatus;
    }

    public Integer getStage4SendStatus() {
        return stage4SendStatus;
    }

    public void setStage4SendStatus(Integer stage4SendStatus) {
        this.stage4SendStatus = stage4SendStatus;
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