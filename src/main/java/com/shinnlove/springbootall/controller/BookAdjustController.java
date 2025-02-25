/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.controller;

import com.shinnlove.springbootall.db.po.BookAdjustReasonMessageEntity;
import com.shinnlove.springbootall.db.po.QdGrowthBookScopeEntity;
import com.shinnlove.springbootall.service.BookAdjustReasonService;
import com.shinnlove.springbootall.service.QdGrowthBookScopeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: BookAdjustController.java, v 0.1 2025-01-10 08:55 Tony Zhao Exp $$
 */
@RestController
@RequestMapping(value = "/book_dao")
public class BookAdjustController {

    private static Logger logger = LoggerFactory.getLogger(DafengController.class);

    @Autowired
    private QdGrowthBookScopeService    qdGrowthBookScopeService;

    @Autowired
    private BookAdjustReasonService     bookAdjustReasonService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello() {
        return "Hello book adjust.";
    }

    @RequestMapping(value = "/query_growth", method = RequestMethod.GET)
    public QdGrowthBookScopeEntity initCheckIn(long cbid) {
        return qdGrowthBookScopeService.queryGrowthByCbid(cbid);
    }

    @RequestMapping(value = "/insert_message", method = RequestMethod.GET)
    public long insertBookAdjustMessage() {
        return bookAdjustReasonService.insertSelective();
    }

    @RequestMapping(value = "/update_book_check_audit", method = RequestMethod.GET)
    public long insertBookAdjustMessage(long id, int checkLevel, String auditStatus) {
        return bookAdjustReasonService.updateBookCurrentCheckLevelAndAuditStatus(id, checkLevel, auditStatus);
    }

    @RequestMapping(value = "/update_book_push_status", method = RequestMethod.GET)
    public long updateBookAdjustReasonPushResult(long id) {
        return bookAdjustReasonService.updateBookAdjustReasonPushResult(id);
    }

    @RequestMapping(value = "/query_book_message_by_cbid", method = RequestMethod.GET)
    public List<BookAdjustReasonMessageEntity> queryMessageByCbid(long cbid) {
        return bookAdjustReasonService.queryMessageByCbid(cbid);
    }

}