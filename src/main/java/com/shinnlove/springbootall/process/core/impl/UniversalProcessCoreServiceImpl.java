/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.core.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.shinnlove.springbootall.dao.UniversalProcessDao;
import com.shinnlove.springbootall.dao.ext.UniversalProcessExtDao;
import com.shinnlove.springbootall.dao.model.UniversalProcessPo;
import com.shinnlove.springbootall.dao.model.UniversalProcessPoExample;
import com.shinnlove.springbootall.process.core.UniversalProcessCoreService;
import com.shinnlove.springbootall.process.model.process.UniversalProcess;
import com.shinnlove.springbootall.util.common.AssertUtil;
import com.shinnlove.springbootall.util.common.CommonUtil;

/**
 * The core service implementation of process machine.
 *
 * @description 流程状态服务
 * @author caowei
 * @date 2021/7/9 7:09 下午
 **/
//@Service
public class UniversalProcessCoreServiceImpl implements UniversalProcessCoreService {

    @Autowired
    private UniversalProcessDao    universalProcessDao;

    @Autowired
    private UniversalProcessExtDao universalProcessExtDao;

    @Override
    public long addProcess(UniversalProcess universalProcess) {
        AssertUtil.isNotNull(universalProcess);
        AssertUtil.isNotNull(universalProcess.getProcessNo());

        UniversalProcessPo po = CommonUtil.copyObj(universalProcess, UniversalProcessPo.class);
        universalProcessDao.insertSelective(po);

        return po.getId();
    }

    @Override
    public UniversalProcess getProcessByNo(long processNo) {
        // build query example
        UniversalProcessPoExample example = new UniversalProcessPoExample();
        UniversalProcessPoExample.Criteria criteria = example.or();
        // query by unique process no
        criteria.andProcessNoEqualTo(processNo);

        List<UniversalProcessPo> pos = universalProcessDao.selectByExample(example);
        if (CollectionUtils.isEmpty(pos)) {
            return null;
        }

        // if collection is not empty, only need the first one since unique
        return CommonUtil.copyObj(pos.get(0), UniversalProcess.class);
    }

    @Override
    public List<UniversalProcess> getProcessListByRefUniqueNos(List<Long> refUniqueNos) {
        // build query conditions
        UniversalProcessPoExample example = new UniversalProcessPoExample();
        UniversalProcessPoExample.Criteria criteria = example.or();
        criteria.andRefUniqueNoIn(refUniqueNos);

        List<UniversalProcessPo> pos = universalProcessDao.selectByExample(example);
        return CommonUtil.copyList(pos, UniversalProcess.class);
    }

    @Override
    public List<UniversalProcess> getProcessListByParentProcessNo(long parentProcessNo) {
        // build query condition
        UniversalProcessPoExample example = new UniversalProcessPoExample();
        UniversalProcessPoExample.Criteria criteria = example.or();
        criteria.andParentProcessNoEqualTo(parentProcessNo);

        List<UniversalProcessPo> pos = universalProcessDao.selectByExample(example);
        return CommonUtil.copyList(pos, UniversalProcess.class);
    }

    @Override
    public UniversalProcess getProcessByRefUniqueNo(long refUniqueNo, boolean lock) {
        UniversalProcessPo po = null;
        if (lock) {
            po = universalProcessExtDao.selectByRefUniqueNoForUpdate(refUniqueNo);
        } else {
            // common query
            UniversalProcessPoExample example = new UniversalProcessPoExample();
            UniversalProcessPoExample.Criteria criteria = example.or();
            criteria.andRefUniqueNoEqualTo(refUniqueNo);

            List<UniversalProcessPo> pos = universalProcessDao.selectByExample(example);
            if (!CollectionUtils.isEmpty(pos)) {
                po = pos.get(0);
            }
        }
        return CommonUtil.copyObj(po, UniversalProcess.class);
    }

    @Override
    public UniversalProcess lockProcessByProcessNo(long processNo) {
        UniversalProcessPo po = universalProcessExtDao.selectByProcessNoForUpdate(processNo);
        return CommonUtil.copyObj(po, UniversalProcess.class);
    }

    @Override
    public int updateProcessStatus(long processNo, int sourceStatus, int destinationStatus) {
        // query original process
        UniversalProcess uProcess = getProcessByNo(processNo);
        UniversalProcessPo po = CommonUtil.copyObj(uProcess, UniversalProcessPo.class);
        // set to current status
        po.setCurrentStatus(destinationStatus);

        // where condition
        UniversalProcessPoExample example = new UniversalProcessPoExample();
        UniversalProcessPoExample.Criteria criteria = example.or();
        criteria.andProcessNoEqualTo(processNo);

        return universalProcessDao.updateByExampleSelective(po, example);
    }

}
