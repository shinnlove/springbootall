package com.shinnlove.springbootall.db.dao;


import com.shinnlove.springbootall.db.po.AssistRecordEntity;
import com.shinnlove.springbootall.db.po.SumAssistRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AssistRecordRepo {

    int createAssistRecord(@Param(value = "entity") AssistRecordEntity assistRecordEntity);

    List<AssistRecordEntity> queryEffectiveAssistRecordByGuid(@Param(value = "inviterGuid") long inviterGuid,
                                                              @Param(value = "currentTimeMillis") long currentTimeMillis);

    SumAssistRecord sumEffectiveAssistRecordByGuid(@Param(value = "inviterGuid") long inviterGuid,
                                                   @Param(value = "currentTimeMillis") long currentTimeMillis);

    int updateReceivedBatch(@Param(value = "recordIds") List<Long> recordIds);

    long countTodayInviteRecords(@Param(value = "inviterGuid") long inviterGuid,
                                 @Param(value = "dateMark") int dateMark);

    long countInviteRecords(@Param(value = "inviterGuid") long inviterGuid);

    List<AssistRecordEntity> pageQueryInviteRecords(@Param(value = "inviterGuid") long inviterGuid,
                                                    @Param(value = "offset") int offset,
                                                    @Param(value = "limit") int limit);

    AssistRecordEntity latestAssistRecord(@Param(value = "inviterGuid") long inviterGuid);

}