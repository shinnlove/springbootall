/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.config.condition.bean;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shinnlove.springbootall.service.RedisService;
import com.shinnlove.springbootall.service.impl.RedisServiceImpl;

/**
 * RedisConfig配置bean。
 *  
 * 条件配置方式之一：
 * `@ConditionalOnMissingBean`注解，只有在当前没有`RedisService`类型的bean的时候才会调用create返回一个实例。
 * 
 * @author Tony Zhao
 * @version $Id: RedisMissBeanConfig.java, v 0.1 2022-01-09 3:04 PM Tony Zhao Exp $$
 */
@Configuration
public class RedisMissBeanConfig {

    @Bean
    @ConditionalOnMissingBean
    public RedisService createRedisService() {
        return new RedisServiceImpl();
    }

}