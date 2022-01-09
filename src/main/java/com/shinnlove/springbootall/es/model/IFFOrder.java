/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.es.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author Tony Zhao
 * @version $Id: IFFOrder.java, v 0.1 2022-01-09 7:26 PM Tony Zhao Exp $$
 */
@Document(indexName = "iff")
public class IFFOrder implements Serializable {

    private static final long serialVersionUID = 6592319365674907056L;

    @Id
    private Long              id;

    @Field(type = FieldType.Keyword)
    private String            cartId;

    @Field(type = FieldType.Keyword)
    private String            vendor;

    @Field(type = FieldType.Keyword)
    private String            transactionId;

    @Field(type = FieldType.Keyword)
    private String            fundingStatus;

    @Field(type = FieldType.Integer)
    private int               isPrimaryOrder;

    @Field(type = FieldType.Double)
    private double            price;

    @Field(type = FieldType.Keyword)
    private String            message;

    @Field(type = FieldType.Text)
    private String            payloads;

    public IFFOrder() {
    }

    public IFFOrder(Long id, String cartId, String vendor, String transactionId,
                    String fundingStatus, int isPrimaryOrder, double price, String message,
                    String payloads) {
        this.id = id;
        this.cartId = cartId;
        this.vendor = vendor;
        this.transactionId = transactionId;
        this.fundingStatus = fundingStatus;
        this.isPrimaryOrder = isPrimaryOrder;
        this.price = price;
        this.message = message;
        this.payloads = payloads;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getFundingStatus() {
        return fundingStatus;
    }

    public void setFundingStatus(String fundingStatus) {
        this.fundingStatus = fundingStatus;
    }

    public int getIsPrimaryOrder() {
        return isPrimaryOrder;
    }

    public void setIsPrimaryOrder(int isPrimaryOrder) {
        this.isPrimaryOrder = isPrimaryOrder;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPayloads() {
        return payloads;
    }

    public void setPayloads(String payloads) {
        this.payloads = payloads;
    }

}