/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.biz.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Tony Zhao
 * @version $Id: ParentProcessInfo.java, v 0.1 2022-02-13 5:19 PM Tony Zhao Exp $$
 */
public class ParentProcessInfo implements Serializable {

    private static final long serialVersionUID = 8713811730955658334L;

    /** child process execute result */
    private int               result;

    /** child process handler 1 execute result */
    private String            auditResult;

    /** complex mix info */
    private ComplexInfo       complexInfo;

    public ParentProcessInfo() {
    }

    public ParentProcessInfo(int result, String auditResult, ComplexInfo complexInfo) {
        this.result = result;
        this.auditResult = auditResult;
        this.complexInfo = complexInfo;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(String auditResult) {
        this.auditResult = auditResult;
    }

    public ComplexInfo getComplexInfo() {
        return complexInfo;
    }

    public void setComplexInfo(ComplexInfo complexInfo) {
        this.complexInfo = complexInfo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}