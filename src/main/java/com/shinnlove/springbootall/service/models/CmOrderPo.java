/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author Tony Zhao
 * @version $Id: CmOrderPo.java, v 0.1 2022-05-10 8:57 PM Tony Zhao Exp $$
 */
public class CmOrderPo implements Serializable {
    /**
     * 主键 自增ID
     */
    private Integer    id;

    /**
     * 订单号
     */
    private Long       orderNo;

    /**
     * 任务id
     */
    private Integer    taskId;

    /**
     * mcn账号id
     */
    private Long       mcnId;

    /**
     * 关联的up主mid
     */
    private Long       upperMid;

    /**
     * 代理商id
     */
    private Integer    agentId;

    /**
     * 二级代理商mid
     */
    private Long       secondaryAgentMid;

    /**
     * 广告主id
     */
    private Integer    accountId;

    /**
     * 合作类型（1-植入视频 2-定制视频）
     */
    private Integer    cooperationType;

    /**
     * 已付款金额
     */
    private BigDecimal paidPrice;

    /**
     * UP主基础报价/up主刊例价
     */
    private BigDecimal upperPrice;

    /**
     * 基于UP主基础报价下单瞬间设定的改价基准
     */
    private BigDecimal reviseBase;

    /**
     * up主平台报价（订单的初始价）
     */
    private BigDecimal platformPrice;

    /**
     * 实际UP主收益
     */
    private BigDecimal actualUpperPrice;

    /**
     * 实际平台收益
     */
    private BigDecimal actualPlatformPrice;

    /**
     * 实际支出成本
     */
    private BigDecimal actualCostPrice;

    /**
     * 平台服务费率
     */
    private BigDecimal serviceFeeRate;

    /**
     * 其他信息技术服务费
     */
    private BigDecimal serviceFee;

    /**
     * 订单价格
     */
    private BigDecimal price;

    /**
     * 订单状态
     */
    private Integer    orderStatus;

    /**
     * 终止操作发起时订单当时的状态
     */
    private Integer    terminationBeforeStatus;

    /**
     * 是否发布邀约广告（0-否 1-是）
     */
    private Integer    isPublishInvitationAd;

    /**
     * 是否跳过交付件（0-不跳过 1-跳过）
     */
    private Integer    isSkipDeliverable;

    /**
     * 销售支持人员
     */
    private String     salesSupportUser;

    /**
     * 销售支持人员id
     */
    private Integer    salesSupportUserId;

    /**
     * 视频上线时间
     */
    private Timestamp  onlineTime;

    /**
     * UP主类型：（1-签约UP主 2-订单UP主入库 3-个人UP主）
     */
    private Integer    upperType;

    /**
     * CRM合同订单ID
     */
    private Integer    crmContractOrderId;

    /**
     * 超电订单id
     */
    private String     chaodianOrderId;

    /**
     * UP主接受邀约广告状态：1-无 2-待确认 3-已接受 4-已拒绝
     */
    private Integer    acceptInvitationAdStatus;

    /**
     * 状态翻转时间
     */
    private Timestamp  statusMtime;

    private Integer    isDeleted;

    /**
     * 创建时间
     */
    private Timestamp  ctime;

    /**
     * 更新时间
     */
    private Timestamp  mtime;

    /**
     * 最新终稿id
     */
    private Integer    finalDraftId;

    /**
     * 折扣系数
     */
    private BigDecimal discountRate;

    /**
     * 操作人角色  0-广告主  4-代理商   7-二级代理运营人员
     */
    private Integer    operatorType;

    /**
     * 操作状态占位（利用二进制占位）
     */
    private Integer    operateStatus;

    /**
     * 是否是核心账号（0-不是 1-是）
     */
    private Integer    isCoreAccount;

    /**
     * 服务商id
     */
    private Integer    serviceProviderId;

    /**
     * 结算方式  1-平台结算 2-服务商结算
     */
    private Integer    settleType;

    /**
     * 服务商抽成比率
     */
    private BigDecimal serviceProviderFeeRate;

    /**
     * 确认收款 （0-无需确认 1-待确认 2-已确认 3-超时确认）
     */
    private Integer    paymentConfirm;

    /**
     * 确认收款时间
     */
    private Timestamp  paymentConfirmTime;

    /**
     * 是否ka账号  1-是 0-否
     */
    private Integer    isKaAccount;

    /**
     * 关联结算信息id
     */
    private Integer    settleInfoId;

    /**
     * 终止订单操作退款的退款金额
     */
    private BigDecimal refundPrice;

    /**
     * 退款凭证
     */
    private String     refundVoucherUrl;

    /**
     * 视频上线开始时间
     */
    private Timestamp  onlineStartTime;

    /**
     * 合作类型备注
     */
    private String     cooperationTypeRemark;

    /**
     * 是否改价 0 否 1是
     */
    private Integer    isChangePrice;

    /**
     * 联合投稿ID
     */
    private Integer    publishId;

    /**
     * 是否特殊结算标识 (0:否, 1:是, 默认:0)
     */
    private Integer    specialSettleSign;

    /**
     * 服务商收益
     */
    private BigDecimal serviceProviderIncome;

    /**
     * 订单增值权益
     */
    private Integer    orderRights;

    /**
     * 二代主键
     */
    private Integer    secAgentId;

    /**
     * 客户期望视频上线时间
     */
    private Timestamp  expectedOnlineTime;

    /**
     * 客户驳回稿件推迟的上线时间
     */
    private Timestamp  draftDelayedTime;

    /**
     * 1-普通订单 2-虚拟订单
     */
    private Integer    orderType;

    /**
     * 价格版本号，默认version0是第一版改价
     */
    private Integer    priceVersion;

    /**
     * 终止订单其他信息技术服务费退款
     */
    private BigDecimal terminatedServiceFeeRefund;

    /**
     * 稿件审核驳回原因ID
     */
    private Integer    draftRejectReasonId;

}