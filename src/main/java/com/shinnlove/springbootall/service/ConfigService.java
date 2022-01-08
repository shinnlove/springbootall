/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;

/**
 * Springboot configuration service.
 *
 * @author Tony Zhao
 * @version $Id: ConfigService.java, v 0.1 2022-01-08 10:54 PM Tony Zhao Exp $$
 */
public interface ConfigService {

    /**
     * 从`application.yml`获取配置字符串。
     *
     * @return
     */
    String getConfigStrFromEnv();

    /**
     * 从`application.yml`获取配置值。
     *
     * @return
     */
    int getConfigValueFromEnv();

    /**
     * 从spring-boot的配置bean中获取通过`@ConfigurationProperties`属性注入的配置值。
     *
     * @return
     */
    String getPropertiesConfig();

    /**
     * 从`@Value`注解和SpEL表达式来获取`application.yml`中的属性。
     *
     * @return
     */
    String getValueFromValueSpEL();

    /**
     * 从自定义的configuration file中读取配置。
     * 
     * @return 
     */
    String getMultipleFileConfig();

}
