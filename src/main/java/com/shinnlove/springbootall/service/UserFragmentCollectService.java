/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;

import com.shinnlove.springbootall.db.po.UserFragmentCollectAggEntity;
import com.shinnlove.springbootall.db.po.UserFragmentCollectEntity;

import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: UserFragmentCollectService.java, v 0.1 2024-04-23 10:27 Tony Zhao Exp $$
 */
public interface UserFragmentCollectService {

    long insertSelective();

    List<UserFragmentCollectEntity> queryUnUsedFragments();

    List<UserFragmentCollectEntity> queryUnUsedFragments(String activityId, Long componentId, Long guid);

    List<UserFragmentCollectAggEntity> queryUserCanCompoundCount();

    Integer updateFragmentStatusById(List<Long> usedIds);

}
