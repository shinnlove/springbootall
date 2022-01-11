package com.shinnlove.springbootall.dao;

import com.shinnlove.springbootall.dao.model.ProcessStatusLogPo;
import com.shinnlove.springbootall.dao.model.ProcessStatusLogPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProcessStatusLogDao {
    long countByExample(ProcessStatusLogPoExample example);

    int deleteByExample(ProcessStatusLogPoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProcessStatusLogPo record);

    int insertSelective(ProcessStatusLogPo record);

    List<ProcessStatusLogPo> selectByExample(ProcessStatusLogPoExample example);

    ProcessStatusLogPo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProcessStatusLogPo record, @Param("example") ProcessStatusLogPoExample example);

    int updateByExample(@Param("record") ProcessStatusLogPo record, @Param("example") ProcessStatusLogPoExample example);

    int updateByPrimaryKeySelective(ProcessStatusLogPo record);

    int updateByPrimaryKey(ProcessStatusLogPo record);
}