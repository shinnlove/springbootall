/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.service.RedisService;

/**
 * @author Tony Zhao
 * @version $Id: RedisServiceImpl.java, v 0.1 2022-01-09 3:02 PM Tony Zhao Exp $$
 */
public class RedisServiceImpl implements RedisService {

    @Override
    public String getValueByKey(String key) {
        return "hello";
    }

}