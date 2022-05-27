/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.shinnlove.springbootall.client.RedisClient;
import com.shinnlove.springbootall.util.TonyPropertySourceFactory;

/**
 * @author Tony Zhao
 * @version $Id: RedisConfig.java, v 0.1 2022-05-27 2:42 PM Tony Zhao Exp $$
 */
@Configuration
@PropertySource(value = "classpath:redis.properties", factory = TonyPropertySourceFactory.class)
public class RedisConfig {

    @Value("${redis.host}")
    private String host;

    @Value("${redis.port}")
    private int    port;

    @Value("${redis.timeout}")
    private long   timeout;

    @Bean
    public RedisClient buildClient() {
        // build the client
        return new RedisClient(host, port, timeout);
    }

}