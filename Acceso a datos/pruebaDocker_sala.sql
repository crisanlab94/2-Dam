-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: pruebaDocker
-- ------------------------------------------------------
-- Server version	8.4.7

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
-- Table structure for table `sala`
--

DROP TABLE IF EXISTS `sala`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sala` (
  `capacidad` int NOT NULL,
  `idSala` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idSala`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sala`
--

LOCK TABLES `sala` WRITE;
/*!40000 ALTER TABLE `sala` DISABLE KEYS */;
INSERT INTO `sala` VALUES (5,1,'Sala1'),(5,2,'Sala1'),(5,3,'Sala1'),(5,4,'Sala1'),(5,5,'Sala1'),(5,6,'Sala1'),(5,7,'Sala1'),(5,8,'Sala1'),(5,9,'Sala1'),(5,10,'Sala1'),(5,11,'Sala1'),(5,12,'Sala1'),(5,13,'Sala1'),(5,14,'Sala1'),(5,15,'Sala1'),(5,16,'Sala1'),(5,17,'Sala1'),(5,18,'Sala1'),(5,19,'Sala1'),(5,20,'Sala1'),(5,21,'Sala1'),(5,22,'Sala1'),(5,23,'Sala1'),(5,24,'Sala1'),(5,25,'Sala1'),(5,26,'Sala1'),(5,27,'Sala1'),(5,28,'Sala1'),(5,29,'Sala1'),(5,30,'Sala1'),(5,31,'Sala1'),(5,32,'Sala1'),(5,33,'Sala1'),(5,34,'Sala1'),(5,35,'Sala1'),(5,36,'Sala1'),(5,37,'Sala1'),(5,38,'Sala1'),(5,39,'Sala1'),(5,40,'Sala1'),(5,41,'Sala1'),(5,42,'Sala1'),(5,43,'Sala1'),(5,44,'Sala1');
/*!40000 ALTER TABLE `sala` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-01-27 10:58:05
