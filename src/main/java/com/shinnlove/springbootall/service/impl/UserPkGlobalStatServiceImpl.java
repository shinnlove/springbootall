/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.dao.UserPkGlobalStatRepo;
import com.shinnlove.springbootall.service.UserPkGlobalStatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tony Zhao
 * @version $Id: UserPkGlobalStatServiceImpl.java, v 0.1 2024-04-16 11:21 Tony Zhao Exp $$
 */
@Service
public class UserPkGlobalStatServiceImpl implements UserPkGlobalStatService {

    private static final Logger logger = LoggerFactory.getLogger(UserPkGlobalStatServiceImpl.class);

    @Autowired
    private UserPkGlobalStatRepo userPkGlobalStatRepo;

    @Override
    public long insertUserGlobalStat() {
        return 0;
    }

}