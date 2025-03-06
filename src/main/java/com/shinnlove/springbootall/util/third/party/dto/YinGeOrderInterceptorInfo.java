/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.third.party.dto;

import lombok.Data;

/**
 * 印鸽订单拦截接口领域模型。
 *
 * @author Tony Zhao
 * @version $Id: YinGeOrderInterceptorInfo.java, v 0.1 2025-03-04 17:05 Tony Zhao Exp $$
 */
@Data
public class YinGeOrderInterceptorInfo extends YinGeSignature {

    /** 业务方订单编号 */
    public String outTradeNo;

    /** intercept=已拦截 */
    public String statusFlag;

    /** 拦截原因，statusFlag 为已拦截时才返回 */
    public String interceptReason;

    private static final long serialVersionUID = -4030837316078653012L;

}