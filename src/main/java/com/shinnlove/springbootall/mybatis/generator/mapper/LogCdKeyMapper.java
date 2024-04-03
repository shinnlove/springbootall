package com.shinnlove.springbootall.mybatis.generator.mapper;

import com.shinnlove.springbootall.mybatis.generator.model.LogCdKey;
import com.shinnlove.springbootall.mybatis.generator.model.LogCdKeyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface LogCdKeyMapper {
    long countByExample(LogCdKeyExample example);

    int deleteByExample(LogCdKeyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LogCdKey record);

    int insertSelective(LogCdKey record);

    List<LogCdKey> selectByExampleWithRowbounds(LogCdKeyExample example, RowBounds rowBounds);

    List<LogCdKey> selectByExample(LogCdKeyExample example);

    LogCdKey selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LogCdKey record, @Param("example") LogCdKeyExample example);

    int updateByExample(@Param("record") LogCdKey record, @Param("example") LogCdKeyExample example);

    int updateByPrimaryKeySelective(LogCdKey record);

    int updateByPrimaryKey(LogCdKey record);
}