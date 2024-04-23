/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.dao.UserFragmentCollectRepo;
import com.shinnlove.springbootall.db.po.UserFragmentCollectAggEntity;
import com.shinnlove.springbootall.db.po.UserFragmentCollectEntity;
import com.shinnlove.springbootall.service.UserFragmentCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author Tony Zhao
 * @version $Id: UserFragmentCollectServiceImpl.java, v 0.1 2024-04-23 10:27 Tony Zhao Exp $$
 */
@Service
public class UserFragmentCollectServiceImpl implements UserFragmentCollectService {

    private static final String COLLECT_TABLE_NAME = "user_fragment_collect";

    private static final String ACTIVITY_ID = "3124365647";

    private static final Long COMPONENT_ID = 123456L;

    private static final Long GUID = 888888L;

    @Autowired
    private UserFragmentCollectRepo userFragmentCollectRepo;

    @Override
    public long insertSelective() {
        // random
        Random r = new Random();
        int temp = r.nextInt(4);

        // assemble
        UserFragmentCollectEntity entity = new UserFragmentCollectEntity();

        entity.setActivityId(ACTIVITY_ID);
        entity.setComponentId(COMPONENT_ID);
        entity.setGuid(GUID);
        entity.setFragmentId(temp + 1);

        return userFragmentCollectRepo.insertSelective(COLLECT_TABLE_NAME, entity);
    }

    @Override
    public List<UserFragmentCollectEntity> queryUnUsedFragments() {
        // query
        List<UserFragmentCollectEntity> pos = userFragmentCollectRepo
                .queryUnUsedFragments(COLLECT_TABLE_NAME, ACTIVITY_ID, COMPONENT_ID, GUID);

        if (CollectionUtils.isEmpty(pos)) {
            return Collections.emptyList();
        }

        return pos;
    }

    @Override
    public List<UserFragmentCollectAggEntity> queryUserCanCompoundCount() {
        // group by query
        List<UserFragmentCollectAggEntity> pos = userFragmentCollectRepo
                .queryUserCanCompoundCount(COLLECT_TABLE_NAME, ACTIVITY_ID, COMPONENT_ID, GUID);

        if (CollectionUtils.isEmpty(pos)) {
            return Collections.emptyList();
        }

        return pos;
    }

    @Override
    public Integer updateFragmentStatusById(List<Long> ids) {
        return userFragmentCollectRepo.updateFragmentStatusById(COLLECT_TABLE_NAME, ids);
    }

}