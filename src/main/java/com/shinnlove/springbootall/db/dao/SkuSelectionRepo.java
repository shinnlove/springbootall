/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.db.dao;

import com.shinnlove.springbootall.db.po.SkuSelectionEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: PromotionSkuSelectionRepo.java, v 0.1 2024-06-12 17:18 Tony Zhao Exp $$
 */
@Repository
public interface SkuSelectionRepo {

    long insertSelective(@Param("entity") SkuSelectionEntity entity);

    SkuSelectionEntity querySelectById(@Param(value = "activityId") String activityId,
                                       @Param(value = "componentId") long componentId,
                                       @Param(value = "id") long id);

    SkuSelectionEntity querySelectByIdForUpdate(@Param(value = "activityId") String activityId,
                                                @Param(value = "componentId") long componentId,
                                                @Param(value = "id") long id);

    SkuSelectionEntity queryCurrentSelection(@Param(value = "activityId") String activityId,
                                             @Param(value = "componentId") long componentId,
                                             @Param(value = "guid") long guid);

    int updateSelectionPayStatus(@Param(value = "activityId") String activityId,
                                 @Param(value = "componentId") long componentId,
                                 @Param(value = "selectId") long selectId,
                                 @Param(value = "payStatus") int payStatus);

    int updateSelectionValidStatus(@Param(value = "activityId") String activityId,
                                   @Param(value = "componentId") long componentId,
                                   @Param(value = "selectId") long selectId,
                                   @Param(value = "validStatus") int validStatus);

    int updateSelection2ndStartEndTime(@Param(value = "activityId") String activityId,
                                       @Param(value = "componentId") long componentId,
                                       @Param(value = "selectId") long selectId,
                                       @Param(value = "start2ndTime") long start2ndTime,
                                       @Param(value = "ddl2ndTime") long ddl2ndTime);

    List<SkuSelectionEntity> cursorQueryPayingSelection(@Param(value = "lastRecordId") Long lastRecordId,
                                                        @Param(value = "pageSize") Integer pageSize);

    List<SkuSelectionEntity> cursorQueryValidSelection(@Param(value = "lastRecordId") Long lastRecordId,
                                                       @Param(value = "pageSize") Integer pageSize);

    int updateSelectionStage1SendStatus(@Param(value = "activityId") String activityId,
                                        @Param(value = "componentId") long componentId,
                                        @Param(value = "selectId") long selectId,
                                        @Param(value = "sendStatus") int sendStatus);

    int updateSelectionStage4SendStatus(@Param(value = "activityId") String activityId,
                                        @Param(value = "componentId") long componentId,
                                        @Param(value = "selectId") long selectId,
                                        @Param(value = "sendStatus") int sendStatus);

}
