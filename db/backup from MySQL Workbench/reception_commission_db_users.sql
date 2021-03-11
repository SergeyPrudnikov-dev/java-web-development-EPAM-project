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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `login` varchar(16) NOT NULL,
  `password` varchar(32) NOT NULL,
  `email` varchar(255) NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (3,'Panda','12345','pandan@doc.st',1),(15,'Admin','12345','admin@ad.min',0),(16,'Nikow','12345','asd@as.gg',1),(18,'Entrant','12345','gdgdgdgdg',1),(33,'entrant2','0000','ииииии',1),(35,'entrant3','0000','12412412sadasc',1),(36,'entrant4','0000','fwefwef',1),(37,'entrant5','0000','rthrthf',1),(38,'entrant6','12345','entrant6@test.com',1),(39,'entrant39','12345','entrant39@test.com',1),(40,'entrant40','12345','entrant40@test.com',1),(41,'entrant41','12345','entrant41@test.com',1),(42,'entrant42','12345','entrant42@test.com',1),(43,'entrant43','12345','entrant43@test.com',1),(44,'entrant44','12345','entrant44@test.com',1),(45,'entrant45','12345','entrant45@test.com',1),(46,'entrant46','12345','entrant46@test.com',1),(47,'entrant47','12345','entrant47@test.com',1),(48,'entrant48','12345','entrant48@test.com',1),(49,'entrant49','12345','entrant49@test.com',1),(50,'entrant50','12345','entrant50@test.com',1),(51,'entrant51','12345','entrant51@test.com',1),(52,'entrant52','12345','entrant52@test.com',1),(53,'entrant53','12345','entrant53@test.com',1),(54,'entrant54','12345','entrant54@test.com',1),(55,'entrant55','12345','entrant55@test.com',1),(56,'entrant999','0000','wefwe',1),(57,'entrant100','12345','100dd',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-31 19:42:16
