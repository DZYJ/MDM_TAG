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
-- Table structure for table `data_dictionary`
--

DROP TABLE IF EXISTS `data_dictionary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data_dictionary` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `ENGLISH_NAME` varchar(100) DEFAULT NULL COMMENT '字段英文名称',
  `CHINESE_NAME` varchar(100) DEFAULT NULL COMMENT '字段中文名称',
  `INDICATE_NAME` varchar(100) DEFAULT NULL COMMENT '字段标示名称',
  `COLUMN_VALUE` varchar(100) DEFAULT NULL COMMENT '字段值',
  `ISACTIVITY` varchar(1) DEFAULT NULL COMMENT '是否活动 Y||N 是或者不是',
  `CREATE_PEOPLE` varchar(20) DEFAULT NULL COMMENT '创建人',
  `insertDate` datetime DEFAULT NULL COMMENT '创建时间',
  `ALTER_PEOPLE` varchar(20) DEFAULT NULL COMMENT '修改人',
  `modifiedDate` datetime DEFAULT NULL COMMENT '修改时间',
  `COMMENT` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ftp_lsp_header`
--

DROP TABLE IF EXISTS `ftp_lsp_header`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ftp_lsp_header` (
  `BUID` double NOT NULL COMMENT '销售渠道ID 销售渠道ID：如8270',
  `IP` varchar(80) DEFAULT NULL COMMENT '承运商FTP的IP',
  `PORT` varchar(60) DEFAULT NULL COMMENT '承运商FTP的端口',
  `LSP_CODE` varchar(60) NOT NULL COMMENT '承运商编码',
  `LSP_ABBREVIATION` varchar(60) DEFAULT NULL COMMENT '承运商缩写',
  `FTP_USER` varchar(512) DEFAULT NULL COMMENT '承运商FTP用户名',
  `FTP_PASS` varchar(512) DEFAULT NULL COMMENT '承运商FTP密码',
  `INSERT_BY` varchar(200) DEFAULT NULL COMMENT '插入者',
  `INSERT_DATE` datetime DEFAULT NULL COMMENT '插入时间',
  `MODIFIED_BY` varchar(200) DEFAULT NULL COMMENT '更新者',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '更新时间',
  `LSP_ENTRY` varchar(512) DEFAULT NULL COMMENT '承运商FTP逻辑',
  PRIMARY KEY (`BUID`,`LSP_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='承运商FTP配置FTP_LSP_HEADER';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hub_process_matrix`
--

DROP TABLE IF EXISTS `hub_process_matrix`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hub_process_matrix` (
  `ID` double NOT NULL,
  `RULE_NAME` varchar(200) DEFAULT NULL,
  `COLUMN1` varchar(800) DEFAULT NULL,
  `COLUMN2` varchar(800) DEFAULT NULL,
  `COLUMN3` varchar(800) DEFAULT NULL,
  `COLUMN4` varchar(800) DEFAULT NULL,
  `COLUMN5` varchar(800) DEFAULT NULL,
  `COLUMN6` varchar(800) DEFAULT NULL,
  `COLUMN7` varchar(800) DEFAULT NULL,
  `COLUMN8` varchar(800) DEFAULT NULL,
  `COLUMN9` varchar(800) DEFAULT NULL,
  `COLUMN10` varchar(800) DEFAULT NULL,
  `COLUMN11` varchar(800) DEFAULT NULL,
  `COLUMN12` varchar(800) DEFAULT NULL,
  `COLUMN13` varchar(800) DEFAULT NULL,
  `COLUMN14` varchar(800) DEFAULT NULL,
  `COLUMN15` varchar(800) DEFAULT NULL,
  `OPERATION_CODE` varchar(60) DEFAULT NULL,
  `HUB_CODE` varchar(60) DEFAULT NULL,
  `MAS_LOC` varchar(60) DEFAULT NULL,
  `LINE_HAUL` varchar(60) DEFAULT NULL,
  `CARRIER_CODE` varchar(60) DEFAULT NULL,
  `PRIORITY` bigint(20) DEFAULT NULL,
  `SORTING_CENTER_CODE` varchar(60) DEFAULT NULL,
  `DA_ID` varchar(80) DEFAULT NULL,
  `SELF_PICKUP_FLAG` varchar(4) DEFAULT NULL,
  `INSERT_BY` varchar(200) DEFAULT NULL,
  `INSERT_DATE` datetime DEFAULT NULL,
  `MODIFIED_BY` varchar(200) DEFAULT NULL,
  `MODIFIED_DATE` datetime DEFAULT NULL,
  `LINE_HAUL2` varchar(60) DEFAULT NULL,
  `DELIVERYMAN_ID` varchar(80) DEFAULT NULL,
  `ENABLED` int(11) DEFAULT NULL,
  `DELIVERY_LEAD_TIME_BK2_` double(8,2) DEFAULT NULL,
  `DELIVERY_LEAD_TIME` double(10,2) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `IDX_HUB_PROCERIX_RULE_NAME` (`RULE_NAME`),
  KEY `IDX_HUB_PROCERIX_COLUMN1` (`COLUMN1`(255)),
  KEY `IDX_HUB_PROCERIX_COLUMN2` (`COLUMN2`(255))
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='承运商区域配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hub_process_matrix_history`
--

DROP TABLE IF EXISTS `hub_process_matrix_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hub_process_matrix_history` (
  `HIS_ID` double DEFAULT NULL,
  `ID` double DEFAULT NULL,
  `RULE_NAME` varchar(200) DEFAULT NULL,
  `COLUMN1` varchar(800) DEFAULT NULL,
  `COLUMN2` varchar(800) DEFAULT NULL,
  `COLUMN3` varchar(800) DEFAULT NULL,
  `COLUMN4` varchar(800) DEFAULT NULL,
  `COLUMN5` varchar(800) DEFAULT NULL,
  `COLUMN6` varchar(800) DEFAULT NULL,
  `COLUMN7` varchar(800) DEFAULT NULL,
  `COLUMN8` varchar(800) DEFAULT NULL,
  `COLUMN9` varchar(800) DEFAULT NULL,
  `COLUMN10` varchar(800) DEFAULT NULL,
  `COLUMN11` varchar(800) DEFAULT NULL,
  `COLUMN12` varchar(800) DEFAULT NULL,
  `COLUMN13` varchar(800) DEFAULT NULL,
  `COLUMN14` varchar(800) DEFAULT NULL,
  `COLUMN15` varchar(800) DEFAULT NULL,
  `OPERATION_CODE` varchar(60) DEFAULT NULL,
  `HUB_CODE` varchar(60) DEFAULT NULL,
  `MAS_LOC` varchar(60) DEFAULT NULL,
  `LINE_HAUL` varchar(60) DEFAULT NULL,
  `CARRIER_CODE` varchar(60) DEFAULT NULL,
  `PRIORITY` bigint(20) DEFAULT NULL,
  `SORTING_CENTER_CODE` varchar(60) DEFAULT NULL,
  `DA_ID` varchar(80) DEFAULT NULL,
  `SELF_PICKUP_FLAG` varchar(4) DEFAULT NULL,
  `INSERT_BY` varchar(200) DEFAULT NULL,
  `INSERT_DATE` datetime DEFAULT NULL,
  `MODIFIED_BY` varchar(200) DEFAULT NULL,
  `MODIFIED_DATE` datetime DEFAULT NULL,
  `LINE_HAUL2` varchar(60) DEFAULT NULL,
  `DELIVERYMAN_ID` varchar(80) DEFAULT NULL,
  `ENABLED` int(11) DEFAULT NULL,
  `DELIVERY_LEAD_TIME_BK2_` double(8,2) DEFAULT NULL,
  `DELIVERY_LEAD_TIME` double(10,2) DEFAULT NULL,
  KEY `IDX_HUB_PROCEORY_RULE_NAME` (`RULE_NAME`),
  KEY `IDX_HUB_PROCEORY_COLUMN1` (`COLUMN1`(255)),
  KEY `IDX_HUB_PROCEORY_COLUMN2` (`COLUMN2`(255))
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='承运商区域配置历史表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lsp_day_half_info`
--

DROP TABLE IF EXISTS `lsp_day_half_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lsp_day_half_info` (
  `BUID` bigint(20) NOT NULL COMMENT 'BUID',
  `LSP_CODE` varchar(60) NOT NULL COMMENT '承运商编码',
  `EX_FROM_DATE` varchar(20) DEFAULT NULL COMMENT '出库时间开始段',
  `EX_TO_DATE` varchar(20) DEFAULT NULL COMMENT '出库时间结束段',
  `NEXT_DAY_FLG` varchar(4) DEFAULT NULL COMMENT '次日达标识',
  `DL_DATE` varchar(20) DEFAULT NULL COMMENT '妥投时间',
  `IS_EFFECTIVE_FLG` varchar(4) DEFAULT NULL COMMENT '生效标识',
  `INSERT_BY` varchar(200) DEFAULT NULL COMMENT '插入者',
  `INSERT_DATE` datetime DEFAULT NULL COMMENT '插入时间',
  `MODIFIED_BY` varchar(200) DEFAULT NULL COMMENT '更新者',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '更新时间',
  `ID` bigint(20) DEFAULT NULL COMMENT '多个半日达ID',
  `INTER_DAY_FLG` varchar(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='承运商半日达信息LSP_DAY_HALF_INFO';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mst_driver`
--

DROP TABLE IF EXISTS `mst_driver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mst_driver` (
  `DRIVER_NO` int(11) NOT NULL COMMENT '司机编号',
  `DRIVER_NAME` varchar(20) DEFAULT NULL COMMENT '司机姓名',
  `DRIVER_LSP` varchar(50) DEFAULT NULL COMMENT '承运商',
  `DRIVER_DEPARTMENT` bigint(20) DEFAULT NULL COMMENT '所属部门 1.北京-- 2.上海-- 3.成都--  4.广州  ',
  `DRIVER_CARD` varchar(50) DEFAULT NULL COMMENT '驾驶证号',
  `DRIVER_BRITHDAY` datetime DEFAULT NULL COMMENT '出生日期',
  `DRIVER_IDCARD` varchar(18) DEFAULT NULL COMMENT '身份证',
  `DRIVER_PHONE` varchar(15) DEFAULT NULL COMMENT '联系电话',
  `DRIVER_ADDRESS` varchar(100) DEFAULT NULL COMMENT '司机联系地址',
  `DRIVER_LEVEL` int(11) DEFAULT NULL COMMENT '员工评级 1.高级  2.中级  3.初级',
  `ENTER_WORK_DATE` datetime DEFAULT NULL COMMENT '参加工作的时间',
  `DRIVER_CARD_CHECKDATE` datetime DEFAULT NULL COMMENT '驾照年审日期',
  `DRIVER_CARD_DATE` datetime DEFAULT NULL COMMENT '驾照日期',
  `ENTER_WORK_ID` varchar(20) DEFAULT NULL COMMENT '从业证号',
  `DRIVER_SKILL_LEVEL` int(11) DEFAULT NULL COMMENT '技能等级 1-高级 2-中级 3-低级',
  `DRIVER_CAR_TYPE` varchar(20) DEFAULT NULL COMMENT '驾驶车型',
  `DRIVER_ISACTIVITY` varchar(1) DEFAULT NULL COMMENT '是否活动 Y||N 是或者不是',
  `COMMENT` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`DRIVER_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='司机信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mst_exwarehouse_dock`
--

DROP TABLE IF EXISTS `mst_exwarehouse_dock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mst_exwarehouse_dock` (
  `ID` double NOT NULL COMMENT '集货口ID',
  `MAS_LOC` varchar(32) DEFAULT NULL COMMENT '仓库编码',
  `EXIT_ID` varchar(80) DEFAULT NULL COMMENT '集货口ID',
  `TYPE` varchar(60) DEFAULT NULL COMMENT '集货口类型',
  `CONTENT` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `IDXU_MST_EXWAROCK_MAS_LOC` (`MAS_LOC`),
  UNIQUE KEY `IDXU_MST_EXWAROCK_TYPE` (`TYPE`),
  UNIQUE KEY `IDXU_MST_EXWAROCK_CONTENT` (`CONTENT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='集货口MST_EXWAREHOUSE_DOCK';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mst_lsp`
--

DROP TABLE IF EXISTS `mst_lsp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mst_lsp` (
  `BUID` bigint(20) NOT NULL COMMENT '销售渠道 销售渠道：8270',
  `LSP_CODE` varchar(60) NOT NULL COMMENT '承运商',
  `LSP_NAME` varchar(200) DEFAULT NULL COMMENT '承运商名称',
  `LSP_ADDRESS1` varchar(1000) DEFAULT NULL COMMENT '承运商地址1',
  `LSP_ADDRESS2` varchar(1000) DEFAULT NULL COMMENT '承运商地址2',
  `LSP_ADDRESS3` varchar(1000) DEFAULT NULL COMMENT '承运商地址3',
  `LSP_ADDRESS4` varchar(1000) DEFAULT NULL COMMENT '承运商地址4',
  `LSP_ADDRESS5` varchar(1000) DEFAULT NULL COMMENT '承运商地址5',
  `LSP_CONTACT` varchar(60) DEFAULT NULL COMMENT '承运商联系人',
  `LSP_EMAIL` varchar(400) DEFAULT NULL COMMENT '承运商邮箱',
  `LSP_PHONE` varchar(120) DEFAULT NULL COMMENT '承运商电话',
  `LSP_FAX` varchar(120) DEFAULT NULL COMMENT '承运商传真',
  `LSP_ST_CODE` varchar(20) DEFAULT NULL COMMENT '承运商区域CODE',
  `LSP_CY_CODE` varchar(8) DEFAULT NULL COMMENT '承运商大区域CODE',
  `LSP_ZIP_CODE` varchar(28) DEFAULT NULL COMMENT '承运商邮编CODE ',
  `INSERT_DATE` datetime DEFAULT NULL COMMENT '插入时间',
  `INSERT_BY` varchar(200) DEFAULT NULL COMMENT '插入者',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '更新时间',
  `MODIFIED_BY` varchar(200) DEFAULT NULL COMMENT '更新者',
  `LSP_DESCRIPTION` varchar(400) DEFAULT NULL COMMENT '承运商描述',
  `ENABLED` int(11) DEFAULT '1' COMMENT '有效性',
  `CREDIT_RANKING_SERVICE` int(11) DEFAULT NULL COMMENT '信用级别',
  `CREDIT_RANKING_FINANCE` int(11) DEFAULT NULL COMMENT '现金信用',
  `COD_RATE` double(9,3) DEFAULT NULL COMMENT '代收费率',
  `INSURANCE_RATE` double(9,3) DEFAULT NULL COMMENT '保险费率',
  `PAYMENT_CYCLE_TIME` int(11) DEFAULT NULL COMMENT '汇款周期',
  `COD_FLAG` varchar(4) DEFAULT NULL COMMENT '支持货到付款',
  `COD_CASH_FLAG` varchar(4) DEFAULT NULL COMMENT '现金标识',
  `COD_POS_FLAG` varchar(4) DEFAULT NULL COMMENT 'POS标识',
  `COD_CASH_MAX` int(11) DEFAULT NULL COMMENT '现金额度',
  `COD_CASH_CURRENT` double(11,2) DEFAULT NULL COMMENT '当前现金 当前现金：该字段可能没用',
  `TRADE_IN_FLAG` varchar(4) DEFAULT NULL COMMENT '以旧换新',
  `VIP_FLAG` varchar(4) DEFAULT NULL COMMENT '高级用户',
  `SELF_PICKUP_FLAG` varchar(4) DEFAULT NULL COMMENT '自提标识',
  `CONTRACT_DUE_DATE` datetime DEFAULT NULL COMMENT '合同截止日期',
  `SHIP_METHOD` varchar(80) DEFAULT NULL COMMENT '货运方式',
  `LSP_TYPE` varchar(20) DEFAULT NULL COMMENT '承运商类型',
  `RETURN_PICKUP_FLAG` varchar(4) DEFAULT NULL,
  `CARTON_WEIGHT_LIMIT` double(11,2) DEFAULT NULL,
  `WS_FLAG` varchar(4) DEFAULT NULL COMMENT 'WEBSERVICE支持标识',
  `FB_CIRCLE` tinyint(4) DEFAULT '1',
  `FB_DATE` varchar(4) DEFAULT 'EOM',
  PRIMARY KEY (`BUID`,`LSP_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='MST_LSP承运商';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mst_lsp_da_amount`
--

DROP TABLE IF EXISTS `mst_lsp_da_amount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mst_lsp_da_amount` (
  `ID` int(11) NOT NULL,
  `LSP` varchar(15) NOT NULL,
  `DA_ID` varchar(20) NOT NULL,
  `MIN_AMOUNT` double(11,2) NOT NULL,
  `MAX_AMOUNT` double(11,2) NOT NULL,
  `PRIORITY` bigint(20) NOT NULL,
  `INSERT_BY` varchar(50) DEFAULT NULL,
  `INSERT_DATE` datetime DEFAULT NULL,
  `MODIFIED_BY` varchar(50) DEFAULT NULL,
  `MODIFIED_DATE` datetime DEFAULT NULL,
  `PARTITION_DATE` datetime DEFAULT NULL,
  `ORDER_DOWNLOAD_BEGIN_TIME` varchar(8) NOT NULL,
  `ORDER_DOWNLOAD_END_TIME` varchar(8) NOT NULL,
  `DIVISION_CODE` varchar(16) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='承运商运力相关';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mst_lsp_da_share`
--

DROP TABLE IF EXISTS `mst_lsp_da_share`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mst_lsp_da_share` (
  `LSP` varchar(15) NOT NULL,
  `DA_ID` varchar(20) DEFAULT NULL,
  `MAX_ORD_QTY` double NOT NULL,
  `SHARE_PERCENT` double DEFAULT NULL,
  `MIN_DIVISOR` double NOT NULL,
  `INSERT_BY` varchar(50) DEFAULT NULL,
  `INSERT_DATE` datetime DEFAULT NULL,
  `MODIFIED_BY` varchar(50) DEFAULT NULL,
  `MODIFIED_DATE` datetime DEFAULT NULL,
  `PARTITION_DATE` datetime DEFAULT NULL,
  `DIVISION_CODE` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='承运商运力相关';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mst_lsp_pickup_schedule`
--

DROP TABLE IF EXISTS `mst_lsp_pickup_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mst_lsp_pickup_schedule` (
  `LSP_CODE` varchar(60) DEFAULT NULL COMMENT '承运商代码',
  `MAS_LOC` varchar(32) DEFAULT NULL COMMENT '主库',
  `DAY` int(11) DEFAULT NULL COMMENT '星期几',
  `TIME` varchar(20) DEFAULT NULL COMMENT '时间',
  `ID` int(20) NOT NULL COMMENT '序号',
  `INSERT_BY` varchar(200) DEFAULT NULL COMMENT '插入者',
  `INSERT_DATE` datetime DEFAULT NULL COMMENT '插入时间',
  `MODIFIED_BY` varchar(200) DEFAULT NULL COMMENT '更新者',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '更新时间',
  `ACTIVE` varchar(4) DEFAULT 'Y' COMMENT '有效标志',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mst_parameter`
--

DROP TABLE IF EXISTS `mst_parameter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mst_parameter` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL COMMENT '参数代码',
  `name` varchar(32) DEFAULT NULL COMMENT '参数名字',
  `description` varchar(255) DEFAULT NULL COMMENT '参数描述',
  `param_type` varchar(32) DEFAULT NULL COMMENT '参数类型',
  `value` varchar(32) DEFAULT NULL COMMENT '参数值',
  `script_id` int(11) DEFAULT NULL COMMENT '脚本ID',
  `script_content` text COMMENT '脚本内容',
  PRIMARY KEY (`Id`),
  KEY `IDX_MST_PARAMETER_code` (`code`,`param_type`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mst_vessel`
--

DROP TABLE IF EXISTS `mst_vessel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mst_vessel` (
  `VESSEL_NO` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '容器编号',
  `VESSEL_NAME` varchar(100) DEFAULT NULL COMMENT '容器名称',
  `FROM_DEPARTMENT` varchar(100) DEFAULT NULL COMMENT '所属部门',
  `VESSEL_COVERAGE` float DEFAULT NULL COMMENT '容器面积',
  `MATERIAL` int(11) DEFAULT NULL COMMENT '材质',
  `QUALITY` varchar(300) DEFAULT NULL COMMENT '质量情况',
  `VESSEL_STATE` int(11) DEFAULT NULL COMMENT '容器状态',
  `CREATE_PEOPLE` varchar(20) DEFAULT NULL COMMENT '创建人',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `ALTER_PEOPLE` varchar(20) DEFAULT NULL COMMENT '修改人',
  `ALTER_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  `COMMENT` varchar(200) DEFAULT NULL COMMENT '备注',
  `VESSEL_HEIGHT` float DEFAULT NULL COMMENT '容器的高度',
  `VESSEL_WIDTH` float DEFAULT NULL COMMENT '容器宽度',
  `VESSEL_LENGTH` float DEFAULT NULL COMMENT '容器长度',
  `VESSEL_VOLUME` float DEFAULT NULL COMMENT '容器称重',
  PRIMARY KEY (`VESSEL_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `security_login_failed_log`
--

DROP TABLE IF EXISTS `security_login_failed_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_login_failed_log` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '登录失败日志ID',
  `username` varchar(32) DEFAULT NULL COMMENT '用户名',
  `content` varchar(512) DEFAULT NULL COMMENT '内容',
  `IP` varchar(32) DEFAULT NULL COMMENT 'IP地址',
  `insert_date` datetime DEFAULT NULL COMMENT '插入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
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
  `SP_ID` int(11) DEFAULT NULL,
  `SP_NAME` varchar(20) DEFAULT NULL,
  `SP_PARENT_ID` int(11) DEFAULT NULL,
  `SP_DESC` varchar(20) DEFAULT NULL,
  `HREF` varchar(100) DEFAULT NULL,
  `TARGET` varchar(20) DEFAULT NULL,
  `ICON` varchar(20) DEFAULT NULL,
  `SP_VISIBLE` varchar(20) DEFAULT NULL,
  `SP_TYPE` varchar(20) DEFAULT NULL,
  `PERMISSION` varchar(50) DEFAULT NULL,
  `VERSION` varchar(1) DEFAULT NULL,
  `SYS_ID` bigint(20) DEFAULT NULL,
  `RANK` bigint(20) DEFAULT NULL
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
) ENGINE=InnoDB AUTO_INCREMENT=312034 DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-10 17:44:17
