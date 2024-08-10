/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.dao.AssistDeductDetailRepo;
import com.shinnlove.springbootall.db.po.AggSelectionAssistEntity;
import com.shinnlove.springbootall.db.po.AssistDeductDetailEntity;
import com.shinnlove.springbootall.exceptions.BusinessCode;
import com.shinnlove.springbootall.exceptions.DBAccessThrowException;
import com.shinnlove.springbootall.exceptions.DBExecuteReturnException;
import com.shinnlove.springbootall.service.AssistDeductDetailService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author Tony Zhao
 * @version $Id: AssistDeductDetailServiceImpl.java, v 0.1 2024-06-30 20:02 Tony Zhao Exp $$
 */
@Service
public class AssistDeductDetailServiceImpl implements AssistDeductDetailService {

    private static final Logger logger = LoggerFactory.getLogger(AssistDeductDetailServiceImpl.class);

    @Resource
    private AssistDeductDetailRepo assistDeductDetailRepo;

    public long insertDeductDetail(String activityId, Long componentId, Long selectId,
                                   Integer helpType, Long gatherGuid, Long helpGuid, Integer randomHelpPrice,
                                   Integer realHelpPrice) throws DBAccessThrowException, DBExecuteReturnException {

        AssistDeductDetailEntity entity = new AssistDeductDetailEntity();
        entity.setActivityId(activityId);
        entity.setComponentId(componentId);
        entity.setSelectId(selectId);
        entity.setHelpType(helpType);
        entity.setGatherGuid(gatherGuid);
        entity.setHelpGuid(helpGuid);
        entity.setRandomHelpPrice(randomHelpPrice);
        entity.setRealHelpPrice(realHelpPrice);

        long result = 0L;
        try {
            result = assistDeductDetailRepo.insertSelective(entity);
        } catch (Exception e) {
            logger.error("AssistDeductDetailService insertDeductDetail has error, ex=" + e.getMessage(), e);
            throw new DBAccessThrowException(BusinessCode.FAIL, e);
        }

        if (result <= 0) {
            throw new DBExecuteReturnException(BusinessCode.FAIL);
        }

        // should return deduct id here...
        return entity.getId();
    }

    /**
     * 主页只显示人的助力记录，并且按助力价格倒序排列。
     *
     * @param activityId
     * @param componentId
     * @param selectId
     * @param helpType
     * @return
     */
    public List<AssistDeductDetailEntity> querySelectionDeductDetailIndex(String activityId, long componentId,
                                                                          long selectId, int helpType) {

        List<AssistDeductDetailEntity> pos = assistDeductDetailRepo
                .querySelectionDeductDetailIndex(activityId, componentId, selectId, helpType);

        if (CollectionUtils.isEmpty(pos)) {
            return Collections.emptyList();
        }

        return pos;
    }

    /**
     * 助力详情页，按照好友助力时间排序。
     *
     * @param activityId
     * @param componentId
     * @param selectId
     * @param helpType
     * @return
     */
    public List<AssistDeductDetailEntity> querySelectionDeductDetailHelp(String activityId, long componentId,
                                                                         long selectId, int helpType) {

        List<AssistDeductDetailEntity> pos = assistDeductDetailRepo
                .querySelectionDeductDetailHelp(activityId, componentId, selectId, helpType);

        if (CollectionUtils.isEmpty(pos)) {
            return Collections.emptyList();
        }

        return pos;
    }

    public List<AssistDeductDetailEntity> queryAssistByGuid(String activityId, long componentId, long guid) {
        List<AssistDeductDetailEntity> pos = assistDeductDetailRepo.queryAssistByGuid(activityId, componentId, guid);

        if (CollectionUtils.isEmpty(pos)) {
            return Collections.emptyList();
        }

        return pos;
    }

    public AggSelectionAssistEntity aggSelectionAssistDeductPrice(String activityId, long componentId, long selectId) {

        AggSelectionAssistEntity aggEntity = new AggSelectionAssistEntity();
        aggEntity.setActivityId(activityId);
        aggEntity.setComponentId(componentId);
        aggEntity.setSelectId(selectId);
        aggEntity.setGatherGuid(0L);
        aggEntity.setTotalRandomPrice(0);
        aggEntity.setTotalDeductPrice(0);

        AggSelectionAssistEntity queryAggResult = assistDeductDetailRepo
                .aggSelectionAssistDeductPrice(activityId, componentId, selectId);

        if (Objects.nonNull(queryAggResult)) {
            aggEntity.setGatherGuid(queryAggResult.getGatherGuid());
            aggEntity.setTotalRandomPrice(queryAggResult.getTotalRandomPrice());
            aggEntity.setTotalDeductPrice(queryAggResult.getTotalDeductPrice());
        }

        return aggEntity;
    }

}