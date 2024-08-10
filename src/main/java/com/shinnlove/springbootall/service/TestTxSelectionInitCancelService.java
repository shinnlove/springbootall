/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;

import com.shinnlove.springbootall.exceptions.DBAccessThrowException;
import com.shinnlove.springbootall.exceptions.DBExecuteReturnException;
import org.springframework.http.server.reactive.ServerHttpRequest;

/**
 * @author Tony Zhao
 * @version $Id: TestTxSelectionInitCancelService.java, v 0.1 2024-07-01 14:38 Tony Zhao Exp $$
 */
public interface TestTxSelectionInitCancelService {

    /**
     * An outta interface for user generate selection.
     *
     * @return
     * @throws DBAccessThrowException
     * @throws DBExecuteReturnException
     */
    long txGenerateSelectionWithDeduct() throws DBAccessThrowException, DBExecuteReturnException;

    /**
     * An outta interface for cancel user current selection and return locked storage if available.
     *
     * @param selectId
     * @return
     * @throws DBAccessThrowException
     * @throws DBExecuteReturnException
     */
    int txCancelSelectionAndReturnStorage(long selectId) throws DBAccessThrowException, DBExecuteReturnException;

}
