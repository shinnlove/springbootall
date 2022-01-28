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
    private int               no;

    /** status name key */
    private String            name;

    /** status description */
    private String            desc;

    /** status refer to parent status if parent exists! default -1 represent no parent. */
    private int               ps               = -1;

    public XmlProcessStatus() {
    }

    public XmlProcessStatus(int no, String name, String desc, int ps) {
        this.no = no;
        this.name = name;
        this.desc = desc;
        this.ps = ps;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    public int compareTo(XmlProcessStatus o) {
        if (this.no < o.getNo()) {
            return -1;
        } else if (this.no == o.getNo()) {
            return 0;
        } else {
            return 1;
        }
    }

}