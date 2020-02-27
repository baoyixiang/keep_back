ALTER TABLE `hengyuan`.`check_in_comments` 
MODIFY COLUMN `reply_to` int(255) NULL DEFAULT NULL COMMENT '回复的用户，如果没有内容，说明回复的是打卡记录而不是用户' AFTER `comment_content`;