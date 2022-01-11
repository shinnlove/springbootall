package com.shinnlove.springbootall.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ProcessStatusMetadataPo implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 模版id
     */
    private Integer templateId;

    /**
     * 状态整型编号
     */
    private Integer statusNumber;

    /**
     * 状态名称
     */
    private String statusName;

    /**
     * 正常完成状态 1-正常完成态 0-非正常完成态
     */
    private Integer normalCompleted;

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

    public Integer getStatusNumber() {
        return statusNumber;
    }

    public void setStatusNumber(Integer statusNumber) {
        this.statusNumber = statusNumber;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName == null ? null : statusName.trim();
    }

    public Integer getNormalCompleted() {
        return normalCompleted;
    }

    public void setNormalCompleted(Integer normalCompleted) {
        this.normalCompleted = normalCompleted;
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
        ProcessStatusMetadataPo other = (ProcessStatusMetadataPo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTemplateId() == null ? other.getTemplateId() == null : this.getTemplateId().equals(other.getTemplateId()))
            && (this.getStatusNumber() == null ? other.getStatusNumber() == null : this.getStatusNumber().equals(other.getStatusNumber()))
            && (this.getStatusName() == null ? other.getStatusName() == null : this.getStatusName().equals(other.getStatusName()))
            && (this.getNormalCompleted() == null ? other.getNormalCompleted() == null : this.getNormalCompleted().equals(other.getNormalCompleted()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()))
            && (this.getMtime() == null ? other.getMtime() == null : this.getMtime().equals(other.getMtime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTemplateId() == null) ? 0 : getTemplateId().hashCode());
        result = prime * result + ((getStatusNumber() == null) ? 0 : getStatusNumber().hashCode());
        result = prime * result + ((getStatusName() == null) ? 0 : getStatusName().hashCode());
        result = prime * result + ((getNormalCompleted() == null) ? 0 : getNormalCompleted().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCtime() == null) ? 0 : getCtime().hashCode());
        result = prime * result + ((getMtime() == null) ? 0 : getMtime().hashCode());
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
        sb.append(", statusNumber=").append(statusNumber);
        sb.append(", statusName=").append(statusName);
        sb.append(", normalCompleted=").append(normalCompleted);
        sb.append(", remark=").append(remark);
        sb.append(", ctime=").append(ctime);
        sb.append(", mtime=").append(mtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}