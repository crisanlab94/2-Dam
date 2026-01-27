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
-- Table structure for table `reunion`
--

DROP TABLE IF EXISTS `reunion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reunion` (
  `idReunion` int NOT NULL,
  `fecha` datetime(6) DEFAULT NULL,
  `asunto` varchar(255) DEFAULT NULL,
  `idSala` int DEFAULT NULL,
  PRIMARY KEY (`idReunion`),
  KEY `FKbwalj0pcwrb1xtwwvwsnpor2r` (`idSala`),
  CONSTRAINT `FKbwalj0pcwrb1xtwwvwsnpor2r` FOREIGN KEY (`idSala`) REFERENCES `sala` (`idSala`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reunion`
--

LOCK TABLES `reunion` WRITE;
/*!40000 ALTER TABLE `reunion` DISABLE KEYS */;
INSERT INTO `reunion` VALUES (1,'2026-01-23 09:55:11.034021','Proxima reunion',NULL),(2,'2026-01-23 10:35:09.848913','Proxima reunion',NULL),(52,'2026-01-23 10:47:41.313084','Proxima reunion',NULL),(102,'2026-01-23 10:49:23.163827','Proxima reunion',NULL),(152,'2026-01-23 10:50:17.430975','Proxima reunion',NULL),(402,'2026-01-23 11:04:47.658069','Proxima reunion',NULL),(452,'2026-01-23 11:05:33.799364','Proxima reunion',NULL),(453,'2026-01-23 11:05:35.449624','Proxima reunion',4),(502,'2026-01-23 11:17:07.310874','Proxima reunion',NULL),(503,'2026-01-23 11:17:09.012777','Proxima reunion',5),(552,'2026-01-23 11:18:03.628397','Proxima reunion',6),(602,'2026-01-23 11:20:28.045010','Proxima reunion',7),(652,'2026-01-23 11:21:03.796597','Proxima reunion',8),(702,'2026-01-23 11:27:05.191349','Proxima reunion',9),(752,'2026-01-23 11:30:58.677109','Proxima reunion',10),(802,'2026-01-23 11:33:42.783059','Proxima reunion',11),(803,'2026-01-23 11:33:41.053031','Proxima reunion',11),(852,'2026-01-24 10:41:27.548912','Proxima reunion',12),(853,'2026-01-24 10:41:25.448720','Proxima reunion',12),(902,'2026-01-24 10:42:49.071389','Proxima reunion',13),(903,'2026-01-24 10:42:47.270343','Proxima reunion 2',13),(952,'2026-01-24 10:58:04.365916','Proxima reunion',14),(1002,'2026-01-24 11:00:10.746838','Proxima reunion',15),(1052,'2026-01-24 11:02:20.375826','Proxima reunion',16),(1053,'2026-01-24 11:02:18.525892','Proxima reunion 2',NULL),(1102,'2026-01-24 11:04:47.645559','Proxima reunion',17),(1103,'2026-01-24 11:04:45.846772','Proxima reunion 2',NULL),(1152,'2026-01-24 11:12:14.718024','Proxima reunion',18),(1153,'2026-01-24 11:12:13.006119','Proxima reunion 2',NULL),(1202,'2026-01-24 11:13:25.126247','Proxima reunion',19),(1203,'2026-01-24 11:13:23.348276','Proxima reunion 2',NULL),(1252,'2026-01-24 11:20:05.659952','Proxima reunion',20),(1302,'2026-01-24 11:20:42.655965','Proxima reunion',21),(1352,'2026-01-24 11:22:19.981063','Proxima reunion',22),(1402,'2026-01-24 11:27:20.576211','Proxima reunion 2',NULL),(1403,'2026-01-24 11:27:22.496600','Proxima reunion',23),(1404,'2026-01-24 11:27:20.578212','Proxima reunion 3',NULL),(1452,'2026-01-24 11:28:00.929833','Proxima reunion 2',NULL),(1453,'2026-01-24 11:28:02.836688','Proxima reunion',24),(1454,'2026-01-24 11:28:00.931339','Proxima reunion 3',NULL),(1502,'2026-01-24 11:31:28.501041','Proxima reunion 1',NULL),(1503,'2026-01-24 11:31:30.437042','Proxima reunion',25),(1552,'2026-01-24 11:31:51.441395','Proxima reunion 1',NULL),(1553,'2026-01-24 11:31:53.349695','Proxima reunion',26),(1554,'2026-01-24 11:31:51.442396','Proxima reunion 3',NULL),(1602,'2026-01-24 11:33:08.047467','Proxima reunion 1',NULL),(1603,'2026-01-24 11:33:09.960151','Proxima reunion',27),(1604,'2026-01-24 11:33:08.048463','Proxima reunion 3',NULL),(1652,'2026-01-24 11:33:54.762243','Proxima reunion 1',NULL),(1653,'2026-01-24 11:33:56.738739','Proxima reunion',28),(1654,'2026-01-24 11:33:54.763250','Proxima reunion 3',NULL),(1702,'2026-01-24 11:39:35.277399','Proxima reunion 1',NULL),(1703,'2026-01-24 11:39:37.237359','Proxima reunion',29),(1704,'2026-01-24 11:39:35.278399','Proxima reunion 3',NULL),(1752,'2026-01-24 11:39:48.238457','Proxima reunion 1',NULL),(1753,'2026-01-24 11:39:50.164330','Proxima reunion',30),(1802,'2026-01-24 11:40:05.221548','Proxima reunion 1',NULL),(1803,'2026-01-24 11:40:07.392826','Proxima reunion',31),(1852,'2026-01-24 11:40:45.876992','Proxima reunion 1',NULL),(1853,'2026-01-24 11:40:47.901496','Proxima reunion',32),(1902,'2026-01-24 12:09:23.533777','Proxima reunion 1',NULL),(1903,'2026-01-24 12:09:25.674284','Proxima reunion',33),(1904,'2026-01-24 12:09:25.757099','Nueva Reunion',33),(1952,'2026-01-24 12:12:46.042662','Proxima reunion 1',NULL),(1953,'2026-01-24 12:12:48.070252','Proxima reunion',34),(1954,'2026-01-24 12:12:48.155190','Nueva Reunion',34),(2002,'2026-01-24 12:14:59.553630','Proxima reunion 1',NULL),(2003,'2026-01-24 12:15:01.550193','Proxima reunion',35),(2004,'2026-01-24 12:15:01.632618','Nueva Reunion',35),(2052,'2026-01-24 12:17:34.497479','Proxima reunion 1',NULL),(2053,'2026-01-24 12:17:36.506760','Proxima reunion',36),(2054,'2026-01-24 12:17:36.594735','Nueva Reunion',36),(2102,'2026-01-24 12:27:39.953901','Proxima reunion 1',NULL),(2103,'2026-01-24 12:27:41.873981','Proxima reunion',37),(2104,'2026-01-24 12:27:41.972950','Nueva Reunion',37),(2152,'2026-01-30 09:14:48.694654','Proxima reunion 1',NULL),(2153,'2026-01-30 09:14:50.792322','Proxima reunion',38),(2154,'2026-01-30 09:14:50.886731','Nueva Reunion',38),(2202,'2026-01-30 09:15:42.032343','Proxima reunion 1',NULL),(2203,'2026-01-30 09:15:43.893565','Proxima reunion',39),(2204,'2026-01-30 09:15:44.005143','Nueva Reunion',39),(2252,'2026-01-30 09:27:55.478361','Proxima reunion 1',NULL),(2253,'2026-01-30 09:27:57.369140','Proxima reunion',40),(2254,'2026-01-30 09:27:57.460163','Nueva Reunion',40),(2302,'2026-01-30 09:31:21.353492','Proxima reunion 1',NULL),(2303,'2026-01-30 09:31:23.219843','Proxima reunion',41),(2304,'2026-01-30 09:31:23.316869','Nueva Reunion',41),(2352,'2026-01-30 09:31:35.831639','Proxima reunion 1',NULL),(2353,'2026-01-30 09:31:37.721621','Proxima reunion',42),(2354,'2026-01-30 09:31:37.836056','Nueva Reunion',42),(2402,'2026-01-30 09:33:21.229521','Proxima reunion 1',NULL),(2403,'2026-01-30 09:33:23.136202','Proxima reunion',43),(2404,'2026-01-30 09:33:23.234509','Nueva Reunion',43),(2452,'2026-01-30 09:33:39.145773','Proxima reunion 1',NULL),(2453,'2026-01-30 09:33:40.998010','Proxima reunion',44),(2454,'2026-01-30 09:33:41.102320','Nueva Reunion',44),(2455,'2026-01-30 09:33:41.102320','Nueva Reunion',44);
/*!40000 ALTER TABLE `reunion` ENABLE KEYS */;
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
