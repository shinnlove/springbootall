/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;

import com.shinnlove.springbootall.db.po.GlobalGuessAggEntity;
import com.shinnlove.springbootall.db.po.UserGuessAggEntity;
import com.shinnlove.springbootall.db.po.UserGuessAuthorEntity;
import com.shinnlove.springbootall.exceptions.DBAccessThrowException;
import com.shinnlove.springbootall.exceptions.DBExecuteReturnException;

import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: UserGuessAuthorService.java, v 0.1 2024-07-09 16:46 Tony Zhao Exp $$
 */
public interface UserGuessAuthorService {

    long saveGuessInfo() throws DBAccessThrowException, DBExecuteReturnException;

    List<UserGuessAuthorEntity> queryGuessesByGuid(long guid);

    UserGuessAuthorEntity queryGuessByCbidAndGuid(long cbid, long guid);

    List<UserGuessAggEntity> countGlobalGuessByCbid(long cbid);

    List<GlobalGuessAggEntity> countGlobalHotGuessAuthors(long cbid);

    /**
     * 统计方法：查询最先猜对作者的前10个用户。
     *
     * @param cbid
     * @param authorId
     * @return
     */
    List<UserGuessAuthorEntity> top10GuessCorrectNameUsers(long cbid, long authorId);

    /**
     * 发完奖后调用，更新用户领奖状态。
     *
     * @param rewardTaken
     * @return
     */
    int updateRewardTakenStatus(Long cbid, Long guid, Integer rewardTaken);

}
