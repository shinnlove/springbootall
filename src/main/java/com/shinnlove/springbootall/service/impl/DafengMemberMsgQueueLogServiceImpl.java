/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.dao.DafengMemberMsgQueueLogRepo;
import com.shinnlove.springbootall.db.po.DafengMemberMsgQueueLogEntity;
import com.shinnlove.springbootall.service.DafengMemberMsgQueueLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tony Zhao
 * @version $Id: DafengMemberMsgQueueLogServiceImpl.java, v 0.1 2024-12-04 21:19 Tony Zhao Exp $$
 */
@Service
public class DafengMemberMsgQueueLogServiceImpl implements DafengMemberMsgQueueLogService {

    private static final String JSON_MESSAGE = "{\"code\":0,\"data\":{\"dailyCheckInProgress\":[{\"hasCheckIn\":1,\"rewardList\":[{\"imageUrl\":\"//noahqd.yuewen.com/nqd/image/2879787c1afd4f3d920b3e05e3e05d6a.png\",\"name\":\"大奉漫画券\",\"prizeId\":0,\"desc\":\"大奉漫画券\"}],\"checkInDay\":1},{\"hasCheckIn\":1,\"rewardList\":[{\"imageUrl\":\"//noahqd.yuewen.com/nqd/image/214f47d66d6d45199d1512936d2c4f6c.png\",\"name\":\"1天体验会员\",\"prizeId\":0,\"desc\":\"\"}],\"checkInDay\":2},{\"hasCheckIn\":1,\"rewardList\":[{\"imageUrl\":\"//noahqd.yuewen.com/nqd/image/a1b450f3f7444113a8bebb948504c9a4.png\",\"name\":\"大奉签名实体书\",\"prizeId\":0,\"desc\":\"大奉签名实体书\"}],\"checkInDay\":3},{\"hasCheckIn\":1,\"rewardList\":[{\"imageUrl\":\"//noahqd.yuewen.com/nqd/image/6af47474bd2b448b94c56b4210ba39cd.png\",\"name\":\"大奉-银锣称号\",\"prizeId\":0,\"desc\":\"大奉-银锣称号\"},{\"imageUrl\":\"//noahqd.yuewen.com/nqd/image/51735f8b2dd74617bb7ff28359280cca.png\",\"name\":\"大奉-银锣挂件\",\"prizeId\":1,\"desc\":\"大奉-银锣挂件\"}],\"checkInDay\":4},{\"hasCheckIn\":1,\"rewardList\":[{\"imageUrl\":\"//noahqd.yuewen.com/nqd/image/4ff7dd305664496284339ad58ec931e4.png\",\"name\":\"1天体验会员\",\"prizeId\":0,\"desc\":\"\"},{\"imageUrl\":\"//noahqd.yuewen.com/nqd/image/f6ad3fb803b34e66a259063adcf209a7.png\",\"name\":\"大奉通行令-普通奖池-10次\",\"prizeId\":1,\"desc\":\"\"}],\"checkInDay\":5},{\"hasCheckIn\":1,\"rewardList\":[{\"imageUrl\":\"//noahqd.yuewen.com/nqd/image/bbc360e37e884ce4986312a36fbf8fd2.png\",\"name\":\"起点听书定制徽章\",\"prizeId\":0,\"desc\":\"\"}],\"checkInDay\":6},{\"hasCheckIn\":1,\"rewardList\":[{\"imageUrl\":\"//noahqd.yuewen.com/nqd/image/6ae949b69aca4497a151ead5ceb3847a.png\",\"name\":\"大奉银锣阅读背景\",\"prizeId\":0,\"desc\":\"大奉银锣阅读背景\"},{\"imageUrl\":\"//noahqd.yuewen.com/nqd/image/6a746ef785204fe28743dc2fb6179174.png\",\"name\":\"大奉金锣阅读背景\",\"prizeId\":1,\"desc\":\"大奉金锣阅读背景\"},{\"imageUrl\":\"//noahqd.yuewen.com/nqd/image/765e133c5ba244c597195d56e9f366ec.png\",\"name\":\"大奉银锣专题卡牌召唤券\",\"prizeId\":2,\"desc\":\"大奉银锣专题卡牌召唤券\"},{\"imageUrl\":\"\",\"name\":\"联合会员红包封面\",\"prizeId\":3,\"desc\":\"\"},{\"imageUrl\":\"\",\"name\":\"独家花絮\",\"prizeId\":4,\"desc\":\"\"},{\"imageUrl\":\"\",\"name\":\"独家剧照\",\"prizeId\":5,\"desc\":\"\"},{\"imageUrl\":\"\",\"name\":\"大奉打更人许七安卡牌\",\"prizeId\":6,\"desc\":\"\"}],\"checkInDay\":7},{\"hasCheckIn\":1,\"rewardList\":[{\"imageUrl\":\"//noahqd.yuewen.com/nqd/image/214f47d66d6d45199d1512936d2c4f6c.png\",\"name\":\"1天体验会员\",\"prizeId\":0,\"desc\":\"会员体验卡\"}],\"checkInDay\":8},{\"hasCheckIn\":1,\"rewardList\":[{\"imageUrl\":\"//noahqd.yuewen.com/nqd/image/51735f8b2dd74617bb7ff28359280cca.png\",\"name\":\"大奉-银锣挂件\",\"prizeId\":0,\"desc\":\"大奉-银锣挂件\"},{\"imageUrl\":\"\",\"name\":\"头像挂件 -- 珠光宝气\",\"prizeId\":1,\"desc\":\"\"},{\"imageUrl\":\"//bossaudioandcomic-1252317822.file.myqcloud.com/activity/document/992bfbcc4d2d47d3b0e050dce6279bed.png\",\"name\":\"发挂件\",\"prizeId\":2,\"desc\":\"发挂件\"},{\"imageUrl\":\"\",\"name\":\"徽章 -- 有点酷\",\"prizeId\":3,\"desc\":\"\"},{\"imageUrl\":\"\",\"name\":\"badge862徽章\",\"prizeId\":4,\"desc\":\"\"},{\"imageUrl\":\"//noahqd.yuewen.com/nqd/image/8a047ca2f3834485abd47ddc8f195dd8.png\",\"name\":\"壁纸 -- 新增活动类壁纸\",\"prizeId\":5,\"desc\":\"\"},{\"imageUrl\":\"//noahqd.yuewen.com/nqd/image/758b8358666f4038a94b2373b4375f61.png\",\"name\":\"角色卡牌\",\"prizeId\":6,\"desc\":\"1角色的卡牌..\"}],\"checkInDay\":9},{\"hasCheckIn\":0,\"rewardList\":[{\"imageUrl\":\"//noahqd.yuewen.com/nqd/image/214f47d66d6d45199d1512936d2c4f6c.png\",\"name\":\"1天体验会员\",\"prizeId\":0,\"desc\":\"\"}],\"checkInDay\":10},{\"hasCheckIn\":0,\"rewardList\":[{\"imageUrl\":\"//noahqd.yuewen.com/nqd/image/2879787c1afd4f3d920b3e05e3e05d6a.png\",\"name\":\"大奉漫画券\",\"prizeId\":0,\"desc\":\"大奉漫画券\"}],\"checkInDay\":11},{\"hasCheckIn\":0,\"rewardList\":[{\"imageUrl\":\"//noahqd.yuewen.com/nqd/image/459ad8ef61cc4c78a35a662c4a3ab51b.jpg\",\"name\":\"壁纸 -- 新增活动类壁纸\",\"prizeId\":0,\"desc\":\"\"},{\"imageUrl\":\"//noahqd.yuewen.com/nqd/image/214f47d66d6d45199d1512936d2c4f6c.png\",\"name\":\"1天体验会员\",\"prizeId\":1,\"desc\":\"\"}],\"checkInDay\":12},{\"hasCheckIn\":0,\"rewardList\":[{\"imageUrl\":\"//noahqd.yuewen.com/nqd/image/be5e2388988044f88298bdd329126c16.png\",\"name\":\"ssr\",\"prizeId\":0,\"desc\":\"\"}],\"checkInDay\":13},{\"hasCheckIn\":0,\"rewardList\":[{\"imageUrl\":\"//noahqd.yuewen.com/nqd/image/30b850cf4e4a4d219a05c347a7fd10e2.png\",\"name\":\"小米手环8\",\"prizeId\":0,\"desc\":\"小米手环8\"},{\"imageUrl\":\"//noahqd.yuewen.com/nqd/image/5da8bbdc1dfd41f581b8b77c3e5488c0.png\",\"name\":\"京东50元购物卡\",\"prizeId\":1,\"desc\":\"京东50元购物卡\"},{\"imageUrl\":\"\",\"name\":\"点币*6\",\"prizeId\":2,\"desc\":\"点币*6\"}],\"checkInDay\":14}],\"userCheckInProgress\":{\"todayCheckIn\":1,\"totalCheckInNum\":9,\"totalNeedCheckInNum\":14,\"activityLastDay\":0,\"finalRewardReceived\":0}},\"msg\":\"success\"}";

    @Autowired
    private DafengMemberMsgQueueLogRepo dafengMemberMsgQueueLogRepo;

    @Override
    public long insertMessageLog() {

        DafengMemberMsgQueueLogEntity entity = new DafengMemberMsgQueueLogEntity();
        entity.setQueueName("my test queue");
        entity.setMessageBody(JSON_MESSAGE);

        try {
            dafengMemberMsgQueueLogRepo.insertSelective(entity);
        } catch (Exception e) {

        }

        return entity.getId();
    }
}