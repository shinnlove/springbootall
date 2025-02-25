package com.shinnlove.springbootall.db.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BookAdjustReasonMessageEntity implements Serializable {
    private Long id;

    private String activityId;

    private String consumerEnv;

    private String originalMsgBody;

    private Long msgCbid;

    private String msgTitle;

    private String msgReason;

    private Integer msgType;

    private String msgOperator;

    private Long msgCreateTime;

    private String msgCreateTimeStr;

    private Integer checkLevel;

    private String auditStatus;

    private Integer needPush;

    private Integer hasSent;

    private Date createTime;

    private Date updateTime;

    private String remark;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId == null ? null : activityId.trim();
    }

    public String getConsumerEnv() {
        return consumerEnv;
    }

    public void setConsumerEnv(String consumerEnv) {
        this.consumerEnv = consumerEnv == null ? null : consumerEnv.trim();
    }

    public String getOriginalMsgBody() {
        return originalMsgBody;
    }

    public void setOriginalMsgBody(String originalMsgBody) {
        this.originalMsgBody = originalMsgBody == null ? null : originalMsgBody.trim();
    }

    public Long getMsgCbid() {
        return msgCbid;
    }

    public void setMsgCbid(Long msgCbid) {
        this.msgCbid = msgCbid;
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle == null ? null : msgTitle.trim();
    }

    public String getMsgReason() {
        return msgReason;
    }

    public void setMsgReason(String msgReason) {
        this.msgReason = msgReason == null ? null : msgReason.trim();
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public String getMsgOperator() {
        return msgOperator;
    }

    public void setMsgOperator(String msgOperator) {
        this.msgOperator = msgOperator == null ? null : msgOperator.trim();
    }

    public Long getMsgCreateTime() {
        return msgCreateTime;
    }

    public void setMsgCreateTime(Long msgCreateTime) {
        this.msgCreateTime = msgCreateTime;
    }

    public String getMsgCreateTimeStr() {
        return msgCreateTimeStr;
    }

    public void setMsgCreateTimeStr(String msgCreateTimeStr) {
        this.msgCreateTimeStr = msgCreateTimeStr == null ? null : msgCreateTimeStr.trim();
    }

    public Integer getCheckLevel() {
        return checkLevel;
    }

    public void setCheckLevel(Integer checkLevel) {
        this.checkLevel = checkLevel;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus == null ? null : auditStatus.trim();
    }

    public Integer getNeedPush() {
        return needPush;
    }

    public void setNeedPush(Integer needPush) {
        this.needPush = needPush;
    }

    public Integer getHasSent() {
        return hasSent;
    }

    public void setHasSent(Integer hasSent) {
        this.hasSent = hasSent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}