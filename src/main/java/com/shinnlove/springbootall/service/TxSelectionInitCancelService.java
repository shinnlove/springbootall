/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;


import com.shinnlove.springbootall.exceptions.DBAccessThrowException;
import com.shinnlove.springbootall.exceptions.DBExecuteReturnException;
import org.springframework.http.server.reactive.ServerHttpRequest;

/**
 * 创建选择和取消选择的事务服务接口。
 *
 * @author Tony Zhao
 * @version $Id: TxSelectionInitCancelService.java, v 0.1 2024-06-25 14:53 Tony Zhao Exp $$
 */
public interface TxSelectionInitCancelService {

    /**
     * An outta interface for user generate selection.
     *
     * @param activityId
     * @param request
     * @param componentId
     * @param guid
     * @param itemId
     * @param itemName
     * @param itemDesc
     * @param itemImgUrl
     * @param originalPrice
     * @param lowestPrice
     * @param ddl1stTime
     * @param systemDeductPrice
     * @return
     * @throws DBAccessThrowException
     * @throws DBExecuteReturnException
     */
    long txGenerateSelectionWithDeduct(String activityId, ServerHttpRequest request,
                                       long componentId, long guid, long itemId, String itemName, String itemDesc,
                                       String itemImgUrl, int originalPrice, int lowestPrice,
                                       long ddl1stTime, int systemDeductPrice) throws DBAccessThrowException, DBExecuteReturnException;

    /**
     * An outta interface for cancel user current selection and return locked storage if available.
     *
     * @param activityId
     * @param guid
     * @param componentId
     * @param selectId
     * @param itemId
     * @return
     * @throws DBAccessThrowException
     * @throws DBExecuteReturnException
     */
    int txCancelSelectionAndReturnStorage(String activityId, long guid, long componentId, long selectId,
                                          long itemId) throws DBAccessThrowException, DBExecuteReturnException;

}