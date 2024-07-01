/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.enums.SelectionPayStatusEnum;
import com.shinnlove.springbootall.exceptions.DBAccessThrowException;
import com.shinnlove.springbootall.exceptions.DBExecuteReturnException;
import com.shinnlove.springbootall.service.TestTxSelectionInitCancelService;
import com.shinnlove.springbootall.service.TxSelectionInitCancelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tony Zhao
 * @version $Id: TestTxSelectionInitCancelServiceImpl.java, v 0.1 2024-07-01 14:38 Tony Zhao Exp $$
 */
@Service
public class TestTxSelectionInitCancelServiceImpl implements TestTxSelectionInitCancelService {

    private static final String activityId = "12345678";

    private static final long componentId = 234567L;

    private static final long guid = 888888L;

    private static final long itemId = 1L;
    private static final String itemName = "测试商品名";
    private static final String itemDesc = "测试商品描述";
    private static final String itemImgUrl = "http://img.baidu.com/13451245/alist/1.jpg";

    private static final int priceSnapshot = 100;
    private static final int lowestPriceSnapshot = 90;

    private static final long ddl1stTime = 13458987654L;
    private static final int payStatus = SelectionPayStatusEnum.READY_TO_PAY.getCode();

    @Autowired
    private TxSelectionInitCancelService txSelectionInitCancelService;

    @Override
    public long txGenerateSelectionWithDeduct() throws DBAccessThrowException, DBExecuteReturnException {
        return 0L;
    }

    @Override
    public int txCancelSelectionAndReturnStorage(long selectId) throws DBAccessThrowException, DBExecuteReturnException {
        return txSelectionInitCancelService
                .txCancelSelectionAndReturnStorage(activityId, guid, componentId, selectId, itemId);
    }

}