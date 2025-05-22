package com.shinnlove.springbootall.db.dao;

import com.shinnlove.springbootall.db.po.UserDailyStatEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDailyStatRepo {

    /**
     * 用户提交竞猜。
     *
     * @param entity
     * @return
     */
    long insertSelective(@Param("entity") UserDailyStatEntity entity);

    /**
     * 查询用户竞猜列表。
     *
     * @param activityId
     * @param componentId
     * @param guid
     * @return
     */
    UserDailyStatEntity queryUserDailyStat(@Param(value = "activityId") String activityId,
                                           @Param(value = "componentId") Long componentId,
                                           @Param(value = "guid") Long guid,
                                           @Param(value = "dateMark") Integer dateMark);

    /**
     * 更新用户日常统计表。
     *
     * @param activityId
     * @param componentId
     * @param guid
     * @param dateMark
     * @return
     */
    Integer updateUserDailyStat(@Param(value = "activityId") String activityId,
                                @Param(value = "componentId") Long componentId,
                                @Param(value = "guid") Long guid,
                                @Param(value = "dateMark") Integer dateMark);

}