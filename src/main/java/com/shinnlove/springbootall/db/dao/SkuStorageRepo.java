/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.db.dao;

import com.shinnlove.springbootall.db.po.SkuStorageEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * update sku storage repo.
 *
 * @author Tony Zhao
 * @version $Id: SkuStorageRepo.java, v 0.1 2024-06-12 17:18 Tony Zhao Exp $$
 */
@Repository
public interface SkuStorageRepo {

    /**
     * todo: need add unique index on table!!! @Tony Zhao
     *
     * @param entity
     * @return
     */
    long insertSelective(@Param("entity") SkuStorageEntity entity);

    SkuStorageEntity queryStorageByItemId(@Param(value = "activityId") String activityId,
                                          @Param(value = "componentId") long componentId,
                                          @Param(value = "itemId") long itemId);

    int updateLockNum(@Param(value = "activityId") String activityId,
                      @Param(value = "componentId") long componentId,
                      @Param(value = "itemId") long itemId,
                      @Param(value = "lockNum") long lockNum);

    int updateSellNumDeductLockNum(@Param(value = "activityId") String activityId,
                                   @Param(value = "componentId") long componentId,
                                   @Param(value = "itemId") long itemId,
                                   @Param(value = "sellNum") long sellNum);

}
