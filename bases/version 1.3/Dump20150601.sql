CREATE DATABASE  IF NOT EXISTS `sis` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `sis`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: sis
-- ------------------------------------------------------
-- Server version	5.6.15

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
-- Table structure for table `citas`
--

DROP TABLE IF EXISTS `citas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `citas` (
  `ID_CITA` int(11) NOT NULL,
  `ID_CLIENTE` int(11) NOT NULL,
  `ID_DOCTOR` int(11) NOT NULL,
  `ID_HORA` int(11) NOT NULL,
  `ID_DIA` int(11) NOT NULL,
  `FECHA_CITA` date NOT NULL,
  PRIMARY KEY (`ID_CITA`),
  KEY `FK_RELATIONSHIP_27` (`ID_CLIENTE`),
  KEY `FK_RELATIONSHIP_30` (`ID_HORA`),
  KEY `FK_RELATIONSHIP_32` (`ID_DOCTOR`),
  KEY `FK_RELATIONSHIP_46` (`ID_DIA`),
  CONSTRAINT `FK_RELATIONSHIP_46` FOREIGN KEY (`ID_DIA`) REFERENCES `dias` (`ID_DIA`),
  CONSTRAINT `FK_RELATIONSHIP_27` FOREIGN KEY (`ID_CLIENTE`) REFERENCES `clientes` (`ID_CLIENTE`),
  CONSTRAINT `FK_RELATIONSHIP_30` FOREIGN KEY (`ID_HORA`) REFERENCES `horas` (`ID_HORA`),
  CONSTRAINT `FK_RELATIONSHIP_32` FOREIGN KEY (`ID_DOCTOR`) REFERENCES `doctores` (`ID_DOCTOR`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `citas`
--

LOCK TABLES `citas` WRITE;
/*!40000 ALTER TABLE `citas` DISABLE KEYS */;
INSERT INTO `citas` VALUES (1,1,5,1,1,'2015-06-01'),(2,1,1,2,2,'2015-06-02'),(3,2,2,3,3,'2015-06-03'),(4,2,7,4,4,'2015-06-04'),(5,3,8,5,5,'2015-06-05'),(6,3,1,6,6,'2015-06-06'),(7,4,20,7,1,'2015-06-01'),(8,4,18,8,2,'2015-06-02'),(9,5,16,9,3,'2015-06-03'),(10,5,12,10,4,'2015-06-04'),(11,6,6,11,5,'2015-06-05'),(12,6,2,12,6,'2015-06-06'),(13,7,4,13,1,'2015-06-01'),(14,7,8,14,2,'2015-06-02'),(15,8,11,15,3,'2015-06-03'),(16,8,3,16,4,'2015-06-04'),(17,9,9,17,5,'2015-06-05'),(18,9,15,18,6,'2015-06-06'),(19,10,17,19,1,'2015-06-01'),(20,10,13,20,2,'2015-06-02');
/*!40000 ALTER TABLE `citas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientes` (
  `ID_CLIENTE` int(11) NOT NULL,
  `ID_USUARIO` int(11) NOT NULL,
  `ID_EPS` int(11) NOT NULL,
  `NOMBRE_CLIENTE` varchar(80) NOT NULL,
  `DOCUMENTO_CLIENTE` int(11) NOT NULL,
  `DIRECCION_CLIENTE` varchar(50) NOT NULL,
  `TELEFONO_CLIENTE` varchar(20) NOT NULL,
  `ESTADO_CLIENTE` varchar(1) NOT NULL,
  `TIPO_DOCUMENTO` varchar(2) NOT NULL,
  `FECHA_AFILIACION` date NOT NULL,
  `FECHA_NACIMIENTO` date NOT NULL,
  PRIMARY KEY (`ID_CLIENTE`),
  KEY `FK_RELATIONSHIP_35` (`ID_USUARIO`),
  KEY `FK_RELATIONSHIP_4` (`ID_EPS`),
  CONSTRAINT `FK_RELATIONSHIP_4` FOREIGN KEY (`ID_EPS`) REFERENCES `eps` (`ID_EPS`),
  CONSTRAINT `FK_RELATIONSHIP_35` FOREIGN KEY (`ID_USUARIO`) REFERENCES `usuarios` (`ID_USUARIO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,1,1,'Juan Nicolas Martinez',1049564322,'Calle 6 No 12-36','7443018','A','CC','1995-08-09','1984-04-28'),(2,2,2,'Julio Cesar Reina',1049904322,'Carrera 6 No 35-67','6543018','A','CC','1997-03-11','1994-05-21'),(3,3,1,'Juan Mateo Gonzalez',1242563322,'Transversal 37 No 123-46','8454048','A','CC','1999-07-17','1989-07-13'),(4,4,2,'Maria Perez Sanchez',1044364372,'Calle 6 No 67-82','7453510','A','TI','2005-05-05','2003-02-15'),(5,5,1,'Oscar Suarez',1349964382,'Carrera 32 No 12-36','7683513','A','CC','2017-04-23','1988-08-16'),(6,6,2,'Jonathan Camargo',1063564322,'Transversal 65 No 14-05','7123709','A','CC','2006-01-27','1994-09-13'),(7,7,1,'Ana Garcia',1549864722,'Calle 21 No 13-56','5460358','A','CC','2011-06-18','1991-11-11'),(8,8,2,'Juan Parada',1747564822,'Calle 65 No 78-09','7403931','A','TI','2012-09-14','2002-12-14'),(9,9,1,'Rodolfo Balvin',1049364322,'Carrera 90 No 39-55','7436017','A','CC','2008-02-19','1992-03-06'),(10,10,2,'Andrea Rojas',1040564322,'Transversal 6 No 44-33','7453012','A','CC','1999-10-20','1974-06-09');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuentas`
--

DROP TABLE IF EXISTS `cuentas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuentas` (
  `NUMERO_CUENTA` varchar(20) NOT NULL,
  `ID_CLIENTE` int(11) NOT NULL,
  `NOMBRE_TITULAR` varchar(30) NOT NULL,
  `TIPO_CUENTA` varchar(1) NOT NULL,
  `CLAVE_CUENTA` varchar(50) NOT NULL,
  `SALDO_CUENTA` int(11) NOT NULL,
  PRIMARY KEY (`NUMERO_CUENTA`),
  KEY `FK_RELATIONSHIP_37` (`ID_CLIENTE`),
  CONSTRAINT `FK_RELATIONSHIP_37` FOREIGN KEY (`ID_CLIENTE`) REFERENCES `clientes` (`ID_CLIENTE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuentas`
--

LOCK TABLES `cuentas` WRITE;
/*!40000 ALTER TABLE `cuentas` DISABLE KEYS */;
INSERT INTO `cuentas` VALUES ('099209948',9,'Rodolfo Balvin','C','1256',1890000),('123124444',10,'Andrea Rojas','C','9021',2328000),('123456789',1,'Juan Nicolas Martinez','A','3456',10000000),('123456799',2,'Julio Cesar Reina','C','4567',5000000),('123456999',3,'Juan Mateo Gonzalez','A','3829',2500000),('123459999',4,'Maria Perez Sanchez','A','3228',1350000),('232343389',5,'Oscar Suarez','C','2343',2340560),('234234789',7,'Ana Garcia','A','4648',23344444),('324236789',8,'Juan Parada','C','9021',3324222),('673234489',6,'Jonathan Camargo','C','2444',3452000);
/*!40000 ALTER TABLE `cuentas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuotas`
--

DROP TABLE IF EXISTS `cuotas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuotas` (
  `ID_CUOTA` int(11) NOT NULL,
  `ID_CLIENTE` int(11) NOT NULL,
  `VALOR_CUOTA` int(11) NOT NULL,
  `FECHA_CUOTA` date NOT NULL,
  PRIMARY KEY (`ID_CUOTA`),
  KEY `FK_RELATIONSHIP_24` (`ID_CLIENTE`),
  CONSTRAINT `FK_RELATIONSHIP_24` FOREIGN KEY (`ID_CLIENTE`) REFERENCES `clientes` (`ID_CLIENTE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuotas`
--

LOCK TABLES `cuotas` WRITE;
/*!40000 ALTER TABLE `cuotas` DISABLE KEYS */;
INSERT INTO `cuotas` VALUES (1,1,81000,'2015-05-15'),(2,2,84000,'2015-05-22'),(3,3,85000,'2015-05-05'),(4,4,83000,'2015-05-12'),(5,5,87000,'2015-05-13'),(6,6,88000,'2015-05-04'),(7,7,89000,'2015-05-06'),(8,8,81000,'2015-05-09'),(9,9,82000,'2015-05-11'),(10,10,88000,'2015-05-20');
/*!40000 ALTER TABLE `cuotas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `descripciones`
--

