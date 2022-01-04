/*
Navicat MySQL Data Transfer

Source Server         : VMmysql
Source Server Version : 80021
Source Host           : 192.168.193.129:3306
Source Database       : sweetcat_market_takeaway_order

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2022-01-04 18:41:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_amount_of_commodity`
-- ----------------------------
DROP TABLE IF EXISTS `t_amount_of_commodity`;
CREATE TABLE `t_amount_of_commodity` (
  `order_id` bigint unsigned NOT NULL COMMENT '订单编号',
  `commodity_id` bigint unsigned NOT NULL COMMENT '商品id',
  `price_of_payment` decimal(10,2) DEFAULT '0.00' COMMENT '实际待支付金额',
  `price_of_commodity` decimal(10,2) DEFAULT '0.00' COMMENT '商品实际金额',
  PRIMARY KEY (`order_id`,`commodity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_amount_of_commodity
-- ----------------------------

-- ----------------------------
-- Table structure for `t_amount_of_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_amount_of_order`;
CREATE TABLE `t_amount_of_order` (
  `order_id` bigint unsigned NOT NULL COMMENT '订单编号',
  `price_of_payment` decimal(10,2) DEFAULT '0.00' COMMENT '实际待支付金额',
  `price_of_commodity` decimal(10,2) DEFAULT '0.00' COMMENT '商品实际金额',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_amount_of_order
-- ----------------------------

-- ----------------------------
-- Table structure for `t_commodity_of_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_commodity_of_order`;
CREATE TABLE `t_commodity_of_order` (
  `order_id` bigint unsigned NOT NULL COMMENT '订单编号',
  `commodity_id` bigint unsigned NOT NULL COMMENT '商品id',
  `commodity_name` varchar(128) NOT NULL COMMENT '商品名',
  `commodity_pic_small` varchar(128) DEFAULT NULL COMMENT '鍟嗗搧涓诲浘',
  `price_when_make_order` decimal(10,2) unsigned DEFAULT '0.00' COMMENT '涓嬪崟鏃朵环鏍?',
  `count` int unsigned DEFAULT '0' COMMENT '购买的数量',
  PRIMARY KEY (`order_id`,`commodity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_commodity_of_order
-- ----------------------------

-- ----------------------------
-- Table structure for `t_store_address`
-- ----------------------------
DROP TABLE IF EXISTS `t_store_address`;
CREATE TABLE `t_store_address` (
  `order_id` bigint unsigned NOT NULL COMMENT '拆分前订单编号',
  `address_id` bigint unsigned NOT NULL COMMENT '收货地址 id',
  `province_name` varchar(100) NOT NULL COMMENT '省名',
  `city_name` varchar(100) NOT NULL COMMENT '市名',
  `area_name` varchar(100) NOT NULL COMMENT '区名',
  `town_name` varchar(100) NOT NULL COMMENT '街道名',
  `detail_address` varchar(100) NOT NULL COMMENT '详细地址',
  PRIMARY KEY (`order_id`,`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_store_address
-- ----------------------------

-- ----------------------------
-- Table structure for `t_store_info`
-- ----------------------------
DROP TABLE IF EXISTS `t_store_info`;
CREATE TABLE `t_store_info` (
  `order_id` bigint unsigned NOT NULL COMMENT '璁㈠崟缂栧彿',
  `store_id` bigint unsigned NOT NULL COMMENT '商家id',
  `store_name` varchar(128) DEFAULT '' COMMENT '店铺名称',
  `store_logo` varchar(128) DEFAULT '' COMMENT '店铺'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_store_info
-- ----------------------------

-- ----------------------------
-- Table structure for `t_takeaway_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_takeaway_order`;
CREATE TABLE `t_takeaway_order` (
  `order_id` bigint unsigned NOT NULL COMMENT '订单编号',
  `order_status` tinyint unsigned DEFAULT '0' COMMENT '璁㈠崟鐘舵€?0锛氭湭鏀粯锛?锛氬緟鍙戣揣锛?锛氬緟鏀惰揣锛?锛氬緟璇勪环锛堜氦鏄撴垚鍔燂級锛?锛氬凡鍙栨秷',
  `user_id` bigint unsigned NOT NULL COMMENT '订单所属用户编号',
  `store_id` bigint unsigned NOT NULL COMMENT '商家id',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_takeaway_order
-- ----------------------------

-- ----------------------------
-- Table structure for `t_time_info_of_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_time_info_of_order`;
CREATE TABLE `t_time_info_of_order` (
  `order_id` bigint unsigned NOT NULL COMMENT '订单编号',
  `place_order_time` bigint unsigned DEFAULT '0' COMMENT '下单时间',
  `pay_order_time` bigint unsigned DEFAULT '0' COMMENT '支付时间',
  `time_out_time` bigint unsigned DEFAULT '0' COMMENT '超时时间',
  `cancel_order_time` bigint unsigned DEFAULT '0' COMMENT '取消订单时间',
  `finish_order_time` bigint unsigned DEFAULT '0' COMMENT '交易完成时间',
  `deliver_commodity_time` bigint unsigned DEFAULT '0' COMMENT '收货时间',
  `received_commodity_time` bigint unsigned DEFAULT '0' COMMENT '收货时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_time_info_of_order
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user_address`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_address`;
CREATE TABLE `t_user_address` (
  `order_id` bigint unsigned NOT NULL COMMENT '拆分前订单编号',
  `address_id` bigint unsigned NOT NULL COMMENT '收货地址 id',
  `user_id` bigint unsigned NOT NULL COMMENT '地址所属用户的 id',
  `receiver_name` varchar(128) NOT NULL COMMENT '收货人姓名',
  `receiver_phone` char(11) NOT NULL COMMENT '收货人手机号',
  `province_name` varchar(100) NOT NULL COMMENT '省名',
  `city_name` varchar(100) NOT NULL COMMENT '市名',
  `area_name` varchar(100) NOT NULL COMMENT '区名',
  `town_name` varchar(100) NOT NULL COMMENT '街道名',
  `detail_address` varchar(100) NOT NULL COMMENT '详细地址',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_user_address
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
