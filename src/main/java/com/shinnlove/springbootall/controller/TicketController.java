/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.controller;

import com.shinnlove.springbootall.models.MonthTicketOrderQuery;
import com.shinnlove.springbootall.models.MonthTicketSellOrderInfo;
import com.shinnlove.springbootall.service.third.party.TicketOrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: TicketController.java, v 0.1 2025-03-13 14:31 Tony Zhao Exp $$
 */
@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Resource
    private TicketOrderService ticketOrderService;

    @PostMapping("/page_query_order")
    public List<MonthTicketSellOrderInfo> pageQueryOrderInfoByCondition(@RequestBody MonthTicketOrderQuery query) {
        return ticketOrderService.pageQueryOrderInfoByCondition(query);
    }

}