/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.dao.UserItemRecordExtRepo;
import com.shinnlove.springbootall.db.po.ItemRecordEntity;
import com.shinnlove.springbootall.service.ItemRecordService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: ItemRecordServiceImpl.java, v 0.1 2024-06-03 16:28 Tony Zhao Exp $$
 */
@Service
public class ItemRecordServiceImpl implements ItemRecordService {

    private static final String RECORD_TABLE_NAME = "user_item_record";

    private static final String ACTIVITY_ID = "3124365647";

    private static final Long COMPONENT_ID = 123456L;

    private static final Long GUID = 888888L;

    private static final int MESSAGE_SENT_VERSION = 1;

    private static final int PAGE_SIZE = 10;

    @Autowired
    private UserItemRecordExtRepo userItemRecordExtRepo;

    @Override
    public List<ItemRecordEntity> cursorQueryItemRecord(int messageSentVersion) {

        long lastRecordId = 2L;

        List<ItemRecordEntity> records = userItemRecordExtRepo
                .cursorQueryItemRecord(RECORD_TABLE_NAME, ACTIVITY_ID, messageSentVersion, lastRecordId, PAGE_SIZE);

        if (CollectionUtils.isEmpty(records)) {
            return Collections.emptyList();
        }

        return records;
    }

    @Override
    public int updateItemRecordMsgVersion(long id, int messageSentVersion) {
        return userItemRecordExtRepo.updateItemRecordMsgVersion(RECORD_TABLE_NAME, id, messageSentVersion);
    }

}