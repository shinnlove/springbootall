/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.core.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinnlove.springbootall.dao.ProcessBlockingDao;
import com.shinnlove.springbootall.dao.model.ProcessBlockingPo;
import com.shinnlove.springbootall.dao.model.ProcessBlockingPoExample;
import com.shinnlove.springbootall.process.core.ProcessBlockingCoreService;
import com.shinnlove.springbootall.process.model.blocking.ProcessBlocking;
import com.shinnlove.springbootall.util.common.AssertUtil;
import com.shinnlove.springbootall.util.common.CommonUtil;

/**
 * Process blocking core service for handling process blockings.
 *
 * @author Tony Zhao
 * @version $Id: ProcessBlockingCoreServiceImpl.java, v 0.1 2021-07-14 3:34 PM Tony Zhao Exp $$
 */
@Service
public class ProcessBlockingCoreServiceImpl implements ProcessBlockingCoreService {

    @Autowired
    private ProcessBlockingDao processBlockingDao;

    @Override
    public int addBlockingProcess2Target(ProcessBlocking blocking) {
        AssertUtil.isNotNull(blocking);
        AssertUtil.isNotNull(blocking.getMainProcessNo());
        AssertUtil.isNotNull(blocking.getObstacleByProcessNo());

        ProcessBlockingPo po = CommonUtil.copyObj(blocking, ProcessBlockingPo.class);
        return processBlockingDao.insertSelective(po);
    }

    @Override
    public int removeBlockingByMainAndObstacleNo(long mainProcessNo, long obstacleByProcessNo) {
        // build condition
        ProcessBlockingPoExample example = new ProcessBlockingPoExample();
        ProcessBlockingPoExample.Criteria criteria = example.or();

        criteria.andMainProcessNoEqualTo(mainProcessNo)
            .andObstacleByProcessNoEqualTo(obstacleByProcessNo);

        return processBlockingDao.deleteByExample(example);
    }

    @Override
    public int removeAllBlockingByMainNo(long mainProcessNo) {
        // build condition
        ProcessBlockingPoExample example = new ProcessBlockingPoExample();
        ProcessBlockingPoExample.Criteria criteria = example.or();

        criteria.andMainProcessNoEqualTo(mainProcessNo);

        return processBlockingDao.deleteByExample(example);
    }

    @Override
    public List<ProcessBlocking> getBlockingByProcessNo(long processNo) {
        // build condition
        ProcessBlockingPoExample example = new ProcessBlockingPoExample();
        ProcessBlockingPoExample.Criteria criteria = example.or();
        criteria.andMainProcessNoEqualTo(processNo);

        List<ProcessBlockingPo> pos = processBlockingDao.selectByExample(example);

        return CommonUtil.copyList(pos, ProcessBlocking.class);
    }

}