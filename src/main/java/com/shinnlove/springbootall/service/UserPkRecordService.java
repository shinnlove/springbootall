/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;

import com.shinnlove.springbootall.db.po.UserPkRecordEntity;

import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: UserPkRecordService.java, v 0.1 2024-04-16 11:13 Tony Zhao Exp $$
 */
public interface UserPkRecordService {

    long save();

    List<UserPkRecordEntity> queryRecordByGuidAndTime();

}