DROP TABLE IF EXISTS `descripciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `descripciones` (
  `ID_PROCEDIMIENTO` int(11) NOT NULL,
  `ID_HISTORIA` int(11) NOT NULL,
  PRIMARY KEY (`ID_PROCEDIMIENTO`,`ID_HISTORIA`),
  KEY `FK_RELATIONSHIP_41` (`ID_HISTORIA`),
  CONSTRAINT `FK_RELATIONSHIP_41` FOREIGN KEY (`ID_HISTORIA`) REFERENCES `historias_ips` (`ID_HISTORIA`),
  CONSTRAINT `FK_RELATIONSHIP_40` FOREIGN KEY (`ID_PROCEDIMIENTO`) REFERENCES `procedimientos` (`ID_PROCEDIMIENTO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `descripciones`
--

LOCK TABLES `descripciones` WRITE;
/*!40000 ALTER TABLE `descripciones` DISABLE KEYS */;
INSERT INTO `descripciones` VALUES (1,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,9),(10,10),(11,11),(12,12),(13,13),(14,14),(15,15),(16,16),(17,17),(18,18),(19,19),(20,20),(1,21),(2,22),(3,23),(4,24),(5,25),(6,26),(7,27),(8,28),(9,29),(10,30),(11,31),(12,32),(13,33),(14,34),(15,35),(16,36),(17,37),(18,38),(19,39),(20,40),(1,41),(2,42),(3,43),(4,44),(5,45),(6,46),(7,47),(8,48),(9,49),(10,50),(11,51),(12,52),(13,53),(14,54),(15,55),(16,56),(17,57),(18,58),(19,59),(20,60);
/*!40000 ALTER TABLE `descripciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dias`
--

DROP TABLE IF EXISTS `dias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dias` (
  `ID_DIA` int(11) NOT NULL,
  `NOMBRE_DIA` varchar(10) NOT NULL,
  PRIMARY KEY (`ID_DIA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dias`
--

LOCK TABLES `dias` WRITE;
/*!40000 ALTER TABLE `dias` DISABLE KEYS */;
INSERT INTO `dias` VALUES (1,'Monday'),(2,'Thuesday'),(3,'Wednesday'),(4,'Thursday'),(5,'Friday'),(6,'Saturday');
/*!40000 ALTER TABLE `dias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dispositivos`
--

DROP TABLE IF EXISTS `dispositivos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dispositivos` (
  `ID_DISPOSITIVO` int(11) NOT NULL,
  `NOMBRE_DISPOSITIVO` varchar(50) NOT NULL,
  `COSTO_DISPOSITIVO` varchar(20) NOT NULL,
  `DESCRIPCION_DISPOSITIVO` varchar(150) NOT NULL,
  PRIMARY KEY (`ID_DISPOSITIVO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dispositivos`
--

LOCK TABLES `dispositivos` WRITE;
/*!40000 ALTER TABLE `dispositivos` DISABLE KEYS */;
INSERT INTO `dispositivos` VALUES (1,'Estetoscopio','50000','utilizado para escuchar los latidos del corazón en los pacientes'),(2,'Marcapasos','200000','aparato mecanico generador de impulsos'),(3,'Electrodos','50000','Utilizados en los electrocardiogramas para medir los diferentes impulsos del corazon'),(4,'Laser','550000','Utilizado en las cirugias ya que ayuda a la cicatrización de heridas utilizado en operaciones de riñón'),(5,'Electrobisturi','350000','equipo electronico capaz de transformar la energía eléctrica en calor'),(6,'Otoscopio','100000','usado para examinar el canal auditivo del paciente'),(7,'Tensiometro electronico','200000','dispositivo que mide la presion en las arterias al llenarse de aire'),(8,'Gantry','2000000','scanner utilizado para la deteccion de diferentes tipos de señales para su procesamiento'),(9,'Respirador artificial','900000','disositivo utilizado para suministrar oxigeno a los pacientes que tengan insuficiencias respiratorias o se encuentren sedados'),(10,'Negatoscopio','500000','dispositivo que permite la visualization de las radiografias con un sistema de iluminacion'),(11,'Equipo microgotero','300000','dispositivo para la suministracion de suero a los pacientes internados'),(12,'Oftalmoscopio','150000','dispositivo que permite ver el estado del ojo del paciente'),(13,'Aspirador electrico','200000',''),(14,'lanringoscopio','300000',''),(15,'Amalgamador','100000',''),(16,'Compresor','300000',''),(17,'Dispositivo quirurgico criogenico','400000',''),(18,'Dispositivo antichoque para succion','200000',''),(19,'Equipo de toma de muestras de sangre','100000',''),(20,'Analizador automatico de pruebas hematologicas','600000',''),(21,'Desfibrilador','300000','utilizado para enviar descargar de corrientes con un elevado voltaje para realizar resucitacion');
/*!40000 ALTER TABLE `dispositivos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctores`
--

DROP TABLE IF EXISTS `doctores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doctores` (
  `ID_DOCTOR` int(11) NOT NULL,
  `ID_TIPO` int(11) NOT NULL,
  `ID_SEDE_IPS` int(11) NOT NULL,
  `NOMBRE_DOCTOR` varchar(30) NOT NULL,
  PRIMARY KEY (`ID_DOCTOR`),
  KEY `FK_RELATIONSHIP_28` (`ID_TIPO`),
  KEY `FK_RELATIONSHIP_33` (`ID_SEDE_IPS`),
  CONSTRAINT `FK_RELATIONSHIP_33` FOREIGN KEY (`ID_SEDE_IPS`) REFERENCES `sedes_ips` (`ID_SEDE_IPS`),
  CONSTRAINT `FK_RELATIONSHIP_28` FOREIGN KEY (`ID_TIPO`) REFERENCES `tipos_cita` (`ID_TIPO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctores`
--

LOCK TABLES `doctores` WRITE;
/*!40000 ALTER TABLE `doctores` DISABLE KEYS */;
INSERT INTO `doctores` VALUES (1,1,1,'Jose Vargas'),(2,1,2,'Maria Paez'),(3,2,3,'Ivan Rojas'),(4,2,4,'Miguel Sanabria'),(5,2,5,'Carlos Zapata'),(6,3,6,'Martha Chaparro'),(7,3,7,'Luis Gonzalez'),(8,3,8,'Sebastian Aguirre'),(9,4,9,'Lina Parra'),(10,4,10,'Juliana Molina'),(11,4,11,'Oscar Pacheco'),(12,5,12,'Monica Castillo'),(13,5,1,'Daniel Diaz'),(14,5,2,'Julio Venegas'),(15,6,3,'Javier Lopez'),(16,6,4,'Ricardo Vivas'),(17,6,5,'Jesus Gomez'),(18,1,6,'Felipe Perez'),(19,2,7,'Jose Perez'),(20,3,8,'Juan Galindo'),(21,4,9,'Hernan Fonseca'),(22,5,10,'Diego Guerrero');
/*!40000 ALTER TABLE `doctores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eps`
--

DROP TABLE IF EXISTS `eps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eps` (
  `ID_EPS` int(11) NOT NULL,
  `NOMBRE_EPS` varchar(30) NOT NULL,
  PRIMARY KEY (`ID_EPS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eps`
--

LOCK TABLES `eps` WRITE;
/*!40000 ALTER TABLE `eps` DISABLE KEYS */;
INSERT INTO `eps` VALUES (1,'SaludCoop'),(2,'FamiSanar');
/*!40000 ALTER TABLE `eps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `farmacias`
--

DROP TABLE IF EXISTS `farmacias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `farmacias` (
  `ID_FARMACIA` int(11) NOT NULL,
  `ID_SEDE_EPS` int(11) NOT NULL,
  `DIRECCION_FARMACIA` varchar(20) NOT NULL,
  `NOMBRE_FARMACIA` varchar(20) NOT NULL,
  PRIMARY KEY (`ID_FARMACIA`),
  KEY `FK_RELATIONSHIP_5` (`ID_SEDE_EPS`),
  CONSTRAINT `FK_RELATIONSHIP_5` FOREIGN KEY (`ID_SEDE_EPS`) REFERENCES `sedes_eps` (`ID_SEDE_EPS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `farmacias`
--

LOCK TABLES `farmacias` WRITE;
/*!40000 ALTER TABLE `farmacias` DISABLE KEYS */;
INSERT INTO `farmacias` VALUES (1,1,'carrera 12 #12-93','farmacia central'),(2,1,'carrera 7 #12-05','farmacia principal'),(3,5,'carrera 26 #11-25','farmacia central'),(4,5,'carrera 6 #11-40','farmacia auxiliar');
/*!40000 ALTER TABLE `farmacias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `herramientas`
--

DROP TABLE IF EXISTS `herramientas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `herramientas` (
  `ID_PROCEDIMIENTO` int(11) NOT NULL,
  `ID_DISPOSITIVO` int(11) NOT NULL,
  PRIMARY KEY (`ID_PROCEDIMIENTO`,`ID_DISPOSITIVO`),
  KEY `FK_RELATIONSHIP_39` (`ID_DISPOSITIVO`),
  CONSTRAINT `FK_RELATIONSHIP_39` FOREIGN KEY (`ID_DISPOSITIVO`) REFERENCES `dispositivos` (`ID_DISPOSITIVO`),
  CONSTRAINT `FK_RELATIONSHIP_38` FOREIGN KEY (`ID_PROCEDIMIENTO`) REFERENCES `procedimientos` (`ID_PROCEDIMIENTO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `herramientas`
--

LOCK TABLES `herramientas` WRITE;
/*!40000 ALTER TABLE `herramientas` DISABLE KEYS */;
INSERT INTO `herramientas` VALUES (1,1),(7,1),(17,1),(3,2),(6,2),(18,2),(17,3),(19,3),(2,4),(20,4),(1,5),(3,5),(4,5),(18,5),(4,6),(5,6),(3,7),(4,7),(5,7),(16,7),(2,8),(6,8),(15,8),(20,8),(1,9),(7,9),(2,10),(8,10),(8,11),(19,11),(7,12),(9,12),(14,12),(6,13),(9,13),(10,13),(13,13),(11,14),(10,15),(12,15),(12,16),(13,16),(5,17),(14,17),(11,18),(15,18),(16,19);
/*!40000 ALTER TABLE `herramientas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historias_ips`
--

DROP TABLE IF EXISTS `historias_ips`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `historias_ips` (
  `ID_HISTORIA` int(11) NOT NULL,
  `ID_CLIENTE` int(11) NOT NULL,
  `ID_IPS` int(11) NOT NULL,
  PRIMARY KEY (`ID_HISTORIA`),
  KEY `FK_RELATIONSHIP_36` (`ID_IPS`),
  KEY `FK_RELATIONSHIP_47` (`ID_CLIENTE`),
  CONSTRAINT `FK_RELATIONSHIP_47` FOREIGN KEY (`ID_CLIENTE`) REFERENCES `clientes` (`ID_CLIENTE`),
  CONSTRAINT `FK_RELATIONSHIP_36` FOREIGN KEY (`ID_IPS`) REFERENCES `ips` (`ID_IPS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historias_ips`
--

LOCK TABLES `historias_ips` WRITE;
/*!40000 ALTER TABLE `historias_ips` DISABLE KEYS */;
INSERT INTO `historias_ips` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,6),(8,1,1),(9,1,4),(10,1,5),(11,2,1),(12,2,2),(13,2,3),(14,2,4),(15,2,5),(16,2,6),(17,2,1),(18,2,3),(19,2,4),(20,2,5),(21,3,1),(22,3,2),(23,3,3),(24,3,4),(25,3,5),(26,3,6),(27,3,1),(28,3,2),(29,3,3),(30,3,4),(31,4,1),(32,4,2),(33,4,3),(34,4,4),(35,4,5),(36,4,6),(37,4,5),(38,4,6),(39,4,1),(40,4,2),(41,5,1),(42,5,2),(43,5,3),(44,5,4),(45,5,5),(46,5,6),(47,5,3),(48,5,2),(49,5,1),(50,5,4),(51,6,1),(52,6,2),(53,6,3),(54,6,4),(55,6,5),(56,6,6),(57,6,1),(58,6,2),(59,6,4),(60,6,3);
/*!40000 ALTER TABLE `historias_ips` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `horas`
--

DROP TABLE IF EXISTS `horas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `horas` (
  `ID_HORA` int(11) NOT NULL,
  `HORA_DISPONIBLE` time NOT NULL,
  PRIMARY KEY (`ID_HORA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `horas`
--

LOCK TABLES `horas` WRITE;
/*!40000 ALTER TABLE `horas` DISABLE KEYS */;
INSERT INTO `horas` VALUES (1,'08:00:00'),(2,'08:30:00'),(3,'09:00:00'),(4,'09:30:00'),(5,'10:00:00'),(6,'10:30:00'),(7,'01:00:00'),(8,'11:30:00'),(9,'12:00:00'),(10,'12:30:00'),(11,'13:00:00'),(12,'13:30:00'),(13,'14:00:00'),(14,'14:30:00'),(15,'15:00:00'),(16,'15:30:00'),(17,'16:00:00'),(18,'16:30:00'),(19,'17:00:00'),(20,'17:30:00'),(21,'18:00:00');
/*!40000 ALTER TABLE `horas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `informaciones`
--

DROP TABLE IF EXISTS `informaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `informaciones` (
  `ID_INFORMACION` int(11) NOT NULL,
  `ID_IPS` int(11) NOT NULL,
  `DESCRIPCION_INFORMACION` varchar(100) NOT NULL,
  `TIPO_INFORMACION` varchar(1) NOT NULL,
  PRIMARY KEY (`ID_INFORMACION`),
  KEY `FK_RELATIONSHIP_14` (`ID_IPS`),
  CONSTRAINT `FK_RELATIONSHIP_14` FOREIGN KEY (`ID_IPS`) REFERENCES `ips` (`ID_IPS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `informaciones`
--

LOCK TABLES `informaciones` WRITE;
/*!40000 ALTER TABLE `informaciones` DISABLE KEYS */;
INSERT INTO `informaciones` VALUES (1,1,'Nueva terapia específica confirma su eficacia contra el cáncer de mama','N'),(2,1,'V Simposio de Urgencias Médicas con enfoque a médicos rurales','E'),(3,2,'Informe de seguimiento y evaluación realizado al Plan Anticorrupción y Mapa de riesgos de corrupción','N'),(4,2,'1er Curso Novedades terapéuticas en Medicina Interna','E'),(5,3,'Pioneros en cirugia cardiovascular','N'),(6,3,'Jornada de Diagnóstico de Enfermedades del Corazón para Niños','E'),(7,4,'NUEVO PROCEDIMIENTO PARA TRATAR LA FIBRILACIÓN AURICULAR','N'),(8,4,'II Simposio de Cardiología Invasiva y Cirugía Cardiovascular','E'),(9,5,'TRATAMIENTO ENDOSCÓPICO PARA EL ENFISEMA','N'),(10,5,'Olimpiadas Nacionales y Festival de Talento 2012','E'),(11,6,'LA MEDICINA DEL DOLOR Y LOS CUIDADOS PALIATIVOS','N'),(12,6,'Rendición de Cuentas Gestión 2014','E');
/*!40000 ALTER TABLE `informaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ips`
--

DROP TABLE IF EXISTS `ips`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ips` (
  `ID_IPS` int(11) NOT NULL,
  `ID_EPS` int(11) DEFAULT NULL,
  `NOMBRE_IPS` varchar(30) NOT NULL,
  PRIMARY KEY (`ID_IPS`),
  KEY `FK_RELATIONSHIP_29` (`ID_EPS`),
  CONSTRAINT `FK_RELATIONSHIP_29` FOREIGN KEY (`ID_EPS`) REFERENCES `eps` (`ID_EPS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ips`
--

LOCK TABLES `ips` WRITE;
/*!40000 ALTER TABLE `ips` DISABLE KEYS */;
INSERT INTO `ips` VALUES (1,1,'Clinica los Andes'),(2,1,'Hospital San Rafael'),(3,1,'Hospital Meissen'),(4,2,'Hospital San Jose'),(5,2,'Clinica Santa Teresa'),(6,2,'Clinica SaludCooop');
/*!40000 ALTER TABLE `ips` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jornadas`
--

DROP TABLE IF EXISTS `jornadas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jornadas` (
  `ID_HORA` int(11) NOT NULL,
  `ID_DIA` int(11) NOT NULL,
  `TIPO_JORNADA` varchar(1) NOT NULL,
  PRIMARY KEY (`ID_HORA`,`ID_DIA`),
  KEY `FK_RELATIONSHIP_45` (`ID_DIA`),
  CONSTRAINT `FK_RELATIONSHIP_45` FOREIGN KEY (`ID_DIA`) REFERENCES `dias` (`ID_DIA`),
  CONSTRAINT `FK_RELATIONSHIP_44` FOREIGN KEY (`ID_HORA`) REFERENCES `horas` (`ID_HORA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jornadas`
--

LOCK TABLES `jornadas` WRITE;
/*!40000 ALTER TABLE `jornadas` DISABLE KEYS */;
INSERT INTO `jornadas` VALUES (1,1,'M'),(1,2,'M'),(1,3,'M'),(1,4,'M'),(1,5,'M'),(1,6,'M'),(2,1,'M'),(2,2,'M'),(2,3,'M'),(2,4,'M'),(2,5,'M'),(2,6,'M'),(3,1,'M'),(3,2,'M'),(3,3,'M'),(3,4,'M'),(3,5,'M'),(3,6,'M'),(4,1,'M'),(4,2,'M'),(4,3,'M'),(4,4,'M'),(4,5,'M'),(4,6,'M'),(5,1,'M'),(5,2,'M'),(5,3,'M'),(5,4,'M'),(5,5,'M'),(5,6,'M'),(6,1,'M'),(6,2,'M'),(6,3,'M'),(6,4,'M'),(6,5,'M'),(6,6,'M'),(7,1,'M'),(7,2,'M'),(7,3,'M'),(7,4,'M'),(7,5,'M'),(7,6,'M'),(8,1,'M'),(8,2,'M'),(8,3,'M'),(8,4,'M'),(8,5,'M'),(8,6,'M'),(9,1,'M'),(9,2,'M'),(9,3,'M'),(9,4,'M'),(9,5,'M'),(9,6,'M'),(10,1,'M'),(10,2,'M'),(10,3,'M'),(10,4,'M'),(10,5,'M'),(10,6,'M'),(11,1,'T'),(11,2,'T'),(11,3,'T'),(11,4,'T'),(11,5,'T'),(12,1,'T'),(12,2,'T'),(12,3,'T'),(12,4,'T'),(12,5,'T'),(13,1,'T'),(13,2,'T'),(13,3,'T'),(13,4,'T'),(13,5,'T'),(14,1,'T'),(14,2,'T'),(14,3,'T'),(14,4,'T'),(14,5,'T'),(15,1,'T'),(15,2,'T'),(15,3,'T'),(15,4,'T'),(15,5,'T'),(16,1,'T'),(16,2,'T'),(16,3,'T'),(16,4,'T'),(16,5,'T'),(17,1,'T'),(17,2,'T'),(17,3,'T'),(17,4,'T'),(17,5,'T'),(18,1,'T'),(18,2,'T'),(18,3,'T'),(18,4,'T'),(18,5,'T'),(19,1,'T'),(19,2,'T'),(19,3,'T'),(19,4,'T'),(19,5,'T'),(20,1,'T'),(20,2,'T'),(20,3,'T'),(20,4,'T'),(20,5,'T'),(21,1,'T'),(21,2,'T'),(21,3,'T'),(21,4,'T'),(21,5,'T');
/*!40000 ALTER TABLE `jornadas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `laboratorios`
--

DROP TABLE IF EXISTS `laboratorios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `laboratorios` (
  `ID_LABORATORIO` int(11) NOT NULL,
  `ID_SEDE_EPS` int(11) NOT NULL,
  `NOMBRE_LABORATORIO` varchar(20) NOT NULL,
  `DIRECCION_LABORATORIO` varchar(20) NOT NULL,
  PRIMARY KEY (`ID_LABORATORIO`),
  KEY `FK_RELATIONSHIP_8` (`ID_SEDE_EPS`),
  CONSTRAINT `FK_RELATIONSHIP_8` FOREIGN KEY (`ID_SEDE_EPS`) REFERENCES `sedes_eps` (`ID_SEDE_EPS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `laboratorios`
--

LOCK TABLES `laboratorios` WRITE;
/*!40000 ALTER TABLE `laboratorios` DISABLE KEYS */;
INSERT INTO `laboratorios` VALUES (1,1,'toma de muestras','calle 2 #34-67'),(2,2,'analisis','carrera 34 #12-64'),(3,1,'especialidades','calle 25 #23-12'),(4,4,'toma de muestras','carrera 10 #10-45'),(5,5,'analisis','transversal 2 #30-19'),(6,5,'especialidades','calle 3 #15-34');
/*!40000 ALTER TABLE `laboratorios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lugares`
--

DROP TABLE IF EXISTS `lugares`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lugares` (
  `ID_LUGAR` int(11) NOT NULL,
  `NOMBRE_LUGAR` varchar(30) NOT NULL,
  PRIMARY KEY (`ID_LUGAR`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lugares`
--

LOCK TABLES `lugares` WRITE;
/*!40000 ALTER TABLE `lugares` DISABLE KEYS */;
INSERT INTO `lugares` VALUES (1,'Tunja'),(2,'Chiquinquira'),(3,'Bogota'),(4,'Medellin'),(5,'Bucaramanga'),(6,'Cali'),(7,'Popayan'),(8,'Cartagena'),(9,'Manizales'),(10,'Arauca');
/*!40000 ALTER TABLE `lugares` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operaciones`
--

DROP TABLE IF EXISTS `operaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operaciones` (
  `ID_OPERACION` int(11) NOT NULL,
  `CUENTA_TRANSACCION` varchar(20) NOT NULL,
  `TIPO_OPERACION` varchar(1) NOT NULL,
  PRIMARY KEY (`ID_OPERACION`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operaciones`
--

LOCK TABLES `operaciones` WRITE;
/*!40000 ALTER TABLE `operaciones` DISABLE KEYS */;
INSERT INTO `operaciones` VALUES (1,'343434343','C'),(2,'453455455','C');
/*!40000 ALTER TABLE `operaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `procedimientos`
--

DROP TABLE IF EXISTS `procedimientos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `procedimientos` (
  `ID_PROCEDIMIENTO` int(11) NOT NULL,
  `NOMBRE_PROCEDIMIENTO` varchar(50) NOT NULL,
  PRIMARY KEY (`ID_PROCEDIMIENTO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `procedimientos`
--

LOCK TABLES `procedimientos` WRITE;
/*!40000 ALTER TABLE `procedimientos` DISABLE KEYS */;
INSERT INTO `procedimientos` VALUES (1,'Revision general'),(2,'Oftalmologico'),(3,'Tomografia'),(4,'Radiografia'),(5,'Retinografia'),(6,'Resonancia Magnetica'),(7,'Tratamiento de piedras riñon'),(8,'Terapia fisica'),(9,'Transplante'),(10,'Cirugia ocular con lasik'),(11,'Cirugia de columna lumbar'),(12,'Laringoscopia'),(13,'Traqueostomia'),(14,'Reparacion de ligamento cruzado anterior'),(15,'Cirugia de cataratas'),(16,'Electrocardiograma'),(17,'Reparacion quirurgica de una hernia umbilical'),(18,'Apendicectomia'),(19,'Conteo completo de la sangre'),(20,'Transplante de higado');
/*!40000 ALTER TABLE `procedimientos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `revisiones`
--

DROP TABLE IF EXISTS `revisiones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `revisiones` (
  `ID_CLIENTE` int(11) NOT NULL,
  `ID_HISTORIA` int(11) NOT NULL,
  `NOMBRE_CONSULTANTE` varchar(50) NOT NULL,
  `DOCUMENTO_CONSULTANTE` varchar(20) NOT NULL,
  `TELEFONO_CONSULTANTE` varchar(20) NOT NULL,
  PRIMARY KEY (`ID_CLIENTE`,`ID_HISTORIA`),
  KEY `FK_RELATIONSHIP_18` (`ID_HISTORIA`),
  CONSTRAINT `FK_RELATIONSHIP_6` FOREIGN KEY (`ID_CLIENTE`) REFERENCES `clientes` (`ID_CLIENTE`),
  CONSTRAINT `FK_RELATIONSHIP_18` FOREIGN KEY (`ID_HISTORIA`) REFERENCES `historias_ips` (`ID_HISTORIA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `revisiones`
--

LOCK TABLES `revisiones` WRITE;
/*!40000 ALTER TABLE `revisiones` DISABLE KEYS */;
/*!40000 ALTER TABLE `revisiones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sedes_eps`
--

DROP TABLE IF EXISTS `sedes_eps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sedes_eps` (
  `ID_SEDE_EPS` int(11) NOT NULL,
  `ID_EPS` int(11) NOT NULL,
  `ID_LUGAR` int(11) NOT NULL,
  `NOMBRE_SEDE_EPS` varchar(30) NOT NULL,
  `DIRECCION_SEDE_EPS` varchar(20) NOT NULL,
  PRIMARY KEY (`ID_SEDE_EPS`),
  KEY `FK_RELATIONSHIP_25` (`ID_EPS`),
  KEY `FK_RELATIONSHIP_34` (`ID_LUGAR`),
  CONSTRAINT `FK_RELATIONSHIP_34` FOREIGN KEY (`ID_LUGAR`) REFERENCES `lugares` (`ID_LUGAR`),
  CONSTRAINT `FK_RELATIONSHIP_25` FOREIGN KEY (`ID_EPS`) REFERENCES `eps` (`ID_EPS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sedes_eps`
--

LOCK TABLES `sedes_eps` WRITE;
/*!40000 ALTER TABLE `sedes_eps` DISABLE KEYS */;
INSERT INTO `sedes_eps` VALUES (1,1,1,'Sede principal','carrera 7 #12-05'),(2,1,4,'Centro de atencion al Cliente','calle 30 #25-11'),(3,1,6,'Sede administrativa','Avenida Sur #1-67'),(4,2,2,'Sede administrativa','carrera 2 #1-30'),(5,2,1,'Sede principal','carrera 26 #11-25'),(6,2,8,'Atencion, quejas y reclamos','carrera 6 #11-25');
/*!40000 ALTER TABLE `sedes_eps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sedes_ips`
--

DROP TABLE IF EXISTS `sedes_ips`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sedes_ips` (
  `ID_SEDE_IPS` int(11) NOT NULL,
  `ID_IPS` int(11) NOT NULL,
  `ID_LUGAR` int(11) NOT NULL,
  `nombre_sede_ips` varchar(50) NOT NULL,
  `DIERECCION_IPS` varchar(20) NOT NULL,
  PRIMARY KEY (`ID_SEDE_IPS`),
  KEY `FK_RELATIONSHIP_20` (`ID_IPS`),
  KEY `FK_RELATIONSHIP_31` (`ID_LUGAR`),
  CONSTRAINT `FK_RELATIONSHIP_20` FOREIGN KEY (`ID_IPS`) REFERENCES `ips` (`ID_IPS`),
  CONSTRAINT `FK_RELATIONSHIP_31` FOREIGN KEY (`ID_LUGAR`) REFERENCES `lugares` (`ID_LUGAR`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sedes_ips`
--

LOCK TABLES `sedes_ips` WRITE;
/*!40000 ALTER TABLE `sedes_ips` DISABLE KEYS */;
INSERT INTO `sedes_ips` VALUES (1,1,1,'Clinica los Andes Tunja','Trans.11 No 30-61'),(2,1,3,'Clinica los Andes Bogota','Calle 6 No 11-15'),(3,2,5,'Hospital San Rafael Bucaramanga','Calle 20 No 102-23'),(4,2,2,'Hospital San Rafael Chiquinquira','Carrera 33 No 34-67'),(5,3,6,'Hospital Meissen Cali','Calle 37 No 12-22'),(6,3,8,'Hospital Meissen Cartagena','Carrera 83 No 32F-22'),(7,4,4,'Hospital San Jose Medellin','Calle 170 No 102-2D'),(8,4,7,'Hospital San Jose Popayan','Carrera 2 No 33-63C'),(9,5,9,'Hospital San Teresa Manizalez','Calle 20 No 102-23'),(10,5,10,'Hospital San Teresa Arauca','Carrera 33 No 34-67'),(11,6,1,'Clinica SaludCooop Tunja','Calle 43 No 33-05'),(12,6,3,'Clinica SaludCooop Bogota','Carrera 28 No 63-62');
/*!40000 ALTER TABLE `sedes_ips` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicios`
--

DROP TABLE IF EXISTS `servicios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servicios` (
  `ID_SEDE_IPS` int(11) NOT NULL,
  `ID_PROCEDIMIENTO` int(11) NOT NULL,
  PRIMARY KEY (`ID_SEDE_IPS`,`ID_PROCEDIMIENTO`),
  KEY `FK_RELATIONSHIP_17` (`ID_PROCEDIMIENTO`),
  CONSTRAINT `FK_RELATIONSHIP_17` FOREIGN KEY (`ID_PROCEDIMIENTO`) REFERENCES `procedimientos` (`ID_PROCEDIMIENTO`),
  CONSTRAINT `FK_RELATIONSHIP_16` FOREIGN KEY (`ID_SEDE_IPS`) REFERENCES `sedes_ips` (`ID_SEDE_IPS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicios`
--

LOCK TABLES `servicios` WRITE;
/*!40000 ALTER TABLE `servicios` DISABLE KEYS */;
INSERT INTO `servicios` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(11,1),(12,1),(1,2),(2,2),(3,2),(4,2),(5,2),(6,2),(7,2),(8,2),(9,2),(10,2),(11,2),(12,2),(1,3),(2,3),(3,3),(4,3),(5,3),(6,3),(7,3),(8,3),(9,3),(10,3),(11,3),(12,3),(1,4),(2,4),(3,4),(4,4),(5,4),(6,4),(7,4),(8,4),(9,4),(10,4),(11,4),(12,4),(1,5),(2,5),(3,5),(4,5),(5,5),(6,5),(7,5),(8,5),(9,5),(10,5),(11,5),(12,5),(1,6),(2,6),(3,6),(4,6),(5,6),(6,6),(7,6),(8,6),(9,6),(10,6),(11,6),(12,6),(1,7),(2,7),(3,7),(4,7),(5,7),(6,7),(7,7),(8,7),(9,7),(10,7),(11,7),(12,7),(1,8),(2,8),(3,8),(4,8),(5,8),(6,8),(7,8),(8,8),(9,8),(10,8),(11,8),(12,8),(1,9),(2,9),(3,9),(4,9),(5,9),(6,9),(7,9),(8,9),(9,9),(10,9),(11,9),(12,9),(2,10),(3,10),(4,10),(5,10),(6,10),(7,10),(9,10),(11,10),(2,11),(5,11),(7,11),(2,12),(3,12),(5,12),(11,12),(12,12),(2,13),(3,13),(8,13),(9,13),(10,13),(12,13),(2,14),(3,14),(6,14),(7,14),(8,14),(10,14),(12,14),(1,15),(3,15),(4,15),(8,15),(9,15),(10,15),(12,15),(1,16),(6,16),(8,16),(9,16),(12,16),(1,17),(6,17),(8,17),(10,17),(1,18),(4,18),(5,18),(6,18),(9,18),(10,18),(11,18),(4,19),(7,19),(11,19),(4,20),(5,20),(7,20),(11,20);
/*!40000 ALTER TABLE `servicios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipos_cita`
--

DROP TABLE IF EXISTS `tipos_cita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipos_cita` (
  `ID_TIPO` int(11) NOT NULL,
  `NOMBRE_TIPO` varchar(30) NOT NULL,
  PRIMARY KEY (`ID_TIPO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos_cita`
--

LOCK TABLES `tipos_cita` WRITE;
/*!40000 ALTER TABLE `tipos_cita` DISABLE KEYS */;
INSERT INTO `tipos_cita` VALUES (1,'Medicina General'),(2,'Odontologia'),(3,'Optometria'),(4,'Oftalmologia'),(5,'Psicologia'),(6,'Fisioterapia');
/*!40000 ALTER TABLE `tipos_cita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `turnos`
--

DROP TABLE IF EXISTS `turnos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `turnos` (
  `ID_DOCTOR` int(11) NOT NULL,
  `ID_DIA` int(11) NOT NULL,
  PRIMARY KEY (`ID_DOCTOR`,`ID_DIA`),
  KEY `FK_RELATIONSHIP_43` (`ID_DIA`),
  CONSTRAINT `FK_RELATIONSHIP_43` FOREIGN KEY (`ID_DIA`) REFERENCES `dias` (`ID_DIA`),
  CONSTRAINT `FK_RELATIONSHIP_42` FOREIGN KEY (`ID_DOCTOR`) REFERENCES `doctores` (`ID_DOCTOR`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turnos`
--

LOCK TABLES `turnos` WRITE;
/*!40000 ALTER TABLE `turnos` DISABLE KEYS */;
INSERT INTO `turnos` VALUES (1,1),(2,1),(3,1),(4,1),(7,1),(8,1),(9,1),(10,1),(11,1),(13,1),(14,1),(15,1),(17,1),(18,1),(19,1),(20,1),(21,1),(1,2),(3,2),(4,2),(5,2),(7,2),(8,2),(9,2),(10,2),(11,2),(12,2),(14,2),(15,2),(17,2),(18,2),(19,2),(20,2),(21,2),(1,3),(2,3),(3,3),(4,3),(7,3),(8,3),(10,3),(11,3),(12,3),(15,3),(17,3),(18,3),(19,3),(21,3),(2,4),(3,4),(4,4),(5,4),(6,4),(8,4),(9,4),(10,4),(11,4),(12,4),(14,4),(15,4),(16,4),(17,4),(18,4),(19,4),(21,4),(1,5),(4,5),(5,5),(6,5),(7,5),(8,5),(9,5),(10,5),(11,5),(13,5),(14,5),(15,5),(16,5),(17,5),(18,5),(20,5),(1,6),(2,6),(3,6),(4,6),(7,6),(9,6),(13,6),(14,6),(16,6),(17,6),(18,6),(20,6);
/*!40000 ALTER TABLE `turnos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `ID_USUARIO` int(11) NOT NULL,
  `NOMBRE_USUARIO` varchar(30) NOT NULL,
  `CONTRASENIA` varchar(50) NOT NULL,
  PRIMARY KEY (`ID_USUARIO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Juan Nicolas Martinez','jnm123'),(2,'Julio Cesar Reina','jcr123'),(3,'Juan Mateo Gonzalez','jmg123'),(4,'Maria Perez Sanchez','mps123'),(5,'Oscar Suarez','os123'),(6,'Jonathan Camargo','jc123'),(7,'Ana Garcia','ag123'),(8,'Juan Parada','jp123'),(9,'Rodolfo Balvin','rb123'),(10,'Andrea Rojas','ar123');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `valores`
--

DROP TABLE IF EXISTS `valores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `valores` (
  `NUMERO_CUENTA` varchar(20) NOT NULL,
  `ID_OPERACION` int(11) NOT NULL,
  `VALOR_OPERACION` int(11) NOT NULL,
  `FECHA_OPERACION` date NOT NULL,
  PRIMARY KEY (`NUMERO_CUENTA`,`ID_OPERACION`),
  KEY `FK_RELATIONSHIP_22` (`ID_OPERACION`),
  CONSTRAINT `FK_RELATIONSHIP_22` FOREIGN KEY (`ID_OPERACION`) REFERENCES `operaciones` (`ID_OPERACION`),
  CONSTRAINT `FK_RELATIONSHIP_21` FOREIGN KEY (`NUMERO_CUENTA`) REFERENCES `cuentas` (`NUMERO_CUENTA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `valores`
--

LOCK TABLES `valores` WRITE;
/*!40000 ALTER TABLE `valores` DISABLE KEYS */;
INSERT INTO `valores` VALUES ('099209948',1,82000,'2015-05-11'),('123124444',2,88000,'2015-05-20'),('123456789',1,81000,'2015-05-15'),('123456799',2,84000,'2015-05-22'),('123456999',2,85000,'2015-05-05'),('123459999',2,83000,'2015-05-12'),('232343389',1,87000,'2015-05-13'),('234234789',2,89000,'2015-05-06'),('324236789',1,81000,'2015-05-09'),('673234489',1,88000,'2015-05-04');
/*!40000 ALTER TABLE `valores` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-06-01 13:21:41
