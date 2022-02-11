/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.model.initialization;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Xml process status parse class.
 * 
 * @author Tony Zhao
 * @version $Id: XmlProcessStatus.java, v 0.1 2022-01-28 5:58 PM Tony Zhao Exp $$
 */
public class XmlProcessStatus implements Serializable, Comparable<XmlProcessStatus> {

    /** uuid */
    private static final long serialVersionUID = 2482692196063669117L;

    /** status number No. */
    private int               no               = -1;

    /** status order */
    private int               sequence         = 0;

    /** status name key */
    private String            name;

    /** status description */
    private String            desc;

    /** status refer to parent status if parent exists! default -1 represent no parent. */
    private int               ps               = -1;

    /** indicate whether a status is normally completed. */
    private int               ac               = 0;

    /**
     * Constructor for reflect.
     */
    public XmlProcessStatus() {
    }

    /**
     * Constructor with all arguments.
     * 
     * @param no 
     * @param sequence
     * @param name
     * @param desc
     * @param ps
     * @param ac
     */
    public XmlProcessStatus(int no, int sequence, String name, String desc, int ps, int ac) {
        this.no = no;
        this.sequence = sequence;
        this.name = name;
        this.desc = desc;
        this.ps = ps;
        this.ac = ac;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public int getAc() {
        return ac;
    }

    public void setAc(int ac) {
        this.ac = ac;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    public int compareTo(XmlProcessStatus o) {
        if (this.sequence < o.getSequence()) {
            return -1;
        } else if (this.sequence == o.getSequence()) {
            return 0;
        } else {
            return 1;
        }
    }

}