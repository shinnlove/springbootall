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
 * @version $Id: ReviseUpdateOrder.java, v 0.1 2022-05-12 10:33 AM Tony Zhao Exp $$
 */
public class ReviseUpdateOrder implements Serializable {

    private static final long serialVersionUID = -5188078495759596828L;

    /** 要修改的订单id */
    private int               orderId;

    /** 改价的父批次 */
    private long              refUniqueNo;

    /** 订单金额 */
    private BigDecimal        price;

    /** 信息技术费 */
    private BigDecimal        serviceFee;

    /** UP主订单收益 */
    private BigDecimal        actualUpperPrice;

    /** 服务商订单收益 */
    private BigDecimal        serviceProviderIncome;

    /** 订单支出 */
    private BigDecimal        actualCostPrice;

    public ReviseUpdateOrder() {
    }

    public ReviseUpdateOrder(int orderId) {
        this.orderId = orderId;
    }

    public ReviseUpdateOrder(int orderId, long refUniqueNo) {
        this.orderId = orderId;
        this.refUniqueNo = refUniqueNo;
    }

    public ReviseUpdateOrder(int orderId, long refUniqueNo, BigDecimal price, BigDecimal serviceFee,
                             BigDecimal actualUpperPrice, BigDecimal serviceProviderIncome,
                             BigDecimal actualCostPrice) {
        this.orderId = orderId;
        this.refUniqueNo = refUniqueNo;
        this.price = price;
        this.serviceFee = serviceFee;
        this.actualUpperPrice = actualUpperPrice;
        this.serviceProviderIncome = serviceProviderIncome;
        this.actualCostPrice = actualCostPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public long getRefUniqueNo() {
        return refUniqueNo;
    }

    public void setRefUniqueNo(long refUniqueNo) {
        this.refUniqueNo = refUniqueNo;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }

    public BigDecimal getActualUpperPrice() {
        return actualUpperPrice;
    }

    public void setActualUpperPrice(BigDecimal actualUpperPrice) {
        this.actualUpperPrice = actualUpperPrice;
    }

    public BigDecimal getServiceProviderIncome() {
        return serviceProviderIncome;
    }

    public void setServiceProviderIncome(BigDecimal serviceProviderIncome) {
        this.serviceProviderIncome = serviceProviderIncome;
    }

    public BigDecimal getActualCostPrice() {
        return actualCostPrice;
    }

    public void setActualCostPrice(BigDecimal actualCostPrice) {
        this.actualCostPrice = actualCostPrice;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}