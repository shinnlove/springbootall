/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.third.party.impl;

import com.shinnlove.springbootall.db.po.MonthTicketCardSellOrderInfoEntity;
import com.shinnlove.springbootall.service.third.party.TicketOrderService;
import com.shinnlove.springbootall.service.third.party.YinGeOrderService;
import com.shinnlove.springbootall.util.third.party.YinGeConverter;
import com.shinnlove.springbootall.util.third.party.YinGeResultFactory;
import com.shinnlove.springbootall.util.third.party.dto.YinGeOrderInfo;
import com.shinnlove.springbootall.util.third.party.dto.YinGeOrderInterceptorInfo;
import com.shinnlove.springbootall.util.third.party.dto.YinGeResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author Tony Zhao
 * @version $Id: YinGeOrderServiceImpl.java, v 0.1 2025-03-07 10:43 Tony Zhao Exp $$
 */
@Service
public class YinGeOrderServiceImpl implements YinGeOrderService {

    @Resource
    private TicketOrderService ticketOrderService;

    @Override
    public YinGeResult<YinGeOrderInfo> queryOrderInfo(String outTradeNo) {

        // todo: use redis to cache temporary data

        // order number convention
        long orderNo = 0L;
        try {
            orderNo = Long.parseLong(outTradeNo);
        } catch (NumberFormatException e) {
            return YinGeResultFactory.fail(1, "订单号格式错误");
        }

        MonthTicketCardSellOrderInfoEntity ticketOrder = ticketOrderService.queryOrderInfoByOrderNo(orderNo);

        if (Objects.isNull(ticketOrder)) {
            return YinGeResultFactory.fail(2, "订单不存在");
        }

        YinGeOrderInfo orderInfo = YinGeConverter.convert2YinGeOrderInfo(ticketOrder);

        return YinGeResultFactory.success(orderInfo);
    }

    @Override
    public YinGeResult<Integer> orderIntercept(YinGeOrderInterceptorInfo interceptorInfo) {
        return null;
    }

}