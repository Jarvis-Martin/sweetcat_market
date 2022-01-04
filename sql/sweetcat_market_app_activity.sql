/*
Navicat MySQL Data Transfer

Source Server         : VMmysql
Source Server Version : 80021
Source Host           : 192.168.193.129:3306
Source Database       : sweetcat_market_app_activity

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2022-01-04 18:38:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_app_activity`
-- ----------------------------
DROP TABLE IF EXISTS `t_app_activity`;
CREATE TABLE `t_app_activity` (
  `activity_id` bigint unsigned NOT NULL COMMENT '活动id',
  `pic_small` varchar(255) DEFAULT '' COMMENT '宣传小图',
  `pic_content` varchar(512) DEFAULT '' COMMENT '活动内容图',
  `is_show_immediately` tinyint unsigned DEFAULT '1' COMMENT '是否立即展示.0：不是；1：是',
  `target_url` varchar(255) DEFAULT '' COMMENT '目标链接',
  `create_time` bigint unsigned DEFAULT '0' COMMENT '创建时间',
  `update_time` bigint unsigned DEFAULT '0' COMMENT '修改时间',
  `start_time` bigint unsigned DEFAULT '0' COMMENT '开始时间',
  `deadline` bigint unsigned DEFAULT '0' COMMENT '结束时间',
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_app_activity
-- ----------------------------
INSERT INTO `t_app_activity` VALUES ('1', 'http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg', '[\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\",\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\",\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\",\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\"]', '1', 'xx', '1634381760870', '1634381760870', '1634381760870', '1640966400000');
INSERT INTO `t_app_activity` VALUES ('2', 'http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg', '[\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\",\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\",\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\",\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\"]', '1', 'yy', '1634381760870', '1634381760870', '1634381760870', '1640966400000');
INSERT INTO `t_app_activity` VALUES ('3', 'http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg', '[\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\",\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\",\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\",\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\"]', '1', 'zz', '1634381760870', '1634381760870', '1634381760870', '1640966400000');
INSERT INTO `t_app_activity` VALUES ('1470291261050191872', 'http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg', '[\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\",\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeghttp://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\"]', '1', '', '1639379522975', '1639379522975', '1639379522975', '1640966400000');
INSERT INTO `t_app_activity` VALUES ('1470299288767692800', 'http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg', '[\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\",\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeghttp://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\"]', '1', '', '1639379522975', '1639379522975', '1639379522975', '1640966400000');
INSERT INTO `t_app_activity` VALUES ('1470299329049788416', 'http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg', '[\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\",\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeghttp://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\"]', '1', '', '1639379522975', '1639379522975', '1639379522975', '1640966400000');
INSERT INTO `t_app_activity` VALUES ('1470299329834123264', 'http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg', '[\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\",\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeghttp://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\"]', '1', '', '1639379522975', '1639379522975', '1639379522975', '1640966400000');
INSERT INTO `t_app_activity` VALUES ('1470299330454880256', 'http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg', '[\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\",\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeghttp://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\"]', '1', '', '1639379522975', '1639379522975', '1639379522975', '1640966400000');
INSERT INTO `t_app_activity` VALUES ('1470299331176300544', 'http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg', '[\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\",\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeghttp://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\"]', '1', '', '1639379522975', '1639379522975', '1639379522975', '1640966400000');
INSERT INTO `t_app_activity` VALUES ('1470299331809640448', 'http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg', '[\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\",\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeghttp://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\"]', '1', '', '1639379522975', '1639379522975', '1639379522975', '1640966400000');
INSERT INTO `t_app_activity` VALUES ('1470299332489117696', 'http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg', '[\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\",\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeghttp://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\"]', '1', '', '1639379522975', '1639379522975', '1639379522975', '1640966400000');
INSERT INTO `t_app_activity` VALUES ('1470299333164400640', 'http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg', '[\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\",\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeghttp://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\"]', '1', '', '1639379522975', '1639379522975', '1639379522975', '1640966400000');
INSERT INTO `t_app_activity` VALUES ('1470299333898403840', 'http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg', '[\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\",\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeghttp://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\"]', '1', '', '1639379522975', '1639379522975', '1639379522975', '1640966400000');
INSERT INTO `t_app_activity` VALUES ('1470299334603046912', 'http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg', '[\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\",\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeghttp://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\"]', '1', '', '1639379522975', '1639379522975', '1639379522975', '1640966400000');
INSERT INTO `t_app_activity` VALUES ('1470299335316078592', 'http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg', '[\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\",\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeghttp://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\"]', '1', '', '1639379522975', '1639379522975', '1639379522975', '1640966400000');
INSERT INTO `t_app_activity` VALUES ('1470299335957807104', 'http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg', '[\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\",\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeghttp://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\"]', '1', '', '1639379522975', '1639379522975', '1639379522975', '1640966400000');
INSERT INTO `t_app_activity` VALUES ('1470299336620507136', 'http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg', '[\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\",\"http://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeghttp://localhost/img/carousel/bf62e726df3ed7825944c9f1546d5a08.jpeg\"]', '1', '', '1639379522975', '1639379522975', '1639379522975', '1640966400000');

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
