/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;

import com.shinnlove.springbootall.exceptions.DBAccessThrowException;
import com.shinnlove.springbootall.exceptions.DBExecuteReturnException;

/**
 * @author Tony Zhao
 * @version $Id: UserTicketChangeLogService.java, v 0.1 2024-07-10 14:13 Tony Zhao Exp $$
 */
public interface UserTicketChangeLogService {

    /**
     * @param guid
     * @param changeType
     * @param ticketNumber
     * @return
     * @throws DBAccessThrowException
     * @throws DBExecuteReturnException
     */
    long addTicketChangeLog(Long guid, Integer changeType, Integer ticketNumber) throws DBAccessThrowException, DBExecuteReturnException;

}
