/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : zjzcn

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2014-03-25 13:44:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_log`
-- ----------------------------
DROP TABLE IF EXISTS `tb_log`;
CREATE TABLE `tb_log` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `logType` int(11) DEFAULT NULL,
  `ip` varchar(20) DEFAULT NULL,
  `create_time` varchar(20) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_log
-- ----------------------------
INSERT INTO `tb_log` VALUES ('6', 'admin', '0', '127.0.0.1', '2014-03-15 22:09:10', '用户登陆', 'username = admin\ncaptcha = 7975\n');
INSERT INTO `tb_log` VALUES ('7', 'admin', '0', '127.0.0.1', '2014-03-15 22:25:01', '用户登陆', 'username = admin\ncaptcha = 8144\n');
INSERT INTO `tb_log` VALUES ('15', null, '1', '127.0.0.1', '2014-03-16 18:04:13', '系统异常', '异常码:20140316180413351');
INSERT INTO `tb_log` VALUES ('16', null, '1', '127.0.0.1', '2014-03-16 18:04:24', '系统异常', '异常码:20140316180424159');
INSERT INTO `tb_log` VALUES ('17', 'admin', '0', '127.0.0.1', '2014-03-16 18:06:34', '用户登陆', 'username = admin\ncaptcha = 6116\n');
INSERT INTO `tb_log` VALUES ('18', null, '0', '127.0.0.1', '2014-03-16 18:08:52', '用户登陆', 'username = admin\ncaptcha = 6119\n');
INSERT INTO `tb_log` VALUES ('19', 'admin', '0', '127.0.0.1', '2014-03-16 18:08:56', '用户登陆', 'username = admin\ncaptcha = 7877\n');
INSERT INTO `tb_log` VALUES ('20', 'admin', '0', '127.0.0.1', '2014-03-16 18:26:50', '用户登陆', 'username = admin\ncaptcha = 8822\n');
INSERT INTO `tb_log` VALUES ('21', null, '0', '127.0.0.1', '2014-03-16 20:26:33', '用户登陆', 'username = admin\ncaptcha = 3216\n');
INSERT INTO `tb_log` VALUES ('22', 'admin', '0', '127.0.0.1', '2014-03-16 20:26:38', '用户登陆', 'username = admin\ncaptcha = 6676\n');
INSERT INTO `tb_log` VALUES ('23', 'admin', '0', '127.0.0.1', '2014-03-16 20:31:16', '用户登陆', 'username = admin\ncaptcha = 7455\n');
INSERT INTO `tb_log` VALUES ('24', 'admin', '1', '127.0.0.1', '2014-03-16 20:33:57', '系统异常', '异常码:20140316203357230');
INSERT INTO `tb_log` VALUES ('25', 'admin', '0', '127.0.0.1', '2014-03-16 20:35:22', '用户登陆', 'username = admin\ncaptcha = 3632\n');
INSERT INTO `tb_log` VALUES ('26', null, '0', '127.0.0.1', '2014-03-16 20:42:22', '用户登陆', 'username = admin\ncaptcha = 1694\n');
INSERT INTO `tb_log` VALUES ('27', 'admin', '0', '127.0.0.1', '2014-03-16 20:42:30', '用户登陆', 'username = admin\ncaptcha = 9272\n');
INSERT INTO `tb_log` VALUES ('28', 'admin', '0', '127.0.0.1', '2014-03-16 20:48:23', '用户登陆', 'username = admin\ncaptcha = 4763\n');
INSERT INTO `tb_log` VALUES ('29', 'admin', '1', '127.0.0.1', '2014-03-16 21:44:38', '系统异常', '异常码:20140316214438238');
INSERT INTO `tb_log` VALUES ('30', 'zjz', '0', '127.0.0.1', '2014-03-16 21:46:10', '用户登陆', 'username = zjz\ncaptcha = 1375\n');
INSERT INTO `tb_log` VALUES ('31', 'admin', '0', '127.0.0.1', '2014-03-16 21:47:57', '用户登陆', 'username = admin\ncaptcha = 3495\n');
INSERT INTO `tb_log` VALUES ('32', 'zjz', '0', '127.0.0.1', '2014-03-16 21:57:24', '用户登陆', 'username = zjz\ncaptcha = 5889\n');
INSERT INTO `tb_log` VALUES ('33', 'zjz', '0', '127.0.0.1', '2014-03-16 21:57:24', '用户登陆', 'username = zjz\ncaptcha = 5889\n');
INSERT INTO `tb_log` VALUES ('34', 'admin', '0', '127.0.0.1', '2014-03-16 21:57:34', '用户登陆', 'username = admin\ncaptcha = 3619\n');
INSERT INTO `tb_log` VALUES ('35', 'zjz', '0', '127.0.0.1', '2014-03-16 21:59:17', '用户登陆', 'username = zjz\ncaptcha = 6545\n');
INSERT INTO `tb_log` VALUES ('36', 'zjz', '0', '127.0.0.1', '2014-03-16 21:59:17', '用户登陆', 'username = zjz\ncaptcha = 6545\n');
INSERT INTO `tb_log` VALUES ('37', 'admin', '0', '127.0.0.1', '2014-03-17 11:00:44', '用户登陆', 'username = admin\ncaptcha = 7151\n');
INSERT INTO `tb_log` VALUES ('38', 'zjz', '0', '127.0.0.1', '2014-03-17 11:01:24', '用户登陆', 'username = zjz\ncaptcha = 1445\n');
INSERT INTO `tb_log` VALUES ('39', 'zjz', '0', '127.0.0.1', '2014-03-17 11:01:24', '用户登陆', 'username = zjz\ncaptcha = 1445\n');
INSERT INTO `tb_log` VALUES ('40', 'zjz', '0', '127.0.0.1', '2014-03-17 11:04:02', '用户登陆', 'username = zjz\ncaptcha = 4284\n');
INSERT INTO `tb_log` VALUES ('41', 'zjz', '0', '127.0.0.1', '2014-03-17 11:04:02', '用户登陆', 'username = zjz\ncaptcha = 4284\n');
INSERT INTO `tb_log` VALUES ('42', 'admin', '0', '127.0.0.1', '2014-03-17 12:20:02', '用户登陆', 'username = admin\ncaptcha = 3674\n');
INSERT INTO `tb_log` VALUES ('43', 'admin', '1', '127.0.0.1', '2014-03-17 12:20:02', '系统异常', '异常码:20140317122002339');
INSERT INTO `tb_log` VALUES ('44', 'admin', '0', '127.0.0.1', '2014-03-17 12:21:57', '用户登陆', 'username = admin\ncaptcha = 2236\n');
INSERT INTO `tb_log` VALUES ('45', 'admin', '0', '127.0.0.1', '2014-03-17 14:02:33', '用户登陆', 'username = admin\ncaptcha = 7495\n');
INSERT INTO `tb_log` VALUES ('46', 'admin', '0', '127.0.0.1', '2014-03-17 14:03:28', '角色-保存', 'ids = \nroleName = 1234\nisSuper = 0\nremark = \n');
INSERT INTO `tb_log` VALUES ('48', 'admin', '0', '127.0.0.1', '2014-03-17 16:17:15', '用户登陆', 'username = admin\ncaptcha = 4188\n');
INSERT INTO `tb_log` VALUES ('49', 'admin', '0', '127.0.0.1', '2014-03-17 16:20:18', '用户-保存', 'id = \nusername = 1234\nname = 1234\nemail = 1234@1221.com\ngender = 0\nisDisabled = 0\nremark = \n');
INSERT INTO `tb_log` VALUES ('50', 'admin', '0', '127.0.0.1', '2014-03-17 16:25:20', '用户-保存', 'id = \nusername = 123222\nname = 122222\nemail = 123@12.22\ngender = 0\nisDisabled = 0\nremark = \n');
INSERT INTO `tb_log` VALUES ('51', 'admin', '0', '127.0.0.1', '2014-03-17 16:25:57', '用户-保存', 'id = \nusername = 234q\nname = qwe\nemail = qwe@12.23\ngender = 0\nisDisabled = 0\nremark = \n');
INSERT INTO `tb_log` VALUES ('52', 'admin', '0', '127.0.0.1', '2014-03-17 16:27:46', '角色-保存', 'ids = \nroleName = qwe\nisSuper = 0\nremark = \n');
INSERT INTO `tb_log` VALUES ('53', 'admin', '0', '127.0.0.1', '2014-03-17 16:28:48', '用户-保存', 'id = \nusername = qwedf\nname = qwe\nemail = qwer@123.dd\ngender = \nisDisabled = 0\nremark = \n');
INSERT INTO `tb_log` VALUES ('54', 'admin', '0', '127.0.0.1', '2014-03-17 16:30:22', '用户-保存', 'id = \nusername = 123we\nname = 1234\nemail = wer@22.222\ngender = \nisDisabled = 0\nremark = \n');
INSERT INTO `tb_log` VALUES ('55', 'admin', '0', '127.0.0.1', '2014-03-17 16:30:46', '用户-保存', 'id = \nusername = 2345\nname = 234\nemail = 2345@123.345\ngender = 0\nisDisabled = 0\nremark = \n');
INSERT INTO `tb_log` VALUES ('56', 'admin', '0', '127.0.0.1', '2014-03-17 16:37:32', '用户登陆', 'username = admin\ncaptcha = 5214\n');
INSERT INTO `tb_log` VALUES ('57', 'admin', '0', '127.0.0.1', '2014-03-17 16:43:04', '用户-更新', 'id = 2\nname = zjz\nemail = 123@12.2\ngender = \nisDisabled = 0\nremark = \n');
INSERT INTO `tb_log` VALUES ('58', 'admin', '0', '127.0.0.1', '2014-03-17 16:43:15', '用户-更新', 'id = 5\nname = qwe\nemail = qwe@12.23\ngender = 0\nisDisabled = 0\nremark = \n');
INSERT INTO `tb_log` VALUES ('59', null, '0', '127.0.0.1', '2014-03-17 21:50:18', '用户登陆', 'username = admin\ncaptcha = 8636\n');
INSERT INTO `tb_log` VALUES ('60', 'admin', '0', '127.0.0.1', '2014-03-17 21:50:40', '用户登陆', 'username = admin\ncaptcha = 4387\n');
INSERT INTO `tb_log` VALUES ('61', 'admin', '0', '127.0.0.1', '2014-03-17 22:03:48', '用户-更新', 'id = 5\nname = qwe\nemail = qwe@12.23\ngender = 0\nisDisabled = 1\nremark = \n');
INSERT INTO `tb_log` VALUES ('62', 'admin', '0', '127.0.0.1', '2014-03-18 09:04:41', '用户登陆', 'username = admin\ncaptcha = 2628\n');
INSERT INTO `tb_log` VALUES ('63', 'admin', '0', '127.0.0.1', '2014-03-18 09:07:19', '角色-保存', 'ids = 9957D7CEAF4A4729AF53B32070142424,\nroleName = 123\nisSuper = 0\nremark = 234\n');
INSERT INTO `tb_log` VALUES ('64', 'admin', '0', '127.0.0.1', '2014-03-18 09:15:53', '用户登陆', 'username = admin\ncaptcha = 7915\n');
INSERT INTO `tb_log` VALUES ('65', 'admin', '0', '127.0.0.1', '2014-03-18 09:23:20', '角色-保存', 'urls = 3C6EAC53938D4BB4ADC4D3BA41222BC0,\nname = 1234\nisSuper = 0\nremark = 1234\n');
INSERT INTO `tb_log` VALUES ('66', 'admin', '0', '127.0.0.1', '2014-03-18 10:08:03', '用户登陆', 'username = admin\ncaptcha = 9263\n');
INSERT INTO `tb_log` VALUES ('67', 'admin', '0', '127.0.0.1', '2014-03-18 10:12:43', '用户-更新', 'id = 1\nname = admin\nemail = zjzcn@126.com\ngender = \nisDisabled = 0\nremark = 1234\n');
INSERT INTO `tb_log` VALUES ('68', 'admin', '0', '127.0.0.1', '2014-03-18 10:12:52', '用户-更新', 'id = 5\nname = qwe\nemail = qwe@12.23\ngender = 0\nisDisabled = 0\nremark = \n');
INSERT INTO `tb_log` VALUES ('69', 'admin', '0', '127.0.0.1', '2014-03-18 13:02:19', '用户登陆', 'username = admin\ncaptcha = 4263\n');
INSERT INTO `tb_log` VALUES ('71', 'admin', '0', '127.0.0.1', '2014-03-19 14:08:54', '用户登陆', 'username = admin\ncaptcha = 6857\n');
INSERT INTO `tb_log` VALUES ('72', null, '0', '127.0.0.1', '2014-03-19 17:23:43', '用户登陆', 'username = admin\ncaptcha = 4358\n');
INSERT INTO `tb_log` VALUES ('73', 'admin', '0', '127.0.0.1', '2014-03-19 17:23:49', '用户登陆', 'username = admin\ncaptcha = 4652\n');
INSERT INTO `tb_log` VALUES ('74', null, '0', '127.0.0.1', '2014-03-19 18:53:37', '用户登陆', 'username = admin\ncaptcha = 6883\n');
INSERT INTO `tb_log` VALUES ('75', 'admin', '0', '127.0.0.1', '2014-03-19 18:53:42', '用户登陆', 'username = admin\ncaptcha = 3765\n');
INSERT INTO `tb_log` VALUES ('76', 'admin', '0', '127.0.0.1', '2014-03-20 15:02:13', '用户登陆', 'username = admin\ncaptcha = 3562\n');
INSERT INTO `tb_log` VALUES ('77', null, '0', '127.0.0.1', '2014-03-20 20:15:55', '用户登陆', 'username = admin\ncaptcha = 9944\n');
INSERT INTO `tb_log` VALUES ('78', 'admin', '0', '127.0.0.1', '2014-03-20 20:16:00', '用户登陆', 'username = admin\ncaptcha = 5141\n');
INSERT INTO `tb_log` VALUES ('79', 'admin', '0', '127.0.0.1', '2014-03-21 08:56:48', '用户登陆', 'username = admin\ncaptcha = 9891\n');
INSERT INTO `tb_log` VALUES ('80', 'admin', '0', '127.0.0.1', '2014-03-21 11:10:52', '用户登陆', 'username = admin\ncaptcha = 3668\n');
INSERT INTO `tb_log` VALUES ('82', 'admin', '0', '127.0.0.1', '2014-03-21 11:22:03', '用户登陆', 'username = admin\ncaptcha = 5995\n');
INSERT INTO `tb_log` VALUES ('83', 'admin', '0', '127.0.0.1', '2014-03-21 11:53:53', '用户登陆', 'username = admin\ncaptcha = 2895\n');
INSERT INTO `tb_log` VALUES ('84', 'admin', '0', '127.0.0.1', '2014-03-21 12:11:21', '用户-保存', 'id = \nusername = 12342\nname = 12342\nemail = 123@123.3\ngender = \nisDisabled = 0\nremark = \n');
INSERT INTO `tb_log` VALUES ('85', 'admin', '0', '127.0.0.1', '2014-03-21 12:40:00', '用户-更新', 'id = 1\nname = admin\nemail = zjzcn@126.com\ngender = 0\nisDisabled = 0\nremark = 1234\n');
INSERT INTO `tb_log` VALUES ('86', 'admin', '0', '127.0.0.1', '2014-03-21 12:49:31', '用户登陆', 'username = admin\ncaptcha = 5765\n');
INSERT INTO `tb_log` VALUES ('87', 'admin', '0', '127.0.0.1', '2014-03-21 13:39:37', '用户登陆', 'username = admin\ncaptcha = 5191\n');
INSERT INTO `tb_log` VALUES ('88', 'admin', '0', '127.0.0.1', '2014-03-21 13:40:49', '角色-保存', 'urls = \nname = 12345\nremark = 234\nisSuper = 0\n');
INSERT INTO `tb_log` VALUES ('89', 'admin', '0', '127.0.0.1', '2014-03-21 14:56:25', '用户登陆', 'username = admin\ncaptcha = 9282\n');
INSERT INTO `tb_log` VALUES ('90', 'admin', '0', '127.0.0.1', '2014-03-24 21:14:27', '用户登陆', 'username = admin\ncaptcha = 5243\n');

-- ----------------------------
-- Table structure for `tb_role`
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `is_super` int(11) DEFAULT NULL,
  `remark` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1', '超级管理员', '1', '超级管理员111');
INSERT INTO `tb_role` VALUES ('2', '2345', '0', '');
INSERT INTO `tb_role` VALUES ('3', null, '0', null);
INSERT INTO `tb_role` VALUES ('4', null, '0', null);
INSERT INTO `tb_role` VALUES ('5', '12345', '0', '');

-- ----------------------------
-- Table structure for `tb_role_perm`
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_perm`;
CREATE TABLE `tb_role_perm` (
  `role_id` int(12) DEFAULT NULL,
  `perm_url` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role_perm
-- ----------------------------
INSERT INTO `tb_role_perm` VALUES ('1', 'role_list');
INSERT INTO `tb_role_perm` VALUES ('2', 'role_list');
INSERT INTO `tb_role_perm` VALUES ('2', 'user_list');
INSERT INTO `tb_role_perm` VALUES ('5', 'self_doChangePwd');
INSERT INTO `tb_role_perm` VALUES ('5', 'user_save');
INSERT INTO `tb_role_perm` VALUES ('5', 'user_add');
INSERT INTO `tb_role_perm` VALUES ('5', 'role_update');
INSERT INTO `tb_role_perm` VALUES ('5', 'user_doAllocRole');
INSERT INTO `tb_role_perm` VALUES ('5', 'role_save');
INSERT INTO `tb_role_perm` VALUES ('5', 'role_list');
INSERT INTO `tb_role_perm` VALUES ('5', 'user_allocRole');
INSERT INTO `tb_role_perm` VALUES ('5', 'self_doChangeInfo');
INSERT INTO `tb_role_perm` VALUES ('5', 'user_list');
INSERT INTO `tb_role_perm` VALUES ('5', 'user_exist');
INSERT INTO `tb_role_perm` VALUES ('5', 'role_edit');
INSERT INTO `tb_role_perm` VALUES ('5', 'role_add');
INSERT INTO `tb_role_perm` VALUES ('5', 'user_delete');
INSERT INTO `tb_role_perm` VALUES ('5', 'log_list');
INSERT INTO `tb_role_perm` VALUES ('5', 'user_update');
INSERT INTO `tb_role_perm` VALUES ('5', 'user_resetPwd');
INSERT INTO `tb_role_perm` VALUES ('5', 'log_delete');
INSERT INTO `tb_role_perm` VALUES ('5', 'user_edit');
INSERT INTO `tb_role_perm` VALUES ('5', 'self_changePwd');
INSERT INTO `tb_role_perm` VALUES ('5', 'role_delete');
INSERT INTO `tb_role_perm` VALUES ('5', 'self_changeInfo');

-- ----------------------------
-- Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `create_time` varchar(20) DEFAULT NULL,
  `is_disabled` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'admin', 'admin', '96E79218965EB72C92A549DD5A330112', '0', 'zjzcn@126.com', null, null, '0');
INSERT INTO `tb_user` VALUES ('2', 'zjz', 'zjz', '96E79218965EB72C92A549DD5A330112', null, '123@12.2', null, null, '0');
INSERT INTO `tb_user` VALUES ('3', '1234', '1234', 'E10ADC3949BA59ABBE56E057F20F883E', '0', '1234@1221.com', null, null, '0');
INSERT INTO `tb_user` VALUES ('5', 'qwe', '234q', 'E10ADC3949BA59ABBE56E057F20F883E', '0', 'qwe@12.23', null, null, '0');
INSERT INTO `tb_user` VALUES ('6', '12342', '12342', 'E10ADC3949BA59ABBE56E057F20F883E', null, '123@123.3', null, '2014-03-21 12:11:20', '0');

-- ----------------------------
-- Table structure for `tb_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `user_id` int(12) DEFAULT NULL,
  `role_id` int(12) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES ('1', '1');
INSERT INTO `tb_user_role` VALUES ('2', '2');
