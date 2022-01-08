/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.config.diy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * import multiple resource file under classpath and using diy properties.
 *
 * @author Tony Zhao
 * @version $Id: ImportMultipleConfig.java, v 0.1 2022-01-08 10:48 PM Tony Zhao Exp $$
 */
@Configuration
@PropertySource("classpath:my-configuration.yml")
public class ImportMultipleConfig {

    @Value("${ip}")
    private String ip;

    @Value("${port}")
    private String port;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

}