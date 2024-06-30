/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.dao.ItemSystemDeductRepo;
import com.shinnlove.springbootall.db.po.ItemSystemDeductEntity;
import com.shinnlove.springbootall.exceptions.BusinessCode;
import com.shinnlove.springbootall.exceptions.DBAccessThrowException;
import com.shinnlove.springbootall.exceptions.DBExecuteReturnException;
import com.shinnlove.springbootall.service.ItemSystemDeductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author Tony Zhao
 * @version $Id: ItemSystemDeductServiceImpl.java, v 0.1 2024-06-30 20:11 Tony Zhao Exp $$
 */
@Service
public class ItemSystemDeductServiceImpl implements ItemSystemDeductService {

    private Logger logger = LoggerFactory.getLogger(ItemSystemDeductServiceImpl.class);

    @Resource
    private ItemSystemDeductRepo itemSystemDeductRepo;

    public long addSystemDeduct(String activityId, Long componentId, Long itemId, Long guid,
                                Integer systemDeductPrice) {
        // assemble info
        ItemSystemDeductEntity entity = new ItemSystemDeductEntity();
        entity.setActivityId(activityId);
        entity.setComponentId(componentId);
        entity.setItemId(itemId);
        entity.setGuid(guid);
        entity.setSystemDeductPrice(systemDeductPrice);

        long result = 0L;
        try {
            result = itemSystemDeductRepo.insertSelective(entity);
        } catch (Exception e) {
            logger.error("ItemSystemDeductService addSystemDeduct, ex=" + e.getMessage(), e);
            throw new DBAccessThrowException(BusinessCode.DB_ACCESS_THROW_ERROR, e);
        }

        if (result <= 0) {
            throw new DBExecuteReturnException(BusinessCode.DB_EXECUTE_RETURN_ERROR);
        }

        return entity.getId();
    }

    public ItemSystemDeductEntity queryByDeductId(String activityId, Long componentId, Long deductId) {
        ItemSystemDeductEntity entity = itemSystemDeductRepo.queryByDeductId(activityId, componentId, deductId);
        return Objects.isNull(entity) ? null : entity;
    }

}