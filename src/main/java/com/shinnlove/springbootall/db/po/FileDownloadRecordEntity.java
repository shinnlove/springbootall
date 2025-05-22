package com.shinnlove.springbootall.db.po;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileDownloadRecordEntity implements Serializable {

    private Long id;

    private String activityId;

    private Long componentId;

    private String queryCondition;

    private String fileKey;

    private String fileName;

    private Long fileSize;

    private Byte status;

    private String createUser;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

}