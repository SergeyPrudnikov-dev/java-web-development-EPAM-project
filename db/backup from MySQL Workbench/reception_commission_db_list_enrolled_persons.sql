CREATE DATABASE  IF NOT EXISTS `reception_commission_db` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `reception_commission_db`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: reception_commission_db
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `list_enrolled_persons`
--

DROP TABLE IF EXISTS `list_enrolled_persons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `list_enrolled_persons` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `full_name` varchar(45) NOT NULL,
  `total_score` int NOT NULL,
  `faculty_id` bigint NOT NULL,
  `statement_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `statements_id_UNIQUE` (`statement_id`),
  KEY `fk_list_enrolled_persons_statements1_idx` (`statement_id`),
  KEY `fk_list_enrolled_persons_faculties1_idx` (`faculty_id`),
  CONSTRAINT `fk_list_enrolled_persons_faculties1` FOREIGN KEY (`faculty_id`) REFERENCES `faculties` (`id`),
  CONSTRAINT `fk_list_enrolled_persons_statements1` FOREIGN KEY (`statement_id`) REFERENCES `statements` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=163 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `list_enrolled_persons`
--

LOCK TABLES `list_enrolled_persons` WRITE;
/*!40000 ALTER TABLE `list_enrolled_persons` DISABLE KEYS */;
INSERT INTO `list_enrolled_persons` VALUES (147,'Иванов Иван  Иванович',400,1,32),(148,'Певчая Кристина Михайловна',253,1,28),(149,'Волков Александр Михайлович',249,1,19),(150,'Белый Дмитрий Михайлович',244,1,22),(151,'Петров Макар Аркадьевич',230,1,1),(152,'Петрова Мария Аркадьевна',215,1,12),(153,'Крутой Игорь Данилович',209,1,25),(154,'Февраль Наталья Сергеевна',352,2,29),(155,'Иванов Матвей Сергеевич',267,2,20),(156,'Черный Михаил Викторович',240,2,23),(157,'Коренин Максим Павлович',225,2,16),(158,'Подольская Татьяна Евграфовна',208,2,26),(159,'Ловчая Анна Петровна',273,3,27),(160,'Жуковский Антон Иванович',221,3,14),(161,'Попов Виктор Игоревич',212,3,24),(162,'Рыбакова Елена Борисовна',195,3,30);
/*!40000 ALTER TABLE `list_enrolled_persons` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-31 19:42:15
