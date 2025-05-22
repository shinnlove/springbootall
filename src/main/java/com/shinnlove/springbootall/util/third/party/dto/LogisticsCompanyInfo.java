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
 * @author Tony Zhao
 * @version $Id: LogisticsCompanyInfo.java, v 0.1 2025-03-07 18:16 Tony Zhao Exp $$
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogisticsCompanyInfo {

    /** 三方公司定义: 重打类订单。1=重打订单 */
    private String redoOrder;

    /** 物流公司编码 */
    private String companyCode;

    /** 物流单号 */
    private String expressNo;

}