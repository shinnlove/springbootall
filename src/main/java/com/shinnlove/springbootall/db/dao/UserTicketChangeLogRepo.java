package com.shinnlove.springbootall.db.dao;

import com.shinnlove.springbootall.db.po.UserTicketChangeLogEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTicketChangeLogRepo {

    /**
     * @param record
     * @return
     */
    long insertSelective(@Param("entity") UserTicketChangeLogEntity record);

}