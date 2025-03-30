/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.models;

import lombok.*;

/**
 * @author Tony Zhao
 * @version $Id: DownloadRecordQueryCondition.java, v 0.1 2025-03-26 15:43 Tony Zhao Exp $$
 */
@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DownloadRecordQueryCondition {

    /** 下载记录创建者 */
    private String createUser;

    /** 状态 */
    private Integer status;

    /** 偏移位 */
    private Integer offset;

    /** 数量限制 */
    private Integer limit;

}