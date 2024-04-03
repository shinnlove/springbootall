package com.shinnlove.springbootall.mybatis.generator.mapper;

import com.shinnlove.springbootall.mybatis.generator.model.LogActivity;
import com.shinnlove.springbootall.mybatis.generator.model.LogActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface LogActivityMapper {
    long countByExample(LogActivityExample example);

    int deleteByExample(LogActivityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LogActivity record);

    int insertSelective(LogActivity record);

    List<LogActivity> selectByExampleWithRowbounds(LogActivityExample example, RowBounds rowBounds);

    List<LogActivity> selectByExample(LogActivityExample example);

    LogActivity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LogActivity record, @Param("example") LogActivityExample example);

    int updateByExample(@Param("record") LogActivity record, @Param("example") LogActivityExample example);

    int updateByPrimaryKeySelective(LogActivity record);

    int updateByPrimaryKey(LogActivity record);
}