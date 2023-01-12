/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : 127.0.0.1:3306
 Source Schema         : test01

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 12/01/2023 17:01:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for agent
-- ----------------------------
DROP TABLE IF EXISTS `agent`;
CREATE TABLE `agent`  (
  `agentID` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `platformCount` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for agents
-- ----------------------------
DROP TABLE IF EXISTS `agents`;
CREATE TABLE `agents`  (
  `agentName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `superior` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `level` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `invitationCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `internal` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for bibeirecord
-- ----------------------------
DROP TABLE IF EXISTS `bibeirecord`;
CREATE TABLE `bibeirecord`  (
  `jitaihao` int(11) NULL DEFAULT NULL,
  `dailihao` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `yazhu` int(11) NULL DEFAULT NULL,
  `daxiao` int(11) NULL DEFAULT NULL,
  `kaipai` int(11) NULL DEFAULT NULL,
  `ining` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for gamejirecord
-- ----------------------------
DROP TABLE IF EXISTS `gamejirecord`;
CREATE TABLE `gamejirecord`  (
  `poker1` int(11) NULL DEFAULT NULL,
  `poker2` int(11) NULL DEFAULT NULL,
  `poker3` int(11) NULL DEFAULT NULL,
  `poker4` int(11) NULL DEFAULT NULL,
  `poker5` int(11) NULL DEFAULT NULL,
  `poker6` int(11) NULL DEFAULT NULL,
  `poker7` int(11) NULL DEFAULT NULL,
  `poker8` int(11) NULL DEFAULT NULL,
  `poker9` int(11) NULL DEFAULT NULL,
  `poker10` int(11) NULL DEFAULT NULL,
  `jitaihao` int(11) NULL DEFAULT NULL,
  `dailihao` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `credit` double(255, 0) NULL DEFAULT NULL,
  `bet` double(255, 0) NULL DEFAULT NULL,
  `win` double(255, 0) NULL DEFAULT NULL,
  `baopai1` int(11) NULL DEFAULT NULL,
  `baopai2` int(11) NULL DEFAULT NULL,
  `baopai3` int(11) NULL DEFAULT NULL,
  `baopai4` int(11) NULL DEFAULT NULL,
  `baopai5` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for jilu1
-- ----------------------------
DROP TABLE IF EXISTS `jilu1`;
CREATE TABLE `jilu1`  (
  `daili` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tinghao` int(11) NULL DEFAULT NULL,
  `jitaihao` int(11) NULL DEFAULT NULL,
  `NOTEAin` int(11) NULL DEFAULT NULL,
  `NOTEAout` int(11) NULL DEFAULT NULL,
  `NOTEAbalance` int(11) NULL DEFAULT NULL,
  `NOTEBin` int(11) NULL DEFAULT NULL,
  `NOTEBout` int(11) NULL DEFAULT NULL,
  `NOTEBbalance` int(11) NULL DEFAULT NULL,
  `_5k80` int(11) NULL DEFAULT NULL,
  `rs80` int(11) NULL DEFAULT NULL,
  `sf80` int(11) NULL DEFAULT NULL,
  `_4k80` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for jilu2
-- ----------------------------
DROP TABLE IF EXISTS `jilu2`;
CREATE TABLE `jilu2`  (
  `daili` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tinghao` int(11) NULL DEFAULT NULL,
  `jitaihao` int(11) NULL DEFAULT NULL,
  `ZhuWanFen` int(11) NULL DEFAULT NULL,
  `ZhuWinFen` int(11) NULL DEFAULT NULL,
  `ZhuWanCi` int(11) NULL DEFAULT NULL,
  `ZhuWinCi` int(11) NULL DEFAULT NULL,
  `BibeiWanFen` int(11) NULL DEFAULT NULL,
  `BibeiWinFen` int(11) NULL DEFAULT NULL,
  `BibeiWinCi` int(11) NULL DEFAULT NULL,
  `BibeiShuCi` int(11) NULL DEFAULT NULL,
  `KaifenZon` int(11) NULL DEFAULT NULL,
  `XifenZon` int(11) NULL DEFAULT NULL,
  `ToubiZon` int(11) NULL DEFAULT NULL,
  `TuibiZon` int(11) NULL DEFAULT NULL,
  `CaijinFen` int(11) NULL DEFAULT NULL,
  `CaijinCi` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for jilu3
-- ----------------------------
DROP TABLE IF EXISTS `jilu3`;
CREATE TABLE `jilu3`  (
  `daili` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tinghao` int(11) NULL DEFAULT NULL,
  `jitaihao` int(11) NULL DEFAULT NULL,
  `P5K` int(11) NULL DEFAULT NULL,
  `rs` int(11) NULL DEFAULT NULL,
  `sf` int(11) NULL DEFAULT NULL,
  `P4k` int(11) NULL DEFAULT NULL,
  `fh` int(11) NULL DEFAULT NULL,
  `fl` int(11) NULL DEFAULT NULL,
  `st` int(11) NULL DEFAULT NULL,
  `P3k` int(11) NULL DEFAULT NULL,
  `P2p` int(11) NULL DEFAULT NULL,
  `P1p` int(11) NULL DEFAULT NULL,
  `no` int(11) NULL DEFAULT NULL,
  `zong` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for jitaiset
-- ----------------------------
DROP TABLE IF EXISTS `jitaiset`;
CREATE TABLE `jitaiset`  (
  `agentID` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `platformhao` int(11) NULL DEFAULT NULL,
  `difficulty` int(11) NULL DEFAULT NULL,
  `guiPercent` int(11) NULL DEFAULT NULL,
  `guiIning` int(11) NULL DEFAULT NULL,
  `startining` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for liujirecord
-- ----------------------------
DROP TABLE IF EXISTS `liujirecord`;
CREATE TABLE `liujirecord`  (
  `strattime` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `dailihao` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `jitaihao` int(11) NULL DEFAULT NULL,
  `endtime` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `stringStart` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `stringEnd` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tinghao` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `credit` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for nowgame
-- ----------------------------
DROP TABLE IF EXISTS `nowgame`;
CREATE TABLE `nowgame`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `jitaihao` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tinghao` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `credit` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `daili` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for paijiining
-- ----------------------------
DROP TABLE IF EXISTS `paijiining`;
CREATE TABLE `paijiining`  (
  `roomName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `jitaihao` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for paixingrecord
-- ----------------------------
DROP TABLE IF EXISTS `paixingrecord`;
CREATE TABLE `paixingrecord`  (
  `paixing1` int(11) NULL DEFAULT NULL,
  `paixing2` int(11) NULL DEFAULT NULL,
  `paixing3` int(11) NULL DEFAULT NULL,
  `paixing4` int(11) NULL DEFAULT NULL,
  `paixing5` int(11) NULL DEFAULT NULL,
  `paixing6` int(11) NULL DEFAULT NULL,
  `paixing7` int(11) NULL DEFAULT NULL,
  `jitaihao` int(11) NULL DEFAULT NULL,
  `dailihao` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `superior` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `invitationCode` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for zuobiset
-- ----------------------------
DROP TABLE IF EXISTS `zuobiset`;
CREATE TABLE `zuobiset`  (
  `agentID` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `platformhao` int(11) NULL DEFAULT NULL,
  `paixing` int(11) NULL DEFAULT NULL,
  `inning` int(11) NULL DEFAULT NULL,
  `curIning` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
