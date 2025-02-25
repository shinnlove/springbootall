package com.shinnlove.springbootall.db.dao;

import com.shinnlove.springbootall.db.po.PassportCashbackLogEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassportCashbackLogRepo {

    /**
     * @param record
     * @return
     */
    int insertSelective(@Param("entity") PassportCashbackLogEntity record);

    /**
     * @param activityId
     * @param componentId
     * @param guid
     * @return
     */
    List<PassportCashbackLogEntity> queryPassportCashbackLogs(@Param("activityId") String activityId,
                                                              @Param("componentId") Long componentId,
                                                              @Param("guid") Long guid);

}