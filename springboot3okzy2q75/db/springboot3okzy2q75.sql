-- MySQL dump 10.13  Distrib 5.7.31, for Linux (x86_64)
--
-- Host: localhost    Database: springboot3okzy2q75
-- ------------------------------------------------------
-- Server version	5.7.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `springboot3okzy2q75`
--

/*!40000 DROP DATABASE IF EXISTS `springboot3okzy2q75`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `springboot3okzy2q75` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `springboot3okzy2q75`;

--
-- Table structure for table `chat`
--

DROP TABLE IF EXISTS `chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `adminid` bigint(20) DEFAULT NULL COMMENT '管理员id',
  `ask` longtext COLLATE utf8mb4_unicode_ci COMMENT '提问',
  `reply` longtext COLLATE utf8mb4_unicode_ci COMMENT '回复',
  `isreply` int(11) DEFAULT NULL COMMENT '是否回复',
  `isread` int(11) DEFAULT '0' COMMENT '已读/未读(1:已读,0:未读)',
  `uname` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `uimage` longtext COLLATE utf8mb4_unicode_ci COMMENT '用户头像',
  `type` int(11) DEFAULT '1' COMMENT '内容类型(1:文本,2:图片,3:视频,4:文件,5:表情)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='智能客服';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat`
--

LOCK TABLES `chat` WRITE;
/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
INSERT INTO `chat` VALUES (1,'2026-04-11 06:38:29',1,1,'提问1','回复1',1,1,'用户名1','upload/chat_uimage1.jpg,upload/chat_uimage2.jpg,upload/chat_uimage3.jpg',1),(2,'2026-04-11 06:38:29',2,2,'提问2','回复2',2,2,'用户名2','upload/chat_uimage2.jpg,upload/chat_uimage3.jpg,upload/chat_uimage4.jpg',2),(3,'2026-04-11 06:38:29',3,3,'提问3','回复3',3,3,'用户名3','upload/chat_uimage3.jpg,upload/chat_uimage4.jpg,upload/chat_uimage5.jpg',3),(4,'2026-04-11 06:38:29',4,4,'提问4','回复4',4,4,'用户名4','upload/chat_uimage4.jpg,upload/chat_uimage5.jpg,upload/chat_uimage6.jpg',4),(5,'2026-04-11 06:38:29',5,5,'提问5','回复5',5,5,'用户名5','upload/chat_uimage5.jpg,upload/chat_uimage6.jpg,upload/chat_uimage7.jpg',5),(6,'2026-04-11 06:38:29',6,6,'提问6','回复6',6,6,'用户名6','upload/chat_uimage6.jpg,upload/chat_uimage7.jpg,upload/chat_uimage8.jpg',6),(7,'2026-04-11 06:38:29',7,7,'提问7','回复7',7,7,'用户名7','upload/chat_uimage7.jpg,upload/chat_uimage8.jpg,upload/chat_uimage1.jpg',7),(8,'2026-04-11 06:38:29',8,8,'提问8','回复8',8,8,'用户名8','upload/chat_uimage8.jpg,upload/chat_uimage1.jpg,upload/chat_uimage2.jpg',8);
/*!40000 ALTER TABLE `chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chathelper`
--

DROP TABLE IF EXISTS `chathelper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chathelper` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `ask` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '提问',
  `reply` longtext COLLATE utf8mb4_unicode_ci COMMENT '回复',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='聊天助手表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chathelper`
--

LOCK TABLES `chathelper` WRITE;
/*!40000 ALTER TABLE `chathelper` DISABLE KEYS */;
INSERT INTO `chathelper` VALUES (1,'2026-04-11 06:38:29','提问1','回复1'),(2,'2026-04-11 06:38:29','提问2','回复2'),(3,'2026-04-11 06:38:29','提问3','回复3'),(4,'2026-04-11 06:38:29','提问4','回复4'),(5,'2026-04-11 06:38:29','提问5','回复5'),(6,'2026-04-11 06:38:29','提问6','回复6'),(7,'2026-04-11 06:38:29','提问7','回复7'),(8,'2026-04-11 06:38:29','提问8','回复8');
/*!40000 ALTER TABLE `chathelper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config`
--

DROP TABLE IF EXISTS `config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '配置参数名称',
  `value` varchar(200) DEFAULT NULL COMMENT '配置参数值',
  `url` varchar(500) DEFAULT NULL COMMENT 'url',
  `type` int(11) DEFAULT NULL COMMENT '参数类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='配置文件';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config`
--

LOCK TABLES `config` WRITE;
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
INSERT INTO `config` VALUES (1,'picture1','upload/picture1.jpg',NULL,1),(2,'picture2','upload/picture2.jpg',NULL,1),(3,'picture3','upload/picture3.jpg',NULL,1),(11,'baidu','{\"appId\":\"49214550\",\"apiKey\":\"7Otjpv2kn0ljQk45qXOXh5MO\",\"secretKey\":\"BMfbXRbTIVaB4C3SbRTtGqDv1wHDvyXS\"}',NULL,2),(13,'aliyun','{\"accessKeyId\":\"LTAI5tFUSkPQR7FpEY8U7tY7\",\"accessKeySecret\":\"ZJrNPnyem4IT5HGOWPxl8nx7GRINw9\"}',NULL,2),(14,'deepseek','{\"key\":\"sk-961af215e60e4f269c2808b3a55eb9e8\"}',NULL,2),(21,'bLoginBackgroundImg','',NULL,3),(22,'bRegisterBackgroundImg','',NULL,3),(23,'bIndexBackgroundImg','',NULL,3),(24,'bTopLogo','',NULL,3),(25,'bHomeLogo','',NULL,3),(29,'appLoginBackgroundImg','',NULL,3),(30,'appRegisterBackgroudImg','',NULL,3),(31,'appLoginLogo','',NULL,3),(32,'appRegLogo','',NULL,3);
/*!40000 ALTER TABLE `config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dianzijiangyi`
--

DROP TABLE IF EXISTS `dianzijiangyi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dianzijiangyi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `jiangyimingcheng` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '讲义名称',
  `jiangyifenlei` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '讲义分类',
  `tupian` longtext COLLATE utf8mb4_unicode_ci COMMENT '图片',
  `fujianxiazai` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '附件下载',
  `kaodianbiji` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '考点笔记',
  `jiangyineirong` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '讲义内容',
  `gonghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '工号',
  `jiaoshixingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '教师姓名',
  `clicktime` datetime DEFAULT NULL COMMENT '最近点击时间',
  `clicknum` int(11) DEFAULT '0' COMMENT '点击次数',
  `discussnum` int(11) DEFAULT '0' COMMENT '评论数',
  `storeupnum` int(11) DEFAULT '0' COMMENT '收藏数',
  PRIMARY KEY (`id`),
  KEY `jiangyifenlei` (`jiangyifenlei`),
  CONSTRAINT `dianzijiangyi_ibfk_1` FOREIGN KEY (`jiangyifenlei`) REFERENCES `jiangyifenlei` (`jiangyifenlei`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='电子讲义';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dianzijiangyi`
--

LOCK TABLES `dianzijiangyi` WRITE;
/*!40000 ALTER TABLE `dianzijiangyi` DISABLE KEYS */;
INSERT INTO `dianzijiangyi` VALUES (1,'2026-04-11 06:38:28','数据库系统','计算机科学','upload/dianzijiangyi_数据库系统1.jpg,upload/dianzijiangyi_数据库系统2.jpg,upload/dianzijiangyi_数据库系统3.jpg','','未生成数据','SQL查询','101','徐天宇','2026-04-11 14:38:28',1,0,1),(2,'2026-04-11 06:38:28','软件工程','计算机科学','upload/dianzijiangyi_软件工程1.jpg,upload/dianzijiangyi_软件工程2.jpg,upload/dianzijiangyi_软件工程3.jpg','','未生成数据','需求分析','105','欧阳晨','2026-04-11 14:38:28',2,0,2),(3,'2026-04-11 06:38:28','计算机图形学','计算机科学','upload/dianzijiangyi_计算机图形学1.jpg,upload/dianzijiangyi_计算机图形学2.jpg,upload/dianzijiangyi_计算机图形学3.jpg','','未生成数据','OpenGL','106','朱琳','2026-04-11 14:38:28',3,0,3),(4,'2026-04-11 06:38:28','计算机网络','计算机科学','upload/dianzijiangyi_计算机网络1.jpg,upload/dianzijiangyi_计算机网络2.jpg,upload/dianzijiangyi_计算机网络3.jpg','','未生成数据','网络协议','108','杨洁','2026-04-11 14:38:28',4,0,4),(5,'2026-04-11 06:38:29','区块链技术','计算机科学','upload/dianzijiangyi_区块链技术1.jpg,upload/dianzijiangyi_区块链技术2.jpg,upload/dianzijiangyi_区块链技术3.jpg','','未生成数据','智能合约','104','吴磊','2026-04-11 14:38:29',5,0,5),(6,'2026-04-11 06:38:29','编译原理','计算机科学','upload/dianzijiangyi_编译原理1.jpg,upload/dianzijiangyi_编译原理2.jpg,upload/dianzijiangyi_编译原理3.jpg','','未生成数据','词法分析','103','郑晓琳','2026-04-11 14:38:29',6,0,6),(7,'2026-04-11 06:38:29','信息安全','计算机科学','upload/dianzijiangyi_信息安全1.jpg,upload/dianzijiangyi_信息安全2.jpg,upload/dianzijiangyi_信息安全3.jpg','','未生成数据','加密算法','102','王若曦','2026-04-11 14:38:29',7,0,7),(8,'2026-04-11 06:38:29','大数据技术','计算机科学','upload/dianzijiangyi_大数据技术1.jpg,upload/dianzijiangyi_大数据技术2.jpg,upload/dianzijiangyi_大数据技术3.jpg','','未生成数据','Hadoop','107','刘芳','2026-04-11 14:38:29',8,0,8);
/*!40000 ALTER TABLE `dianzijiangyi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discussdianzijiangyi`
--

DROP TABLE IF EXISTS `discussdianzijiangyi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discussdianzijiangyi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `refid` bigint(20) NOT NULL COMMENT '关联表id',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `avatarurl` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  `nickname` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论内容',
  `reply` longtext COLLATE utf8mb4_unicode_ci COMMENT '回复内容',
  `thumbsupnum` int(11) DEFAULT '0' COMMENT '赞',
  `crazilynum` int(11) DEFAULT '0' COMMENT '踩',
  `istop` int(11) DEFAULT '0' COMMENT '置顶(1:置顶,0:非置顶)',
  `tuserids` longtext COLLATE utf8mb4_unicode_ci COMMENT '赞用户ids',
  `cuserids` longtext COLLATE utf8mb4_unicode_ci COMMENT '踩用户ids',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='电子讲义评论';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discussdianzijiangyi`
--

LOCK TABLES `discussdianzijiangyi` WRITE;
/*!40000 ALTER TABLE `discussdianzijiangyi` DISABLE KEYS */;
/*!40000 ALTER TABLE `discussdianzijiangyi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discusskechengxinxi`
--

DROP TABLE IF EXISTS `discusskechengxinxi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discusskechengxinxi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `refid` bigint(20) NOT NULL COMMENT '关联表id',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `avatarurl` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  `nickname` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论内容',
  `score` double DEFAULT NULL COMMENT '评分',
  `reply` longtext COLLATE utf8mb4_unicode_ci COMMENT '回复内容',
  `thumbsupnum` int(11) DEFAULT '0' COMMENT '赞',
  `crazilynum` int(11) DEFAULT '0' COMMENT '踩',
  `istop` int(11) DEFAULT '0' COMMENT '置顶(1:置顶,0:非置顶)',
  `tuserids` longtext COLLATE utf8mb4_unicode_ci COMMENT '赞用户ids',
  `cuserids` longtext COLLATE utf8mb4_unicode_ci COMMENT '踩用户ids',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='课程信息评论';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discusskechengxinxi`
--

LOCK TABLES `discusskechengxinxi` WRITE;
/*!40000 ALTER TABLE `discusskechengxinxi` DISABLE KEYS */;
/*!40000 ALTER TABLE `discusskechengxinxi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exampaper`
--

DROP TABLE IF EXISTS `exampaper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exampaper` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '试卷名称',
  `time` int(11) NOT NULL COMMENT '考试时长(分钟)',
  `status` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '启用' COMMENT '试卷状态',
  `examnum` int(11) DEFAULT '1' COMMENT '限考次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='在线测试';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exampaper`
--

LOCK TABLES `exampaper` WRITE;
/*!40000 ALTER TABLE `exampaper` DISABLE KEYS */;
INSERT INTO `exampaper` VALUES (1,'2026-04-11 06:38:29','十万个为什么',60,'1',1);
/*!40000 ALTER TABLE `exampaper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `examquestion`
--

DROP TABLE IF EXISTS `examquestion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `examquestion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `score` bigint(20) DEFAULT '0' COMMENT '分值',
  `answer` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '正确答案',
  `analysis` longtext COLLATE utf8mb4_unicode_ci COMMENT '答案解析',
  `type` bigint(20) DEFAULT NULL COMMENT '试题类型',
  `sequence` bigint(20) DEFAULT NULL COMMENT '试题排序，值越大排越前面',
  `paperid` bigint(20) NOT NULL COMMENT '试卷id',
  `papername` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '试卷名称',
  `questionname` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '试题名称',
  `options` longtext COLLATE utf8mb4_unicode_ci COMMENT '选项，json字符串',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='试题';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `examquestion`
--

LOCK TABLES `examquestion` WRITE;
/*!40000 ALTER TABLE `examquestion` DISABLE KEYS */;
INSERT INTO `examquestion` VALUES (1,'2026-04-11 06:38:29',20,'C','蜂鸟',0,1,1,'十万个为什么','下面动物不属于昆虫的是（）。','[{\"text\":\"A.苍蝇\",\"code\":\"A\"},{\"text\":\"B.蜜蜂\",\"code\":\"B\"},{\"text\":\"C.蜂鸟\",\"code\":\"C\"}]'),(2,'2026-04-11 06:38:29',20,'B','油着火后不可以用水扑灭',2,2,1,'十万个为什么','油着火后可以用水扑灭。','[{\"text\":\"A.对\",\"code\":\"A\"},{\"text\":\"B.错\",\"code\":\"B\"}]'),(3,'2026-04-11 06:38:29',30,'赤道','赤道',3,3,1,'十万个为什么','地球是个球体，中间是（ ）。','[]'),(4,'2026-04-11 06:38:29',30,'A,B','狗不会流汗',1,4,1,'十万个为什么','下面动物中会流汗的有（ ）。','[{\"text\":\"A.马\",\"code\":\"A\"},{\"text\":\"B.猫\",\"code\":\"B\"},{\"text\":\"C.狗\",\"code\":\"C\"}]'),(5,'2026-04-11 06:38:29',30,'','目前没有证据表明现实中存在完美的圆',4,5,1,'十万个为什么','世界上为什么不存在完美的圆？','[]');
/*!40000 ALTER TABLE `examquestion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `examquestionbank`
--

DROP TABLE IF EXISTS `examquestionbank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `examquestionbank` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `questionname` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '试题名称',
  `options` longtext COLLATE utf8mb4_unicode_ci COMMENT '选项，json字符串',
  `score` bigint(20) DEFAULT '0' COMMENT '分值',
  `answer` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '正确答案',
  `analysis` longtext COLLATE utf8mb4_unicode_ci COMMENT '答案解析',
  `type` bigint(20) DEFAULT NULL COMMENT '试题类型',
  `sequence` bigint(20) DEFAULT NULL COMMENT '试题排序，值越大排越前面',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='试题库';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `examquestionbank`
--

LOCK TABLES `examquestionbank` WRITE;
/*!40000 ALTER TABLE `examquestionbank` DISABLE KEYS */;
INSERT INTO `examquestionbank` VALUES (1,'2026-04-11 06:38:29','下面动物不属于昆虫的是（）。','[{\"text\":\"A.苍蝇\",\"code\":\"A\"},{\"text\":\"B.蜜蜂\",\"code\":\"B\"},{\"text\":\"C.蜂鸟\",\"code\":\"C\"}]',20,'C','蜂鸟',0,1),(2,'2026-04-11 06:38:29','油着火后可以用水扑灭。','[{\"text\":\"A.对\",\"code\":\"A\"},{\"text\":\"B.错\",\"code\":\"B\"}]',20,'B','油着火后不可以用水扑灭',2,2),(3,'2026-04-11 06:38:29','地球是个球体，中间是（ ）。','[]',30,'赤道','赤道',3,3),(4,'2026-04-11 06:38:29','下面动物中会流汗的有（ ）。','[{\"text\":\"A.马\",\"code\":\"A\"},{\"text\":\"B.猫\",\"code\":\"B\"},{\"text\":\"C.狗\",\"code\":\"C\"}]',30,'A,B','狗不会流汗',1,4),(5,'2026-04-11 06:38:29','世界上为什么不存在完美的圆？','[]',30,'','目前没有证据表明现实中存在完美的圆',4,5);
/*!40000 ALTER TABLE `examquestionbank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `examrecord`
--

DROP TABLE IF EXISTS `examrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `examrecord` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `username` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `paperid` bigint(20) NOT NULL COMMENT '试卷id',
  `papername` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '试卷名称',
  `questionid` bigint(20) NOT NULL COMMENT '试题id',
  `questionname` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '试题名称',
  `type` bigint(20) DEFAULT NULL COMMENT '试题类型',
  `ismark` bigint(20) DEFAULT NULL COMMENT '是否批卷',
  `options` longtext COLLATE utf8mb4_unicode_ci COMMENT '选项，json字符串',
  `score` bigint(20) DEFAULT '0' COMMENT '分值',
  `answer` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '正确答案',
  `analysis` longtext COLLATE utf8mb4_unicode_ci COMMENT '答案解析',
  `myscore` bigint(20) DEFAULT '0' COMMENT '试题得分',
  `myanswer` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '考生答案',
  `examno` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '考试编号',
  `userid` bigint(20) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='测试记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `examrecord`
--

LOCK TABLES `examrecord` WRITE;
/*!40000 ALTER TABLE `examrecord` DISABLE KEYS */;
INSERT INTO `examrecord` VALUES (1,'2026-04-11 06:42:32','吴磊',1,'十万个为什么',5,'世界上为什么不存在完美的圆？',4,0,'[]',30,'','目前没有证据表明现实中存在完美的圆',0,'2','1775889745989',15),(2,'2026-04-11 06:42:35','吴磊',1,'十万个为什么',4,'下面动物中会流汗的有（ ）。',1,0,'[{\"text\":\"A.马\",\"code\":\"A\"},{\"text\":\"B.猫\",\"code\":\"B\"},{\"text\":\"C.狗\",\"code\":\"C\"}]',30,'A,B','狗不会流汗',30,'A,B','1775889745989',15),(3,'2026-04-11 06:42:36','吴磊',1,'十万个为什么',3,'地球是个球体，中间是（ ）。',3,0,'[]',30,'赤道','赤道',0,'21','1775889745989',15),(4,'2026-04-11 06:42:37','吴磊',1,'十万个为什么',2,'油着火后可以用水扑灭。',2,0,'[{\"text\":\"A.对\",\"code\":\"A\"},{\"text\":\"B.错\",\"code\":\"B\"}]',20,'B','油着火后不可以用水扑灭',20,'B','1775889745989',15),(5,'2026-04-11 06:42:39','吴磊',1,'十万个为什么',1,'下面动物不属于昆虫的是（）。',0,0,'[{\"text\":\"A.苍蝇\",\"code\":\"A\"},{\"text\":\"B.蜜蜂\",\"code\":\"B\"},{\"text\":\"C.蜂鸟\",\"code\":\"C\"}]',20,'C','蜂鸟',0,'A','1775889745989',15);
/*!40000 ALTER TABLE `examrecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jiangyifenlei`
--

DROP TABLE IF EXISTS `jiangyifenlei`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jiangyifenlei` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `jiangyifenlei` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '讲义分类',
  PRIMARY KEY (`id`),
  UNIQUE KEY `jiangyifenlei` (`jiangyifenlei`),
  KEY `jiangyifenlei_zhnf` (`jiangyifenlei`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='讲义分类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jiangyifenlei`
--

LOCK TABLES `jiangyifenlei` WRITE;
/*!40000 ALTER TABLE `jiangyifenlei` DISABLE KEYS */;
INSERT INTO `jiangyifenlei` VALUES (1,'2026-04-11 06:38:28','计算机科学'),(2,'2026-04-11 06:38:28','讲义分类2'),(3,'2026-04-11 06:38:28','讲义分类3'),(4,'2026-04-11 06:38:28','讲义分类4'),(5,'2026-04-11 06:38:28','讲义分类5'),(6,'2026-04-11 06:38:28','讲义分类6'),(7,'2026-04-11 06:38:28','讲义分类7'),(8,'2026-04-11 06:38:28','讲义分类8');
/*!40000 ALTER TABLE `jiangyifenlei` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jiaoshi`
--

DROP TABLE IF EXISTS `jiaoshi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jiaoshi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gonghao` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '工号',
  `mima` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `jiaoshixingming` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '教师姓名',
  `touxiang` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  `zizhizhengming` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '资质证明',
  `xingbie` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性别',
  `nianling` int(11) DEFAULT NULL COMMENT '年龄',
  `zhicheng` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '职称',
  `shanzhangkemu` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '擅长科目',
  `jiaoxuefengge` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '教学风格',
  `fudaochengguo` longtext COLLATE utf8mb4_unicode_ci COMMENT '辅导成果',
  `sfsh` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '待审核' COMMENT '是否审核',
  `shhf` longtext COLLATE utf8mb4_unicode_ci COMMENT '审核回复',
  `mobile` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机号',
  `pquestion` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密保问题',
  `panswer` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密保答案',
  `status` int(11) DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `gonghao` (`gonghao`),
  KEY `jiaoshi_u3ho` (`gonghao`,`jiaoshixingming`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='教师';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jiaoshi`
--

LOCK TABLES `jiaoshi` WRITE;
/*!40000 ALTER TABLE `jiaoshi` DISABLE KEYS */;
INSERT INTO `jiaoshi` VALUES (21,'2026-04-11 06:38:28','101','123456','徐天宇','upload/jiaoshi_touxiang1.jpg','','男',1,'助教','计算机科学','思维拓展型','辅导成果1','是','','13823888881','密保问题1','密保答案1',0),(22,'2026-04-11 06:38:28','105','123456','欧阳晨','upload/jiaoshi_touxiang2.jpg','','男',2,'副教授','计算机科学','脉络梳理型','辅导成果2','是','','13823888882','密保问题2','密保答案2',0),(23,'2026-04-11 06:38:28','106','123456','朱琳','upload/jiaoshi_touxiang3.jpg','','男',3,'讲师','计算机科学','规范指导型','辅导成果3','是','','13823888883','密保问题3','密保答案3',0),(24,'2026-04-11 06:38:28','108','123456','杨洁','upload/jiaoshi_touxiang4.jpg','','男',4,'副教授','计算机科学','细致推导型','辅导成果4','是','','13823888884','密保问题4','密保答案4',0),(25,'2026-04-11 06:38:28','104','123456','吴磊','upload/jiaoshi_touxiang5.jpg','','男',5,'副教授','计算机科学','实验探究型','辅导成果5','是','','13823888885','密保问题5','密保答案5',0),(26,'2026-04-11 06:38:28','103','123456','郑晓琳','upload/jiaoshi_touxiang6.jpg','','男',6,'副教授','计算机科学','活泼互动型','辅导成果6','是','','13823888886','密保问题6','密保答案6',0),(27,'2026-04-11 06:38:28','102','123456','王若曦','upload/jiaoshi_touxiang7.jpg','','男',7,'教授','计算机科学','系统提升型','辅导成果7','是','','13823888887','密保问题7','密保答案7',0),(28,'2026-04-11 06:38:28','107','123456','刘芳','upload/jiaoshi_touxiang8.jpg','','男',8,'讲师','计算机科学','趣味图解型','辅导成果8','是','','13823888888','密保问题8','密保答案8',0);
/*!40000 ALTER TABLE `jiaoshi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kebiaoxinix`
--

DROP TABLE IF EXISTS `kebiaoxinix`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kebiaoxinix` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `kechengmingcheng` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '课程名称',
  `sectionnum` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '节数',
  `riqi` date NOT NULL COMMENT '日期',
  `week` int(11) NOT NULL COMMENT '星期',
  `banji` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '班级',
  `jiaoshi` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '教室',
  `gonghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '工号',
  `jiaoshixingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '教师姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='课表信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kebiaoxinix`
--

LOCK TABLES `kebiaoxinix` WRITE;
/*!40000 ALTER TABLE `kebiaoxinix` DISABLE KEYS */;
INSERT INTO `kebiaoxinix` VALUES (1,'2026-04-11 06:38:29','数据库系统','1','2026-04-11',1,'土木24-3','606','工号1','教师姓名1'),(2,'2026-04-11 06:38:29','软件工程','2','2026-04-11',2,'中文24-1','101','工号2','教师姓名2'),(3,'2026-04-11 06:38:29','计算机图形学','3','2026-04-11',3,'音乐24-1','1001','工号3','教师姓名3'),(4,'2026-04-11 06:38:29','计算机网络','4','2026-04-11',4,'机械24-2','707','工号4','教师姓名4'),(5,'2026-04-11 06:38:29','区块链技术','5','2026-04-11',5,'自动化24-1','909','工号5','教师姓名5'),(6,'2026-04-11 06:38:29','编译原理','6','2026-04-11',6,'中文24-2','505','工号6','教师姓名6'),(7,'2026-04-11 06:38:29','信息安全','7','2026-04-11',7,'国贸24-3','808','工号7','教师姓名7'),(8,'2026-04-11 06:38:29','大数据技术','8','2026-04-11',1,'美术24-3','1102','工号8','教师姓名8');
/*!40000 ALTER TABLE `kebiaoxinix` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kechengfenlei`
--

DROP TABLE IF EXISTS `kechengfenlei`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kechengfenlei` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `kechengfenlei` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '课程分类',
  PRIMARY KEY (`id`),
  UNIQUE KEY `kechengfenlei` (`kechengfenlei`),
  KEY `kechengfenlei_231r` (`kechengfenlei`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='课程分类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kechengfenlei`
--

LOCK TABLES `kechengfenlei` WRITE;
/*!40000 ALTER TABLE `kechengfenlei` DISABLE KEYS */;
INSERT INTO `kechengfenlei` VALUES (1,'2026-04-11 06:38:28','计算机科学'),(2,'2026-04-11 06:38:28','课程分类2'),(3,'2026-04-11 06:38:28','课程分类3'),(4,'2026-04-11 06:38:28','课程分类4'),(5,'2026-04-11 06:38:28','课程分类5'),(6,'2026-04-11 06:38:28','课程分类6'),(7,'2026-04-11 06:38:28','课程分类7'),(8,'2026-04-11 06:38:28','课程分类8');
/*!40000 ALTER TABLE `kechengfenlei` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kechengxinxi`
--

DROP TABLE IF EXISTS `kechengxinxi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kechengxinxi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `kechenghao` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '课程号',
  `kechengmingcheng` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '课程名称',
  `kechengfenlei` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '课程分类',
  `tupian` longtext COLLATE utf8mb4_unicode_ci COMMENT '图片',
  `shangkeshijian` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '上课时间',
  `feiyong` double NOT NULL COMMENT '费用',
  `nandu` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '难度',
  `kechengshipin` longtext COLLATE utf8mb4_unicode_ci COMMENT '课程视频',
  `keshi` double NOT NULL COMMENT '课时',
  `kechengdagang` longtext COLLATE utf8mb4_unicode_ci COMMENT '课程大纲',
  `kechengneirong` longtext COLLATE utf8mb4_unicode_ci COMMENT '课程内容',
  `gonghao` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '工号',
  `jiaoshixingming` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '教师姓名',
  `sfsh` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '待审核' COMMENT '是否审核',
  `shhf` longtext COLLATE utf8mb4_unicode_ci COMMENT '审核回复',
  `clicktime` datetime DEFAULT NULL COMMENT '最近点击时间',
  `clicknum` int(11) DEFAULT '0' COMMENT '点击次数',
  `discussnum` int(11) DEFAULT '0' COMMENT '评论数',
  `totalscore` double DEFAULT '0' COMMENT '评分',
  `storeupnum` int(11) DEFAULT '0' COMMENT '收藏数',
  PRIMARY KEY (`id`),
  UNIQUE KEY `kechenghao` (`kechenghao`),
  KEY `kechengfenlei` (`kechengfenlei`),
  CONSTRAINT `kechengxinxi_ibfk_1` FOREIGN KEY (`kechengfenlei`) REFERENCES `kechengfenlei` (`kechengfenlei`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='课程信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kechengxinxi`
--

LOCK TABLES `kechengxinxi` WRITE;
/*!40000 ALTER TABLE `kechengxinxi` DISABLE KEYS */;
INSERT INTO `kechengxinxi` VALUES (1,'2026-04-11 06:38:28','GK003','数据库系统','计算机科学','upload/kechengxinxi_数据库系统1.jpg,upload/kechengxinxi_数据库系统2.jpg,upload/kechengxinxi_数据库系统3.jpg','8:-12:00',130,'中级','',53,'1.关系模型 2.SQL 3.索引','ER图、SQL查询、索引优化','101','徐天宇','是','','2026-04-11 14:38:28',1,0,0,1),(2,'2026-04-11 06:38:28','GK005','软件工程','计算机科学','upload/kechengxinxi_软件工程1.jpg,upload/kechengxinxi_软件工程2.jpg,upload/kechengxinxi_软件工程3.jpg','8:-12:00',142,'中级','',72,'1.需求分析 2.设计 3.测试','UML建模、单元测试、Scrum','105','欧阳晨','是','','2026-04-11 14:38:28',2,0,0,2),(3,'2026-04-11 06:38:28','GK013','计算机图形学','计算机科学','upload/kechengxinxi_计算机图形学1.jpg,upload/kechengxinxi_计算机图形学2.jpg,upload/kechengxinxi_计算机图形学3.jpg','8:-12:00',92,'中级','',44,'1.渲染 2.建模 3.动画','顶点着色器、纹理映射、骨骼动画','106','朱琳','是','','2026-04-11 14:38:28',3,0,0,3),(4,'2026-04-11 06:38:28','GK019','计算机网络','计算机科学','upload/kechengxinxi_计算机网络1.jpg,upload/kechengxinxi_计算机网络2.jpg,upload/kechengxinxi_计算机网络3.jpg','8:-12:00',150,'高级','',64,'1.OSI模型 2.TCP/IP 3.路由','TCP三次握手、IP地址分配、路由算法','108','杨洁','是','','2026-04-11 14:38:28',4,0,0,4),(5,'2026-04-11 06:38:28','GK002','区块链技术','计算机科学','upload/kechengxinxi_区块链技术1.jpg,upload/kechengxinxi_区块链技术2.jpg,upload/kechengxinxi_区块链技术3.jpg','8:-12:00',135,'中级','',58,'1.共识 2.智能合约 3.DApp','PoW、Solidity、Web3.js','104','吴磊','是','','2026-04-11 14:38:28',5,0,0,5),(6,'2026-04-11 06:38:28','GK010','编译原理','计算机科学','upload/kechengxinxi_编译原理1.jpg,upload/kechengxinxi_编译原理2.jpg,upload/kechengxinxi_编译原理3.jpg','8:-12:00',90,'高级','',28,'1.词法分析 2.语法分析 3.优化','正则表达式、LL(1)文法、中间代码生成','103','郑晓琳','是','','2026-04-11 14:38:28',6,0,0,6),(7,'2026-04-11 06:38:28','GK001','信息安全','计算机科学','upload/kechengxinxi_信息安全1.jpg,upload/kechengxinxi_信息安全2.jpg,upload/kechengxinxi_信息安全3.jpg','8:-12:00',89,'中级','',68,'1.加密 2.防火墙 3.渗透测试','AES加密、包过滤、SQL注入','102','王若曦','是','','2026-04-11 14:38:28',7,0,0,7),(8,'2026-04-11 06:38:28','GK011','大数据技术','计算机科学','upload/kechengxinxi_大数据技术1.jpg,upload/kechengxinxi_大数据技术2.jpg,upload/kechengxinxi_大数据技术3.jpg','8:-12:00',83,'中级','',29,'1.Hadoop 2.Spark 3.数据挖掘','MapReduce、RDD、K-means聚类','107','刘芳','是','','2026-04-11 14:38:28',8,0,0,8);
/*!40000 ALTER TABLE `kechengxinxi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kehoufudao`
--

DROP TABLE IF EXISTS `kehoufudao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kehoufudao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `kechengmingcheng` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程名称',
  `kechengfenlei` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程分类',
  `tupian` longtext COLLATE utf8mb4_unicode_ci COMMENT '图片',
  `xuexixiaoguo` longtext COLLATE utf8mb4_unicode_ci COMMENT '学习效果',
  `fudaoneirong` longtext COLLATE utf8mb4_unicode_ci COMMENT '辅导内容',
  `xuehao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学号',
  `xingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '姓名',
  `gonghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '工号',
  `jiaoshixingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '教师姓名',
  `fudaoshijian` datetime DEFAULT NULL COMMENT '辅导时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='课后辅导';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kehoufudao`
--

LOCK TABLES `kehoufudao` WRITE;
/*!40000 ALTER TABLE `kehoufudao` DISABLE KEYS */;
INSERT INTO `kehoufudao` VALUES (1,'2026-04-11 06:38:29','课程名称1','课程分类1','upload/kehoufudao_tupian1.jpg,upload/kehoufudao_tupian2.jpg,upload/kehoufudao_tupian3.jpg','学习效果1','辅导内容1','学号1','姓名1','工号1','教师姓名1','2026-04-11 14:38:29'),(2,'2026-04-11 06:38:29','课程名称2','课程分类2','upload/kehoufudao_tupian2.jpg,upload/kehoufudao_tupian3.jpg,upload/kehoufudao_tupian4.jpg','学习效果2','辅导内容2','学号2','姓名2','工号2','教师姓名2','2026-04-11 14:38:29'),(3,'2026-04-11 06:38:29','课程名称3','课程分类3','upload/kehoufudao_tupian3.jpg,upload/kehoufudao_tupian4.jpg,upload/kehoufudao_tupian5.jpg','学习效果3','辅导内容3','学号3','姓名3','工号3','教师姓名3','2026-04-11 14:38:29'),(4,'2026-04-11 06:38:29','课程名称4','课程分类4','upload/kehoufudao_tupian4.jpg,upload/kehoufudao_tupian5.jpg,upload/kehoufudao_tupian6.jpg','学习效果4','辅导内容4','学号4','姓名4','工号4','教师姓名4','2026-04-11 14:38:29'),(5,'2026-04-11 06:38:29','课程名称5','课程分类5','upload/kehoufudao_tupian5.jpg,upload/kehoufudao_tupian6.jpg,upload/kehoufudao_tupian7.jpg','学习效果5','辅导内容5','学号5','姓名5','工号5','教师姓名5','2026-04-11 14:38:29'),(6,'2026-04-11 06:38:29','课程名称6','课程分类6','upload/kehoufudao_tupian6.jpg,upload/kehoufudao_tupian7.jpg,upload/kehoufudao_tupian8.jpg','学习效果6','辅导内容6','学号6','姓名6','工号6','教师姓名6','2026-04-11 14:38:29'),(7,'2026-04-11 06:38:29','课程名称7','课程分类7','upload/kehoufudao_tupian7.jpg,upload/kehoufudao_tupian8.jpg,upload/kehoufudao_tupian1.jpg','学习效果7','辅导内容7','学号7','姓名7','工号7','教师姓名7','2026-04-11 14:38:29'),(8,'2026-04-11 06:38:29','课程名称8','课程分类8','upload/kehoufudao_tupian8.jpg,upload/kehoufudao_tupian1.jpg,upload/kehoufudao_tupian2.jpg','学习效果8','辅导内容8','学号8','姓名8','工号8','教师姓名8','2026-04-11 14:38:29');
/*!40000 ALTER TABLE `kehoufudao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `title` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题',
  `introduction` longtext COLLATE utf8mb4_unicode_ci COMMENT '简介',
  `picture` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图片',
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '内容',
  `name` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '发布人',
  `headportrait` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='公告资讯';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` VALUES (1,'2026-04-11 06:38:29','调剂系统操作指南','调剂系统使用全流程解析','upload/news_picture1.jpg','调剂报名流程、志愿填报技巧、院校调剂名额查询、复试调剂注意事项','徐天宇','upload/news_headportrait1.jpg'),(2,'2026-04-11 06:38:29','考研住宿及考场安排','考试住宿及考场注意事项','upload/news_picture2.jpg','考场查询方法、住宿预订技巧、考试当天时间规划及物品准备清单','欧阳晨','upload/news_headportrait2.jpg'),(3,'2026-04-11 06:38:29','考研推免生申请指南','推免生申请流程及技巧','upload/news_picture3.jpg','推免条件、材料准备、复试考核内容、接收院校选择策略','朱琳','upload/news_headportrait3.jpg'),(4,'2026-04-11 06:38:29','考研备考资料推荐','必备考研资料及获取渠道','upload/news_picture4.jpg','公共课、专业课优质参考书、网课资源、真题资料及高效使用方法','杨洁','upload/news_headportrait4.jpg'),(5,'2026-04-11 06:38:29','考研加分政策解读','考研加分及优惠政策汇总','upload/news_picture5.jpg','少数民族照顾政策、退役大学生士兵计划、支教团加分等政策详解','吴磊','upload/news_headportrait5.jpg'),(6,'2026-04-11 06:38:29','考研英语备考规划','英语全年备考时间规划','upload/news_picture6.jpg','基础阶段、强化阶段、冲刺阶段复习重点，真题使用方法及作文模板推荐','郑晓琳','upload/news_headportrait6.jpg'),(7,'2026-04-11 06:38:29','专业课复习方法分享','专业课高效复习经验','upload/news_picture7.jpg','参考书选择、笔记整理技巧、真题利用方法、重难点突破策略','王若曦','upload/news_headportrait7.jpg'),(8,'2026-04-11 06:38:29','考研数学真题解析','历年数学真题详细解析','upload/news_picture8.jpg','按章节分类讲解真题，总结解题思路，高频考点归纳，易错点提醒','刘芳','upload/news_headportrait8.jpg');
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `popupremind`
--

DROP TABLE IF EXISTS `popupremind`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `popupremind` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint(20) NOT NULL COMMENT '发布人id',
  `role` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色',
  `title` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题',
  `type` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '个人' COMMENT '类型',
  `brief` longtext COLLATE utf8mb4_unicode_ci COMMENT '简介',
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '内容',
  `remindtime` datetime DEFAULT NULL COMMENT '提醒时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='弹窗提醒';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `popupremind`
--

LOCK TABLES `popupremind` WRITE;
/*!40000 ALTER TABLE `popupremind` DISABLE KEYS */;
INSERT INTO `popupremind` VALUES (1,'2026-04-11 06:38:29',1,'角色1','标题1','个人','简介1','内容1','2026-04-11 14:38:29'),(2,'2026-04-11 06:38:29',2,'角色2','标题2','个人','简介2','内容2','2026-04-11 14:38:29'),(3,'2026-04-11 06:38:29',3,'角色3','标题3','个人','简介3','内容3','2026-04-11 14:38:29'),(4,'2026-04-11 06:38:29',4,'角色4','标题4','个人','简介4','内容4','2026-04-11 14:38:29'),(5,'2026-04-11 06:38:29',5,'角色5','标题5','个人','简介5','内容5','2026-04-11 14:38:29'),(6,'2026-04-11 06:38:29',6,'角色6','标题6','个人','简介6','内容6','2026-04-11 14:38:29'),(7,'2026-04-11 06:38:29',7,'角色7','标题7','个人','简介7','内容7','2026-04-11 14:38:29'),(8,'2026-04-11 06:38:29',8,'角色8','标题8','个人','简介8','内容8','2026-04-11 14:38:29');
/*!40000 ALTER TABLE `popupremind` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scheduletime`
--

DROP TABLE IF EXISTS `scheduletime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scheduletime` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `sectionnum` int(11) NOT NULL COMMENT '节数',
  `starttime` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '开始时间',
  `endtime` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='课表时间';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scheduletime`
--

LOCK TABLES `scheduletime` WRITE;
/*!40000 ALTER TABLE `scheduletime` DISABLE KEYS */;
INSERT INTO `scheduletime` VALUES (1,'2026-04-11 06:38:29',1,'08:00','09:00'),(2,'2026-04-11 06:38:29',2,'09:00','10:00'),(3,'2026-04-11 06:38:29',3,'10:00','11:00'),(4,'2026-04-11 06:38:29',4,'14:00','15:00'),(5,'2026-04-11 06:38:29',5,'15:00','16:00'),(6,'2026-04-11 06:38:29',6,'16:00','17:00'),(7,'2026-04-11 06:38:29',7,'20:00','21:00'),(8,'2026-04-11 06:38:29',8,'21:00','22:00');
/*!40000 ALTER TABLE `scheduletime` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `smsregistercode`
--

DROP TABLE IF EXISTS `smsregistercode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `smsregistercode` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mobile` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '手机',
  `role` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色',
  `code` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '验证码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='短信验证码';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `smsregistercode`
--

LOCK TABLES `smsregistercode` WRITE;
/*!40000 ALTER TABLE `smsregistercode` DISABLE KEYS */;
INSERT INTO `smsregistercode` VALUES (1,'2026-04-11 06:38:29','手机1','角色1','验证码1'),(2,'2026-04-11 06:38:29','手机2','角色2','验证码2'),(3,'2026-04-11 06:38:29','手机3','角色3','验证码3'),(4,'2026-04-11 06:38:29','手机4','角色4','验证码4'),(5,'2026-04-11 06:38:29','手机5','角色5','验证码5'),(6,'2026-04-11 06:38:29','手机6','角色6','验证码6'),(7,'2026-04-11 06:38:29','手机7','角色7','验证码7'),(8,'2026-04-11 06:38:29','手机8','角色8','验证码8');
/*!40000 ALTER TABLE `smsregistercode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storeup`
--

DROP TABLE IF EXISTS `storeup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `storeup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `refid` bigint(20) DEFAULT NULL COMMENT '外键id',
  `tablename` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '表名',
  `name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `picture` longtext COLLATE utf8mb4_unicode_ci COMMENT '图片',
  `type` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '1' COMMENT '类型',
  `inteltype` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '推荐类型',
  `remark` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='收藏表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storeup`
--

LOCK TABLES `storeup` WRITE;
/*!40000 ALTER TABLE `storeup` DISABLE KEYS */;
INSERT INTO `storeup` VALUES (1,'2026-04-11 06:43:58',21,1,'popupremind','标题1',NULL,'61',NULL,NULL),(2,'2026-04-11 06:43:58',21,2,'popupremind','标题2',NULL,'61',NULL,NULL),(3,'2026-04-11 06:43:58',21,3,'popupremind','标题3',NULL,'61',NULL,NULL),(4,'2026-04-11 06:43:58',21,4,'popupremind','标题4',NULL,'61',NULL,NULL),(5,'2026-04-11 06:43:58',21,5,'popupremind','标题5',NULL,'61',NULL,NULL),(6,'2026-04-11 06:43:58',21,6,'popupremind','标题6',NULL,'61',NULL,NULL),(7,'2026-04-11 06:43:59',21,7,'popupremind','标题7',NULL,'61',NULL,NULL),(8,'2026-04-11 06:43:59',21,8,'popupremind','标题8',NULL,'61',NULL,NULL);
/*!40000 ALTER TABLE `storeup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `tablename` varchar(100) DEFAULT NULL COMMENT '表名',
  `role` varchar(100) DEFAULT NULL COMMENT '角色',
  `token` varchar(500) NOT NULL COMMENT '密码',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `expiratedtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='token表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES (1,15,'001','xuesheng','学生','vxqibhh9p3iaixuc36ydwf8fy65or83w','2026-04-11 06:42:24','2026-04-11 07:42:25'),(2,21,'101','jiaoshi','管理员','zd8xq2t9zozqrjbp3t3600zc6qamf87z','2026-04-11 06:43:58','2026-04-11 07:43:59');
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tousufankui`
--

DROP TABLE IF EXISTS `tousufankui`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tousufankui` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `biaoti` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题',
  `tupian` longtext COLLATE utf8mb4_unicode_ci COMMENT '图片',
  `tousuneirong` longtext COLLATE utf8mb4_unicode_ci COMMENT '投诉内容',
  `tousushijian` datetime DEFAULT NULL COMMENT '投诉时间',
  `xuehao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学号',
  `xingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '姓名',
  `sfsh` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '待审核' COMMENT '是否审核',
  `shhf` longtext COLLATE utf8mb4_unicode_ci COMMENT '审核回复',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='投诉反馈';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tousufankui`
--

LOCK TABLES `tousufankui` WRITE;
/*!40000 ALTER TABLE `tousufankui` DISABLE KEYS */;
INSERT INTO `tousufankui` VALUES (1,'2026-04-11 06:38:29','标题1','upload/tousufankui_数据库系统1.jpg,upload/tousufankui_数据库系统2.jpg,upload/tousufankui_数据库系统3.jpg','投诉内容1','2026-04-11 14:38:29','005','徐天宇','是',''),(2,'2026-04-11 06:38:29','标题2','upload/tousufankui_软件工程1.jpg,upload/tousufankui_软件工程2.jpg,upload/tousufankui_软件工程3.jpg','投诉内容2','2026-04-11 14:38:29','002','欧阳晨','是',''),(3,'2026-04-11 06:38:29','标题3','upload/tousufankui_计算机图形学1.jpg,upload/tousufankui_计算机图形学2.jpg,upload/tousufankui_计算机图形学3.jpg','投诉内容3','2026-04-11 14:38:29','007','朱琳','是',''),(4,'2026-04-11 06:38:29','标题4','upload/tousufankui_计算机网络1.jpg,upload/tousufankui_计算机网络2.jpg,upload/tousufankui_计算机网络3.jpg','投诉内容4','2026-04-11 14:38:29','008','杨洁','是',''),(5,'2026-04-11 06:38:29','标题5','upload/tousufankui_区块链技术1.jpg,upload/tousufankui_区块链技术2.jpg,upload/tousufankui_区块链技术3.jpg','投诉内容5','2026-04-11 14:38:29','001','吴磊','是',''),(6,'2026-04-11 06:38:29','标题6','upload/tousufankui_编译原理1.jpg,upload/tousufankui_编译原理2.jpg,upload/tousufankui_编译原理3.jpg','投诉内容6','2026-04-11 14:38:29','003','郑晓琳','是',''),(7,'2026-04-11 06:38:29','标题7','upload/tousufankui_信息安全1.jpg,upload/tousufankui_信息安全2.jpg,upload/tousufankui_信息安全3.jpg','投诉内容7','2026-04-11 14:38:29','004','王若曦','是',''),(8,'2026-04-11 06:38:29','标题8','upload/tousufankui_大数据技术1.jpg,upload/tousufankui_大数据技术2.jpg,upload/tousufankui_大数据技术3.jpg','投诉内容8','2026-04-11 14:38:29','006','刘芳','是','');
/*!40000 ALTER TABLE `tousufankui` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tuifeishenqing`
--

DROP TABLE IF EXISTS `tuifeishenqing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tuifeishenqing` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `kechenghao` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程号',
  `kechengmingcheng` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程名称',
  `kechengfenlei` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程分类',
  `tupian` longtext COLLATE utf8mb4_unicode_ci COMMENT '图片',
  `nandu` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '难度',
  `feiyong` double DEFAULT NULL COMMENT '费用',
  `xuehao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学号',
  `xingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '姓名',
  `gonghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '工号',
  `jiaoshixingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '教师姓名',
  `tuifeishuoming` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '退费说明',
  `shenqingshijian` datetime DEFAULT NULL COMMENT '申请时间',
  `sfsh` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '待审核' COMMENT '是否审核',
  `shhf` longtext COLLATE utf8mb4_unicode_ci COMMENT '审核回复',
  `ispay` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '未支付' COMMENT '是否支付',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='退费申请';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tuifeishenqing`
--

LOCK TABLES `tuifeishenqing` WRITE;
/*!40000 ALTER TABLE `tuifeishenqing` DISABLE KEYS */;
INSERT INTO `tuifeishenqing` VALUES (1,'2026-04-11 06:38:28','GK003','数据库系统','计算机科学','upload/tuifeishenqing_数据库系统1.jpg,upload/tuifeishenqing_数据库系统2.jpg,upload/tuifeishenqing_数据库系统3.jpg','中级',130,'005','徐天宇','101','徐天宇','退费说明1','2026-04-11 14:38:28','是','','已支付'),(2,'2026-04-11 06:38:28','GK005','软件工程','计算机科学','upload/tuifeishenqing_软件工程1.jpg,upload/tuifeishenqing_软件工程2.jpg,upload/tuifeishenqing_软件工程3.jpg','中级',142,'002','欧阳晨','105','欧阳晨','退费说明2','2026-04-11 14:38:28','是','','已支付'),(3,'2026-04-11 06:38:28','GK013','计算机图形学','计算机科学','upload/tuifeishenqing_计算机图形学1.jpg,upload/tuifeishenqing_计算机图形学2.jpg,upload/tuifeishenqing_计算机图形学3.jpg','中级',92,'007','朱琳','106','朱琳','退费说明3','2026-04-11 14:38:28','是','','已支付'),(4,'2026-04-11 06:38:28','GK019','计算机网络','计算机科学','upload/tuifeishenqing_计算机网络1.jpg,upload/tuifeishenqing_计算机网络2.jpg,upload/tuifeishenqing_计算机网络3.jpg','高级',150,'008','杨洁','108','杨洁','退费说明4','2026-04-11 14:38:28','是','','已支付'),(5,'2026-04-11 06:38:28','GK002','区块链技术','计算机科学','upload/tuifeishenqing_区块链技术1.jpg,upload/tuifeishenqing_区块链技术2.jpg,upload/tuifeishenqing_区块链技术3.jpg','中级',135,'001','吴磊','104','吴磊','退费说明5','2026-04-11 14:38:28','是','','已支付'),(6,'2026-04-11 06:38:28','GK010','编译原理','计算机科学','upload/tuifeishenqing_编译原理1.jpg,upload/tuifeishenqing_编译原理2.jpg,upload/tuifeishenqing_编译原理3.jpg','高级',90,'003','郑晓琳','103','郑晓琳','退费说明6','2026-04-11 14:38:28','是','','已支付'),(7,'2026-04-11 06:38:28','GK001','信息安全','计算机科学','upload/tuifeishenqing_信息安全1.jpg,upload/tuifeishenqing_信息安全2.jpg,upload/tuifeishenqing_信息安全3.jpg','中级',89,'004','王若曦','102','王若曦','退费说明7','2026-04-11 14:38:28','是','','已支付'),(8,'2026-04-11 06:38:28','GK011','大数据技术','计算机科学','upload/tuifeishenqing_大数据技术1.jpg,upload/tuifeishenqing_大数据技术2.jpg,upload/tuifeishenqing_大数据技术3.jpg','中级',83,'006','刘芳','107','刘芳','退费说明8','2026-04-11 14:38:28','是','','已支付');
/*!40000 ALTER TABLE `tuifeishenqing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `username` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `role` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '管理员' COMMENT '角色',
  `image` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='管理员';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'2026-04-11 06:38:29','admin','admin','管理员','upload/image1.jpg');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xuankexinxi`
--

DROP TABLE IF EXISTS `xuankexinxi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xuankexinxi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `kechenghao` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程号',
  `kechengmingcheng` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程名称',
  `kechengfenlei` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程分类',
  `tupian` longtext COLLATE utf8mb4_unicode_ci COMMENT '图片',
  `nandu` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '难度',
  `keshi` double DEFAULT NULL COMMENT '剩余课时',
  `feiyong` double DEFAULT NULL COMMENT '费用',
  `xuehao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学号',
  `xingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '姓名',
  `gonghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '工号',
  `jiaoshixingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '教师姓名',
  `xuankeshijian` datetime DEFAULT NULL COMMENT '选课时间',
  `crossuserid` bigint(20) DEFAULT NULL COMMENT '跨表用户id',
  `crossrefid` bigint(20) DEFAULT NULL COMMENT '跨表主键id',
  `sfsh` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '待审核' COMMENT '是否审核',
  `shhf` longtext COLLATE utf8mb4_unicode_ci COMMENT '审核回复',
  `ispay` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '未支付' COMMENT '是否支付',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='选课信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xuankexinxi`
--

LOCK TABLES `xuankexinxi` WRITE;
/*!40000 ALTER TABLE `xuankexinxi` DISABLE KEYS */;
INSERT INTO `xuankexinxi` VALUES (1,'2026-04-11 06:38:28','GK003','数据库系统','计算机科学','upload/xuankexinxi_数据库系统1.jpg,upload/xuankexinxi_数据库系统2.jpg,upload/xuankexinxi_数据库系统3.jpg','中级',53,130,'005','徐天宇','101','徐天宇','2026-04-11 14:38:28',1,1,'是','','已支付'),(2,'2026-04-11 06:38:28','GK005','软件工程','计算机科学','upload/xuankexinxi_软件工程1.jpg,upload/xuankexinxi_软件工程2.jpg,upload/xuankexinxi_软件工程3.jpg','中级',72,142,'002','欧阳晨','105','欧阳晨','2026-04-11 14:38:28',2,2,'是','','已支付'),(3,'2026-04-11 06:38:28','GK013','计算机图形学','计算机科学','upload/xuankexinxi_计算机图形学1.jpg,upload/xuankexinxi_计算机图形学2.jpg,upload/xuankexinxi_计算机图形学3.jpg','中级',44,92,'007','朱琳','106','朱琳','2026-04-11 14:38:28',3,3,'是','','已支付'),(4,'2026-04-11 06:38:28','GK019','计算机网络','计算机科学','upload/xuankexinxi_计算机网络1.jpg,upload/xuankexinxi_计算机网络2.jpg,upload/xuankexinxi_计算机网络3.jpg','高级',64,150,'008','杨洁','108','杨洁','2026-04-11 14:38:28',4,4,'是','','已支付'),(5,'2026-04-11 06:38:28','GK002','区块链技术','计算机科学','upload/xuankexinxi_区块链技术1.jpg,upload/xuankexinxi_区块链技术2.jpg,upload/xuankexinxi_区块链技术3.jpg','中级',58,135,'001','吴磊','104','吴磊','2026-04-11 14:38:28',5,5,'是','','已支付'),(6,'2026-04-11 06:38:28','GK010','编译原理','计算机科学','upload/xuankexinxi_编译原理1.jpg,upload/xuankexinxi_编译原理2.jpg,upload/xuankexinxi_编译原理3.jpg','高级',28,90,'003','郑晓琳','103','郑晓琳','2026-04-11 14:38:28',6,6,'是','','已支付'),(7,'2026-04-11 06:38:28','GK001','信息安全','计算机科学','upload/xuankexinxi_信息安全1.jpg,upload/xuankexinxi_信息安全2.jpg,upload/xuankexinxi_信息安全3.jpg','中级',68,89,'004','王若曦','102','王若曦','2026-04-11 14:38:28',7,7,'是','','已支付'),(8,'2026-04-11 06:38:28','GK011','大数据技术','计算机科学','upload/xuankexinxi_大数据技术1.jpg,upload/xuankexinxi_大数据技术2.jpg,upload/xuankexinxi_大数据技术3.jpg','中级',29,83,'006','刘芳','107','刘芳','2026-04-11 14:38:28',8,8,'是','','已支付');
/*!40000 ALTER TABLE `xuankexinxi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xuesheng`
--

DROP TABLE IF EXISTS `xuesheng`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xuesheng` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `xuehao` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '学号',
  `mima` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `xingming` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '姓名',
  `touxiang` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  `xingbie` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性别',
  `nianling` int(11) DEFAULT NULL COMMENT '年龄',
  `mubiaoyuanxiao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '目标院校',
  `banji` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '班级',
  `beikaokemu` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备考科目',
  `jichushuiping` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '基础水平',
  `mobile` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机号',
  `pquestion` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密保问题',
  `panswer` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密保答案',
  `status` int(11) DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `xuehao` (`xuehao`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='学生';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xuesheng`
--

LOCK TABLES `xuesheng` WRITE;
/*!40000 ALTER TABLE `xuesheng` DISABLE KEYS */;
INSERT INTO `xuesheng` VALUES (11,'2026-04-11 06:38:28','005','123456','徐天宇','upload/xuesheng_touxiang1.jpg','男',1,'理学院','班级1','政治','基础水平1','13823888881','密保问题1','密保答案1',0),(12,'2026-04-11 06:38:28','002','123456','欧阳晨','upload/xuesheng_touxiang2.jpg','男',2,'教育学院','班级2','英语一','基础水平2','13823888882','密保问题2','密保答案2',0),(13,'2026-04-11 06:38:28','007','123456','朱琳','upload/xuesheng_touxiang3.jpg','男',3,'外国语学院','班级3','英语一','基础水平3','13823888883','密保问题3','密保答案3',0),(14,'2026-04-11 06:38:28','008','123456','杨洁','upload/xuesheng_touxiang4.jpg','男',4,'国际教育学院','班级4','数学三','基础水平4','13823888884','密保问题4','密保答案4',0),(15,'2026-04-11 06:38:28','001','123456','吴磊','upload/xuesheng_touxiang5.jpg','男',5,'文学院','班级5','数学一','基础水平5','13823888885','密保问题5','密保答案5',0),(16,'2026-04-11 06:38:28','003','123456','郑晓琳','upload/xuesheng_touxiang6.jpg','男',6,'海洋学院','班级6','数学三','基础水平6','13823888886','密保问题6','密保答案6',0),(17,'2026-04-11 06:38:28','004','123456','王若曦','upload/xuesheng_touxiang7.jpg','男',7,'商学院','班级7','数学一','基础水平7','13823888887','密保问题7','密保答案7',0),(18,'2026-04-11 06:38:28','006','123456','刘芳','upload/xuesheng_touxiang8.jpg','男',8,'医学院','班级8','政治','基础水平8','13823888888','密保问题8','密保答案8',0);
/*!40000 ALTER TABLE `xuesheng` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xuexijindu`
--

DROP TABLE IF EXISTS `xuexijindu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xuexijindu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `kechenghao` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程号',
  `kechengmingcheng` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程名称',
  `kechengfenlei` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程分类',
  `tupian` longtext COLLATE utf8mb4_unicode_ci COMMENT '图片',
  `nandu` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '难度',
  `zuoyewanchenglv` double NOT NULL COMMENT '作业完成率',
  `zhishizhangwoqingkuang` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '知识掌握情况',
  `xuehao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学号',
  `xingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '姓名',
  `gonghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '工号',
  `jiaoshixingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '教师姓名',
  `xuexishijian` datetime DEFAULT NULL COMMENT '学习时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='学习进度';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xuexijindu`
--

LOCK TABLES `xuexijindu` WRITE;
/*!40000 ALTER TABLE `xuexijindu` DISABLE KEYS */;
INSERT INTO `xuexijindu` VALUES (1,'2026-04-11 06:38:28','GK003','数据库系统','计算机科学','upload/xuexijindu_数据库系统1.jpg,upload/xuexijindu_数据库系统2.jpg,upload/xuexijindu_数据库系统3.jpg','中级',1,'知识掌握情况1','005','徐天宇','101','徐天宇','2026-04-11 14:38:28'),(2,'2026-04-11 06:38:28','GK005','软件工程','计算机科学','upload/xuexijindu_软件工程1.jpg,upload/xuexijindu_软件工程2.jpg,upload/xuexijindu_软件工程3.jpg','中级',2,'知识掌握情况2','002','欧阳晨','105','欧阳晨','2026-04-11 14:38:28'),(3,'2026-04-11 06:38:28','GK013','计算机图形学','计算机科学','upload/xuexijindu_计算机图形学1.jpg,upload/xuexijindu_计算机图形学2.jpg,upload/xuexijindu_计算机图形学3.jpg','中级',3,'知识掌握情况3','007','朱琳','106','朱琳','2026-04-11 14:38:28'),(4,'2026-04-11 06:38:28','GK019','计算机网络','计算机科学','upload/xuexijindu_计算机网络1.jpg,upload/xuexijindu_计算机网络2.jpg,upload/xuexijindu_计算机网络3.jpg','高级',4,'知识掌握情况4','008','杨洁','108','杨洁','2026-04-11 14:38:28'),(5,'2026-04-11 06:38:28','GK002','区块链技术','计算机科学','upload/xuexijindu_区块链技术1.jpg,upload/xuexijindu_区块链技术2.jpg,upload/xuexijindu_区块链技术3.jpg','中级',5,'知识掌握情况5','001','吴磊','104','吴磊','2026-04-11 14:38:28'),(6,'2026-04-11 06:38:28','GK010','编译原理','计算机科学','upload/xuexijindu_编译原理1.jpg,upload/xuexijindu_编译原理2.jpg,upload/xuexijindu_编译原理3.jpg','高级',6,'知识掌握情况6','003','郑晓琳','103','郑晓琳','2026-04-11 14:38:28'),(7,'2026-04-11 06:38:28','GK001','信息安全','计算机科学','upload/xuexijindu_信息安全1.jpg,upload/xuexijindu_信息安全2.jpg,upload/xuexijindu_信息安全3.jpg','中级',7,'知识掌握情况7','004','王若曦','102','王若曦','2026-04-11 14:38:28'),(8,'2026-04-11 06:38:28','GK011','大数据技术','计算机科学','upload/xuexijindu_大数据技术1.jpg,upload/xuexijindu_大数据技术2.jpg,upload/xuexijindu_大数据技术3.jpg','中级',8,'知识掌握情况8','006','刘芳','107','刘芳','2026-04-11 14:38:28');
/*!40000 ALTER TABLE `xuexijindu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zuoyepigai`
--

DROP TABLE IF EXISTS `zuoyepigai`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zuoyepigai` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `zuoyemingcheng` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '作业名称',
  `kemu` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '科目',
  `tupian` longtext COLLATE utf8mb4_unicode_ci COMMENT '图片',
  `xuehao` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学号',
  `xingming` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '姓名',
  `chengji` int(11) NOT NULL COMMENT '成绩',
  `pingyu` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评语',
  `pigaizhuangkuang` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '批改状况',
  `pigaishijian` datetime DEFAULT NULL COMMENT '批改时间',
  `gonghao` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '工号',
  `jiaoshixingming` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '教师姓名',
  `crossuserid` bigint(20) DEFAULT NULL COMMENT '跨表用户id',
  `crossrefid` bigint(20) DEFAULT NULL COMMENT '跨表主键id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='作业批改';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zuoyepigai`
--

LOCK TABLES `zuoyepigai` WRITE;
/*!40000 ALTER TABLE `zuoyepigai` DISABLE KEYS */;
INSERT INTO `zuoyepigai` VALUES (1,'2026-04-11 06:38:28','数据库系统','计算机科学','upload/zuoyepigai_数据库系统1.jpg,upload/zuoyepigai_数据库系统2.jpg,upload/zuoyepigai_数据库系统3.jpg','005','徐天宇',1,'评语1','批改状况1','2026-04-11 14:38:28','101','徐天宇',1,1),(2,'2026-04-11 06:38:28','软件工程','计算机科学','upload/zuoyepigai_软件工程1.jpg,upload/zuoyepigai_软件工程2.jpg,upload/zuoyepigai_软件工程3.jpg','002','欧阳晨',2,'评语2','批改状况2','2026-04-11 14:38:28','105','欧阳晨',2,2),(3,'2026-04-11 06:38:28','计算机图形学','计算机科学','upload/zuoyepigai_计算机图形学1.jpg,upload/zuoyepigai_计算机图形学2.jpg,upload/zuoyepigai_计算机图形学3.jpg','007','朱琳',3,'评语3','批改状况3','2026-04-11 14:38:28','106','朱琳',3,3),(4,'2026-04-11 06:38:28','计算机网络','计算机科学','upload/zuoyepigai_计算机网络1.jpg,upload/zuoyepigai_计算机网络2.jpg,upload/zuoyepigai_计算机网络3.jpg','008','杨洁',4,'评语4','批改状况4','2026-04-11 14:38:28','108','杨洁',4,4),(5,'2026-04-11 06:38:28','区块链技术','计算机科学','upload/zuoyepigai_区块链技术1.jpg,upload/zuoyepigai_区块链技术2.jpg,upload/zuoyepigai_区块链技术3.jpg','001','吴磊',5,'评语5','批改状况5','2026-04-11 14:38:28','104','吴磊',5,5),(6,'2026-04-11 06:38:28','编译原理','计算机科学','upload/zuoyepigai_编译原理1.jpg,upload/zuoyepigai_编译原理2.jpg,upload/zuoyepigai_编译原理3.jpg','003','郑晓琳',6,'评语6','批改状况6','2026-04-11 14:38:28','103','郑晓琳',6,6),(7,'2026-04-11 06:38:28','信息安全','计算机科学','upload/zuoyepigai_信息安全1.jpg,upload/zuoyepigai_信息安全2.jpg,upload/zuoyepigai_信息安全3.jpg','004','王若曦',7,'评语7','批改状况7','2026-04-11 14:38:28','102','王若曦',7,7),(8,'2026-04-11 06:38:28','大数据技术','计算机科学','upload/zuoyepigai_大数据技术1.jpg,upload/zuoyepigai_大数据技术2.jpg,upload/zuoyepigai_大数据技术3.jpg','006','刘芳',8,'评语8','批改状况8','2026-04-11 14:38:28','107','刘芳',8,8);
/*!40000 ALTER TABLE `zuoyepigai` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zuoyetijiao`
--

DROP TABLE IF EXISTS `zuoyetijiao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zuoyetijiao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `zuoyemingcheng` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '作业名称',
  `kemu` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '科目',
  `tupian` longtext COLLATE utf8mb4_unicode_ci COMMENT '图片',
  `gonghao` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '工号',
  `jiaoshixingming` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '教师姓名',
  `tijiaozuoye` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '提交作业',
  `tijiaoshijian` datetime DEFAULT NULL COMMENT '提交时间',
  `xuehao` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学号',
  `xingming` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '姓名',
  `crossuserid` bigint(20) DEFAULT NULL COMMENT '跨表用户id',
  `crossrefid` bigint(20) DEFAULT NULL COMMENT '跨表主键id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='作业提交';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zuoyetijiao`
--

LOCK TABLES `zuoyetijiao` WRITE;
/*!40000 ALTER TABLE `zuoyetijiao` DISABLE KEYS */;
INSERT INTO `zuoyetijiao` VALUES (1,'2026-04-11 06:38:28','数据库系统','计算机科学','upload/zuoyetijiao_数据库系统1.jpg,upload/zuoyetijiao_数据库系统2.jpg,upload/zuoyetijiao_数据库系统3.jpg','101','徐天宇','','2026-04-11 14:38:28','005','徐天宇',1,1),(2,'2026-04-11 06:38:28','软件工程','计算机科学','upload/zuoyetijiao_软件工程1.jpg,upload/zuoyetijiao_软件工程2.jpg,upload/zuoyetijiao_软件工程3.jpg','105','欧阳晨','','2026-04-11 14:38:28','002','欧阳晨',2,2),(3,'2026-04-11 06:38:28','计算机图形学','计算机科学','upload/zuoyetijiao_计算机图形学1.jpg,upload/zuoyetijiao_计算机图形学2.jpg,upload/zuoyetijiao_计算机图形学3.jpg','106','朱琳','','2026-04-11 14:38:28','007','朱琳',3,3),(4,'2026-04-11 06:38:28','计算机网络','计算机科学','upload/zuoyetijiao_计算机网络1.jpg,upload/zuoyetijiao_计算机网络2.jpg,upload/zuoyetijiao_计算机网络3.jpg','108','杨洁','','2026-04-11 14:38:28','008','杨洁',4,4),(5,'2026-04-11 06:38:28','区块链技术','计算机科学','upload/zuoyetijiao_区块链技术1.jpg,upload/zuoyetijiao_区块链技术2.jpg,upload/zuoyetijiao_区块链技术3.jpg','104','吴磊','','2026-04-11 14:38:28','001','吴磊',5,5),(6,'2026-04-11 06:38:28','编译原理','计算机科学','upload/zuoyetijiao_编译原理1.jpg,upload/zuoyetijiao_编译原理2.jpg,upload/zuoyetijiao_编译原理3.jpg','103','郑晓琳','','2026-04-11 14:38:28','003','郑晓琳',6,6),(7,'2026-04-11 06:38:28','信息安全','计算机科学','upload/zuoyetijiao_信息安全1.jpg,upload/zuoyetijiao_信息安全2.jpg,upload/zuoyetijiao_信息安全3.jpg','102','王若曦','','2026-04-11 14:38:28','004','王若曦',7,7),(8,'2026-04-11 06:38:28','大数据技术','计算机科学','upload/zuoyetijiao_大数据技术1.jpg,upload/zuoyetijiao_大数据技术2.jpg,upload/zuoyetijiao_大数据技术3.jpg','107','刘芳','','2026-04-11 14:38:28','006','刘芳',8,8);
/*!40000 ALTER TABLE `zuoyetijiao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zuoyexinxi`
--

DROP TABLE IF EXISTS `zuoyexinxi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zuoyexinxi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `zuoyemingcheng` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '作业名称',
  `kemu` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '科目',
  `tupian` longtext COLLATE utf8mb4_unicode_ci COMMENT '图片',
  `zuoyewenjian` longtext COLLATE utf8mb4_unicode_ci COMMENT '作业文件',
  `zuoyeyaoqiu` longtext COLLATE utf8mb4_unicode_ci COMMENT '作业要求',
  `gonghao` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '工号',
  `jiaoshixingming` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '教师姓名',
  `reversetime` datetime DEFAULT NULL COMMENT '倒计结束时间',
  PRIMARY KEY (`id`),
  KEY `gonghao` (`gonghao`,`jiaoshixingming`),
  CONSTRAINT `zuoyexinxi_ibfk_1` FOREIGN KEY (`gonghao`, `jiaoshixingming`) REFERENCES `jiaoshi` (`gonghao`, `jiaoshixingming`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='作业信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zuoyexinxi`
--

LOCK TABLES `zuoyexinxi` WRITE;
/*!40000 ALTER TABLE `zuoyexinxi` DISABLE KEYS */;
INSERT INTO `zuoyexinxi` VALUES (1,'2026-04-11 06:38:28','数据库系统','计算机科学','upload/zuoyexinxi_数据库系统1.jpg,upload/zuoyexinxi_数据库系统2.jpg,upload/zuoyexinxi_数据库系统3.jpg','','1.关系模型 2.SQL 3.索引','101','徐天宇','2026-04-12 00:00:00'),(2,'2026-04-11 06:38:28','软件工程','计算机科学','upload/zuoyexinxi_软件工程1.jpg,upload/zuoyexinxi_软件工程2.jpg,upload/zuoyexinxi_软件工程3.jpg','','1.需求分析 2.设计 3.测试','105','欧阳晨','2026-04-12 00:00:00'),(3,'2026-04-11 06:38:28','计算机图形学','计算机科学','upload/zuoyexinxi_计算机图形学1.jpg,upload/zuoyexinxi_计算机图形学2.jpg,upload/zuoyexinxi_计算机图形学3.jpg','','1.渲染 2.建模 3.动画','106','朱琳','2026-04-12 00:00:00'),(4,'2026-04-11 06:38:28','计算机网络','计算机科学','upload/zuoyexinxi_计算机网络1.jpg,upload/zuoyexinxi_计算机网络2.jpg,upload/zuoyexinxi_计算机网络3.jpg','','1.OSI模型 2.TCP/IP 3.路由','108','杨洁','2026-04-12 00:00:00'),(5,'2026-04-11 06:38:28','区块链技术','计算机科学','upload/zuoyexinxi_区块链技术1.jpg,upload/zuoyexinxi_区块链技术2.jpg,upload/zuoyexinxi_区块链技术3.jpg','','1.共识 2.智能合约 3.DApp','104','吴磊','2026-04-12 00:00:00'),(6,'2026-04-11 06:38:28','编译原理','计算机科学','upload/zuoyexinxi_编译原理1.jpg,upload/zuoyexinxi_编译原理2.jpg,upload/zuoyexinxi_编译原理3.jpg','','1.词法分析 2.语法分析 3.优化','103','郑晓琳','2026-04-12 00:00:00'),(7,'2026-04-11 06:38:28','信息安全','计算机科学','upload/zuoyexinxi_信息安全1.jpg,upload/zuoyexinxi_信息安全2.jpg,upload/zuoyexinxi_信息安全3.jpg','','1.加密 2.防火墙 3.渗透测试','102','王若曦','2026-04-12 00:00:00'),(8,'2026-04-11 06:38:28','大数据技术','计算机科学','upload/zuoyexinxi_大数据技术1.jpg,upload/zuoyexinxi_大数据技术2.jpg,upload/zuoyexinxi_大数据技术3.jpg','','1.Hadoop 2.Spark 3.数据挖掘','107','刘芳','2026-04-12 00:00:00');
/*!40000 ALTER TABLE `zuoyexinxi` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-17 18:22:37
