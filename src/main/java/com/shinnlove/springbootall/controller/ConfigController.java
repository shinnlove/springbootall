/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shinnlove.springbootall.service.ConfigService;

/**
 * A controller read configuration from config service and export.
 * 
 * @author Tony Zhao
 * @version $Id: ConfigController.java, v 0.1 2022-01-08 11:03 PM Tony Zhao Exp $$
 */
@RestController
@RequestMapping(value = "/config")
public class ConfigController {

    @Autowired
    private ConfigService configService;

    /**
     * 测试spring-boot不同方式读取配置。
     * 
     * @return
     */
    @RequestMapping(value = "/read")
    public String getConfig() {
        StringBuilder sb = new StringBuilder();

        // 直接使用上下文中注入的配置
        String envInfo = configService.getPropertiesConfig();

        // 使用配置服务
        String configStr = configService.getConfigStrFromEnv();
        int configValue = configService.getConfigValueFromEnv();

        // 使用@Value注解读取配置
        String valueStr = configService.getValueFromValueSpEL();

        // 多文件配置读取
        String multipleFileStr = configService.getMultipleFileConfig();

        // 准备输出
        sb.append("env info:").append(envInfo).append("; \n");
        sb.append("environment get:").append(configStr).append(":").append(configValue)
            .append("; \n");
        sb.append("value spEL:").append(valueStr).append("; \n");
        sb.append("multiple file:").append(multipleFileStr).append("; \n");

        return sb.toString();
    }

}