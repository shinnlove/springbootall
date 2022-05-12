/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.enums;

import java.util.Arrays;
import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: PickupOperatorType.java, v 0.1 2022-05-12 10:35 AM Tony Zhao Exp $$
 */
public enum PickupOperatorType {

    /** 商单系统 - 商业平台 */
    COMMERCIAL_SYSTEM(-1, "平台"),

    /**
     * 品牌主
     */
    ADVERTISERS(0, "品牌主"),

    /**
     * 运营人员
     * 提供刚给cpm-mng的服务接口时，会使用到此枚举值
     */
    OPERATING_PERSONNEL(1, "运营人员"),

    /**
     * 系统
     * 通常由JOB调用所触发
     */
    SYSTEM(2, "系统"),

    /**
     * UP主
     */
    UP(3, "up主"),

    /**
     * 代理商
     * 由代理商系统直接跳转进入花火
     */
    AGENT(4, "代理商"),

    /**
     * 二代-投放管理员
     * 对应 CrmSecondaryAgentRolePo.roleType = 3
     */
    AGENT_LAUNCH_ADMIN(6, "投放管理员"),

    /**
     * 二代-运营人员
     * 对应 CrmSecondaryAgentRolePo.roleType = 4
     */
    SECONDARY_AGENT(7, "二级代理商"),

    /**
     * MCN
     */
    MCN(8, "MCN"),

    /** 客户(临时使用一下) */
    CUSTOMER(98, "客户"),

    /**
     * 服务商
     */
    SERVICE_PROVIDER(99, "服务商"),

    /**
     * 新客
     */
    VISITOR(100, "新客");

    private final Integer code;
    private final String  desc;

    PickupOperatorType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static PickupOperatorType getByCode(Integer code) {
        if (code != null) {
            for (PickupOperatorType operatorType : PickupOperatorType.values()) {
                if (code.equals(operatorType.code)) {
                    return operatorType;
                }
            }
        }
        return null;
    }

    /**
     * for robust..
     *
     * @param code
     * @return
     */
    public static PickupOperatorType getByCodeDefault(Integer code) {
        if (code != null) {
            for (PickupOperatorType operatorType : PickupOperatorType.values()) {
                if (code.equals(operatorType.code)) {
                    return operatorType;
                }
            }
        }
        return COMMERCIAL_SYSTEM;
    }

    public static final List<Integer> getAllSecondaryAgentAndAgent() {
        return Arrays.asList(
                AGENT.code,
                AGENT_LAUNCH_ADMIN.code,
                SECONDARY_AGENT.code
        );
    }

    public static final List<Integer> getAllSecondaryAgent() {
        return Arrays.asList(
                AGENT_LAUNCH_ADMIN.code,
                SECONDARY_AGENT.code
        );
    }

    public static final List<Integer> getAllAd() {
        return Arrays.asList(
                ADVERTISERS.code,
                AGENT.code,
                AGENT_LAUNCH_ADMIN.code,
                SECONDARY_AGENT.code
        );
    }

    public static final List<Integer> getAllUp() {
        return Arrays.asList(
                UP.code,
                MCN.code,
                SERVICE_PROVIDER.code
        );
    }

    public static final List<Integer> OP_SYS = Arrays.asList(COMMERCIAL_SYSTEM.code, OPERATING_PERSONNEL.code);

    public static final boolean belongToSecondaryAgent(Integer code) {
        return getAllSecondaryAgent().contains(code);
    }

    public static final boolean belongToAgentOrSecondaryAgent(Integer code) {
        return getAllSecondaryAgentAndAgent().contains(code);
    }
}