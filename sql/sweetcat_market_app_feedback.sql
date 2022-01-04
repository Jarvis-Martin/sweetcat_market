/*
Navicat MySQL Data Transfer

Source Server         : VMmysql
Source Server Version : 80021
Source Host           : 192.168.193.129:3306
Source Database       : sweetcat_market_app_feedback

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2022-01-04 18:39:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_app_feedback`
-- ----------------------------
DROP TABLE IF EXISTS `t_app_feedback`;
CREATE TABLE `t_app_feedback` (
  `feedback_id` bigint unsigned NOT NULL COMMENT '反馈记录id',
  `user_id` bigint unsigned NOT NULL COMMENT '评论人id',
  `content` varchar(255) DEFAULT NULL COMMENT '反馈内容',
  `feedback_pics` varchar(521) DEFAULT '[]' COMMENT '反馈配图',
  `status` tinyint unsigned DEFAULT '0' COMMENT '状态',
  `create_time` bigint unsigned DEFAULT '0' COMMENT '创建时间',
  `processor_id` bigint DEFAULT NULL,
  `response_title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '''''',
  `response_content` varchar(512) DEFAULT NULL,
  `process_time` bigint unsigned DEFAULT '0' COMMENT '处理时间',
  PRIMARY KEY (`feedback_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_app_feedback
-- ----------------------------
INSERT INTO `t_app_feedback` VALUES ('1459724366085488640', '660946505393242112', '用户反馈的问题', '[\"$10$q4DKg6oRKcBS/j8J6qfp.OY5QQig6A0SZvQhodRUi/bmZUMtxdiPK\",\"$10$q4DKg6oRKcBS/j8J6qfp.OY5QQig6A0SZvQhodRUi/bmZUMtxdiPK\"]', '0', '1636860378324', null, null, null, null);
INSERT INTO `t_app_feedback` VALUES ('1459741390534737920', '660946505393242112', '用户反馈的问题132456', '[\"$10$q4DKg6oRKcBS/j8J6qfp.OY5QQig6A0SZvQhodRUi/bmZUMtxdiPK\",\"$10$q4DKg6oRKcBS/j8J6qfp.OY5QQig6A0SZvQhodRUi/bmZUMtxdiPK\"]', '0', '1636860378324', null, null, null, null);
INSERT INTO `t_app_feedback` VALUES ('1459743015286472704', '660946505393242112', '用户反馈的问题132456789', '[\"$10$q4DKg6oRKcBS/j8J6qfp.OY5QQig6A0SZvQhodRUi/bmZUMtxdiPK\",\"$10$q4DKg6oRKcBS/j8J6qfp.OY5QQig6A0SZvQhodRUi/bmZUMtxdiPK\"]', '0', '1636860378324', null, null, null, null);
INSERT INTO `t_app_feedback` VALUES ('1459744713732128768', '660946505393242112', '用户反馈的问题132456789', '[\"$10$q4DKg6oRKcBS/j8J6qfp.OY5QQig6A0SZvQhodRUi/bmZUMtxdiPK\",\"$10$q4DKg6oRKcBS/j8J6qfp.OY5QQig6A0SZvQhodRUi/bmZUMtxdiPK\"]', '0', '1636860378324', '1459502946973777920', null, '0000000000000000000000', '1636860489000');
INSERT INTO `t_app_feedback` VALUES ('1463739782424297472', '660946505393242112', '反馈给客服人员的问题', '[\"http://localhost/img/avatar/3b62d83f-23b3-49f8-b557-243436effb17u\\u003d1729126734\",\"721726763\\u0026fm\\u003d26\\u0026gp\\u003d0.jpg\",\"http://localhost/img/avatar/3b62d83f-23b3-49f8-b557-243436effb17u\\u003d1729126734\",\"721726763\\u0026fm\\u003d26\\u0026gp\\u003d0.jpg\",\"http://localhost/img/avatar/3b62d83f-23b3-49f8-b557-243436effb17u\\u003d1729126734\",\"721726763\\u0026fm\\u003d26\\u0026gp\\u003d0.jpg\"]', '0', '1637817717902', '1459502946973777920', '测试sweetcat-user-information', '用于测试sweetcat-user-information', '1637922619716');
INSERT INTO `t_app_feedback` VALUES ('1474962099036880896', '660946505393242112', 'test TCC mode', '[\"http:localhost:80/img/icon_default_avatar.png\",\"http:localhost:80/img/icon_default_avatar.png\",\"http:localhost:80/img/icon_default_avatar.png\"]', '0', '1640489103153', null, null, null, null);
INSERT INTO `t_app_feedback` VALUES ('1474962464968933376', '660946505393242112', 'test TCC mode', '[\"http:localhost:80/img/icon_default_avatar.png\",\"http:localhost:80/img/icon_default_avatar.png\",\"http:localhost:80/img/icon_default_avatar.png\"]', '0', '1640489103153', null, null, null, null);
INSERT INTO `t_app_feedback` VALUES ('1474963003576287232', '660946505393242112', 'test TCC mode', '[\"http:localhost:80/img/icon_default_avatar.png\",\"http:localhost:80/img/icon_default_avatar.png\",\"http:localhost:80/img/icon_default_avatar.png\"]', '0', '1640489103153', null, null, null, null);
INSERT INTO `t_app_feedback` VALUES ('1474963964931735552', '660946505393242112', 'test TCC mode', '[\"http:localhost:80/img/icon_default_avatar.png\",\"http:localhost:80/img/icon_default_avatar.png\",\"http:localhost:80/img/icon_default_avatar.png\"]', '0', '1640489103153', null, null, null, null);
INSERT INTO `t_app_feedback` VALUES ('1474964011048108032', '660946505393242112', 'test TCC mode', '[\"http:localhost:80/img/icon_default_avatar.png\",\"http:localhost:80/img/icon_default_avatar.png\",\"http:localhost:80/img/icon_default_avatar.png\"]', '0', '1640489103153', null, null, null, null);
INSERT INTO `t_app_feedback` VALUES ('1474964257119535104', '660946505393242112', 'test TCC mode', '[\"http:localhost:80/img/icon_default_avatar.png\",\"http:localhost:80/img/icon_default_avatar.png\",\"http:localhost:80/img/icon_default_avatar.png\"]', '0', '1640489103153', null, null, null, null);
INSERT INTO `t_app_feedback` VALUES ('1474966150696796160', '660946505393242112', 'test TCC mode', '[\"http:localhost:80/img/icon_default_avatar.png\",\"http:localhost:80/img/icon_default_avatar.png\",\"http:localhost:80/img/icon_default_avatar.png\"]', '0', '1640489103153', null, null, null, null);

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
