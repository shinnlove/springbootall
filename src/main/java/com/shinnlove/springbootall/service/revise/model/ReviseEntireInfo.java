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
 * @version $Id: Revise.java, v 0.1 2022-02-24 6:01 PM Tony Zhao Exp $$
 */
public class ReviseEntireInfo implements Serializable {

    private static final long serialVersionUID = -4982090353101517638L;

    private long              createId;
    private String            creatorName;

    private BigDecimal        upperProfitBefore;
    private BigDecimal        upperProfitAfter;

    private BigDecimal        orderPriceBefore;
    private BigDecimal        orderPriceAfter;

    private BigDecimal        orderExpenseBefore;
    private BigDecimal        orderExpenseAfter;

    private BigDecimal        serviceFeeBefore;
    private BigDecimal        serviceFeeAfter;

    private BigDecimal        vendorProfitBefore;
    private BigDecimal        vendorProfitAfter;

    private String            reviseReason;

    public ReviseEntireInfo() {
    }

    public ReviseEntireInfo(long createId, String creatorName, BigDecimal upperProfitBefore,
                            BigDecimal upperProfitAfter, BigDecimal orderPriceBefore,
                            BigDecimal orderPriceAfter, BigDecimal orderExpenseBefore,
                            BigDecimal orderExpenseAfter, BigDecimal serviceFeeBefore,
                            BigDecimal serviceFeeAfter, BigDecimal vendorProfitBefore,
                            BigDecimal vendorProfitAfter, String reviseReason) {
        this.createId = createId;
        this.creatorName = creatorName;
        this.upperProfitBefore = upperProfitBefore;
        this.upperProfitAfter = upperProfitAfter;
        this.orderPriceBefore = orderPriceBefore;
        this.orderPriceAfter = orderPriceAfter;
        this.orderExpenseBefore = orderExpenseBefore;
        this.orderExpenseAfter = orderExpenseAfter;
        this.serviceFeeBefore = serviceFeeBefore;
        this.serviceFeeAfter = serviceFeeAfter;
        this.vendorProfitBefore = vendorProfitBefore;
        this.vendorProfitAfter = vendorProfitAfter;
        this.reviseReason = reviseReason;
    }

    public long getCreateId() {
        return createId;
    }

    public void setCreateId(long createId) {
        this.createId = createId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
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

    public BigDecimal getOrderPriceBefore() {
        return orderPriceBefore;
    }

    public void setOrderPriceBefore(BigDecimal orderPriceBefore) {
        this.orderPriceBefore = orderPriceBefore;
    }

    public BigDecimal getOrderPriceAfter() {
        return orderPriceAfter;
    }

    public void setOrderPriceAfter(BigDecimal orderPriceAfter) {
        this.orderPriceAfter = orderPriceAfter;
    }

    public BigDecimal getOrderExpenseBefore() {
        return orderExpenseBefore;
    }

    public void setOrderExpenseBefore(BigDecimal orderExpenseBefore) {
        this.orderExpenseBefore = orderExpenseBefore;
    }

    public BigDecimal getOrderExpenseAfter() {
        return orderExpenseAfter;
    }

    public void setOrderExpenseAfter(BigDecimal orderExpenseAfter) {
        this.orderExpenseAfter = orderExpenseAfter;
    }

    public BigDecimal getServiceFeeBefore() {
        return serviceFeeBefore;
    }

    public void setServiceFeeBefore(BigDecimal serviceFeeBefore) {
        this.serviceFeeBefore = serviceFeeBefore;
    }

    public BigDecimal getServiceFeeAfter() {
        return serviceFeeAfter;
    }

    public void setServiceFeeAfter(BigDecimal serviceFeeAfter) {
        this.serviceFeeAfter = serviceFeeAfter;
    }

    public BigDecimal getVendorProfitBefore() {
        return vendorProfitBefore;
    }

    public void setVendorProfitBefore(BigDecimal vendorProfitBefore) {
        this.vendorProfitBefore = vendorProfitBefore;
    }

    public BigDecimal getVendorProfitAfter() {
        return vendorProfitAfter;
    }

    public void setVendorProfitAfter(BigDecimal vendorProfitAfter) {
        this.vendorProfitAfter = vendorProfitAfter;
    }

    public String getReviseReason() {
        return reviseReason;
    }

    public void setReviseReason(String reviseReason) {
        this.reviseReason = reviseReason;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}