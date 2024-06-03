package com.shinnlove.springbootall.db.po;

import lombok.*;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @date: 2024/4/7 10:31
 * @author: Welles
 * @version: 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class ItemRecordEntity {

    // id
    private Long id;
    // 活动id
    private String activityId;
    // guid
    private Long guid;
    // 另一个guid
    private Long otherGuid;
    // 道具类型
    private Integer itemType;
    // 操作类型
    private String operateDesc;
    // 操作类型
    private Integer operateType;
    // 来源id
    private String sourceId;
    // 操作数量
    private Integer count;
    // 消息发送版本
    private Integer messageSentVersion;
    // 创建时间
    private Date createTime;

}