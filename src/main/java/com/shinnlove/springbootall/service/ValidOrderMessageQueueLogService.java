/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;

import com.shinnlove.springbootall.db.po.ValidOrderMessageQueueLogEntity;
import com.shinnlove.springbootall.exceptions.BusinessCode;
import com.shinnlove.springbootall.exceptions.DBAccessThrowException;
import com.shinnlove.springbootall.exceptions.DBExecuteReturnException;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author Tony Zhao
 * @version $Id: ValidOrderMessageQueueLogService.java, v 0.1 2024-07-30 10:21 Tony Zhao Exp $$
 */
public interface ValidOrderMessageQueueLogService {

    /**
     * @return
     * @throws DBAccessThrowException
     * @throws DBExecuteReturnException
     */
    long saveOrderMessage() throws DBAccessThrowException, DBExecuteReturnException;

    /**
     * @return
     */
    List<ValidOrderMessageQueueLogEntity> queryMessageByGuid();

    /**
     * @return
     */
    ValidOrderMessageQueueLogEntity queryMessageByOrderId();

    /**
     * @param reconcileStatus
     * @return
     * @throws DBAccessThrowException
     * @throws DBExecuteReturnException
     */
    int updateMessageLogStatus(Integer reconcileStatus) throws DBAccessThrowException, DBExecuteReturnException;

}
