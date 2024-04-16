/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.db.dao;

import com.shinnlove.springbootall.db.po.UserPkDailyStatEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: UserPkDailyStatRepo.java, v 0.1 2024-04-15 14:34 Tony Zhao Exp $$
 */
@Repository
public interface UserPkDailyStatRepo {

    long insertUserDailyStat(@Param("tableName") String tableName,
                             @Param("entity") UserPkDailyStatEntity entity);

    List<UserPkDailyStatEntity> queryUserDailyPk(@Param("tableName") String tableName,
                                                 @Param(value = "activityId") String activityId,
                                                 @Param("guid") Long guid,
                                                 @Param("statDate") Integer statDate);

    Integer updateDailySuccess(@Param("tableName") String tableName,
                               @Param(value = "activityId") String activityId,
                               @Param("guid") Long guid);

    Integer incDailyFailure(@Param("tableName") String tableName,
                            @Param(value = "activityId") String activityId,
                            @Param("guid") Long guid);

    Integer incDailyDraw(@Param("tableName") String tableName,
                         @Param(value = "activityId") String activityId,
                         @Param("guid") Long guid);

    Integer incDailyChance(@Param("tableName") String tableName,
                           @Param(value = "activityId") String activityId,
                           @Param("guid") Long guid);

}
