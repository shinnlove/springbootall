/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.dao.AssistDeductCodeRepo;
import com.shinnlove.springbootall.db.po.AssistDeductCodeEntity;
import com.shinnlove.springbootall.enums.ValidStatusEnum;
import com.shinnlove.springbootall.exceptions.BusinessCode;
import com.shinnlove.springbootall.exceptions.DBAccessThrowException;
import com.shinnlove.springbootall.exceptions.DBExecuteReturnException;
import com.shinnlove.springbootall.service.AssistDeductCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author Tony Zhao
 * @version $Id: AssistDeductCodeServiceImpl.java, v 0.1 2024-06-30 19:50 Tony Zhao Exp $$
 */
@Service
public class AssistDeductCodeServiceImpl implements AssistDeductCodeService {

    private static Logger logger = LoggerFactory.getLogger(AssistDeductCodeServiceImpl.class);

    @Resource
    private AssistDeductCodeRepo    assistDeductCodeRepo;

    public long insertDeductCode(String activityId, Long componentId, Long gatherGuid, Long selectId, Long itemId,
                                 String assistCode) throws DBAccessThrowException, DBExecuteReturnException {

        AssistDeductCodeEntity entity = new AssistDeductCodeEntity();
        entity.setActivityId(activityId);
        entity.setComponentId(componentId);
        entity.setGatherGuid(gatherGuid);
        entity.setSelectId(selectId);
        entity.setItemId(itemId);
        entity.setAssistCode(assistCode);
        entity.setValidStatus(ValidStatusEnum.VALID.getCode());

        long result = 0L;
        try {
            result = assistDeductCodeRepo.insertSelective(entity);
        } catch (Exception e) {
            logger.error("AssistDeductCodeService insertDeductCode has error, ex=" + e.getMessage(), e);
            throw new DBAccessThrowException(BusinessCode.FAIL, e);
        }

        if (result <= 0) {
            throw new DBExecuteReturnException(BusinessCode.FAIL);
        }

        return entity.getId();
    }

    public AssistDeductCodeEntity queryByAssistCode(String activityId, Long componentId, String assistCode) {
        AssistDeductCodeEntity entity = assistDeductCodeRepo.queryByAssistCode(activityId, componentId, assistCode);
        return Objects.isNull(entity) ? null : entity;
    }

    public AssistDeductCodeEntity queryBySelectId(String activityId, Long componentId, Long selectId) {
        AssistDeductCodeEntity entity = assistDeductCodeRepo.queryBySelectId(activityId, componentId, selectId);
        return Objects.isNull(entity) ? null : entity;
    }

}