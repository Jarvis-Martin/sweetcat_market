/*
Navicat MySQL Data Transfer

Source Server         : VMmysql
Source Server Version : 80021
Source Host           : 192.168.193.129:3306
Source Database       : sweetcat_market_user_information

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2022-01-04 18:42:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_user_comment_reply`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_comment_reply`;
CREATE TABLE `t_user_comment_reply` (
  `information_id` bigint unsigned NOT NULL COMMENT '通知id',
  `commodity_id` bigint unsigned NOT NULL COMMENT '商品id',
  `commodity_name` varchar(50) NOT NULL COMMENT '商品名称',
  `commodity_pic_small` varchar(128) NOT NULL COMMENT '商品主图',
  `store_id` bigint unsigned NOT NULL COMMENT '商家id',
  `store_name` varchar(50) NOT NULL COMMENT '商家名',
  `target_url` varchar(128) DEFAULT '' COMMENT '响应给用户的标题',
  PRIMARY KEY (`information_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_user_comment_reply
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user_information`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_information`;
CREATE TABLE `t_user_information` (
  `information_id` bigint unsigned NOT NULL COMMENT '通知id',
  `publisher_id` bigint unsigned NOT NULL COMMENT '发布人id',
  `publisher_name` varchar(100) NOT NULL COMMENT '发布人昵称',
  `publisher_avatar` varchar(256) NOT NULL COMMENT '发布人头像',
  `receiver_id` bigint unsigned NOT NULL COMMENT '接收人id',
  `content` varchar(256) NOT NULL COMMENT '通知内容',
  `content_pics` varchar(512) DEFAULT NULL COMMENT '通知配图',
  `create_time` bigint unsigned DEFAULT '0' COMMENT '创建时间',
  `type` tinyint DEFAULT '0',
  `status` tinyint unsigned DEFAULT '0' COMMENT '状态 0未读 1已读',
  PRIMARY KEY (`information_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_user_information
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user_system_information`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_system_information`;
CREATE TABLE `t_user_system_information` (
  `information_id` bigint unsigned NOT NULL COMMENT '通知id',
  `process_time` bigint unsigned DEFAULT '0' COMMENT '反馈处理时间',
  `response_title` varchar(128) NOT NULL COMMENT '响应给用户的标题',
  PRIMARY KEY (`information_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_user_system_information
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
