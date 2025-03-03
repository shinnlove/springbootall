package com.shinnlove.springbootall.db.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthTicketCardSellOrderInfoEntity implements Serializable {

    private Long id;

    private String activityId;

    private Long componentId;

    private Long guid;

    private String stubId;

    private String customizeNo;

    private String orderNo;

    private String payOrderNo;

    private Integer status;

    private Integer materialType;

    private String thumbUrl;

    private String appType;

    private String itemId;

    private Integer productType;

    private String qimei;

    private String qimei36;

    private String userContextJson;

    private String deviceContextJson;

    private Integer amount;

    private Long userAddressLogId;

    private String consigneeName;

    private String consigneeMobile;

    private String province;

    private String city;

    private String district;

    private String addressLine1;

    private String addressLine2;

    private Date fillAddressTime;

    private String companyCode;

    private String expressNo;

    private String redoOrder;

    private Date expireTime;

    private Date paidTime;

    private Date noticedPaidTime;

    private Date refundTime;

    private Date createTime;

    private Date updateTime;

    private String remark;

    private static final long serialVersionUID = 1L;

}