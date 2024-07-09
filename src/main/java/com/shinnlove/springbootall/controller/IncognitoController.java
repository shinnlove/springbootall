/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.controller;

import com.shinnlove.springbootall.db.po.GlobalGuessAggEntity;
import com.shinnlove.springbootall.db.po.UserGuessAggEntity;
import com.shinnlove.springbootall.db.po.UserGuessAuthorEntity;
import com.shinnlove.springbootall.service.UserGuessAuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: IncognitoController.java, v 0.1 2024-07-09 16:39 Tony Zhao Exp $$
 */
@RestController
@RequestMapping(value = "/incognito_dao")
public class IncognitoController {

    private static Logger logger = LoggerFactory.getLogger(IncognitoController.class);

    @Autowired
    private UserGuessAuthorService userGuessAuthorService;

    @RequestMapping(value = "/save_guess_info", method = RequestMethod.GET)
    public long saveGuessInfo() {
        return userGuessAuthorService.saveGuessInfo();
    }

    @RequestMapping(value = "/query_guesses_by_guid", method = RequestMethod.GET)
    public List<UserGuessAuthorEntity> queryGuessesByGuid() {
        return userGuessAuthorService.queryGuessesByGuid();
    }

    @RequestMapping(value = "/query_guesses_by_cbid_guid", method = RequestMethod.GET)
    public UserGuessAuthorEntity queryGuessByCbidAndGuid() {
        return userGuessAuthorService.queryGuessByCbidAndGuid();
    }

    @RequestMapping(value = "/count_global_guesses", method = RequestMethod.GET)
    public List<UserGuessAggEntity> countGlobalGuessByCbid() {
        return userGuessAuthorService.countGlobalGuessByCbid();
    }

    @RequestMapping(value = "/count_global_hot_guesses", method = RequestMethod.GET)
    public List<GlobalGuessAggEntity> countGlobalHotGuessAuthors() {
        return userGuessAuthorService.countGlobalHotGuessAuthors();
    }

    @RequestMapping(value = "/top10_correct_guesses", method = RequestMethod.GET)
    public List<UserGuessAuthorEntity> top10GuessCorrectNameUsers() {
        return userGuessAuthorService.top10GuessCorrectNameUsers();
    }

    @RequestMapping(value = "/update_reward_taken", method = RequestMethod.GET)
    public int updateRewardTakenStatus(int rewardTaken) {
        return userGuessAuthorService.updateRewardTakenStatus(rewardTaken);
    }

}