package com.shinnlove.springbootall.db.dao;

import com.shinnlove.springbootall.db.po.MonthTicketSkuBindingEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthTicketSkuBindingRepo {

    /**
     * 插入月票绑定关系。
     *
     * @param entity
     * @return
     */
    int insertSelective(@Param("entity") MonthTicketSkuBindingEntity entity);

    /**
     * 通过stubId查询月票。
     *
     * @param stubId
     * @return
     */
    MonthTicketSkuBindingEntity queryTicketBindingByStubId(@Param("stubId") String stubId);

}