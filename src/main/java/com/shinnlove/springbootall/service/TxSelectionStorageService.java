/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;


import com.shinnlove.springbootall.exceptions.TxExecuteException;

/**
 * 事务型库存处理服务接口。。
 *
 * @author Tony Zhao
 * @version $Id: TxSelectionStorageService.java, v 0.1 2024-06-24 11:38 Tony Zhao Exp $$
 */
public interface TxSelectionStorageService {

    /**
     * VIP: Query and lock/re-lock the item id storage by given select id.
     *
     * 事务型 - 按选择商品记录锁定库存一次、并变更相关库存。
     *
     * 事务型锁定库存，做3个表的动作：
     * a) 先锁定库存
     * b) 或者减少库存，或者保持不动
     * c) 增加一条库存变更日志
     *
     * @param activityId
     * @param componentId
     * @param itemId
     * @param selectId
     * @param guid
     * @param changeNum
     * @return              1 or record id: locked successfully; 0: locked failed
     */
    long txLockSelectStorageOnce4Pay(String activityId, Long componentId, Long itemId, Long selectId, Long guid, Integer changeNum);

    /**
     * 事务型 - 收到支付回调消息后，更新商品选择记录与库存。
     *
     * @param activityId
     * @param componentId
     * @param itemId
     * @param selectId
     * @param guid
     * @param changeNum
     * @return
     * @throws TxExecuteException
     */
    int txPaidSelectStorage(String activityId, Long componentId, Long itemId, Long selectId, Long guid,
                            Integer changeNum) throws TxExecuteException;

}