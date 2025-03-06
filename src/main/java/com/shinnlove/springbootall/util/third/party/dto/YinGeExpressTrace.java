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
 * 印鸽物流站点追踪信息领域模型。
 *
 * @author Tony Zhao
 * @version $Id: YinGeExpressTrace.java, v 0.1 2025-03-04 17:01 Tony Zhao Exp $$
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YinGeExpressTrace {

    /** 物流时间 */
    private String time;

    /** 物流站点信息 */
    private String context;

    /** 物流状态码 */
    private String status;

    /** 物流信息所在城市 */
    private String city;

}