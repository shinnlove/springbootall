/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.po.SkuSelectionEntity;
import com.shinnlove.springbootall.exceptions.DBAccessThrowException;
import com.shinnlove.springbootall.exceptions.DBExecuteReturnException;
import com.shinnlove.springbootall.exceptions.TxExecuteException;
import com.shinnlove.springbootall.service.SkuSelectionService;
import com.shinnlove.springbootall.service.TxPaySelectionService;
import com.shinnlove.springbootall.service.TxSelectionStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Tony Zhao
 * @version $Id: TxPaySelectionServiceImpl.java, v 0.1 2024-06-30 21:30 Tony Zhao Exp $$
 */

/**
 * A service implementation of transaction changing selection status and sku storage .
 *
 * @author Tony Zhao
 * @version $Id: TxPaySelectionServiceImpl.java, v 0.1 2024-06-13 10:38 Tony Zhao Exp $$
 */
@Service
public class TxPaySelectionServiceImpl implements TxPaySelectionService {

    private static final Logger logger = LoggerFactory.getLogger(TxPaySelectionServiceImpl.class);

    @Resource
    private SkuSelectionService skuSelectionService;

    /** 处理库存的事务型服务 */
    @Resource
    private TxSelectionStorageService txSelectionStorageService;

    /**
     * @see TxPaySelectionService#txPaySelectionAndLockStorageOnce(String, ServerHttpRequest, long, long)
     */
    @Override
    public int txPaySelectionAndLockStorageOnce(String activityId, ServerHttpRequest request, long componentId,
                                                long selectId) throws DBAccessThrowException, DBExecuteReturnException {

        // fetch reference id by select id
        SkuSelectionEntity selection = skuSelectionService.queryBySelectId(activityId, componentId, selectId);

        long guid = selection.getGuid();
        long itemId = selection.getItemId();

        // todo: fetch from config => how many item numbers each order can buy
        int lockNum = 1;

        // check if current selection has locked any storage
        long lockResult = 0L;
        try {
            lockResult = txSelectionStorageService.txLockSelectStorageOnce4Pay(activityId, componentId, itemId, selectId, guid, lockNum);
        } catch (Exception e) {
            logger.error("update selection status failed, ex="+e.getMessage(), e);
            return 0;
        }

        if (lockResult <= 0) {
            return 0;
        }

        return 1;
    }

    /**
     * @see TxPaySelectionService#txUpdatePaidStatusAndSellNum(String, ServerHttpRequest, long, long)
     */
    @Override
    public int txUpdatePaidStatusAndSellNum(String activityId, ServerHttpRequest request, long componentId,
                                            long selectId) throws DBAccessThrowException, DBExecuteReturnException {

        // fetch reference id by select id
        SkuSelectionEntity selection = skuSelectionService.queryBySelectId(activityId, componentId, selectId);

        long guid = selection.getGuid();
        long itemId = selection.getItemId();
        // 读配置，一次购买几个
        int sellNum = 1;

        // update paid status, sell num and storage logs...
        int updateResult = 0;
        try {
            updateResult = txSelectionStorageService
                    .txPaidSelectStorage(activityId, componentId, itemId, selectId, guid, sellNum);
        } catch (TxExecuteException e) {
            logger.error("TxExecuteException: txUpdatePaidStatusAndSellNum failed, ex=" + e.getMessage(), e);
            return 0;
        } catch (Exception e) {
            logger.error("Exception: txUpdatePaidStatusAndSellNum failed, ex=" + e.getMessage(), e);
            return 0;
        }

        return updateResult;
    }

}