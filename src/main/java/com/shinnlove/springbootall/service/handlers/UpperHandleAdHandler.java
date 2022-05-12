/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.handlers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.bilibili.universal.dao.ProcessStatusLogDao;
import com.bilibili.universal.dao.UniversalProcessDao;
import com.bilibili.universal.dao.model.ProcessStatusLogPo;
import com.bilibili.universal.dao.model.UniversalProcessPo;
import com.bilibili.universal.dao.model.UniversalProcessPoExample;
import com.bilibili.universal.process.chain.ActionChain;
import com.bilibili.universal.process.interfaces.ActionHandler;
import com.bilibili.universal.process.model.context.DataContext;
import com.bilibili.universal.process.model.context.ProcessContext;
import com.bilibili.universal.util.log.LoggerUtil;
import com.shinnlove.springbootall.service.consts.ReviseConstant;
import com.shinnlove.springbootall.service.enums.*;
import com.shinnlove.springbootall.service.models.ReviseAttitude;
import com.shinnlove.springbootall.service.models.ReviseRegister;

/**
 * UP主拒绝广告主手动加一条消息。
 *
 * 实属无奈手动加的记录，@Deprecated。
 *
 * @author Tony Zhao
 * @version $Id: UpperHandleAdHandler.java, v 0.1 2022-03-24 10:46 PM Tony Zhao Exp $$
 */
@Deprecated
@Service
public class UpperHandleAdHandler implements ActionHandler<ReviseAttitude, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(UpperHandleAdHandler.class);

    @Autowired
    private UniversalProcessDao universalProcessDao;

    @Autowired
    private ProcessStatusLogDao processStatusLogDao;

    @Override
    public Integer process(ActionChain chain, ProcessContext<ReviseAttitude> context) {

        LoggerUtil.warn(logger, "AdAuthBindAvIdHandler handler executed");

        // 处理意见
        ReviseAttitude attitude = param(context);
        int att = attitude.getAttitude();

        // 变更目标起始，特别注意：这是父流程的
        int source = context.getSourceStatus();
        int destination = context.getDestinationStatus();

        if (ReviseStatus.UPPER_CONFIRM.getCode() != source) {
            chain.continue0();
        }

        // 操作员信息
        DataContext dataContext = context.getDataContext();
        int operatorType = dataContext.getOperatorType();
        long operatorId = dataContext.getOperatorId();
        String operator = dataContext.getOperator();
        String remark = dataContext.getRemark();

        long parentReviseNo = context.getRefUniqueNo();

        int sum = 0;

        for (ReviseRegister register : new ArrayList<ReviseRegister>()) {
            long reviseNo = register.getReviseNo();

            UniversalProcessPoExample example = new UniversalProcessPoExample();
            example.or().andRefUniqueNoEqualTo(reviseNo);

            List<UniversalProcessPo> pos = universalProcessDao.selectByExample(example);
            if (CollectionUtils.isEmpty(pos)) {
                continue;
            }

            UniversalProcessPo po = pos.get(0);
            long processNo = po.getProcessNo();
            int templateId = po.getTemplateId();

            ReviseItemType reviseItemType = ReviseItemType.getByTemplateId(templateId);
            int interestGroup = reviseItemType.getInterestGroup();

            if (ReviseInterestGroup.ADVERTISER.getCode() != interestGroup) {
                continue;
            }

            ProcessStatusLogPo manualLog = new ProcessStatusLogPo();

            manualLog.setProcessNo(processNo);
            manualLog.setTemplateId(templateId);
            manualLog.setActionId(-1);

            manualLog.setSourceStatus(source);
            manualLog.setDestinationStatus(destination);

            manualLog.setOperatorType(operatorType);
            manualLog.setOperatorId(operatorId);
            manualLog.setOperator(operator);
            manualLog.setRemark(remark);

            if (PickupOperatorType.OP_SYS.contains(operatorType)) {
                if (ReviseAttitudeType.APPROVE.getCode() == att) {
                    // 同意
                    manualLog.setRemark(ReviseConstant.ACCEPT_UP_REVISE_FROM_OP);
                } else {
                    // 拒绝
                    manualLog.setRemark(ReviseConstant.REJECT_UP_REVISE_FROM_OP);
                }
            }

            sum += processStatusLogDao.insertSelective(manualLog);

        } // for

        return sum;
    }

}