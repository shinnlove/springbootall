/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.threads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shinnlove.springbootall.util.log.LoggerUtil;

/**
 * A util class to create thread pool.
 *
 * @author Tony Zhao
 * @version $Id: TheadUtils.java, v 0.1 2021-08-02 12:03 PM Tony Zhao Exp $$
 */
public class ThreadUtils {

    private final static Logger logger = LoggerFactory.getLogger(ThreadUtils.class);

    public static ThreadPoolExecutor createPool(String threadGroupName, int maximum) {
        return new TrackingThreadPoolExecutor(30, maximum, 300L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(50), new NamedThreadFactory(threadGroupName),
            (r, executor) -> LoggerUtil.warn(logger,
                "Too many requests, reject by thread pool..."));
    }

    public static ThreadPoolExecutor createPool(String threadGroupName, int core, int maximum,
                                                int queueSize) {
        return new TrackingThreadPoolExecutor(core, maximum, 300L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(queueSize), new NamedThreadFactory(threadGroupName),
            (r, executor) -> LoggerUtil.warn(logger,
                "Too many requests, reject by thread pool..."));
    }

    public static ThreadPoolExecutor createPool(String threadGroupName, int core, int maximum,
                                                int keepAliveSeconds, int queueSize) {
        return new TrackingThreadPoolExecutor(core, maximum, keepAliveSeconds, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(queueSize), new NamedThreadFactory(threadGroupName),
            (r, executor) -> LoggerUtil.warn(logger,
                "Too many requests, reject by thread pool..."));
    }

}