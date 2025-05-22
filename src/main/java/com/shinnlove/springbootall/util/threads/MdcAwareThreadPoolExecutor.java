/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 可以拷贝MDC上下文、又会在线程结束时清理上下文的线程池。
 *
 * @author Tony Zhao
 * @version $Id: MdcAwareThreadPoolExecutor.java, v 0.1 2025-03-26 15:57 Tony Zhao Exp $$
 */
public class MdcAwareThreadPoolExecutor extends ThreadPoolExecutor {

    private final static Logger logger = LoggerFactory.getLogger(MdcAwareThreadPoolExecutor.class);

    public MdcAwareThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                                      TimeUnit unit, BlockingQueue<Runnable> workQueue,
                                      ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        // 在这里可以添加自定义的前置逻辑
        logger.info("Thread {} is about to execute task {}", t.getName(), r);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        // 在这里可以添加自定义的后置逻辑
        logger.info("Thread has finished executing task {}", r);
        // 确保MDC被清理
        MDC.clear();
    }

    @Override
    public void execute(Runnable command) {
        // 获取当前线程的MDC上下文
        Map<String, String> context = MDC.getCopyOfContextMap();
        super.execute(() -> {
            // 恢复MDC上下文
            if (context != null) {
                MDC.setContextMap(context);
            }
            try {
                command.run();
            } finally {
                MDC.clear();
            }
        });
    }

}