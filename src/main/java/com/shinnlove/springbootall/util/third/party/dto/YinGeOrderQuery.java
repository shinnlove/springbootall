/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.third.party.dto;

import lombok.Data;

/**
 * 印鸽请求查询订单的领域模型。
 *
 * @author Tony Zhao
 * @version $Id: YinGeOrderQuery.java, v 0.1 2025-03-04 17:07 Tony Zhao Exp $$
 */
@Data
public class YinGeOrderQuery extends YinGeSignature {

    /** 业务方订单编号 */
    private String outTradeNo;

    private static final long serialVersionUID = -732995456104624668L;

}