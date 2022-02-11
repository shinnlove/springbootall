/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shinnlove.springbootall.process.chain.ActionChain;
import com.shinnlove.springbootall.process.handler.interfaces.ActionHandler;
import com.shinnlove.springbootall.process.model.context.ProcessContext;
import com.shinnlove.springbootall.service.enums.MissionProcessStatus;
import com.shinnlove.springbootall.service.models.RecruitMission;
import com.shinnlove.springbootall.util.log.LoggerUtil;

/**
 * @author Tony Zhao
 * @version $Id: AccomplishMissionHandler.java, v 0.1 2021-07-22 10:54 AM Tony Zhao Exp $$
 */
@Service
public class AccomplishMissionHandler implements ActionHandler<RecruitMission, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(AccomplishMissionHandler.class);

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Integer process(ActionChain chain, ProcessContext context) {
        LoggerUtil.info(logger, "AccomplishMissionHandler begin to execute, context", context);

        RecruitMission recruitMission = params(context);
        recruitMission.setMissionProcessStatus(MissionProcessStatus.OVER.getCode());

        int result = -1;

        // use data
        Integer o = result(context, AccomplishMissionHandler.class);
        System.out.println(o);

        return result;
    }

}