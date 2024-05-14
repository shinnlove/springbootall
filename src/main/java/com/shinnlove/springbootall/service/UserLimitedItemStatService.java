/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;

import com.shinnlove.springbootall.db.po.UserLimitedItemStatEntity;

/**
 * @author Tony Zhao
 * @version $Id: UserLimitedItemStatService.java, v 0.1 2024-05-14 19:01 Tony Zhao Exp $$
 */
public interface UserLimitedItemStatService {

    UserLimitedItemStatEntity queryByGuidAndLimitedType(int limitedType);

    long insertSelective(int limitedType);

    Integer updateStatTotalCount(int limitedType, int addCount);

    Integer updateStatUsedCount(int limitedType, int usedCount);

}
