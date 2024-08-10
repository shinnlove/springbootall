/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.dao.UserBandLogExtRepo;
import com.shinnlove.springbootall.db.po.UserBandLogExtEntity;
import com.shinnlove.springbootall.service.UserBandLogService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: UserBandLogServiceImpl.java, v 0.1 2024-06-04 20:15 Tony Zhao Exp $$
 */
@Service
public class UserBandLogServiceImpl implements UserBandLogService {

    private static final String BAND_TABLE_NAME = "user_band_log";

    private static final String ACTIVITY_ID = "934367089";

    private static final Long COMPONENT_ID = 123456L;

    private static final Long GUID = 888888L;

    @Autowired
    private UserBandLogExtRepo userBandLogExtRepo;

    @Override
    public List<UserBandLogExtEntity> queryUserMaxBandsWithTime() {

        List<Long> guids = new ArrayList<>();
        guids.add(900005457960L);
        guids.add(120131853650L);
        guids.add(117302714020L);
        guids.add(854027213200L);
        guids.add(120131813350L);
        guids.add(120010491330L);

        List<UserBandLogExtEntity> pos = userBandLogExtRepo
                .queryUserMaxBandsWithTime(BAND_TABLE_NAME, ACTIVITY_ID, guids);

        if (CollectionUtils.isEmpty(pos)) {
            return Collections.emptyList();
        }

        return pos;
    }

}