/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.core;


import com.shinnlove.springbootall.process.model.blocking.ProcessBlocking;

import java.util.List;

/**
 * Process blocking core service interface.
 *
 * @author Tony Zhao
 * @version $Id: ProcessBlockingCoreService.java, v 0.1 2021-07-14 3:19 PM Tony Zhao Exp $$
 */
public interface ProcessBlockingCoreService {

    /**
     * Add blocking process to target.
     *
     * @param blocking
     * @return
     */
    int addBlockingProcess2Target(ProcessBlocking blocking);

    /**
     * Remove one specific process blocking from main process by given obstacle process no.
     *
     * @param mainProcessNo                 the main process No.
     * @param obstacleByProcessNo           the obstacle process No.
     * @return
     */
    int removeBlockingByMainAndObstacleNo(long mainProcessNo, long obstacleByProcessNo);

    /**
     * Remove all process blocking from main process.
     *
     * @param mainProcessNo
     * @return
     */
    int removeAllBlockingByMainNo(long mainProcessNo);

    /**
     * Get blocking process by process No.
     *
     * @param processNo
     * @return
     */
    List<ProcessBlocking> getBlockingByProcessNo(long processNo);

}
