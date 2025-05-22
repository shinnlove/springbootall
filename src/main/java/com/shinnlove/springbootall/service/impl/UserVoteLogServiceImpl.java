/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.dao.UserVoteLogRepo;
import com.shinnlove.springbootall.service.UserVoteLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tony Zhao
 * @version $Id: UserVoteLogServiceImpl.java, v 0.1 2024-09-04 17:16 Tony Zhao Exp $$
 */
@Service
public class UserVoteLogServiceImpl implements UserVoteLogService {

    @Autowired
    private UserVoteLogRepo userVoteLogRepo;

}