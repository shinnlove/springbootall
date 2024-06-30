/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.db.dao;

import com.shinnlove.springbootall.db.po.SkuStorageChangeLogEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Tony Zhao
 * @version $Id: SkuStorageChangeLogRepo.java, v 0.1 2024-06-23 18:28 Tony Zhao Exp $$
 */
@Repository
public interface SkuStorageChangeLogRepo {

    /**
     * @param entity
     * @return
     */
    long insertSelective(@Param("entity") SkuStorageChangeLogEntity entity);

    /**
     * 查询某个商品sku的库存变更记录。
     *
     * @param activityId
     * @param componentId
     * @param itemId
     * @return
     */
    SkuStorageChangeLogEntity queryStorageChangeLogByItemId(@Param("activityId") String activityId,
                                                            @Param("componentId") Long componentId,
                                                            @Param("itemId") Long itemId);

    /**
     * 查询某个选择的库存变更记录。
     *
     * @param activityId
     * @param componentId
     * @param selectId
     * @return
     */
    SkuStorageChangeLogEntity queryStorageChangeLogBySelectId(@Param("activityId") String activityId,
                                                              @Param("componentId") Long componentId,
                                                              @Param("selectId") Long selectId);

}