/*
Navicat MySQL Data Transfer

Source Server         : VMmysql
Source Server Version : 80021
Source Host           : 192.168.193.129:3306
Source Database       : sweetcat_market_takeaway_man_info

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2022-01-04 18:40:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_takeaway_man_info`
-- ----------------------------
DROP TABLE IF EXISTS `t_takeaway_man_info`;
CREATE TABLE `t_takeaway_man_info` (
  `man_id` bigint unsigned NOT NULL COMMENT '骑手编号',
  `name` varchar(50) NOT NULL COMMENT '骑手姓名',
  `phone` char(11) NOT NULL COMMENT '骑手联系方式',
  `create_time` bigint unsigned DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`man_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_takeaway_man_info
-- ----------------------------
INSERT INTO `t_takeaway_man_info` VALUES ('1457245889516142592', '美团骑手小哥', '15294969856', '1636268463253');

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
