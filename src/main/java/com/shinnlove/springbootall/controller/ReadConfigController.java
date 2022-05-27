/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shinnlove.springbootall.client.RedisClient;

/**
 * @author Tony Zhao
 * @version $Id: ReadConfigController.java, v 0.1 2022-05-27 2:44 PM Tony Zhao Exp $$
 */
@RestController
@RequestMapping("/config")
public class ReadConfigController {

    @Autowired
    private RedisClient redisClient;

    @RequestMapping(value = "/redis", method = RequestMethod.GET)
    public String readRedisConfig() {
        return redisClient.readConfig();
    }

}