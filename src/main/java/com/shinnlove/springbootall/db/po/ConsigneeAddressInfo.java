/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.db.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Tony Zhao
 * @version $Id: ConsigneeAddressInfo.java, v 0.1 2025-02-28 15:41 Tony Zhao Exp $$
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsigneeAddressInfo implements Serializable {

    private long userAddressLogId;

    private String consigneeName;

    private String consigneeMobile;

    private String province;

    private String city;

    private String district;

    private String addressLine1;

    private String addressLine2;

    private static final long serialVersionUID = 1L;

}