/*
Navicat MySQL Data Transfer

Source Server         : VMmysql
Source Server Version : 80021
Source Host           : 192.168.193.129:3306
Source Database       : sweetcat_market_store_info

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2022-01-04 18:39:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_store_address`
-- ----------------------------
DROP TABLE IF EXISTS `t_store_address`;
CREATE TABLE `t_store_address` (
  `address_id` bigint DEFAULT NULL,
  `store_id` bigint unsigned NOT NULL COMMENT '商家编号',
  `province_name` varchar(100) NOT NULL COMMENT '省名',
  `city_name` varchar(100) NOT NULL COMMENT '市名',
  `area_name` varchar(100) NOT NULL COMMENT '区名',
  `town_name` varchar(100) NOT NULL COMMENT '街道名',
  `detail_address` varchar(100) NOT NULL COMMENT '详细地址',
  `create_time` bigint unsigned DEFAULT '0' COMMENT '创建时间',
  `update_time` bigint unsigned DEFAULT '0' COMMENT '修改时间',
  PRIMARY KEY (`store_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_store_address
-- ----------------------------

-- ----------------------------
-- Table structure for `t_store_info`
-- ----------------------------
DROP TABLE IF EXISTS `t_store_info`;
CREATE TABLE `t_store_info` (
  `store_id` bigint unsigned NOT NULL COMMENT '商家编号',
  `store_logo` varchar(128) DEFAULT '''''',
  `store_name` varchar(100) NOT NULL COMMENT '商家名',
  `principal_name` varchar(10) NOT NULL COMMENT '负责人姓名',
  `principal_phone` char(11) NOT NULL COMMENT '负责人手机',
  `main_business` varchar(100) DEFAULT '' COMMENT '主要业务,如：主要：信阳毛尖',
  `type` tinyint unsigned DEFAULT '0' COMMENT '店铺类型,0：网店；1：实体店',
  `create_time` bigint unsigned DEFAULT '0' COMMENT '创建时间',
  `update_time` bigint unsigned DEFAULT '0' COMMENT '修改时间',
  PRIMARY KEY (`store_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_store_info
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
