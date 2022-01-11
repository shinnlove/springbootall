package com.shinnlove.springbootall.dao;

import com.shinnlove.springbootall.dao.model.ProcessActionMetadataPo;
import com.shinnlove.springbootall.dao.model.ProcessActionMetadataPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProcessActionMetadataDao {
    long countByExample(ProcessActionMetadataPoExample example);

    int deleteByExample(ProcessActionMetadataPoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProcessActionMetadataPo record);

    int insertSelective(ProcessActionMetadataPo record);

    List<ProcessActionMetadataPo> selectByExample(ProcessActionMetadataPoExample example);

    ProcessActionMetadataPo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProcessActionMetadataPo record, @Param("example") ProcessActionMetadataPoExample example);

    int updateByExample(@Param("record") ProcessActionMetadataPo record, @Param("example") ProcessActionMetadataPoExample example);

    int updateByPrimaryKeySelective(ProcessActionMetadataPo record);

    int updateByPrimaryKey(ProcessActionMetadataPo record);
}