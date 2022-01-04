/*
Navicat MySQL Data Transfer

Source Server         : VMmysql
Source Server Version : 80021
Source Host           : 192.168.193.129:3306
Source Database       : sweetcat_market_user_comment

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2022-01-04 18:41:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_user_comment`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_comment`;
CREATE TABLE `t_user_comment` (
  `comment_id` bigint unsigned NOT NULL COMMENT '评论id',
  `publisher_id` bigint unsigned NOT NULL COMMENT '鍙戝竷璇勮鐨勭敤鎴穒d',
  `create_time` bigint unsigned DEFAULT '0' COMMENT '发布时间',
  `content` varchar(512) DEFAULT '' COMMENT '评论内容',
  `comment_type` tinyint NOT NULL DEFAULT '0' COMMENT '评论的类型；0商品评价；1商评价用户评论',
  `commodity_id` bigint NOT NULL DEFAULT '0',
  `content_pics` varchar(512) DEFAULT '' COMMENT '评论配图',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_user_comment
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user_comment_comment`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_comment_comment`;
CREATE TABLE `t_user_comment_comment` (
  `comment_id` bigint unsigned NOT NULL COMMENT '评论id',
  `parent_comment_id` bigint unsigned NOT NULL COMMENT '被评论的那条评论的id',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_user_comment_comment
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user_comment_commodity`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_comment_commodity`;
CREATE TABLE `t_user_comment_commodity` (
  `comment_id` bigint unsigned NOT NULL COMMENT '评论id',
  `commodity_id` bigint unsigned NOT NULL COMMENT '被评论商品的id',
  `stars` bigint unsigned DEFAULT '0' COMMENT '星级',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_user_comment_commodity
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
