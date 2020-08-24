/*
 Navicat Premium Data Transfer

 Source Server         : JDBC
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : oa

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 25/07/2020 15:06:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `loginname` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录名',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `status` int(1) NULL DEFAULT NULL COMMENT '状态',
  `createDate` datetime(0) NULL DEFAULT NULL COMMENT '建档日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (1, 'zhnagsan', 'san', '1', 0, '2020-07-21 01:28:56');
INSERT INTO `account` VALUES (6, '十大杀手', '大飒飒', '1', 1, '2020-07-06 09:20:05');
INSERT INTO `account` VALUES (26, 'jack1111', 'jack1', '1', 1, '2020-07-25 14:07:55');
INSERT INTO `account` VALUES (27, 'jack', 'jack11', '1', 1, '2020-07-25 14:18:14');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `remark` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (20, '人事', '招人');
INSERT INTO `department` VALUES (21, '后勤', '做杂事');
INSERT INTO `department` VALUES (22, '财务', '管钱的');

-- ----------------------------
-- Table structure for document
-- ----------------------------
DROP TABLE IF EXISTS `document`;
CREATE TABLE `document`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fileName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `createDate` datetime(0) NULL DEFAULT NULL,
  `uid` int(10) NULL DEFAULT NULL COMMENT ' 是外键连用户表主键',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_uid`(`uid`) USING BTREE,
  CONSTRAINT `fk_uid` FOREIGN KEY (`uid`) REFERENCES `account` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of document
-- ----------------------------
INSERT INTO `document` VALUES (14, '请假', 'CSS 参考手册V4.2.3.chw', '请假', '2020-07-25 00:00:00', 1);
INSERT INTO `document` VALUES (15, 'java基础', 'JDK_API_1_6_zh_CN(1).chw', '讲java的', '2020-07-25 00:00:00', 1);

-- ----------------------------
-- Table structure for email
-- ----------------------------
DROP TABLE IF EXISTS `email`;
CREATE TABLE `email`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createtime` date NOT NULL,
  `attachment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件的链接URL',
  `toId` int(11) NULL DEFAULT NULL COMMENT '外键，指向用户表',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_toId`(`toId`) USING BTREE,
  CONSTRAINT `fk_toId` FOREIGN KEY (`toId`) REFERENCES `account` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 68 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of email
-- ----------------------------
INSERT INTO `email` VALUES (73, '接单', '大阿斯顿', '2020-07-25', '0225三阶段机试.doc', 6);
INSERT INTO `email` VALUES (74, '阿诗丹顿撒', '大飒飒大所大撒大所多', '2020-07-25', 'JDK_API_1_6_zh_CN(1).chw', 26);

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cardId` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `postCode` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qqNum` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` int(1) NULL DEFAULT NULL,
  `party` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `race` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `education` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `speciality` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hobby` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createDate` datetime(0) NULL DEFAULT NULL,
  `uid` int(10) NULL DEFAULT NULL COMMENT '用户表主键',
  `did` int(10) NULL DEFAULT NULL COMMENT '部门表主键',
  `jid` int(10) NULL DEFAULT NULL COMMENT '职位表主键',
  `phone` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_did`(`did`) USING BTREE,
  INDEX `fk_jid`(`jid`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  CONSTRAINT `fk_did` FOREIGN KEY (`did`) REFERENCES `department` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_jid` FOREIGN KEY (`jid`) REFERENCES `job` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `uid` FOREIGN KEY (`uid`) REFERENCES `account` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (24, 'lili', '11', '苏州', '123', '1235674', '7899', '13812345678', 2, '党员', '2020-07-01', '1232', '萨达萨达', '撒', '123232', 'sdassdasad', '2020-07-25 14:38:56', 1, 20, 17, '13338963389');
INSERT INTO `employee` VALUES (25, 'lili', '123', '苏州', '123', '1235674', '12', '13812345678', 0, '党员', '2020-07-01', '的三大', '萨达萨达', '3453', '但是', '1122', '2020-07-25 14:40:36', 1, 21, 14, '13338963389');

-- ----------------------------
-- Table structure for inform
-- ----------------------------
DROP TABLE IF EXISTS `inform`;
CREATE TABLE `inform`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `createDate` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of inform
-- ----------------------------
INSERT INTO `inform` VALUES (14, '今日头条', '没啥新闻', '2020-07-25 09:06:21');
INSERT INTO `inform` VALUES (15, '今日放假', '放三十天', '2020-07-25 10:24:52');
INSERT INTO `inform` VALUES (16, '七宗罪', '暴力血腥', '2020-07-25 14:44:35');

-- ----------------------------
-- Table structure for job
-- ----------------------------
DROP TABLE IF EXISTS `job`;
CREATE TABLE `job`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `remark` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of job
-- ----------------------------
INSERT INTO `job` VALUES (14, '营销人员', '做营销');
INSERT INTO `job` VALUES (15, '销售人员', '买东西');
INSERT INTO `job` VALUES (16, '人事小姐姐', '招人');
INSERT INTO `job` VALUES (17, '助理', '做杂事');

-- ----------------------------
-- Table structure for signin
-- ----------------------------
DROP TABLE IF EXISTS `signin`;
CREATE TABLE `signin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createtime` datetime(0) NULL DEFAULT NULL,
  `flag` int(11) NULL DEFAULT NULL COMMENT '是否有效：1有效2无效',
  `uid` int(11) NULL DEFAULT NULL COMMENT '用户表主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of signin
-- ----------------------------
INSERT INTO `signin` VALUES (32, '2020-07-23 10:57:08', 2, 1);
INSERT INTO `signin` VALUES (33, '2020-07-23 18:57:23', 1, 1);
INSERT INTO `signin` VALUES (34, '2020-07-24 10:57:36', 2, 1);
INSERT INTO `signin` VALUES (35, '2020-07-25 18:57:46', 1, 1);
INSERT INTO `signin` VALUES (37, '2020-07-25 09:36:16', 2, 1);
INSERT INTO `signin` VALUES (38, '2020-07-25 14:36:25', 2, 1);
INSERT INTO `signin` VALUES (39, '2020-07-26 14:52:34', 2, 1);

SET FOREIGN_KEY_CHECKS = 1;
