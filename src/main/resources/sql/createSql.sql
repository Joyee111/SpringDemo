-- MySQL dump 10.13  Distrib 5.5.28, for Win32 (x86)
--
-- Host: localhost    Database: joyeeblog
-- ------------------------------------------------------
-- Server version        5.5.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sy_comm_blog`
--

DROP TABLE IF EXISTS `sy_comm_blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sy_comm_blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(300) DEFAULT NULL,
  `S_ATIME` datetime DEFAULT NULL,
  `author` varchar(30) DEFAULT NULL,
  `description` varchar(600) DEFAULT NULL,
  `imgUrl` varchar(600) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='blog表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sy_comm_blog`
--

LOCK TABLES `sy_comm_blog` WRITE;
/*!40000 ALTER TABLE `sy_comm_blog` DISABLE KEYS */;
INSERT INTO `sy_comm_blog` VALUES (1,'demo2','2019-12-28 17:46:45','admin','第二个demo','/assets/img/微信图片_20190912211336.jpg'),(2,'demo2','2019-12-28 17:47:24','admin','第二个demo','/assets/img/asd.png'),(3,'第三个demo','2019-12-28 18:46:38','admin','关于测试环境的发布问题','/assets/img/1578ED901859022F122C8ED881C21F69.jpg'),(4,'第四个demo','2019-12-28 18:47:28','admin','关于zookeeper的问题','/assets/img/Steam.jpg'),(5,'第5个demo','2019-12-28 18:48:00','admin','关于docker的问题','/assets/img/屏幕截图(2).png'),(6,'测试日志','2019-12-29 23:10:12','admin','测试内容','/assets/img/屏幕截图(4).png'),(7,'AAA','2019-12-29 23:42:59','admin','123','/assets/img/屏幕截图(1).png'),(8,'Spring','2020-01-04 22:29:11','admin','项目','/assets/img/屏幕截图(1).png'),(9,'sdf','2020-05-17 21:20:13','admin','fff','/assets/img/aaa.jpg'),(10,'测试2','2020-05-17 21:47:08','admin','测试detail','/assets/img/微信图片_20180403090606.jpg'),(11,'ceshi0518','2020-05-18 01:53:39','admin','测试0518','/assets/img/微信图片_20180403090557.jpg'),(12,'测试返回刷新','2020-05-18 01:55:38','admin','走缓存数据·','/assets/img/zhang.jpg'),(13,'测试上传删除缓存','2020-05-18 02:16:14','admin','删除并关闭页面','/assets/img/未标题-1.jpg'),(14,'111','2020-05-18 02:17:17','admin','222','/assets/img/一寸照2.jpg'),(15,'111','2020-05-18 02:18:41','admin','222','/assets/img/587011819448126025.jpg'),(16,'ggg','2020-05-18 02:21:42','admin','sdaf','/assets/img/83780510027475382.jpg'),(17,'sdf','2020-05-18 02:22:17','admin','asdf','/assets/img/一寸照2.jpg'),(18,'g','2020-05-18 02:23:21','admin','sd','/assets/img/587011819448126025.jpg'),(19,'asdf','2020-05-18 02:23:44','admin','ff','/assets/img/未标题-1.jpg'),(20,'fa','2020-05-18 02:24:13','admin','dd','/assets/img/户口复印件.jpg'),(21,'asdf','2020-05-18 02:25:10','admin','ffd','/assets/img/户口复印件.jpg'),(22,'gaaasdfasdf','2020-05-18 02:26:20','admin','agasdfasdf','/assets/img/未标题-1.jpg'),(23,'e5rt','2020-05-18 02:27:04','admin','wert','/assets/img/一寸照2.jpg'),(24,'4444444444444','2020-05-18 02:31:59','admin','3333333333333','/assets/img/未标题-1.jpg'),(25,'ghads','2020-05-18 02:37:08','admin','1111111111111111','/assets/img/428757894055403568.jpg'),(26,'22222222222222','2020-05-18 02:37:52','admin','122222222222','/assets/img/一寸照2.jpg'),(27,'测试缓存','2020-05-18 02:40:21','admin','弄完睡觉','/assets/img/未标题-1.jpg'),(28,'踩踩踩','2020-05-18 02:41:45','admin','啊啊啊','/assets/img/587011819448126025.jpg'),(29,'安安','2020-05-18 02:42:23','admin','对对对','/assets/img/83780510027475382.jpg'),(30,'阿斯蒂','2020-05-18 02:44:21','admin','阿斯蒂','/assets/img/未标题-1.jpg'),(31,'和人','2020-05-18 02:44:43','admin','地方干','/assets/img/未标题-1.jpg');
/*!40000 ALTER TABLE `sy_comm_blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sy_org_role`
--

DROP TABLE IF EXISTS `sy_org_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sy_org_role` (
  `role_code` int(11) NOT NULL DEFAULT '0',
  `role_name` varchar(200) DEFAULT NULL,
  `user_code` int(11) DEFAULT NULL,
  `s_flag` int(11) DEFAULT NULL,
  `s_mtime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`role_code`),
  KEY `sy_org_role_sy_org_user_user_code_fk` (`user_code`),
  CONSTRAINT `sy_org_role_sy_org_user_user_code_fk` FOREIGN KEY (`user_code`) REFERENCES `sy_org_user` (`user_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sy_org_role`
--

LOCK TABLES `sy_org_role` WRITE;
/*!40000 ALTER TABLE `sy_org_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `sy_org_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sy_org_user`
--

DROP TABLE IF EXISTS `sy_org_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sy_org_user` (
  `user_code` int(11) NOT NULL DEFAULT '0',
  `user_name` varchar(50) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `mobile_phone` varchar(50) DEFAULT NULL,
  `Email_address` varchar(200) DEFAULT NULL,
  `s_flag` int(11) DEFAULT NULL,
  `s_mtime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`user_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sy_org_user`
--

LOCK TABLES `sy_org_user` WRITE;
/*!40000 ALTER TABLE `sy_org_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `sy_org_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sy_user_act`
--

DROP TABLE IF EXISTS `sy_user_act`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sy_user_act` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_code` int(11) NOT NULL,
  `act_interface` varchar(500) DEFAULT NULL,
  `act_parameters` varchar(500) DEFAULT NULL,
  `act_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sy_user_act_sy_org_user_user_code_fk` (`user_code`),
  CONSTRAINT `sy_user_act_sy_org_user_user_code_fk` FOREIGN KEY (`user_code`) REFERENCES `sy_org_user` (`user_code`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='记录用户行为数据';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sy_user_act`
--

LOCK TABLES `sy_user_act` WRITE;
/*!40000 ALTER TABLE `sy_user_act` DISABLE KEYS */;
/*!40000 ALTER TABLE `sy_user_act` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-19 15:02:32