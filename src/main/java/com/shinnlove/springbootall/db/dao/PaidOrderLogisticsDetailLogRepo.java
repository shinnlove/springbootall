package com.shinnlove.springbootall.db.dao;

import com.shinnlove.springbootall.db.po.PaidOrderLogisticsDetailLogEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaidOrderLogisticsDetailLogRepo {

    /**
     * 一把查询物流信息。
     *
     * @param expressNo
     * @return
     */
    List<PaidOrderLogisticsDetailLogEntity> queryLogisticsLogsByExpressNo(@Param(value = "expressNo") String expressNo);

    /**
     * 插入新的物流记录。
     *
     * @param record
     * @return
     */
    int insertSelective(@Param("entity") PaidOrderLogisticsDetailLogEntity record);

    /**
     * 删除给定快递单号下的所有物流信息。
     *
     * @param expressNo
     * @return
     */
    int deleteOldExpressLogs(@Param(value = "expressNo") String expressNo);

}