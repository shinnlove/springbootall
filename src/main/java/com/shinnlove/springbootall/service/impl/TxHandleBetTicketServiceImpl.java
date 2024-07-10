/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.po.UserBetTicketEntity;
import com.shinnlove.springbootall.enums.BetTicketChangeEnum;
import com.shinnlove.springbootall.service.TxHandleBetTicketService;
import com.shinnlove.springbootall.service.UserBetTicketService;
import com.shinnlove.springbootall.service.UserTicketChangeLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author Tony Zhao
 * @version $Id: TxHandleBetTicketServiceImpl.java, v 0.1 2024-07-10 15:02 Tony Zhao Exp $$
 */
@Service
public class TxHandleBetTicketServiceImpl implements TxHandleBetTicketService {

    private static final Logger         logger = LoggerFactory.getLogger(TxHandleBetTicketServiceImpl.class);

    private static final String         activityId = "123456";

    private static final long           componentId = 123L;

    /** 用户竞猜券标量仓储 */
    @Autowired
    private UserBetTicketService        userBetTicketService;

    /** 用户竞猜券变更日志仓储 */
    @Autowired
    private UserTicketChangeLogService  userTicketChangeLogService;

    /** 事务模板 */
    @Autowired
    private TransactionTemplate         transactionTemplate;

    @Override
    public Integer txAddBetTicket(long guid, int ticketNumber) {
        try {
            UserBetTicketEntity betTicket = userBetTicketService.queryUserBetTicket(guid);
            if (Objects.isNull(betTicket)) {
                userBetTicketService.initUserBetTicket(guid);
            }

            final int changeType = BetTicketChangeEnum.ADD_BET_TICKET.getCode();

            return tx(status -> {

                userBetTicketService.incUserTicketNumber(guid, ticketNumber);

                userTicketChangeLogService.addTicketChangeLog(guid, changeType, ticketNumber);

                return ticketNumber;

            });
        } catch (Exception e) {
            logger.error("txAddBetTicket error", e);
            return 0;
        }
    }

    @Override
    public Integer txReduceBetTicket(long guid, int ticketNumber) {
        try {
            UserBetTicketEntity betTicket = userBetTicketService.queryUserBetTicket(guid);
            if (Objects.isNull(betTicket)) {
                userBetTicketService.initUserBetTicket(guid);
            }

            final int changeType = BetTicketChangeEnum.REDUCE_BET_TICKET.getCode();

            return tx(status -> {

                userBetTicketService.decrUserTicketNumber(guid, ticketNumber);

                userTicketChangeLogService.addTicketChangeLog(guid, changeType, ticketNumber);

                return ticketNumber;

            });
        } catch (Exception e) {
            logger.error("txReduceBetTicket error", e);
            return 0;
        }
    }

    private void txn(Consumer<TransactionStatus> f) {
        transactionTemplate.executeWithoutResult(f);
    }

    private <T> T tx(TransactionCallback<T> callback) {
        return transactionTemplate.execute(callback);
    }

}