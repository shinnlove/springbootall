/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.dao.UserPkRecordRepo;
import com.shinnlove.springbootall.db.po.UserPkRecordEntity;
import com.shinnlove.springbootall.service.UserPkRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: UserPkRecordServiceImpl.java, v 0.1 2024-04-16 11:13 Tony Zhao Exp $$
 */
@Service
public class UserPkRecordServiceImpl implements UserPkRecordService {

    @Autowired
    private UserPkRecordRepo userPkRecordRepo;

    @Override
    public UserPkRecordEntity queryRecordByGuidAndTime() {

        Long guid = 123456L;
        String activityId = "test_activity_1";

        Timestamp start = new Timestamp(System.currentTimeMillis());
        Timestamp end = new Timestamp(System.currentTimeMillis() + 3600000L);

        String pkRecordTableName = "user_pk_record";
        List<UserPkRecordEntity> pos = userPkRecordRepo
                .queryRecordByGuidAndTime(pkRecordTableName, activityId, guid, start, end);

        if (CollectionUtils.isEmpty(pos)) {
            return null;
        }

        return pos.get(0);
    }

}