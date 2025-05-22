/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.alibaba.fastjson.JSON;
import com.shinnlove.springbootall.service.DataChangeInterface;
import org.springframework.stereotype.Service;

/**
 * @author Tony Zhao
 * @version $Id: DataChangeInterfaceImpl.java, v 0.1 2025-04-11 5:09 PM Tony Zhao Exp $$
 */
@Service
public class DataChangeInterfaceImpl implements DataChangeInterface {

    @Override
    public <T> int change(String tableName, T afterData, Class<T> clazz) {
        T beforeData = queryExistingData(tableName, clazz);

        return doChange(tableName, beforeData, afterData);
    }

    @Override
    public <T> T queryExistingData(String tableName, Class<T> clazz) {

        T existingData = queryDataFromDB();

        return existingData;
    }

    @Override
    public <T> int storeChangeLog(T beforeData, T afterData) {
        String before = JSON.toJSONString(beforeData);
        String after = JSON.toJSONString(afterData);

        // add logs here...

        // add database here...

        return 0;
    }

    private <T> T queryDataFromDB() {
        // 这里每个仓储自己实现，返回对应的领域模型对象
        return (T) null;
    }

    @Override
    public <T> int doChange(String tableName, T beforeData, T afterData) {
        return 0;
    }

}