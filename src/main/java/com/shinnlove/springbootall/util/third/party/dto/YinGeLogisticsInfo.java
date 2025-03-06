/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.third.party.dto;

import lombok.Data;

import java.util.List;

/**
 * 印鸽同步的物流通知领域模型。
 *
 * @author Tony Zhao
 * @version $Id: YinGeLogisticsInfo.java, v 0.1 2025-03-04 16:59 Tony Zhao Exp $$
 */
@Data
public class YinGeLogisticsInfo extends YinGeSignature {

    /** 制作完成页面跳转回业务方时会携带该参数，如果有多个用逗号隔开，如: "EG001,EG002" */
    private String customizeNo;

    /** 业务方订单编号 */
    private String outTradeNo;

    /** 物流公司编码 */
    private String companyCode;

    /** 物流编号 */
    private String expressNo;

    /** 物流跟踪详情。默认不开启，需要联系对接人员开通 */
    private List<YinGeExpressTrace> expressTrace;

    /** 重打类订单，默认不推送，需要联系对接人员开通。1=重打订单 */
    private String redoOrder;

    private static final long serialVersionUID = 3910140462754452324L;

}