/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.model.action;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Tony Zhao
 * @version $Id: ProcessAction.java, v 0.1 2021-07-26 10:32 PM Tony Zhao Exp $$
 */
public class ProcessAction implements Serializable {

    private static final long serialVersionUID = -8055215777159567369L;

    /** Id */
    private Long              id;

    /** 模版id */
    private Integer           templateId;

    /** 动作id */
    private Integer           actionId;

    /** 动作描述 */
    private String            actionDescription;

    /** 变迁前源状态 */
    private Integer           sourceStatus;

    /** 变迁到目标状态 */
    private Integer           destinationStatus;

    /** 当前action是否为流程入口动作 */
    private Integer           processEntrance;

    /** 备注 */
    private String            remark;

    /**
     * Constructor with no arguments.
     */
    public ProcessAction() {
    }

    /**
     * Constructor with all arguments.
     *
     * @param id
     * @param templateId
     * @param actionId
     * @param actionDescription
     * @param sourceStatus
     * @param destinationStatus
     * @param processEntrance
     * @param remark
     */
    public ProcessAction(Long id, Integer templateId, Integer actionId, String actionDescription,
                         Integer sourceStatus, Integer destinationStatus, Integer processEntrance,
                         String remark) {
        this.id = id;
        this.templateId = templateId;
        this.actionId = actionId;
        this.actionDescription = actionDescription;
        this.sourceStatus = sourceStatus;
        this.destinationStatus = destinationStatus;
        this.processEntrance = processEntrance;
        this.remark = remark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public String getActionDescription() {
        return actionDescription;
    }

    public void setActionDescription(String actionDescription) {
        this.actionDescription = actionDescription;
    }

    public Integer getSourceStatus() {
        return sourceStatus;
    }

    public void setSourceStatus(Integer sourceStatus) {
        this.sourceStatus = sourceStatus;
    }

    public Integer getDestinationStatus() {
        return destinationStatus;
    }

    public void setDestinationStatus(Integer destinationStatus) {
        this.destinationStatus = destinationStatus;
    }

    public Integer getProcessEntrance() {
        return processEntrance;
    }

    public void setProcessEntrance(Integer processEntrance) {
        this.processEntrance = processEntrance;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}