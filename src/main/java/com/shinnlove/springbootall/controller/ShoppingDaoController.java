/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.controller;

import com.shinnlove.springbootall.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tony Zhao
 * @version $Id: ShoppingDaoController.java, v 0.1 2024-06-30 19:37 Tony Zhao Exp $$
 */
@RestController
@RequestMapping(value = "/shopping_dao")
public class ShoppingDaoController {

    private static Logger logger = LoggerFactory.getLogger(ShoppingDaoController.class);

    @Autowired
    private TestSkuSelectionService testSkuSelectionService;

    @Autowired
    private TestSkuStorageService testSkuStorageService;

    @Autowired
    private SkuStorageChangeLogService skuStorageChangeLogService;

    @Autowired
    private SkuStorageDeDupLockService skuStorageDeDupLockService;

    @Autowired
    private ItemSystemDeductService itemSystemDeductService;

    @Autowired
    private AssistDeductCodeService assistDeductCodeService;

    @Autowired
    private AssistDeductDetailService assistDeductDetailService;

    @Autowired
    private TestTxPaySelectionService testTxPaySelectionService;

    @Autowired
    private TestTxSelectionInitCancelService testTxSelectionInitCancelService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello() {
        return "Hello world.";
    }

    @RequestMapping(value = "/init_item_storage", method = RequestMethod.GET)
    public long initItemStorage() {
        return testSkuStorageService.initItemStorage();
    }

    @RequestMapping(value = "/add_item_selection", method = RequestMethod.GET)
    public long addItemSelection() {
        return testSkuSelectionService.addItemSelection();
    }

    @RequestMapping(value = "/tx_pay_and_lock", method = RequestMethod.GET)
    public long txPayAndLock(long selectId) {
        return testTxPaySelectionService.txPaySelectionAndLockStorageOnce(selectId);
    }

    @RequestMapping(value = "/tx_paid_and_sold", method = RequestMethod.GET)
    public long txPaidAndSold(long selectId) {
        return testTxPaySelectionService.txUpdatePaidStatusAndSellNum(selectId);
    }

    @RequestMapping(value = "/cancel_and_return", method = RequestMethod.GET)
    public long txCancelAndReturn(long selectId) {
        return testTxSelectionInitCancelService.txCancelSelectionAndReturnStorage(selectId);
    }

}