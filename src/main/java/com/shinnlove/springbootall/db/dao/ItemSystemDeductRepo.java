/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.db.dao;

import com.shinnlove.springbootall.db.po.ItemSystemDeductEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Tony Zhao
 * @version $Id: ItemSystemDeductRepo.java, v 0.1 2024-06-25 14:26 Tony Zhao Exp $$
 */
@Repository
public interface ItemSystemDeductRepo {

    /**
     * @param entity
     * @return
     */
    long insertSelective(@Param("entity") ItemSystemDeductEntity entity);

    /**
     * @param deductId
     * @return
     */
    ItemSystemDeductEntity queryByDeductId(@Param(value = "activityId") String activityId,
            @Param(value = "componentId") Long componentId,
            @Param(value = "deductId") Long deductId);

}
