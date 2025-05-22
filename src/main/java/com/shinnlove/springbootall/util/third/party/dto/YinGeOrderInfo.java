/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.third.party.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 印鸽需要的订单信息。
 *
 * @author Tony Zhao
 * @version $Id: YinGeOrderInfo.java, v 0.1 2025-03-04 10:49 Tony Zhao Exp $$
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class YinGeOrderInfo {

    /** 业务方订单编号 */
    private String outTradeNo;

    /** 业务方系统内用户标识 */
    private String outUserId;

    /** 订单状态：PAID|REFUNED */
    private String orderStatus;

    /** 订单创建时间 */
    private int createdAt;

    /** 订单支付时间 */
    private int paidAt;

    /** 订单商品 */
    private List<YinGeProductInfo> orderProducts;

    /** 收货人信息 */
    private YinGeConsignee consignee;

    /** 订单扩展信息 */
    private String extendParams;

}