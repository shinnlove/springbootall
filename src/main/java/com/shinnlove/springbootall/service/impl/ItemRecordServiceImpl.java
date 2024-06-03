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
import java.util.concurrent.TimeUnit;

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
    public long cursorQueryItemRecord(int messageSentVersion, int pageSize) {
        long totalScan = 0L;
        long lastId = 0L;

        while (true) {

            // query item record
            List<ItemRecordEntity> records = userItemRecordExtRepo
                    .cursorQueryItemRecord(RECORD_TABLE_NAME, ACTIVITY_ID, messageSentVersion, lastId, pageSize);

            if (CollectionUtils.isEmpty(records)) {
                break;
            }

            long batchSuccess = 0L;
            int batchTotal = records.size();
            totalScan += batchTotal;
            lastId = records.get(batchTotal - 1).getId();

            for (ItemRecordEntity record : records) {
                // do something
                long id = record.getId();
                batchSuccess += userItemRecordExtRepo.updateItemRecordMsgVersion(RECORD_TABLE_NAME, id, messageSentVersion);

            } // for

            if (batchSuccess < batchTotal) {
                // add a warn log for warning...
                System.out.println("[singleTableBatchSetMessageVersion] set message version ok, batchSuccess:"
                        + batchSuccess + ", batchTotal=" + batchTotal);
            }

            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                System.out.println("[singleTableBatchSetMessageVersion] sleep error=" + e.getMessage());
            }

        } // while

        return totalScan;
    }

    @Override
    public List<ItemRecordEntity> simpleQueryItemRecord(int messageSentVersion) {

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