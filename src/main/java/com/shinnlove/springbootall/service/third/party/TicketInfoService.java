/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.third.party;


import com.shinnlove.springbootall.util.dto.ServiceResult;
import com.shinnlove.springbootall.util.third.party.dto.CustomizeInfo;

/**
 * 票根信息查询服务接口。
 *
 * @author Tony Zhao
 * @version $Id: TicketInfoService.java, v 0.1 2025-03-03 15:47 Tony Zhao Exp $$
 */
public interface TicketInfoService {

    /**
     * 根据印鸽侧的自定义单号查询票根信息，来做信息校验比对。
     *
     * @param customizeNo       印鸽侧的自定义单号
     * @return                  请求回来的票根信息
     */
    ServiceResult<CustomizeInfo> queryTicketInfoByCustomizeNo(String customizeNo);

}

