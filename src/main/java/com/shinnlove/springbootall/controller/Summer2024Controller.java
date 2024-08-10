/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.controller;

import com.shinnlove.springbootall.db.po.ValidOrderMessageQueueLogEntity;
import com.shinnlove.springbootall.service.ValidOrderMessageQueueLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: Summer2024Controller.java, v 0.1 2024-07-30 09:50 Tony Zhao Exp $$
 */
@RestController
@RequestMapping(value = "/summer2024")
public class Summer2024Controller {

    @Autowired
    private ValidOrderMessageQueueLogService validOrderMessageQueueLogService;

    @RequestMapping(value = "/say_hello")
    public String sayHello() {
        return "Hello, Summer 2024!";
    }

    @RequestMapping(value = "/save_order_message", method = RequestMethod.GET)
    public long saveOrderMessage() {
        return validOrderMessageQueueLogService.saveOrderMessage();
    }

    @RequestMapping(value = "/query_message_by_guid", method = RequestMethod.GET)
    public List<ValidOrderMessageQueueLogEntity> queryMessageByGuid() {
        return validOrderMessageQueueLogService.queryMessageByGuid();
    }

    @RequestMapping(value = "/query_message_by_order_id", method = RequestMethod.GET)
    public ValidOrderMessageQueueLogEntity queryMessageByOrderId() {
        return validOrderMessageQueueLogService.queryMessageByOrderId();
    }

    @RequestMapping(value = "/update_message_log_status", method = RequestMethod.GET)
    public int updateMessageLogStatus() {
        return validOrderMessageQueueLogService.updateMessageLogStatus(1);
    }

}