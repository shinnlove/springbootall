/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.dao.SkuSelectionRepo;
import com.shinnlove.springbootall.db.po.SkuSelectionEntity;
import com.shinnlove.springbootall.enums.ValidStatusEnum;
import com.shinnlove.springbootall.exceptions.BusinessCode;
import com.shinnlove.springbootall.exceptions.DBAccessThrowException;
import com.shinnlove.springbootall.exceptions.DBExecuteReturnException;
import com.shinnlove.springbootall.service.SkuSelectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author Tony Zhao
 * @version $Id: SkuSelectionServiceImpl.java, v 0.1 2024-06-30 21:00 Tony Zhao Exp $$
 */
@Service
public class SkuSelectionServiceImpl implements SkuSelectionService {

    private static Logger logger = LoggerFactory.getLogger(SkuSelectionServiceImpl.class);

    @Resource
    private SkuSelectionRepo skuSelectionRepo;

    public long addItemSelection(String activityId, Long componentId, Long guid, Long itemId,
                                 String itemNameSnapshot, String itemDescSnapshot, String itemImgUrlSnapshot,
                                 int priceSnapshot, int lowestPriceSnapshot, long ddl1stTime, Integer payStatus) {
        // assemble info here:
        SkuSelectionEntity entity = new SkuSelectionEntity();
        entity.setActivityId(activityId);
        entity.setComponentId(componentId);
        entity.setGuid(guid);
        entity.setItemId(itemId);
        entity.setItemNameSnapshot(itemNameSnapshot);
        entity.setItemDescSnapshot(itemDescSnapshot);
        entity.setItemImgUrlSnapshot(itemImgUrlSnapshot);
        entity.setPriceSnapshot(priceSnapshot);
        entity.setLowestPriceSnapshot(lowestPriceSnapshot);
        entity.setDdl1stTime(ddl1stTime);
        // 先置为0L，代表没开始
        entity.setStart2ndTime(0L);
        entity.setDdl2ndTime(0L);
        entity.setValidStatus(ValidStatusEnum.VALID.getCode());
        entity.setPayStatus(payStatus);

        long result = 0L;
        try {
            result = skuSelectionRepo.insertSelective(entity);
        } catch (Exception e) {
            logger.error("insertLockRecord error, ex=" + e.getMessage(), e);
            throw new DBAccessThrowException(BusinessCode.DB_ACCESS_THROW_ERROR, e);
        }

        if (result <= 0) {
            throw new DBExecuteReturnException(BusinessCode.DB_EXECUTE_RETURN_ERROR);
        }

        // need return the id of the record
        return entity.getId();
    }

    public int updateSelectionPayStatus(String activityId, long componentId, long selectId,
                                        int payStatus) throws DBAccessThrowException, DBExecuteReturnException {

        int result = 0;

        try {
            result = skuSelectionRepo.updateSelectionPayStatus(activityId, componentId, selectId, payStatus);
        } catch (Exception e) {
            logger.error("SkuSelectionService updateSelectionPayStatus DBAccessThrowException, ex: {}", e.getMessage());
            throw new DBAccessThrowException(BusinessCode.DB_ACCESS_THROW_ERROR, e);
        }

        if (result <= 0) {
            throw new DBExecuteReturnException(BusinessCode.DB_EXECUTE_RETURN_ERROR);
        }

        return result;
    }

    public int updateSelectionValidStatus(String activityId, long componentId, long selectId,
                                          int validStatus) throws DBAccessThrowException, DBExecuteReturnException {

        int result = 0;

        try {
            result = skuSelectionRepo.updateSelectionValidStatus(activityId, componentId, selectId, validStatus);
        } catch (Exception e) {
            logger.error("SkuSelectionService updateSelectionValidStatus DBAccessThrowException, ex: {}", e.getMessage());
            throw new DBAccessThrowException(BusinessCode.DB_ACCESS_THROW_ERROR, e);
        }

        if (result <= 0) {
            throw new DBExecuteReturnException(BusinessCode.DB_EXECUTE_RETURN_ERROR);
        }

        return result;
    }

    public int updateSelection2ndStartEndTime(String activityId, long componentId, long selectId, long start2ndTime,
                                              long ddl2ndTime) throws DBAccessThrowException, DBExecuteReturnException {

        int result = 0;

        try {
            result = skuSelectionRepo
                    .updateSelection2ndStartEndTime(activityId, componentId, selectId, start2ndTime, ddl2ndTime);
        } catch (Exception e) {
            logger.error("SkuSelectionService updateSelection2ndStartEndTime DBAccessThrowException, ex: {}", e.getMessage());
            throw new DBAccessThrowException(BusinessCode.DB_ACCESS_THROW_ERROR, e);
        }

        if (result <= 0) {
            throw new DBExecuteReturnException(BusinessCode.DB_EXECUTE_RETURN_ERROR);
        }

        return result;
    }

    public SkuSelectionEntity queryCurrentSelection(String activityId, long componentId, long guid) {
        SkuSelectionEntity currentSelection = skuSelectionRepo.queryCurrentSelection(activityId, componentId, guid);
        return Objects.isNull(currentSelection) ? null : currentSelection;
    }

    public SkuSelectionEntity queryBySelectId(String activityId, long componentId, long selectId) {
        SkuSelectionEntity toPaySelection = skuSelectionRepo.querySelectById(activityId, componentId, selectId);
        return Objects.isNull(toPaySelection) ? null : toPaySelection;
    }

    public SkuSelectionEntity querySelectByIdForUpdate(String activityId, long componentId, long selectId) {
        SkuSelectionEntity toPaySelection = skuSelectionRepo.querySelectByIdForUpdate(activityId, componentId, selectId);
        return Objects.isNull(toPaySelection) ? null : toPaySelection;
    }

}