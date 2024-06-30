/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;

import com.shinnlove.springbootall.db.po.SkuStorageDeDupLockEntity;

/**
 * @author Tony Zhao
 * @version $Id: SkuStorageDeDupLockService.java, v 0.1 2024-06-30 21:06 Tony Zhao Exp $$
 */
public interface SkuStorageDeDupLockService {

    SkuStorageDeDupLockEntity queryStorageLockBySelectIdWithoutChangeType(String activityId, Long componentId, Long selectId);

    SkuStorageDeDupLockEntity queryStorageLockBySelectIdWithChangeType(String activityId, Long componentId,
                                                                              Long selectId, Integer changeType);

    long insertLockRecord(String activityId, Long componentId, Long itemId, Long selectId, Long guid,
                                 Integer changeType, Integer changeNum);

    int updateSelectionStorageLockStatus(String activityId, Long componentId, Long selectId, Integer changeType);

}
