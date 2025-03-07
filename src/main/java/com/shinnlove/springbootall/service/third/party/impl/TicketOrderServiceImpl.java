/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.third.party.impl;

import com.shinnlove.springbootall.db.dao.MonthTicketCardSellOrderInfoRepo;
import com.shinnlove.springbootall.db.po.MonthTicketCardSellOrderInfoEntity;
import com.shinnlove.springbootall.exceptions.DBAccessThrowException;
import com.shinnlove.springbootall.exceptions.DBExecuteReturnException;
import com.shinnlove.springbootall.service.third.party.TicketOrderService;
import com.shinnlove.springbootall.util.third.party.dto.LogisticsCompanyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}