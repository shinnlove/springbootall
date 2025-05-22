/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.models;

import com.alibaba.fastjson.JSONObject;
import com.shinnlove.springbootall.enums.OrderStatusEnums;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @author Tony Zhao
 * @version $Id: MonthTicketOrderQuery.java, v 0.1 2025-03-13 14:29 Tony Zhao Exp $$
 */
@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonthTicketOrderQuery implements Serializable {

    /** 按guid查询 */
    private Long guid;

    /** 按orderNo查询 */
    private String orderNo;

    /** 按印鸽自定义id查询 */
    private String customizeNo;

    /** 按支付订单查询 */
    private String payOrderNo;

    /** 订单状态 */
    private String status;

    /** 是否升级流沙材质 */
    private String materialType;

    /** 是否赠送黄金 */
    private String gildSelected;

    /** 支付查询开始时间(秒) */
    private String startTime;

    /** 支付查询结束时间(秒) */
    private String endTime;

    private int page;
    private int perPage;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String getQueryConditionJson() {
        JSONObject object = new JSONObject();

        if (Objects.nonNull(guid)) {
            object.put("guid", guid);
        }

        if (StringUtils.isNotBlank(orderNo)) {
            object.put("orderNo", orderNo);
        }

        if (StringUtils.isNotBlank(customizeNo)) {
            object.put("customizeNo", customizeNo);
        }

        if (StringUtils.isNotBlank(payOrderNo)) {
            object.put("payOrderNo", payOrderNo);
        }

        if (StringUtils.isNotBlank(status)) {
            object.put("statusDesc", OrderStatusEnums.getDescByCode(Integer.parseInt(status)));
        }

        if (StringUtils.isNotBlank(materialType)) {
            object.put("materialType", materialType);
        }

        if (StringUtils.isNotBlank(gildSelected)) {
            object.put("gildSelected", gildSelected);
        }

        if (StringUtils.isNotBlank(startTime)) {
            long startTime2 = Long.parseLong(this.startTime);
            long startTimeLong = startTime2 * 1000;

            // 创建一个Date对象表示当前时间
            Date now = new Date(startTimeLong);

            // 创建SimpleDateFormat对象并指定格式
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            // 格式化Date对象为字符串
            String dateStr = sdf.format(now);

            object.put("startTime", dateStr);
        }

        if (StringUtils.isNotBlank(endTime)) {
            long endTime2 = Long.parseLong(this.endTime);
            long endTimeLong = endTime2 * 1000;

            // 创建一个Date对象表示当前时间
            Date now = new Date(endTimeLong);

            // 创建SimpleDateFormat对象并指定格式
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            // 格式化Date对象为字符串
            String dateStr = sdf.format(now);

            object.put("endTime", dateStr);
        }

        return object.toJSONString();
    }

}