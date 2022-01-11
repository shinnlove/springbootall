package com.shinnlove.springbootall.dao;

import com.shinnlove.springbootall.dao.model.ProcessBlockingPo;
import com.shinnlove.springbootall.dao.model.ProcessBlockingPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProcessBlockingDao {
    long countByExample(ProcessBlockingPoExample example);

    int deleteByExample(ProcessBlockingPoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProcessBlockingPo record);

    int insertSelective(ProcessBlockingPo record);

    List<ProcessBlockingPo> selectByExample(ProcessBlockingPoExample example);

    ProcessBlockingPo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProcessBlockingPo record, @Param("example") ProcessBlockingPoExample example);

    int updateByExample(@Param("record") ProcessBlockingPo record, @Param("example") ProcessBlockingPoExample example);

    int updateByPrimaryKeySelective(ProcessBlockingPo record);

    int updateByPrimaryKey(ProcessBlockingPo record);
}