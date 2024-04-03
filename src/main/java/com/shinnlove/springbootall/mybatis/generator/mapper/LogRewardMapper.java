package com.shinnlove.springbootall.mybatis.generator.mapper;

import com.shinnlove.springbootall.mybatis.generator.model.LogReward;
import com.shinnlove.springbootall.mybatis.generator.model.LogRewardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface LogRewardMapper {
    long countByExample(LogRewardExample example);

    int deleteByExample(LogRewardExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LogReward record);

    int insertSelective(LogReward record);

    List<LogReward> selectByExampleWithRowbounds(LogRewardExample example, RowBounds rowBounds);

    List<LogReward> selectByExample(LogRewardExample example);

    LogReward selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LogReward record, @Param("example") LogRewardExample example);

    int updateByExample(@Param("record") LogReward record, @Param("example") LogRewardExample example);

    int updateByPrimaryKeySelective(LogReward record);

    int updateByPrimaryKey(LogReward record);
}