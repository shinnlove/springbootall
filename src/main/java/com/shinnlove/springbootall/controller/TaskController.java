/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.controller;

import com.shinnlove.springbootall.service.TaskScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tony Zhao
 * @version $Id: TaskController.java, v 0.1 2024-05-07 19:14 Tony Zhao Exp $$
 */
@RestController
public class TaskController {

    @Autowired
    private TaskScheduleService taskScheduleService;

    @RequestMapping(value = "/task_start", method = RequestMethod.GET)
    public Integer startTask(String name) {
        return taskScheduleService.startTask(name);
    }

    @RequestMapping(value = "/task_stop", method = RequestMethod.GET)
    public Integer stopTask(String name) {
        return taskScheduleService.stopTask(name);
    }

}