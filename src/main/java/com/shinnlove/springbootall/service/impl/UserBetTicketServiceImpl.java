/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.dao.UserBetTicketRepo;
import com.shinnlove.springbootall.db.po.UserBetTicketEntity;
import com.shinnlove.springbootall.exceptions.BusinessCode;
import com.shinnlove.springbootall.exceptions.DBAccessThrowException;
import com.shinnlove.springbootall.exceptions.DBExecuteReturnException;
import com.shinnlove.springbootall.service.UserBetTicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author Tony Zhao
 * @version $Id: UserBetTicketServiceImpl.java, v 0.1 2024-07-10 11:15 Tony Zhao Exp $$
 */
@Service
public class UserBetTicketServiceImpl implements UserBetTicketService {

    private static final Logger logger = LoggerFactory.getLogger(UserBetTicketServiceImpl.class);

    private static final String activityId = "123456";

    private static final long componentId = 123L;

    @Autowired
    private UserBetTicketRepo userBetTicketRepo;

    @Override
    public long initUserBetTicket(Long guid) throws DBAccessThrowException, DBExecuteReturnException {
        UserBetTicketEntity newBetTicket = new UserBetTicketEntity();
        newBetTicket.setActivityId(activityId);
        newBetTicket.setComponentId(componentId);
        newBetTicket.setGuid(guid);

        long result = 0L;
        try {
            result = userBetTicketRepo.insertSelective(newBetTicket);
        } catch (Exception e) {
            logger.error("UserBetTicketService initUserBetTicket has error, ex=" + e.getMessage(), e);
            throw new DBAccessThrowException(BusinessCode.FAIL, e);
        }

        if (result <= 0) {
            throw new DBExecuteReturnException(BusinessCode.FAIL);
        }

        return newBetTicket.getId();
    }

    @Override
    public UserBetTicketEntity queryUserBetTicket(Long guid) {
        UserBetTicketEntity betTicket = userBetTicketRepo.queryUserBetTicket(activityId, componentId, guid);
        return Objects.isNull(betTicket) ? null : betTicket;
    }

    @Override
    public int incUserTicketNumber(Long guid, Integer incNum) throws DBAccessThrowException, DBExecuteReturnException {
        int result = 0;

        try {
            result = userBetTicketRepo.incUserTicketNumber(activityId, componentId, guid, incNum);
        } catch (Exception e) {
            logger.error("UserBetTicketService incUserTicketNumber has error, ex=" + e.getMessage(), e);
            throw new DBAccessThrowException(BusinessCode.FAIL, e);
        }

        if (result <= 0) {
            throw new DBExecuteReturnException(BusinessCode.FAIL);
        }

        return result;
    }

    @Override
    public int decrUserTicketNumber(Long guid, Integer decrNum) throws DBAccessThrowException, DBExecuteReturnException {
        int result = 0;

        try {
            result = userBetTicketRepo.decrUserTicketNumber(activityId, componentId, guid, decrNum);
        } catch (Exception e) {
            logger.error("UserBetTicketService decrUserTicketNumber has error, ex=" + e.getMessage(), e);
            throw new DBAccessThrowException(BusinessCode.FAIL, e);
        }

        if (result <= 0) {
            throw new DBExecuteReturnException(BusinessCode.FAIL);
        }

        return result;
    }

}