/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.dao.SkuStorageRepo;
import com.shinnlove.springbootall.db.po.SkuStorageEntity;
import com.shinnlove.springbootall.exceptions.BusinessCode;
import com.shinnlove.springbootall.exceptions.DBAccessThrowException;
import com.shinnlove.springbootall.exceptions.DBExecuteReturnException;
import com.shinnlove.springbootall.service.SkuStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Tony Zhao
 * @version $Id: SkuStorageServiceImpl.java, v 0.1 2024-06-30 21:09 Tony Zhao Exp $$
 */
@Service
public class SkuStorageServiceImpl implements SkuStorageService {

    private static final Logger logger = LoggerFactory.getLogger(SkuStorageServiceImpl.class);

    /** 库存仓库服务 */
    @Resource
    private SkuStorageRepo skuStorageRepo;

    public long initItemStorage(String activityId, long componentId, long itemId) throws DBAccessThrowException, DBExecuteReturnException {

        SkuStorageEntity entity = new SkuStorageEntity();

        entity.setActivityId(activityId);
        entity.setComponentId(componentId);
        entity.setItemId(itemId);
        entity.setLockNum(0);
        entity.setSellNum(0);

        long result = 0L;
        try {
            result = skuStorageRepo.insertSelective(entity);
        } catch (Exception e) {
            logger.error("SkuStorageService initItemStorage, ex=" + e.getMessage(), e);
            throw new DBAccessThrowException(BusinessCode.DB_ACCESS_THROW_ERROR, e);
        }

        if (result <= 0) {
            throw new DBExecuteReturnException(BusinessCode.DB_EXECUTE_RETURN_ERROR);
        }

        return entity.getId();
    }

    public SkuStorageEntity queryStorageByItemId(String activityId, long componentId, long itemId) {
        return skuStorageRepo.queryStorageByItemId(activityId, componentId, itemId);
    }

    public int updateLockNum(String activityId, long componentId, long itemId, int lockNum) {

        int result = 0;

        try {
            result = skuStorageRepo.updateLockNum(activityId, componentId, itemId, lockNum);
        } catch (Exception e) {
            logger.error("SkuStorageService updateLockNum, ex: {}", e.getMessage());
            throw new DBAccessThrowException(BusinessCode.DB_ACCESS_THROW_ERROR, e);
        }

        if (result <= 0) {
            throw new DBExecuteReturnException(BusinessCode.DB_EXECUTE_RETURN_ERROR);
        }

        return result;
    }

    public int updateSellNum(String activityId, long componentId, long itemId, int sellNum) {

        int result = 0;

        try {
            result = skuStorageRepo.updateSellNum(activityId, componentId, itemId, sellNum);
        } catch (Exception e) {
            logger.error("SkuStorageService updateSellNum, ex: {}", e.getMessage());
            throw new DBAccessThrowException(BusinessCode.DB_ACCESS_THROW_ERROR, e);
        }

        if (result <= 0) {
            throw new DBExecuteReturnException(BusinessCode.DB_EXECUTE_RETURN_ERROR);
        }

        return result;
    }

}