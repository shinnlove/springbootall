/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;

import com.shinnlove.springbootall.db.po.UserPkDailyStatEntity;

/**
 * @author Tony Zhao
 * @version $Id: UserPkDailyStatService.java, v 0.1 2024-04-16 11:14 Tony Zhao Exp $$
 */
public interface UserPkDailyStatService {

    long insertUserDailyStat();

    UserPkDailyStatEntity queryUserTodayStat();

    Integer updateDailySuccess();

    Integer incDailyFailure();

    Integer incDailyDraw();

    Integer incDailyChance();

    Integer incDailyChanceByCount();

}
