/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : aqtp

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2013-07-12 18:51:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `breaderbreed`
-- ----------------------------
DROP TABLE IF EXISTS `breaderbreed`;
CREATE TABLE `breaderbreed` (
  `dbid` int(11) NOT NULL AUTO_INCREMENT,
  `breederId` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `chickenBatchDbid` int(11) DEFAULT NULL,
  PRIMARY KEY (`dbid`),
  KEY `FK_Reference_12` (`chickenBatchDbid`),
  KEY `FK_Reference_13` (`breederId`),
  CONSTRAINT `FK_Reference_12` FOREIGN KEY (`chickenBatchDbid`) REFERENCES `chickenbatch` (`dbid`),
  CONSTRAINT `FK_Reference_13` FOREIGN KEY (`breederId`) REFERENCES `breeder` (`dbid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of breaderbreed
-- ----------------------------

-- ----------------------------
-- Table structure for `breed`
-- ----------------------------
DROP TABLE IF EXISTS `breed`;
CREATE TABLE `breed` (
  `dbid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `characteristic` varchar(500) DEFAULT NULL,
  `note` varchar(2000) DEFAULT NULL,
  `charCode` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`dbid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='Ʒϵ��';

-- ----------------------------
-- Records of breed
-- ----------------------------
INSERT INTO `breed` VALUES ('3', '品系5', '品系5品系5品系5品系5品系5品系5品系5品系5品系5品系5品系5', '品系5品系5品系5品系5品系5品系5品系5品系5品系5品系5品系5品系5品系5品系5品系5品系', '5');
INSERT INTO `breed` VALUES ('4', '品系', '特性', '特性', '12');
INSERT INTO `breed` VALUES ('6', '12', '12', '12', '12');

-- ----------------------------
-- Table structure for `breeder`
-- ----------------------------
DROP TABLE IF EXISTS `breeder`;
CREATE TABLE `breeder` (
  `dbid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `photo` varchar(500) DEFAULT NULL,
  `educationalBackground` varchar(50) DEFAULT NULL,
  `graduationSchool` varchar(50) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`dbid`),
  KEY `FK_Reference_18` (`userId`),
  CONSTRAINT `FK_Reference_18` FOREIGN KEY (`userId`) REFERENCES `user` (`dbid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of breeder
-- ----------------------------
INSERT INTO `breeder` VALUES ('2', '张三', '男', '2013-06-30', '/archives/3/2013-07-07/201307071014171359684134584.jpg', '大专生', '毕业学校', '4');
INSERT INTO `breeder` VALUES ('6', 'admin', '男', '2013-07-17', '', '小学', '12', '9');
INSERT INTO `breeder` VALUES ('7', 'shusanz', '男', '2013-07-10', '', '小学', '', '10');

-- ----------------------------
-- Table structure for `chickenbatch`
-- ----------------------------
DROP TABLE IF EXISTS `chickenbatch`;
CREATE TABLE `chickenbatch` (
  `dbid` int(11) NOT NULL AUTO_INCREMENT,
  `batchNo` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `outBarDate` datetime DEFAULT NULL,
  `intoBarDate` datetime DEFAULT NULL,
  `gradeLevelDbid` int(11) DEFAULT NULL,
  `breedDbid` int(11) DEFAULT NULL,
  PRIMARY KEY (`dbid`),
  KEY `FK_Reference_2` (`gradeLevelDbid`),
  KEY `FK_Reference_3` (`breedDbid`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`gradeLevelDbid`) REFERENCES `grade` (`dbid`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`breedDbid`) REFERENCES `breed` (`dbid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of chickenbatch
-- ----------------------------
INSERT INTO `chickenbatch` VALUES ('1', '2013072301', '批次1', '2013-07-07', '1', '2013-07-20 17:05:14', '2013-07-08 17:05:19', '1', '3');

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
-- Table structure for `dimensiona`
-- ----------------------------
DROP TABLE IF EXISTS `dimensiona`;
CREATE TABLE `dimensiona` (
  `DBID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `chickenBatchDbid` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`DBID`),
  KEY `FK_Reference_20` (`chickenBatchDbid`),
  CONSTRAINT `FK_Reference_20` FOREIGN KEY (`chickenBatchDbid`) REFERENCES `chickenbatch` (`dbid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of dimensiona
-- ----------------------------
INSERT INTO `dimensiona` VALUES ('6', '112', '2013-07-12 18:04:16', '1', '1', '3');
INSERT INTO `dimensiona` VALUES ('7', '122', '2013-07-12 18:20:26', '11', '1', '3');

-- ----------------------------
-- Table structure for `dimensionacode`
-- ----------------------------
DROP TABLE IF EXISTS `dimensionacode`;
CREATE TABLE `dimensionacode` (
  `dbid` int(11) NOT NULL AUTO_INCREMENT,
  `code` int(11) DEFAULT NULL,
  `photo` varchar(500) DEFAULT NULL,
  `dimensionaId` int(11) DEFAULT NULL,
  PRIMARY KEY (`dbid`),
  KEY `FK_Reference_19` (`dimensionaId`),
  CONSTRAINT `FK_Reference_19` FOREIGN KEY (`dimensionaId`) REFERENCES `dimensiona` (`DBID`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of dimensionacode
-- ----------------------------
INSERT INTO `dimensionacode` VALUES ('15', '1', '/dimensiona/6/61.png', '6');
INSERT INTO `dimensionacode` VALUES ('17', '1', '/dimensiona/7/71.png', '7');
INSERT INTO `dimensionacode` VALUES ('18', '2', '/dimensiona/7/72.png', '7');
INSERT INTO `dimensionacode` VALUES ('19', '3', '/dimensiona/7/73.png', '7');
INSERT INTO `dimensionacode` VALUES ('20', '4', '/dimensiona/7/74.png', '7');
INSERT INTO `dimensionacode` VALUES ('21', '5', '/dimensiona/7/75.png', '7');
INSERT INTO `dimensionacode` VALUES ('22', '6', '/dimensiona/7/76.png', '7');
INSERT INTO `dimensionacode` VALUES ('23', '7', '/dimensiona/7/77.png', '7');
INSERT INTO `dimensionacode` VALUES ('24', '8', '/dimensiona/7/78.png', '7');
INSERT INTO `dimensionacode` VALUES ('25', '9', '/dimensiona/7/79.png', '7');
INSERT INTO `dimensionacode` VALUES ('26', '10', '/dimensiona/7/710.png', '7');
INSERT INTO `dimensionacode` VALUES ('27', '11', '/dimensiona/7/711.png', '7');

-- ----------------------------
-- Table structure for `drag`
-- ----------------------------
DROP TABLE IF EXISTS `drag`;
CREATE TABLE `drag` (
  `dbid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `generateBatch` varchar(100) DEFAULT NULL,
  `effect` varchar(2000) DEFAULT NULL,
  `specification` varchar(100) DEFAULT NULL,
  `directions` varchar(8000) DEFAULT NULL,
  `note` varchar(2000) DEFAULT NULL,
  `dragTypeId` int(11) DEFAULT NULL,
  `recordId` int(11) DEFAULT NULL,
  PRIMARY KEY (`dbid`),
  KEY `FK_Reference_14` (`dragTypeId`),
  CONSTRAINT `FK_Reference_14` FOREIGN KEY (`dragTypeId`) REFERENCES `dragtype` (`dbid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of drag
-- ----------------------------
INSERT INTO `drag` VALUES ('1', '名称', '生产批号', '药品作用药品作用药品作用药品作用药品作用药品作用', '药品规格', '药品说明药品说明药品说明药品说明药品说明药品说明药品说明药品说明药品说明药品说明', '备注', '2', null);
INSERT INTO `drag` VALUES ('2', '11', '12', '12', '12', '12', '12', '1', null);
INSERT INTO `drag` VALUES ('4', '1', '1', '1', '1', '1', '1', '2', null);

-- ----------------------------
-- Table structure for `dragtype`
-- ----------------------------
DROP TABLE IF EXISTS `dragtype`;
CREATE TABLE `dragtype` (
  `dbid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`dbid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of dragtype
-- ----------------------------
INSERT INTO `dragtype` VALUES ('1', '保健药', '1');
INSERT INTO `dragtype` VALUES ('2', '检疫用药', '2');

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
-- Table structure for `feeder`
-- ----------------------------
DROP TABLE IF EXISTS `feeder`;
CREATE TABLE `feeder` (
  `dbid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `elementsPercentage` varchar(200) DEFAULT NULL,
  `image` varchar(500) DEFAULT NULL,
  `note` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`dbid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of feeder
-- ----------------------------
INSERT INTO `feeder` VALUES ('1', '名称', '组成百分比', '/archives/3/2013-07-07/201307070820141359684134584.jpg', '备注');
INSERT INTO `feeder` VALUES ('4', '名称', '组成百分比', '/archives/3/2013-07-07/201307070822301360133534655.jpg', '备注备注备注备注备注备注备注备注备注备注备注备注备注备注备注备注备注备注备注备注备注');
INSERT INTO `feeder` VALUES ('5', '久久感冒灵', '玉米30%；糯康30%；', '/archives/3/2013-07-07/20130707082320IMAG0266.jpg', '备注:备注:备注:');
INSERT INTO `feeder` VALUES ('7', 'dd ', '1221212', '/archives/3/2013-07-07/20130707115839qrcode_for_gh_b8695d816d88_430.jpg', 'testssd');

-- ----------------------------
-- Table structure for `feedfeeder`
-- ----------------------------
DROP TABLE IF EXISTS `feedfeeder`;
CREATE TABLE `feedfeeder` (
  `dbid` int(11) NOT NULL AUTO_INCREMENT,
  `feederId` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `chickenBatchDbid` int(11) DEFAULT NULL,
  PRIMARY KEY (`dbid`),
  KEY `FK_Reference_10` (`chickenBatchDbid`),
  KEY `FK_Reference_11` (`feederId`),
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`chickenBatchDbid`) REFERENCES `chickenbatch` (`dbid`),
  CONSTRAINT `FK_Reference_11` FOREIGN KEY (`feederId`) REFERENCES `feeder` (`dbid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of feedfeeder
-- ----------------------------

-- ----------------------------
-- Table structure for `grade`
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `dbid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `level` varchar(50) DEFAULT NULL,
  `note` varchar(2000) DEFAULT NULL,
  `retailPrice` float DEFAULT NULL,
  PRIMARY KEY (`dbid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of grade
-- ----------------------------
INSERT INTO `grade` VALUES ('1', '特级', '特级', '无备注信息无备注信息无备注信息无备注信息无备注信息无备注信息无备注信息无备注信息', '10');
INSERT INTO `grade` VALUES ('2', 'test', '122', '112', '12');
INSERT INTO `grade` VALUES ('3', '特殊t', '1112', '1212', '12');

-- ----------------------------
-- Table structure for `healthcare`
-- ----------------------------
DROP TABLE IF EXISTS `healthcare`;
CREATE TABLE `healthcare` (
  `dbid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `beginDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `chickenBatchDbid` int(11) DEFAULT NULL,
  PRIMARY KEY (`dbid`),
  KEY `FK_Reference_5` (`chickenBatchDbid`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`chickenBatchDbid`) REFERENCES `chickenbatch` (`dbid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of healthcare
-- ----------------------------

-- ----------------------------
-- Table structure for `healthcaredrag`
-- ----------------------------
DROP TABLE IF EXISTS `healthcaredrag`;
CREATE TABLE `healthcaredrag` (
  `dbid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `dose` varchar(50) DEFAULT NULL,
  `healthCareId` int(11) DEFAULT NULL,
  `dragId` int(11) DEFAULT NULL,
  PRIMARY KEY (`dbid`),
  KEY `FK_Reference_15` (`dragId`),
  KEY `FK_Reference_6` (`healthCareId`),
  CONSTRAINT `FK_Reference_15` FOREIGN KEY (`dragId`) REFERENCES `drag` (`dbid`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`healthCareId`) REFERENCES `healthcare` (`dbid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of healthcaredrag
-- ----------------------------

-- ----------------------------
-- Table structure for `immune`
-- ----------------------------
DROP TABLE IF EXISTS `immune`;
CREATE TABLE `immune` (
  `dbid` int(11) NOT NULL AUTO_INCREMENT,
  `immuneDate` date DEFAULT NULL,
  `immunePerson` varchar(50) DEFAULT NULL,
  `note` varchar(2000) DEFAULT NULL,
  `chickenBatchDbid` int(11) DEFAULT NULL,
  PRIMARY KEY (`dbid`),
  KEY `FK_Reference_7` (`chickenBatchDbid`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`chickenBatchDbid`) REFERENCES `chickenbatch` (`dbid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of immune
-- ----------------------------

-- ----------------------------
-- Table structure for `immunedrag`
-- ----------------------------
DROP TABLE IF EXISTS `immunedrag`;
CREATE TABLE `immunedrag` (
  `dbid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `dose` varchar(50) DEFAULT NULL,
  `healthCareId` int(11) DEFAULT NULL,
  `dragId` int(11) DEFAULT NULL,
  PRIMARY KEY (`dbid`),
  KEY `FK_Reference_16` (`dragId`),
  KEY `FK_Reference_8` (`healthCareId`),
  CONSTRAINT `FK_Reference_16` FOREIGN KEY (`dragId`) REFERENCES `drag` (`dbid`),
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`healthCareId`) REFERENCES `immune` (`dbid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of immunedrag
-- ----------------------------

-- ----------------------------
-- Table structure for `loginlog`
-- ----------------------------
DROP TABLE IF EXISTS `loginlog`;
CREATE TABLE `loginlog` (
  `dbid` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `userName` varchar(50) DEFAULT NULL,
  `loginDate` datetime DEFAULT NULL,
  `ipAddress` varchar(50) DEFAULT NULL,
  `loginAddress` varchar(50) DEFAULT NULL,
  `sessionId` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`dbid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of loginlog
-- ----------------------------
INSERT INTO `loginlog` VALUES ('1', '3', 'admin', '2013-07-11 23:13:14', '192.168.1.102', 'IP地址库文件错误:IP地址库文件错误', '9709B0BB3A06DDD4537F7EBA859546AE');
INSERT INTO `loginlog` VALUES ('2', '3', 'admin', '2013-07-11 23:15:25', '192.168.1.102', '局域网', '7EEA58C6A4A63E7ED186F478815C25B6');
INSERT INTO `loginlog` VALUES ('3', '3', 'admin', '2013-07-11 23:22:43', '192.168.1.102', '局域网', '7B94245A647C6EB56C8B0BB552DE2B61');
INSERT INTO `loginlog` VALUES ('4', '3', 'admin', '2013-07-12 16:20:22', '192.168.99.37', '局域网', '0FED440ECD5ED67916938458C15D981D');
INSERT INTO `loginlog` VALUES ('5', '3', 'admin', '2013-07-12 17:16:16', '192.168.99.37', '局域网', '2E125098B5E8604F90C196C5333F662E');
INSERT INTO `loginlog` VALUES ('6', '3', 'admin', '2013-07-12 17:44:52', '192.168.99.37', '局域网', '6A7C2B107C05ABC25334AA1C476D6BDF');
INSERT INTO `loginlog` VALUES ('7', '3', 'admin', '2013-07-12 17:47:16', '192.168.99.37', '局域网', '743254B8E083B9DB0077677AEA2E48C7');
INSERT INTO `loginlog` VALUES ('8', '3', 'admin', '2013-07-12 17:57:51', '192.168.99.37', '局域网', '37422398E4611E8665E2DDDE6262874D');
INSERT INTO `loginlog` VALUES ('9', '3', 'admin', '2013-07-12 18:09:36', '192.168.99.37', '局域网', 'AB480ED284A13C87EAA4B5EBD2CB1A35');
INSERT INTO `loginlog` VALUES ('10', '3', 'admin', '2013-07-12 18:24:19', '192.168.99.37', '局域网', 'D7423CE002E29382B9FA8FA78008D029');

-- ----------------------------
-- Table structure for `operatelog`
-- ----------------------------
DROP TABLE IF EXISTS `operatelog`;
CREATE TABLE `operatelog` (
  `dbid` int(11) NOT NULL AUTO_INCREMENT,
  `operator` varchar(50) DEFAULT NULL,
  `operatedate` date DEFAULT NULL,
  `operateobj` varchar(200) DEFAULT NULL,
  `operatetype` varchar(200) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `operatefeild` varchar(50) DEFAULT NULL,
  `ipAddress` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`dbid`)
) ENGINE=InnoDB AUTO_INCREMENT=239 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of operatelog
-- ----------------------------
INSERT INTO `operatelog` VALUES ('236', '舒三战', '2013-07-10', '用户管理', '新增', '3', '用户ID【10】', '192.168.1.102');
INSERT INTO `operatelog` VALUES ('237', '舒三战', '2013-07-10', '饲养员管理', '新增', '3', '饲养员ID【7】', '192.168.1.102');
INSERT INTO `operatelog` VALUES ('238', '舒三战', '2013-07-10', '药品管理', '更新', '3', '药品ID【4】', '192.168.1.102');

-- ----------------------------
-- Table structure for `quarantinecertificate`
-- ----------------------------
DROP TABLE IF EXISTS `quarantinecertificate`;
CREATE TABLE `quarantinecertificate` (
  `dbid` int(11) NOT NULL AUTO_INCREMENT,
  `awardGroup` varchar(50) DEFAULT NULL,
  `awardDate` date DEFAULT NULL,
  `certificateImage` varchar(50) DEFAULT NULL,
  `note` varchar(2000) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `chickenBatchDbid` int(11) DEFAULT NULL,
  PRIMARY KEY (`dbid`),
  KEY `FK_Reference_9` (`chickenBatchDbid`),
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`chickenBatchDbid`) REFERENCES `chickenbatch` (`dbid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of quarantinecertificate
-- ----------------------------

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
  `mobilePhone` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `realName` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`dbid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('3', 'admin', '1229393', '19229', 'shusanzhan@163.com', '舒三战', 'b594510740d2ac4261c1b2fe87850d08');
INSERT INTO `user` VALUES ('4', 'shusanzhan', '028-85537134', '028-85537134', 'shusanzhan@163.com', '张三', 'dd38d1980aa84412e042c7a01aa4312b');
INSERT INTO `user` VALUES ('9', '122222', '', '', '', 'admin', '9baa01e2c8796d0ed8341caa8313dcdc');
INSERT INTO `user` VALUES ('10', 'shusanzhan1', '', '', '', 'shusanz', 'daacc04da104d10c413030e1eea732c8');

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
