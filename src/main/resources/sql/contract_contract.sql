-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: contract
-- ------------------------------------------------------
-- Server version	5.6.26-log

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
-- Table structure for table `contract`
--

DROP TABLE IF EXISTS `contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contract` (
  `contractId` varchar(255) NOT NULL,
  `acceptance` datetime DEFAULT NULL,
  `biddingType` int(11) DEFAULT NULL,
  `budget` int(11) DEFAULT NULL,
  `budgetMoney` double DEFAULT NULL,
  `budgetType` varchar(15) DEFAULT NULL,
  `contractBeginDate` date NOT NULL,
  `contractCloseDate` datetime DEFAULT NULL,
  `contractEndDate` date NOT NULL,
  `contractMoney` double NOT NULL,
  `contractOperator` varchar(10) NOT NULL,
  `contractProperty` int(11) DEFAULT NULL,
  `contractProvide` int(11) NOT NULL,
  `contractSignCompany` varchar(255) DEFAULT NULL,
  `count` int(11) NOT NULL,
  `duePerformanceBond` datetime DEFAULT NULL,
  `guaranteePeriod` int(11) DEFAULT NULL,
  `invoice` int(11) DEFAULT NULL,
  `lawer` int(11) NOT NULL,
  `mainContent` varchar(300) NOT NULL,
  `paymentType` varchar(255) DEFAULT NULL,
  `performanceBond` double NOT NULL,
  `receivableOrPay` int(11) DEFAULT NULL,
  `receivableOrPayMoney` double NOT NULL,
  `remark` varchar(150) DEFAULT NULL,
  `settleAccount` int(11) DEFAULT NULL,
  `signContractDate` datetime NOT NULL,
  `stamp` int(11) DEFAULT NULL,
  `stampTax` int(11) NOT NULL,
  `supportFile` varchar(10) DEFAULT NULL,
  `unit_price` double NOT NULL,
  `version` int(11) DEFAULT NULL,
  `contractName` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `department` int(11) DEFAULT NULL,
  PRIMARY KEY (`contractId`),
  KEY `FKE20F7532DB5FFB39` (`department`),
  KEY `FKE20F75329FD96DFB` (`type`),
  KEY `FKE20F75324C157CAF` (`contractName`),
  CONSTRAINT `FKE20F75324C157CAF` FOREIGN KEY (`contractName`) REFERENCES `contractname` (`id`),
  CONSTRAINT `FKE20F75329FD96DFB` FOREIGN KEY (`type`) REFERENCES `contracttype` (`id`),
  CONSTRAINT `FKE20F7532DB5FFB39` FOREIGN KEY (`department`) REFERENCES `department` (`departmentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract`
--

LOCK TABLES `contract` WRITE;
/*!40000 ALTER TABLE `contract` DISABLE KEYS */;
INSERT INTO `contract` VALUES ('PC15-STD-1453',NULL,4,0,140000,'个人办公计算机','2015-08-18',NULL,'2015-08-18',50000,'何斌',0,0,'顺德港华, 信息技术, 无',2,'2015-08-16 23:57:40',12,17,0,'购买台式电脑','其他, 货款',0,0,50000,'噶',2,'2015-08-16 23:57:40',1,0,'供应商报价单',0,1,6,7,1);
/*!40000 ALTER TABLE `contract` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-08-17  0:12:04
