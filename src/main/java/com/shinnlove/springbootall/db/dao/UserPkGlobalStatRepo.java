/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.db.dao;

import com.shinnlove.springbootall.db.po.UserPkGlobalStatEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: UserPkGlobalStatRepo.java, v 0.1 2024-04-15 14:38 Tony Zhao Exp $$
 */
@Repository
public interface UserPkGlobalStatRepo {

    long insertUserGlobalStat(@Param("tableName") String tableName,
                              @Param("entity") UserPkGlobalStatEntity entity);

    List<UserPkGlobalStatEntity> queryUserGlobalStat(@Param("tableName") String tableName,
                                                     @Param(value = "activityId") String activityId,
                                                     @Param("guid") Long guid);

    Integer updateGlobalSuccess(@Param("tableName") String tableName,
                                @Param(value = "activityId") String activityId,
                                @Param("guid") Long guid);

    Integer updateGlobalSuccessWithPromotionLevel(@Param("tableName") String tableName,
                                                  @Param(value = "activityId") String activityId,
                                                  @Param("guid") Long guid);

    Integer updateGlobalFailure(@Param("tableName") String tableName,
                                @Param(value = "activityId") String activityId,
                                @Param("guid") Long guid);

    Integer updateGlobalDraw(@Param("tableName") String tableName,
                             @Param(value = "activityId") String activityId,
                             @Param("guid") Long guid);

}