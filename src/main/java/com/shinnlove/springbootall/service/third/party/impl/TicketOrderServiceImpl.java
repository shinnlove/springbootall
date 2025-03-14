/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.third.party.impl;

import com.shinnlove.springbootall.db.dao.MonthTicketCardSellOrderInfoRepo;
import com.shinnlove.springbootall.db.po.MonthTicketCardSellOrderInfoEntity;
import com.shinnlove.springbootall.exceptions.DBAccessThrowException;
import com.shinnlove.springbootall.exceptions.DBExecuteReturnException;
import com.shinnlove.springbootall.models.MonthTicketOrderQuery;
import com.shinnlove.springbootall.models.MonthTicketSellOrderInfo;
import com.shinnlove.springbootall.service.third.party.TicketOrderService;
import com.shinnlove.springbootall.util.converter.OrderInfoConverter;
import com.shinnlove.springbootall.util.third.party.dto.LogisticsCompanyInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Tony Zhao
 * @version $Id: TicketOrderServiceImpl.java, v 0.1 2025-03-07 10:45 Tony Zhao Exp $$
 */
@Service
public class TicketOrderServiceImpl implements TicketOrderService {

    @Autowired
    private MonthTicketCardSellOrderInfoRepo monthTicketCardSellOrderInfoRepo;

    @Override
    public MonthTicketCardSellOrderInfoEntity queryOrderInfoByOrderNo(Long orderNo) throws DBAccessThrowException, DBExecuteReturnException {
        return monthTicketCardSellOrderInfoRepo.queryOrderByOrderNo(orderNo);
    }

    public Integer updateOrderWithExpressInfo(Long orderNo, LogisticsCompanyInfo logisticsCompanyInfo) {

        String companyCode = logisticsCompanyInfo.getCompanyCode();
        String expressNo = logisticsCompanyInfo.getExpressNo();

        return monthTicketCardSellOrderInfoRepo.updateExpressInfoByOrderNo(orderNo, expressNo, companyCode);
    }

    /**
     * @see TicketOrderService#pageQueryOrderInfoByCondition(MonthTicketOrderQuery)
     */
    @Override
    public List<MonthTicketSellOrderInfo> pageQueryOrderInfoByCondition(MonthTicketOrderQuery query) {
        if (Objects.isNull(query)) {
            return Collections.emptyList();
        }

        String customizeNo = query.getCustomizeNo();
        Long orderNo = query.getOrderNo();
        String payOrderNo = query.getPayOrderNo();
        Long guid = query.getGuid();

        long total = monthTicketCardSellOrderInfoRepo.countOrderByCondition(customizeNo, orderNo, payOrderNo, guid);

        if (total <= 0) {
            return Collections.emptyList();
        }

        int page = query.getPage();
        int pageSize = query.getPageSize();

        int offset = (page - 1) * pageSize;
        int limit = pageSize;

        List<MonthTicketCardSellOrderInfoEntity> orders = monthTicketCardSellOrderInfoRepo
                .pageQueryOrderByCondition(customizeNo, orderNo, payOrderNo, guid, offset, limit);

        if (CollectionUtils.isEmpty(orders)) {
            return Collections.emptyList();
        }

        return orders.stream()
                .filter(Objects::nonNull)
                .map(OrderInfoConverter::convert2OrderDTO)
                .collect(Collectors.toList());
    }

}