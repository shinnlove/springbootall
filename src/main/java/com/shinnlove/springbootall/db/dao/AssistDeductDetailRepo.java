/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.db.dao;

import com.shinnlove.springbootall.db.po.AggSelectionAssistEntity;
import com.shinnlove.springbootall.db.po.AssistDeductDetailEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: AssistDeductDetailRepo.java, v 0.1 2024-06-18 15:42 Tony Zhao Exp $$
 */
@Repository
public interface AssistDeductDetailRepo {

    /**
     * 插入一条助力记录。
     *
     * @param entity
     * @return
     */
    long insertSelective(@Param("entity") AssistDeductDetailEntity entity);

    /**
     * 根据selectId查询助力的情况。
     *
     * @param activityId
     * @param componentId
     * @param selectId              某个select选择id
     * @param helpType              助力类型，系统助力还是好友助力，允许区分
     * @return
     */
    List<AssistDeductDetailEntity> querySelectionDeductDetailIndex(@Param("activityId") String activityId,
                                                                   @Param("componentId") Long componentId,
                                                                   @Param("selectId") Long selectId,
                                                                   @Param("helpType") Integer helpType);

    /**
     * 根据selectId查询助力的情况。
     *
     * @param activityId
     * @param componentId
     * @param selectId              某个select选择id
     * @param helpType              助力类型，系统助力还是好友助力，允许区分
     * @return
     */
    List<AssistDeductDetailEntity> querySelectionDeductDetailHelp(@Param("activityId") String activityId,
                                                                  @Param("componentId") Long componentId,
                                                                  @Param("selectId") Long selectId,
                                                                  @Param("helpType") Integer helpType);

    /**
     * 查询某个用户的助力情况。
     *
     * @param activityId
     * @param componentId
     * @param helpGuid
     * @return
     */
    List<AssistDeductDetailEntity> queryAssistByGuid(@Param("activityId") String activityId,
                                                     @Param("componentId") Long componentId,
                                                     @Param("helpGuid") Long helpGuid);

    /**
     * 聚合查询某个selectId已经被助力扣减的金额。
     *
     * @param activityId
     * @param componentId
     * @param selectId
     * @return
     */
    AggSelectionAssistEntity aggSelectionAssistDeductPrice(@Param("activityId") String activityId,
                                                           @Param("componentId") Long componentId,
                                                           @Param("selectId") Long selectId);

}
