/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;

/**
 * @author Tony Zhao
 * @version $Id: RedisService.java, v 0.1 2022-01-09 3:01 PM Tony Zhao Exp $$
 */
public interface RedisService {

    /**
     * @param key 
     * @return
     */
    String getValueByKey(String key);

}
