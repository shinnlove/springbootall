package com.shinnlove.springbootall.db.dao;

import com.shinnlove.springbootall.db.po.QdGrowthBookScopeEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QdGrowthBookScopeRepo {

    /**
     * @param cbid
     * @return
     */
    QdGrowthBookScopeEntity queryGrowthByCbid(@Param(value = "cbid") Long cbid);

}