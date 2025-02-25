/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.dao.UserDailyStatRepo;
import com.shinnlove.springbootall.db.po.UserDailyStatEntity;
import com.shinnlove.springbootall.service.UserDailyStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author Tony Zhao
 * @version $Id: UserDailyStatServiceImpl.java, v 0.1 2024-09-04 17:17 Tony Zhao Exp $$
 */
@Service
public class UserDailyStatServiceImpl implements UserDailyStatService {

    private static final String activityId = "53214567";
    private static final Long componentId = 147621L;
    private static final Long guid = 666888L;
    private static final Integer dateMark = 20240904;

    @Autowired
    private UserDailyStatRepo userDailyStatRepo;

    @Override
    public long insertSelective() {

        UserDailyStatEntity entity = new UserDailyStatEntity();

        long insertResult = userDailyStatRepo.insertSelective(entity);

        return 0;
    }

    @Override
    public UserDailyStatEntity queryUserDailyStat() {

        UserDailyStatEntity entity = userDailyStatRepo.queryUserDailyStat(activityId, componentId, guid, dateMark);

        return Objects.isNull(entity) ? null : entity;
    }

    @Override
    public Integer updateUserDailyStat() {

        int result = userDailyStatRepo.updateUserDailyStat(activityId, componentId, guid, dateMark);

        return result;
    }
}