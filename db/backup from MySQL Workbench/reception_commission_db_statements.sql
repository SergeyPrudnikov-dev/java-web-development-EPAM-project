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
-- Table structure for table `statements`
--

DROP TABLE IF EXISTS `statements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `statements` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `last_name` varchar(45) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `patronymic` varchar(45) NOT NULL,
  `passport_id` varchar(45) NOT NULL,
  `certificate_score` int DEFAULT NULL,
  `subject_1_score` int DEFAULT NULL,
  `subject_2_score` int DEFAULT NULL,
  `subject_3_score` int DEFAULT NULL,
  `date` date NOT NULL,
  `user_id` bigint NOT NULL,
  `faculty_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `passport_id_UNIQUE` (`passport_id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `users_id_UNIQUE` (`user_id`),
  KEY `fk_statements_users_idx` (`user_id`),
  KEY `fk_statements_faculties1_idx` (`faculty_id`),
  CONSTRAINT `fk_statements_faculties1` FOREIGN KEY (`faculty_id`) REFERENCES `faculties` (`id`),
  CONSTRAINT `fk_statements_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statements`
--

LOCK TABLES `statements` WRITE;
/*!40000 ALTER TABLE `statements` DISABLE KEYS */;
INSERT INTO `statements` VALUES (1,'Петров','Макар','Аркадьевич','TP1234567',50,70,60,50,'2021-01-30',18,1),(2,'Марченко','Алексей','Владимирович','DC123457',6,4,5,5,'2021-01-28',33,2),(3,'Жуков','Иван','Иванович','BM1236547',9,7,7,8,'2021-01-30',35,1),(4,'Цветкова','Анна','Алексеевна','КА9513214',2,6,5,6,'2021-01-31',36,1),(6,'Коренина','Маргарита','Павловна','ТК6548196',6,3,5,7,'2021-01-28',37,1),(11,'Лосик','Григорий','Борисович','МВ3214587',7,6,5,4,'2021-01-28',3,1),(12,'Петрова','Мария','Аркадьевна','TP1234447',33,72,60,50,'2021-01-30',38,1),(13,'Марченков','Алексей','Владимирович','DC123413',36,44,45,65,'2021-01-30',39,2),(14,'Жуковский','Антон','Иванович','BM1236514',49,37,57,78,'2021-01-30',40,3),(15,'Цапля','Алена','Алексеевна','КА9513215',25,73,35,66,'2021-01-30',41,1),(16,'Коренин','Максим','Павлович','ТК6548116',64,34,52,75,'2021-01-30',42,2),(18,'Заяц','Кирил','Борисович','МВ3214517',35,25,55,30,'2021-01-30',43,3),(19,'Волков','Александр','Михайлович','МВ3214518',46,78,81,44,'2021-01-30',44,1),(20,'Иванов','Матвей','Сергеевич','МВ3214519',57,65,80,65,'2021-01-30',45,2),(21,'Лютый','Сергей','Дмитриевич','МВ3214520',64,32,54,32,'2021-01-30',46,3),(22,'Белый','Дмитрий','Михайлович','МВ3214521',72,46,74,52,'2021-01-30',47,1),(23,'Черный','Михаил','Викторович','МВ3214522',80,50,36,74,'2021-01-30',48,2),(24,'Попов','Виктор','Игоревич','МВ3214523',39,60,28,85,'2021-01-30',49,3),(25,'Крутой','Игорь','Данилович','МВ3214524',47,70,30,62,'2021-01-30',50,1),(26,'Подольская','Татьяна','Евграфовна','МВ3214525',55,20,59,74,'2021-01-30',51,2),(27,'Ловчая','Анна','Петровна','МВ3214526',69,80,54,70,'2021-01-30',52,3),(28,'Певчая','Кристина','Михайловна','МВ3214527',70,46,87,50,'2021-01-30',53,1),(29,'Февраль','Наталья','Сергеевна','МВ3214528',95,99,68,90,'2021-01-30',54,2),(30,'Рыбакова','Елена','Борисовна','МВ3214529',45,40,70,40,'2021-01-30',55,3),(31,'gderg','erge','ergerg','gdrgdr',6,5,4,3,'2021-01-31',56,1),(32,'Иванов','Иван ','Иванович','йцвйцвйцв',100,100,100,100,'2021-01-31',57,1);
/*!40000 ALTER TABLE `statements` ENABLE KEYS */;
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
