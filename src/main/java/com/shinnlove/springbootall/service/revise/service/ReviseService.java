/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.revise.service;

import com.shinnlove.springbootall.service.revise.model.UpperReviseInfo;

/**
 * @author Tony Zhao
 * @version $Id: ReviseService.java, v 0.1 2022-02-24 5:54 PM Tony Zhao Exp $$
 */
public interface ReviseService {

    long submitRevise(UpperReviseInfo upperReviseInfo);

    long auditSuccess(long parentRefUniqueNo, int approve, String operator, String remark);

}
