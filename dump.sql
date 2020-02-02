-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: parcels
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `country` varchar(50) NOT NULL,
  `region` varchar(45) NOT NULL,
  `active` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `country_UNIQUE` (`country`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'Russia','Europe',0),(2,'Belarus','Europe',1),(3,'Spain','Europe',0),(4,'Germany','Europe',0),(5,'France','Europe',0),(6,'Portugal','Europe',0),(7,'Ukraine','Europe',0),(8,'Sweden','Europe',0),(9,'England','Europe',0),(10,'Denmark','Europe',1),(11,'Italy','Europe',0),(12,'Poland','Europe',0),(13,'Greece','Europe',0),(14,'Switzerland','Europe',0),(15,'Egypt','Africa',0),(16,'Nigeria','Africa',0),(17,'Sudan','Africa',0),(18,'Liberia','Africa',0),(19,'Libya','Africa',0),(20,'Morocco','Africa',0),(21,'Niger','Africa',0),(22,'Mali','Africa',0),(23,'Chad','Africa',1),(24,'Somalia','Africa',0),(25,'Tanzania','Africa',0),(26,'Algeria','Africa',1),(27,'China','Asia',1),(28,'India','Asia',0),(29,'Japan','Asia',0),(30,'Kazakhstan','Asia',0),(31,'Mongolia','Asia',0),(32,'Singapore','Asia',0),(33,'Iran','Asia',0),(34,'Pakistan','Asia',0),(35,'Afghanistan','Asia',1),(36,'Turkmenistan','Asia',0),(37,'Uzbekistan','Asia',0),(38,'USA','South America',0),(39,'Mexico','South America',0),(40,'Canada','South America',1),(41,'Panama','South America',0),(42,'Cuba','South America',1),(43,'Costa-Rica','South America',1),(44,'Jamaica','South America',0),(45,'Honduras','South America',0),(46,'Brazil','North America',1),(47,'Chili','North America',1),(48,'Peru','North America',0),(49,'Argentina','North America',1),(50,'Uruguay','North America',0),(51,'Venezuela','North America',0),(52,'Columbia','North America',1),(53,'Bolivia','North America',1),(54,'Australia','Australia and Oceania',1),(55,'New Zealand','Australia and Oceania',0),(56,'Indonesia','Australia and Oceania',0),(57,'Philippines','Australia and Oceania',0),(58,'Slovakia','Europe',0),(59,'Norway','Europe',0),(60,'Croatia','Europe',1),(61,'Belgium','Europe',1),(62,'Slovenia','Europe',0),(63,'Antarctica','Antarctica',1),(64,'Macedonia','Europe',0),(65,'Moldova','Europe',0),(66,'Barbados','South America',1),(67,'Holland','Europe',0),(68,'Zambia','Africa',0),(69,'Gabon','Africa',0),(70,'Tunisia','Africa',0),(71,'Austria','Europe',1),(72,'Bahrain','Asia',1),(73,'Cambogia','Asia',1),(74,'Taiwan','Asia',1);
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discount` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `month` int(11) NOT NULL,
  `nameT` varchar(45) NOT NULL,
  `dis` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
INSERT INTO `discount` VALUES (3,3,' South America-Asia ',0.07),(4,4,' South America-North America ',0.04),(5,5,' Australia and Oceania-Antarctica ',0.05),(6,6,' Asia-Africa ',0.1),(7,7,' Europe-North America ',0.09),(8,8,' Europe-South America ',0.09),(9,9,' Australia and Oceania-South America ',0.11),(10,10,' Antarctica-South America ',0.07),(11,11,' Australia and Oceania-Asia ',0.12),(13,12,' Asia-Asia ',0.25),(14,12,' Europe-Europe ',0.18),(15,12,' North America-North America ',0.22),(20,6,' Europe-Europe ',0.2),(21,3,' North America-North America ',0.1);
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `totalp_price` float NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_fk_idx` (`id_user`),
  CONSTRAINT `user_id_fk` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,1,34),(17,8,142),(19,8,396.5),(21,8,1257.5),(23,9,80),(24,10,368),(25,10,38);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parcel`
--

DROP TABLE IF EXISTS `parcel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parcel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date_of_send` date NOT NULL,
  `country_sender` int(11) NOT NULL,
  `country_recipient` int(11) NOT NULL,
  `weight` float NOT NULL,
  `tarif_id` int(11) NOT NULL,
  `order_id` int(11) DEFAULT NULL,
  `express` tinyint(4) NOT NULL DEFAULT '0',
  `user_id` int(11) NOT NULL,
  `total_price` double NOT NULL,
  `delivery_time` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id_fk_idx` (`order_id`),
  KEY `tarif_id_fk_idx` (`tarif_id`),
  KEY `country_start_fk_idx` (`country_sender`),
  KEY `country_end_fk_idx` (`country_recipient`),
  KEY `id_user_fk_idx` (`user_id`),
  CONSTRAINT `country_end_fk` FOREIGN KEY (`country_recipient`) REFERENCES `country` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `country_start_fk` FOREIGN KEY (`country_sender`) REFERENCES `country` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `order_id_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tarif_id_fk` FOREIGN KEY (`tarif_id`) REFERENCES `tarif` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parcel`
--

LOCK TABLES `parcel` WRITE;
/*!40000 ALTER TABLE `parcel` DISABLE KEYS */;
INSERT INTO `parcel` VALUES (12,'2018-11-06',45,49,2,15,17,0,8,38,6),(13,'2018-11-06',12,52,2,9,17,0,8,54,12),(14,'2018-11-22',49,57,2,20,17,0,8,50,11),(24,'2018-12-20',47,67,10,9,1,0,8,270,12),(25,'2018-11-06',2,9,5,1,21,0,8,50,4),(26,'2018-12-20',3,1,5,1,1,0,8,41,4),(28,'2018-11-15',3,45,4,8,19,0,8,100,11),(29,'2019-03-27',69,70,4,3,1,0,8,40,5),(30,'2019-01-01',1,1,4,1,1,0,8,38,4),(31,'2019-01-01',1,1,4,1,1,0,8,38,4),(32,'2018-01-01',1,1,4,1,19,0,8,40,4),(33,'2018-12-10',1,3,4,1,1,0,8,32.8,4),(35,'2019-02-21',2,9,1,1,1,0,8,10,4),(36,'2019-02-21',2,7,6,1,1,0,8,60,4),(37,'2019-02-25',1,1,2,1,1,0,8,20,4),(41,'2019-12-31',34,63,3,22,1,0,8,96,15),(42,'2018-11-30',40,63,4,24,1,0,8,140,18),(44,'2018-11-30',63,20,4,23,1,0,8,84,10),(46,'2018-12-20',2,50,3,9,1,0,8,81,12),(47,'2018-12-20',2,2,3,1,1,0,8,35.4,4),(48,'2018-12-21',27,35,4,2,1,0,8,36,6),(60,'2019-06-19',71,20,3,7,1,0,8,60,6),(61,'2018-12-13',1,1,9,1,1,0,1,73.8,4),(62,'2018-12-12',1,1,0.1,1,1,0,1,0.8,4),(63,'2018-12-31',1,9,3,1,1,0,8,24.6,4),(64,'2018-12-31',1,9,3,1,1,0,8,24.6,4),(65,'2018-12-31',1,9,3,1,1,0,8,24.6,4),(66,'2018-12-31',1,9,3,1,1,0,8,24.6,4),(67,'2018-12-31',1,9,3,1,1,0,8,24.6,4),(68,'2018-12-31',1,9,3,1,1,0,8,24.6,4),(71,'2019-04-04',3,4,5,1,23,0,9,50,4),(72,'2019-04-24',11,67,3,1,23,0,9,30,4),(75,'2019-04-24',52,16,3,13,1,0,9,63,8),(76,'2019-03-20',6,7,3,1,1,0,8,30,4),(77,'2019-03-28',2,2,4,1,1,0,8,40,4),(78,'2019-05-30',53,49,5,5,1,0,9,55,6),(79,'2019-05-25',2,26,1.3,7,1,0,9,26,6),(81,'2019-05-23',10,27,4,6,24,1,10,108,4),(82,'2019-05-24',10,23,13,7,24,0,10,260,6),(83,'2019-05-29',42,46,2,15,25,0,10,38,6),(84,'2019-12-08',51,38,4,15,1,0,8,76,6);
/*!40000 ALTER TABLE `parcel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `idRole` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`idRole`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'user'),(2,'admin');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarif`
--

DROP TABLE IF EXISTS `tarif`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tarif` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `price` double NOT NULL,
  `delivery_time` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarif`
--

LOCK TABLES `tarif` WRITE;
/*!40000 ALTER TABLE `tarif` DISABLE KEYS */;
INSERT INTO `tarif` VALUES (1,' Europe-Europe ',1,4),(2,' Asia-Asia ',1.2,6),(3,' Africa-Africa ',1.1,5),(4,' South America-South America ',1.3,5),(5,' North America-North America ',1.1,6),(6,' Europe-Asia ',1.8,7),(7,' Europe-Africa ',2,6),(8,' Europe-South America ',2.5,11),(9,' Europe-North America ',2.7,12),(10,' Asia-Africa ',2.2,6),(11,' Asia-South America ',2.4,9),(12,' Asia-North America ',3,11),(13,' Africa-North America ',2.1,8),(14,' Africa-South America ',2.3,10),(15,' North America-South America ',1.9,6),(16,' Australia and Oceania-Australia and Oceania ',1.8,3),(17,' Australia and Oceania-Europe ',2.4,13),(18,' Australia and Oceania-Africa ',2.1,9),(19,' Australia and Oceania-South America ',2.7,13),(20,' Australia and Oceania-North America ',2.5,11),(21,' Antarctica-Antarctica ',1.8,7),(22,' Antarctica-Asia ',3.2,15),(23,' Antarctica-Africa ',2.1,10),(24,' Antarctica-South America ',3.5,18),(25,' Antarctica-North America ',2.3,11),(26,' Antarctica-Australia and Oceania ',2.2,9),(27,' Antarctica-Europe ',3.2,17),(28,' Australia and Oceania-Asia ',2,13);
/*!40000 ALTER TABLE `tarif` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(90) NOT NULL,
  `password` varchar(1000) NOT NULL,
  `email` varchar(90) NOT NULL,
  `name` varchar(90) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `role` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `roles_id_fk_idx` (`role`),
  CONSTRAINT `roles_id_fk` FOREIGN KEY (`role`) REFERENCES `role` (`idRole`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Nikita_123','$2a$10$.8s8w.rHQY0/aKxJleI3suYc9JgNJwNnmgv4Lr526rTsJsey4OF0q','nikitaperegud@gmail.com','Nikita','Peregud','+375 (44) 7756216',2),(8,'qwertyuiop','$2a$10$.8s8w.rHQY0/aKxJleI3suYc9JgNJwNnmgv4Lr526rTsJsey4OF0q','nikita.peregud@list.ru','Arthur','Peregud','+375 (44) 7815659',1),(9,'qwertyuiop1234','$2a$10$TAapAya2pfx.VfNQ8GEF2e8ZheEHBJ5LTDBRQU8SakvTC0R0wTZgi','aperegud@list.ru','Ilya','Peregud','+375 (44) 7832331',1),(10,'qwertyuiop12','$2a$10$eIm7dMRBQGn4PkQaAJkriOlWFSZ2gAUZ3tdBwUYoxHYHaHdScBaTu','stavakalov@inbox.ru','Nikita','Peregud','+375 (44) 7815659',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-29 21:35:22
