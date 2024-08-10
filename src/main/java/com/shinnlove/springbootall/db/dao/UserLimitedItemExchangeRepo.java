package com.shinnlove.springbootall.db.dao;

import com.shinnlove.springbootall.db.po.UserLimitedItemExchangeEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLimitedItemExchangeRepo {

    long insertSelective(@Param("tableName") String tableName,
                         @Param("entity") UserLimitedItemExchangeEntity record);

}