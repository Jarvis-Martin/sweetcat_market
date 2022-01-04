/*
Navicat MySQL Data Transfer

Source Server         : VMmysql
Source Server Version : 80021
Source Host           : 192.168.193.129:3306
Source Database       : sweetcat_market_user_favorite

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2022-01-04 18:41:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_user_favorate`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_favorate`;
CREATE TABLE `t_user_favorate` (
  `favorite_id` bigint NOT NULL,
  `user_id` bigint unsigned DEFAULT NULL,
  `commodity_id` bigint unsigned DEFAULT NULL,
  `price_when_favorite` decimal(10,2) DEFAULT '0.00',
  `create_time` bigint unsigned DEFAULT '0',
  PRIMARY KEY (`favorite_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_user_favorate
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
