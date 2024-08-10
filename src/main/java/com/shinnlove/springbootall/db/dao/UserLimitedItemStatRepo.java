package com.shinnlove.springbootall.db.dao;

import com.shinnlove.springbootall.db.po.UserLimitedItemStatEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLimitedItemStatRepo {

    long insertSelective(@Param("tableName") String tableName,
                         @Param("entity") UserLimitedItemStatEntity record);

    UserLimitedItemStatEntity queryByGuidAndLimitedType(@Param("tableName") String tableName,
                                                        @Param("activityId") String activityId,
                                                        @Param("componentId") Long componentId,
                                                        @Param("guid") Long guid,
                                                        @Param("limitedType") Integer limitedType);

    Integer updateStatTotalCount(@Param("tableName") String tableName,
                                 @Param("activityId") String activityId,
                                 @Param("componentId") Long componentId,
                                 @Param("guid") Long guid,
                                 @Param("limitedType") Integer limitedType,
                                 @Param("addTotalCount") Integer addTotalCount);

    Integer updateStatUsedCount(@Param("tableName") String tableName,
                                @Param("activityId") String activityId,
                                @Param("componentId") Long componentId,
                                @Param("guid") Long guid,
                                @Param("limitedType") Integer limitedType,
                                @Param("addUsedCount") Integer addUsedCount);

}