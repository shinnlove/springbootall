/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.dao.UserGuessAuthorRepo;
import com.shinnlove.springbootall.db.po.GlobalGuessAggEntity;
import com.shinnlove.springbootall.db.po.UserGuessAggEntity;
import com.shinnlove.springbootall.db.po.UserGuessAuthorEntity;
import com.shinnlove.springbootall.exceptions.BusinessCode;
import com.shinnlove.springbootall.exceptions.DBAccessThrowException;
import com.shinnlove.springbootall.exceptions.DBExecuteReturnException;
import com.shinnlove.springbootall.service.UserGuessAuthorService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author Tony Zhao
 * @version $Id: UserGuessAuthorServiceImpl.java, v 0.1 2024-07-09 16:47 Tony Zhao Exp $$
 */
@Service
public class UserGuessAuthorServiceImpl implements UserGuessAuthorService {

    private static final String activityId = "activityId";

    private static final long componentId = 123L;

    private static final long guid = 123456L;

    private static final long cbid = 666888999;

    private static final long authorId = 666888L;

    private static final String authorName = "小逍遥";

    private static final int betMultiplier = 100;

    private static final int limit = 100;

    private static final Logger logger = LoggerFactory.getLogger(UserGuessAuthorServiceImpl.class);

    @Autowired
    private UserGuessAuthorRepo userGuessAuthorRepo;

    public long saveGuessInfo() throws DBAccessThrowException, DBExecuteReturnException {
        // 组装信息
        UserGuessAuthorEntity guess = new UserGuessAuthorEntity();
        guess.setActivityId(activityId);
        guess.setComponentId(componentId);
        guess.setGuid(guid);
        guess.setCbid(cbid);
        // 是否揭面
        guess.setGuessRevealIdentity(1);
        // 若揭面的作者信息
        guess.setGuessAuthorId(authorId);
        guess.setGuessAuthorName(authorName);
        guess.setBetMultiplier(betMultiplier);
        // 竞猜时候一定还没有领奖
        guess.setRewardTaken(0);

        // 保存用户竞猜记录
        long guessId = 0L;
        try {
            long result = userGuessAuthorRepo.insertSelective(guess);
            if (result <= 0) {
                throw new DBExecuteReturnException(BusinessCode.DB_EXECUTE_RETURN_ERROR);
            }
            guessId = guess.getId();
        } catch (Exception e) {
            logger.error("submitGuess4Book error", e);
            throw new DBAccessThrowException(BusinessCode.DB_ACCESS_THROW_ERROR, e);
        }

        return guessId;
    }

    public List<UserGuessAuthorEntity> queryGuessesByGuid() {
        List<UserGuessAuthorEntity> guesses = userGuessAuthorRepo.queryGuessesByGuid(activityId, componentId, guid);
        if (CollectionUtils.isEmpty(guesses)) {
            return Collections.emptyList();
        }

        return guesses;
    }

    public UserGuessAuthorEntity queryGuessByCbidAndGuid() {
        UserGuessAuthorEntity guess = userGuessAuthorRepo.queryGuessByCbidAndGuid(activityId, componentId, cbid, guid);
        return Objects.isNull(guess) ? null : guess;
    }

    public List<UserGuessAggEntity> countGlobalGuessByCbid() {
        List<UserGuessAggEntity> globalGuesses = userGuessAuthorRepo.countGlobalGuessByCbid(activityId, componentId, cbid);
        if (CollectionUtils.isEmpty(globalGuesses)) {
            return Collections.emptyList();
        }

        return globalGuesses;
    }

    public List<GlobalGuessAggEntity> countGlobalHotGuessAuthors() {
        List<GlobalGuessAggEntity> globalGuessAuthors = userGuessAuthorRepo.countGlobalHotGuessAuthors(activityId, componentId, cbid);
        if (CollectionUtils.isEmpty(globalGuessAuthors)) {
            return Collections.emptyList();
        }

        return globalGuessAuthors;
    }

    /**
     * 统计方法：查询最先猜对作者的前10个用户。
     *
     * @return
     */
    public List<UserGuessAuthorEntity> top10GuessCorrectNameUsers() {
        List<UserGuessAuthorEntity> guesses = userGuessAuthorRepo.top10GuessCorrectNameUsers(activityId, componentId, cbid, authorId, limit);
        if (CollectionUtils.isEmpty(guesses)) {
            return Collections.emptyList();
        }

        return guesses;
    }

    /**
     * 发完奖后调用，更新用户领奖状态。
     *
     * @param rewardTaken
     * @return
     */
    public int updateRewardTakenStatus(Integer rewardTaken) {
        int result = 0;

        try {
            result = userGuessAuthorRepo.updateRewardTakenStatus(activityId, componentId, cbid, guid, rewardTaken);
        } catch (Exception e) {
            logger.error("updateRewardTakenStatus error", e);
            throw new DBAccessThrowException(BusinessCode.DB_ACCESS_THROW_ERROR, e);
        }

        if (result <= 0) {
            throw new DBExecuteReturnException(BusinessCode.DB_EXECUTE_RETURN_ERROR);
        }

        return result;
    }

}