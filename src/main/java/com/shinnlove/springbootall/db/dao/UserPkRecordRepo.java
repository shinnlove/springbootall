/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.db.dao;

import com.shinnlove.springbootall.db.po.UserPkRecordEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: UserPkRecordRepo.java, v 0.1 2024-04-15 14:40 Tony Zhao Exp $$
 */
@Repository
public interface UserPkRecordRepo {

    long save(@Param("tableName") String tableName,
              @Param(value = "entity") UserPkRecordEntity entity);

    List<UserPkRecordEntity> queryRecordByGuidAndTime(@Param("tableName") String tableName,
                                                      @Param(value = "activityId") String activityId,
                                                      @Param("guid") Long guid,
                                                      @Param("startTime") Timestamp startTime,
                                                      @Param("endTime") Timestamp endTime);

}
