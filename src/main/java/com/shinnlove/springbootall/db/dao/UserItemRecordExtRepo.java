/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.db.dao;

import com.shinnlove.springbootall.db.po.ItemRecordEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: UserItemRecordExtRepo.java, v 0.1 2024-06-03 16:21 Tony Zhao Exp $$
 */
@Repository
public interface UserItemRecordExtRepo {

    long createUserItemRecord(@Param("tableName") String tableName,
                              @Param("entity") ItemRecordEntity entity);

    /**
     * 按消息的游标方式来查询用户的物品记录决定是否勾兑消息。
     *
     * @param tableName
     * @param activityId
     * @param messageSentVersion        当前的message发送版本，小于此版本的消息都应该被捞起来勾兑到当前版本消息。
     * @param lastRecordId
     * @param pageSize
     * @return
     */
    List<ItemRecordEntity> cursorQueryItemRecord(@Param(value = "tableName") String tableName,
                                                 @Param(value = "activityId") String activityId,
                                                 @Param(value = "messageSentVersion") Integer messageSentVersion,
                                                 @Param(value = "lastRecordId") Long lastRecordId,
                                                 @Param(value = "pageSize") Integer pageSize);

    /**
     * 根据消息id将消息版本勾兑成指定版本。
     *
     * @param tableName
     * @param id
     * @param messageSentVersion
     * @return
     */
    int updateItemRecordMsgVersion(@Param(value = "tableName") String tableName,
                                   @Param(value = "id") Long id,
                                   @Param(value = "messageSentVersion") Integer messageSentVersion);

}
