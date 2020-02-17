/*
 Navicat Premium Data Transfer

 Source Server         : localmysql
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : hengyuan

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 18/12/2019 17:18:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for check_in
-- ----------------------------
DROP TABLE IF EXISTS `check_in`;
CREATE TABLE `check_in`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `custome_id` int(11) NOT NULL COMMENT '习惯id',
  `check_in_time` timestamp(0) NOT NULL COMMENT '本次打卡时间',
  `word_content` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '打卡的文字内容',
  `images` json NULL COMMENT '打卡的图片内容的url',
  `voice` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '打卡的语音内容',
  `days` int(255) NOT NULL COMMENT '本次打卡是此用户在此习惯上打卡的第几天',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for check_in_comments
-- ----------------------------
DROP TABLE IF EXISTS `check_in_comments`;
CREATE TABLE `check_in_comments`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `check_in_id` int(11) NOT NULL COMMENT '打卡记录的id',
  `comment_time` timestamp(0) NOT NULL COMMENT '评论时间',
  `comment_content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论内容',
  `reply_to` int(255) NOT NULL COMMENT '回复的用户，如果没有内容，说明回复的是打卡记录而不是用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for custom
-- ----------------------------
DROP TABLE IF EXISTS `custom`;
CREATE TABLE `custom`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '习惯名称',
  `logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'logo的url',
  `create_user_id` int(11) NOT NULL COMMENT '创建习惯的用户的id',
  `create_time` timestamp(0) NOT NULL COMMENT '创建时间',
  `tags` json NULL COMMENT '习惯的标签',
  `is_default` tinyint(1) NOT NULL COMMENT '是否是系统默认的习惯（即管理员创建的习惯）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hope
-- ----------------------------
DROP TABLE IF EXISTS `hope`;
CREATE TABLE `hope`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word_content` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '心愿的文字内容',
  `images` json NULL COMMENT '心愿的图片内容',
  `voice` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '心愿的语音内容',
  `create_user_id` int(11) NOT NULL COMMENT '创建心愿的用户的id',
  `create_time` timestamp(0) NOT NULL COMMENT '创建时间',
  `is_anonymous` tinyint(1) NOT NULL COMMENT '是否匿名，如果匿名，那么发布的时候不展示是谁发布的',
  `is_see_self` tinyint(1) NOT NULL COMMENT '是否仅自己可见，如果仅自己可见，那么只能在我的页面进行查看',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hope_comments
-- ----------------------------
DROP TABLE IF EXISTS `hope_comments`;
CREATE TABLE `hope_comments`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `hope_id` int(11) NOT NULL,
  `comment_time` timestamp(0) NOT NULL COMMENT '评论时间',
  `comment_content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论内容',
  `reply_to` int(255) NOT NULL COMMENT '回复的用户，如果没有内容，说明回复的是打卡记录而不是用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for join_custom
-- ----------------------------
DROP TABLE IF EXISTS `join_custom`;
CREATE TABLE `join_custom`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `custom_id` int(11) NOT NULL COMMENT '习惯id',
  `join_time` timestamp(0) NOT NULL COMMENT '加入时间',
  `is_public` tinyint(1) NOT NULL COMMENT '如果用户不公开，则用户在此习惯中的打卡不会被别人看到',
  `target_days` int(255) NOT NULL COMMENT '目标天数，如果不设置，默认为0，如果为0，则说明没有目标天数',
  `is_completed` tinyint(1) NOT NULL COMMENT '目标天数是否达到',
  `beans_count` int(255) NOT NULL COMMENT '投入的习惯豆',
  `check_days_count` int(255) NOT NULL COMMENT '已经打卡的天数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `wechat_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '微信用户的id',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户头像',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户昵称',
  `personal_signature` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '个性签名',
  `gender` tinyint(1) NOT NULL COMMENT '性别',
  `last_login_time` timestamp(0) NOT NULL COMMENT '最后登录时间',
  `beans_count` int(255) NOT NULL COMMENT '习惯豆的数量',
  `roles` json NOT NULL COMMENT '用户角色，用来鉴权',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_attention
-- ----------------------------
DROP TABLE IF EXISTS `user_attention`;
CREATE TABLE `user_attention`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` int(11) NOT NULL COMMENT '主动关注的用户的id',
  `followed_user_id` int(11) NOT NULL COMMENT '被关注用户的id',
  `follow_time` timestamp(0) NOT NULL COMMENT '关注时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_like_check_in
-- ----------------------------
DROP TABLE IF EXISTS `user_like_check_in`;
CREATE TABLE `user_like_check_in`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `check_in_id` int(11) NOT NULL COMMENT '打卡记录id',
  `like_time` timestamp(0) NOT NULL COMMENT '点赞的时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_like_hope
-- ----------------------------
DROP TABLE IF EXISTS `user_like_hope`;
CREATE TABLE `user_like_hope`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `hope_id` int(11) NOT NULL,
  `like_time` timestamp(0) NOT NULL COMMENT '点赞的时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
