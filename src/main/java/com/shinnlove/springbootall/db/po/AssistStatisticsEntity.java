package com.shinnlove.springbootall.db.po;

import java.io.Serializable;
import java.util.Date;

public class AssistStatisticsEntity implements Serializable {
    private Long id;

    private Long guid;

    private Long receivePointCount;

    private Integer inviteCount;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGuid() {
        return guid;
    }

    public void setGuid(Long guid) {
        this.guid = guid;
    }

    public Long getReceivePointCount() {
        return receivePointCount;
    }

    public void setReceivePointCount(Long receivePointCount) {
        this.receivePointCount = receivePointCount;
    }

    public Integer getInviteCount() {
        return inviteCount;
    }

    public void setInviteCount(Integer inviteCount) {
        this.inviteCount = inviteCount;
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
}