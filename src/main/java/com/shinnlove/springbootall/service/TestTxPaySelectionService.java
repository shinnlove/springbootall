/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;

import com.shinnlove.springbootall.exceptions.DBAccessThrowException;
import com.shinnlove.springbootall.exceptions.DBExecuteReturnException;

/**
 * @author Tony Zhao
 * @version $Id: TestTxPaySelectionService.java, v 0.1 2024-07-01 14:34 Tony Zhao Exp $$
 */
public interface TestTxPaySelectionService {

    /**
     * An outta interface for internal order module to call to pay the selection,
     * and lock the storage only once each select id.
     *
     * @param selectId
     * @return
     * @throws DBAccessThrowException
     * @throws DBExecuteReturnException
     */
    int txPaySelectionAndLockStorageOnce(long selectId) throws DBAccessThrowException, DBExecuteReturnException;

    /**
     * An outta interface for internal order module to call when receive success notifications from payments side,
     * update item storage sell num and selection status.
     *
     * @param selectId
     * @return
     * @throws DBAccessThrowException
     * @throws DBExecuteReturnException
     */
    int txUpdatePaidStatusAndSellNum(long selectId) throws DBAccessThrowException, DBExecuteReturnException;

}
