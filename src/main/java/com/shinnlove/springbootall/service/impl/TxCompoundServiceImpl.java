/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.po.UserFragmentCollectEntity;
import com.shinnlove.springbootall.service.TxCompoundService;
import com.shinnlove.springbootall.service.UserCompoundRecordService;
import com.shinnlove.springbootall.service.UserFragmentCollectService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author Tony Zhao
 * @version $Id: TxCompoundServiceImpl.java, v 0.1 2024-04-26 10:41 Tony Zhao Exp $$
 */
@Service
public class TxCompoundServiceImpl implements TxCompoundService {

    @Autowired
    private UserFragmentCollectService  userFragmentCollectService;

    @Autowired
    private UserCompoundRecordService   userCompoundRecordService;

    @Autowired
    private TransactionTemplate         transactionTemplate;

    @Override
    public Integer compound(String activityId, Long componentId, Long guid) {

        int thisTimeCount = 3;

        // query unused
        List<UserFragmentCollectEntity> unused = userFragmentCollectService
                .queryUnUsedFragments(activityId, componentId, guid);

        if (CollectionUtils.isEmpty(unused)) {
            return 0;
        }

        // reflect: fragmentId => ids
        Map<Integer, List<Long>> typeIdsMap = unused.stream()
                .collect(Collectors.groupingBy(UserFragmentCollectEntity::getFragmentId,
                        Collectors.mapping(UserFragmentCollectEntity::getId, Collectors.toList())));

        // pick up ids
        List<List<Long>> usedIds = new ArrayList<>();
        for (int i = 0; i < thisTimeCount; i++) {
            List<Long> each = new ArrayList<>();
            for (Map.Entry<Integer, List<Long>> entry: typeIdsMap.entrySet()) {
                List<Long> ids = entry.getValue();
                each.add(ids.get(i));
            }
            usedIds.add(each);
        }
        List<Long> flatIds = usedIds.stream().flatMap(Collection::stream).collect(Collectors.toList());

        // 先合成（插记录、标记状态）
        txn(s -> {
            // loop insert
            for (final List<Long> ids : usedIds) {
                userCompoundRecordService.insertUserCompoundRecord(activityId, componentId, guid, ids);
            }

            // update used once
            userFragmentCollectService.updateFragmentStatusById(flatIds);
        });

        return 1;
    }

    private <T> T tx(TransactionCallback<T> callback) {
        return transactionTemplate.execute(callback);
    }

    private void txn(Consumer<TransactionStatus> f) {
        transactionTemplate.executeWithoutResult(f);
    }

}