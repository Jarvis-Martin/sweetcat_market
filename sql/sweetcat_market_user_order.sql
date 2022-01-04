/*
Navicat MySQL Data Transfer

Source Server         : VMmysql
Source Server Version : 80021
Source Host           : 192.168.193.129:3306
Source Database       : sweetcat_market_user_order

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2022-01-04 18:42:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_amount_of_commodity`
-- ----------------------------
DROP TABLE IF EXISTS `t_amount_of_commodity`;
CREATE TABLE `t_amount_of_commodity` (
  `order_id` bigint unsigned NOT NULL COMMENT '拆分前订单编号',
  `price_of_payment` decimal(10,2) DEFAULT '0.00' COMMENT '实际待支付金额',
  `price_of_commodity` decimal(10,2) DEFAULT '0.00' COMMENT '商品实际金额',
  `credit` bigint unsigned DEFAULT '0' COMMENT '整个订单消耗的积分数量'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_amount_of_commodity
-- ----------------------------

-- ----------------------------
-- Table structure for `t_amount_of_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_amount_of_order`;
CREATE TABLE `t_amount_of_order` (
  `order_id` bigint unsigned NOT NULL COMMENT '拆分前订单编号',
  `price_of_payment` decimal(10,0) unsigned DEFAULT '0' COMMENT '瀹為檯寰呮敮浠橀噾棰?',
  `price_of_commodity` decimal(10,0) unsigned DEFAULT '0' COMMENT '鍟嗗搧瀹為檯閲戦',
  `credit` int unsigned DEFAULT '0' COMMENT '整个订单消耗的积分数量'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_amount_of_order
-- ----------------------------

-- ----------------------------
-- Table structure for `t_children_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_children_order`;
CREATE TABLE `t_children_order` (
  `parent_order_id` bigint unsigned NOT NULL COMMENT '拆分前父订单编号',
  `user_id` bigint NOT NULL DEFAULT '0',
  `order_status` tinyint NOT NULL DEFAULT '0',
  `children_order_id` bigint unsigned NOT NULL COMMENT '拆分后子订单编号',
  PRIMARY KEY (`parent_order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_children_order
-- ----------------------------

-- ----------------------------
-- Table structure for `t_commodity_of_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_commodity_of_order`;
CREATE TABLE `t_commodity_of_order` (
  `order_id` bigint unsigned NOT NULL COMMENT '拆分前订单编号',
  `commodity_id` bigint unsigned NOT NULL COMMENT '商品id',
  `commodity_name` varchar(128) NOT NULL COMMENT '商品名',
  `commodity_pic_small` varchar(10) DEFAULT NULL COMMENT '鍟嗗搧涓诲浘',
  `price_when_make_order` decimal(10,0) unsigned DEFAULT '0' COMMENT '涓嬪崟鏃朵环鏍?',
  `specification` varchar(255) DEFAULT '' COMMENT '购买的商品规格',
  `count` int unsigned DEFAULT '0' COMMENT '购买的数量'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_commodity_of_order
-- ----------------------------

-- ----------------------------
-- Table structure for `t_coupon_of_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_coupon_of_order`;
CREATE TABLE `t_coupon_of_order` (
  `order_id` bigint unsigned NOT NULL COMMENT '拆分前订单编号',
  `coupon_id` bigint unsigned NOT NULL COMMENT '订单id',
  `threshold_price` decimal(10,2) DEFAULT '0.00' COMMENT '最低可使用价格',
  `counteract_price` decimal(10,2) DEFAULT '0.00' COMMENT '抵扣价格'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_coupon_of_order
-- ----------------------------

-- ----------------------------
-- Table structure for `t_store_info_of_commodity`
-- ----------------------------
DROP TABLE IF EXISTS `t_store_info_of_commodity`;
CREATE TABLE `t_store_info_of_commodity` (
  `order_id` bigint unsigned NOT NULL COMMENT '拆分前订单编号',
  `store_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '鎼存鎽甸崥宥囆?',
  `store_logo` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '鎼存鎽祃ogo'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_store_info_of_commodity
-- ----------------------------

-- ----------------------------
-- Table structure for `t_time_info_of_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_time_info_of_order`;
CREATE TABLE `t_time_info_of_order` (
  `order_id` bigint unsigned NOT NULL COMMENT '拆分前订单编号',
  `place_order_time` bigint unsigned DEFAULT '0' COMMENT '下单时间',
  `pay_order_time` bigint unsigned DEFAULT '0' COMMENT '支付时间',
  `time_out_time` bigint unsigned DEFAULT '0' COMMENT '超时时间',
  `cancel_order_time` bigint unsigned DEFAULT '0' COMMENT '取消订单时间',
  `finish_order_time` bigint unsigned DEFAULT '0' COMMENT '交易完成时间',
  `deliver_commodity_time` bigint unsigned DEFAULT '0' COMMENT '收货时间',
  `received_commodity_time` bigint unsigned DEFAULT '0' COMMENT '收货时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_time_info_of_order
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user_address_of_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_address_of_order`;
CREATE TABLE `t_user_address_of_order` (
  `order_id` bigint unsigned NOT NULL COMMENT '拆分前订单编号',
  `address_id` bigint unsigned NOT NULL COMMENT '收货地址 id',
  `user_id` bigint unsigned NOT NULL COMMENT '地址所属用户的 id',
  `receiver_name` varchar(128) NOT NULL COMMENT '收货人姓名',
  `receiver_phone` char(11) NOT NULL COMMENT '收货人手机号',
  `province_name` varchar(100) NOT NULL COMMENT '省名',
  `city_name` varchar(100) NOT NULL COMMENT '市名',
  `area_name` varchar(100) NOT NULL COMMENT '区名',
  `town_name` varchar(100) NOT NULL COMMENT '街道名',
  `detail_address` varchar(100) NOT NULL COMMENT '详细地址'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_user_address_of_order
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_order`;
CREATE TABLE `t_user_order` (
  `order_id` bigint unsigned NOT NULL COMMENT '拆分前订单编号',
  `order_staus` tinyint unsigned DEFAULT '0' COMMENT '订单状态.0：未支付；1：待发货；2：待收货；3：待评价（交易成功）；4：已取消',
  `type` tinyint NOT NULL DEFAULT '0' COMMENT '0未拆分订单，1已拆分订单',
  `user_id` bigint unsigned NOT NULL COMMENT '订单所属用户编号',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_user_order
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
