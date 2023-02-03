/*
 Navicat Premium Data Transfer

 Source Server         : 华为
 Source Server Type    : MySQL
 Source Server Version : 50740
 Source Host           : 139.9.107.144:3306
 Source Schema         : test01

 Target Server Type    : MySQL
 Target Server Version : 50740
 File Encoding         : 65001

 Date: 29/01/2023 15:59:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for agent
-- ----------------------------
DROP TABLE IF EXISTS `agent`;
CREATE TABLE `agent`  (
  `agentID` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `platformCount` int(11) NULL DEFAULT NULL,
  `difficulty` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`agentID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of agent
-- ----------------------------
INSERT INTO `agent` VALUES ('365xy', 1, 1);

-- ----------------------------
-- Table structure for agents
-- ----------------------------
DROP TABLE IF EXISTS `agents`;
CREATE TABLE `agents`  (
  `agentName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `superior` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `level` int(255) NULL DEFAULT NULL,
  `invitationCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `internaled` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `locked` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`agentName`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of agents
-- ----------------------------
INSERT INTO `agents` VALUES ('111', '123', 'admin', 4, '1111', '是', '是');
INSERT INTO `agents` VALUES ('admin', '1', '3', 3, '3', '1', NULL);
INSERT INTO `agents` VALUES ('ceshi', '111111', 'admin', 4, 'admin', '是', NULL);
INSERT INTO `agents` VALUES ('qq1', 'qqqq', 'admin', 4, 'qqqq', '是', NULL);

-- ----------------------------
-- Table structure for bibeirecord
-- ----------------------------
DROP TABLE IF EXISTS `bibeirecord`;
CREATE TABLE `bibeirecord`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jitaihao` int(11) NULL DEFAULT NULL,
  `dailihao` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `yazhu` int(11) NULL DEFAULT NULL,
  `daxiao` int(11) NULL DEFAULT NULL,
  `kaipai` int(11) NULL DEFAULT NULL,
  `ining` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bibeirecord
-- ----------------------------
INSERT INTO `bibeirecord` VALUES (1, 1, '365xy', 0, 0, 0, 1);
INSERT INTO `bibeirecord` VALUES (2, 1, '365xy', 0, 0, 0, 38);
INSERT INTO `bibeirecord` VALUES (3, 1, '365xy', 0, 0, 0, 38);
INSERT INTO `bibeirecord` VALUES (4, 1, '365xy', 0, 0, 0, 39);
INSERT INTO `bibeirecord` VALUES (5, 1, '365xy', 0, 0, 0, 39);
INSERT INTO `bibeirecord` VALUES (6, 1, '365xy', 0, 0, 0, 39);
INSERT INTO `bibeirecord` VALUES (7, 1, '365xy', 0, 0, 0, 39);
INSERT INTO `bibeirecord` VALUES (8, 1, '365xy', 0, 0, 0, 39);
INSERT INTO `bibeirecord` VALUES (9, 1, '365xy', 0, 0, 0, 40);
INSERT INTO `bibeirecord` VALUES (10, 1, '365xy', 0, 0, 0, 40);
INSERT INTO `bibeirecord` VALUES (11, 1, '365xy', 0, 0, 0, 41);
INSERT INTO `bibeirecord` VALUES (12, 1, '365xy', 0, 0, 0, 41);
INSERT INTO `bibeirecord` VALUES (13, 1, '365xy', 0, 0, 0, 41);
INSERT INTO `bibeirecord` VALUES (14, 1, '365xy', 0, 0, 0, 41);
INSERT INTO `bibeirecord` VALUES (15, 1, '365xy', 0, 0, 0, 41);
INSERT INTO `bibeirecord` VALUES (16, 1, '365xy', 0, 0, 0, 42);
INSERT INTO `bibeirecord` VALUES (17, 1, '365xy', 0, 0, 0, 42);
INSERT INTO `bibeirecord` VALUES (18, 1, '365xy', 0, 0, 0, 44);
INSERT INTO `bibeirecord` VALUES (19, 1, '365xy', 0, 0, 0, 44);
INSERT INTO `bibeirecord` VALUES (20, 1, '365xy', 0, 0, 0, 45);
INSERT INTO `bibeirecord` VALUES (21, 1, '365xy', 0, 0, 0, 45);
INSERT INTO `bibeirecord` VALUES (22, 1, '365xy', 0, 0, 0, 51);
INSERT INTO `bibeirecord` VALUES (23, 1, '365xy', 0, 0, 0, 51);
INSERT INTO `bibeirecord` VALUES (24, 1, '365xy', 0, 0, 0, 52);
INSERT INTO `bibeirecord` VALUES (25, 1, '365xy', 0, 0, 0, 53);
INSERT INTO `bibeirecord` VALUES (26, 1, '365xy', 0, 0, 0, 53);
INSERT INTO `bibeirecord` VALUES (27, 1, '365xy', 0, 0, 0, 53);
INSERT INTO `bibeirecord` VALUES (28, 1, '365xy', 0, 0, 0, 53);
INSERT INTO `bibeirecord` VALUES (29, 1, '365xy', 0, 0, 0, 53);
INSERT INTO `bibeirecord` VALUES (30, 1, '365xy', 0, 0, 0, 53);
INSERT INTO `bibeirecord` VALUES (31, 1, '365xy', 0, 0, 0, 53);
INSERT INTO `bibeirecord` VALUES (32, 1, '365xy', 0, 0, 0, 53);
INSERT INTO `bibeirecord` VALUES (33, 1, '365xy', 0, 0, 0, 54);
INSERT INTO `bibeirecord` VALUES (34, 1, '365xy', 0, 0, 0, 55);
INSERT INTO `bibeirecord` VALUES (35, 1, '365xy', 0, 0, 0, 56);
INSERT INTO `bibeirecord` VALUES (36, 1, '365xy', 0, 0, 0, 56);
INSERT INTO `bibeirecord` VALUES (37, 1, '365xy', 0, 0, 0, 57);
INSERT INTO `bibeirecord` VALUES (38, 1, '365xy', 0, 0, 0, 58);
INSERT INTO `bibeirecord` VALUES (39, 1, '365xy', 0, 0, 0, 59);

-- ----------------------------
-- Table structure for gamedetail
-- ----------------------------
DROP TABLE IF EXISTS `gamedetail`;
CREATE TABLE `gamedetail`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gameName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `numPeople` int(11) NULL DEFAULT NULL,
  `numOnline` int(11) NULL DEFAULT NULL,
  `sumBet` int(11) NULL DEFAULT NULL,
  `sumBonus` int(11) NULL DEFAULT NULL,
  `sumProfit` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for gamejirecord
-- ----------------------------
DROP TABLE IF EXISTS `gamejirecord`;
CREATE TABLE `gamejirecord`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
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
  `baopai5` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 63 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gamejirecord
-- ----------------------------
INSERT INTO `gamejirecord` VALUES (1, 4, 25, 22, 36, 39, 16, 25, 22, 36, 39, 1, '365xy', 10283, 205, 0, 0, 1, 1, 1, 1);
INSERT INTO `gamejirecord` VALUES (2, 6, 48, 19, 14, 35, 6, 48, 19, 26, 35, 1, '365xy', 498, 154, 308, 1, 1, 1, 0, 1);
INSERT INTO `gamejirecord` VALUES (3, 8, 41, 15, 28, 5, 19, 41, 15, 28, 33, 1, '365xy', 6045, 135, 405, 0, 1, 1, 1, 0);
INSERT INTO `gamejirecord` VALUES (4, 9, 51, 17, 24, 3, 46, 34, 15, 13, 32, 1, '365xy', 4425, 135, 0, 0, 0, 0, 0, 0);
INSERT INTO `gamejirecord` VALUES (5, 11, 19, 31, 43, 15, 50, 19, 31, 43, 15, 1, '365xy', 4560, 135, 0, 0, 1, 1, 1, 1);
INSERT INTO `gamejirecord` VALUES (6, 12, 35, 16, 9, 48, 41, 35, 30, 9, 48, 1, '365xy', 6904, 104, 312, 0, 1, 0, 1, 1);
INSERT INTO `gamejirecord` VALUES (7, 16, 47, 40, 3, 34, 16, 47, 38, 3, 34, 1, '365xy', 2038, 154, 308, 1, 1, 0, 1, 1);
INSERT INTO `gamejirecord` VALUES (8, 19, 37, 43, 10, 5, 28, 7, 51, 48, 14, 1, '365xy', 92, 500, 0, 0, 0, 0, 0, 0);
INSERT INTO `gamejirecord` VALUES (9, 21, 8, 37, 34, 16, 21, 8, 51, 34, 30, 1, '365xy', 11513, 205, 615, 1, 1, 0, 1, 0);
INSERT INTO `gamejirecord` VALUES (10, 23, 38, 14, 20, 16, 23, 18, 14, 20, 16, 1, '365xy', 10078, 205, 1230, 1, 0, 1, 1, 1);
INSERT INTO `gamejirecord` VALUES (11, 25, 13, 38, 8, 36, 25, 15, 38, 11, 31, 1, '365xy', 4560, 135, 135, 1, 0, 1, 0, 0);
INSERT INTO `gamejirecord` VALUES (12, 27, 50, 18, 5, 37, 33, 50, 18, 5, 37, 1, '365xy', 2930, 131, 262, 0, 1, 1, 1, 1);
INSERT INTO `gamejirecord` VALUES (13, 28, 14, 37, 5, 43, 28, 14, 10, 5, 43, 1, '365xy', 2176, 104, 0, 1, 1, 0, 1, 1);
INSERT INTO `gamejirecord` VALUES (14, 29, 42, 16, 5, 8, 29, 42, 16, 33, 19, 1, '365xy', 806, 154, 462, 1, 1, 1, 0, 0);
INSERT INTO `gamejirecord` VALUES (15, 31, 11, 15, 43, 50, 36, 11, 19, 38, 50, 1, '365xy', 1884, 154, 154, 0, 1, 0, 0, 1);
INSERT INTO `gamejirecord` VALUES (16, 32, 40, 14, 27, 33, 47, 40, 14, 27, 22, 1, '365xy', 4695, 135, 405, 0, 1, 1, 1, 0);
INSERT INTO `gamejirecord` VALUES (17, 34, 1, 19, 47, 6, 34, 46, 19, 47, 6, 1, '365xy', 652, 154, 308, 1, 0, 1, 1, 1);
INSERT INTO `gamejirecord` VALUES (18, 35, 1, 17, 48, 4, 35, 46, 17, 48, 4, 1, '365xy', 4965, 135, 270, 1, 0, 1, 1, 1);
INSERT INTO `gamejirecord` VALUES (19, 36, 25, 33, 2, 47, 18, 16, 39, 22, 4, 1, '365xy', 3061, 131, 0, 0, 0, 0, 0, 0);
INSERT INTO `gamejirecord` VALUES (20, 37, 1, 17, 50, 4, 37, 46, 17, 50, 4, 1, '365xy', 11308, 205, 410, 1, 0, 1, 1, 1);
INSERT INTO `gamejirecord` VALUES (21, 38, 43, 19, 25, 13, 38, 31, 52, 25, 36, 1, '365xy', 1884, 154, 154, 1, 0, 0, 1, 0);
INSERT INTO `gamejirecord` VALUES (22, 40, 33, 24, 27, 11, 40, 1, 24, 27, 11, 1, '365xy', 13358, 205, 1640, 1, 0, 1, 1, 1);
INSERT INTO `gamejirecord` VALUES (23, 41, 23, 5, 28, 10, 41, 23, 2, 28, 10, 1, '365xy', 11923, 205, 1640, 1, 1, 0, 1, 1);
INSERT INTO `gamejirecord` VALUES (24, 42, 24, 5, 29, 11, 42, 24, 3, 29, 11, 1, '365xy', 2072, 104, 832, 1, 1, 0, 1, 1);
INSERT INTO `gamejirecord` VALUES (25, 16, 47, 34, 3, 28, 16, 47, 34, 3, 52, 1, '365xy', 3171, 105, 210, 1, 1, 1, 1, 0);
INSERT INTO `gamejirecord` VALUES (26, 28, 14, 10, 48, 19, 37, 14, 7, 43, 19, 1, '365xy', 5797, 131, 0, 0, 1, 0, 0, 1);
INSERT INTO `gamejirecord` VALUES (27, 1, 33, 7, 20, 51, 16, 33, 7, 20, 12, 1, '365xy', 5666, 131, 393, 0, 1, 1, 1, 0);
INSERT INTO `gamejirecord` VALUES (28, 17, 47, 40, 4, 34, 17, 47, 38, 4, 34, 1, '365xy', 5928, 131, 262, 1, 1, 0, 1, 1);
INSERT INTO `gamejirecord` VALUES (29, 52, 44, 36, 13, 11, 52, 51, 32, 13, 6, 1, '365xy', 6059, 131, 262, 1, 0, 0, 1, 0);
INSERT INTO `gamejirecord` VALUES (30, 19, 6, 41, 32, 1, 19, 6, 37, 32, 16, 1, '365xy', 6190, 131, 393, 1, 1, 0, 1, 0);
INSERT INTO `gamejirecord` VALUES (31, 27, 48, 17, 4, 35, 33, 48, 17, 4, 35, 1, '365xy', 6452, 131, 262, 0, 1, 1, 1, 1);
INSERT INTO `gamejirecord` VALUES (32, 30, 52, 11, 32, 49, 25, 52, 51, 44, 49, 1, '365xy', 6583, 131, 131, 0, 1, 0, 0, 1);
INSERT INTO `gamejirecord` VALUES (33, 14, 21, 20, 24, 16, 14, 21, 20, 24, 16, 1, '365xy', 6583, 131, 786, 1, 1, 1, 1, 1);
INSERT INTO `gamejirecord` VALUES (34, 41, 33, 25, 28, 12, 41, 2, 25, 28, 12, 1, '365xy', 7238, 131, 1048, 1, 0, 1, 1, 1);
INSERT INTO `gamejirecord` VALUES (35, 13, 11, 50, 15, 38, 52, 11, 50, 19, 36, 1, '365xy', 3969, 135, 135, 0, 1, 1, 0, 0);
INSERT INTO `gamejirecord` VALUES (36, 3, 49, 16, 14, 36, 3, 49, 16, 26, 36, 1, '365xy', 3969, 135, 270, 1, 1, 1, 0, 1);
INSERT INTO `gamejirecord` VALUES (37, 36, 52, 30, 6, 51, 44, 11, 32, 37, 13, 1, '365xy', 4104, 135, 135, 0, 0, 0, 0, 0);
INSERT INTO `gamejirecord` VALUES (38, 28, 51, 10, 7, 37, 43, 14, 19, 5, 48, 1, '365xy', 4016, 500, 0, 0, 0, 0, 0, 0);
INSERT INTO `gamejirecord` VALUES (39, 5, 48, 18, 14, 35, 5, 48, 18, 26, 35, 1, '365xy', 3016, 500, 1000, 1, 1, 1, 0, 1);
INSERT INTO `gamejirecord` VALUES (40, 37, 48, 43, 10, 28, 7, 5, 51, 19, 14, 1, '365xy', 8553, 111, 0, 0, 0, 0, 0, 0);
INSERT INTO `gamejirecord` VALUES (41, 3, 48, 16, 14, 35, 3, 48, 16, 26, 35, 1, '365xy', 11585, 160, 320, 1, 1, 1, 0, 1);
INSERT INTO `gamejirecord` VALUES (42, 19, 10, 51, 28, 43, 14, 5, 48, 37, 7, 1, '365xy', 4813, 203, 0, 0, 0, 0, 0, 0);
INSERT INTO `gamejirecord` VALUES (43, 5, 47, 18, 14, 34, 5, 47, 18, 26, 34, 1, '365xy', 4610, 203, 406, 1, 1, 1, 0, 1);
INSERT INTO `gamejirecord` VALUES (44, 34, 51, 13, 9, 3, 17, 32, 24, 46, 15, 1, '365xy', 6737, 103, 0, 0, 0, 0, 0, 0);
INSERT INTO `gamejirecord` VALUES (45, 30, 43, 17, 5, 47, 30, 43, 17, 33, 49, 1, '365xy', 6634, 103, 309, 1, 1, 1, 0, 0);
INSERT INTO `gamejirecord` VALUES (46, 32, 34, 24, 3, 17, 9, 13, 24, 51, 17, 1, '365xy', 19, 201, 0, 0, 0, 1, 0, 1);
INSERT INTO `gamejirecord` VALUES (47, 43, 24, 5, 30, 11, 43, 24, 4, 30, 11, 1, '365xy', 478, 201, 1608, 1, 1, 0, 1, 1);
INSERT INTO `gamejirecord` VALUES (48, 24, 37, 11, 20, 36, 24, 37, 11, 44, 32, 1, '365xy', 2689, 201, 603, 1, 1, 1, 0, 0);
INSERT INTO `gamejirecord` VALUES (49, 47, 37, 11, 24, 22, 49, 37, 11, 24, 20, 1, '365xy', 3091, 201, 603, 0, 1, 1, 1, 0);
INSERT INTO `gamejirecord` VALUES (50, 52, 40, 48, 43, 49, 52, 40, 48, 43, 49, 1, '365xy', 3493, 201, 1206, 1, 1, 1, 1, 1);
INSERT INTO `gamejirecord` VALUES (51, 22, 4, 13, 12, 3, 11, 4, 13, 12, 3, 1, '365xy', 4498, 201, 1206, 0, 1, 1, 1, 1);
INSERT INTO `gamejirecord` VALUES (52, 32, 30, 25, 13, 51, 49, 37, 25, 36, 51, 1, '365xy', 5704, 201, 402, 0, 0, 1, 0, 1);
INSERT INTO `gamejirecord` VALUES (53, 46, 52, 44, 43, 48, 46, 52, 44, 43, 48, 1, '365xy', 5905, 201, 1206, 1, 1, 1, 1, 1);
INSERT INTO `gamejirecord` VALUES (54, 41, 34, 30, 8, 47, 37, 34, 39, 8, 47, 1, '365xy', 7312, 201, 603, 0, 1, 0, 1, 1);
INSERT INTO `gamejirecord` VALUES (55, 3, 17, 13, 51, 15, 3, 17, 13, 9, 15, 1, '365xy', 0, 170, 0, 1, 1, 1, 0, 1);
INSERT INTO `gamejirecord` VALUES (56, 28, 7, 48, 43, 5, 14, 51, 48, 43, 19, 1, '365xy', 0, 110, 0, 0, 0, 1, 1, 0);
INSERT INTO `gamejirecord` VALUES (57, 40, 25, 5, 27, 12, 40, 25, 1, 27, 12, 1, '365xy', 180, 110, 880, 1, 1, 0, 1, 1);
INSERT INTO `gamejirecord` VALUES (58, 13, 50, 38, 43, 31, 11, 50, 19, 52, 36, 1, '365xy', 0, 200, 200, 0, 1, 0, 0, 0);
INSERT INTO `gamejirecord` VALUES (59, 18, 47, 40, 5, 34, 18, 47, 38, 5, 34, 1, '365xy', 0, 200, 400, 1, 1, 0, 1, 1);
INSERT INTO `gamejirecord` VALUES (60, 3, 12, 11, 13, 8, 3, 12, 11, 13, 8, 1, '365xy', 200, 200, 1200, 1, 1, 1, 1, 1);
INSERT INTO `gamejirecord` VALUES (61, 43, 33, 25, 30, 12, 43, 4, 25, 30, 12, 1, '365xy', 400, 100, 800, 1, 0, 1, 1, 1);
INSERT INTO `gamejirecord` VALUES (62, 32, 13, 37, 30, 52, 6, 13, 25, 51, 52, 1, '365xy', 1500, 100, 200, 0, 1, 0, 0, 1);

-- ----------------------------
-- Table structure for jilu1
-- ----------------------------
DROP TABLE IF EXISTS `jilu1`;
CREATE TABLE `jilu1`  (
  `daili` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
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
  `_4k80` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`daili`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jilu1
-- ----------------------------
INSERT INTO `jilu1` VALUES ('365xy', 1, 1, 31576, 44067, -12491, 0, 0, 0, 0, 0, 0, 0);

-- ----------------------------
-- Table structure for jilu2
-- ----------------------------
DROP TABLE IF EXISTS `jilu2`;
CREATE TABLE `jilu2`  (
  `daili` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
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
  `CaijinCi` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`daili`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jilu2
-- ----------------------------
INSERT INTO `jilu2` VALUES ('365xy', 1, 1, 2747, 37195, 15, 63, 571, 0, 0, 0, 37195, 2747, 735, 490, 0, 0);

-- ----------------------------
-- Table structure for jilu3
-- ----------------------------
DROP TABLE IF EXISTS `jilu3`;
CREATE TABLE `jilu3`  (
  `daili` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
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
  `zong` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`daili`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jilu3
-- ----------------------------
INSERT INTO `jilu3` VALUES ('365xy', 1, 1, 0, 0, 0, 0, 8, 7, 0, 0, 0, 0, 14, 29);

-- ----------------------------
-- Table structure for jitaiset
-- ----------------------------
DROP TABLE IF EXISTS `jitaiset`;
CREATE TABLE `jitaiset`  (
  `agentID` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `platformhao` int(11) NULL DEFAULT NULL,
  `difficulty` int(11) NULL DEFAULT NULL,
  `guiPercent` int(11) NULL DEFAULT NULL,
  `guiIning` int(11) NULL DEFAULT NULL,
  `startining` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`agentID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for liujirecord
-- ----------------------------
DROP TABLE IF EXISTS `liujirecord`;
CREATE TABLE `liujirecord`  (
  `strattime` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `dailihao` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `jitaihao` int(11) NULL DEFAULT NULL,
  `endtime` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `stringStart` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `stringEnd` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tinghao` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `credit` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`strattime`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for lobbys
-- ----------------------------
DROP TABLE IF EXISTS `lobbys`;
CREATE TABLE `lobbys`  (
  `lobbyId` int(11) NOT NULL,
  `lobbyName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `numMachine` int(11) NULL DEFAULT NULL,
  `score` int(11) NULL DEFAULT NULL,
  `minUpScore` int(11) NULL DEFAULT NULL,
  `minDownScore` int(11) NULL DEFAULT NULL,
  `maxBet` int(11) NULL DEFAULT NULL,
  `defaultBet` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`lobbyId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lobbys
-- ----------------------------
INSERT INTO `lobbys` VALUES (1, '2分', 20, 200, 10, 100, 200, 80);

-- ----------------------------
-- Table structure for machine
-- ----------------------------
DROP TABLE IF EXISTS `machine`;
CREATE TABLE `machine`  (
  `lobbyId` int(11) NOT NULL,
  `_5K` int(11) NULL DEFAULT NULL,
  `RS` int(11) NULL DEFAULT NULL,
  `SF` int(11) NULL DEFAULT NULL,
  `_4K` int(11) NULL DEFAULT NULL,
  `numMachine` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`lobbyId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of machine
-- ----------------------------
INSERT INTO `machine` VALUES (1, 1131, 1131, 1131, 131, 1);

-- ----------------------------
-- Table structure for morennandu
-- ----------------------------
DROP TABLE IF EXISTS `morennandu`;
CREATE TABLE `morennandu`  (
  `id` int(11) NOT NULL,
  `nandu` int(11) NULL DEFAULT NULL,
  `difficulty` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of morennandu
-- ----------------------------
INSERT INTO `morennandu` VALUES (1, 1, 1);

-- ----------------------------
-- Table structure for nowgame
-- ----------------------------
DROP TABLE IF EXISTS `nowgame`;
CREATE TABLE `nowgame`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `jitaihao` int(255) NULL DEFAULT NULL,
  `tinghao` int(255) NULL DEFAULT NULL,
  `credit` int(255) NULL DEFAULT NULL,
  `daili` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for paijiining
-- ----------------------------
DROP TABLE IF EXISTS `paijiining`;
CREATE TABLE `paijiining`  (
  `roomName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `jitaihao` int(11) NULL DEFAULT NULL,
  `ining` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`roomName`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of paijiining
-- ----------------------------
INSERT INTO `paijiining` VALUES ('365xy', 1, 59);

-- ----------------------------
-- Table structure for paixingrecord
-- ----------------------------
DROP TABLE IF EXISTS `paixingrecord`;
CREATE TABLE `paixingrecord`  (
  `paixing1` int(11) NOT NULL,
  `paixing2` int(11) NULL DEFAULT NULL,
  `paixing3` int(11) NULL DEFAULT NULL,
  `paixing4` int(11) NULL DEFAULT NULL,
  `paixing5` int(11) NULL DEFAULT NULL,
  `paixing6` int(11) NULL DEFAULT NULL,
  `paixing7` int(11) NULL DEFAULT NULL,
  `jitaihao` int(11) NULL DEFAULT NULL,
  `dailihao` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`paixing1`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of paixingrecord
-- ----------------------------
INSERT INTO `paixingrecord` VALUES (0, 0, 0, 0, 8, 7, 0, 1, '365xy');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `superior` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `invitationCode` int(11) NULL DEFAULT NULL,
  `locked` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `balance` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('aaaa', 'aaaa', '365xy', 1, '是', 10533);

-- ----------------------------
-- Table structure for zuobiset
-- ----------------------------
DROP TABLE IF EXISTS `zuobiset`;
CREATE TABLE `zuobiset`  (
  `agentID` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `platformhao` int(11) NULL DEFAULT NULL,
  `paixing` int(11) NULL DEFAULT NULL,
  `inning` int(11) NULL DEFAULT NULL,
  `curIning` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`agentID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
