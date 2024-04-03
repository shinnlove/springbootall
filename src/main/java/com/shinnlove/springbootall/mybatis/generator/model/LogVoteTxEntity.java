package com.shinnlove.springbootall.mybatis.generator.model;

import java.io.Serializable;
import java.util.Date;

public class LogVoteTxEntity implements Serializable {
    private Long id;

    private String txRoleId;

    private Long roleId;

    private Long voteCount;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTxRoleId() {
        return txRoleId;
    }

    public void setTxRoleId(String txRoleId) {
        this.txRoleId = txRoleId == null ? null : txRoleId.trim();
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Long voteCount) {
        this.voteCount = voteCount;
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