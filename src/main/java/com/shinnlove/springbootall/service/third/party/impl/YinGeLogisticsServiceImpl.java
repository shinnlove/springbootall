/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.third.party.impl;

import com.shinnlove.springbootall.db.po.MonthTicketCardSellOrderInfoEntity;
import com.shinnlove.springbootall.service.third.party.OrderExpressService;
import com.shinnlove.springbootall.service.third.party.TicketOrderService;
import com.shinnlove.springbootall.service.third.party.YinGeLogisticsService;
import com.shinnlove.springbootall.util.third.party.YinGeResultFactory;
import com.shinnlove.springbootall.util.third.party.dto.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: YinGeLogisticsServiceImpl.java, v 0.1 2025-03-07 18:13 Tony Zhao Exp $$
 */
@Service
public class YinGeLogisticsServiceImpl implements YinGeLogisticsService {

    /** 票务订单服务 */
    @Resource
    private TicketOrderService ticketOrderService;

    /** 订单快递服务 */
    @Resource
    private OrderExpressService orderExpressService;

    @Override
    public YinGeResult<Integer> logisticsNotify(YinGeLogisticsInfo logisticsInfo) {

        String outTradeNo = logisticsInfo.getOutTradeNo();

        long orderNo = 0L;
        try {
            orderNo = Long.parseLong(outTradeNo);
        } catch (NumberFormatException e) {
            return YinGeResultFactory.fail(201, "outTradeNo is not a number");
        }

        LogisticsCompanyInfo info = new LogisticsCompanyInfo();
        info.setCompanyCode(logisticsInfo.getCompanyCode());
        info.setExpressNo(logisticsInfo.getExpressNo());
        info.setRedoOrder(logisticsInfo.getRedoOrder());

        // 1st query order info express no
        MonthTicketCardSellOrderInfoEntity entity = ticketOrderService.queryOrderInfoByOrderNo(orderNo);

        if (StringUtils.isBlank(entity.getExpressNo())) {
            // need update order express info for the first time
            int result = ticketOrderService.updateOrderWithExpressInfo(orderNo, info);
            if (result <= 0) {
                return YinGeResultFactory.fail(202, "update order express info failed");
            }
        }

        String expressNo = logisticsInfo.getExpressNo();

        int total = 0;
        List<LogisticsExpressTrace> traces = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(logisticsInfo.getExpressTrace())) {
            for (YinGeExpressTrace trace : logisticsInfo.getExpressTrace()) {
                traces.add(LogisticsExpressTrace.builder()
                        .city(trace.getCity())
                        .time(trace.getTime())
                        .status(trace.getStatus())
                        .context(trace.getContext())
                        .build());
            }

            // 2nd. update logistics number
            total = orderExpressService.storeLogisticsInfo(expressNo, traces);
        }

        return YinGeResultFactory.success(total);
    }

}