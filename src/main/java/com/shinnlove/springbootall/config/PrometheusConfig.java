/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.prometheus.client.Counter;
import io.prometheus.client.Histogram;

/**
 * Project prometheus configuration.
 *
 * @author Tony Zhao
 * @version $Id: PrometheusConfig.java, v 0.1 2021-08-31 4:51 PM Tony Zhao Exp $$
 */
@Configuration
public class PrometheusConfig {

    private static final String DEFAULT_BUCKETS = "5,10,25,50,100,250,500,1000,2500,5000,10000";

    @Bean("webErrorCounter")
    public Counter webErrorCounter() {
        return Counter.build().name("web_request_error_count")
            .labelNames("request_mapping", "execute_result", "ex_class")
            .help("sampling controller errors").register();
    }

    @Bean("webRequestTotalCounter")
    public Counter webRequestTotalCounter() {
        return Counter.build().name("web_request_total_count").labelNames("request_mapping")
            .help("sampling controller requests").register();
    }

    @Bean("webRequestRTHistogram")
    public Histogram webRequestRTHistogram() {
        // could use config center here
        String histogramBuckets = DEFAULT_BUCKETS;
        String[] bucketsStr = histogramBuckets.split(",");
        double[] bucketArray = Arrays.asList(bucketsStr).stream().mapToDouble(Double::parseDouble)
            .toArray();
        return Histogram.build().name("web_rt_request_duration_ms").labelNames("request_mapping")
            .buckets(bucketArray).help("sampling response time duration in ms").register();
    }

    @Bean("statusMachineCounter")
    public Counter statusMachineCounter() {
        return Counter.build().name("status_machine_count").labelNames("template_id", "action_id")
            .help("sampling status machine tx count").register();
    }

}