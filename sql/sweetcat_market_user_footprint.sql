/*
Navicat MySQL Data Transfer

Source Server         : VMmysql
Source Server Version : 80021
Source Host           : 192.168.193.129:3306
Source Database       : sweetcat_market_user_footprint

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2022-01-04 18:41:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_user_footprint`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_footprint`;
CREATE TABLE `t_user_footprint` (
  `footprint_id` bigint unsigned NOT NULL COMMENT '记录id',
  `user_id` bigint unsigned NOT NULL COMMENT '用户id',
  `commodity_id` bigint unsigned NOT NULL COMMENT '商品id',
  `price_when_browser` decimal(10,2) NOT NULL,
  `pic_small` varchar(512) NOT NULL COMMENT '商品正面图（small）',
  `start_time` bigint unsigned DEFAULT '0' COMMENT '浏览开始时间',
  `browser_duration` int unsigned DEFAULT '1' COMMENT '浏览时长, 单位: ms',
  PRIMARY KEY (`footprint_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_user_footprint
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
