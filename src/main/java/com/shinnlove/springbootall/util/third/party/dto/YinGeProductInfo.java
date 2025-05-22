/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.third.party.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 印鸽需要的订单商品信息。
 *
 * @author Tony Zhao
 * @version $Id: YinGeProductInfo.java, v 0.1 2025-03-04 16:51 Tony Zhao Exp $$
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YinGeProductInfo {

    /** 业务方商品ID */
    private String id;

    /** 业务方商品数量 */
    private int quantity;

    /** 印鸽子定制单号，用于一个订单有多个定制效果 */
    private String customizeNo;

}