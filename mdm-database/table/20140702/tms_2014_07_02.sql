-- MySQL dump 10.13  Distrib 5.6.15, for Win64 (x86_64)
--
-- Host: 10.57.40.106    Database: tms
-- ------------------------------------------------------
-- Server version	5.5.27

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
-- Table structure for table `security_login_failed_log`
--

DROP TABLE IF EXISTS `security_login_failed_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_login_failed_log` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '登录失败日志ID',
  `username` varchar(32) DEFAULT NULL COMMENT '用户名',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `IP` varchar(32) DEFAULT NULL COMMENT 'IP地址',
  `insert_date` datetime DEFAULT NULL COMMENT '插入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `security_login_succeed_log`
--

DROP TABLE IF EXISTS `security_login_succeed_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_login_succeed_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `username` varchar(32) DEFAULT NULL COMMENT '用户名',
  `IP` varchar(32) DEFAULT NULL COMMENT 'IP地址',
  `insert_date` datetime DEFAULT NULL COMMENT '插入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `security_operation_log`
--

DROP TABLE IF EXISTS `security_operation_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_operation_log` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '操作日志ID',
  `title` varchar(64) DEFAULT NULL COMMENT '标题',
  `username` varchar(32) DEFAULT NULL COMMENT '用户名',
  `content` varchar(512) DEFAULT NULL COMMENT '操作内容',
  `IP` varchar(32) DEFAULT NULL COMMENT 'IP地址',
  `insert_date` datetime DEFAULT NULL COMMENT '插入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `security_permission`
--

DROP TABLE IF EXISTS `security_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_permission` (
  `SP_ID` int(11) DEFAULT NULL COMMENT 'ID',
  `SP_NAME` varchar(20) DEFAULT NULL COMMENT '资源名称',
  `SP_PARENT_ID` int(11) DEFAULT NULL COMMENT '父类ID',
  `SP_DESC` varchar(20) DEFAULT NULL COMMENT '描述',
  `HREF` varchar(100) DEFAULT NULL COMMENT 'URL',
  `TARGET` varchar(20) DEFAULT NULL COMMENT '打开方式',
  `ICON` varchar(20) DEFAULT NULL COMMENT '图标',
  `SP_VISIBLE` varchar(20) DEFAULT NULL COMMENT '是否显示',
  `SP_TYPE` varchar(20) DEFAULT NULL COMMENT '类型',
  `PERMISSION` varchar(50) DEFAULT NULL COMMENT '权限',
  `VERSION` varchar(1) DEFAULT NULL COMMENT '版本号',
  `RANK` bigint(20) DEFAULT NULL COMMENT '排序',
  `SYS_ID` bigint(20) DEFAULT NULL COMMENT '系统ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `security_role`
--

DROP TABLE IF EXISTS `security_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_role` (
  `sr_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `sr_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `sr_description` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `insert_by` varchar(50) DEFAULT NULL COMMENT '插入人',
  `insert_date` datetime DEFAULT NULL COMMENT '插入时间',
  `modified_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `modified_date` datetime DEFAULT NULL COMMENT '更新时间',
  `buid` int(11) DEFAULT NULL COMMENT 'BUID号',
  `SYS_ID` int(11) DEFAULT NULL COMMENT '系统ID',
  PRIMARY KEY (`sr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=312030 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `security_role_permission`
--

DROP TABLE IF EXISTS `security_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_role_permission` (
  `SR_ID` int(11) NOT NULL COMMENT '角色ID',
  `SP_ID` int(11) NOT NULL COMMENT '资源ID',
  PRIMARY KEY (`SR_ID`,`SP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `security_user`
--

DROP TABLE IF EXISTS `security_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_user` (
  `su_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID号',
  `su_account` varchar(50) DEFAULT NULL COMMENT '账户',
  `su_name` varchar(50) DEFAULT NULL COMMENT '名称',
  `su_email` varchar(50) DEFAULT NULL COMMENT 'E-mail',
  `su_extension` varchar(30) DEFAULT NULL COMMENT '电话分机',
  `su_segment` varchar(50) DEFAULT NULL,
  `su_active` varchar(1) DEFAULT 'Y' COMMENT '失效标识，Y为有效，N为失效',
  `su_admin_flag` varchar(1) DEFAULT 'N' COMMENT '是否为管理员',
  `su_remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `su_password` varchar(50) DEFAULT NULL COMMENT '密码',
  `su_lsp_code` varchar(10) DEFAULT NULL COMMENT 'lsp代码',
  `su_domain` varchar(20) DEFAULT NULL COMMENT '域',
  `su_roles` varchar(200) DEFAULT NULL COMMENT '角色',
  `su_title` varchar(50) DEFAULT NULL COMMENT '标题',
  `su_is_employee` varchar(1) DEFAULT 'Y' COMMENT '是否为企业员工',
  `su_manager_email` varchar(50) DEFAULT NULL COMMENT '管理员Email',
  `su_enabled` int(11) DEFAULT '1' COMMENT '是否激活',
  `insert_by` varchar(50) DEFAULT NULL COMMENT '插入人',
  `insert_date` datetime DEFAULT NULL COMMENT '插入时间',
  `modified_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `modified_date` datetime DEFAULT NULL COMMENT '更新时间',
  `su_timezone` varchar(100) DEFAULT NULL COMMENT '时区',
  `su_language` varchar(32) DEFAULT NULL COMMENT '语言',
  `su_default_buid` int(11) DEFAULT NULL COMMENT '默认buid',
  `su_country_code` varchar(3) DEFAULT NULL COMMENT '国家编码',
  `su_default_area_code` varchar(10) DEFAULT NULL COMMENT '区域编码',
  `last_login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `has_changed_pswd` varchar(1) DEFAULT 'N',
  `invalid_attampts` int(11) DEFAULT '0',
  `lockout_time` datetime DEFAULT NULL COMMENT '锁定时间',
  `mas_loc` varchar(8) DEFAULT NULL COMMENT '仓库',
  `rf_login_count` int(11) DEFAULT '0' COMMENT '登录次数统计',
  `login_status` varchar(4) DEFAULT 'N' COMMENT '登录状态',
  `telephone_num` varchar(32) DEFAULT NULL COMMENT '电话号码',
  `sex` bit(1) DEFAULT NULL COMMENT '性别',
  `effect_date` datetime DEFAULT NULL COMMENT '作用时间',
  `expire_date` datetime DEFAULT NULL COMMENT '终止时间',
  `employee_no` varchar(10) DEFAULT NULL COMMENT '部门编号',
  PRIMARY KEY (`su_id`)
) ENGINE=InnoDB AUTO_INCREMENT=318989 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `security_user_role`
--

DROP TABLE IF EXISTS `security_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_user_role` (
  `su_id` int(11) NOT NULL COMMENT '用户ID',
  `sr_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`su_id`,`sr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `system_model`
--

DROP TABLE IF EXISTS `system_model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system_model` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(32) NOT NULL COMMENT '名称',
  `description` varchar(255) DEFAULT NULL COMMENT '系统描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-02 12:01:59
