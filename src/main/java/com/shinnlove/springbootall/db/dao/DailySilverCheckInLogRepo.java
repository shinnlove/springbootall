package com.shinnlove.springbootall.db.dao;

import com.shinnlove.springbootall.db.po.DailySilverCheckInLogEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DailySilverCheckInLogRepo {

    /**
     * 插入捡银子日志记录。
     *
     * @param record
     * @return
     */
    long insertSelective(@Param("entity") DailySilverCheckInLogEntity record);

    /**
     * 更新捡银子签到的奖品。
     *
     * @param id
     * @param actionQueries
     * @param prizeIdList
     * @return
     */
    int updateCheckInPrizeIdsById(@Param(value = "id") Long id,
                                  @Param(value = "actionQueries") String actionQueries,
                                  @Param(value = "prizeIdList") String prizeIdList);

}