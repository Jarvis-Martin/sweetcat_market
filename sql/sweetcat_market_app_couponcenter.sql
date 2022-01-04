/*
Navicat MySQL Data Transfer

Source Server         : VMmysql
Source Server Version : 80021
Source Host           : 192.168.193.129:3306
Source Database       : sweetcat_market_app_couponcenter

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2022-01-04 18:38:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_app_coupon_center_coupon`
-- ----------------------------
DROP TABLE IF EXISTS `t_app_coupon_center_coupon`;
CREATE TABLE `t_app_coupon_center_coupon` (
  `coupon_id` bigint unsigned NOT NULL COMMENT '目标优惠券id',
  `target_type` tinyint unsigned DEFAULT '0' COMMENT '优惠券使用对象类别表示；0通用券，1商品券',
  `store_id` bigint unsigned DEFAULT NULL COMMENT '商品对应的店家id',
  `store_name` varchar(100) DEFAULT NULL COMMENT '商品对应的店家名称',
  `commodity_id` bigint unsigned DEFAULT NULL COMMENT '商品编号',
  `commodity_pic_small` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '鍟嗗搧姝ｉ潰鍥撅紙small锛?3寮犳渶浣?',
  `commodity_name` varchar(255) DEFAULT NULL COMMENT '商品名',
  `time_type` tinyint unsigned DEFAULT '0' COMMENT '券的时间类型：0限时券；1区间券',
  `valid_duration` bigint unsigned DEFAULT NULL COMMENT '显示券的有效时长，自领取时开始计算',
  `start_time` bigint unsigned DEFAULT NULL COMMENT '区间券的可使用时间',
  `deadline` bigint unsigned DEFAULT NULL COMMENT '区间券的最后可使用时间',
  PRIMARY KEY (`coupon_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_app_coupon_center_coupon
-- ----------------------------

-- ----------------------------
-- Table structure for `t_app_couponcenter`
-- ----------------------------
DROP TABLE IF EXISTS `t_app_couponcenter`;
CREATE TABLE `t_app_couponcenter` (
  `coupon_id` bigint unsigned NOT NULL COMMENT '优惠券id',
  `threshold_price` decimal(10,2) DEFAULT '0.00' COMMENT '最低可使用价格',
  `counteract_price` decimal(10,2) DEFAULT '0.00' COMMENT '鎶垫墸浠锋牸',
  `creator_id` bigint unsigned NOT NULL COMMENT '创建人id',
  `stock` bigint NOT NULL DEFAULT '0' COMMENT '优惠券库存',
  `create_time` bigint unsigned DEFAULT '0' COMMENT '创建时间',
  `target_type` tinyint DEFAULT '0',
  `update_time` bigint unsigned DEFAULT '0' COMMENT '更新时间',
  PRIMARY KEY (`coupon_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_app_couponcenter
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
