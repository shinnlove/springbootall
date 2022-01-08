/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.config.env;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * autowire configuration field by annotation @ConfigurationProperties.
 *
 * @author Tony Zhao
 * @version $Id: EnvPropertyConfig.java, v 0.1 2022-01-08 10:46 PM Tony Zhao Exp $$
 */
@Configuration
@ConfigurationProperties("memo")
public class EnvPropertyConfig {

    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}