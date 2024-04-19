/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.dao.UserItemRankingRepo;
import com.shinnlove.springbootall.db.po.UserItemRankingEntity;
import com.shinnlove.springbootall.service.UserItemRankingService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author Tony Zhao
 * @version $Id: UserItemRankingServiceImpl.java, v 0.1 2024-04-19 10:28 Tony Zhao Exp $$
 */
@Service
public class UserItemRankingServiceImpl implements UserItemRankingService {

    private static final String TABLE_NAME = "user_item_ranking";

    private static final String ACTIVITY_ID = "123456";

    private static final Long GUID = 888888L;

    private static final Long NEW_GUID = 67453532534254L;

    @Resource
    private UserItemRankingRepo userItemRankingRepo;

    @Override
    public long insertUserItemRanking() {
        UserItemRankingEntity entity = new UserItemRankingEntity();
        entity.setActivityId(ACTIVITY_ID);
        entity.setGuid(NEW_GUID);
        entity.setItemTypeCount(1);

        long result = userItemRankingRepo.insertUserItemRanking(TABLE_NAME, entity);

        if (result > 0) {
            return entity.getId();
        }

        return result;
    }

    @Override
    public List<UserItemRankingEntity> selectTopRanking() {
        List<UserItemRankingEntity> pos = userItemRankingRepo.selectTopRanking(TABLE_NAME, ACTIVITY_ID);

        if (CollectionUtils.isEmpty(pos)) {
            return Collections.emptyList();
        }

        return pos;
    }

    @Override
    public UserItemRankingEntity queryByActivityGuid(Long guid) {
        UserItemRankingEntity entity = userItemRankingRepo.queryByActivityGuid(TABLE_NAME, ACTIVITY_ID, guid);
        return Objects.nonNull(entity) ? entity : null;
    }

    @Override
    public Integer incItemTypeCount() {
        return userItemRankingRepo.incItemTypeCount(TABLE_NAME, ACTIVITY_ID, GUID);
    }

    @Override
    public Integer updateAndCollectAllItem() {
        Integer totalItemTypeCount = 15;
        long timestamp = System.currentTimeMillis();

        return userItemRankingRepo.updateAndCollectAllItem(TABLE_NAME, ACTIVITY_ID, GUID, totalItemTypeCount, timestamp);
    }

}