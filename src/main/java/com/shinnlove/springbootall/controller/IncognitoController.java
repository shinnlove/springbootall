/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.controller;

import com.shinnlove.springbootall.db.po.GlobalGuessAggEntity;
import com.shinnlove.springbootall.db.po.UserBetTicketEntity;
import com.shinnlove.springbootall.db.po.UserGuessAggEntity;
import com.shinnlove.springbootall.db.po.UserGuessAuthorEntity;
import com.shinnlove.springbootall.service.TxHandleBetTicketService;
import com.shinnlove.springbootall.service.UserBetTicketService;
import com.shinnlove.springbootall.service.UserGuessAuthorService;
import com.shinnlove.springbootall.service.UserTicketChangeLogService;
import com.shinnlove.springbootall.service.impl.UserBetTicketServiceImpl;
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

    private static Logger               logger = LoggerFactory.getLogger(IncognitoController.class);

    @Autowired
    private UserGuessAuthorService      userGuessAuthorService;

    @Autowired
    private UserBetTicketService        userBetTicketService;

    @Autowired
    private UserTicketChangeLogService  userTicketChangeLogService;

    @Autowired
    private TxHandleBetTicketService    txHandleBetTicketService;

    @RequestMapping(value = "/save_guess_info", method = RequestMethod.GET)
    public long saveGuessInfo() {
        return userGuessAuthorService.saveGuessInfo();
    }

    @RequestMapping(value = "/query_guesses_by_guid", method = RequestMethod.GET)
    public List<UserGuessAuthorEntity> queryGuessesByGuid(long guid) {
        return userGuessAuthorService.queryGuessesByGuid(guid);
    }

    @RequestMapping(value = "/query_guesses_by_cbid_guid", method = RequestMethod.GET)
    public UserGuessAuthorEntity queryGuessByCbidAndGuid(long cbid, long guid) {
        return userGuessAuthorService.queryGuessByCbidAndGuid(cbid, guid);
    }

    @RequestMapping(value = "/count_global_guesses", method = RequestMethod.GET)
    public List<UserGuessAggEntity> countGlobalGuessByCbid(long cbid) {
        return userGuessAuthorService.countGlobalGuessByCbid(cbid);
    }

    @RequestMapping(value = "/count_global_hot_guesses", method = RequestMethod.GET)
    public List<GlobalGuessAggEntity> countGlobalHotGuessAuthors(long cbid) {
        return userGuessAuthorService.countGlobalHotGuessAuthors(cbid);
    }

    @RequestMapping(value = "/top10_correct_guesses", method = RequestMethod.GET)
    public List<UserGuessAuthorEntity> top10GuessCorrectNameUsers(long cbid, long authorId) {
        return userGuessAuthorService.top10GuessCorrectNameUsers(cbid, authorId);
    }

    @RequestMapping(value = "/update_reward_taken", method = RequestMethod.GET)
    public int updateRewardTakenStatus(long cbid, long guid, int rewardTaken) {
        return userGuessAuthorService.updateRewardTakenStatus(cbid, guid, rewardTaken);
    }

    @RequestMapping(value = "/init_user_ticket", method = RequestMethod.GET)
    public long initUserBetTicket(long guid) {
        return userBetTicketService.initUserBetTicket(guid);
    }

    @RequestMapping(value = "/query_user_ticket", method = RequestMethod.GET)
    public UserBetTicketEntity queryUserBetTicket(long guid) {
        return userBetTicketService.queryUserBetTicket(guid);
    }

    @RequestMapping(value = "/inc_user_ticket", method = RequestMethod.GET)
    public int incUserBetTicket(long guid, int num) {
        return userBetTicketService.incUserTicketNumber(guid, num);
    }

    @RequestMapping(value = "/decr_user_ticket", method = RequestMethod.GET)
    public int decrUserBetTicket(long guid, int num) {
        return userBetTicketService.decrUserTicketNumber(guid, num);
    }

    @RequestMapping(value = "/ticket_change_log", method = RequestMethod.GET)
    public long addTicketChangeLog(long guid, int changeType, int num) {
        return userTicketChangeLogService.addTicketChangeLog(guid, changeType, num);
    }

    @RequestMapping(value = "/tx_add_ticket", method = RequestMethod.GET)
    public long txAddBetTicket(long guid, int number) {
        return txHandleBetTicketService.txAddBetTicket(guid, number);
    }

    @RequestMapping(value = "/tx_reduce_ticket", method = RequestMethod.GET)
    public long txReduceBetTicket(long guid, int number) {
        return txHandleBetTicketService.txReduceBetTicket(guid, number);
    }

}