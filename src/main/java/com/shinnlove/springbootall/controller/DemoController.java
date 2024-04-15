/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.controller;

import com.shinnlove.springbootall.redis.RedisManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author Tony Zhao
 * @version $Id: DemoController.java, v 0.1 2024-04-15 10:22 Tony Zhao Exp $$
 */
@RestController
public class DemoController {

    private static Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private RedisManager redisManager;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello() {
        return "Hello world.";
    }

    // http://localhost:8080/testRedisSave?id=1090330
    @RequestMapping(value = "/testRedisSave", method = RequestMethod.GET)
    public String testRedisSave(Long id) {
        redisManager.del("userId:"+id);
        redisManager.set("userId:"+ id, "random value=" + UUID.randomUUID().toString());
        return "已经为userId" + id + "添加了随机值, 请在/testRedisGet?id=" + id + "中查看";
    }

    // http://localhost:8080/testRedisGet?id=1090330
    @RequestMapping(value = "/testRedisGet", method = RequestMethod.GET)
    public String testRedisGet(Long id) {
        String myStr = redisManager.get("userId:" + id);
        if(!StringUtils.isEmpty(myStr)) {
            return myStr;
        }
        return null;
    }

}