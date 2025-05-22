package com.shinnlove.springbootall.db.dao;


import com.shinnlove.springbootall.db.po.AssistStatisticsEntity;
import org.apache.ibatis.annotations.Param;

public interface AssistStatisticsRepo {

    AssistStatisticsEntity getStatisticsByInviteGuid(@Param(value = "guid") long guid);

    void createStatisticsEntity(@Param(value = "guid") long guid);

    int updateInviteCount(@Param(value = "guid") Long guid);

    int updatePointCount(@Param(value = "guid") Long guid,
                         @Param(value = "pointCount") Long pointCount);

}