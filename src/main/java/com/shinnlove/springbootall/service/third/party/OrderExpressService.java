/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.third.party;

import com.shinnlove.springbootall.exceptions.DBAccessThrowException;
import com.shinnlove.springbootall.exceptions.DBExecuteReturnException;
import com.shinnlove.springbootall.util.third.party.dto.LogisticsExpressTrace;

import java.util.List;

/**
 * 订单快递服务接口。
 *
 * @author Tony Zhao
 * @version $Id: OrderExpressService.java, v 0.1 2025-02-28 16:19 Tony Zhao Exp $$
 */
public interface OrderExpressService {

    /**
     * 删除过期的物流信息。
     *
     * @param expressNo
     * @return
     * @throws DBAccessThrowException
     * @throws DBExecuteReturnException
     */
    int deleteOldExpressLogs(String expressNo) throws DBAccessThrowException, DBExecuteReturnException;

    /**
     * 写入最新的物流信息。
     *
     * @param expressNo
     * @param traces
     * @return
     * @throws DBAccessThrowException
     * @throws DBExecuteReturnException
     */
    int storeLogisticsInfo(String expressNo, List<LogisticsExpressTrace> traces) throws DBAccessThrowException, DBExecuteReturnException;

}
