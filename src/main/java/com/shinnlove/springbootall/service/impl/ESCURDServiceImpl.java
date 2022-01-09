/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import java.io.IOException;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shinnlove.springbootall.config.ESHighRestClientConfig;
import com.shinnlove.springbootall.model.User;
import com.shinnlove.springbootall.service.ESCURDService;

/**
 * A service for es high rest client service query.
 * 
 * Tips: please add ES CURD method in service.
 * 
 * @author Tony Zhao
 * @version $Id: ESCURDServiceImpl.java, v 0.1 2022-01-09 8:43 PM Tony Zhao Exp $$
 */
@Service("esCURDService")
public class ESCURDServiceImpl implements ESCURDService {

    private static final Logger logger       = LoggerFactory.getLogger(ESCURDServiceImpl.class);

    private ObjectMapper        objectMapper = new ObjectMapper();

    @Autowired
    private RestHighLevelClient highLevelClient;

    @Override
    public String addDocument(User user) {
        //指定索引和id
        IndexRequest request = new IndexRequest("users");
        // 如果不指定，可能会返回`Hp3YPn4BFBjRge4tqSSb`这样的id串，也是个string
        request.id("1");

        //数据转为json格式
        String userString = null;
        try {
            userString = objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
        }
        request.source(userString, XContentType.JSON);

        //执行保存操作
        IndexResponse indexResponse = null;
        try {
            indexResponse = highLevelClient.index(request, ESHighRestClientConfig.COMMON_OPTIONS);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

        System.out.println(indexResponse);

        return indexResponse.getId();
    }

    @Override
    public String aggregateQuery() {
        //1.创建检索请求
        SearchRequest searchRequest = new SearchRequest();
        //指定索引
        searchRequest.indices("bank");
        //指定DSL，检索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //1.1 构造检索条件
        //address中包含mill
        searchSourceBuilder.query(QueryBuilders.matchQuery("address", "mill"));
        //按照前10种年龄分布进行聚合
        TermsAggregationBuilder ageAgg = AggregationBuilders.terms("ageAgg").field("age").size(10);
        searchSourceBuilder.aggregation(ageAgg);
        //求所有人年龄的平均值
        AvgAggregationBuilder ageAvg = AggregationBuilders.avg("ageAvg").field("age");
        searchSourceBuilder.aggregation(ageAvg);

        System.out.println(searchSourceBuilder);
        searchRequest.source(searchSourceBuilder);

        //2.执行检索
        SearchResponse searchResponse = null;
        try {
            searchResponse = highLevelClient.search(searchRequest,
                ESHighRestClientConfig.COMMON_OPTIONS);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //3.分析结果
        //3.1 查看查询结果
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit : searchHits) {
            System.out.println(hit);
        }
        //3.2 查看聚合结果
        Aggregations aggs = searchResponse.getAggregations();

        Terms ageAgg2 = aggs.get("ageAgg");
        for (Terms.Bucket bucket : ageAgg2.getBuckets()) {
            System.out.println("年龄：" + bucket.getKeyAsString() + "==> 人数：" + bucket.getDocCount());
        }
        System.out.println();

        Avg ageAvg2 = aggs.get("ageAvg");
        System.out.println("平均年龄：" + ageAvg2.getValue());

        return String.valueOf(ageAvg2.getValue());
    }

}