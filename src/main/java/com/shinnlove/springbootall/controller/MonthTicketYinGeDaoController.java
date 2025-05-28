/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.controller;

import com.shinnlove.springbootall.service.third.party.TicketOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Tony Zhao
 * @version $Id: MonthTicketYinGeDaoController.java, v 0.1 2025-05-28 11:32 Tony Zhao Exp $$
 */
@RestController
@RequestMapping(value = "/month_ticket_dao")
public class MonthTicketYinGeDaoController {

    private static Logger logger = LoggerFactory.getLogger(MonthTicketYinGeDaoController.class);

    @Resource
    private TicketOrderService ticketOrderService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello() {
        return "Hello world.";
    }

    @RequestMapping(value = "/update_unique_id", method = RequestMethod.GET)
    public int updateUniqueSensitiveId(String uniqueSensitiveId, long orderNo) {
        return ticketOrderService.updateUniqueSensitiveId(uniqueSensitiveId, orderNo);
    }

}