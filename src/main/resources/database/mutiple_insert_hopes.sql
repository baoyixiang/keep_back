/*
Navicat MySQL Data Transfer

Source Server         : root-local
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : hengyuan

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2020-02-21 23:52:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Procedure structure for MultiInsert_hope
-- ----------------------------
DROP PROCEDURE IF EXISTS `MultiInsert_hope`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `MultiInsert_hope`()
BEGIN
	#Routine body goes here...

	DECLARE i int DEFAULT 0;
	DECLARE n int DEFAULT 10; #循环插入n条hope记录
	
#设置插入记录的默认值#
	DECLARE word_content_0 VARCHAR(30) DEFAULT '这是心愿';
	DECLARE voice_0 VARCHAR(30) DEFAULT 'this is voice';
	DECLARE create_user_id_0 INT DEFAULT 3535;
	DECLARE create_time_0 TIMESTAMP;
	DECLARE is_anonymous_0 TINYINT DEFAULT 1;
	DECLARE is_see_self_0 TINYINT DEFAULT 1;
    DECLARE likeCount_0 TINYINT DEFAULT 0;
    DECLARE CommentCount_0 TINYINT DEFAULT 0;

#循环插入n条hope记录
	WHILE i<n DO 
	SET word_content_0=CONCAT('这是心愿',CONCAT(i,'')); 
	SET create_time_0=CURRENT_TIMESTAMP();
	SET create_user_id_0=create_user_id_0+1;

	IF i/5=0 
	THEN
		SET create_user_id_0=5555;
		SET voice_0='这是5555的声音';
	ELSE
		SET voice_0=CONCAT('voice',i);
	END IF;

	INSERT INTO hope(word_content,voice,create_user_id,create_time,is_anonymous,is_see_self,likeCount,CommentCount)
						VALUES(word_content_0,voice_0,create_user_id_0,create_time_0,is_anonymous_0,is_see_self_0,likeCount_0,CommentCount_0);
	SET i=i+1;
	END WHILE;
END
;;
DELIMITER ;
