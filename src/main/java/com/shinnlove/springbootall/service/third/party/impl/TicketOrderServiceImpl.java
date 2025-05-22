/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.third.party.impl;

import com.shinnlove.springbootall.db.dao.MonthTicketCardSellOrderInfoRepo;
import com.shinnlove.springbootall.db.po.MonthTicketCardSellOrderInfoEntity;
import com.shinnlove.springbootall.enums.MaterialTypeEnums;
import com.shinnlove.springbootall.enums.OrderStatusEnums;
import com.shinnlove.springbootall.enums.GildSelectedEnum;
import com.shinnlove.springbootall.exceptions.DBAccessThrowException;
import com.shinnlove.springbootall.exceptions.DBExecuteReturnException;
import com.shinnlove.springbootall.models.MonthTicketOrderQuery;
import com.shinnlove.springbootall.models.MonthTicketSellOrderInfo;
import com.shinnlove.springbootall.models.OrderQueryCondition;
import com.shinnlove.springbootall.service.third.party.TicketOrderService;
import com.shinnlove.springbootall.util.constants.Biz3rdPartyConstant;
import com.shinnlove.springbootall.util.converter.OrderInfoConverter;
import com.shinnlove.springbootall.util.third.party.dto.LogisticsCompanyInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Tony Zhao
 * @version $Id: TicketOrderServiceImpl.java, v 0.1 2025-03-07 10:45 Tony Zhao Exp $$
 */
@Service
public class TicketOrderServiceImpl implements TicketOrderService {

    @Autowired
    private MonthTicketCardSellOrderInfoRepo monthTicketCardSellOrderInfoRepo;

    @Override
    public MonthTicketCardSellOrderInfoEntity queryOrderInfoByOrderNo(Long orderNo) throws DBAccessThrowException, DBExecuteReturnException {
        return monthTicketCardSellOrderInfoRepo.queryOrderByOrderNo(orderNo);
    }

    public Integer updateOrderWithExpressInfo(Long orderNo, LogisticsCompanyInfo logisticsCompanyInfo) {

        String companyCode = logisticsCompanyInfo.getCompanyCode();
        String expressNo = logisticsCompanyInfo.getExpressNo();

        return monthTicketCardSellOrderInfoRepo.updateExpressInfoByOrderNo(orderNo, expressNo, companyCode);
    }

    /**
     * @see TicketOrderService#pageQueryOrderInfoByCondition(MonthTicketOrderQuery)
     */
    @Override
    public List<MonthTicketSellOrderInfo> pageQueryOrderInfoByCondition(MonthTicketOrderQuery query) {
        if (Objects.isNull(query)) {
            return Collections.emptyList();
        }

        OrderQueryCondition condition = new OrderQueryCondition();

        // 基础5个查询条件

        if (Objects.nonNull(query.getCustomizeNo())) {
            condition.setCustomizeNo(query.getCustomizeNo());
        }

        if (StringUtils.isNotBlank(query.getOrderNo())) {
            try {
                condition.setOrderNo(Long.parseLong(query.getOrderNo()));
            } catch (Exception e) {
                // ignore, 视作无限制订单号查询
            }
        }

        if (Objects.nonNull(query.getPayOrderNo())) {
            condition.setPayOrderNo(query.getPayOrderNo());
        }

        if (Objects.nonNull(query.getGuid())) {
            condition.setGuid(query.getGuid());
        }

        if (StringUtils.isNotBlank(query.getStatus())) {
            try {
                int code = Integer.parseInt(query.getStatus());
                if (code >= 0) {
                    // 有状态查询条件
                    OrderStatusEnums statusEnums = OrderStatusEnums.getByCode(code);
                    condition.setStatus(statusEnums.getCode());
                }
            } catch (Exception e) {
                // ignore, 视作无限制状态查询
            }
        }

        // 进阶选择框查询条件

        // 是否材质升级
        if (StringUtils.isNotBlank(query.getMaterialType())
                && !Biz3rdPartyConstant.NO_LIMITATION.equalsIgnoreCase(query.getMaterialType())
                && MaterialTypeEnums.ALL_MATERIAL_TYPE_NAMES.contains(query.getMaterialType())) {
            // 需要查询材质
            MaterialTypeEnums materialTypeEnums = MaterialTypeEnums.getByName(query.getMaterialType());
            if (Objects.nonNull(materialTypeEnums)) {
                // 材质名称传递正确才允许查询
                condition.setMaterialType(materialTypeEnums.getCode());
            }
        }

        // 是否烫金
        if (StringUtils.isNotBlank(query.getGildSelected())
                && !Biz3rdPartyConstant.NO_LIMITATION.equalsIgnoreCase(query.getGildSelected())) {
            GildSelectedEnum gildSelectedEnum = GildSelectedEnum.getByName(query.getGildSelected());
            if (Objects.nonNull(gildSelectedEnum)) {
                // 黄金赠品传递正确才允许查询
                condition.setGildSelected(gildSelectedEnum.getCode());
            }
        }

        // 增加时间范围查询条件
        if (StringUtils.isNotBlank(query.getStartTime())) {
            long startTimeSeconds = Long.parseLong(query.getStartTime());
            long startTimeMilliseconds = startTimeSeconds * 1000;
            condition.setStartTime(new Timestamp(startTimeMilliseconds));
        }

        if (StringUtils.isNotBlank(query.getEndTime())) {
            long endTimeSeconds = Long.parseLong(query.getEndTime());
            long endTimeMilliseconds = endTimeSeconds * 1000;
            condition.setEndTime(new Timestamp(endTimeMilliseconds));
        }

        // 先统计数据库有多少条满足条件
        long total = monthTicketCardSellOrderInfoRepo.countOrderByCondition(condition);

        if (total <= 0) {
            // 没有命中查询条件
            return Collections.emptyList();
        }

        // 命中的情况下计算分页
        int page = query.getPage();
        int pageSize = query.getPerPage();

        // 偏移量和查询窗口
        int offset = (page - 1) * pageSize;
        int limit = pageSize;

        condition.setOffset(offset);
        condition.setLimit(limit);

        // page query
        List<MonthTicketCardSellOrderInfoEntity> orders = monthTicketCardSellOrderInfoRepo
                .pageQueryOrderByCondition(condition);

        if (CollectionUtils.isEmpty(orders)) {
            return Collections.emptyList();
        }

        return orders.stream()
                .filter(Objects::nonNull)
                .map(OrderInfoConverter::convert2OrderDTO)
                .collect(Collectors.toList());
    }

}