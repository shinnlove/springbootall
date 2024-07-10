/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.dao.UserTicketChangeLogRepo;
import com.shinnlove.springbootall.db.po.UserTicketChangeLogEntity;
import com.shinnlove.springbootall.exceptions.BusinessCode;
import com.shinnlove.springbootall.exceptions.DBAccessThrowException;
import com.shinnlove.springbootall.exceptions.DBExecuteReturnException;
import com.shinnlove.springbootall.service.UserTicketChangeLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tony Zhao
 * @version $Id: UserTicketChangeLogServiceImpl.java, v 0.1 2024-07-10 14:13 Tony Zhao Exp $$
 */
@Service
public class UserTicketChangeLogServiceImpl implements UserTicketChangeLogService {

    private static final Logger logger = LoggerFactory.getLogger(UserTicketChangeLogServiceImpl.class);

    private static final String activityId = "123456";

    private static final long componentId = 123L;

    /** 用户竞猜券变更日志仓储 */
    @Autowired
    private UserTicketChangeLogRepo userTicketChangeLogRepo;

    /**
     * 变更用户竞猜券日志。
     *
     * @param guid
     * @param changeType
     * @param ticketNumber
     * @return
     */
    @Override
    public long addTicketChangeLog(Long guid, Integer changeType, Integer ticketNumber) throws DBAccessThrowException, DBExecuteReturnException {

        UserTicketChangeLogEntity changeLog = new UserTicketChangeLogEntity();
        changeLog.setActivityId(activityId);
        changeLog.setComponentId(componentId);
        changeLog.setGuid(guid);
        changeLog.setChangeType(changeType);
        changeLog.setChangeNumber(ticketNumber);

        long result = 0L;
        try {
            result = userTicketChangeLogRepo.insertSelective(changeLog);
        } catch (Exception e) {
            logger.error("UserTicketChangeLogService addTicketChangeLog has error, ex=" + e.getMessage(), e);
            throw new DBAccessThrowException(BusinessCode.FAIL, e);
        }

        if (result <= 0) {
            throw new DBExecuteReturnException(BusinessCode.FAIL);
        }

        return result;
    }

}