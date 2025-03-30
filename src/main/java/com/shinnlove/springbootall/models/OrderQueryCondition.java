/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.models;

import lombok.*;

/**
 * @author Tony Zhao
 * @version $Id: OrderQueryCondition.java, v 0.1 2025-03-30 22:45 Tony Zhao Exp $$
 */
@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderQueryCondition {

    /** 按印鸽自定义id查询 */
    private String customizeNo;

    /** 按orderNo查询 */
    private Long orderNo;

    /** 按支付订单查询 */
    private String payOrderNo;

    /** 按guid查询 */
    private Long guid;

    /** 订单状态查询 */
    private Integer status;

    /** 是否升级流沙材质 */
    private Integer materialType;

    /** 是否赠送黄金 */
    private Integer gildSelected;

    /** 新条件: 支付查询开始时间(秒) */
    private Long startTime;

    /** 新条件: 支付查询结束时间(秒) */
    private Long endTime;

    /** 偏移位 */
    private Integer offset;

    /** 数量限制 */
    private Integer limit;

}