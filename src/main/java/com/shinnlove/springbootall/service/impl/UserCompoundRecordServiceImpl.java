/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.dao.UserCompoundRecordRepo;
import com.shinnlove.springbootall.db.po.UserCompoundRecordAggEntity;
import com.shinnlove.springbootall.db.po.UserCompoundRecordEntity;
import com.shinnlove.springbootall.service.UserCompoundRecordService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Tony Zhao
 * @version $Id: UserCompoundRecordServiceImpl.java, v 0.1 2024-04-23 10:53 Tony Zhao Exp $$
 */
@Service
public class UserCompoundRecordServiceImpl implements UserCompoundRecordService {

    private static final String COMPOUND_TABLE_NAME = "user_compound_record";

    private static final String ACTIVITY_ID = "3124365647";

    private static final Long COMPONENT_ID = 123456L;

    private static final Long GUID = 888888L;

    @Autowired
    private UserCompoundRecordRepo userCompoundRecordRepo;

    @Override
    public long insertSelective(List<Long> usedIds) {

        String defaultIds = "1,2,3,4";
        if (!CollectionUtils.isEmpty(usedIds)) {
            List<String> ids = usedIds.stream().map(String::valueOf).collect(Collectors.toList());
            defaultIds = String.join(",", ids);
        }

        // assemble
        UserCompoundRecordEntity entity = new UserCompoundRecordEntity();
        entity.setActivityId(ACTIVITY_ID);
        entity.setComponentId(COMPONENT_ID);
        entity.setGuid(GUID);
        entity.setUsedIds(defaultIds);

        return userCompoundRecordRepo.insertSelective(COMPOUND_TABLE_NAME, entity);
    }

    @Override
    public long insertUserCompoundRecord(String activityId, Long componentId, Long guid, List<Long> usedIds) {
        String defaultUsedIds = "";
        if (CollectionUtils.isNotEmpty(usedIds)) {
            List<String> ids = usedIds.stream().map(String::valueOf).collect(Collectors.toList());
            defaultUsedIds = String.join(",", ids);
        }

        UserCompoundRecordEntity entity = new UserCompoundRecordEntity();
        entity.setActivityId(activityId);
        entity.setComponentId(componentId);
        entity.setGuid(guid);
        entity.setUsedIds(defaultUsedIds);

        long result = 0L;

        try {
            result = userCompoundRecordRepo.insertSelective(COMPOUND_TABLE_NAME, entity);
        } catch(Exception e) {

        }

        if (result <= 0L) {
            throw new RuntimeException("insert error");
        }

        return result;
    }

    @Override
    public List<UserCompoundRecordAggEntity> queryUserCompoundCount() {

        List<UserCompoundRecordAggEntity> pos = userCompoundRecordRepo
                .queryUserCompoundCount(COMPOUND_TABLE_NAME, ACTIVITY_ID, COMPONENT_ID, GUID);

        if (CollectionUtils.isEmpty(pos)) {
            return Collections.emptyList();
        }

        return pos;
    }

}