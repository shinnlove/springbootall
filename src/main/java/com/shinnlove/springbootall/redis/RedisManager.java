/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.function.Function;

/**
 * @author Tony Zhao
 * @version $Id: RedisManager.java, v 0.1 2024-04-15 10:53 Tony Zhao Exp $$
 */
@Component
public class RedisManager {

    @Autowired
    private JedisPool jedisPool;

    /**
     * 存储字符串键值对，永久有效
     * @param key
     * @param value
     * @return
     * @author hw
     * @date 2018年12月14日
     */
    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.set(key, value);
        } catch (Exception e) {
            return "-1";
        } finally {
            // 业务操作完成，将连接归还连接池
            jedisPool.returnResource(jedis);
        }
    }

    /**
     * 根据传入key获取指定Value
     * @param key
     * @return
     * @author hw
     * @date 2018年12月14日
     */
    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.get(key);
        } catch (Exception e) {
            return "-1";
        } finally {
            jedis.close();
        }
    }

    /**
     * 删除字符串键值对
     * @param key
     * @return
     * @author hw
     * @date 2018年12月14日
     */
    public Long del(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.del(key);
        } catch (Exception e) {
            return -1L;
        } finally {
            jedis.close();
        }
    }
    /**
     * 校验Key值是否存在
     */
    public Boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        } catch (Exception e) {
            return false;
        } finally {
            // 归还连接
            jedis.close();
        }
    }

    public <T> T execute(Function<Jedis, T> action) {
        try (Jedis jedis = jedisPool.getResource()) {
            return action.apply(jedis);
        }
    }

}