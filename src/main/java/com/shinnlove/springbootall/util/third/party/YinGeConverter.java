/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.third.party;

import com.shinnlove.springbootall.db.po.MonthTicketCardSellOrderInfoEntity;
import com.shinnlove.springbootall.util.third.party.dto.YinGeConsignee;
import com.shinnlove.springbootall.util.third.party.dto.YinGeConsigneeOption;
import com.shinnlove.springbootall.util.third.party.dto.YinGeOrderInfo;
import com.shinnlove.springbootall.util.third.party.dto.YinGeProductInfo;

import java.util.Objects;

/**
 * @author Tony Zhao
 * @version $Id: YinGeConverter.java, v 0.1 2025-03-07 10:47 Tony Zhao Exp $$
 */
public class YinGeConverter {

    public static YinGeOrderInfo convert2YinGeOrderInfo(MonthTicketCardSellOrderInfoEntity entity) {

        if (Objects.isNull(entity)) {
            return null;
        }

        // 商品
        YinGeProductInfo productInfo = YinGeProductInfo.builder()
                // stub-id作为商品id
                .id(entity.getStubId())
                .quantity(1)
                .customizeNo(entity.getCustomizeNo())
                .build();

        // 地址加密
        YinGeConsigneeOption option = new YinGeConsigneeOption("oaid-13456");

        // 收货人
        YinGeConsignee consignee = YinGeConsignee.builder()
                .name(entity.getConsigneeName())
                .mobile(entity.getConsigneeMobile())
                .province(entity.getProvince())
                .city(entity.getCity())
                .district(entity.getDistrict())
                .address(entity.getAddressLine1() + ", " + entity.getAddressLine2())
                .options(option)
                .build();

        return YinGeOrderInfo.builder()
                .outTradeNo(String.valueOf(entity.getOrderNo()))
                .outUserId(String.valueOf(entity.getGuid()))
                // todo: use order enum
//                .orderStatus(entity.getOrderStatus())
                .createdAt((int) entity.getCreateTime().getTime() / 1000)
                .paidAt((int) entity.getPaidTime().getTime() / 1000)
                // todo: google guava
//                .orderProducts(ImmutableList.of(productInfo))
                .consignee(consignee)
                .extendParams(entity.getBizExt())
                .build();
    }

}