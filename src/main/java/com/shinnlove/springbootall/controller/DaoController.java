/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.controller;

import com.shinnlove.springbootall.db.po.*;
import com.shinnlove.springbootall.models.PageResult;
import com.shinnlove.springbootall.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Tony Zhao
 * @version $Id: DaoController.java, v 0.1 2024-04-16 11:02 Tony Zhao Exp $$
 */
@RestController
@RequestMapping(value = "/dao")
public class DaoController {

    private static Logger logger = LoggerFactory.getLogger(DaoController.class);

    @Autowired
    private UserPkGlobalStatService userPkGlobalStatService;

    @Autowired
    private UserPkDailyStatService          userPkDailyStatService;

    @Autowired
    private UserPkRecordService             userPkRecordService;

    @Autowired
    private UserItemRankingService          userItemRankingService;

    @Autowired
    private UserFragmentCollectService      userFragmentCollectService;

    @Autowired
    private UserCompoundRecordService       userCompoundRecordService;

    @Autowired
    private UserLimitedItemStatService      userLimitedItemStatService;

    @Autowired
    private UserLimitedItemExchangeService  userLimitedItemExchangeService;

    @Autowired
    private ItemRecordService               itemRecordService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello() {
        return "Hello world.";
    }

    @RequestMapping(value = "/insert_global", method = RequestMethod.GET)
    public long insertUserGlobalStat() {
        return userPkGlobalStatService.insertUserGlobalStat();
    }

    @RequestMapping(value = "/query_global", method = RequestMethod.GET)
    public String queryUserGlobalStat() {
        UserPkGlobalStatEntity entity = userPkGlobalStatService.queryUserGlobalStat();
        return Objects.nonNull(entity) ? entity.toString() : "没有查询到global信息";
    }

    @RequestMapping(value = "/update_global_success", method = RequestMethod.GET)
    public long updateGlobalSuccess() {
        return userPkGlobalStatService.updateGlobalSuccess();
    }

    @RequestMapping(value = "/update_global_success_and_level", method = RequestMethod.GET)
    public long updateGlobalSuccessWithPromotionLevel() {
        return userPkGlobalStatService.updateGlobalSuccessWithPromotionLevel();
    }

    @RequestMapping(value = "/update_global_failure", method = RequestMethod.GET)
    public long updateGlobalFailure() {
        return userPkGlobalStatService.updateGlobalFailure();
    }

    @RequestMapping(value = "/update_global_draw", method = RequestMethod.GET)
    public long updateGlobalDraw() {
        return userPkGlobalStatService.updateGlobalDraw();
    }

    @RequestMapping(value = "/insert_daily", method = RequestMethod.GET)
    public long insertUserDailyStat() {
        return userPkDailyStatService.insertUserDailyStat();
    }

    @RequestMapping(value = "/query_daily", method = RequestMethod.GET)
    public String queryUserTodayStat() {
        UserPkDailyStatEntity entity = userPkDailyStatService.queryUserTodayStat();
        return Objects.nonNull(entity) ? entity.toString() : "没有查询到daily信息";
    }

    @RequestMapping(value = "/update_daily_success", method = RequestMethod.GET)
    public int updateDailySuccess() {
        return userPkDailyStatService.updateDailySuccess();
    }

    @RequestMapping(value = "/inc_daily_failure", method = RequestMethod.GET)
    public int incDailyFailure() {
        return userPkDailyStatService.incDailyFailure();
    }

    @RequestMapping(value = "/inc_daily_draw", method = RequestMethod.GET)
    public int incDailyDraw() {
        return userPkDailyStatService.incDailyDraw();
    }

    @RequestMapping(value = "/inc_daily_chance", method = RequestMethod.GET)
    public int incDailyChance() {
        return userPkDailyStatService.incDailyChance();
    }

    @RequestMapping(value = "/inc_daily_chance_by_count", method = RequestMethod.GET)
    public int incDailyChanceByCount() {
        return userPkDailyStatService.incDailyChanceByCount();
    }

    @RequestMapping(value = "/insert_pk_record", method = RequestMethod.GET)
    public long insertPkRecord() {
        return userPkRecordService.save();
    }

    @RequestMapping(value = "/query_record_by_time", method = RequestMethod.GET)
    public String queryRecordByGuidAndTime() {
        List<UserPkRecordEntity> pos = userPkRecordService.queryRecordByGuidAndTime();
        return CollectionUtils.isEmpty(pos) ? "没有查询到record信息" : pos.toString();
    }

    @RequestMapping(value = "/update_daily_record", method = RequestMethod.GET)
    public Integer updateDailyPkRecord(Long id) {
        return userPkRecordService.updateDailyPkRecord(id);
    }

    @RequestMapping(value = "/insert_user_item_ranking", method = RequestMethod.GET)
    public long insertUserItemRanking() {
        return userItemRankingService.insertUserItemRanking();
    }

