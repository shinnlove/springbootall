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
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单快递服务实现。
 *
 * @author Tony Zhao
 * @version $Id: OrderExpressServiceImpl.java, v 0.1 2025-03-04 19:50 Tony Zhao Exp $$
 */
@Service
public class OrderExpressServiceImpl implements OrderExpressService {

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

        // todo: add a batch insert here

        int total = 0;
        for (LogisticsExpressTrace trace : traces) {
            PaidOrderLogisticsDetailLogEntity entity = new PaidOrderLogisticsDetailLogEntity();
            entity.setExpressNo(expressNo);
            // todo: change time field type
//            entity.setTime(trace.getTime());
            entity.setContext(trace.getContext());
            entity.setStatus(trace.getStatus());
            entity.setCity(trace.getCity());

            try {
                total += paidOrderLogisticsDetailLogRepo.insertSelective(entity);
            } catch (Exception e) {
                // todo: add logs here...
            }
        }

        return total;
    }

}