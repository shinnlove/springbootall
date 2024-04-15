/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.controller;

import com.shinnlove.springbootall.business.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tony Zhao
 * @version $Id: TestRedisController.java, v 0.1 2024-04-15 11:13 Tony Zhao Exp $$
 */
@RestController
@RequestMapping(value = "/redis")
public class TestRedisController {

    @Autowired
    private RedisService redisService;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public Long testRedisInitLevel(Long guid, Integer startLevel) {
        return redisService.redisInitLevel(guid, startLevel);
    }

    @RequestMapping(value = "/promote", method = RequestMethod.GET)
    public Long testRedisPromote(Long guid, Integer updateLevel) {
        return redisService.redisPromote(guid, updateLevel);
    }

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public Long testRedisRandomOpponentGuid(Long selfGuid, Integer level) {
        return redisService.redisRandomOpponentGuid(selfGuid, level);
    }

}