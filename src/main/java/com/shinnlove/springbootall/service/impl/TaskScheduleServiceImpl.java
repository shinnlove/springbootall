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
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Tony Zhao
 * @version $Id: TaskScheduleServiceImpl.java, v 0.1 2024-05-07 19:15 Tony Zhao Exp $$
 */
@Service
public class TaskScheduleServiceImpl implements TaskScheduleService {

    public static final String PREEMPT_TASK_CRON = "0/15 * * * * ?";

    private final Map<String, ScheduledFuture<?>> taskFutures = new ConcurrentHashMap<>();

    private static final AtomicLong taskCounter = new AtomicLong(0);

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Override
    public Integer startTask(String taskName) {

        ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(
                () -> {

                    System.out.println("【" + taskName + "】 before task executed " + taskCounter.incrementAndGet() + " time...");
                    try {
                        TimeUnit.SECONDS.sleep(5000L);
                    } catch (InterruptedException e) {
                        System.out.println("【" + taskName + "】 sleep error, ex=" + e.getMessage());
//                        throw new RuntimeException("sleep error, ex=" + e.getMessage(), e);
                    }
                    System.out.println("【" + taskName + "】 after task executed " + taskCounter.incrementAndGet() + " time...");
                    System.out.println("【" + taskName + "】 after task executed " + taskCounter.incrementAndGet() + " time...");
                    System.out.println("【" + taskName + "】 after task executed " + taskCounter.incrementAndGet() + " time...");
                    System.out.println("【" + taskName + "】 after task executed " + taskCounter.incrementAndGet() + " time...");
                    System.out.println("【" + taskName + "】 after task executed " + taskCounter.incrementAndGet() + " time...");
                    System.out.println("【" + taskName + "】 after task executed " + taskCounter.incrementAndGet() + " time...");
                    System.out.println("【" + taskName + "】 after task executed " + taskCounter.incrementAndGet() + " time...");

//                    throw new RuntimeException("hahaha");
                },
                triggerContext -> new CronTrigger(PREEMPT_TASK_CRON).nextExecutionTime(triggerContext));

        taskFutures.put(taskName, future);

        System.out.println("【" + taskName + "】 scheduled completed...");

        return 1;
    }

    @Override
    public Integer cancelTask(String taskName) {
        if (taskFutures.containsKey(taskName)) {
            ScheduledFuture<?> future = taskFutures.get(taskName);
            future.cancel(true);
            taskFutures.remove(taskName);
            System.out.println("【" + taskName + "】 task manually canceled...");
            return 1;
        }

        return 0;
    }

}