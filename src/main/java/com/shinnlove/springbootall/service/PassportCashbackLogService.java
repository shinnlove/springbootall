/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;

import com.shinnlove.springbootall.db.po.PassportCashbackLogEntity;

import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: PassportCashbackLogService.java, v 0.1 2024-12-09 20:19 Tony Zhao Exp $$
 */
public interface PassportCashbackLogService {

    /**
     * @param packageType
     * @param memberGearType
     * @param hasSent
     * @return
     */
    long insertCashbackLog(int packageType, int memberGearType, int hasSent);

    /**
     * @param guid
     * @return
     */
    List<PassportCashbackLogEntity> queryPassportCashbackLogs(Long guid);

}
