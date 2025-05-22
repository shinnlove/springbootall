/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.models;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Tony Zhao
 * @version $Id: MonthTicketSellOrderInfo.java, v 0.1 2025-03-13 14:28 Tony Zhao Exp $$
 */
@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MonthTicketSellOrderInfo implements Serializable {

    private Long id;

    private String activityId;

    private Long componentId;

    private Long guid;

    private String stubId;

    private String customizeNo;

    private Long orderNo;

    private String payOrderNo;

    private Integer status;

    /** 订单状态描述 */
    private String statusDesc;

    private String thumbUrl;

    /** 材质升级类型 */
    private Integer materialType;

    /** 材质升级类型描述 */
    private String materialTypeDesc;

    /** 是否抽中额外烫金黄金材质 */
    private Integer gildSelected;

    private String bizExt;

    private String appType;

    private String itemId;

    private Integer productType;

    private String qimei;

    private String qimei36;

    private String userContextJson;

    private String deviceContextJson;

    /** 购买类型 */
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
    // 物流公司枚举
    private String companyCode;

    private String companyCodeDesc;

    // 快递单号
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

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}