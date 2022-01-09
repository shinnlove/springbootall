/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.config.condition.bean;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;

import com.shinnlove.springbootall.service.client.JedisClient;

/**
 * @author Tony Zhao
 * @version $Id: JedisConfig.java, v 0.1 2022-01-09 2:47 PM Tony Zhao Exp $$
 */
@Configuration
@ConditionalOnBean(JedisClient.class)
public class JedisConfig {
}