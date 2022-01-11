/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.model.status;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Process status metadata for specific template id and action id.
 *
 * @author Tony Zhao
 * @version $Id: ProcessStatus.java, v 0.1 2021-07-06 3:57 PM Tony Zhao Exp $$
 */
public class ProcessStatus implements Serializable {
    private static final long serialVersionUID = 7822631287705453581L;

    /** 主键id */
    private long              id;

    /** 模版id */
    private int               templateId;

    /** 状态整型编号 */
    private int               statusNumber;

    /** 状态名称 */
    private String            statusName;

    /** 正常完成状态 1-正常完成态 0-非正常完成态 */
    private int               normalCompleted;

    /** 备注 */
    private String            remark;

    /**
     * Constructor with no arguments.
     */
    public ProcessStatus() {
    }

    /**
     * Constructor with all arguments.
     *
     * @param id
     * @param templateId
     * @param statusNumber
     * @param statusName
     * @param normalCompleted
     * @param remark
     */
    public ProcessStatus(long id, int templateId, int statusNumber, String statusName,
                         int normalCompleted, String remark) {
        this.id = id;
        this.templateId = templateId;
        this.statusNumber = statusNumber;
        this.statusName = statusName;
        this.normalCompleted = normalCompleted;
        this.remark = remark;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public int getStatusNumber() {
        return statusNumber;
    }

    public void setStatusNumber(int statusNumber) {
        this.statusNumber = statusNumber;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public int getNormalCompleted() {
        return normalCompleted;
    }

    public void setNormalCompleted(int normalCompleted) {
        this.normalCompleted = normalCompleted;
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