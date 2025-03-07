/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.constants;

import lombok.Data;

/**
 * 月票业务相关配置。
 *
 * @author Tony Zhao
 * @version $Id: MonthTicketBizConfig.java, v 0.1 2025-03-03 16:18 Tony Zhao Exp $$
 */
@Data
public class MonthTicketBizConfig {

    /** 阅文在印鸽侧的flag */
    private String resellerFlag = "qddsipqddsipqddsip";

    /** 印鸽给阅文的secret token */
    private String secret = "e8428181ea320d7cc5ddc4ec241ef8a2";

    /** 月票实体卡定制印鸽商品流水线编号 */
    private String productId = "202503066688001";

    /** 印鸽默认接口版本 */
    private int version = 1;

    /** 印鸽接口签名版本 */
    private int signType = 2;

    /** 自己本地调试看http请求域名 */
    private String localhostDebugDomain = "http://127.0.0.1:9000";

    /** 三方接入接口域名 */
    private String thirdPartyDomain = "https://open.yinge.tech";

    /** 三方mock接口沙箱域名 */
    private String thirdPartySandBoxDomain = "https://preapi.songzhaopian.com";

    /** 1st. 订单付款通知 */
    private String thirdPartyOrderPaidNotifyEndpoint = "/resellers/thirdparty/order/paid";

    /** 2nd. 印鸽给的自定义信息校验三方url地址 */
    private String customizeInfoValidateUrl = "/resellers/thirdparty/qddsip/customize/info";

    /** 3rd. 发货地址修改 */
    private String thirdPartyModifyUserAddressEndpoint = "/resellers/thirdparty/order/address/change";

    /** 4th. 订单退款接口 */
    private String thirdPartyOrderRefundEndpoint = "/resellers/thirdparty/order/refund";

}