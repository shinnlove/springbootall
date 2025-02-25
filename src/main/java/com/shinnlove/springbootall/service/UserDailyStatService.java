/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;

import com.shinnlove.springbootall.db.po.UserDailyStatEntity;
import org.apache.ibatis.annotations.Param;

/**
 * @author Tony Zhao
 * @version $Id: UserDailyStatService.java, v 0.1 2024-09-04 17:16 Tony Zhao Exp $$
 */
public interface UserDailyStatService {

    long insertSelective();

    UserDailyStatEntity queryUserDailyStat();

    Integer updateUserDailyStat();

}
