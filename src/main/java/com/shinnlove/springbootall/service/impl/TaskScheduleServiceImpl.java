/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.service.TaskScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Tony Zhao
 * @version $Id: TaskScheduleServiceImpl.java, v 0.1 2024-05-07 19:15 Tony Zhao Exp $$
 */
@Service
public class TaskScheduleServiceImpl implements TaskScheduleService {

    public static final String PREEMPT_TASK_CRON = "0/2 * * * * ?";

    private final Map<String, ScheduledFuture<?>> taskFutures = new ConcurrentHashMap<>();

    private static final AtomicLong taskCounter = new AtomicLong(0);

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Override
    public Integer startTask(String taskName) {

        ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(
                () -> System.out.println("【" + taskName + "】 task executed " + taskCounter.incrementAndGet() + " time..."),
                triggerContext -> new CronTrigger(PREEMPT_TASK_CRON).nextExecutionTime(triggerContext));

        taskFutures.put(taskName, future);

        return 1;
    }

    @Override
    public Integer stopTask(String taskName) {
        if (taskFutures.containsKey(taskName)) {
            ScheduledFuture<?> future = taskFutures.get(taskName);
            future.cancel(true);
            taskFutures.remove(taskName);
            System.out.println("【" + taskName + "】 task stopped...");
            return 1;
        }

        return 0;
    }

}