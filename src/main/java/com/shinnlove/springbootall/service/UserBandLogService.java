/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;

import com.shinnlove.springbootall.db.po.UserBandLogExtEntity;

import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: UserBandLogService.java, v 0.1 2024-06-04 20:14 Tony Zhao Exp $$
 */
public interface UserBandLogService {

    List<UserBandLogExtEntity> queryUserMaxBandsWithTime();

}
