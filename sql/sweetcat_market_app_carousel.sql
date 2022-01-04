/*
Navicat MySQL Data Transfer

Source Server         : VMmysql
Source Server Version : 80021
Source Host           : 192.168.193.129:3306
Source Database       : sweetcat_market_app_carousel

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2022-01-04 18:38:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_app_carousel`
-- ----------------------------
DROP TABLE IF EXISTS `t_app_carousel`;
CREATE TABLE `t_app_carousel` (
  `carousel_id` bigint unsigned NOT NULL COMMENT '轮播图id',
  `pic_path` varchar(255) NOT NULL COMMENT '图片路径',
  `target_url` varchar(255) NOT NULL COMMENT '目标链接',
  `create_time` bigint unsigned DEFAULT '0' COMMENT '创建时间',
  `update_time` bigint unsigned DEFAULT '0' COMMENT '修改时间',
  `start_time` bigint unsigned DEFAULT '0' COMMENT '开始时间',
  `deadline` bigint unsigned DEFAULT '0' COMMENT '结束时间',
  `is_show_immediately` tinyint unsigned DEFAULT '1' COMMENT '是否立即展示.0：不是；1：是',
  PRIMARY KEY (`carousel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_app_carousel
-- ----------------------------
INSERT INTO `t_app_carousel` VALUES ('1', 'http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg', 'xx', '1634356917370', '1634356917370', '1634356917370', '1636560000000', '1');
INSERT INTO `t_app_carousel` VALUES ('2', 'http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg', 'yy', '1634356917370', '1634356917370', '1634356917370', '1636560000000', '1');
INSERT INTO `t_app_carousel` VALUES ('1470299348150648832', 'http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg', '', '1639381511728', '1639381511728', '1639381511728', '1640966400000', '1');

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
