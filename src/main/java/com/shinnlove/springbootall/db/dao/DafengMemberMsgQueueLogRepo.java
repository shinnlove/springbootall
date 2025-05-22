package com.shinnlove.springbootall.db.dao;

import com.shinnlove.springbootall.db.po.DafengMemberMsgQueueLogEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DafengMemberMsgQueueLogRepo {

    /**
     * 插入消息
     *
     * @param entity 消息实体
     * @return
     */
    long insertSelective(@Param("entity") DafengMemberMsgQueueLogEntity entity);

}