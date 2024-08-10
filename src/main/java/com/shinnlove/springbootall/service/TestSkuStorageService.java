/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;

import com.shinnlove.springbootall.db.po.SkuStorageEntity;
import com.shinnlove.springbootall.exceptions.DBAccessThrowException;
import com.shinnlove.springbootall.exceptions.DBExecuteReturnException;

/**
 * @author Tony Zhao
 * @version $Id: TestSkuStorageService.java, v 0.1 2024-07-01 15:11 Tony Zhao Exp $$
 */
public interface TestSkuStorageService {

    long initItemStorage() throws DBAccessThrowException, DBExecuteReturnException;

    SkuStorageEntity queryStorageByItemId();

    int updateLockNum();

    int updateSellNum();

}
