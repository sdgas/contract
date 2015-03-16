/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : contract

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2015-03-13 11:07:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for attachment
-- ----------------------------
DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment` (
  `id` varchar(255) NOT NULL,
  `attachmentName` varchar(255) DEFAULT NULL,
  `contract` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of attachment
-- ----------------------------
INSERT INTO `attachment` VALUES ('201503111061', '20150311143024合同会签表-2014-12-22(4).DOC', '999');
INSERT INTO `attachment` VALUES ('201503111151', '20150311151815入党积极分子考察登记表.doc', '999');
INSERT INTO `attachment` VALUES ('201503111579', '20150311143024入党积极分子考察登记表.doc', '999');
INSERT INTO `attachment` VALUES ('201503112303', '20150311142736fr_pic_khfwzx.jpg', '33');
INSERT INTO `attachment` VALUES ('201503113582', '201503111427352015RK.pdf', '33');
INSERT INTO `attachment` VALUES ('201503114543', '20150311143024合同管理流程-附件5.pdf', '999');
INSERT INTO `attachment` VALUES ('201503116160', '20150311142736入党积极分子考察登记表.doc', '33');
INSERT INTO `attachment` VALUES ('201503117754', '20150311143023fr_pic_khfwzx.jpg', '999');
INSERT INTO `attachment` VALUES ('201503118690', '20150311142736合同会签表-2014-12-22(4).DOC', '33');
INSERT INTO `attachment` VALUES ('201503119061', '20150311142736合同管理流程-附件5.pdf', '33');
INSERT INTO `attachment` VALUES ('201503121216', '20150312112626合同管理流程-附件5.pdf', '222222222');
INSERT INTO `attachment` VALUES ('201503121830', '201503121126252015RK.pdf', '222222222');
INSERT INTO `attachment` VALUES ('201503122490', '20150312112626入党积极分子考察登记表.doc', '222222222');
INSERT INTO `attachment` VALUES ('201503125096', '20150312112626合同会签表-2014-12-22(4).DOC', '222222222');
INSERT INTO `attachment` VALUES ('201503129228', '20150312112626fr_pic_khfwzx.jpg', '222222222');

-- ----------------------------
-- Table structure for contract
-- ----------------------------
DROP TABLE IF EXISTS `contract`;
CREATE TABLE `contract` (
  `contractId` varchar(255) NOT NULL,
  `attachment` varchar(100) DEFAULT NULL,
  `budget` int(11) DEFAULT NULL,
  `budgetMoney` double NOT NULL,
  `contractBeginDate` date NOT NULL,
  `contractCloseDate` time DEFAULT NULL,
  `contractEndDate` date NOT NULL,
  `contractFilingDate` time DEFAULT NULL,
  `contractMoney` double NOT NULL,
  `contractOperator` varchar(10) NOT NULL,
  `contractProperty` int(11) DEFAULT NULL,
  `contractSignCompany` varchar(30) DEFAULT NULL,
  `count` int(11) NOT NULL,
  `mainContent` varchar(300) NOT NULL,
  `remark` varchar(150) DEFAULT NULL,
  `signContractDate` time NOT NULL,
  `type` int(11) DEFAULT NULL,
  `department` int(11) DEFAULT NULL,
  `performanceBond` double NOT NULL,
  `contractName` int(11) DEFAULT NULL,
  `budgetType` varchar(15) DEFAULT NULL,
  `biddingType` int(11) DEFAULT NULL,
  PRIMARY KEY (`contractId`),
  KEY `FKE20F7532DB5FFB39` (`department`),
  KEY `FKE20F75329FD96DFB` (`type`),
  KEY `FKE20F75324C157CAF` (`contractName`),
  CONSTRAINT `FKE20F75324C157CAF` FOREIGN KEY (`contractName`) REFERENCES `contractname` (`id`),
  CONSTRAINT `FKE20F75329FD96DFB` FOREIGN KEY (`type`) REFERENCES `contracttype` (`id`),
  CONSTRAINT `FKE20F7532DB5FFB39` FOREIGN KEY (`department`) REFERENCES `department` (`departmentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contract
-- ----------------------------
INSERT INTO `contract` VALUES ('PC15-STD-0001', null, '0', '15000', '2015-03-19', null, '2015-03-19', null, '5000', '何斌', null, '佛山市赛维思信息技术有限公司', '2', '一台办公计算机', '固定资产采购', '16:35:55', '7', '1', '0', '6', null, null);
INSERT INTO `contract` VALUES ('PC15-STD-0002', null, null, '0', '2015-03-12', null, '2015-03-12', null, '2', '何斌', null, '佛山市顺德区', '2', '2', '2', '14:19:26', '7', '1', '0', '6', null, null);

