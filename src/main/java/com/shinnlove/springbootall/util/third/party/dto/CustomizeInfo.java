/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.third.party.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tony Zhao
 * @version $Id: CustomizeInfo.java, v 0.1 2025-03-03 15:48 Tony Zhao Exp $$
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomizeInfo {

    /** 印鸽侧叫outSkuId；阅文侧叫stub_id、由month_ticket_id + month_ticket_card_no 唯一生成 */
    private String outSkuId;

    /** 书封 */
    private String bookCoverUrl;

    /** 书名 */
    private String bookName;

    /** 作者名 */
    private String authorName;

    /** 投票人名字 */
    private String voterName;

    /** 票号，用户可定制(可无) */
    private String ticketNumber;

    /** 投票日期 */
    private String voteDate;

}