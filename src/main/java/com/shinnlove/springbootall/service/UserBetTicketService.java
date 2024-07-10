/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;

import com.shinnlove.springbootall.db.po.UserBetTicketEntity;
import com.shinnlove.springbootall.exceptions.BusinessCode;
import com.shinnlove.springbootall.exceptions.DBAccessThrowException;
import com.shinnlove.springbootall.exceptions.DBExecuteReturnException;

/**
 * @author Tony Zhao
 * @version $Id: UserBetTicketService.java, v 0.1 2024-07-10 11:15 Tony Zhao Exp $$
 */
public interface UserBetTicketService {

    /**
     * 初始化用户竞猜券标量记录。
     *
     * @param guid
     * @return
     * @throws DBAccessThrowException
     * @throws DBExecuteReturnException
     */
    long initUserBetTicket(Long guid) throws DBAccessThrowException, DBExecuteReturnException;

    /**
     * 查询用户有多少竞猜券。
     *
     * @param guid
     * @return
     */
    UserBetTicketEntity queryUserBetTicket(Long guid);

    /**
     * @param guid
     * @param incNum
     * @return
     * @throws DBAccessThrowException
     * @throws DBExecuteReturnException
     */
    int incUserTicketNumber(Long guid, Integer incNum) throws DBAccessThrowException, DBExecuteReturnException;

    /**
     * @param guid
     * @param decrNum
     * @return
     * @throws DBAccessThrowException
     * @throws DBExecuteReturnException
     */
    int decrUserTicketNumber(Long guid, Integer decrNum) throws DBAccessThrowException, DBExecuteReturnException;

}