-- ----------------------------
-- Table structure for contractname
-- ----------------------------
DROP TABLE IF EXISTS `contractname`;
CREATE TABLE `contractname` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contractName` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contractname
-- ----------------------------
INSERT INTO `contractname` VALUES ('1', '三方移交补充协议');
INSERT INTO `contractname` VALUES ('2', '三方移交');
INSERT INTO `contractname` VALUES ('3', '补充协议');
INSERT INTO `contractname` VALUES ('4', '供气合同');
INSERT INTO `contractname` VALUES ('5', '工程合同');
INSERT INTO `contractname` VALUES ('6', '采购合同');
INSERT INTO `contractname` VALUES ('7', '劳务合同');
INSERT INTO `contractname` VALUES ('8', '培训合同');
INSERT INTO `contractname` VALUES ('9', '租赁合同');
INSERT INTO `contractname` VALUES ('10', '报装合同');
INSERT INTO `contractname` VALUES ('11', '融资合同');
INSERT INTO `contractname` VALUES ('12', '工程服务合同');
INSERT INTO `contractname` VALUES ('13', '其他合同');

-- ----------------------------
-- Table structure for contracttype
-- ----------------------------
DROP TABLE IF EXISTS `contracttype`;
CREATE TABLE `contracttype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contractType` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contracttype
-- ----------------------------
INSERT INTO `contracttype` VALUES ('1', '工业');
INSERT INTO `contracttype` VALUES ('2', '商业');
INSERT INTO `contracttype` VALUES ('3', '公福');
INSERT INTO `contracttype` VALUES ('4', '权责');
INSERT INTO `contracttype` VALUES ('5', '综合');
INSERT INTO `contracttype` VALUES ('6', '销售');
INSERT INTO `contracttype` VALUES ('7', '采购');
INSERT INTO `contracttype` VALUES ('8', '服务');
INSERT INTO `contracttype` VALUES ('9', '安装维修');
INSERT INTO `contracttype` VALUES ('10', '工程');
INSERT INTO `contracttype` VALUES ('11', '其他');
INSERT INTO `contracttype` VALUES ('12', '民用');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `departmentId` int(11) NOT NULL AUTO_INCREMENT,
  `departmentName` varchar(15) NOT NULL,
  PRIMARY KEY (`departmentId`),
  UNIQUE KEY `departmentName` (`departmentName`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '安全技术部');
INSERT INTO `department` VALUES ('9', '客户服务部');
INSERT INTO `department` VALUES ('4', '工程管理部');
INSERT INTO `department` VALUES ('3', '市场经营部');
INSERT INTO `department` VALUES ('7', '招标采购部');
INSERT INTO `department` VALUES ('5', '生产运行部');
INSERT INTO `department` VALUES ('6', '综合管理部');
INSERT INTO `department` VALUES ('8', '设计室');
INSERT INTO `department` VALUES ('2', '财务管理部');

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `link` varchar(255) DEFAULT NULL,
  `newsState` int(11) DEFAULT NULL,
  `sendTime` datetime DEFAULT NULL,
  `receiver` varchar(255) NOT NULL,
  `sender` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK24FEF3C2FECE55` (`sender`),
  KEY `FK24FEF3C8CA9E0F` (`receiver`),
  CONSTRAINT `FK24FEF3C2FECE55` FOREIGN KEY (`sender`) REFERENCES `user` (`userId`),
  CONSTRAINT `FK24FEF3C8CA9E0F` FOREIGN KEY (`receiver`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news
-- ----------------------------

-- ----------------------------
-- Table structure for position
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position` (
  `positionId` int(11) NOT NULL AUTO_INCREMENT,
  `positionName` varchar(15) NOT NULL,
  PRIMARY KEY (`positionId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of position
-- ----------------------------
INSERT INTO `position` VALUES ('1', '总经理');
INSERT INTO `position` VALUES ('2', '副总经理');
INSERT INTO `position` VALUES ('3', '部门经理');
INSERT INTO `position` VALUES ('4', '部门副经理');
INSERT INTO `position` VALUES ('5', '部门经理助理');
INSERT INTO `position` VALUES ('6', '职能线负责人');
INSERT INTO `position` VALUES ('7', '员工');
INSERT INTO `position` VALUES ('8', '管理员');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` varchar(255) NOT NULL,
  `pwd` varchar(16) NOT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `userName` varchar(15) NOT NULL,
  `department` int(11) DEFAULT NULL,
  `position` int(11) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  KEY `FK285FEBDB5FFB39` (`department`),
  KEY `FK285FEB9D76C827` (`position`),
  CONSTRAINT `FK285FEB9D76C827` FOREIGN KEY (`position`) REFERENCES `position` (`positionId`),
  CONSTRAINT `FK285FEBDB5FFB39` FOREIGN KEY (`department`) REFERENCES `department` (`departmentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('admin', '0000', '', '管理员', '1', '8');
