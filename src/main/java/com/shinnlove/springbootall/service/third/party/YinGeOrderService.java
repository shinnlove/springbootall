/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.third.party;

import com.shinnlove.springbootall.util.third.party.dto.YinGeOrderInfo;
import com.shinnlove.springbootall.util.third.party.dto.YinGeOrderInterceptorInfo;
import com.shinnlove.springbootall.util.third.party.dto.YinGeResult;

/**
 * @author Tony Zhao
 * @version $Id: YinGeOrderService.java, v 0.1 2025-03-07 10:42 Tony Zhao Exp $$
 */
public interface YinGeOrderService {

    /**
     * @param outTradeNo
     * @return
     */
    YinGeResult<YinGeOrderInfo> queryOrderInfo(String outTradeNo);

    /**
     * @param interceptorInfo
     * @return
     */
    YinGeResult<Integer> orderIntercept(YinGeOrderInterceptorInfo interceptorInfo);


}
