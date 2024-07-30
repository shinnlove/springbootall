/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.alibaba.fastjson.JSON;
import com.shinnlove.springbootall.db.dao.ValidOrderMessageQueueLogRepo;
import com.shinnlove.springbootall.db.po.ValidOrderMessageQueueLogEntity;
import com.shinnlove.springbootall.enums.TradeMessageTypeEnum;
import com.shinnlove.springbootall.exceptions.BusinessCode;
import com.shinnlove.springbootall.exceptions.DBAccessThrowException;
import com.shinnlove.springbootall.exceptions.DBExecuteReturnException;
import com.shinnlove.springbootall.message.QdBuyMsgEntity;
import com.shinnlove.springbootall.service.ValidOrderMessageQueueLogService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author Tony Zhao
 * @version $Id: ValidOrderMessageQueueLogServiceImpl.java, v 0.1 2024-07-30 10:21 Tony Zhao Exp $$
 */
@Service
public class ValidOrderMessageQueueLogServiceImpl implements ValidOrderMessageQueueLogService {

    private static final Logger logger = LoggerFactory.getLogger(ValidOrderMessageQueueLogServiceImpl.class);

    private static final String activityId = "118368228";

    private static final Long componentId = 0L;

    private static final String queueName = "test-youzan-action-qdread-queue";

    private static final String orderId = "666666888888";

    private static final Long guid = 12345678L;

    private static String messageBody = "";

    static {
        QdBuyMsgEntity msg = new QdBuyMsgEntity();
        msg.setEventType(TradeMessageTypeEnum.TRADE_TRADE_PAID.getTypeKey());
        msg.setOrderId("666666888888");
        msg.setGuid(12345678L);

        try {
            messageBody = JSON.toJSONString(msg);
        } catch (Exception e) {

        }
    }

    @Autowired
    private ValidOrderMessageQueueLogRepo validOrderMessageQueueLogRepo;

    @Override
    public long saveOrderMessage() throws DBAccessThrowException, DBExecuteReturnException {
        // assemble
        ValidOrderMessageQueueLogEntity entity = new ValidOrderMessageQueueLogEntity();
        entity.setActivityId(activityId);
        entity.setComponentId(componentId);
        entity.setQueueName(queueName);
        entity.setMessageBody(messageBody);
        entity.setOrderId(orderId);
        entity.setGuid(guid);
        entity.setReconcileStatus(0);

        long result = 0L;
        try {
            result = validOrderMessageQueueLogRepo.insertSelective(entity);
        } catch (Exception e) {
            logger.error("ValidOrderMessageQueueLogService saveOrderMessage access error, ex=" + e.getMessage(), e);
            throw new DBAccessThrowException(BusinessCode.DB_ACCESS_THROW_ERROR, e);
        }

        if (result <= 0) {
            logger.error("ValidOrderMessageQueueLogService saveOrderMessage result error, result=" + result);
            throw new DBExecuteReturnException(BusinessCode.DB_EXECUTE_RETURN_ERROR);
        }

        return entity.getId();
    }

    @Override
    public List<ValidOrderMessageQueueLogEntity> queryMessageByGuid() {
        List<ValidOrderMessageQueueLogEntity> entities = validOrderMessageQueueLogRepo
                .queryMessageByGuid(activityId, componentId, guid);

        if (CollectionUtils.isEmpty(entities)) {
            return Collections.emptyList();
        }

        return entities;
    }

    @Override
    public ValidOrderMessageQueueLogEntity queryMessageByOrderId() {
        ValidOrderMessageQueueLogEntity entity = validOrderMessageQueueLogRepo
                .queryMessageByOrderId(activityId, componentId, orderId);

        return Objects.nonNull(entity) ? entity : null;
    }

    /**
     * @param reconcileStatus
     * @return
     * @throws DBAccessThrowException
     * @throws DBExecuteReturnException
     */
    public int updateMessageLogStatus(Integer reconcileStatus) throws DBAccessThrowException, DBExecuteReturnException {
        int result = 0;
        try {
            result = validOrderMessageQueueLogRepo
                    .updateReconcileStatusByOrderId(activityId, componentId, orderId, reconcileStatus);
        } catch (Exception e) {
            logger.error("updateMessageLogStatus access error, ex=" + e.getMessage(), e);
            throw new DBAccessThrowException(BusinessCode.DB_ACCESS_THROW_ERROR, e);
        }

        if (result <= 0) {
            logger.error("updateMessageLogStatus result error, result=" + result);
            throw new DBExecuteReturnException(BusinessCode.DB_EXECUTE_RETURN_ERROR);
        }

        return result;
    }

}