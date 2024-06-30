/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.dao.SkuStorageChangeLogRepo;
import com.shinnlove.springbootall.db.po.SkuStorageChangeLogEntity;
import com.shinnlove.springbootall.exceptions.BusinessCode;
import com.shinnlove.springbootall.exceptions.DBAccessThrowException;
import com.shinnlove.springbootall.exceptions.DBExecuteReturnException;
import com.shinnlove.springbootall.service.SkuStorageChangeLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author Tony Zhao
 * @version $Id: SkuStorageChangeLogServiceImpl.java, v 0.1 2024-06-30 21:04 Tony Zhao Exp $$
 */
@Service
public class SkuStorageChangeLogServiceImpl implements SkuStorageChangeLogService {

    private static final Logger logger = LoggerFactory.getLogger(SkuStorageChangeLogServiceImpl.class);

    @Resource
    private SkuStorageChangeLogRepo skuStorageChangeLogRepo;

    /**
     * Query all storage change record by given selectId.
     *
     * @param activityId
     * @param componentId
     * @param selectId
     * @return
     */
    public SkuStorageChangeLogEntity queryStorageChangeLogBySelectId(String activityId, Long componentId, Long selectId) {
        SkuStorageChangeLogEntity entity = skuStorageChangeLogRepo.queryStorageChangeLogBySelectId(activityId, componentId, selectId);
        return Objects.isNull(entity) ? null : entity;
    }

    /**
     * Record any storage change by selectId.
     *
     * @param activityId
     * @param componentId
     * @param itemId
     * @param selectId
     * @param guid
     * @param changeType
     * @param changeNum
     * @return
     * @throws DBAccessThrowException
     * @throws DBExecuteReturnException
     */
    public long addStorageChangeLog(String activityId, long componentId, long itemId, long selectId, long guid,
                                    int changeType, int changeNum) throws DBAccessThrowException, DBExecuteReturnException {

        SkuStorageChangeLogEntity entity = new SkuStorageChangeLogEntity();

        entity.setActivityId(activityId);
        entity.setComponentId(componentId);
        entity.setItemId(itemId);
        entity.setSelectId(selectId);
        entity.setGuid(guid);
        entity.setChangeType(changeType);
        entity.setChangeNum(changeNum);

        long result = 0L;
        try {
            result = skuStorageChangeLogRepo.insertSelective(entity);
        } catch (Exception e) {
            logger.error("SkuStorageChangeDetailService addStorageChangeRecord, ex: {}", e.getMessage());
            throw new DBAccessThrowException(BusinessCode.DB_ACCESS_THROW_ERROR, e);
        }

        if (result <= 0) {
            throw new DBExecuteReturnException(BusinessCode.DB_EXECUTE_RETURN_ERROR);
        }

        return entity.getId();
    }

}