package com.shinnlove.springbootall.db.dao;

import com.shinnlove.springbootall.db.po.BookAdjustReasonMessageEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookAdjustReasonMessageRepo {

    /**
     * @param entity
     * @return
     */
    long insertSelective(@Param("entity") BookAdjustReasonMessageEntity entity);

    /**
     * @param id
     * @param checkLevel
     * @param auditStatus
     * @param needPush
     * @return
     */
    int updateBookCurrentCheckLevelAndAuditStatus(@Param(value = "id") Long id,
                                                  @Param(value = "checkLevel") Integer checkLevel,
                                                  @Param(value = "auditStatus") String auditStatus,
                                                  @Param(value = "needPush") Integer needPush);

    /**
     * @param id
     * @param hasSent
     * @return
     */
    int updateBookAdjustReasonPushResult(@Param(value = "id") Long id,
                                         @Param(value = "hasSent") Integer hasSent);

    /**
     * @param cbid
     * @return
     */
    List<BookAdjustReasonMessageEntity> queryMessageByCbid(@Param(value = "cbid") Long cbid);

}