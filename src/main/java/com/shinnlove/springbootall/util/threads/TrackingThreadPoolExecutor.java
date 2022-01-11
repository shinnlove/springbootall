/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.threads;

import java.util.concurrent.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shinnlove.springbootall.util.log.LoggerUtil;
import com.shinnlove.springbootall.util.tracking.SystemTrackingInfo;
import com.shinnlove.springbootall.util.tracking.TrackingUtil;

/**
 * A tracking util thread pool executor that wrapper tracking thread context before execution and clear after executed.
 *
 * @author Tony Zhao
 * @version $Id: TrackingThreadPoolExecutor.java, v 0.1 2021-08-03 8:39 PM Tony Zhao Exp $$
 */
public class TrackingThreadPoolExecutor extends ThreadPoolExecutor {

    private static final Logger logger = LoggerFactory.getLogger(TrackingThreadPoolExecutor.class);

    public TrackingThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                                      TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public TrackingThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                                      TimeUnit unit, BlockingQueue<Runnable> workQueue,
                                      ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public TrackingThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                                      TimeUnit unit, BlockingQueue<Runnable> workQueue,
                                      RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public TrackingThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                                      TimeUnit unit, BlockingQueue<Runnable> workQueue,
                                      ThreadFactory threadFactory,
                                      RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory,
            handler);
    }

    @Override
    public void execute(Runnable command) {
        SystemTrackingInfo trackingInfo = TrackingUtil.getOrCreateTracking();
        TrackingRunnable r = new TrackingRunnable(command, trackingInfo);
        super.execute(r);
    }

    @Override
    public Future<?> submit(Runnable task) {
        SystemTrackingInfo trackingInfo = TrackingUtil.getOrCreateTracking();
        TrackingRunnable r = new TrackingRunnable(task, trackingInfo);
        return super.submit(r);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        // VIP: this method should be invoked first
        super.afterExecute(r, t);

        long tId = Thread.currentThread().getId();
        SystemTrackingInfo info = TrackingUtil.get();
        String uuid = info.getServiceUUID();
        LoggerUtil.debug(logger, "[test-local-", tId, "]After Execute, Current thread uuid=", uuid);

        // VIP: clear thread pool's tracking UUID!!!
        TrackingUtil.remove();

        LoggerUtil.debug(logger, "[test-local-", tId, "]Thread local removed!!!");
        SystemTrackingInfo afterInfo = TrackingUtil.get();
        if (afterInfo == null) {
            LoggerUtil.debug(logger, "[test-local-", tId, "]Thread local tracking has removed!");
        } else {
            String afterUUID = afterInfo.getServiceUUID();
            LoggerUtil.debug(logger, "[test-local-", tId,
                "]Thread local tracking not removed, uuid=", afterUUID);
        }

    }

}