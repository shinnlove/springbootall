/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.dao.UserLimitedItemExchangeRepo;
import com.shinnlove.springbootall.db.po.UserLimitedItemExchangeEntity;
import com.shinnlove.springbootall.service.UserLimitedItemExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tony Zhao
 * @version $Id: UserLimitedItemExchangeServiceImpl.java, v 0.1 2024-05-14 19:59 Tony Zhao Exp $$
 */
@Service
public class UserLimitedItemExchangeServiceImpl implements UserLimitedItemExchangeService {

    private static final String EXCHANGE_TABLE_NAME = "user_limited_item_exchange";

    private static final String ACTIVITY_ID = "3124365647";

    private static final Long COMPONENT_ID = 123456L;

    private static final Long GUID = 888888L;

    @Autowired
    private UserLimitedItemExchangeRepo userLimitedItemExchangeRepo;

    @Override
    public long insertSelective(int limitedType) {
        // assemble
        UserLimitedItemExchangeEntity entity = new UserLimitedItemExchangeEntity();
        entity.setActivityId(ACTIVITY_ID);
        entity.setComponentId(COMPONENT_ID);
        entity.setGuid(GUID);
        entity.setExchangeItemType(limitedType);

        return userLimitedItemExchangeRepo.insertSelective(EXCHANGE_TABLE_NAME, entity);
    }

}