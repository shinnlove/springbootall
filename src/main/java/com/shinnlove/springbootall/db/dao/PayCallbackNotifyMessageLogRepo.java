package com.shinnlove.springbootall.db.dao;

import com.shinnlove.springbootall.db.po.PayCallbackNotifyMessageLogEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PayCallbackNotifyMessageLogRepo {

    /**
     * 支付回调通知消息插入。
     *
     * @param record
     * @return
     */
    int insertSelective(@Param("entity") PayCallbackNotifyMessageLogEntity record);

}