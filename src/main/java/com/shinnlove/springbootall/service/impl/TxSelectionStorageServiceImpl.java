/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.po.SkuSelectionEntity;
import com.shinnlove.springbootall.db.po.SkuStorageDeDupLockEntity;
import com.shinnlove.springbootall.enums.SelectionPayStatusEnum;
import com.shinnlove.springbootall.enums.StorageChangeTypeEnum;
import com.shinnlove.springbootall.exceptions.BusinessCode;
import com.shinnlove.springbootall.exceptions.DBExecuteReturnException;
import com.shinnlove.springbootall.exceptions.TxExecuteException;
import com.shinnlove.springbootall.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author Tony Zhao
 * @version $Id: TxSelectionStorageServiceImpl.java, v 0.1 2024-06-30 21:15 Tony Zhao Exp $$
 */
@Service
public class TxSelectionStorageServiceImpl implements TxSelectionStorageService {


    /** 用户选择商品仓储 */
    @Resource
    private SkuSelectionService skuSelectionService;

    /** 库存仓储服务 */
    @Resource
    private SkuStorageService skuStorageService;

    /** 库存锁仓储服务 */
    @Resource
    private SkuStorageDeDupLockService skuStorageDeDupLockService;

    /** 库存变更日志仓储服务 */
    @Resource
    private SkuStorageChangeLogService skuStorageChangeLogService;

    /** 事务模板 */
    @Resource
    private TransactionTemplate transactionTemplate;

    /**
     * @see TxSelectionStorageService#txLockSelectStorageOnce4Pay(String, Long, Long, Long, Long, Integer)
     */
    @Override
    public long txLockSelectStorageOnce4Pay(String activityId, Long componentId, Long itemId, Long selectId, Long guid, Integer changeNum) {

        int selectToPayStatus = SelectionPayStatusEnum.PAYING.getCode();

        // case 1: need lock the item storage(new)
        // case 2: if StorageChangeTypeEnum.STORAGE_RETURNED, need update change type again...
        final int changeType = StorageChangeTypeEnum.STORAGE_LOCKED.getCode();

        // first query existed storage lock
        SkuStorageDeDupLockEntity entity = skuStorageDeDupLockService
                .queryStorageLockBySelectIdWithoutChangeType(activityId, componentId, selectId);
        if (Objects.isNull(entity)) {
            return tx(status -> {
                // 1st, lock selection, change status for pay
                skuSelectionService.updateSelectionPayStatus(activityId, componentId, selectId, selectToPayStatus);

                // lock ok, deduct storage and add a log
                skuStorageService.updateLockNum(activityId, componentId, itemId, changeNum);

                // add a change log
                skuStorageChangeLogService.addStorageChangeLog(activityId, componentId, itemId, selectId, guid, changeType, changeNum);

                // need insert a record
                return skuStorageDeDupLockService.insertLockRecord(activityId, componentId, itemId, selectId, guid, changeType, changeNum);
            });
        }

        if (StorageChangeTypeEnum.STORAGE_SOLD.getCode() == entity.getChangeType()) {
            // cannot lock again for one selectId, lock failed
            throw new DBExecuteReturnException(BusinessCode.DB_LOCK_STATUS_ERROR);
        }

        if (StorageChangeTypeEnum.STORAGE_LOCKED.getCode() == entity.getChangeType()) {
            // already locked
            return 1L;
        }

        // only lock the amount when the first time user created order to pay...
        return tx(status -> {
            // add a change log
            skuStorageChangeLogService.addStorageChangeLog(activityId, componentId, itemId, selectId, guid, changeType, changeNum);

            // don't need to lock again, just update the change type
            return (long) skuStorageDeDupLockService.updateSelectionStorageLockStatus(activityId, componentId, selectId, changeType);
        });
    }

    /**
     * @see TxSelectionStorageService#txPaidSelectStorage(String, Long, Long, Long, Long, Integer)
     */
    @Override
    public int txPaidSelectStorage(String activityId, Long componentId, Long itemId, Long selectId, Long guid,
                                   Integer changeNum) throws TxExecuteException {

        final int selectPayStatus = SelectionPayStatusEnum.PAID.getCode();
        final int changeType = StorageChangeTypeEnum.STORAGE_SOLD.getCode();

        return tx(status -> {

            // query for update
            SkuSelectionEntity selection = skuSelectionService.querySelectByIdForUpdate(activityId, componentId, selectId);
            if (SelectionPayStatusEnum.PAID.getCode() == selection.getPayStatus()) {
                throw new TxExecuteException(BusinessCode.SELECT_HAS_PAID);
            }

            // update selection status
            skuSelectionService.updateSelectionPayStatus(activityId, componentId, selectId, selectPayStatus);

            // update item storage sell num
            skuStorageService.updateSellNum(activityId, componentId, itemId, changeNum);

            // update lock status
            skuStorageDeDupLockService.updateSelectionStorageLockStatus(activityId, componentId, selectId, changeType);

            // add storage change log
            skuStorageChangeLogService.addStorageChangeLog(activityId, componentId, itemId, selectId, guid, changeType, changeNum);

            return 1;
        });
    }

    private void txn(Consumer<TransactionStatus> f) {
        transactionTemplate.executeWithoutResult(f);
    }

    private <T> T tx(TransactionCallback<T> callback) {
        return transactionTemplate.execute(callback);
    }

}