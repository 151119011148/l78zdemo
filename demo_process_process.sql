/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : 时序图2.puml

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2018-06-21 15:27:28
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `demo_process_process_config`
-- ----------------------------
DROP TABLE IF EXISTS `demo_process_process_config`;
CREATE TABLE `demo_process_process_config` (
  `PROCESS_ID` int(4) NOT NULL COMMENT '流程ID',
  `SYSTEM_ID` int(4) DEFAULT NULL COMMENT '系统ID',
  `PROCESS_DESC` varchar(56) DEFAULT NULL COMMENT '描述',
  `STEP_COUNT` tinyint(2) DEFAULT NULL COMMENT '流程执行次数',
  `OUT_TO_IN_TRANSLATE` varchar(128) DEFAULT NULL,
  `IN_TO_OUT_TRANSLATE` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`PROCESS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of demo_process_process_config
-- ----------------------------
INSERT INTO `demo_process_process_config` VALUES ('1', '201', '核心支付受理流程', '5', '{ \"A\": \"A\", \"B\": \"B\", \"C\": \"C\", \"D\": \"D\" }\r\n    \"C\": \"C\",\r\n    \"D\": \"D\"\r\n}', '{ \"A\": \"A\", \"B\": \"B\", \"C\": \"C\", \"D\": \"D\" }');

-- ----------------------------
-- Table structure for `demo_process_process_step_config`
-- ----------------------------
DROP TABLE IF EXISTS `demo_process_process_step_config`;
CREATE TABLE `demo_process_process_step_config` (
  `STEP_ID` int(5) NOT NULL COMMENT '步骤ID',
  `PROCESS_ID` int(4) DEFAULT NULL COMMENT '流程ID',
  `STEP_INDEX` tinyint(2) DEFAULT NULL COMMENT '步骤执行标识',
  `RESULT_TO_INDEX` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '执行结果描述',
  `MAX_EXECUTE_TIME` int(4) DEFAULT NULL COMMENT '最大执行时间',
  PRIMARY KEY (`STEP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of demo_process_process_step_config
-- ----------------------------
INSERT INTO `demo_process_process_step_config` VALUES ('1', '1', '1', '{\"suc\":2,\"fail\":0}', '50');
INSERT INTO `demo_process_process_step_config` VALUES ('2', '1', '4', '{\"suc\":0,\"fail\":0}', '100');
INSERT INTO `demo_process_process_step_config` VALUES ('3', '1', '2', '{\"suc\":3,\"fail\":4}', '200');
INSERT INTO `demo_process_process_step_config` VALUES ('4', '1', '3', '{\"suc\":4,\"fail\":4}', '300');
INSERT INTO `demo_process_process_step_config` VALUES ('5', '1', '0', '{\"suc\":1,\"fail\":0}', '50');

-- ----------------------------
-- Table structure for `demo_process_step_config`
-- ----------------------------
DROP TABLE IF EXISTS `demo_process_step_config`;
CREATE TABLE `demo_process_step_config` (
  `STEP_ID` int(5) DEFAULT NULL COMMENT '步骤ID',
  `STEP_DESCRIPTION` varchar(32) DEFAULT NULL COMMENT '步骤描述',
  `RESULT_DESCRIPTION` varchar(64) DEFAULT NULL COMMENT '结果描述',
  `HANDLE_CLASS` varchar(128) DEFAULT NULL COMMENT '执行类'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of demo_process_step_config
-- ----------------------------
INSERT INTO `demo_process_step_config` VALUES ('1', '初始化核心通道流水', null, 'com.processexample.process.step.Step1');
INSERT INTO `demo_process_step_config` VALUES ('2', '更新核心通道流水', null, 'com.processexample.process.step.Step2');
INSERT INTO `demo_process_step_config` VALUES ('3', '支付路由选择', null, 'com.processexample.process.step.Step3');
INSERT INTO `demo_process_step_config` VALUES ('4', '通道统一交易步骤', null, 'com.processexample.process.step.Step4');
INSERT INTO `demo_process_step_config` VALUES ('5', '通道接入权限校验', null, 'com.processexample.process.step.Step5');
