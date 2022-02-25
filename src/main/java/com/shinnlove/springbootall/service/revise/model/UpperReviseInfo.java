/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.revise.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Tony Zhao
 * @version $Id: UpperReviseInfo.java, v 0.1 2022-02-24 5:51 PM Tony Zhao Exp $$
 */
public class UpperReviseInfo implements Serializable {

    private static final long serialVersionUID = -3360568275179174300L;

    private BigDecimal        upperProfitBefore;

    private BigDecimal        upperProfitAfter;

    private String            reason;

    public UpperReviseInfo() {
    }

    public UpperReviseInfo(BigDecimal upperProfitBefore, BigDecimal upperProfitAfter) {
        this.upperProfitBefore = upperProfitBefore;
        this.upperProfitAfter = upperProfitAfter;
    }

    public UpperReviseInfo(BigDecimal upperProfitBefore, BigDecimal upperProfitAfter,
                           String reason) {
        this.upperProfitBefore = upperProfitBefore;
        this.upperProfitAfter = upperProfitAfter;
        this.reason = reason;
    }

    public BigDecimal getUpperProfitBefore() {
        return upperProfitBefore;
    }

    public void setUpperProfitBefore(BigDecimal upperProfitBefore) {
        this.upperProfitBefore = upperProfitBefore;
    }

    public BigDecimal getUpperProfitAfter() {
        return upperProfitAfter;
    }

    public void setUpperProfitAfter(BigDecimal upperProfitAfter) {
        this.upperProfitAfter = upperProfitAfter;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}