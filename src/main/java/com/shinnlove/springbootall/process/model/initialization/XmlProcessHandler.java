/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.model.initialization;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Xml file process handler attributes domain model,
 * It implement the Comparable<T> interface for sorting after read handlers from xml file unordered.
 *
 * @author Tony Zhao
 * @version $Id: XmlProcessHandler.java, v 0.1 2021-07-21 2:55 PM Tony Zhao Exp $$
 */
public class XmlProcessHandler implements Serializable, Comparable<XmlProcessHandler> {

    private static final long serialVersionUID = 4990649830797400714L;

    /** handler bean id in springboot context */
    private String            refBeanId;
    /** handler sequence */
    private int               sequence;
    /** handler description */
    private String            desc;
    /** handler execution in trans */
    private boolean           trans            = true;

    /**
     * Constructor for reflect.
     */
    public XmlProcessHandler() {
    }

    public XmlProcessHandler(boolean trans) {
        this.trans = trans;
    }

    public XmlProcessHandler(String refBeanId, int sequence, String desc, boolean trans) {
        this.refBeanId = refBeanId;
        this.sequence = sequence;
        this.desc = desc;
        this.trans = trans;
    }

    public String getRefBeanId() {
        return refBeanId;
    }

    public void setRefBeanId(String refBeanId) {
        this.refBeanId = refBeanId;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isTrans() {
        return trans;
    }

    public void setTrans(boolean trans) {
        this.trans = trans;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    public int compareTo(XmlProcessHandler o) {
        if (this.sequence < o.getSequence()) {
            return -1;
        } else if (this.sequence == o.getSequence()) {
            return 0;
        } else {
            return 1;
        }
    }

}