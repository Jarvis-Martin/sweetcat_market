/*
Navicat MySQL Data Transfer

Source Server         : VMmysql
Source Server Version : 80021
Source Host           : 192.168.193.129:3306
Source Database       : sweetcat_market_user_recommend

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2022-01-04 18:42:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_user_commodity_recommend`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_commodity_recommend`;
CREATE TABLE `t_user_commodity_recommend` (
  `record_id` bigint unsigned NOT NULL COMMENT '记录id',
  `referrer_id` bigint unsigned NOT NULL COMMENT '鐢ㄦ埛id',
  `commodity_id` bigint unsigned NOT NULL COMMENT '商品id',
  `reason` varchar(512) DEFAULT '' COMMENT '推荐原因',
  `star` tinyint unsigned DEFAULT '5' COMMENT '推荐星级,范围 0 ~ 5',
  `commodity_pics` varchar(512) DEFAULT '[]' COMMENT '推荐时配图',
  `commodity_specification` varchar(512) DEFAULT '[]' COMMENT '商品规格',
  `create_time` bigint unsigned DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_user_commodity_recommend
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
