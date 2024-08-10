/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;

import com.shinnlove.springbootall.db.po.AssistDeductCodeEntity;
import com.shinnlove.springbootall.exceptions.DBAccessThrowException;
import com.shinnlove.springbootall.exceptions.DBExecuteReturnException;

/**
 * @author Tony Zhao
 * @version $Id: AssistDeductCodeService.java, v 0.1 2024-06-30 19:50 Tony Zhao Exp $$
 */
public interface AssistDeductCodeService {

    long insertDeductCode(String activityId, Long componentId, Long gatherGuid, Long selectId, Long itemId,
                          String assistCode) throws DBAccessThrowException, DBExecuteReturnException;

    AssistDeductCodeEntity queryByAssistCode(String activityId, Long componentId, String assistCode);

    AssistDeductCodeEntity queryBySelectId(String activityId, Long componentId, Long selectId);

}
