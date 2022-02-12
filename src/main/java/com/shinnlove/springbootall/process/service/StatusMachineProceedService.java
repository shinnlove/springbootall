/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.service;

import com.shinnlove.springbootall.process.callback.ProcessCallback;
import com.shinnlove.springbootall.process.model.context.DataContext;
import com.shinnlove.springbootall.process.model.context.ProcessContext;

/**
 * @author Tony Zhao
 * @version $Id: StatusMachineProceedService.java, v 0.1 2022-02-10 11:55 AM Tony Zhao Exp $$
 */
public interface StatusMachineProceedService {

    ProcessContext proceedProcess(final int actionId, final long refUniqueNo,
                                  final DataContext dataContext);

    ProcessContext proceedProcess(final int actionId, final long refUniqueNo,
                                  final DataContext dataContext, final ProcessCallback callback);

}