    @RequestMapping(value = "/page_query_top_ranking", method = RequestMethod.GET)
    public PageResult<UserItemRankingEntity> selectTopRanking(@RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
                                                              @RequestParam(value = "size", defaultValue = "5", required = false) Integer size) {
        return userItemRankingService.pageQueryTopRanking(page, size);
    }

    @RequestMapping(value = "/count_collect_all_user", method = RequestMethod.GET)
    public long countCollectAllUser(Integer types) {
        return userItemRankingService.countCollectAllUser(types);
    }

    @RequestMapping(value = "/query_guid_item", method = RequestMethod.GET)
    public String queryByActivityGuid(Long guid) {
        UserItemRankingEntity entity = userItemRankingService.queryByActivityGuid(guid);
        return entity.toString();
    }

    @RequestMapping(value = "/user_item_count", method = RequestMethod.GET)
    public Integer incItemTypeCount() {
        return userItemRankingService.incItemTypeCount();
    }

    @RequestMapping(value = "/insert_user_fragment_collect", method = RequestMethod.GET)
    public long insertUserFragmentCollect() {
        return userFragmentCollectService.insertSelective();
    }

    @RequestMapping(value = "/query_unused_fragments", method = RequestMethod.GET)
    public String queryUnUsedFragments() {
        List<UserFragmentCollectEntity> entities = userFragmentCollectService.queryUnUsedFragments();
        return CollectionUtils.isEmpty(entities) ? "没有查询到未使用碎片信息" : entities.toString();
    }

    @RequestMapping(value = "/query_can_compound", method = RequestMethod.GET)
    public String queryUserCanCompoundCount() {
        List<UserFragmentCollectAggEntity> entities = userFragmentCollectService.queryUserCanCompoundCount();
        return CollectionUtils.isEmpty(entities) ? "没有查询到用户合成碎片" : entities.toString();
    }

    @RequestMapping(value = "/update_fragment_status", method = RequestMethod.GET)
    public Integer updateFragmentStatusById(String ids) {

        List<Long> idList = new ArrayList<>();
        String[] idStr = ids.split(",");
        for (int i = 0; i < idStr.length; i++) {
            idList.add(Long.parseLong(idStr[i]));
        }

        return userFragmentCollectService.updateFragmentStatusById(idList);
    }

    @RequestMapping(value = "/insert_user_compound_record", method = RequestMethod.GET)
    public long insertUserCompoundRecord(String usedIds) {

        List<Long> idList = new ArrayList<>();
        String[] idStr = usedIds.split(",");
        for (int i = 0; i < idStr.length; i++) {
            idList.add(Long.parseLong(idStr[i]));
        }

        return userCompoundRecordService.insertSelective(idList);
    }

    @RequestMapping(value = "/query_user_compound", method = RequestMethod.GET)
    public String queryUserCompoundCount() {
        List<UserCompoundRecordAggEntity>  entities = userCompoundRecordService.queryUserCompoundCount();
        return CollectionUtils.isEmpty(entities) ? "没有查询到用户合成记录" : entities.toString();
    }

    @RequestMapping(value = "/query_limit_stat", method = RequestMethod.GET)
    public String queryUserLimitedItemStat(int type) {
        UserLimitedItemStatEntity entity = userLimitedItemStatService.queryByGuidAndLimitedType(type);
        return Objects.isNull(entity) ? "N/A" : entity.toString();
    }

    @RequestMapping(value = "/insert_limited_stat", method = RequestMethod.GET)
    public long insertUserLimitedItemStat(int type) {
        return userLimitedItemStatService.insertSelective(type);
    }

    @RequestMapping(value = "/update_limit_total", method = RequestMethod.GET)
    public long updateStatTotal(int type, int count) {
        return userLimitedItemStatService.updateStatTotalCount(type, count);
    }

    @RequestMapping(value = "/update_limit_used", method = RequestMethod.GET)
    public long updateStatUsed(int type, int count) {
        return userLimitedItemStatService.updateStatUsedCount(type, count);
    }

    @RequestMapping(value = "/insert_limited_exchange", method = RequestMethod.GET)
    public long insertUserLimitedItemExchange(int type) {
        return userLimitedItemExchangeService.insertSelective(type);
    }

    @RequestMapping(value = "/cursor_query_item_record", method = RequestMethod.GET)
    public long cursorQueryItemRecord(int version, int pageSize) {
        return itemRecordService.cursorQueryItemRecord(version, pageSize);
    }

    @RequestMapping(value = "/query_item_record", method = RequestMethod.GET)
    public List<ItemRecordEntity> queryItemRecord(int version) {
        return itemRecordService.simpleQueryItemRecord(version);
    }

    @RequestMapping(value = "/update_msg_version", method = RequestMethod.GET)
    public int updateMsgVersion(long id, int version) {
        return itemRecordService.updateItemRecordMsgVersion(id, version);
    }

}