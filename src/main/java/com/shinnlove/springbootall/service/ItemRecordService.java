/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;

import com.shinnlove.springbootall.db.po.ItemRecordEntity;

import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: ItemRecordService.java, v 0.1 2024-06-03 16:27 Tony Zhao Exp $$
 */
public interface ItemRecordService {

    long cursorQueryItemRecord(int messageSentVersion, int pageSize);

    List<ItemRecordEntity> simpleQueryItemRecord(int messageSentVersion);

    int updateItemRecordMsgVersion(long id, int messageSentVersion);

}
