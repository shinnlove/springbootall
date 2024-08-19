/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.dao.AssistRecordRepo;
import com.shinnlove.springbootall.db.po.AssistRecordEntity;
import com.shinnlove.springbootall.db.po.SumAssistRecord;
import com.shinnlove.springbootall.service.AssistRecordService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author Tony Zhao
 * @version $Id: AssistRecordServiceImpl.java, v 0.1 2024-08-19 17:33 Tony Zhao Exp $$
 */
@Service
public class AssistRecordServiceImpl implements AssistRecordService {

    private static final Long inviterGuid = 888888L;

    @Autowired
    private AssistRecordRepo assistRecordRepo;

    @Deprecated
    @Override
    public long createAssistRecord() {
        // assemble info
        AssistRecordEntity assistRecordEntity = new AssistRecordEntity();
        assistRecordEntity.setInviterGuid(inviterGuid);

        int result = assistRecordRepo.createAssistRecord(assistRecordEntity);

        if (result <= 0) {
            return 0L;
        }

        return assistRecordEntity.getId();
    }

    @Deprecated
    @Override
    public List<AssistRecordEntity> queryEffectiveAssistRecordByGuid() {
        long currentTimeMillis = System.currentTimeMillis();

        List<AssistRecordEntity> pos = assistRecordRepo
                .queryEffectiveAssistRecordByGuid(inviterGuid, currentTimeMillis);

        return CollectionUtils.isEmpty(pos) ? Collections.emptyList() : pos;
    }

    @Override
    public SumAssistRecord sumEffectiveAssistRecordByGuid() {
        long currentTimeMillis = System.currentTimeMillis();

        SumAssistRecord assistRecord = assistRecordRepo.sumEffectiveAssistRecordByGuid(inviterGuid, currentTimeMillis);

        return Objects.nonNull(assistRecord) ? assistRecord : new SumAssistRecord();
    }

    @Deprecated
    @Override
    public int updateReceivedBatch() {
        List<Long> recordIds = new ArrayList<>();
        return assistRecordRepo.updateReceivedBatch(recordIds);
    }

    @Override
    public long countTodayInviteRecords() {

        // 获取当前日期
        LocalDate currentDate = LocalDate.now();

        // 格式化为"yyyyMM"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = currentDate.format(formatter);
        int dateMark = Integer.parseInt(formattedDate);

        return assistRecordRepo.countTodayInviteRecords(inviterGuid, dateMark);
    }

    @Override
    public long countInviteRecords() {
        return assistRecordRepo.countInviteRecords(inviterGuid);
    }

    @Override
    public List<AssistRecordEntity> pageQueryInviteRecords(int page, int size) {

        int offset = (page - 1) * size;
        int limit = size;

        List<AssistRecordEntity> entities = assistRecordRepo.pageQueryInviteRecords(inviterGuid, offset, limit);

        return CollectionUtils.isEmpty(entities) ? Collections.emptyList() : entities;
    }

    @Override
    public AssistRecordEntity latestAssistRecord() {
        return assistRecordRepo.latestAssistRecord(inviterGuid);
    }

}