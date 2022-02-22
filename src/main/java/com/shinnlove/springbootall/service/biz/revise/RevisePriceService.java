/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.biz.revise;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: RevisePriceService.java, v 0.1 2022-02-10 2:48 PM Tony Zhao Exp $$
 */
public interface RevisePriceService {

    long submitMultipleRevise(BigDecimal before, BigDecimal after,
                              String operator);

    long submitRevise(int itemType, BigDecimal before, BigDecimal after, String operator);

    long auditRevise(int actionId, long refUniqueNo, int approve, String operator);

    Object pipelineAudit(int actionId);

}
