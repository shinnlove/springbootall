package com.shinnlove.springbootall.dao;

import com.shinnlove.springbootall.dao.model.ProcessStatusMetadataPo;
import com.shinnlove.springbootall.dao.model.ProcessStatusMetadataPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProcessStatusMetadataDao {
    long countByExample(ProcessStatusMetadataPoExample example);

    int deleteByExample(ProcessStatusMetadataPoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProcessStatusMetadataPo record);

    int insertSelective(ProcessStatusMetadataPo record);

    List<ProcessStatusMetadataPo> selectByExample(ProcessStatusMetadataPoExample example);

    ProcessStatusMetadataPo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProcessStatusMetadataPo record, @Param("example") ProcessStatusMetadataPoExample example);

    int updateByExample(@Param("record") ProcessStatusMetadataPo record, @Param("example") ProcessStatusMetadataPoExample example);

    int updateByPrimaryKeySelective(ProcessStatusMetadataPo record);

    int updateByPrimaryKey(ProcessStatusMetadataPo record);
}