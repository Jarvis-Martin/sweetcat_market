/*
Navicat MySQL Data Transfer

Source Server         : VMmysql
Source Server Version : 80021
Source Host           : 192.168.193.129:3306
Source Database       : sweetcat_market_user_info

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2022-01-04 18:41:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_user_address`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_address`;
CREATE TABLE `t_user_address` (
  `address_id` bigint unsigned NOT NULL COMMENT '收货地址 id',
  `user_id` bigint unsigned NOT NULL COMMENT '地址所属用户的 id',
  `receiver_name` varchar(255) NOT NULL COMMENT '收货人姓名',
  `receiver_phone` char(11) NOT NULL COMMENT '收货人手机号',
  `province_name` varchar(100) NOT NULL COMMENT '省名',
  `city_name` varchar(100) NOT NULL COMMENT '市名',
  `area_name` varchar(100) NOT NULL COMMENT '区名',
  `town_name` varchar(100) NOT NULL COMMENT '街道名',
  `detail_address` varchar(100) NOT NULL COMMENT '详细地址',
  `is_default` tinyint unsigned DEFAULT '1' COMMENT '默认地址？0：是默认地址；1：非默认地址,用户首次添加的地址设置为默认地址',
  `create_time` bigint unsigned DEFAULT '0' COMMENT '创建时间',
  `update_time` bigint unsigned DEFAULT '0' COMMENT '更新时间',
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_user_address
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user_info`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_info`;
CREATE TABLE `t_user_info` (
  `user_id` bigint unsigned NOT NULL COMMENT '账号',
  `nickname` varchar(50) NOT NULL COMMENT '昵称',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `avatar_path` varchar(255) DEFAULT '/img/icon_default_avatar.png' COMMENT '头像',
  `gender` tinyint unsigned DEFAULT '0' COMMENT '性别,0: 女； 1: 男',
  `birthday` bigint unsigned DEFAULT '0' COMMENT '生日',
  `vip_level` tinyint unsigned DEFAULT '0' COMMENT 'vip 级别',
  `is_referrer` tinyint unsigned DEFAULT '0' COMMENT '推荐官？0: 不是， 1：是',
  `personalized_signature` varchar(255) DEFAULT '' COMMENT '个性签名',
  `create_time` bigint unsigned DEFAULT '0' COMMENT '创建时间',
  `update_time` bigint unsigned DEFAULT '0' COMMENT '更新时间',
  `phone` char(11) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_user_info
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
