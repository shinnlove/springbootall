/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;

/**
 * @author Tony Zhao
 * @version $Id: TaskScheduleService.java, v 0.1 2024-05-07 19:15 Tony Zhao Exp $$
 */
public interface TaskScheduleService {

    /**
     * Starts a task with the given name.
     *
     * @param taskName the name of the task to start
     * @return an Integer representing the status of the operation. The meaning of the status code is defined by the implementation.
     */
    Integer startTask(String taskName);

    Integer stopTask(String taskName);

}
