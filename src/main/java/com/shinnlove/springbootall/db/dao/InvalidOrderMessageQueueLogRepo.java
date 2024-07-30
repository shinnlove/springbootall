/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.db.dao;

import com.shinnlove.springbootall.db.po.InvalidOrderMessageQueueLogEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Tony Zhao
 * @version $Id: InvalidOrderMessageQueueLogRepo.java, v 0.1 2024-07-30 10:19 Tony Zhao Exp $$
 */
@Repository
public interface InvalidOrderMessageQueueLogRepo {

    long insertSelective(@Param("entity") InvalidOrderMessageQueueLogEntity record);

    InvalidOrderMessageQueueLogEntity queryMessageByOrderId(@Param(value = "activityId") String activityId,
                                                            @Param(value = "componentId") Long componentId,
                                                            @Param(value = "orderId") String orderId);

}