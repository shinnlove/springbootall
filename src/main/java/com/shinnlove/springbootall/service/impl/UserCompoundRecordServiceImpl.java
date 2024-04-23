/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.dao.UserCompoundRecordRepo;
import com.shinnlove.springbootall.db.po.UserCompoundRecordAggEntity;
import com.shinnlove.springbootall.db.po.UserCompoundRecordEntity;
import com.shinnlove.springbootall.service.UserCompoundRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: UserCompoundRecordServiceImpl.java, v 0.1 2024-04-23 10:53 Tony Zhao Exp $$
 */
@Service
public class UserCompoundRecordServiceImpl implements UserCompoundRecordService {

    private static final String COLLECT_TABLE_NAME = "user_fragment_collect";

    private static final String ACTIVITY_ID = "3124365647";

    private static final Long COMPONENT_ID = 123456L;

    private static final Long GUID = 888888L;

    @Autowired
    private UserCompoundRecordRepo userCompoundRecordRepo;

    @Override
    public long insertSelective() {
        // assemble
        UserCompoundRecordEntity entity = new UserCompoundRecordEntity();

        return userCompoundRecordRepo.insertSelective(COLLECT_TABLE_NAME, entity);
    }

    @Override
    public List<UserCompoundRecordAggEntity> queryUserCompoundCount() {

        List<UserCompoundRecordAggEntity> pos = userCompoundRecordRepo
                .queryUserCompoundCount(COLLECT_TABLE_NAME, ACTIVITY_ID, COMPONENT_ID, GUID);

        if (CollectionUtils.isEmpty(pos)) {
            return Collections.emptyList();
        }

        return pos;
    }

}