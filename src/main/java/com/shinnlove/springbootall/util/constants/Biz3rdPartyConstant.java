/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.constants;

import java.util.Arrays;
import java.util.List;

/**
 * 跟印鸽对接的三方接口所需的常量字段。
 *
 * @author Tony Zhao
 * @version $Id: Biz3rdPartyConstant.java, v 0.1 2025-03-04 14:42 Tony Zhao Exp $$
 */
public class Biz3rdPartyConstant {

    /** 空字符串 */
    public static final String EMPTY_STR = "";

    /** 查询无限制 */
    public static final String NO_LIMITATION = "NO_LIMITATION";

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

    public static final String REDO_ORDER = "redoOrder";

    public static final String MATERIAL_TYPE = "materialType";

    /** 5th. 其他常量 */

    /** Amis订单查看生产环境地址 */
    public static final String ORDER_INFO_LINK = "https://amis.yuewen.com/group/QDActivityTools/pageQueryMonthTicketOrder?perPage=20&status=-1&materialType=NO_LIMITATION&gildSelected=NO_LIMITATION&page=1&orderNo=";

    /** 6th. 下载导出文件表头 */

    /** 订单导出表头字段 */
    private static final String[] ORDER_EXPORT_FIELDS = {"记录时间", "订单编号", "用户guid", "用户昵称", "订单状态", "支付时间", "通知印鸽已支付时间", "印鸽定制编号", "订单价格(分)", "材质(是否升级)", "是否黄金材质" };

    /** 表头列表 */
    public static final List<String> ORDER_EXPORT_HEADER = Arrays.asList(ORDER_EXPORT_FIELDS);

}