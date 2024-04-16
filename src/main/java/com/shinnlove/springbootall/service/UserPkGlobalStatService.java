/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;

import com.shinnlove.springbootall.db.po.UserPkGlobalStatEntity;

import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: UserPkGlobalStatService.java, v 0.1 2024-04-16 11:21 Tony Zhao Exp $$
 */
public interface UserPkGlobalStatService {

    long insertUserGlobalStat();

    UserPkGlobalStatEntity queryUserGlobalStat();

    Integer updateGlobalSuccess();

    Integer updateGlobalSuccessWithPromotionLevel();

    Integer updateGlobalFailure();

    Integer updateGlobalDraw();

}
