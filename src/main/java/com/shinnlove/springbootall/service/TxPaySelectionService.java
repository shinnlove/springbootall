/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;


import com.shinnlove.springbootall.exceptions.DBAccessThrowException;
import com.shinnlove.springbootall.exceptions.DBExecuteReturnException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * A service implementation of transaction changing selection status and sku storage .
 *
 * @author Tony Zhao
 * @version $Id: TxPaySelectionServiceImpl.java, v 0.1 2024-06-13 10:38 Tony Zhao Exp $$
 */

/**
 * 2024/06/12 Mark:
 * todo: need add transactional support no matter whether there is a transaction from outside caller!!!
 *
 * @author Tony Zhao
 * @version $Id: TxPaySelectionService.java, v 0.1 2024-06-12 19:21 Tony Zhao Exp $$
 */
public interface TxPaySelectionService {

    /**
     * An outta interface for internal order module to call to pay the selection,
     * and lock the storage only once each select id.
     *
     * @param activityId
     * @param request
     * @param componentId
     * @param selectId
     * @return
     * @throws DBAccessThrowException
     * @throws DBExecuteReturnException
     */
    int txPaySelectionAndLockStorageOnce(String activityId, ServerHttpRequest request, long componentId,
                                         long selectId) throws DBAccessThrowException, DBExecuteReturnException;

    /**
     * An outta interface for internal order module to call when receive success notifications from payments side,
     * update item storage sell num and selection status.
     *
     * @param activityId
     * @param request
     * @param componentId
     * @param selectId
     * @return
     * @throws DBAccessThrowException
     * @throws DBExecuteReturnException
     */
    int txUpdatePaidStatusAndSellNum(String activityId, ServerHttpRequest request, long componentId,
                                     long selectId) throws DBAccessThrowException, DBExecuteReturnException;

}