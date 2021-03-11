DROP DATABASE IF EXISTS `reception_commission_db`;

CREATE DATABASE `reception_commission_db` DEFAULT CHARACTER SET utf8;

USE `reception_commission_db`;

DROP TABLE IF EXISTS `faculties`;

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `users`;

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
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `statements`;

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `list_enrolled_persons`;

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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

INSERT INTO `users` VALUES 
(38,'admin','12345','admin@test.com',0);
(39,'entrant39','12345','entrant39@test.com',1), 
(40,'entrant40','12345','entrant40@test.com',1),
(41,'entrant41','12345','entrant41@test.com',1),
(42,'entrant42','12345','entrant42@test.com',1),
(43,'entrant43','12345','entrant43@test.com',1),
(44,'entrant44','12345','entrant44@test.com',1),
(45,'entrant45','12345','entrant45@test.com',1),
(46,'entrant46','12345','entrant46@test.com',1),
(47,'entrant47','12345','entrant47@test.com',1),
(48,'entrant48','12345','entrant48@test.com',1),
(49,'entrant49','12345','entrant49@test.com',1),
(50,'entrant50','12345','entrant50@test.com',1),
(51,'entrant51','12345','entrant51@test.com',1),
(52,'entrant52','12345','entrant52@test.com',1),
(53,'entrant53','12345','entrant53@test.com',1),
(54,'entrant54','12345','entrant54@test.com',1),
(55,'entrant55','12345','entrant55@test.com',1);

INSERT INTO `faculties` VALUES 
(1,'ФАКУЛЬТЕТ МАТЕМАТИКИ И ИНФОРМАЦИОННЫХ ТЕХНОЛОГИЙ',5,'Аттестат об общем среднем образовании','Информатика','Математика','Бел./рус. язык'),
(2,'ЮРИДИЧЕСКИЙ ФАКУЛЬТЕТ',5,'Аттестат об общем среднем образовании','Обществоведение','Иностранный язык','Бел./рус. язык'),
(3,'ПЕДАГОГИЧЕСКИЙ ФАКУЛЬТЕТ',5,'Аттестат об общем среднем образовании','История Беларуси','Биология','Бел./рус. язык');

INSERT INTO `statements` VALUES 
(13,'Марченков','Алексей','Владимирович','DC123413',36,44,45,65,'2021-01-30',39,2),
(14,'Жуковский','Антон','Иванович','BM1236514',49,37,57,78,'2021-01-30',40,3),
(15,'Цапля','Алена','Алексеевна','КА9513215',25,73,35,66,'2021-01-30',41,1),
(16,'Коренин','Максим','Павлович','ТК6548116',64,34,52,75,'2021-01-30',42,2),
(18,'Заяц','Кирил','Борисович','МВ3214517',35,25,55,30,'2021-01-30',43,3),
(19,'Волков','Александр','Михайлович','МВ3214518',46,78,81,44,'2021-01-30',44,1),
(20,'Иванов','Матвей','Сергеевич','МВ3214519',57,65,80,65,'2021-01-30',45,2),
(21,'Лютый','Сергей','Дмитриевич','МВ3214520',64,32,54,32,'2021-01-30',46,3),
(22,'Белый','Дмитрий','Михайлович','МВ3214521',72,46,74,52,'2021-01-30',47,1),
(23,'Черный','Михаил','Викторович','МВ3214522',80,50,36,74,'2021-01-30',48,2),
(24,'Попов','Виктор','Игоревич','МВ3214523',39,60,28,85,'2021-01-30',49,3),
(25,'Крутой','Игорь','Данилович','МВ3214524',47,70,30,62,'2021-01-30',50,1),
(26,'Подольская','Татьяна','Евграфовна','МВ3214525',55,20,59,74,'2021-01-30',51,2),
(27,'Ловчая','Анна','Петровна','МВ3214526',69,80,54,70,'2021-01-30',52,3),
(28,'Певчая','Кристина','Михайловна','МВ3214527',70,46,87,50,'2021-01-30',53,1),
(29,'Февраль','Наталья','Сергеевна','МВ3214528',95,99,68,90,'2021-01-30',54,2),
(30,'Рыбакова','Елена','Борисовна','МВ3214529',45,40,70,40,'2021-01-30',55,3);
