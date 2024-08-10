/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;

import com.shinnlove.springbootall.db.po.AggSelectionAssistEntity;
import com.shinnlove.springbootall.db.po.AssistDeductDetailEntity;
import com.shinnlove.springbootall.exceptions.DBAccessThrowException;
import com.shinnlove.springbootall.exceptions.DBExecuteReturnException;

import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: AssistDeductDetailService.java, v 0.1 2024-06-30 20:02 Tony Zhao Exp $$
 */
public interface AssistDeductDetailService {

    long insertDeductDetail(String activityId, Long componentId, Long selectId,
                                   Integer helpType, Long gatherGuid, Long helpGuid, Integer randomHelpPrice,
                                   Integer realHelpPrice) throws DBAccessThrowException, DBExecuteReturnException;

    /**
     * 主页只显示人的助力记录，并且按助力价格倒序排列。
     *
     * @param activityId
     * @param componentId
     * @param selectId
     * @param helpType
     * @return
     */
    List<AssistDeductDetailEntity> querySelectionDeductDetailIndex(String activityId, long componentId,
                                                                          long selectId, int helpType);

    /**
     * 助力详情页，按照好友助力时间排序。
     *
     * @param activityId
     * @param componentId
     * @param selectId
     * @param helpType
     * @return
     */
    List<AssistDeductDetailEntity> querySelectionDeductDetailHelp(String activityId, long componentId,
                                                                         long selectId, int helpType);

    List<AssistDeductDetailEntity> queryAssistByGuid(String activityId, long componentId, long guid);

    AggSelectionAssistEntity aggSelectionAssistDeductPrice(String activityId, long componentId, long selectId);

}
