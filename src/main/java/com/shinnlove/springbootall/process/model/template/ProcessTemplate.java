/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.model.template;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Process Template Model.
 *
 * @author Tony Zhao
 * @version $Id: ProcessTemplate.java, v 0.1 2021-07-06 3:57 PM Tony Zhao Exp $$
 */
@Deprecated
public class ProcessTemplate implements Serializable {
    private static final long serialVersionUID = 3868454237819062121L;

    private long              id;

    private int               templateId;

    private String            templateName;

    private int               completeReconcileParent;

    private int               coordinateMode;

    private String            remark;

    public ProcessTemplate() {
    }

    public ProcessTemplate(long id, int templateId, String templateName,
                           int completeReconcileParent, int coordinateMode, String remark) {
        this.id = id;
        this.templateId = templateId;
        this.templateName = templateName;
        this.completeReconcileParent = completeReconcileParent;
        this.coordinateMode = coordinateMode;
        this.remark = remark;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
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