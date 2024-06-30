/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.db.dao;

import com.shinnlove.springbootall.db.po.SkuStorageDeDupLockEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Tony Zhao
 * @version $Id: SkuStorageDeDupLockRepo.java, v 0.1 2024-06-23 18:29 Tony Zhao Exp $$
 */
@Repository
public interface SkuStorageDeDupLockRepo {

    /**
     * 通过selectId查询库存锁住的情况。
     *
     * @param activityId
     * @param componentId
     * @param selectId
     * @return
     */
    SkuStorageDeDupLockEntity queryStorageLockBySelectIdWithoutChangeType(@Param("activityId") String activityId,
                                                                          @Param("componentId") Long componentId,
                                                                          @Param("selectId") Long selectId);

    /**
     * 通过selectId查询库存锁住的情况。
     *
     * @param activityId
     * @param componentId
     * @param selectId
     * @param changeType
     * @return
     */
    SkuStorageDeDupLockEntity queryStorageLockBySelectIdWithChangeType(@Param("activityId") String activityId,
                                                                       @Param("componentId") Long componentId,
                                                                       @Param("selectId") Long selectId,
                                                                       @Param("changeType") Integer changeType);

    /**
     * 插入一条去重锁库存记录。
     *
     * @param entity
     * @return
     */
    long insertSelective(@Param("entity") SkuStorageDeDupLockEntity entity);

    /**
     * 更新库存锁定状态。
     *
     * @param activityId
     * @param componentId
     * @param selectId
     * @param changeType        库存锁定状态类型：0-未变更 1-已锁定 2-已扣减 3-超时已退还
     * @return
     */
    int updateSelectionStorageLockStatus(@Param("activityId") String activityId,
                                         @Param("componentId") Long componentId,
                                         @Param("selectId") Long selectId,
                                         @Param("changeType") Integer changeType);

}
