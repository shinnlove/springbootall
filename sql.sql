
CREATE TABLE `qyn_log_activity` (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
                                    `role_id` bigint(20) DEFAULT '0' COMMENT '活动关联角色id',
                                    `topic_id` bigint(20) DEFAULT '0' COMMENT '活动对应的帖子id',
                                    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                    PRIMARY KEY (`id`),
                                    KEY `idx_role_topic` (`role_id`, `topic_id`)
) ENGINE = InnoDB CHARSET = utf8mb4 COMMENT '日记活动表';


CREATE TABLE `qyn_log_vote` (
                                `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
                                `guid` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户guid',
                                `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户userid',
                                `activity_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '投票活动id',
                                `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                PRIMARY KEY (`id`),
                                KEY `idx_user_activity` (`user_id`, `activity_id`)
) ENGINE = InnoDB CHARSET = utf8mb4 COMMENT '日记活动用户投票表';


CREATE TABLE `qyn_log_vote_tx` (
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
                                   `tx_role_id` varchar(128) NOT NULL DEFAULT '' COMMENT '腾讯物品id',
                                   `role_id` bigint(20) UNSIGNED NOT NULL DEFAULT '0' COMMENT 'roleId',
                                   `vote_count` bigint(20) UNSIGNED NOT NULL DEFAULT '0',
                                   `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                   `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                   PRIMARY KEY (`id`),
                                   UNIQUE KEY `qyn_log_vote_tx_tx_role_id_IDX` USING BTREE (`tx_role_id`)
) ENGINE = InnoDB CHARSET = utf8mb4 COMMENT '腾讯侧投票表';


CREATE TABLE `qyn_log_reward` (
                                  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
                                  `guid` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户guid',
                                  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户userid',
                                  `reward_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '用户领奖状态 0-未领奖 1-已领奖',
                                  `reward_package_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户领奖的包裹id',
                                  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                  PRIMARY KEY (`id`),
                                  KEY `idx_user_package` (`user_id`, `reward_package_id`)
) ENGINE = InnoDB CHARSET = utf8mb4 COMMENT '日记活动用户领奖表';


CREATE TABLE `qyn_log_cd_key` (
                                  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
                                  `guid` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户guid',
                                  `cd_key` varchar(256) NOT NULL DEFAULT '' COMMENT '兑换cd-key码',
                                  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'cd-key被领取状态 0-未领取 1-已领取',
                                  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                  PRIMARY KEY (`id`),
                                  KEY `idx_user` (`guid`),
                                  KEY `idx_cd_key` (`cd_key`)
) ENGINE = InnoDB CHARSET = utf8mb4 COMMENT '日记领奖ck-key表';


CREATE TABLE `qyn_log_task` (
                                `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
                                `task_type` int(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '1 截止通知任务 2 首章通知',
                                `status` int(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '0 未完成 1 已完成',
                                `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                PRIMARY KEY (`id`),
                                UNIQUE KEY `qyn_log_task_task_type_IDX` USING BTREE (`task_type`)
) ENGINE = InnoDB CHARSET = utf8mb4 COMMENT '日记活动任务表';


