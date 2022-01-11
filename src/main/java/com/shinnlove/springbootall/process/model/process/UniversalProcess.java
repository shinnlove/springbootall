/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.model.process;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Tony Zhao
 * @version $Id: UniversalProcess.java, v 0.1 2022-01-10 11:28 PM Tony Zhao Exp $$
 */
public class UniversalProcess implements Serializable {

    private static final long serialVersionUID = -5116285023457567012L;

    /** id */
    private Long              id;

    /** 流程编号 */
    private Long              processNo;

    /** 模版id */
    private Integer           templateId;

    /** 流程所属业务: 1-悬赏任务 */
    private Integer           processType;

    /** 关联唯一业务No. */
    private Long              refUniqueNo;

    /** 父流程No. */
    private Long              parentProcessNo;

    /** 当前状态 */
    private Integer           currentStatus;

    /** 最后操作人 */
    private String            latestOperator;

    /** 备注 */
    private String            remark;

    /** 创建时间 */
    private Timestamp         ctime;

    /** 更新时间 */
    private Timestamp         mtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProcessNo() {
        return processNo;
    }

    public void setProcessNo(Long processNo) {
        this.processNo = processNo;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public Integer getProcessType() {
        return processType;
    }

    public void setProcessType(Integer processType) {
        this.processType = processType;
    }

    public Long getRefUniqueNo() {
        return refUniqueNo;
    }

    public void setRefUniqueNo(Long refUniqueNo) {
        this.refUniqueNo = refUniqueNo;
    }

    public Long getParentProcessNo() {
        return parentProcessNo;
    }

    public void setParentProcessNo(Long parentProcessNo) {
        this.parentProcessNo = parentProcessNo;
    }

    public Integer getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Integer currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getLatestOperator() {
        return latestOperator;
    }

    public void setLatestOperator(String latestOperator) {
        this.latestOperator = latestOperator;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Timestamp getCtime() {
        return ctime;
    }

    public void setCtime(Timestamp ctime) {
        this.ctime = ctime;
    }

    public Timestamp getMtime() {
        return mtime;
    }

    public void setMtime(Timestamp mtime) {
        this.mtime = mtime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}