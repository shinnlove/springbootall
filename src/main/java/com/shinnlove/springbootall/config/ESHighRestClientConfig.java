/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Elasticsearch high rest client configuration.
 * 
 * @author Tony Zhao
 * @version $Id: ElasticsearchClientConfig.java, v 0.1 2022-01-09 8:03 PM Tony Zhao Exp $$
 */
@Configuration
public class ESHighRestClientConfig {

    /** a request option config that every request could use. */
    public static final RequestOptions COMMON_OPTIONS;

    private static final String        HEAD_AUTH          = "Authorization";
    private static final String        AUTH_BEARER        = "Bearer ";
    private static final String        TOKEN              = "DFHJKQWFDBASFS";
    private static final int           BUFFER_LIMIT_BYTES = 200 * 1024 * 1024;

    private static final String        DOMAIN             = "localhost";
    private static final int           PORT               = 9200;
    private static final String        SCHEMA             = "http";

    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
        builder.addHeader(HEAD_AUTH, AUTH_BEARER + TOKEN);
        builder.setHttpAsyncResponseConsumerFactory(
            new HttpAsyncResponseConsumerFactory.HeapBufferedResponseConsumerFactory(
                BUFFER_LIMIT_BYTES));
        COMMON_OPTIONS = builder.build();
    }

    @Bean
    public RestHighLevelClient client() {
        RestClientBuilder builder = RestClient.builder(new HttpHost(DOMAIN, PORT, SCHEMA));
        RestHighLevelClient client = new RestHighLevelClient(builder);
        return client;
    }

}