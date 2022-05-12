/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.models;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Tony Zhao
 * @version $Id: ArchiveBindMessageInfo.java, v 0.1 2022-05-10 5:40 PM Tony Zhao Exp $$
 */
public class ArchiveBindMessageInfo implements Serializable {

    private static final long serialVersionUID = 8345453503305669947L;

    private Integer           type;
    private Integer           subType;
    private Long              aid;
    private Long              oid;
    private String            mtime;

    public ArchiveBindMessageInfo() {
    }

    public ArchiveBindMessageInfo(Integer type, Integer subType, Long aid, Long oid, String mtime) {
        this.type = type;
        this.subType = subType;
        this.aid = aid;
        this.oid = oid;
        this.mtime = mtime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSubType() {
        return subType;
    }

    public void setSubType(Integer subType) {
        this.subType = subType;
    }

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}