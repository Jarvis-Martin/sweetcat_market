/*
Navicat MySQL Data Transfer

Source Server         : VMmysql
Source Server Version : 80021
Source Host           : 192.168.193.129:3306
Source Database       : sweetcat_market_app_customerservice

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2022-01-04 18:39:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_app_customerservice_feedback_from_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_app_customerservice_feedback_from_user`;
CREATE TABLE `t_app_customerservice_feedback_from_user` (
  `record_id` bigint unsigned NOT NULL COMMENT '记录id',
  `feedback_id` bigint unsigned NOT NULL COMMENT '待处理或已处理的用户反馈的id',
  `create_time` bigint unsigned DEFAULT '0' COMMENT '反馈创建时间',
  `update_time` bigint unsigned DEFAULT '0' COMMENT '记录更新时间',
  `process_time` bigint unsigned NOT NULL DEFAULT '0' COMMENT '鍙嶉澶勭悊鏃堕棿',
  `status` tinyint DEFAULT NULL COMMENT '反馈状态：0为处理，1已处理',
  `receiver_id` bigint NOT NULL,
  `customer_staff_id` bigint unsigned DEFAULT NULL COMMENT '澶勭悊璇ュ弽棣堢殑瀹㈡湇id',
  `response_title` varchar(128) DEFAULT NULL,
  `response_content` varchar(512) NOT NULL COMMENT '处理后的响应内容',
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_app_customerservice_feedback_from_user
-- ----------------------------

-- ----------------------------
-- Table structure for `t_app_customerservice_staff_info`
-- ----------------------------
DROP TABLE IF EXISTS `t_app_customerservice_staff_info`;
CREATE TABLE `t_app_customerservice_staff_info` (
  `staff_id` bigint unsigned NOT NULL COMMENT '客服id',
  `staff_nickname` varchar(50) NOT NULL COMMENT '客服昵称',
  `staff_avatar` varchar(128) DEFAULT '''''',
  `create_time` bigint unsigned DEFAULT '0' COMMENT '账号创建时间',
  `update_time` bigint unsigned DEFAULT '0' COMMENT '账号更新时间',
  PRIMARY KEY (`staff_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_app_customerservice_staff_info
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
