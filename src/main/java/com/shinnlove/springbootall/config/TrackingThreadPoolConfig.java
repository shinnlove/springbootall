/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.config;

import static com.shinnlove.springbootall.util.consts.ThreadPoolConstants.*;

import java.util.concurrent.ExecutorService;

import org.springframework.context.annotation.Bean;

import com.shinnlove.springbootall.util.threads.ThreadUtils;

/**
 * Tracking thread pool for all config.
 *
 * @author Tony Zhao
 * @version $Id: TrackingThreadPoolConfig.java, v 0.1 2021-08-10 6:19 PM Tony Zhao Exp $$
 */
//@Configuration
public class TrackingThreadPoolConfig {

    /**
     * DIY async execute thread pool.
     *
     * @return
     */
    @Bean("xxlTrackingPool")
    public ExecutorService initXxlJobPool() {
        return ThreadUtils.createPool(XXL_JOB_THREAD_POOL_NAME, XXL_JOB_THREAD_POOL_NUM);
    }

    /**
     * DIY tracking pool for tag messages consuming.
     *
     * @return
     */
    @Bean("tagConsumePool")
    public ExecutorService initTagConsumePool() {
        return ThreadUtils.createPool(TAG_CONSUME_POOL_NAME, TAG_CONSUME_POOL_NUM);
    }

    /**
     * DIY async execute thread pool.
     *
     * @return
     */
    @Bean("processPool")
    public ExecutorService initProcessPool() {
        return ThreadUtils.createPool(MACHINE_THREAD_POOL_NAME, MACHINE_THREAD_POOL_NUM);
    }

    /**
     * DIY notification dispatch thread pool.
     *
     * @return
     */
    @Bean("notifyPool")
    public ExecutorService initNotifyPool() {
        return ThreadUtils.createPool(NOTIFY_THREAD_POOL_NAME, NOTIFY_THREAD_POOL_NUM);
    }

    /**
     * Initialize mail async thread pool.
     *
     * @return
     */
    @Bean("mailPool")
    public ExecutorService initMailPool() {
        return ThreadUtils.createPool(MAIL_THREAD_POOL_NAME, MAIL_THREAD_POOL_CORE_NUM,
            MAIL_THREAD_POOL_MAX_NUM, MAIL_THREAD_POOL_QUEUE_SIZE);
    }

}