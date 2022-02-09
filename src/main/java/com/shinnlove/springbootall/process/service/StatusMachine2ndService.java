/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.service;

import com.shinnlove.springbootall.process.callback.ProcessCallback;
import com.shinnlove.springbootall.process.model.context.DataContext;

/**
 * @author Tony Zhao
 * @version $Id: StatusMachine2ndService.java, v 0.1 2022-02-09 5:43 PM Tony Zhao Exp $$
 */
public interface StatusMachine2ndService {

    long initProcess(int templateId, int destination, long refUniqueNo,
                     final DataContext dataContext);

    long initProcess(int templateId, int destination, long refUniqueNo,
                     final DataContext dataContext, final ProcessCallback callback);

    long proceedProcess(final int actionId, long refUniqueNo, final DataContext dataContext);

    long proceedProcess(final int actionId, long refUniqueNo, final DataContext dataContext,
                        final ProcessCallback callback);

}
