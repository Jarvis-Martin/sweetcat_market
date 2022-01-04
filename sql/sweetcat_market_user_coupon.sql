/*
Navicat MySQL Data Transfer

Source Server         : VMmysql
Source Server Version : 80021
Source Host           : 192.168.193.129:3306
Source Database       : sweetcat_market_user_coupon

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2022-01-04 18:41:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_user_coupon`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_coupon`;
CREATE TABLE `t_user_coupon` (
  `record_id` bigint unsigned NOT NULL COMMENT '记录id',
  `user_id` bigint unsigned NOT NULL COMMENT '用户id',
  `coupon_id` bigint unsigned NOT NULL COMMENT '优惠券id',
  `threshold_price` decimal(10,2) DEFAULT '0.00' COMMENT '最低可使用价格',
  `counteract_price` decimal(10,2) DEFAULT '0.00' COMMENT '抵扣价格',
  `obtain_time` bigint unsigned DEFAULT '0' COMMENT '创建时间/获得优惠券的时间',
  `target_type` tinyint unsigned DEFAULT '0' COMMENT '优惠券类型；0商品券，1通用券',
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_user_coupon
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user_coupon_coupon_info`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_coupon_coupon_info`;
CREATE TABLE `t_user_coupon_coupon_info` (
  `coupon_id` bigint unsigned NOT NULL COMMENT '目标优惠券id',
  `store_id` bigint unsigned DEFAULT NULL COMMENT '商品对应的店家id',
  `store_name` varchar(100) DEFAULT NULL COMMENT '商品对应的店家名称',
  `commodity_id` bigint unsigned DEFAULT NULL COMMENT '商品编号',
  `commodity_pic_small` varchar(1024) DEFAULT NULL COMMENT '鍟嗗搧姝ｉ潰鍥撅紙small锛?3寮犳渶浣?',
  `commodity_name` varchar(255) DEFAULT NULL COMMENT '商品名',
  `obtain_time` bigint DEFAULT '0' COMMENT '时长型优惠券的获得日期',
  `time_type` tinyint unsigned DEFAULT '0' COMMENT '券的时间类型：0限时券；1区间券',
  `valid_duration` bigint unsigned DEFAULT NULL COMMENT '显示券的有效时长，自领取时开始计算',
  `start_time` bigint unsigned DEFAULT NULL COMMENT '区间券的可使用时间',
  `deadline` bigint unsigned DEFAULT NULL COMMENT '区间券的最后可使用时间',
  PRIMARY KEY (`coupon_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_user_coupon_coupon_info
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user_coupon_usage_record`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_coupon_usage_record`;
CREATE TABLE `t_user_coupon_usage_record` (
  `record_id` bigint unsigned NOT NULL COMMENT '记录id',
  `user_id` bigint unsigned NOT NULL COMMENT '用户id',
  `coupon_id` bigint unsigned NOT NULL COMMENT '优惠券id',
  `type` tinyint unsigned DEFAULT '0' COMMENT '记录类型。0：失效；1：使用',
  `time` bigint unsigned DEFAULT '0' COMMENT '使用时间/失效时间',
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_user_coupon_usage_record
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
