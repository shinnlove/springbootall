package com.shinnlove.springbootall.db.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BookInfoHistoryEntity implements Serializable {
    private Long id;

    private Long cbid;

    private String msgId;

    private String bookName;

    private Integer checkLevel;

    private Integer auditStatus;

    private Integer allowFree;

    private Integer monthlyAllow;

    private Integer status;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCbid() {
        return cbid;
    }

    public void setCbid(Long cbid) {
        this.cbid = cbid;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId == null ? null : msgId.trim();
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    public Integer getCheckLevel() {
        return checkLevel;
    }

    public void setCheckLevel(Integer checkLevel) {
        this.checkLevel = checkLevel;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getAllowFree() {
        return allowFree;
    }

    public void setAllowFree(Integer allowFree) {
        this.allowFree = allowFree;
    }

    public Integer getMonthlyAllow() {
        return monthlyAllow;
    }

    public void setMonthlyAllow(Integer monthlyAllow) {
        this.monthlyAllow = monthlyAllow;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}