/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.dao.UserItemRankingRepo;
import com.shinnlove.springbootall.db.po.UserItemRankingEntity;
import com.shinnlove.springbootall.models.PageResult;
import com.shinnlove.springbootall.service.UserItemRankingService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    public PageResult<UserItemRankingEntity> pageQueryTopRanking(Integer pageIndex, Integer pageSize) {

        List<Long> blackGuids = new ArrayList<>();
//        blackGuids.add(647358237L);
//        blackGuids.add(6654238223L);
//        blackGuids.add(888888L);

        // how many
        long total = userItemRankingRepo.countTopRanking(TABLE_NAME, ACTIVITY_ID, blackGuids);
        if (total <= 0) {
            return PageResult.EMPTY_PAGE_RESULT;
        }

        // limit query
        int offset = (pageIndex - 1) * pageSize;
        int limit = pageSize;
        List<UserItemRankingEntity> pos = userItemRankingRepo.queryTopRanking(TABLE_NAME, ACTIVITY_ID, blackGuids, offset, limit);

        if (CollectionUtils.isEmpty(pos)) {
            return PageResult.EMPTY_PAGE_RESULT;
        }

        return new PageResult<>(total, pos);
    }

    @Override
    public long countCollectAllUser(Integer totalTypes) {
        List<Long> blackGuids = new ArrayList<>();
        blackGuids.add(647358237L);
//        blackGuids.add(6654238223L);
//        blackGuids.add(888888L);

        return userItemRankingRepo.countCollectAllUser(TABLE_NAME, ACTIVITY_ID, totalTypes, blackGuids);
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

}