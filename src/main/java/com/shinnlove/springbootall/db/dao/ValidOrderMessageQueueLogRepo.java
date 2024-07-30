/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.db.dao;

import com.shinnlove.springbootall.db.po.ValidOrderMessageQueueLogEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: ValidOrderMessageQueueLogRepo.java, v 0.1 2024-07-30 10:07 Tony Zhao Exp $$
 */
@Repository
public interface ValidOrderMessageQueueLogRepo {

    long insertSelective(@Param("entity") ValidOrderMessageQueueLogEntity record);

    List<ValidOrderMessageQueueLogEntity> queryMessageByGuid(@Param(value = "activityId") String activityId,
                                                             @Param(value = "componentId") Long componentId,
                                                             @Param(value = "guid") Long guid);

    ValidOrderMessageQueueLogEntity queryMessageByOrderId(@Param(value = "activityId") String activityId,
                                                          @Param(value = "componentId") Long componentId,
                                                          @Param(value = "orderId") String orderId);

    int updateReconcileStatusByOrderId(@Param(value = "activityId") String activityId,
                                       @Param(value = "componentId") Long componentId,
                                       @Param(value = "orderId") String orderId,
                                       @Param(value = "reconcileStatus") Integer reconcileStatus);

}
