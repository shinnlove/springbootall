/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.es.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.shinnlove.springbootall.es.model.IFFOrder;

/**
 * @author Tony Zhao
 * @version $Id: IFFOrderRepository.java, v 0.1 2022-01-09 7:25 PM Tony Zhao Exp $$
 */
@Repository("iffOrderRepository")
public interface IFFOrderRepository extends ElasticsearchRepository<IFFOrder, Long> {
}
