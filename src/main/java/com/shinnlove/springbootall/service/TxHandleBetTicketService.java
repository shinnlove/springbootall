/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;

/**
 * @author Tony Zhao
 * @version $Id: TxHandleBetTicketService.java, v 0.1 2024-07-10 14:32 Tony Zhao Exp $$
 */
public interface TxHandleBetTicketService {

    /**
     * 事务增加竞猜券。
     *
     * @param guid
     * @param ticketNumber
     * @return                  1 - 券记录成功 0 - 券记录失败
     */
    Integer txAddBetTicket(long guid, int ticketNumber);

    /**
     * 事务减少竞猜券。
     *
     * @param guid
     * @param ticketNumber
     * @return                  1 - 券记录成功 0 - 券记录失败
     */
    Integer txReduceBetTicket(long guid, int ticketNumber);

}
