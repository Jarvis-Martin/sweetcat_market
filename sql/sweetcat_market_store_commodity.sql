/*
Navicat MySQL Data Transfer

Source Server         : VMmysql
Source Server Version : 80021
Source Host           : 192.168.193.129:3306
Source Database       : sweetcat_market_store_commodity

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2022-01-04 18:39:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_store_commodity_comment`
-- ----------------------------
DROP TABLE IF EXISTS `t_store_commodity_comment`;
CREATE TABLE `t_store_commodity_comment` (
  `comment_id` bigint unsigned NOT NULL COMMENT '评论编号',
  `user_id` bigint unsigned NOT NULL COMMENT '发布评论的用户id',
  `commodity_id` bigint unsigned NOT NULL COMMENT '被评论商品的id',
  `content` varchar(512) DEFAULT '' COMMENT '评论内容',
  `content_pics` varchar(512) DEFAULT '' COMMENT '评论配图',
  `stars` tinyint unsigned DEFAULT '0' COMMENT '星级',
  `create_time` bigint unsigned DEFAULT '0' COMMENT '发布时间',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_store_commodity_comment
-- ----------------------------

-- ----------------------------
-- Table structure for `t_store_commodity_info`
-- ----------------------------
DROP TABLE IF EXISTS `t_store_commodity_info`;
CREATE TABLE `t_store_commodity_info` (
  `commodity_id` bigint unsigned NOT NULL COMMENT '商品od',
  `store_id` bigint unsigned NOT NULL COMMENT '店铺id',
  `commodity_name` varchar(255) DEFAULT '' COMMENT '商品名',
  `brand` varchar(255) DEFAULT '' COMMENT '品牌',
  `pic_small` varchar(512) NOT NULL COMMENT '商品正面图（small）',
  `pic_big` varchar(512) NOT NULL COMMENT '商品主图（big）',
  `first_category` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '涓€绾у垎绫?',
  `second_category` varchar(128) NOT NULL COMMENT '二级分类',
  `third_category` varchar(128) NOT NULL COMMENT '三级分类',
  `use_times` tinyint unsigned DEFAULT '0' COMMENT '几手货.0：全新；1：二手货',
  `production_area` varchar(255) DEFAULT '0' COMMENT '产地',
  `old_price` decimal(10,2) unsigned DEFAULT '0.00' COMMENT '原价',
  `current_price` decimal(10,2) unsigned NOT NULL COMMENT '现价',
  `description` varchar(512) DEFAULT '0' COMMENT '宝贝描述',
  `stock` int unsigned DEFAULT '0' COMMENT '库存',
  `monthly_sales` int unsigned DEFAULT '0' COMMENT '月销量',
  `add_to_car_number` int unsigned DEFAULT '0' COMMENT '加购数',
  `collect_number` int unsigned DEFAULT '0' COMMENT '收藏数',
  `guarantee` varchar(512) DEFAULT '' COMMENT '保障',
  `create_time` bigint unsigned DEFAULT '0' COMMENT '创建时间',
  `update_time` bigint unsigned DEFAULT '0' COMMENT '修改时间',
  `status` bigint unsigned DEFAULT '0' COMMENT '商品状态.0： 待审核; 1： 审核通过; 2： 审核未通过; 3：已下架',
  `specification` varchar(512) NOT NULL COMMENT '商品规格',
  `comment_number` bigint unsigned DEFAULT '0' COMMENT '评论总数',
  `default_post_charge` decimal(10,2) DEFAULT '0.00' COMMENT '默认运费',
  `subjoin_charge_per_good` decimal(10,2) DEFAULT '0.00' COMMENT '商品多余一件时，多出的商品每件多少运费',
  `is_credit_can_offset` tinyint DEFAULT '0' COMMENT '是否为积分商品。即购买时可够用积分抵扣部分金额。0不是，1是。当 coin_counteract_rate > 0时，该值必须为 1',
  `coin_counteract_rate` decimal(10,2) DEFAULT '0.00' COMMENT '金币抵扣率,取值范围：0% ~ 99%',
  PRIMARY KEY (`commodity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_store_commodity_info
-- ----------------------------

-- ----------------------------
-- Table structure for `t_store_commodity_post_charge`
-- ----------------------------
DROP TABLE IF EXISTS `t_store_commodity_post_charge`;
CREATE TABLE `t_store_commodity_post_charge` (
  `charge_id` bigint unsigned NOT NULL COMMENT '记录id',
  `commodity_id` bigint unsigned NOT NULL COMMENT '商品id',
  `store_id` bigint unsigned NOT NULL COMMENT '商家id',
  `province_code` varchar(100) NOT NULL COMMENT '省名',
  `update_time` bigint NOT NULL DEFAULT '0',
  `create_time` bigint NOT NULL DEFAULT '0',
  `post_charge` decimal(10,2) unsigned DEFAULT '0.00' COMMENT '运费',
  PRIMARY KEY (`charge_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_store_commodity_post_charge
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
