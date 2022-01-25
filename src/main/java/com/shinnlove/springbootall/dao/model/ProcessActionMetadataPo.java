package com.shinnlove.springbootall.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ProcessActionMetadataPo implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 模版id
     */
    private Integer templateId;

    /**
     * 动作id
     */
    private Integer actionId;

    /**
     * 动作描述
     */
    private String actionDescription;

    /**
     * 变迁前源状态
     */
    private Integer sourceStatus;

    /**
     * 变迁到目标状态
     */
    private Integer destinationStatus;

    /**
     * 当前action是否为流程入口动作
     */
    private Integer processEntrance;

    /**
     * 创建时间
     */
    private Timestamp ctime;

    /**
     * 更新时间
     */
    private Timestamp mtime;

    /**
     * 备注
     */
    private String remark;

    private static final long serialVersionUID = 1L;

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
        this.actionDescription = actionDescription == null ? null : actionDescription.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
        ProcessActionMetadataPo other = (ProcessActionMetadataPo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTemplateId() == null ? other.getTemplateId() == null : this.getTemplateId().equals(other.getTemplateId()))
            && (this.getActionId() == null ? other.getActionId() == null : this.getActionId().equals(other.getActionId()))
            && (this.getActionDescription() == null ? other.getActionDescription() == null : this.getActionDescription().equals(other.getActionDescription()))
            && (this.getSourceStatus() == null ? other.getSourceStatus() == null : this.getSourceStatus().equals(other.getSourceStatus()))
            && (this.getDestinationStatus() == null ? other.getDestinationStatus() == null : this.getDestinationStatus().equals(other.getDestinationStatus()))
            && (this.getProcessEntrance() == null ? other.getProcessEntrance() == null : this.getProcessEntrance().equals(other.getProcessEntrance()))
            && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()))
            && (this.getMtime() == null ? other.getMtime() == null : this.getMtime().equals(other.getMtime()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTemplateId() == null) ? 0 : getTemplateId().hashCode());
        result = prime * result + ((getActionId() == null) ? 0 : getActionId().hashCode());
        result = prime * result + ((getActionDescription() == null) ? 0 : getActionDescription().hashCode());
        result = prime * result + ((getSourceStatus() == null) ? 0 : getSourceStatus().hashCode());
        result = prime * result + ((getDestinationStatus() == null) ? 0 : getDestinationStatus().hashCode());
        result = prime * result + ((getProcessEntrance() == null) ? 0 : getProcessEntrance().hashCode());
        result = prime * result + ((getCtime() == null) ? 0 : getCtime().hashCode());
        result = prime * result + ((getMtime() == null) ? 0 : getMtime().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", templateId=").append(templateId);
        sb.append(", actionId=").append(actionId);
        sb.append(", actionDescription=").append(actionDescription);
        sb.append(", sourceStatus=").append(sourceStatus);
        sb.append(", destinationStatus=").append(destinationStatus);
        sb.append(", processEntrance=").append(processEntrance);
        sb.append(", ctime=").append(ctime);
        sb.append(", mtime=").append(mtime);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}