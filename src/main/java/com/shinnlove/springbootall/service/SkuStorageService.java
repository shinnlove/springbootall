/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;

import com.shinnlove.springbootall.db.po.SkuStorageEntity;
import com.shinnlove.springbootall.exceptions.DBAccessThrowException;
import com.shinnlove.springbootall.exceptions.DBExecuteReturnException;

/**
 * @author Tony Zhao
 * @version $Id: SkuStorageService.java, v 0.1 2024-06-30 21:09 Tony Zhao Exp $$
 */
public interface SkuStorageService {

    long initItemStorage(String activityId, long componentId, long itemId) throws DBAccessThrowException, DBExecuteReturnException;

    SkuStorageEntity queryStorageByItemId(String activityId, long componentId, long itemId);

    int updateLockNum(String activityId, long componentId, long itemId, int lockNum);

    int updateSellNumDeductLockNum(String activityId, long componentId, long itemId, int sellNum);

}
