/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.controller;

import com.shinnlove.springbootall.db.po.AssistRecordEntity;
import com.shinnlove.springbootall.db.po.AssistStatisticsEntity;
import com.shinnlove.springbootall.db.po.SumAssistRecord;
import com.shinnlove.springbootall.service.AssistRecordService;
import com.shinnlove.springbootall.service.AssistStatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: ShareController.java, v 0.1 2024-08-19 17:28 Tony Zhao Exp $$
 */
@RestController
@RequestMapping(value = "/share_dao")
public class ShareController {

    private static Logger logger = LoggerFactory.getLogger(ShareController.class);

    @Autowired
    private AssistRecordService assistRecordService;

    @Autowired
    private AssistStatisticsService assistStatisticsService;

    @RequestMapping(value = "/effective_records", method = RequestMethod.GET)
    public SumAssistRecord queryUserMaxBandsWithTime() {
        return assistRecordService.sumEffectiveAssistRecordByGuid();
    }

    @RequestMapping(value = "/daily_invite", method = RequestMethod.GET)
    public long countTodayInviteRecords() {
        return assistRecordService.countTodayInviteRecords();
    }

    @RequestMapping(value = "/count_invite", method = RequestMethod.GET)
    public long countInviteRecords() {
        return assistRecordService.countInviteRecords();
    }

    @RequestMapping(value = "/page_invite_records", method = RequestMethod.GET)
    public List<AssistRecordEntity> pageQueryInviteRecords(int page, int size) {
        return assistRecordService.pageQueryInviteRecords(page, size);
    }

    @RequestMapping(value = "/latest_record", method = RequestMethod.GET)
    public AssistRecordEntity latestAssistRecord() {
        return assistRecordService.latestAssistRecord();
    }

    @RequestMapping(value = "/guid_statistics", method = RequestMethod.GET)
    public AssistStatisticsEntity getStatisticsByInviteGuid() {
        return assistStatisticsService.getStatisticsByInviteGuid();
    }

    @RequestMapping(value = "/update_invite", method = RequestMethod.GET)
    public int updateInviteCount() {
        return assistStatisticsService.updateInviteCount();
    }

    @RequestMapping(value = "/update_point", method = RequestMethod.GET)
    public int updatePointCount(int point) {
        return assistStatisticsService.updatePointCount(point);
    }

}