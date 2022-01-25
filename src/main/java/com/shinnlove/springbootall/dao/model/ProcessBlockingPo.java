package com.shinnlove.springbootall.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ProcessBlockingPo implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 主流程No.
     */
    private Long mainProcessNo;

    /**
     * 阻塞流程No.
     */
    private Long obstacleByProcessNo;

    /**
     * 主流程需阻塞的状态
     */
    private Integer refStatus;

    /**
     * 流程在某状态被其他流程阻塞时提示语
     */
    private String blockingMessage;

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

    public Long getMainProcessNo() {
        return mainProcessNo;
    }

    public void setMainProcessNo(Long mainProcessNo) {
        this.mainProcessNo = mainProcessNo;
    }

    public Long getObstacleByProcessNo() {
        return obstacleByProcessNo;
    }

    public void setObstacleByProcessNo(Long obstacleByProcessNo) {
        this.obstacleByProcessNo = obstacleByProcessNo;
    }

    public Integer getRefStatus() {
        return refStatus;
    }

    public void setRefStatus(Integer refStatus) {
        this.refStatus = refStatus;
    }

    public String getBlockingMessage() {
        return blockingMessage;
    }

    public void setBlockingMessage(String blockingMessage) {
        this.blockingMessage = blockingMessage == null ? null : blockingMessage.trim();
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
        ProcessBlockingPo other = (ProcessBlockingPo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMainProcessNo() == null ? other.getMainProcessNo() == null : this.getMainProcessNo().equals(other.getMainProcessNo()))
            && (this.getObstacleByProcessNo() == null ? other.getObstacleByProcessNo() == null : this.getObstacleByProcessNo().equals(other.getObstacleByProcessNo()))
            && (this.getRefStatus() == null ? other.getRefStatus() == null : this.getRefStatus().equals(other.getRefStatus()))
            && (this.getBlockingMessage() == null ? other.getBlockingMessage() == null : this.getBlockingMessage().equals(other.getBlockingMessage()))
            && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()))
            && (this.getMtime() == null ? other.getMtime() == null : this.getMtime().equals(other.getMtime()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMainProcessNo() == null) ? 0 : getMainProcessNo().hashCode());
        result = prime * result + ((getObstacleByProcessNo() == null) ? 0 : getObstacleByProcessNo().hashCode());
        result = prime * result + ((getRefStatus() == null) ? 0 : getRefStatus().hashCode());
        result = prime * result + ((getBlockingMessage() == null) ? 0 : getBlockingMessage().hashCode());
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
        sb.append(", mainProcessNo=").append(mainProcessNo);
        sb.append(", obstacleByProcessNo=").append(obstacleByProcessNo);
        sb.append(", refStatus=").append(refStatus);
        sb.append(", blockingMessage=").append(blockingMessage);
        sb.append(", ctime=").append(ctime);
        sb.append(", mtime=").append(mtime);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}