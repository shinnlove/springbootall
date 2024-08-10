/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.dao.SkuStorageDeDupLockRepo;
import com.shinnlove.springbootall.db.po.SkuStorageDeDupLockEntity;
import com.shinnlove.springbootall.exceptions.BusinessCode;
import com.shinnlove.springbootall.exceptions.DBAccessThrowException;
import com.shinnlove.springbootall.exceptions.DBExecuteReturnException;
import com.shinnlove.springbootall.service.SkuStorageDeDupLockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author Tony Zhao
 * @version $Id: SkuStorageDeDupLockServiceImpl.java, v 0.1 2024-06-30 21:06 Tony Zhao Exp $$
 */
@Service
public class SkuStorageDeDupLockServiceImpl implements SkuStorageDeDupLockService {

    private static final Logger logger = LoggerFactory.getLogger(SkuStorageDeDupLockServiceImpl.class);

    @Resource
    private SkuStorageDeDupLockRepo skuStorageDeDupLockRepo;

    public SkuStorageDeDupLockEntity queryStorageLockBySelectIdWithoutChangeType(String activityId, Long componentId, Long selectId) {
        SkuStorageDeDupLockEntity entity = skuStorageDeDupLockRepo
                .queryStorageLockBySelectIdWithoutChangeType(activityId, componentId, selectId);
        return Objects.isNull(entity) ? null : entity;
    }

    public SkuStorageDeDupLockEntity queryStorageLockBySelectIdWithChangeType(String activityId, Long componentId,
                                                                              Long selectId, Integer changeType) {
        SkuStorageDeDupLockEntity entity = skuStorageDeDupLockRepo
                .queryStorageLockBySelectIdWithChangeType(activityId, componentId, selectId, changeType);
        return Objects.isNull(entity) ? null : entity;
    }

    public long insertLockRecord(String activityId, Long componentId, Long itemId, Long selectId, Long guid,
                                 Integer changeType, Integer changeNum) {

        // assemble info here:

        SkuStorageDeDupLockEntity storageLock = new SkuStorageDeDupLockEntity();
        storageLock.setActivityId(activityId);
        storageLock.setComponentId(componentId);
        storageLock.setItemId(itemId);
        storageLock.setSelectId(selectId);
        storageLock.setGuid(guid);
        storageLock.setChangeType(changeType);
        storageLock.setChangeNum(changeNum);

        long result = 0L;

        try {
            result = skuStorageDeDupLockRepo.insertSelective(storageLock);
        } catch (Exception e) {
            logger.error("insertLockRecord error, ex=" + e.getMessage(), e);
            throw new DBAccessThrowException(BusinessCode.DB_ACCESS_THROW_ERROR, e);
        }

        if (result <= 0) {
            throw new DBExecuteReturnException(BusinessCode.DB_EXECUTE_RETURN_ERROR);
        }

        // need return the id of the record
        return storageLock.getId();
    }

    public int updateSelectionStorageLockStatus(String activityId, Long componentId, Long selectId, Integer changeType) {

        int result = 0;

        try {
            result = skuStorageDeDupLockRepo.updateSelectionStorageLockStatus(activityId, componentId, selectId, changeType);
        } catch (Exception e) {
            logger.error("updateSelectionStorageLockStatus error, ex=" + e.getMessage(), e);
            throw new DBAccessThrowException(BusinessCode.DB_ACCESS_THROW_ERROR, e);
        }

        if (result <= 0) {
            throw new DBExecuteReturnException(BusinessCode.DB_EXECUTE_RETURN_ERROR);
        }

        return result;
    }

}