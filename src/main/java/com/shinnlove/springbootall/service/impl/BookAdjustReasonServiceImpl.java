/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.dao.BookAdjustReasonMessageRepo;
import com.shinnlove.springbootall.db.po.BookAdjustReasonMessageEntity;
import com.shinnlove.springbootall.service.BookAdjustReasonService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: BookAdjustReasonServiceImpl.java, v 0.1 2025-01-13 17:02 Tony Zhao Exp $$
 */
@Service
public class BookAdjustReasonServiceImpl implements BookAdjustReasonService {

    private static final Logger logger = LoggerFactory.getLogger(BookAdjustReasonServiceImpl.class);

    private static final String ACTIVITY_ID = "12345678";

    @Resource
    private BookAdjustReasonMessageRepo bookAdjustReasonMessageRepo;

    @Override
    public long insertSelective() {

        try {

            BookAdjustReasonMessageEntity entity = new BookAdjustReasonMessageEntity();

            entity.setActivityId(ACTIVITY_ID);
            entity.setConsumerEnv("test");
            entity.setOriginalMsgBody("{\"cbid\":25885374000570602,\"title\":\"歌王\",\"reason\":\"审核等级修改原因: 内容中确实涉及未成年性行为相关描写，男主前身设定为吸毒人员并对女主曾有过家暴行为，内容中也确实曾多处提及该设定。作品背景未架空，人民代表大会制度替换成君主立宪制。且多章涉及中华皇族作为配角的相关描写，建议架空背景并修改未成年涉性与男主前身涉毒的相关描写。作品主线为都市重生文娱文，男主带着原世界的文娱成果穿越到一个吸毒家暴男身上，利用另一时空的文娱成果，重新吸引被家暴的女主和成为平行世界的文娱第一人，最终和女主双双隐世；建议修改涉毒涉政内容 oldchecklevel15\",\"type\":3,\"operator\":\"麒麟\",\"createTime\":1736734191000}");
            entity.setMsgCbid(1345678908765L);
            entity.setMsgTitle("消息标题");
            entity.setMsgReason("消息理由");
            entity.setMsgType(3);
            entity.setMsgOperator("默认操作人");
            entity.setMsgCreateTime(1736759525000L);
            entity.setMsgCreateTimeStr("2025-01-13 17:13:00");

            long result = bookAdjustReasonMessageRepo.insertSelective(entity);

            if (result > 0) {
                return entity.getId();
            }

        } catch (Exception e) {
            logger.error("插入失败, ex=" + e.getMessage(), e);
        }

        return 0;
    }

    @Override
    public int updateBookCurrentCheckLevelAndAuditStatus(long id, int checkLevel, String auditStatus) {

        int result = 0;
        try {
            result = bookAdjustReasonMessageRepo.updateBookCurrentCheckLevelAndAuditStatus(id, checkLevel, auditStatus, 0);
        } catch (Exception e) {
            logger.error("更新书本信息和是否需要推送失败, ex=" + e.getMessage(), e);
        }

        return result;
    }

    @Override
    public int updateBookAdjustReasonPushResult(long id) {
        int result = 0;
        try {
            result = bookAdjustReasonMessageRepo.updateBookAdjustReasonPushResult(id,  1);
        } catch (Exception e) {
            logger.error("更新发送状态失败, ex=" + e.getMessage(), e);
        }

        return result;
    }

    @Override
    public List<BookAdjustReasonMessageEntity> queryMessageByCbid(long cbid) {
        List<BookAdjustReasonMessageEntity> entities = bookAdjustReasonMessageRepo.queryMessageByCbid(cbid);
        return CollectionUtils.isEmpty(entities) ? Collections.emptyList() : entities;
    }

}