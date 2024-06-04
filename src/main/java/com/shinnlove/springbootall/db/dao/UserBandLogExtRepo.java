/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.db.dao;

import com.shinnlove.springbootall.db.po.UserBandLogExtEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: UserBandLogExtRepo.java, v 0.1 2024-06-04 19:45 Tony Zhao Exp $$
 */
@Repository
public interface UserBandLogExtRepo {

    /**
     * 查询用户最大的等级记录。
     *
     * @param tableName
     * @param activityId
     * @param guids
     * @return
     */
    List<UserBandLogExtEntity> queryUserMaxBandsWithTime(@Param(value = "tableName") String tableName,
                                                         @Param(value = "activityId") String activityId,
                                                         @Param(value = "guids") List<Long> guids);

}
