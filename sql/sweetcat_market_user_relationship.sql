/*
Navicat MySQL Data Transfer

Source Server         : VMmysql
Source Server Version : 80021
Source Host           : 192.168.193.129:3306
Source Database       : sweetcat_market_user_relationship

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2022-01-04 18:42:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_user_fans`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_fans`;
CREATE TABLE `t_user_fans` (
  `user_id` bigint unsigned NOT NULL COMMENT '发起关注的人的 id',
  `user_avatar` varchar(255) NOT NULL,
  `user_nickname` varchar(100) NOT NULL,
  `user_personalized_signature` varchar(255) DEFAULT NULL,
  `target_user_id` bigint unsigned NOT NULL COMMENT '被关注id',
  `target_avatar` varchar(255) NOT NULL COMMENT '目标头像',
  `target_nickname` varchar(100) NOT NULL COMMENT '昵称',
  `target_personalized_signature` varchar(255) DEFAULT NULL COMMENT '个性签名',
  `create_time` bigint unsigned DEFAULT '0' COMMENT '关注时间',
  PRIMARY KEY (`user_id`,`target_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_user_fans
-- ----------------------------

-- ----------------------------
-- Table structure for `undo_log`
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `branch_id` bigint NOT NULL,
  `xid` varchar(100) NOT NULL,
  `context` varchar(128) NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of undo_log
-- ----------------------------
