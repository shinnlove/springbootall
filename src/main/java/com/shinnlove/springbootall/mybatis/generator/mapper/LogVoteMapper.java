package com.shinnlove.springbootall.mybatis.generator.mapper;

import com.shinnlove.springbootall.mybatis.generator.model.LogVote;
import com.shinnlove.springbootall.mybatis.generator.model.LogVoteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface LogVoteMapper {
    long countByExample(LogVoteExample example);

    int deleteByExample(LogVoteExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LogVote record);

    int insertSelective(LogVote record);

    List<LogVote> selectByExampleWithRowbounds(LogVoteExample example, RowBounds rowBounds);

    List<LogVote> selectByExample(LogVoteExample example);

    LogVote selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LogVote record, @Param("example") LogVoteExample example);

    int updateByExample(@Param("record") LogVote record, @Param("example") LogVoteExample example);

    int updateByPrimaryKeySelective(LogVote record);

    int updateByPrimaryKey(LogVote record);
}