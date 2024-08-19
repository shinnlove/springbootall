/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;

import com.shinnlove.springbootall.db.po.AssistRecordEntity;
import com.shinnlove.springbootall.db.po.SumAssistRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: AssistRecordService.java, v 0.1 2024-08-19 17:32 Tony Zhao Exp $$
 */
public interface AssistRecordService {

    @Deprecated
    long createAssistRecord();

    @Deprecated
    List<AssistRecordEntity> queryEffectiveAssistRecordByGuid();

    SumAssistRecord sumEffectiveAssistRecordByGuid();

    @Deprecated
    int updateReceivedBatch();

    long countTodayInviteRecords();

    long countInviteRecords();

    List<AssistRecordEntity> pageQueryInviteRecords(int page, int size);

    AssistRecordEntity latestAssistRecord();

}
