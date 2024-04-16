/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.dao.UserPkGlobalStatRepo;
import com.shinnlove.springbootall.db.po.UserPkGlobalStatEntity;
import com.shinnlove.springbootall.service.UserPkGlobalStatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: UserPkGlobalStatServiceImpl.java, v 0.1 2024-04-16 11:21 Tony Zhao Exp $$
 */
@Service
public class UserPkGlobalStatServiceImpl implements UserPkGlobalStatService {

    private static final Logger logger = LoggerFactory.getLogger(UserPkGlobalStatServiceImpl.class);

    private static final String activityId = "test_activity_1";
    private static final String pkGlobalTableName = "user_pk_global_stat";

    @Autowired
    private UserPkGlobalStatRepo userPkGlobalStatRepo;

    @Override
    public long insertUserGlobalStat() {

        Long guid = 123456L;

        UserPkGlobalStatEntity entity = new UserPkGlobalStatEntity();
        entity.setActivityId(activityId);
        entity.setGuid(guid);
        entity.setTotalSuccessCount(20);
        entity.setTotalFailureCount(30);
        entity.setTotalDrawCount(40);
        entity.setPromotionLevel(3);

        long result = 0L;

        try {
            result = userPkGlobalStatRepo.insertUserGlobalStat(pkGlobalTableName, entity);
        } catch(Exception e) {
            logger.error("pk global insert error, ex=" + e.getMessage(), e);
        }

        return result;
    }

    @Override
    public UserPkGlobalStatEntity queryUserGlobalStat() {
        Long guid = 123456L;
        List<UserPkGlobalStatEntity> entities = userPkGlobalStatRepo
                .queryUserGlobalStat(pkGlobalTableName, activityId, guid);

        if (CollectionUtils.isEmpty(entities)) {
            return null;
        }

        return entities.get(0);
    }

    @Override
    public Integer updateGlobalSuccess() {
        Long guid = 123456L;
        return userPkGlobalStatRepo.updateGlobalSuccess(pkGlobalTableName, activityId, guid);
    }

    @Override
    public Integer updateGlobalSuccessWithPromotionLevel() {
        Long guid = 123456L;
        return userPkGlobalStatRepo.updateGlobalSuccessWithPromotionLevel(pkGlobalTableName, activityId, guid);
    }

    @Override
    public Integer updateGlobalFailure() {
        Long guid = 123456L;
        return userPkGlobalStatRepo.updateGlobalFailure(pkGlobalTableName, activityId, guid);
    }

    @Override
    public Integer updateGlobalDraw() {
        Long guid = 123456L;
        return userPkGlobalStatRepo.updateGlobalDraw(pkGlobalTableName, activityId, guid);
    }

}