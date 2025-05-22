/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.third.party.impl;

/**
 * @author Tony Zhao
 * @version $Id: OrderExpressServiceImpl.java, v 0.1 2025-03-07 18:21 Tony Zhao Exp $$
 */

import com.shinnlove.springbootall.db.dao.PaidOrderLogisticsDetailLogRepo;
import com.shinnlove.springbootall.db.po.PaidOrderLogisticsDetailLogEntity;
import com.shinnlove.springbootall.service.third.party.OrderExpressService;
import com.shinnlove.springbootall.util.third.party.dto.LogisticsExpressTrace;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单快递服务实现。
 *
 * @author Tony Zhao
 * @version $Id: OrderExpressServiceImpl.java, v 0.1 2025-03-04 19:50 Tony Zhao Exp $$
 */
@Service
public class OrderExpressServiceImpl implements OrderExpressService {

    private static final Logger logger = LoggerFactory.getLogger(OrderExpressServiceImpl.class);

    /** 支付订单物流仓储 */
    @Resource
    private PaidOrderLogisticsDetailLogRepo paidOrderLogisticsDetailLogRepo;

    /**
     * @see OrderExpressService#deleteOldExpressLogs(String)
     */
    public int deleteOldExpressLogs(String expressNo) {
        return paidOrderLogisticsDetailLogRepo.deleteOldExpressLogs(expressNo);
    }

    /**
     * @see OrderExpressService#storeLogisticsInfo(String, List)
     */
    public int storeLogisticsInfo(String expressNo, List<LogisticsExpressTrace> traces) {

        if (CollectionUtils.isEmpty(traces)) {
            return 0;
        }

        List<PaidOrderLogisticsDetailLogEntity> entities = new ArrayList<>();

        int i = 1;
        for (LogisticsExpressTrace trace : traces) {
            PaidOrderLogisticsDetailLogEntity entity = new PaidOrderLogisticsDetailLogEntity();
            entity.setExpressNo(expressNo);
            entity.setTime(trace.getTime());  // 假设 trace.getTime() 返回的是 Date 类型
            entity.setContext(trace.getContext());
            entity.setStatus(trace.getStatus());
            entity.setCity(trace.getCity());
            entity.setLogOrder(i++);
            entity.setIsDeleted(0);  // 默认值

            entities.add(entity);
        }

        try {
            return paidOrderLogisticsDetailLogRepo.batchInsert(entities);
        } catch (Exception e) {
            logger.error("批量插入物流信息失败", e);
            return 0;
        }
    }

}