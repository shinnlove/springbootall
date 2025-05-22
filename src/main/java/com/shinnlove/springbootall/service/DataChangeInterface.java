/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;

/**
 * @author Tony Zhao
 * @version $Id: DataChangeInterface.java, v 0.1 2025-04-11 5:08 PM Tony Zhao Exp $$
 */
public interface DataChangeInterface {

    /**
     * @param tableName
     * @param afterData
     * @param clazz
     * @return
     * @param <T>
     */
    <T> int change(String tableName, T afterData, Class<T> clazz);

    /**
     * @param tableName
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T queryExistingData(String tableName, Class<T> clazz);

    /**
     * @param beforeData
     * @param afterData
     * @param <T>
     * @return
     */
    <T> int storeChangeLog(T beforeData, T afterData);

    /**
     * @param tableName
     * @param beforeData
     * @param afterData
     * @param <T>
     * @return
     */
    <T> int doChange(String tableName, T beforeData, T afterData);

}
