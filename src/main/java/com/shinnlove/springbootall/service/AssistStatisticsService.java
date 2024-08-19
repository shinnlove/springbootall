/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;

import com.shinnlove.springbootall.db.po.AssistStatisticsEntity;

/**
 * @author Tony Zhao
 * @version $Id: AssistStatisticsService.java, v 0.1 2024-08-19 17:51 Tony Zhao Exp $$
 */
public interface AssistStatisticsService {

    AssistStatisticsEntity getStatisticsByInviteGuid();

    @Deprecated
    long createStatisticsEntity();

    int updateInviteCount();

    int updatePointCount(long points);

}
