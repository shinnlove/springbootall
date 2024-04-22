package com.shinnlove.springbootall.db.dao;


import com.shinnlove.springbootall.db.po.UserFragmentCollectAggEntity;
import com.shinnlove.springbootall.db.po.UserFragmentCollectEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFragmentCollectRepo {

    long insertSelective(@Param("tableName") String tableName,
                         @Param("entity") UserFragmentCollectEntity entity);

    List<UserFragmentCollectEntity> queryUnUsedFragments(@Param("tableName") String tableName,
                                                         @Param("activityId") String activityId,
                                                         @Param("componentId") Long componentId,
                                                         @Param("guid") Long guid);

    @Deprecated
    List<UserFragmentCollectAggEntity> queryUserCanCompoundCount(@Param("tableName") String tableName,
                                                                 @Param("activityId") String activityId,
                                                                 @Param("componentId") Long componentId,
                                                                 @Param("guid") Long guid);

    Integer updateFragmentStatusById(@Param("tableName") String tableName,
                                     @Param("ids") List<Long> ids);

}