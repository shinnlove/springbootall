/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.controller;

import com.shinnlove.springbootall.db.po.UserPkDailyStatEntity;
import com.shinnlove.springbootall.db.po.UserPkRecordEntity;
import com.shinnlove.springbootall.service.UserPkDailyStatService;
import com.shinnlove.springbootall.service.UserPkGlobalStatService;
import com.shinnlove.springbootall.service.UserPkRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author Tony Zhao
 * @version $Id: DaoController.java, v 0.1 2024-04-16 11:02 Tony Zhao Exp $$
 */
@RestController
@RequestMapping(value = "/dao")
public class DaoController {

    private static Logger logger = LoggerFactory.getLogger(DaoController.class);

    @Autowired
    private UserPkGlobalStatService userPkGlobalStatService;

    @Autowired
    private UserPkDailyStatService userPkDailyStatService;

    @Autowired
    private UserPkRecordService userPkRecordService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello() {
        return "Hello world.";
    }

    @RequestMapping(value = "/insert_global", method = RequestMethod.GET)
    public long insertUserGlobalStat() {
        return userPkDailyStatService.insertUserDailyStat();
    }

    @RequestMapping(value = "/insert_daily", method = RequestMethod.GET)
    public long insertUserDailyStat() {
        return userPkDailyStatService.insertUserDailyStat();
    }

    @RequestMapping(value = "/query_daily", method = RequestMethod.GET)
    public String queryUserTodayStat() {
        UserPkDailyStatEntity entity = userPkDailyStatService.queryUserTodayStat();
        return Objects.nonNull(entity) ? entity.toString() : "没有查询到daily信息";
    }

    @RequestMapping(value = "/update_daily_success", method = RequestMethod.GET)
    public int updateDailySuccess() {
        return userPkDailyStatService.updateDailySuccess();
    }

    @RequestMapping(value = "/inc_daily_failure", method = RequestMethod.GET)
    public int incDailyFailure() {
        return userPkDailyStatService.incDailyFailure();
    }

    @RequestMapping(value = "/inc_daily_draw", method = RequestMethod.GET)
    public int incDailyDraw() {
        return userPkDailyStatService.incDailyDraw();
    }

    @RequestMapping(value = "/inc_daily_chance", method = RequestMethod.GET)
    public int incDailyChance() {
        return userPkDailyStatService.incDailyChance();
    }

    @RequestMapping(value = "/insert_pk_record", method = RequestMethod.GET)
    public long insertPkRecord() {
        return userPkRecordService.save();
    }

    @RequestMapping(value = "/query_record_by_time", method = RequestMethod.GET)
    public String queryRecordByGuidAndTime() {
        UserPkRecordEntity entity = userPkRecordService.queryRecordByGuidAndTime();
        return Objects.nonNull(entity) ? entity.toString() : "没有查询到record信息";
    }

}