
-- MySQL dump 10.13  Distrib 5.7.11, for Win64 (x86_64)
--
-- Host: localhost    Database: ryspringoaut1
-- ------------------------------------------------------
-- Server version	5.7.11-log
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


/*!40000 ALTER TABLE account DISABLE KEYS */;
/*'1', '0', '1', '0', '0', '$2a$10$ppZEmet3dNz0RPNrlohR0OyN6ww.6jq027Zff0LlikHtIOdZaLCsO', 'admin', NULL, NULL, NULL*/

INSERT INTO account VALUES (1,0,'1',0, 0,'$2a$10$ppZEmet3dNz0RPNrlohR0OyN6ww.6jq027Zff0LlikHtIOdZaLCsO','admin',NULL,NULL,NULL);
INSERT INTO account VALUES (2,0,'1',0, 0,'$2a$10$5SONOLPejS.ASXM2oZgzUO3fshVwySCwxXZ8HpWL0N4Mm5MbzdeXu','user',NULL,NULL,NULL);

/*!40000 ALTER TABLE role DISABLE KEYS */;
INSERT INTO role VALUES (1,'ROLE_ADMIN','User');
INSERT INTO role VALUES (2,'ROLE_USER','User');


/*!40000 ALTER TABLE account_role DISABLE KEYS */;
INSERT INTO account_role VALUES (1,1);
/*!40000 ALTER TABLE account_role ENABLE KEYS */;

/*!40000 ALTER TABLE role ENABLE KEYS */;

/*!40000 ALTER TABLE todo DISABLE KEYS */;

/*!40000 ALTER TABLE todo ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-12 16:24:55
