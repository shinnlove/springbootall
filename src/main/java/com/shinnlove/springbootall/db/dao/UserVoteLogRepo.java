package com.shinnlove.springbootall.db.dao;

import com.shinnlove.springbootall.db.po.UserDailyStatEntity;
import com.shinnlove.springbootall.db.po.UserVoteLogEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface UserVoteLogRepo {

    long insertSelective(@Param("entity") UserDailyStatEntity entity);

    List<UserVoteLogEntity> queryUserVoteLogs(@Param(value = "activityId") String activityId,
                                              @Param(value = "componentId") Long componentId,
                                              @Param(value = "workId") Long workId);

}