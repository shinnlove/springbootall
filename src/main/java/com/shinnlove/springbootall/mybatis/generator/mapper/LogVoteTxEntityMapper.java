package com.shinnlove.springbootall.mybatis.generator.mapper;

import com.shinnlove.springbootall.mybatis.generator.model.LogVoteTxEntity;
import com.shinnlove.springbootall.mybatis.generator.model.LogVoteTxEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface LogVoteTxEntityMapper {
    long countByExample(LogVoteTxEntityExample example);

    int deleteByExample(LogVoteTxEntityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LogVoteTxEntity record);

    int insertSelective(LogVoteTxEntity record);

    List<LogVoteTxEntity> selectByExampleWithRowbounds(LogVoteTxEntityExample example, RowBounds rowBounds);

    List<LogVoteTxEntity> selectByExample(LogVoteTxEntityExample example);

    LogVoteTxEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LogVoteTxEntity record, @Param("example") LogVoteTxEntityExample example);

    int updateByExample(@Param("record") LogVoteTxEntity record, @Param("example") LogVoteTxEntityExample example);

    int updateByPrimaryKeySelective(LogVoteTxEntity record);

    int updateByPrimaryKey(LogVoteTxEntity record);
}