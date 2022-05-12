/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.models;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Tony Zhao
 * @version $Id: SubmitReviseItem.java, v 0.1 2022-02-28 3:18 PM Tony Zhao Exp $$
 */
public class SubmitReviseItem implements Serializable {

    private static final long serialVersionUID = 6965755265173304210L;

    private int               reviseItemType;

    /**
     * 目标状态
     */
    private int               dst              = -1;

    private BigDecimal        reviseValueBefore;
    private BigDecimal        reviseValueAfter;

    public SubmitReviseItem() {
    }

    public SubmitReviseItem(int reviseItemType) {
        this.reviseItemType = reviseItemType;
    }

    public SubmitReviseItem(int reviseItemType, BigDecimal reviseValueBefore,
                            BigDecimal reviseValueAfter) {
        this.reviseItemType = reviseItemType;
        this.reviseValueBefore = reviseValueBefore;
        this.reviseValueAfter = reviseValueAfter;
    }

    public int getReviseItemType() {
        return reviseItemType;
    }

    public void setReviseItemType(int reviseItemType) {
        this.reviseItemType = reviseItemType;
    }

    public BigDecimal getReviseValueBefore() {
        return reviseValueBefore;
    }

    public void setReviseValueBefore(BigDecimal reviseValueBefore) {
        this.reviseValueBefore = reviseValueBefore;
    }

    public BigDecimal getReviseValueAfter() {
        return reviseValueAfter;
    }

    public void setReviseValueAfter(BigDecimal reviseValueAfter) {
        this.reviseValueAfter = reviseValueAfter;
    }

    public int getDst() {
        return dst;
    }

    public void setDst(int dst) {
        this.dst = dst;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}