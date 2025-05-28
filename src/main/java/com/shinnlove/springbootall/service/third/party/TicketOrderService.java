/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.third.party;

import com.shinnlove.springbootall.db.po.MonthTicketCardSellOrderInfoEntity;
import com.shinnlove.springbootall.exceptions.DBAccessThrowException;
import com.shinnlove.springbootall.exceptions.DBExecuteReturnException;
import com.shinnlove.springbootall.models.MonthTicketOrderQuery;
import com.shinnlove.springbootall.models.MonthTicketSellOrderInfo;
import com.shinnlove.springbootall.util.third.party.dto.LogisticsCompanyInfo;

import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: TicketOrderService.java, v 0.1 2025-03-07 10:44 Tony Zhao Exp $$
 */
public interface TicketOrderService {

    /**
     * @param orderNo
     * @return
     * @throws DBAccessThrowException
     * @throws DBExecuteReturnException
     */
    MonthTicketCardSellOrderInfoEntity queryOrderInfoByOrderNo(Long orderNo) throws DBAccessThrowException, DBExecuteReturnException;

    /**
     * Step1: 更新记录增加id
     *
     * @param uniqueSensitiveId
     * @param orderNo
     * @return
     */
    Integer updateUniqueSensitiveId(String uniqueSensitiveId, Long orderNo);

    /**
     * 更新订单的物流信息。
     *
     * @param orderNo
     * @param logisticsCompanyInfo
     * @return
     */
    Integer updateOrderWithExpressInfo(Long orderNo, LogisticsCompanyInfo logisticsCompanyInfo);

    /**
     * Query order info by pages with diverse conditions.
     *
     * @param query
     * @return
     */
    List<MonthTicketSellOrderInfo> pageQueryOrderInfoByCondition(MonthTicketOrderQuery query);

}
