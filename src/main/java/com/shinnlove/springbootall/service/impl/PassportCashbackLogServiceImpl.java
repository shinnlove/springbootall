/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.dao.PassportCashbackLogRepo;
import com.shinnlove.springbootall.db.po.PassportCashbackLogEntity;
import com.shinnlove.springbootall.exceptions.BusinessCode;
import com.shinnlove.springbootall.exceptions.DBAccessThrowException;
import com.shinnlove.springbootall.exceptions.DBExecuteReturnException;
import com.shinnlove.springbootall.service.PassportCashbackLogService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: PassportCashbackLogServiceImpl.java, v 0.1 2024-12-09 20:19 Tony Zhao Exp $$
 */
@Service
public class PassportCashbackLogServiceImpl implements PassportCashbackLogService {

    private static final Logger logger = LoggerFactory.getLogger(PassportCashbackLogServiceImpl.class);

    private static final String activityId = "53214567";
    private static final Long componentId = 147621L;
    private static final Long guid2 = 666888L;
    private static final String actionQueries = "#A2015,#A2016,#A2017";

    @Resource
    private PassportCashbackLogRepo passportCashbackLogRepo;

    @Override
    public long insertCashbackLog(int packageType, int memberGearType, int hasSent) {
        PassportCashbackLogEntity entity = PassportCashbackLogEntity.builder()
                .activityId(activityId)
                .componentId(componentId)
                .guid(guid2)
                .packageType(packageType)
                .memberGearType(memberGearType)
                .actionQueries(actionQueries)
                .hasSent(hasSent)
                .build();

        long result = 0L;

        try {
            result = passportCashbackLogRepo.insertSelective(entity);
        } catch (Exception e) {
            logger.error("[insertCashbackLog] init cashback access failed, ex=" + e.getMessage(), e);
            throw new DBAccessThrowException(BusinessCode.DB_ACCESS_THROW_ERROR, e);
        }

        if (result <= 0L) {
            logger.error("[insertCashbackLog] init cashback execute failed.");
            throw new DBExecuteReturnException(BusinessCode.DB_EXECUTE_RETURN_ERROR);
        }

        return entity.getId();
    }

    @Override
    public List<PassportCashbackLogEntity> queryPassportCashbackLogs(Long guid) {
        List<PassportCashbackLogEntity> entities = passportCashbackLogRepo
                .queryPassportCashbackLogs(activityId, componentId, guid);
        return CollectionUtils.isEmpty(entities) ? Collections.emptyList() : entities;
    }

}