/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.controller;

import com.shinnlove.springbootall.db.po.UserCompoundRecordAggEntity;
import com.shinnlove.springbootall.service.UserCompoundRecordService;
import com.shinnlove.springbootall.service.UserFragmentCollectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: BizTestController.java, v 0.1 2024-04-24 10:48 Tony Zhao Exp $$
 */
@RestController
@RequestMapping(value = "/biz")
public class BizTestController {

    private static Logger logger = LoggerFactory.getLogger(BizTestController.class);

    @Autowired
    private UserFragmentCollectService userFragmentCollectService;

    @Autowired
    private UserCompoundRecordService userCompoundRecordService;

    @RequestMapping(value = "/test_compound", method = RequestMethod.GET)
    public Integer testCompound() {
        return 1;
    }

}