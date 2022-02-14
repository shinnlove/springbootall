/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.model.cascade;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Tony Zhao
 * @version $Id: PrepareParent.java, v 0.1 2022-02-14 2:52 PM Tony Zhao Exp $$
 */
public class PrepareParent implements Serializable {

    private static final long serialVersionUID = 5431365365471103612L;

    private String            className;
    private int               parentTemplateId;

    /**
     * Constructor for reflect.
     */
    public PrepareParent() {
    }

    public PrepareParent(String className, int parentTemplateId) {
        this.className = className;
        this.parentTemplateId = parentTemplateId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getParentTemplateId() {
        return parentTemplateId;
    }

    public void setParentTemplateId(int parentTemplateId) {
        this.parentTemplateId = parentTemplateId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}