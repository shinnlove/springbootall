/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.db.dao;

import com.shinnlove.springbootall.db.po.UserItemRankingEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: UserItemRankingRepo.java, v 0.1 2024-04-19 10:30 Tony Zhao Exp $$
 */
@Repository
public interface UserItemRankingRepo {

    long insertUserItemRanking(@Param("tableName") String tableName,
                               @Param("entity") UserItemRankingEntity entity);

    List<UserItemRankingEntity> selectTopRanking(@Param("tableName") String tableName,
                                                 @Param("activityId") String activityId);

    UserItemRankingEntity queryByActivityGuid(@Param("tableName") String tableName,
                                              @Param("activityId") String activityId,
                                              @Param("guid") Long guid);

    Integer incItemTypeCount(@Param("tableName") String tableName,
                             @Param("activityId") String activityId,
                             @Param("guid") Long guid);

    Integer updateAndCollectAllItem(@Param("tableName") String tableName,
                                    @Param("activityId") String activityId,
                                    @Param("guid") Long guid,
                                    @Param("totalCount") Integer totalCount,
                                    @Param("timestamp") Long timestamp);

}