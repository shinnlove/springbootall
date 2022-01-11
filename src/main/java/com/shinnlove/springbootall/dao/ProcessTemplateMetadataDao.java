package com.shinnlove.springbootall.dao;

import com.shinnlove.springbootall.dao.model.ProcessTemplateMetadataPo;
import com.shinnlove.springbootall.dao.model.ProcessTemplateMetadataPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProcessTemplateMetadataDao {
    long countByExample(ProcessTemplateMetadataPoExample example);

    int deleteByExample(ProcessTemplateMetadataPoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProcessTemplateMetadataPo record);

    int insertSelective(ProcessTemplateMetadataPo record);

    List<ProcessTemplateMetadataPo> selectByExample(ProcessTemplateMetadataPoExample example);

    ProcessTemplateMetadataPo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProcessTemplateMetadataPo record, @Param("example") ProcessTemplateMetadataPoExample example);

    int updateByExample(@Param("record") ProcessTemplateMetadataPo record, @Param("example") ProcessTemplateMetadataPoExample example);

    int updateByPrimaryKeySelective(ProcessTemplateMetadataPo record);

    int updateByPrimaryKey(ProcessTemplateMetadataPo record);
}