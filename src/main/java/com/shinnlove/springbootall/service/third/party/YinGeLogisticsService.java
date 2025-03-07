/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.third.party;


import com.shinnlove.springbootall.util.third.party.dto.YinGeLogisticsInfo;
import com.shinnlove.springbootall.util.third.party.dto.YinGeResult;

/**
 * 印鸽物流对接处理服务接口。
 *
 * @author Tony Zhao
 * @version $Id: YinGeLogisticsService.java, v 0.1 2025-03-04 17:18 Tony Zhao Exp $$
 */
public interface YinGeLogisticsService {

    /**
     * 印鸽同步物流信息。
     *
     * @param logisticsInfo
     * @return
     */
    YinGeResult<Integer> logisticsNotify(YinGeLogisticsInfo logisticsInfo);

}
