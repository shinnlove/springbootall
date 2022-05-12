/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.models;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Tony Zhao
 * @version $Id: PickupMessage.java, v 0.1 2022-05-10 5:26 PM Tony Zhao Exp $$
 */
public class PickupMessage implements Serializable {

    /** uuid */
    private static final long serialVersionUID = -1825714598546499297L;

    /**
     * id
     */
    private Long              id;

    /**
     * 消息本身唯一标识，md5生成
     */
    private String            messageNo;

    /**
     * 消息对象唯一标识
     */
    private Long              objId;

    /**
     * 消息类型 0-未定义, 1-订单消息(参考原pickup_message_type)
     */
    private Integer           messageType;

    /**
     * 消息消费状态 0 未消费, 1 已消费
     */
    private Integer           consumeStatus;

    /**
     * 重试次数
     */
    private Integer           retryTimes;

    /**
     * 业务领域模型
     */
    private String            domainModel;

    /**
     * 消息内容存根
     */
    private String            jsonObject;

    /**
     * 创建时间
     */
    private Timestamp         ctime;

    /**
     * 更新时间
     */
    private Timestamp         mtime;

    /**
     * 备注
     */
    private String            remark;

    public PickupMessage() {
    }

    public PickupMessage(Long id, String messageNo, Long objId, Integer messageType,
                         Integer consumeStatus, Integer retryTimes, String domainModel,
                         String jsonObject, Timestamp ctime, Timestamp mtime, String remark) {
        this.id = id;
        this.messageNo = messageNo;
        this.objId = objId;
        this.messageType = messageType;
        this.consumeStatus = consumeStatus;
        this.retryTimes = retryTimes;
        this.domainModel = domainModel;
        this.jsonObject = jsonObject;
        this.ctime = ctime;
        this.mtime = mtime;
        this.remark = remark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageNo() {
        return messageNo;
    }

    public void setMessageNo(String messageNo) {
        this.messageNo = messageNo;
    }

    public Long getObjId() {
        return objId;
    }

    public void setObjId(Long objId) {
        this.objId = objId;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public Integer getConsumeStatus() {
        return consumeStatus;
    }

    public void setConsumeStatus(Integer consumeStatus) {
        this.consumeStatus = consumeStatus;
    }

    public Integer getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(Integer retryTimes) {
        this.retryTimes = retryTimes;
    }

    public String getDomainModel() {
        return domainModel;
    }

    public void setDomainModel(String domainModel) {
        this.domainModel = domainModel;
    }

    public String getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(String jsonObject) {
        this.jsonObject = jsonObject;
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
        this.remark = remark;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}