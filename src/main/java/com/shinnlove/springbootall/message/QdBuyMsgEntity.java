/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.message;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author Tony Zhao
 * @version $Id: QdBuyMsgEntity.java, v 0.1 2024-07-30 10:26 Tony Zhao Exp $$
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class QdBuyMsgEntity implements Serializable {

    private String orderId;

    private long guid;

    private String eventType;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}