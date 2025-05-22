/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.dao.AssistStatisticsRepo;
import com.shinnlove.springbootall.db.po.AssistStatisticsEntity;
import com.shinnlove.springbootall.service.AssistStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tony Zhao
 * @version $Id: AssistStatisticsServiceImpl.java, v 0.1 2024-08-19 17:51 Tony Zhao Exp $$
 */
@Service
public class AssistStatisticsServiceImpl implements AssistStatisticsService {

    private static final Long guid = 888888L;

    @Autowired
    private AssistStatisticsRepo assistStatisticsRepo;

    public AssistStatisticsEntity getStatisticsByInviteGuid(long guid) {
        return assistStatisticsRepo.getStatisticsByInviteGuid(guid);
    }

    @Deprecated
    public long createStatisticsEntity() {
        assistStatisticsRepo.createStatisticsEntity(guid);

        return 1L;
    }

    public int updateInviteCount() {
        return assistStatisticsRepo.updateInviteCount(guid);
    }

    public int updatePointCount(long points) {
        return assistStatisticsRepo.updatePointCount(guid, 1L);
    }

}