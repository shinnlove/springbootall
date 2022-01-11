/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.threads;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Tony Zhao
 * @version $Id: NamedThreadFactory.java, v 0.1 2021-08-02 12:04 PM Tony Zhao Exp $$
 */
public class NamedThreadFactory implements ThreadFactory {

    static final AtomicInteger poolNumber   = new AtomicInteger(1);

    final AtomicInteger        threadNumber = new AtomicInteger(1);
    final ThreadGroup          group;
    final String               namePrefix;
    final boolean              isDaemon;

    public NamedThreadFactory() {
        this("diy-default-pool");
    }

    public NamedThreadFactory(String name) {
        this(name, false);
    }

    public NamedThreadFactory(String preffix, boolean daemon) {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        namePrefix = preffix + "-" + poolNumber.getAndIncrement() + "-thread-";
        isDaemon = daemon;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
        t.setDaemon(isDaemon);
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }

}