/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.po.SkuStorageEntity;
import com.shinnlove.springbootall.enums.SelectionPayStatusEnum;
import com.shinnlove.springbootall.exceptions.DBAccessThrowException;
import com.shinnlove.springbootall.exceptions.DBExecuteReturnException;
import com.shinnlove.springbootall.service.SkuStorageService;
import com.shinnlove.springbootall.service.TestSkuStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tony Zhao
 * @version $Id: TestSkuStorageServiceImpl.java, v 0.1 2024-07-01 15:11 Tony Zhao Exp $$
 */
@Service
public class TestSkuStorageServiceImpl implements TestSkuStorageService {

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
    private SkuStorageService skuStorageService;

    @Override
    public long initItemStorage() throws DBAccessThrowException, DBExecuteReturnException {
        return skuStorageService.initItemStorage(activityId, componentId, itemId);
    }

    @Override
    public SkuStorageEntity queryStorageByItemId() {
        return null;
    }

    @Override
    public int updateLockNum() {
        return 0;
    }

    @Override
    public int updateSellNum() {
        return 0;
    }

}