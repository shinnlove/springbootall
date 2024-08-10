/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;

import com.shinnlove.springbootall.db.po.UserCompoundRecordAggEntity;

import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: UserCompoundRecordService.java, v 0.1 2024-04-23 10:53 Tony Zhao Exp $$
 */
public interface UserCompoundRecordService {

    long insertSelective(List<Long> usedIds);

    long insertUserCompoundRecord(String activityId, Long componentId, Long guid, List<Long> usedIds);

    List<UserCompoundRecordAggEntity> queryUserCompoundCount();

}
