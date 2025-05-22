/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;

import com.shinnlove.springbootall.db.po.UserDailyStatEntity;

/**
 * @author Tony Zhao
 * @version $Id: DailySilverCheckInLogService.java, v 0.1 2024-12-03 20:30 Tony Zhao Exp $$
 */
public interface DailySilverCheckInLogService {

    long insertSelective();

    Integer updateCheckInPrizeIds(long id);

}
