/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.models;

import lombok.*;

/**
 * @author Tony Zhao
 * @version $Id: MonthTicketOrderQuery.java, v 0.1 2025-03-13 14:29 Tony Zhao Exp $$
 */
@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonthTicketOrderQuery {

    /** 按印鸽自定义id查询 */
    private String customizeNo;

    /** 按orderNo查询 */
    private Long orderNo;

    /** 按支付订单查询 */
    private String payOrderNo;

    /** 按guid查询 */
    private Long guid;

    /** 分页页码 */
    private Integer page = 1;

    /** 分页条数 */
    private Integer pageSize = 10;

}