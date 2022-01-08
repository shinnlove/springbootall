/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.shinnlove.springbootall.config.diy.ImportMultipleConfig;
import com.shinnlove.springbootall.config.env.EnvPropertyConfig;
import com.shinnlove.springbootall.service.ConfigService;

/**
 * Springboot configuration service implementation.
 *
 * @author Tony Zhao
 * @version $Id: ConfigServiceImpl.java, v 0.1 2022-01-08 10:54 PM Tony Zhao Exp $$
 */
@Service
public class ConfigServiceImpl implements ConfigService {

    private static final String  JAVA_HOME         = "JAVA_HOME";
    private static final String  COLON             = ":";
    private static final String  SEMICOLON         = ";";
    private static final String  PROPERTY_DIY_IP   = "diy.ip";
    private static final String  PROPERTY_DIY_PORT = "diy.port";
    private static final String  POOL_SIZE         = "spring.datasource.hikari.maximum-pool-size";

    /** auto-created by springboot framework and hold all properties in configuration files. */
    @Autowired
    private Environment          environment;

    /** auto registered env properties */
    @Autowired
    private EnvPropertyConfig    envPropertyConfig;

    @Autowired
    private ImportMultipleConfig importMultipleConfig;

    @Value("${server.tomcat.accesslog.enabled:true}")
    private boolean              tomcatLogEnabled;

    @Override
    public String getConfigStrFromEnv() {
        String str = environment.getProperty(JAVA_HOME) + SEMICOLON;
        // special warning: env does not contain any diy ip or port and return null
        str += environment.getProperty(PROPERTY_DIY_IP) + SEMICOLON;
        str += environment.getProperty(PROPERTY_DIY_PORT) + SEMICOLON;

        return str;
    }

    @Override
    public int getConfigValueFromEnv() {
        // 默认getProperty都当做字符串了，要获取整型需要转型、但是注意别抛错
        try {
            return environment.getProperty(POOL_SIZE, Integer.class);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public String getPropertiesConfig() {
        return envPropertyConfig.getInfo();
    }

    @Override
    public String getValueFromValueSpEL() {
        return "Tomcat log enabled:" + tomcatLogEnabled;
    }

    @Override
    public String getMultipleFileConfig() {
        String ip = importMultipleConfig.getIp();
        String port = importMultipleConfig.getPort();

        return ip + COLON + port;
    }

}