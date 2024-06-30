/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.db.dao;

import com.shinnlove.springbootall.db.po.AssistDeductCodeEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Tony Zhao
 * @version $Id: AssistDeductCodeRepo.java, v 0.1 2024-06-18 15:15 Tony Zhao Exp $$
 */
@Repository
public interface AssistDeductCodeRepo {

    /**
     * @param entity
     * @return
     */
    long insertSelective(@Param("entity") AssistDeductCodeEntity entity);

    /**
     * @param assistCode
     * @return
     */
    AssistDeductCodeEntity queryByAssistCode(@Param(value = "activityId") String activityId,
                                             @Param(value = "componentId") long componentId,
                                             @Param(value = "assistCode") String assistCode);

    /**
     * @param activityId
     * @param componentId
     * @param selectId
     * @return
     */
    AssistDeductCodeEntity queryBySelectId(@Param(value = "activityId") String activityId,
                                           @Param(value = "componentId") long componentId,
                                           @Param(value = "selectId") long selectId);

}
