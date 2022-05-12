/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.models;

import java.io.Serializable;

/**
 * @author Tony Zhao
 * @version $Id: ArchiveInfo.java, v 0.1 2022-05-10 5:45 PM Tony Zhao Exp $$
 */
public class ArchiveInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long              aid;
    private String            title;
    private Long              mid;
    private Integer           state;
    // 发布时间(精确到秒)
    private Long              ptime;
    // 投稿时间
    private Long              ctime;
    /** 稿件二级分区 **/
    private Integer           tid;
    private Integer           pid;
    private String            pName;
    private String            tname;

    /** 动态描述(/videoup/view) **/
    private String            dynamic;

    /** 稿件简介(/videoup/view) **/
    private String            desc;

    /** 稿件标签,以逗号分隔(/videoup/view) **/
    private String            tag;

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getPtime() {
        return ptime;
    }

    public void setPtime(Long ptime) {
        this.ptime = ptime;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getTname() {
        return tname;
    }

    public ArchiveInfo setTname(String tname) {
        this.tname = tname;
        return this;
    }

    public String getDynamic() {
        return dynamic;
    }

    public ArchiveInfo setDynamic(String dynamic) {
        this.dynamic = dynamic;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public ArchiveInfo setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public ArchiveInfo setTag(String tag) {
        this.tag = tag;
        return this;
    }

}