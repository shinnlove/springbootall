package com.shinnlove.springbootall.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ProcessStatusLogPo implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 流程唯一unique No.
     */
    private Long processNo;

    /**
     * 模版id
     */
    private Integer templateId;

    /**
     * 动作id
     */
    private Integer actionId;

    /**
     * 源状态
     */
    private Integer sourceStatus;

    /**
     * 目标状态
     */
    private Integer destinationStatus;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Timestamp ctime;

    /**
     * 更新时间
     */
    private Timestamp mtime;

    /**
     * 软删除: 0 否 1是
     */
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;

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

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ProcessStatusLogPo other = (ProcessStatusLogPo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProcessNo() == null ? other.getProcessNo() == null : this.getProcessNo().equals(other.getProcessNo()))
            && (this.getTemplateId() == null ? other.getTemplateId() == null : this.getTemplateId().equals(other.getTemplateId()))
            && (this.getActionId() == null ? other.getActionId() == null : this.getActionId().equals(other.getActionId()))
            && (this.getSourceStatus() == null ? other.getSourceStatus() == null : this.getSourceStatus().equals(other.getSourceStatus()))
            && (this.getDestinationStatus() == null ? other.getDestinationStatus() == null : this.getDestinationStatus().equals(other.getDestinationStatus()))
            && (this.getOperator() == null ? other.getOperator() == null : this.getOperator().equals(other.getOperator()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()))
            && (this.getMtime() == null ? other.getMtime() == null : this.getMtime().equals(other.getMtime()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProcessNo() == null) ? 0 : getProcessNo().hashCode());
        result = prime * result + ((getTemplateId() == null) ? 0 : getTemplateId().hashCode());
        result = prime * result + ((getActionId() == null) ? 0 : getActionId().hashCode());
        result = prime * result + ((getSourceStatus() == null) ? 0 : getSourceStatus().hashCode());
        result = prime * result + ((getDestinationStatus() == null) ? 0 : getDestinationStatus().hashCode());
        result = prime * result + ((getOperator() == null) ? 0 : getOperator().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCtime() == null) ? 0 : getCtime().hashCode());
        result = prime * result + ((getMtime() == null) ? 0 : getMtime().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", processNo=").append(processNo);
        sb.append(", templateId=").append(templateId);
        sb.append(", actionId=").append(actionId);
        sb.append(", sourceStatus=").append(sourceStatus);
        sb.append(", destinationStatus=").append(destinationStatus);
        sb.append(", operator=").append(operator);
        sb.append(", remark=").append(remark);
        sb.append(", ctime=").append(ctime);
        sb.append(", mtime=").append(mtime);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}