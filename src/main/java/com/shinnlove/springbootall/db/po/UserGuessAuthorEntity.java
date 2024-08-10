package com.shinnlove.springbootall.db.po;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class UserGuessAuthorEntity implements Serializable {
    private Long id;

    private String activityId;

    private Long componentId;

    private Long guid;

    private Long cbid;

    private Integer guessRevealIdentity;

    private Long guessAuthorId;

    private String guessAuthorName;

    private Integer betMultiplier;

    private Integer rewardTaken;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

}