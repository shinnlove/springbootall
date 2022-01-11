package com.shinnlove.springbootall.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ProcessTemplateMetadataPo implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 模版id
     */
    private Integer templateId;

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 是否需要勾兑父流程
     */
    private Integer completeReconcileParent;

    /**
     * 所属父流程下所有子流程勾兑父流程的方式: 0-或 1-且
     */
    private Integer coordinateMode;

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

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
    }

    public Integer getCompleteReconcileParent() {
        return completeReconcileParent;
    }

    public void setCompleteReconcileParent(Integer completeReconcileParent) {
        this.completeReconcileParent = completeReconcileParent;
    }

    public Integer getCoordinateMode() {
        return coordinateMode;
    }

    public void setCoordinateMode(Integer coordinateMode) {
        this.coordinateMode = coordinateMode;
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
        ProcessTemplateMetadataPo other = (ProcessTemplateMetadataPo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTemplateId() == null ? other.getTemplateId() == null : this.getTemplateId().equals(other.getTemplateId()))
            && (this.getTemplateName() == null ? other.getTemplateName() == null : this.getTemplateName().equals(other.getTemplateName()))
            && (this.getCompleteReconcileParent() == null ? other.getCompleteReconcileParent() == null : this.getCompleteReconcileParent().equals(other.getCompleteReconcileParent()))
            && (this.getCoordinateMode() == null ? other.getCoordinateMode() == null : this.getCoordinateMode().equals(other.getCoordinateMode()))
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
        result = prime * result + ((getTemplateId() == null) ? 0 : getTemplateId().hashCode());
        result = prime * result + ((getTemplateName() == null) ? 0 : getTemplateName().hashCode());
        result = prime * result + ((getCompleteReconcileParent() == null) ? 0 : getCompleteReconcileParent().hashCode());
        result = prime * result + ((getCoordinateMode() == null) ? 0 : getCoordinateMode().hashCode());
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
        sb.append(", templateId=").append(templateId);
        sb.append(", templateName=").append(templateName);
        sb.append(", completeReconcileParent=").append(completeReconcileParent);
        sb.append(", coordinateMode=").append(coordinateMode);
        sb.append(", remark=").append(remark);
        sb.append(", ctime=").append(ctime);
        sb.append(", mtime=").append(mtime);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}