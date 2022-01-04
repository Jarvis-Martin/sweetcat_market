/*
Navicat MySQL Data Transfer

Source Server         : VMmysql
Source Server Version : 80021
Source Host           : 192.168.193.129:3306
Source Database       : sweetcat_market_app_credit

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2022-01-04 18:38:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_app_credit_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_app_credit_log`;
CREATE TABLE `t_app_credit_log` (
  `credit_log_id` bigint unsigned NOT NULL COMMENT '签到记录id',
  `user_id` bigint unsigned DEFAULT '0' COMMENT '积分记录用户',
  `log_type` int unsigned DEFAULT '0' COMMENT '记录类型：0收入；1支付',
  `description` varchar(50) DEFAULT '' COMMENT '描述：如签到获得30积分；兑换支出300积分',
  `credit_number` int unsigned DEFAULT '0' COMMENT '纪录涉及的积分数量',
  `create_time` bigint unsigned DEFAULT '0' COMMENT '记录创建时间',
  PRIMARY KEY (`credit_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_app_credit_log
-- ----------------------------

-- ----------------------------
-- Table structure for `t_app_credit_market`
-- ----------------------------
DROP TABLE IF EXISTS `t_app_credit_market`;
CREATE TABLE `t_app_credit_market` (
  `market_item_id` bigint unsigned NOT NULL COMMENT '积分商城像编号',
  `creator_id` bigint unsigned NOT NULL COMMENT '创建人编号',
  `creator_name` varchar(100) NOT NULL COMMENT '创建人名称',
  `stock` bigint unsigned NOT NULL COMMENT '库存',
  `create_time` bigint unsigned DEFAULT '0' COMMENT '创建时间',
  `update_time` bigint unsigned DEFAULT '0' COMMENT '更新时间',
  `commodity_type` tinyint DEFAULT '0' COMMENT '积分可兑换商品的分类',
  `credit_number` int unsigned DEFAULT '999999' COMMENT '兑换商品所需积分数',
  PRIMARY KEY (`market_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_app_credit_market
-- ----------------------------

-- ----------------------------
-- Table structure for `t_app_redeem_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_app_redeem_log`;
CREATE TABLE `t_app_redeem_log` (
  `redeem_log_id` bigint unsigned NOT NULL COMMENT '兑换记录id',
  `redeem_user_id` bigint unsigned NOT NULL COMMENT '发起兑换的用户id',
  `commodity_id` bigint unsigned NOT NULL COMMENT '兑换的商品的id',
  `cost_credit_number` int unsigned DEFAULT '0' COMMENT '兑换商品所需积分数',
  `create_time` bigint unsigned DEFAULT '0' COMMENT '兑换时间',
  PRIMARY KEY (`redeem_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_app_redeem_log
-- ----------------------------

-- ----------------------------
-- Table structure for `t_app_user_credit`
-- ----------------------------
DROP TABLE IF EXISTS `t_app_user_credit`;
CREATE TABLE `t_app_user_credit` (
  `user_id` bigint unsigned NOT NULL COMMENT '用户编号',
  `total_credit` bigint unsigned DEFAULT '0' COMMENT '鐢ㄦ埛绉垎鎬绘暟',
  `create_time` bigint unsigned DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_app_user_credit
-- ----------------------------

-- ----------------------------
-- Table structure for `t_credit_market_commodity_coupon`
-- ----------------------------
DROP TABLE IF EXISTS `t_credit_market_commodity_coupon`;
CREATE TABLE `t_credit_market_commodity_coupon` (
  `market_item_id` bigint unsigned NOT NULL COMMENT '积分商城像编号',
  `coupon_id` bigint unsigned NOT NULL COMMENT '目标优惠券id',
  `threshold_price` decimal(10,2) NOT NULL COMMENT '最低可使用价格',
  `counteract_price` decimal(10,2) NOT NULL COMMENT '抵扣价格',
  `target_type` tinyint unsigned DEFAULT '0' COMMENT '优惠券使用对象类别表示；0通用券，1商品券',
  `store_id` bigint unsigned DEFAULT NULL COMMENT '商品对应的店家id',
  `store_name` varchar(100) DEFAULT NULL COMMENT '商品对应的店家名称',
  `commodity_id` bigint unsigned DEFAULT NULL COMMENT '商品编号',
  `commodity_pic_small` varchar(512) DEFAULT NULL COMMENT '商品正面图（small） 3张最佳',
  `commodity_name` varchar(255) DEFAULT NULL COMMENT '商品名',
  `time_type` tinyint unsigned DEFAULT '0' COMMENT '券的时间类型：0限时券；1区间券',
  `valid_duration` bigint unsigned DEFAULT NULL COMMENT '显示券的有效时长，自领取时开始计算',
  `start_time` bigint unsigned DEFAULT NULL COMMENT '区间券的可使用时间',
  `deadline` bigint unsigned DEFAULT NULL COMMENT '区间券的最后可使用时间',
  PRIMARY KEY (`market_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_credit_market_commodity_coupon
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
