/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.controller;

import com.shinnlove.springbootall.service.TxCompoundService;
import com.shinnlove.springbootall.service.TxUserLimitItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tony Zhao
 * @version $Id: TxController.java, v 0.1 2024-04-26 10:47 Tony Zhao Exp $$
 */
@RestController
@RequestMapping(value = "/tx")
public class TxController {

    private static Logger logger = LoggerFactory.getLogger(TxController.class);

    @Autowired
    private TxCompoundService       txCompoundService;

    @Autowired
    private TxUserLimitItemService  txUserLimitItemService;

    @RequestMapping(value = "/compound", method = RequestMethod.GET)
    public Integer doCompound() {
        String activityId = "3124365647";
        Long componentId = 123456L;
        Long guid = 888888L;
        return txCompoundService.compound(activityId, componentId, guid);
    }

    @RequestMapping(value = "/use_limited_item", method = RequestMethod.GET)
    public Integer useLimitedItem(int type) {
        return txUserLimitItemService.useLimitedItem(type);
    }

}