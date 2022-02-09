/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.model.cache;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Tony Zhao
 * @version $Id: TemplateMetadata.java, v 0.1 2022-02-09 3:05 PM Tony Zhao Exp $$
 */
public class TemplateMetadata implements Serializable {

    private static final long serialVersionUID        = 7568522756583893545L;

    private int               id;

    private int               parentId                = -1;

    private String            name;

    private String            desc;

    private int               completeReconcileParent = 0;

    private int               coordinateMode          = 1;

    /**
     * Constructor for reflect.
     */
    public TemplateMetadata() {
    }

    /**
     * Constructor with all arguments.
     * 
     * @param id 
     * @param parentId
     * @param name
     * @param desc
     * @param completeReconcileParent
     * @param coordinateMode
     */
    public TemplateMetadata(int id, int parentId, String name, String desc,
                            int completeReconcileParent, int coordinateMode) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.desc = desc;
        this.completeReconcileParent = completeReconcileParent;
        this.coordinateMode = coordinateMode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
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

    public int getCompleteReconcileParent() {
        return completeReconcileParent;
    }

    public void setCompleteReconcileParent(int completeReconcileParent) {
        this.completeReconcileParent = completeReconcileParent;
    }

    public int getCoordinateMode() {
        return coordinateMode;
    }

    public void setCoordinateMode(int coordinateMode) {
        this.coordinateMode = coordinateMode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}