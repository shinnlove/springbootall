/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.db.dao;

import com.shinnlove.springbootall.db.po.GlobalGuessAggEntity;
import com.shinnlove.springbootall.db.po.UserGuessAggEntity;
import com.shinnlove.springbootall.db.po.UserGuessAuthorEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: UserGuessAuthorRepo.java, v 0.1 2024-07-04 16:10 Tony Zhao Exp $$
 */
@Repository
public interface UserGuessAuthorRepo {

    /**
     * 用户提交竞猜。
     *
     * @param entity
     * @return
     */
    long insertSelective(@Param("entity") UserGuessAuthorEntity entity);

    /**
     * 查询用户竞猜列表。
     *
     * @param activityId
     * @param componentId
     * @param guid
     * @return
     */
    List<UserGuessAuthorEntity> queryGuessesByGuid(@Param(value = "activityId") String activityId,
                                                   @Param(value = "componentId") Long componentId,
                                                   @Param(value = "guid") Long guid);

    /**
     * 领奖时，根据cbid和guid查询某个用户对某本书的竞猜。
     *
     * @param activityId
     * @param componentId
     * @param cbid
     * @param guid
     * @return
     */
    UserGuessAuthorEntity queryGuessByCbidAndGuid(@Param(value = "activityId") String activityId,
                                                  @Param(value = "componentId") Long componentId,
                                                  @Param(value = "cbid") Long cbid,
                                                  @Param(value = "guid") Long guid);

    /**
     * 查询某本书的全局竞猜是否揭面标量信息。
     *
     * @param activityId
     * @param componentId
     * @param cbid
     * @return
     */
    List<UserGuessAggEntity> countGlobalGuessByCbid(@Param(value = "activityId") String activityId,
                                                    @Param(value = "componentId") Long componentId,
                                                    @Param(value = "cbid") Long cbid);

    /**
     * 查询某本书的全局热猜作者和数量。
     *
     * @param activityId
     * @param componentId
     * @param cbid
     * @return
     */
    List<GlobalGuessAggEntity> countGlobalHotGuessAuthors(@Param(value = "activityId") String activityId,
                                                          @Param(value = "componentId") Long componentId,
                                                          @Param(value = "cbid") Long cbid);

    /**
     * 查询每本书如果揭面后，最先猜对作者的前10个用户。
     *
     * @param activityId        活动id
     * @param componentId       组件id
     * @param cbid              书cbid
     * @param authorId          作者id
     * @param limit             最先猜对的多少个用户，一般情况下limit = 10
     * @return
     */
    List<UserGuessAuthorEntity> top10GuessCorrectNameUsers(@Param(value = "activityId") String activityId,
                                                           @Param(value = "componentId") Long componentId,
                                                           @Param(value = "cbid") Long cbid,
                                                           @Param(value = "authorId") Long authorId,
                                                           @Param(value = "limit") Integer limit);

    /**
     * 更新用户领奖状态。
     *
     * @param activityId
     * @param componentId
     * @param guid
     * @param cbid
     * @param rewardTaken
     * @return
     */
    Integer updateRewardTakenStatus(@Param(value = "activityId") String activityId,
                                    @Param(value = "componentId") Long componentId,
                                    @Param(value = "cbid") Long cbid,
                                    @Param(value = "guid") Long guid,
                                    @Param(value = "rewardTaken") Integer rewardTaken);

}
