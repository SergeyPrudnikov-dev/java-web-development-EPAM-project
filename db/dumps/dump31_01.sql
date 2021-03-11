-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: reception_commission_db
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `faculties`
--

DROP TABLE IF EXISTS `faculties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faculties` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(75) NOT NULL,
  `recruitment_plan` int DEFAULT NULL,
  `certificate` varchar(45) DEFAULT NULL,
  `subject_1` varchar(45) DEFAULT NULL,
  `subject_2` varchar(45) DEFAULT NULL,
  `subject_3` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculties`
--

LOCK TABLES `faculties` WRITE;
/*!40000 ALTER TABLE `faculties` DISABLE KEYS */;
INSERT INTO `faculties` VALUES (1,'ФАКУЛЬТЕТ МАТЕМАТИКИ И ИНФОРМАЦИОННЫХ ТЕХНОЛОГИЙ',7,'Аттестат об общем среднем образовании','Информатика','Математика','Бел./рус. язык'),(2,'ЮРИДИЧЕСКИЙ ФАКУЛЬТЕТ',5,'Аттестат об общем среднем образовании','Обществоведение','Иностранный язык','Бел./рус. язык'),(3,'ПЕДАГОГИЧЕСКИЙ ФАКУЛЬТЕТ',4,'Аттестат об общем среднем образовании','История Беларуси','Биология','Бел./рус. язык');
/*!40000 ALTER TABLE `faculties` ENABLE KEYS */;
UNLOCK TABLES;

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

-- Dump completed on 2021-01-31 19:38:19
