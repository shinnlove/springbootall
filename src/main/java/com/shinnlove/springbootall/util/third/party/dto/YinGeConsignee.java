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
 * 印鸽收货人信息领域模型。
 *
 * @author Tony Zhao
 * @version $Id: YinGeConsignee.java, v 0.1 2025-03-04 16:53 Tony Zhao Exp $$
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YinGeConsignee {

    /** 收货人姓名 */
    private String name;

    /** 收货人手机号 */
    private String mobile;

    /** 收货人省 */
    private String province;

    /** 收货人市 */
    private String city;

    /** 收货人区 */
    private String district;

    /** 收货人地址栏1 */
    private String address;

    /** 地址扩展: 针对收件人姓名电话详细地址被加密的场景，用oaid换解密地址 */
    private YinGeConsigneeOption options;

}