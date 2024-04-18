/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.dao.UserPkDailyStatRepo;
import com.shinnlove.springbootall.db.po.UserPkDailyStatEntity;
import com.shinnlove.springbootall.service.UserPkDailyStatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: UserPkDailyStatServiceImpl.java, v 0.1 2024-04-16 11:14 Tony Zhao Exp $$
 */
@Service
public class UserPkDailyStatServiceImpl implements UserPkDailyStatService {

    private static final Logger logger = LoggerFactory.getLogger(UserPkDailyStatServiceImpl.class);

    private static final String activityId = "test_activity_1";
    private static final String pkDailyTableName = "user_pk_daily_stat";

    @Autowired
    private UserPkDailyStatRepo userPkDailyStatRepo;

    @Override
    public long insertUserDailyStat() {

        Long guid = 123456L;

        UserPkDailyStatEntity entity = new UserPkDailyStatEntity();
        entity.setActivityId(activityId);
        entity.setGuid(guid);
        entity.setChanceCount(1);
        entity.setSuccessCount(0);
        entity.setFailureCount(0);
        entity.setStatDate(20240416);

        long result = 0L;

        try {
            result = userPkDailyStatRepo.insertUserDailyStat(pkDailyTableName, entity);
        } catch(Exception e) {
            logger.error("pk daily insert error, ex=" + e.getMessage(), e);
        }

        return result;
    }

    @Override
    public UserPkDailyStatEntity queryUserTodayStat() {

        Long guid = 123456L;

        int date = 20240416;

        List<UserPkDailyStatEntity> pos = userPkDailyStatRepo
                .queryUserDailyPk(pkDailyTableName, activityId, guid, date);

        if (CollectionUtils.isEmpty(pos)) {
            return null;
        }

        return pos.get(0);
    }

    @Override
    public Integer updateDailySuccess() {
        Long guid = 123456L;
        return userPkDailyStatRepo.updateDailySuccess(pkDailyTableName, activityId, guid);
    }

    @Override
    public Integer incDailyFailure() {
        Long guid = 123456L;
        return userPkDailyStatRepo.incDailyFailure(pkDailyTableName, activityId, guid);
    }

    @Override
    public Integer incDailyDraw() {
        Long guid = 123456L;
        return userPkDailyStatRepo.incDailyDraw(pkDailyTableName, activityId, guid);
    }

    @Override
    public Integer incDailyChance() {
        Long guid = 123456L;
        return userPkDailyStatRepo.incDailyChance(pkDailyTableName, activityId, guid);
    }

    @Override
    public Integer incDailyChanceByCount() {
        Long guid = 123456L;
        Integer count = 17;
        return userPkDailyStatRepo.incDailyChanceByCount(pkDailyTableName, activityId, guid, count);
    }

}