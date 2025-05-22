package com.shinnlove.springbootall.db.dao;

import com.shinnlove.springbootall.db.po.FileDownloadRecordEntity;
import com.shinnlove.springbootall.models.DownloadRecordQueryCondition;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileDownloadRecordRepo {

    /**
     * 保存下载记录。
     *
     * @param record
     * @return
     */
    long insertSelective(@Param("entity") FileDownloadRecordEntity record);

    /**
     * 更新下载记录状态
     *
     * @param status
     * @param fileSize
     * @param id
     * @return
     */
    int updateDownloadFileStatus(@Param(value = "status") Integer status,
                                 @Param(value = "fileSize") Long fileSize,
                                 @Param(value = "id") Long id);

    /**
     * 按查询条件统计下载记录数量，用作分页。
     *
     * @param condition
     * @return
     */
    long countDownloadRecordByCondition(@Param("condition") DownloadRecordQueryCondition condition);

    /**
     * 按查询条件分页查询下载记录。
     *
     * @param condition
     * @return
     */
    List<FileDownloadRecordEntity> pageQueryDownloadRecordByCondition(@Param("condition") DownloadRecordQueryCondition condition);

}