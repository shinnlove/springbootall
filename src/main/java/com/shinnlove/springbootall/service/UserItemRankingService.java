/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;

import com.shinnlove.springbootall.db.po.UserItemRankingEntity;
import com.shinnlove.springbootall.models.PageResult;


/**
 * @author Tony Zhao
 * @version $Id: UserItemRankingService.java, v 0.1 2024-04-19 10:28 Tony Zhao Exp $$
 */
public interface UserItemRankingService {

    long insertUserItemRanking();

    PageResult<UserItemRankingEntity> pageQueryTopRanking(Integer pageIndex, Integer pageSize);

    long countCollectAllUser(Integer totalTypes);

    UserItemRankingEntity queryByActivityGuid(Long guid);

    Integer incItemTypeCount();

}
