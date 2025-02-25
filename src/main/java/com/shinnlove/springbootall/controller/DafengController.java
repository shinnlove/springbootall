/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.controller;

import com.shinnlove.springbootall.db.po.PassportCashbackLogEntity;
import com.shinnlove.springbootall.service.DafengMemberMsgQueueLogService;
import com.shinnlove.springbootall.service.DailySilverCheckInLogService;
import com.shinnlove.springbootall.service.PassportCashbackLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: DafengController.java, v 0.1 2024-12-03 20:13 Tony Zhao Exp $$
 */
@RestController
@RequestMapping(value = "/df_dao")
public class DafengController {

    private static Logger logger = LoggerFactory.getLogger(DafengController.class);

    @Autowired
    private DailySilverCheckInLogService    dailySilverCheckInLogService;

    @Autowired
    private DafengMemberMsgQueueLogService  dafengMemberMsgQueueLogService;

    @Autowired
    private PassportCashbackLogService      passportCashbackLogService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello() {
        return "Hello dafeng.";
    }

    @RequestMapping(value = "/init_check_in", method = RequestMethod.GET)
    public long initCheckIn() {
        return dailySilverCheckInLogService.insertSelective();
    }

    @RequestMapping(value = "/update_prize_ids", method = RequestMethod.GET)
    public int updateCheckInPrizeIds(long id) {
        return dailySilverCheckInLogService.updateCheckInPrizeIds(id);
    }

    @RequestMapping(value = "/insert_message_log", method = RequestMethod.GET)
    public long insertMessageLog() {
        return dafengMemberMsgQueueLogService.insertMessageLog();
    }

    @RequestMapping(value = "/insert_cashback_log", method = RequestMethod.GET)
    public long insertCashbackLog(int type, int gear, int hasSent) {
        return passportCashbackLogService.insertCashbackLog(type, gear, hasSent);
    }

    @RequestMapping(value = "/query_cashback_log", method = RequestMethod.GET)
    public List<PassportCashbackLogEntity> queryPassportCashbackLogs(long guid) {
        return passportCashbackLogService.queryPassportCashbackLogs(guid);
    }

}