package com.shinnlove.springbootall.db.dao;

import com.shinnlove.springbootall.db.po.UserBetTicketEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBetTicketRepo {

    /**
     * 插入用户券记录信息。
     *
     * @param record
     * @return
     */
    int insertSelective(@Param("record") UserBetTicketEntity record);

    /**
     * 查询用户券信息。
     *
     * @param activityId
     * @param componentId
     * @param guid
     * @return
     */
    UserBetTicketEntity queryUserBetTicket(@Param("activityId") String activityId,
                                           @Param("componentId") Long componentId,
                                           @Param("guid") Long guid);

    /**
     * 购买时增加用户券数量。
     *
     * @param activityId
     * @param componentId
     * @param guid
     * @param incNum
     * @return
     */
    int incUserTicketNumber(@Param("activityId") String activityId,
                            @Param("componentId") Long componentId,
                            @Param("guid") Long guid,
                            @Param("incNum") Integer incNum);

    /**
     * 竞猜时扣减用户券数量。
     *
     * @param activityId
     * @param componentId
     * @param guid
     * @param decrNum
     * @return
     */
    int decrUserTicketNumber(@Param("activityId") String activityId,
                            @Param("componentId") Long componentId,
                            @Param("guid") Long guid,
                            @Param("decrNum") Integer decrNum);

}