/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.constants;

/**
 * 跟印鸽对接的三方接口所需的常量字段。
 *
 * @author Tony Zhao
 * @version $Id: Biz3rdPartyConstant.java, v 0.1 2025-03-04 14:42 Tony Zhao Exp $$
 */
public class Biz3rdPartyConstant {

    /** 1st. 签名验签算法常量 */

    public static final String SIGN_TYPE_MD5 = "MD5";

    /** 2nd. 公共请求方法常量 */

    /** GET请求 */
    public static final String METHOD_GET = "GET";

    /** POST请求 */
    public static final String METHOD_POST = "POST";

    /** 3rd. 三方签名所需公共参数常量 */

    public static final String TIMESTAMP = "timestamp";

    public static final String RESELLER_FLAG = "resellerFlag";

    public static final String VERSION = "version";

    public static final String SIGN_TYPE = "signType";

    public static final String SIGN = "sign";

    public static final String SECRET = "secret";

    /** 4th. 三方交互业务常量 */

    public static final String EXPRESS_TRACE = "expressTrace";

    public static final String CUSTOMIZE_NO = "customizeNo";

    public static final String OUT_TRADE_NO = "outTradeNo";

    public static final String COMPANY_CODE = "companyCode";

    public static final String EXPRESS_NO = "expressNo";

}