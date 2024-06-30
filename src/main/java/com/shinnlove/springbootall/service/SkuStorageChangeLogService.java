/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;

import com.shinnlove.springbootall.db.po.SkuStorageChangeLogEntity;
import com.shinnlove.springbootall.exceptions.DBAccessThrowException;
import com.shinnlove.springbootall.exceptions.DBExecuteReturnException;

/**
 * @author Tony Zhao
 * @version $Id: SkuStorageChangeLogService.java, v 0.1 2024-06-30 21:03 Tony Zhao Exp $$
 */
public interface SkuStorageChangeLogService {


    /**
     * Query all storage change record by given selectId.
     *
     * @param activityId
     * @param componentId
     * @param selectId
     * @return
     */
    SkuStorageChangeLogEntity queryStorageChangeLogBySelectId(String activityId, Long componentId, Long selectId);

    /**
     * Record any storage change by selectId.
     *
     * @param activityId
     * @param componentId
     * @param itemId
     * @param selectId
     * @param guid
     * @param changeType
     * @param changeNum
     * @return
     * @throws DBAccessThrowException
     * @throws DBExecuteReturnException
     */
    long addStorageChangeLog(String activityId, long componentId, long itemId, long selectId, long guid,
                                    int changeType, int changeNum) throws DBAccessThrowException, DBExecuteReturnException;

}
