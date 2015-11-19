/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-10-26 15:54:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `f_function`
-- ----------------------------
DROP TABLE IF EXISTS `f_function`;
CREATE TABLE `f_function` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `functionname` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of f_function
-- ----------------------------
INSERT INTO `f_function` VALUES ('1', '系统管理');
INSERT INTO `f_function` VALUES ('2', '用户管理');
INSERT INTO `f_function` VALUES ('3', '角色管理');
INSERT INTO `f_function` VALUES ('4', '角色管理');
INSERT INTO `f_function` VALUES ('5', '角色管理');
INSERT INTO `f_function` VALUES ('6', '角色管理');
INSERT INTO `f_function` VALUES ('7', '角色管理');

-- ----------------------------
-- Table structure for `rf_rolefunction`
-- ----------------------------
DROP TABLE IF EXISTS `rf_rolefunction`;
CREATE TABLE `rf_rolefunction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `functionid` int(11) DEFAULT NULL,
  `roleid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fcte0m778hpeai9fj8ej6elp3` (`functionid`),
  KEY `FK_p9xah9wl9em3wwf71owqbho0l` (`roleid`),
  CONSTRAINT `FK_fcte0m778hpeai9fj8ej6elp3` FOREIGN KEY (`functionid`) REFERENCES `f_function` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_p9xah9wl9em3wwf71owqbho0l` FOREIGN KEY (`roleid`) REFERENCES `r_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rf_rolefunction
-- ----------------------------
INSERT INTO `rf_rolefunction` VALUES ('7', '2', '1');
INSERT INTO `rf_rolefunction` VALUES ('9', '1', '1');
INSERT INTO `rf_rolefunction` VALUES ('10', '1', '2');

-- ----------------------------
-- Table structure for `r_role`
-- ----------------------------
DROP TABLE IF EXISTS `r_role`;
CREATE TABLE `r_role` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of r_role
-- ----------------------------
INSERT INTO `r_role` VALUES ('1', 'gy');
INSERT INTO `r_role` VALUES ('2', 'Christy');

-- ----------------------------
-- Table structure for `ur_userrole`
-- ----------------------------
DROP TABLE IF EXISTS `ur_userrole`;
CREATE TABLE `ur_userrole` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `userid` int(10) DEFAULT NULL,
  `roleid` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `USERID` (`userid`),
  KEY `ROLEID` (`roleid`),
  CONSTRAINT `ROLEID` FOREIGN KEY (`roleid`) REFERENCES `r_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `USERID` FOREIGN KEY (`userid`) REFERENCES `u_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ur_userrole
-- ----------------------------
INSERT INTO `ur_userrole` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for `u_user`
-- ----------------------------
DROP TABLE IF EXISTS `u_user`;
CREATE TABLE `u_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_user
-- ----------------------------
INSERT INTO `u_user` VALUES ('1', '123456', '123456');
INSERT INTO `u_user` VALUES ('2', '428', null);
INSERT INTO `u_user` VALUES ('55', '1231', '23424');
INSERT INTO `u_user` VALUES ('56', '1231', '123131');
