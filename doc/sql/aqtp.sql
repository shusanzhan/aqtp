/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : springsecurity

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2013-06-17 20:42:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `dbid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `discription` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `suqNo` int(11) DEFAULT NULL,
  `phone` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `fax` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `manager` int(11) DEFAULT NULL,
  PRIMARY KEY (`dbid`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('36', '总经办', '备注', null, '1', null, null, null);
INSERT INTO `department` VALUES ('37', '销售部', '销售部', '36', '1', null, null, null);
INSERT INTO `department` VALUES ('38', '技术部', '技术部', '36', '2', '', '', null);
INSERT INTO `department` VALUES ('39', '后勤部', '后勤部', '36', '2', null, null, null);
INSERT INTO `department` VALUES ('40', '财务部', '财务部', '36', '4', null, null, null);
INSERT INTO `department` VALUES ('41', '销售一部', '销售一部', '37', '1', '1', '1', '4');
INSERT INTO `department` VALUES ('45', '财务', '描述', '36', '2', '15123126474', '12344567', '3');

-- ----------------------------
-- Table structure for `enterprise`
-- ----------------------------
DROP TABLE IF EXISTS `enterprise`;
CREATE TABLE `enterprise` (
  `dbid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `fax` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `zipCode` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `address` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `webAddress` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `bank` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `account` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `content` varchar(8000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`dbid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of enterprise
-- ----------------------------
INSERT INTO `enterprise` VALUES ('2', '四川英盛科技', '023-62136558', '023-62136558', '111122', '111', '111', 'shusanzhan@163.com', '111', '111', '<p>\r\n	实施</p>\r\n');


-- ----------------------------
-- Table structure for `resource`
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `dbid` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `content` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `menu` tinyint(4) DEFAULT NULL,
  `orderNo` int(11) DEFAULT NULL,
  PRIMARY KEY (`dbid`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('16', 'url', null, '权限根节点', '0', '0', '0');
INSERT INTO `resource` VALUES ('17', null, '', '系统管理', '16', '0', '2');
INSERT INTO `resource` VALUES ('18', null, '/user/queryList', '用户管理', '17', '1', '0');
INSERT INTO `resource` VALUES ('19', null, '/user/edit', '用户编辑', '18', '2', '0');
INSERT INTO `resource` VALUES ('20', 'url', '/user/add', '用户添加', '18', '2', '1');
INSERT INTO `resource` VALUES ('21', 'url', '/user/delete', '用户删除', '18', '2', '2');
INSERT INTO `resource` VALUES ('22', 'url', '/role/queryList', '角色管理', '17', '1', '1');
INSERT INTO `resource` VALUES ('23', 'url', '/role/add', '角色添加', '22', '2', '0');
INSERT INTO `resource` VALUES ('24', null, '/role/edit', '角色编辑', '22', '2', '1');
INSERT INTO `resource` VALUES ('25', 'url', '/role/delete', '角色删除', '22', '2', '2');
INSERT INTO `resource` VALUES ('27', null, '', '公共消息', '16', '0', '1');
INSERT INTO `resource` VALUES ('29', null, '/news/queryList', '新闻管理', '27', '1', '1');
INSERT INTO `resource` VALUES ('31', null, '/news/add', '添加新闻', '29', '2', '2');
INSERT INTO `resource` VALUES ('32', null, '', '系统主页', '16', '0', '0');
INSERT INTO `resource` VALUES ('33', null, '/main/index', '系统主页', '32', '1', '0');
INSERT INTO `resource` VALUES ('34', null, '/resource/list', '资源管理', '17', '1', '2');
INSERT INTO `resource` VALUES ('35', null, '/resource/add', '添加资源', '34', '2', '0');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `dbid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `note` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`dbid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '超级管理员', '1', '超级管理');
INSERT INTO `role` VALUES ('2', '管理员', '1', '管理员');

-- ----------------------------
-- Table structure for `roleresource`
-- ----------------------------
DROP TABLE IF EXISTS `roleresource`;
CREATE TABLE `roleresource` (
  `roleId` int(11) NOT NULL,
  `resourceId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of roleresource
-- ----------------------------
INSERT INTO `roleresource` VALUES ('2', '33');
INSERT INTO `roleresource` VALUES ('2', '32');
INSERT INTO `roleresource` VALUES ('2', '31');
INSERT INTO `roleresource` VALUES ('2', '27');
INSERT INTO `roleresource` VALUES ('2', '16');
INSERT INTO `roleresource` VALUES ('2', '29');
INSERT INTO `roleresource` VALUES ('1', '17');
INSERT INTO `roleresource` VALUES ('1', '25');
INSERT INTO `roleresource` VALUES ('1', '23');
INSERT INTO `roleresource` VALUES ('1', '20');
INSERT INTO `roleresource` VALUES ('1', '18');
INSERT INTO `roleresource` VALUES ('1', '34');
INSERT INTO `roleresource` VALUES ('1', '16');
INSERT INTO `roleresource` VALUES ('1', '29');
INSERT INTO `roleresource` VALUES ('1', '19');
INSERT INTO `roleresource` VALUES ('1', '27');
INSERT INTO `roleresource` VALUES ('1', '22');
INSERT INTO `roleresource` VALUES ('1', '33');
INSERT INTO `roleresource` VALUES ('1', '31');
INSERT INTO `roleresource` VALUES ('1', '32');
INSERT INTO `roleresource` VALUES ('1', '35');
INSERT INTO `roleresource` VALUES ('1', '24');
INSERT INTO `roleresource` VALUES ('1', '21');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `dbid` int(11) NOT NULL AUTO_INCREMENT,
  `userId` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `realName` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`dbid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('3', 'admin', '舒三战', 'b594510740d2ac4261c1b2fe87850d08', 'shusanzhan@163.com');
INSERT INTO `user` VALUES ('4', 'shusanzhan', '张三', 'dd38d1980aa84412e042c7a01aa4312b', 'shusanzhan@163.com');

-- ----------------------------
-- Table structure for `userroles`
-- ----------------------------
DROP TABLE IF EXISTS `userroles`;
CREATE TABLE `userroles` (
  `userId` int(11) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of userroles
-- ----------------------------
INSERT INTO `userroles` VALUES ('3', '1');
