/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.converter;

import com.shinnlove.springbootall.db.po.MonthTicketCardSellOrderInfoEntity;
import com.shinnlove.springbootall.enums.ExpressCompanyEnum;
import com.shinnlove.springbootall.enums.MaterialTypeEnums;
import com.shinnlove.springbootall.enums.OrderStatusEnums;
import com.shinnlove.springbootall.models.MonthTicketSellOrderInfo;

/**
 * @author Tony Zhao
 * @version $Id: OrderInfoConverter.java, v 0.1 2025-03-13 14:33 Tony Zhao Exp $$
 */
public class OrderInfoConverter {

    /**
     * 将DO模型转化为DTO模型。
     *
     * @param entity
     * @return
     */
    public static MonthTicketSellOrderInfo convert2OrderDTO(MonthTicketCardSellOrderInfoEntity entity) {
        return MonthTicketSellOrderInfo.builder()
                .id(entity.getId())
                .activityId(entity.getActivityId())
                .componentId(entity.getComponentId())
                .guid(entity.getGuid())
                .stubId(entity.getStubId())
                .customizeNo(entity.getCustomizeNo())
                .orderNo(entity.getOrderNo())
                .payOrderNo(entity.getPayOrderNo())
                .status(entity.getStatus())
                .statusDesc(OrderStatusEnums.getDescByCode(entity.getStatus()))
                .thumbUrl(entity.getThumbUrl())
                .materialType(entity.getMaterialType())
                .materialTypeDesc(MaterialTypeEnums.getDescByCode(entity.getMaterialType()))
                .gildSelected(entity.getGildSelected())
                .appType(entity.getAppType())
                .itemId(entity.getItemId())
                .productType(entity.getProductType())
                .qimei(entity.getQimei())
                .qimei36(entity.getQimei36())
                .userContextJson(entity.getUserContextJson())
                .deviceContextJson(entity.getDeviceContextJson())
                .amount(entity.getAmount())
                .userAddressLogId(entity.getUserAddressLogId())
                // 收货人信息需要解密
                .consigneeName((entity.getConsigneeName()))
                .consigneeMobile((entity.getConsigneeMobile()))
                .province((entity.getProvince()))
                .city((entity.getCity()))
                .district((entity.getDistrict()))
                .addressLine1((entity.getAddressLine1()))
                .addressLine2((entity.getAddressLine2()))
                .bizExt(entity.getBizExt())
                .companyCode(entity.getCompanyCode())
                .companyCodeDesc(ExpressCompanyEnum.getExpressCompanyNameByCode(entity.getCompanyCode()))
                .expressNo(entity.getExpressNo())
                .redoOrder(entity.getRedoOrder())
                .expireTime(entity.getExpireTime())
                .paidTime(entity.getPaidTime())
                .noticedPaidTime(entity.getNoticedPaidTime())
                .refundTime(entity.getRefundTime())
                .createTime(entity.getCreateTime())
                .updateTime(entity.getUpdateTime())
                .remark(entity.getRemark())
                .build();
    }

}