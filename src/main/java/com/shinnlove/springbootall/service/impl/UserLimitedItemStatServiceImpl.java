/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.dao.UserLimitedItemStatRepo;
import com.shinnlove.springbootall.db.po.UserLimitedItemStatEntity;
import com.shinnlove.springbootall.service.UserLimitedItemStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tony Zhao
 * @version $Id: UserLimitedItemStatServiceImpl.java, v 0.1 2024-05-14 19:01 Tony Zhao Exp $$
 */
@Service
public class UserLimitedItemStatServiceImpl implements UserLimitedItemStatService {

    private static final String STAT_TABLE_NAME = "user_limited_item_stat";

    private static final String ACTIVITY_ID = "3124365647";

    private static final Long COMPONENT_ID = 123456L;

    private static final Long GUID = 888888L;

    @Autowired
    private UserLimitedItemStatRepo userLimitedItemStatRepo;

    @Override
    public UserLimitedItemStatEntity queryByGuidAndLimitedType(int limitedType) {
        return userLimitedItemStatRepo
                .queryByGuidAndLimitedType(STAT_TABLE_NAME, ACTIVITY_ID, COMPONENT_ID, GUID, limitedType);
    }

    @Override
    public long insertSelective(int limitedType) {
        // assemble
        UserLimitedItemStatEntity entity = new UserLimitedItemStatEntity();
        entity.setActivityId(ACTIVITY_ID);
        entity.setComponentId(COMPONENT_ID);
        entity.setGuid(GUID);
        entity.setLimitedType(limitedType);
        // special warning: should assign this value before insert to avoid NPE when on-duplicate
        entity.setTotalCount(0);
        entity.setUsedCount(0);

        long result = userLimitedItemStatRepo.insertSelective(STAT_TABLE_NAME, entity);

        if (result <= 0) {
            return 0;
        }

        return result;
    }

    @Override
    public Integer updateStatTotalCount(int limitedType, int addCount) {
        return userLimitedItemStatRepo
                .updateStatTotalCount(STAT_TABLE_NAME, ACTIVITY_ID, COMPONENT_ID, GUID, limitedType, addCount);
    }

    @Override
    public Integer updateStatUsedCount(int limitedType, int usedCount) {
        return userLimitedItemStatRepo
                .updateStatUsedCount(STAT_TABLE_NAME, ACTIVITY_ID, COMPONENT_ID, GUID, limitedType, usedCount);
    }

}