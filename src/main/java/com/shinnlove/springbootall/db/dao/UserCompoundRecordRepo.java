package com.shinnlove.springbootall.db.dao;

import com.shinnlove.springbootall.db.po.UserCompoundRecordAggEntity;
import com.shinnlove.springbootall.db.po.UserCompoundRecordEntity;
import com.shinnlove.springbootall.db.po.UserFragmentCollectAggEntity;
import com.shinnlove.springbootall.db.po.UserFragmentCollectEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCompoundRecordRepo {

    long insertSelective(@Param("tableName") String tableName,
                         @Param("entity") UserCompoundRecordEntity entity);

    List<UserCompoundRecordAggEntity> queryUserCompoundCount(@Param("tableName") String tableName,
                                                             @Param("activityId") String activityId,
                                                             @Param("componentId") Long componentId,
                                                             @Param("guid") Long guid);

}