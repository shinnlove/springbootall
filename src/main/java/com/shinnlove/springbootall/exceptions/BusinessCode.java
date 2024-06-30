/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Tony Zhao
 * @version $Id: BusinessCode.java, v 0.1 2024-06-30 19:56 Tony Zhao Exp $$
 */
@Getter
@AllArgsConstructor
public enum BusinessCode implements ErrorCodeProvider {

    // 一些通用服务层错误
    DB_ACCESS_THROW_ERROR(6001, "数据库访问直接抛错"),
    DB_EXECUTE_RETURN_ERROR(6002, "数据库sql返回执行结果失败"),
    DB_LOCK_STATUS_ERROR(6003, "已售卖商品不能再被锁定"),

    // 好友助力
    ASSIST_MISSING_HELP_GUID(12001, "好友助力缺少助力人"),
    ASSIST_CANNOT_HELP_SELF(12002, "不能给自己助力"),
    ASSIST_CODE_NOT_FOUND(12003, "邀请码无效"),
    ASSIST_CODE_SELECTION_EXPIRED(12004, "邀请码已失效(用户已重新选择商品)"),
    ASSIST_CODE_TIME_EXPIRED(12005, "好友邀请助力时间已结束(超过邀请码自身有效时间或活动结束)"),
    MISSING_CONFIGURED_ITEM_INFO(12006, "缺少可售卖商品的配置信息"),
    MISSING_ITEM_DEDUCT_PRICE(12007, "缺少商品可扣减金额标配"),
    ASSIST_CAN_ONLY_ONCE(12008, "每人只有1次助力机会哦"),
    ASSIST_ITEM_NO_STORAGE(12009, "助力商品库存不足"),
    ASSIST_REACHED_LOWEST_PRICE(12010, "邀请助力已结束"),
    ASSIST_ITEM_NO_CONFIGURED_STORAGE(12011, "助力失败，商品没有库存配置"),
    ASSIST_SYSTEM_ERROR(12012, "助力失败，系统异常，请稍后再试"),
    MISSING_ITEM_DEDUCT_PRICE_2(12013, "缺少商品可扣减金额非标配"),
    ASSIST_RANDOM_PRICE_ERROR(12014, "助力随机减价失败"),

    GENERATE_ASSIST_CODE_ERROR(13001, "生成助力码失败"),

    // 下单支付时商品与库存错误码
    SELECT_LOCK_ITEM_STORAGE_FAILED(13001, "库存锁定失败，请稍后再试"),
    SELECT_HAS_PAID(13002, "选择商品已支付"),

    // 订单
    ORDER_INFO_CREATE_FAILED(10201, "订单创建失败"),
    ORDER_INFO_CREATE_EXCEPTION(10202, "订单创建异常"),
    ORDER_INFO_SET_WAITING_FAILED(10203, "订单设置待支付失败"),
    ORDER_INFO_SET_WAITING_EXCEPTION(10204, "订单设置待支付异常"),
    ORDER_INFO_SET_EXPIRED_FAILED(10205, "订单设置过期失败"),
    ORDER_INFO_SET_EXPIRED_EXCEPTION(10206, "订单设置过期异常"),
    ORDER_INFO_SET_PAID_FAILED(10207, "订单设置已支付失败"),
    ORDER_INFO_SET_PAID_EXCEPTION(10208, "订单设置已支付异常"),
    ORDER_INFO_NOT_EXIST(10209, "订单不存在"),
    SELECTION_NOT_FOUND(10210, "商品选择未找到"),
    OUT_OF_TIME(10211, "已超时"),
    COMPONENT_NOT_ON_SALE(10212, "商品不在销售时间范围内"),
    ITEM_NOT_FOUND(10213, "商品未找到"),
    FAIL(10301, "消息队列日志创建失败"),

    ;

    private final int code;
    private final String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}