package com.shinnlove.springbootall.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class UniversalProcessPo implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 流程编号
     */
    private Long processNo;

    /**
     * 模版id
     */
    private Integer templateId;

    /**
     * 流程所属业务: 1-悬赏任务
     */
    private Integer processType;

    /**
     * 关联业务唯一No.
     */
    private Long refUniqueNo;

    /**
     * 父流程id
     */
    private Long parentProcessNo;

    /**
     * 当前状态
     */
    private Integer currentStatus;

    /**
     * 最后操作人
     */
    private String latestOperator;

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
        this.latestOperator = latestOperator == null ? null : latestOperator.trim();
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
        UniversalProcessPo other = (UniversalProcessPo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProcessNo() == null ? other.getProcessNo() == null : this.getProcessNo().equals(other.getProcessNo()))
            && (this.getTemplateId() == null ? other.getTemplateId() == null : this.getTemplateId().equals(other.getTemplateId()))
            && (this.getProcessType() == null ? other.getProcessType() == null : this.getProcessType().equals(other.getProcessType()))
            && (this.getRefUniqueNo() == null ? other.getRefUniqueNo() == null : this.getRefUniqueNo().equals(other.getRefUniqueNo()))
            && (this.getParentProcessNo() == null ? other.getParentProcessNo() == null : this.getParentProcessNo().equals(other.getParentProcessNo()))
            && (this.getCurrentStatus() == null ? other.getCurrentStatus() == null : this.getCurrentStatus().equals(other.getCurrentStatus()))
            && (this.getLatestOperator() == null ? other.getLatestOperator() == null : this.getLatestOperator().equals(other.getLatestOperator()))
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
        result = prime * result + ((getProcessType() == null) ? 0 : getProcessType().hashCode());
        result = prime * result + ((getRefUniqueNo() == null) ? 0 : getRefUniqueNo().hashCode());
        result = prime * result + ((getParentProcessNo() == null) ? 0 : getParentProcessNo().hashCode());
        result = prime * result + ((getCurrentStatus() == null) ? 0 : getCurrentStatus().hashCode());
        result = prime * result + ((getLatestOperator() == null) ? 0 : getLatestOperator().hashCode());
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
        sb.append(", processType=").append(processType);
        sb.append(", refUniqueNo=").append(refUniqueNo);
        sb.append(", parentProcessNo=").append(parentProcessNo);
        sb.append(", currentStatus=").append(currentStatus);
        sb.append(", latestOperator=").append(latestOperator);
        sb.append(", remark=").append(remark);
        sb.append(", ctime=").append(ctime);
        sb.append(", mtime=").append(mtime);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}