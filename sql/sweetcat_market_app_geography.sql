/*
Navicat MySQL Data Transfer

Source Server         : VMmysql
Source Server Version : 80021
Source Host           : 192.168.193.129:3306
Source Database       : sweetcat_market_app_geography

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2022-01-04 18:39:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_app_geography`
-- ----------------------------
DROP TABLE IF EXISTS `t_app_geography`;
CREATE TABLE `t_app_geography` (
  `address_code` char(12) NOT NULL COMMENT '鍦板尯鐮?',
  `address_name` varchar(20) NOT NULL COMMENT '地区名',
  `province_code` char(12) DEFAULT '' COMMENT '该地址对应的省码',
  `city_code` char(12) DEFAULT '' COMMENT '该地址对应的市码',
  `area_code` char(12) DEFAULT '' COMMENT '该地址对应的区码',
  `town_code` char(12) DEFAULT '' COMMENT '该地址对应的县码',
  `create_time` bigint unsigned DEFAULT '0' COMMENT '创建时间',
  `update_time` bigint unsigned DEFAULT '0' COMMENT '修改时间',
  PRIMARY KEY (`address_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_app_geography
-- ----------------------------
INSERT INTO `t_app_geography` VALUES ('11223344', '北京', '河北', '11', '22', '33', '123456789', '123456789');

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
