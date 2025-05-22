/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.dao.DailySilverCheckInLogRepo;
import com.shinnlove.springbootall.db.po.DailySilverCheckInLogEntity;
import com.shinnlove.springbootall.exceptions.BusinessCode;
import com.shinnlove.springbootall.exceptions.DBAccessThrowException;
import com.shinnlove.springbootall.exceptions.DBExecuteReturnException;
import com.shinnlove.springbootall.service.DailySilverCheckInLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * @author Tony Zhao
 * @version $Id: DailySilverCheckInLogServiceImpl.java, v 0.1 2024-12-03 20:30 Tony Zhao Exp $$
 */
@Service
public class DailySilverCheckInLogServiceImpl implements DailySilverCheckInLogService {

    private static Logger logger = LoggerFactory.getLogger(DailySilverCheckInLogServiceImpl.class);

    private static final String activityId = "53214567";
    private static final Long componentId = 147621L;
    private static final Long guid = 666888L;
    private static final Integer dayNumber = 6;
    private static final Integer dateMark = 20240904;
    private static final String actionQueries = "#A2015,#A2016,#A2017";
    private static final String prizeIdList = "1,2,3,4,5,6,7,8,9,10";

    @Autowired
    private DailySilverCheckInLogRepo dailySilverCheckInLogRepo;

    @Override
    public long insertSelective() {

        long currentMillis = System.currentTimeMillis();

        DailySilverCheckInLogEntity entity = DailySilverCheckInLogEntity.builder()
                .activityId(activityId)
                .componentId(componentId)
                .guid(guid)
                .dateMark(dateMark)
                .dayNumber(dayNumber)
                // 插入的时候动作空
                .actionQueries("")
                // 插入的时候默认奖品也是空
                .prizeIdList("")
                .createTime(new Timestamp(currentMillis))
                .build();

        long result = 0L;

        try {
            result = dailySilverCheckInLogRepo.insertSelective(entity);
        } catch (Exception e) {
            logger.error("[DailySilverCheckInService] init daily check in access failed, ex=" + e.getMessage(), e);
            throw new DBAccessThrowException(BusinessCode.DB_ACCESS_THROW_ERROR, e);
        }

        if (result <= 0L) {
            logger.error("[DailySilverCheckInService] init daily check in execute failed.");
            throw new DBExecuteReturnException(BusinessCode.DB_EXECUTE_RETURN_ERROR);
        }

        return entity.getId();

    }

    @Override
    public Integer updateCheckInPrizeIds(long id) {
        int result = 0;

        try {
            result = dailySilverCheckInLogRepo.updateCheckInPrizeIdsById(id, actionQueries, prizeIdList);
        } catch (Exception e) {
            logger.error("[DailySilverCheckInService] update check in prizes access failed, ex=" + e.getMessage(), e);
            throw new DBAccessThrowException(BusinessCode.DB_ACCESS_THROW_ERROR, e);
        }

        if (result <= 0) {
            logger.error("[DailySilverCheckInService] update check in prizes execute failed.");
            throw new DBExecuteReturnException(BusinessCode.DB_EXECUTE_RETURN_ERROR);
        }

        return result;
    }

}