/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 创建自定义线程池工具类。
 *
 * @author Tony Zhao
 * @version $Id: ThreadUtils.java, v 0.1 2025-03-26 15:51 Tony Zhao Exp $$
 */
public class ThreadUtils {

    private final static Logger logger = LoggerFactory.getLogger(ThreadUtils.class);

    public static ThreadPoolExecutor createPool(String threadGroupName, int maximum) {
        return new MdcAwareThreadPoolExecutor(30, maximum, 300L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(50), new NamedThreadFactory(threadGroupName),
                (r, executor) -> logger.warn("Too many requests, reject by thread pool..."));
    }

    public static ThreadPoolExecutor createPool(String threadGroupName, int core, int maximum,
                                                int queueSize) {
        return new MdcAwareThreadPoolExecutor(core, maximum, 300L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(queueSize), new NamedThreadFactory(threadGroupName),
                (r, executor) -> logger.warn("Too many requests, reject by thread pool..."));
    }

    public static ThreadPoolExecutor createPool(String threadGroupName, int core, int maximum,
                                                int keepAliveSeconds, int queueSize) {
        return new MdcAwareThreadPoolExecutor(core, maximum, keepAliveSeconds, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(queueSize), new NamedThreadFactory(threadGroupName),
                (r, executor) -> logger.warn("Too many requests, reject by thread pool..."));
    }

}