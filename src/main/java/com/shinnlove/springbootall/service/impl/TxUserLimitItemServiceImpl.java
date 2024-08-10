/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.dao.UserLimitedItemExchangeRepo;
import com.shinnlove.springbootall.db.dao.UserLimitedItemStatRepo;
import com.shinnlove.springbootall.service.TxUserLimitItemService;
import com.shinnlove.springbootall.service.UserLimitedItemExchangeService;
import com.shinnlove.springbootall.service.UserLimitedItemStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author Tony Zhao
 * @version $Id: TxUserLimitItemServiceImpl.java, v 0.1 2024-05-14 20:16 Tony Zhao Exp $$
 */
@Service
public class TxUserLimitItemServiceImpl implements TxUserLimitItemService {

    @Autowired
    private UserLimitedItemStatService      userLimitedItemStatService;

    @Autowired
    private UserLimitedItemExchangeService  userLimitedItemExchangeService;

    @Autowired
    private TransactionTemplate             transactionTemplate;

    @Override
    public Integer useLimitedItem(final int type) {

        final int usedCount = 1;

        return transactionTemplate.execute(status -> {

            userLimitedItemStatService.updateStatUsedCount(type, usedCount);

            userLimitedItemExchangeService.insertSelective(type);

            return 1;
        });

    }

}