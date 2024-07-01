/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.po.SkuStorageDeDupLockEntity;
import com.shinnlove.springbootall.enums.DeductHelpTypeEnum;
import com.shinnlove.springbootall.enums.SelectionPayStatusEnum;
import com.shinnlove.springbootall.enums.StorageChangeTypeEnum;
import com.shinnlove.springbootall.enums.ValidStatusEnum;
import com.shinnlove.springbootall.exceptions.DBAccessThrowException;
import com.shinnlove.springbootall.exceptions.DBExecuteReturnException;
import com.shinnlove.springbootall.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.function.Consumer;


/**
 * 创建选择和取消选择的事务服务实现。
 *
 * @author Tony Zhao
 * @version $Id: TxSelectionInitCancelServiceImpl.java, v 0.1 2024-06-25 14:54 Tony Zhao Exp $$
 */
@Service
public class TxSelectionInitCancelServiceImpl implements TxSelectionInitCancelService {

    private static final Logger logger = LoggerFactory.getLogger(TxSelectionInitCancelServiceImpl.class);

    /** 用户选择商品仓储 */
    @Resource
    private SkuSelectionService skuSelectionService;

    /** 库存变更仓储 */
    @Resource
    private SkuStorageService skuStorageService;

    /** 幂等库存锁去重服务 */
    @Resource
    private SkuStorageDeDupLockService skuStorageDeDupLockService;

    /** 库存变更日志表 */
    @Resource
    private SkuStorageChangeLogService skuStorageChangeLogService;

    /** 选择助力减价仓储(系统减价用) */
    @Resource
    private AssistDeductDetailService   assistDeductDetailService;

    /** 事务模板 */
    @Resource
    private TransactionTemplate transactionTemplate;

    /**
     * @see TxSelectionInitCancelService#txGenerateSelectionWithDeduct(String, ServerHttpRequest, long, long, long, String, String, String, int, int, long, int)
     */
    @Override
    public long txGenerateSelectionWithDeduct(String activityId, ServerHttpRequest request,
                                              long componentId, long guid, long itemId, String itemName, String itemDesc,
                                              String itemImgUrl, int originalPrice, int lowestPrice,
                                              long ddl1stTime, int systemDeductPrice) throws DBAccessThrowException, DBExecuteReturnException {

        // 生成selection的时候就可以当前价格直接带走商品了
        final int payStatus = SelectionPayStatusEnum.READY_TO_PAY.getCode();
        // 还要附带一条系统助力记录
        final int helpType = DeductHelpTypeEnum.SYSTEM_RANDOM_DEDUCT.getCode();

        return tx(status -> {
            final long selectId = skuSelectionService.addItemSelection(activityId, componentId, guid,
                    itemId, itemName, itemDesc, itemImgUrl, originalPrice, lowestPrice, ddl1stTime, payStatus);

            assistDeductDetailService.insertDeductDetail(activityId, componentId, selectId, helpType,
                    guid, -1L, systemDeductPrice, systemDeductPrice);

            return selectId;
        });
    }

    /**
     * @see TxSelectionInitCancelService#txCancelSelectionAndReturnStorage(String, long, long, long, long)
     */
    @Override
    public int txCancelSelectionAndReturnStorage(String activityId, long guid, long componentId,
                                                 long selectId, long itemId) throws DBAccessThrowException, DBExecuteReturnException {

        final int validStatus = ValidStatusEnum.INVALID.getCode();
        // 锁定的状态
        final int previousChangeType = StorageChangeTypeEnum.STORAGE_LOCKED.getCode();
        // 返还的状态
        final int returnChangeType = StorageChangeTypeEnum.STORAGE_RETURNED.getCode();

        return tx(status -> {

            // 1st. lock this selection
            skuSelectionService.querySelectByIdForUpdate(activityId, componentId, selectId);

            // 2nd. update selection's invalid status
            skuSelectionService.updateSelectionValidStatus(activityId, componentId, selectId, validStatus);

            // 3rd. judge whether this selection has storage lock...
            SkuStorageDeDupLockEntity deDupLock = skuStorageDeDupLockService
                    .queryStorageLockBySelectIdWithChangeType(activityId, componentId, selectId, previousChangeType);

            if (Objects.nonNull(deDupLock)) {

                // has lock, should update lock status and release lock storage num...
                int returnNum = deDupLock.getChangeNum();

                // 4th. update lock record status
                skuStorageDeDupLockService
                        .updateSelectionStorageLockStatus(activityId, componentId, selectId, returnChangeType);

                // 5th. return total storage number
                skuStorageService.updateLockNum(activityId, componentId, itemId, -returnNum);

                // 6th. add storage change log
                skuStorageChangeLogService
                        .addStorageChangeLog(activityId, componentId, itemId, selectId, guid, returnChangeType, returnNum);
            }

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