package com.shinnlove.springbootall.db.po;

import java.io.Serializable;
import java.util.Date;

public class AssistRecordEntity implements Serializable {
    private Long id;

    private Long inviterGuid;

    private Long assistantGuid;

    private Long bookId;

    private Long pointCount;

    private Integer assistantType;

    private Integer received;

    private Date receiveTime;

    private Integer dateMark;

    private Long expiredTimestamp;

    private Long assistTimestamp;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInviterGuid() {
        return inviterGuid;
    }

    public void setInviterGuid(Long inviterGuid) {
        this.inviterGuid = inviterGuid;
    }

    public Long getAssistantGuid() {
        return assistantGuid;
    }

    public void setAssistantGuid(Long assistantGuid) {
        this.assistantGuid = assistantGuid;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getPointCount() {
        return pointCount;
    }

    public void setPointCount(Long pointCount) {
        this.pointCount = pointCount;
    }

    public Integer getAssistantType() {
        return assistantType;
    }

    public void setAssistantType(Integer assistantType) {
        this.assistantType = assistantType;
    }

    public Integer getReceived() {
        return received;
    }

    public void setReceived(Integer received) {
        this.received = received;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Integer getDateMark() {
        return dateMark;
    }

    public void setDateMark(Integer dateMark) {
        this.dateMark = dateMark;
    }

    public Long getExpiredTimestamp() {
        return expiredTimestamp;
    }

    public void setExpiredTimestamp(Long expiredTimestamp) {
        this.expiredTimestamp = expiredTimestamp;
    }

    public Long getAssistTimestamp() {
        return assistTimestamp;
    }

    public void setAssistTimestamp(Long assistTimestamp) {
        this.assistTimestamp = assistTimestamp;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}