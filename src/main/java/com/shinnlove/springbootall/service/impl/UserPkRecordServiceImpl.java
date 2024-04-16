/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.dao.UserPkRecordRepo;
import com.shinnlove.springbootall.db.po.UserPkRecordEntity;
import com.shinnlove.springbootall.service.UserPkRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: UserPkRecordServiceImpl.java, v 0.1 2024-04-16 11:13 Tony Zhao Exp $$
 */
@Service
public class UserPkRecordServiceImpl implements UserPkRecordService {

    @Autowired
    private UserPkRecordRepo userPkRecordRepo;

    @Override
    public long save() {

        Long guid = 123456L;
        String activityId = "test_activity_1";
        Integer itemType = 1;
        String itemImgUrl = "http://";
        String itemName = "pk的东西";
        int pkStatus = 1;
        int myCount = 100;
        long defenderGuid = 7891643L;
        int defenderCount = 50;
        String prizeName = "17点币";
        String prizeDesc = "17点币";
        String prizeImgUrl = "http://img.yow.com";

        UserPkRecordEntity record = new UserPkRecordEntity();
        record.setActivityId(activityId);
        record.setItemType(itemType);
        record.setItemName(itemName);
        record.setItemImgUrl(itemImgUrl);
        record.setPkStatus(pkStatus);
        record.setChallengerGuid(guid);
        record.setChallengerItemNum(myCount);
        record.setDefenderGuid(defenderGuid);
        record.setDefenderItemNum(defenderCount);
        record.setPrizeName(prizeName);
        record.setPrizeDesc(prizeDesc);
        record.setPrizeImgUrl(prizeImgUrl);

        String pkRecordTableName = "user_pk_record";
        return userPkRecordRepo.save(pkRecordTableName, record);
    }

    @Override
    public List<UserPkRecordEntity> queryRecordByGuidAndTime() {

        Long guid = 123456L;
        String activityId = "test_activity_1";

        Date todayStart = todayStart();
        Date todayEnd = todayEnd();

        Timestamp start = new Timestamp(todayStart.getTime());
        Timestamp end = new Timestamp(todayEnd.getTime());

        String pkRecordTableName = "user_pk_record";
        List<UserPkRecordEntity> pos = userPkRecordRepo
                .queryRecordByGuidAndTime(pkRecordTableName, activityId, guid, start, end);

        if (CollectionUtils.isEmpty(pos)) {
            return Collections.emptyList();
        }

        return pos;
    }

    public static Date getTodayDate() {
        return Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取每天的开始时间 00:00:00:00
     *
     * @return
     */
    public static Date todayStart() {
        Date today = getTodayDate();

        Calendar dateStart = Calendar.getInstance();
        dateStart.setTime(today);
        dateStart.set(Calendar.HOUR_OF_DAY, 0);
        dateStart.set(Calendar.MINUTE, 0);
        dateStart.set(Calendar.SECOND, 0);
        return dateStart.getTime();
    }

    /**
     * 获取每天的结束时间 23:59:59:999
     *
     * @return
     */
    public static Date todayEnd() {
        Date today = getTodayDate();

        Calendar dateEnd = Calendar.getInstance();
        dateEnd.setTime(today);
        dateEnd.set(Calendar.HOUR_OF_DAY, 23);
        dateEnd.set(Calendar.MINUTE, 59);
        dateEnd.set(Calendar.SECOND, 59);
        return dateEnd.getTime();
    }

}