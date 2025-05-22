/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.config;

import com.shinnlove.springbootall.util.http.HttpClientUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Tony Zhao
 * @version $Id: HttpClientConfiguration.java, v 0.1 2025-03-03 15:12 Tony Zhao Exp $$
 */
@Configuration
public class HttpClientConfiguration {

    @Bean
    public HttpClientUtil getHttpClientUtil() {
        return new HttpClientUtil(1000, 3000, 2000);
    }

}