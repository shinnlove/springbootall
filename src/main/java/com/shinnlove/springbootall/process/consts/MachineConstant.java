/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.consts;

/**
 * @author Tony Zhao
 * @version $Id: MachineConstant.java, v 0.1 2022-01-10 11:52 PM Tony Zhao Exp $$
 */
public final class MachineConstant {

    private MachineConstant() {

    }

    public static final String INVALID_PARAMETERS          = "invalid parameters.";

    public static final String DEFAULT_OPERATOR            = "CommercialOrder System";

    public static final String DEFAULT_REMARK              = "Auto reconcile by child process accomplished.";

    public static final String NO_PROCESS_IN_SYSTEM        = "不存在对应的业务流程";

    public static final String SOURCE_STATUS_INCORRECT     = "template_id/action_id起始变迁状态不符";

    public static final String STATUS_HAS_BLOCKING_PROCESS = "当前流程有Blocking流程没有达到终态无法推进";

}