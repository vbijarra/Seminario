CREATE DATABASE  IF NOT EXISTS `inc` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `inc`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: inc
-- ------------------------------------------------------
-- Server version	8.0.37

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
-- Table structure for table `aplicaciones`
--

DROP TABLE IF EXISTS `aplicaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aplicaciones` (
  `Id_Aplicacion` int NOT NULL,
  `Desc_Aplicacion` varchar(45) NOT NULL,
  PRIMARY KEY (`Id_Aplicacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aplicaciones`
--

LOCK TABLES `aplicaciones` WRITE;
/*!40000 ALTER TABLE `aplicaciones` DISABLE KEYS */;
INSERT INTO `aplicaciones` VALUES (1,'Sys_Admin'),(2,'Production'),(3,'Sales System'),(4,'RH System'),(5,'Hardware'),(6,'Telefonia');
/*!40000 ALTER TABLE `aplicaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `areas`
--

DROP TABLE IF EXISTS `areas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `areas` (
  `Id_Area` int NOT NULL,
  `Desc_Area` varchar(45) NOT NULL,
  PRIMARY KEY (`Id_Area`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `areas`
--

LOCK TABLES `areas` WRITE;
/*!40000 ALTER TABLE `areas` DISABLE KEYS */;
INSERT INTO `areas` VALUES (1,'Administración'),(2,'Producción'),(3,'Envasado'),(4,'Recursos Humanos'),(5,'Sistemas'),(6,'Ventas'),(7,'Dirección'),(8,'Compras');
/*!40000 ALTER TABLE `areas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleados`
--

DROP TABLE IF EXISTS `empleados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleados` (
  `Legajo` int NOT NULL,
  `Nombre` varchar(45) NOT NULL,
  `Apellido` varchar(45) NOT NULL,
  `Fecha_Ingreso` date NOT NULL,
  `Fecha_Egreso` date DEFAULT NULL,
  `Id_Area` int NOT NULL,
  PRIMARY KEY (`Legajo`),
  KEY `ID_AREA_FK_idx` (`Id_Area`),
  CONSTRAINT `ID_AREA_FK` FOREIGN KEY (`Id_Area`) REFERENCES `areas` (`Id_Area`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleados`
--

LOCK TABLES `empleados` WRITE;
/*!40000 ALTER TABLE `empleados` DISABLE KEYS */;
INSERT INTO `empleados` VALUES (1001,'Emanuel','Biondini','2012-05-01',NULL,1),(1002,'Sofia','Gomez','2012-02-01',NULL,6),(1003,'Joaquin','Saomero','2013-05-01',NULL,2),(1004,'Elena','Romero','2015-01-01',NULL,4),(1005,'Juan','Soler','2011-04-01',NULL,2),(1006,'Emanuel','Donati','2014-02-01',NULL,8),(1007,'Geremias','Lazarini','2011-06-01',NULL,5),(1008,'Emanuel','Murua','2014-07-01',NULL,5);
/*!40000 ALTER TABLE `empleados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `errores`
--

DROP TABLE IF EXISTS `errores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `errores` (
  `Id_Error` int NOT NULL,
  `Id_Pantalla` int NOT NULL,
  `Codigo` varchar(10) NOT NULL,
  `Descripcion` varchar(250) NOT NULL,
  `Pasos_Reprod` varchar(250) NOT NULL,
  `Id_Tipo_Prob` int NOT NULL,
  PRIMARY KEY (`Id_Error`),
  KEY `Id_Pantalla_FK_idx` (`Id_Pantalla`),
  KEY `Id_TipoProb_idx` (`Id_Tipo_Prob`),
  CONSTRAINT `Id_Pantalla_FK` FOREIGN KEY (`Id_Pantalla`) REFERENCES `pantallas` (`Id_Pantalla`),
  CONSTRAINT `Id_Tipo_Prob_FK` FOREIGN KEY (`Id_Tipo_Prob`) REFERENCES `tipo_problemas` (`Id_Tipo_Prob`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `errores`
--

LOCK TABLES `errores` WRITE;
/*!40000 ALTER TABLE `errores` DISABLE KEYS */;
INSERT INTO `errores` VALUES (1,1,'1002','Problema al registar factura. Se generó error 1002 por inconsistencia tipo de dato al registrar registro.','Luego de seleccionar el proveedor OLEOS DEL SUR, al dar tab, aparece error 1002.',1),(2,22,'1000','Error al registrar nuevo Transporte.','Luego de colocar información de razón social, chasis y choferes aparece error 1000-No Data Found.',1),(3,5,'1010','Error al registrar pago.','Luego de seleccionar el banco CITI cta 9987, aparece error 1010, Código de Banco es NULL.',1),(4,5,'1010','Error al registrar pago.','Luego de seleccionar el banco CITI cta 9987, aparece error 1010, Código de Banco es NULL.',1),(5,1,'001','DJJDJDJD','JDJJDJD',1),(6,1,'001','DKDKD','KDKDKD',1),(7,8,'001','jdjdjd','jdjdjd',1),(8,1,'001','jdjdd','jdjdjd',1),(9,1,'001','jejeje','jjeje',1),(10,1,'001','djdjjd','jdjdjd',1),(11,31,'001','kdkdkdk','kdkdkd',1),(12,31,'001','kdkdkd','kdkdkd',1),(13,2,'001','ksksks','ksksks',1),(14,1,'001','jdjdjd','jdjdjd',1),(15,5,'001','kdkdkd','kdkdkd',1),(16,5,'001','kdkdkd','kdkdk',1),(17,1,'','kdkdkd','kdkdkd',1),(18,5,'001','kdkdkd','kdkdkd',1),(19,5,'001','kakaka','kakaka',1),(20,1,'001','dkdkdkd','kdkdkd',1),(21,1,'001','kkekeke','lelele',1),(22,1,'001','kksksk','ksksks',1),(23,5,'001','kdkdkd','kdkdkd',1),(24,5,'001','kakakaka','kakakak',1),(25,1,'001','jdjdjjd','lelele',1),(26,5,'001','kkdkd','kdkdk',1),(27,5,'001','KSKSKS','KSKSKS',1),(28,5,'001','kdkdk','dkdkkd',1),(29,5,'001','ksksks','ksksks',1),(30,5,'001','ksksks','ksksks',1),(31,5,'001','kakaka','kakaka',1),(32,5,'001','jdjdjd','jdjdjd',1),(33,5,'002','kdkdkd','kdkdkd',1),(34,5,'001','kkfkfkf','kfkfkf',1),(35,5,'002','kkdkdd','kdkdkd',1),(36,5,'001','kkdkdk','kdkdd',1),(37,5,'001','kdkdkdk','kdkdkd',1),(38,5,'001','kdkdkd','kdkdkd',1),(39,5,'002','kdkdkd','kdkdkd',1),(40,5,'001','kdkdkd','kdkdkd',1),(41,5,'001','lflfllf','lkfkfkfk',1),(42,5,'001','kkdkdk','kdkdkd',1),(43,1,'001','kdkdkd','kkdkdkd',1),(44,5,'001','jjfjfj','kfkfkf',1),(45,1,'001','kdkdkd','kdkdkd',1),(46,1,'001','jdjdjd','jdjdjdj',1),(47,1,'001','jejej','kkekek',1),(48,5,'001','jdjdjdj','kdkdkd',1),(49,1,'001','kkdkdk','kfkfk',1),(50,5,'001','kdkdkd','kdkdkdkd',1),(51,1,'001','fkfkfkf','kfkfkfkf',1),(52,22,'001','Error al registrar nuevo Transporte','Luego de colocar información de razón social, chasis y choferes aparece error 1000-No Data Found. dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd',1),(53,22,'001','ddddddddddddddddddddddddd','ddddddddddddddddddddddddddddddddddddddddddddddddddddd',1),(54,1,'001','kfkfkfkf','kfkfkf',1),(55,5,'001','ddddddddddd','dddddddddddddddddddddddd',1),(56,1,'001','ksksks','skskks',1),(57,5,'001','kkfkkf','kfkfkfkf',1),(58,1,'001','kkkeke','kekeke',1),(59,5,'001','','kdkdkd',1),(60,1,'001','dkdkdkd','kkdkdkd',1),(61,1,'001','kdkdkdk','kdkdkd',1);
/*!40000 ALTER TABLE `errores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estados`
--

DROP TABLE IF EXISTS `estados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estados` (
  `Id_Estado` int NOT NULL,
  `Desc_Estado` varchar(45) NOT NULL,
  PRIMARY KEY (`Id_Estado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estados`
--

LOCK TABLES `estados` WRITE;
/*!40000 ALTER TABLE `estados` DISABLE KEYS */;
INSERT INTO `estados` VALUES (1,'Abierto'),(2,'Cancelado'),(3,'Asignado'),(4,'Cerrado');
/*!40000 ALTER TABLE `estados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `incidentes`
--

DROP TABLE IF EXISTS `incidentes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `incidentes` (
  `Id_Incidente` int NOT NULL,
  `Id_Usuario` int NOT NULL,
  `Id_Resolutor` int(10) unsigned zerofill DEFAULT NULL,
  `Fecha` date NOT NULL,
  `Anexo` longblob,
  `Id_Error` int NOT NULL,
  `Prioridad` int NOT NULL,
  `Id_Estado` int NOT NULL,
  `Id_Solucion` int DEFAULT NULL,
  PRIMARY KEY (`Id_Incidente`),
  KEY `Id_Res_FK` (`Id_Resolutor`),
  KEY `Id_Err_FK_idx` (`Id_Error`),
  KEY `Id_Sol_FK_idx` (`Id_Solucion`),
  KEY `Id_Est_FK_idx` (`Id_Estado`),
  KEY `Id_Usu_FK_idx` (`Id_Usuario`),
  CONSTRAINT `Id_Err_FK` FOREIGN KEY (`Id_Error`) REFERENCES `errores` (`Id_Error`),
  CONSTRAINT `Id_Est_FK` FOREIGN KEY (`Id_Estado`) REFERENCES `estados` (`Id_Estado`),
  CONSTRAINT `Id_Sol_FK` FOREIGN KEY (`Id_Solucion`) REFERENCES `soluciones` (`Id_Solucion`),
  CONSTRAINT `Id_Usu_FK` FOREIGN KEY (`Id_Usuario`) REFERENCES `usuarios` (`Id_Usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incidentes`
--

LOCK TABLES `incidentes` WRITE;
/*!40000 ALTER TABLE `incidentes` DISABLE KEYS */;
INSERT INTO `incidentes` VALUES (1,1,0000000001,'2024-05-10',NULL,1,2,1,3),(2,2,NULL,'2024-05-10',NULL,2,2,1,NULL),(3,1,0000000001,'2024-05-11',NULL,3,2,4,1),(4,1,0000000001,'2024-05-15',NULL,4,2,4,2),(5,1,NULL,'2024-06-04',NULL,5,2,2,NULL),(6,1,NULL,'2024-06-04',NULL,6,2,2,NULL),(7,1,NULL,'2024-06-04',NULL,7,2,2,NULL),(8,1,NULL,'2024-06-05',NULL,8,2,2,NULL),(9,1,NULL,'2024-06-05',NULL,9,2,2,NULL),(10,1,NULL,'2024-06-05',NULL,10,2,2,NULL),(11,1,NULL,'2024-06-05',NULL,11,2,2,NULL),(12,1,NULL,'2024-06-05',NULL,12,2,2,NULL),(13,1,NULL,'2024-06-05',NULL,13,2,2,NULL),(14,1,NULL,'2024-06-06',NULL,14,2,2,NULL),(15,1,NULL,'2024-06-06',NULL,15,2,2,NULL),(16,1,NULL,'2024-06-06',NULL,16,1,2,NULL),(17,1,NULL,'2024-06-06',NULL,17,1,2,NULL),(18,1,NULL,'2024-06-06',NULL,18,2,2,NULL),(19,1,NULL,'2024-06-06',NULL,19,2,2,NULL),(20,1,NULL,'2024-06-06',NULL,20,1,2,NULL),(21,1,NULL,'2024-06-06',NULL,21,2,2,NULL),(22,1,NULL,'2024-06-06',NULL,22,1,2,NULL),(23,1,NULL,'2024-06-06',NULL,23,2,2,NULL),(24,1,NULL,'2024-06-06',NULL,24,2,2,NULL),(25,1,NULL,'2024-06-06',NULL,25,2,2,NULL),(26,1,NULL,'2024-06-06',NULL,26,1,2,NULL),(27,1,NULL,'2024-06-06',NULL,27,2,2,NULL),(28,1,NULL,'2024-06-06',NULL,28,2,2,NULL),(29,1,NULL,'2024-06-06',NULL,29,2,2,NULL),(30,1,NULL,'2024-06-06',NULL,30,2,1,NULL),(31,1,NULL,'2024-06-06',NULL,31,2,2,NULL),(32,1,NULL,'2024-06-06',NULL,32,2,2,NULL),(33,1,NULL,'2024-06-06',NULL,33,2,1,NULL),(34,1,NULL,'2024-06-07',NULL,34,2,1,NULL),(35,1,NULL,'2024-06-07',NULL,35,2,1,NULL),(36,1,NULL,'2024-06-07',NULL,36,2,1,NULL),(37,1,NULL,'2024-06-07',NULL,37,2,1,NULL),(38,1,NULL,'2024-06-07',NULL,38,2,1,NULL),(39,1,NULL,'2024-06-07',NULL,39,2,2,NULL),(40,1,NULL,'2024-06-07',NULL,40,2,2,NULL),(41,1,NULL,'2024-06-07',NULL,41,2,1,NULL),(42,1,NULL,'2024-06-07',NULL,42,1,2,NULL),(43,1,NULL,'2024-06-07',NULL,43,2,2,NULL),(44,1,NULL,'2024-06-07',NULL,44,2,2,NULL),(45,7,NULL,'2024-06-07',NULL,45,2,1,NULL),(46,1,NULL,'2024-06-08',NULL,46,2,2,NULL),(47,1,NULL,'2024-06-08',NULL,47,1,1,NULL),(48,1,NULL,'2024-06-08',NULL,48,2,1,NULL),(49,1,NULL,'2024-06-08',NULL,49,1,2,NULL),(50,1,NULL,'2024-06-08',NULL,50,2,2,NULL),(51,1,NULL,'2024-06-08',NULL,51,2,2,NULL),(52,1,NULL,'2024-06-08',NULL,53,1,2,NULL),(53,1,NULL,'2024-06-08',NULL,54,1,2,NULL),(54,1,NULL,'2024-06-08',NULL,55,2,2,NULL),(55,1,NULL,'2024-06-08',NULL,58,1,2,NULL),(56,1,NULL,'2024-06-08',NULL,59,2,1,NULL),(57,1,NULL,'2024-06-09',NULL,60,1,2,NULL),(58,1,NULL,'2024-06-09',NULL,61,2,2,NULL);
/*!40000 ALTER TABLE `incidentes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modulos`
--

DROP TABLE IF EXISTS `modulos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `modulos` (
  `Id_Modulo` int NOT NULL,
  `Desc_Modulo` varchar(45) NOT NULL,
  `Id_Aplicacion` int NOT NULL,
  PRIMARY KEY (`Id_Modulo`),
  KEY `Id_Aplicacion_FK_idx` (`Id_Aplicacion`),
  CONSTRAINT `Id_Aplicacion_FK` FOREIGN KEY (`Id_Aplicacion`) REFERENCES `aplicaciones` (`Id_Aplicacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modulos`
--

LOCK TABLES `modulos` WRITE;
/*!40000 ALTER TABLE `modulos` DISABLE KEYS */;
INSERT INTO `modulos` VALUES (1,'Administración',1),(2,'Pagos',1),(3,'Producción',2),(4,'Empaquetado',2),(5,'Pedidos',3),(6,'Facturación',3),(7,'Empleados',4),(8,'Liquidación',4),(9,'Beneficios',4),(10,'Hardware',5),(11,'Telefonía',6);
/*!40000 ALTER TABLE `modulos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pantallas`
--

DROP TABLE IF EXISTS `pantallas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pantallas` (
  `Id_Pantalla` int NOT NULL,
  `Desc_Pantalla` varchar(45) NOT NULL,
  `Id_Proceso` int NOT NULL,
  PRIMARY KEY (`Id_Pantalla`),
  KEY `Id_Proceso_FK_idx` (`Id_Proceso`),
  CONSTRAINT `Id_Proceso_FK` FOREIGN KEY (`Id_Proceso`) REFERENCES `procesos` (`Id_Proceso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pantallas`
--

LOCK TABLES `pantallas` WRITE;
/*!40000 ALTER TABLE `pantallas` DISABLE KEYS */;
INSERT INTO `pantallas` VALUES (1,'Factura',1),(2,'Linea Factura',1),(3,'Proveedor',2),(4,'Dirección de Proveedor',2),(5,'Pago',3),(6,'Bancos',4),(7,'Termino Pago',4),(8,'Tipo de Pago',4),(9,'Producción L1',5),(10,'Producción L2',5),(11,'Producción L3',5),(12,'Items',6),(13,'Formulas',6),(14,'Maquinas',6),(15,'Empaquetado',7),(16,'Pedido',8),(17,'Linea Pedido',8),(18,'Precios',9),(19,'Lineas Precio',9),(20,'Calificador de Precio',9),(21,'Despacho',10),(22,'Transporte',10),(23,'Factura',11),(24,'Linea Factura',11),(25,'Impuesto Factura',11),(26,'Impresor Factura',11),(27,'Cliente',12),(28,'Dirección Cliente',12),(29,'Impuestos Cliente',12),(30,'Comunicación Cliente',12),(31,'Lote de Pago',13),(32,'Aplicación de Pago',13),(33,'Ingreso Pago',13),(34,'Detalle de Cuenta',13),(35,'Persona',14),(36,'Empleado',14),(37,'Asignación de Area',14),(38,'Promociones',15),(39,'Liquidación',16),(40,'Lectura Reloj',17),(41,'Detalle E/S',17),(42,'Inasistencias',17),(43,'Otorgar Beneficios',18),(44,'Historial Beneficio',19),(45,'Alta Beneficios',20),(46,'Impresora',22),(47,'Monitor',22),(48,'Teclado',22),(49,'Mouse',22),(50,'Cpu',22),(51,'Notebook',22),(52,'Impresora',21),(53,'Monitor',21),(54,'Teclado',21),(55,'Mouse',21),(56,'Cpu',21),(57,'Notebook',21),(58,'Analogico',23),(59,'Digital',23),(60,'Celular',23),(61,'Analogico',24),(62,'Digital',24),(63,'Celular',24),(64,'Analogico',25),(65,'Digital',25),(66,'Celular',25);
/*!40000 ALTER TABLE `pantallas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `procesos`
--

DROP TABLE IF EXISTS `procesos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `procesos` (
  `Id_Proceso` int NOT NULL,
  `Desc_Proceso` varchar(45) NOT NULL,
  `Id_Modulo` int NOT NULL,
  PRIMARY KEY (`Id_Proceso`),
  KEY `Id_Modulo_FK_idx` (`Id_Modulo`),
  CONSTRAINT `Id_Modulo_FK` FOREIGN KEY (`Id_Modulo`) REFERENCES `modulos` (`Id_Modulo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `procesos`
--

LOCK TABLES `procesos` WRITE;
/*!40000 ALTER TABLE `procesos` DISABLE KEYS */;
INSERT INTO `procesos` VALUES (1,'Ingreso Facturas',1),(2,'Proveedores',1),(3,'Ingreso Pagos',2),(4,'Configuración',2),(5,'Ingreso Producción',3),(6,'Configuración',3),(7,'Empaquetar',4),(8,'Ingreso Pedido',5),(9,'Definir Precios',5),(10,'Despachar',5),(11,'Facturación',6),(12,'Clientes',6),(13,'Registrar Cobranza',1),(14,'Alta/Baja Empleado',7),(15,'Promoción',7),(16,'Liquidación',8),(17,'Asistencia',8),(18,'Otorgar Beneficio',9),(19,'Historial de Beneficio',9),(20,'Configuración Beneficio',9),(21,'Asignar Hardware',10),(22,'Cambiar Hardware',10),(23,'Asignar Telefono',11),(24,'Desasignar Telefono',11),(25,'Reparar Telefono ',11);
/*!40000 ALTER TABLE `procesos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resolutores`
--

DROP TABLE IF EXISTS `resolutores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resolutores` (
  `Id_Resolutor` int NOT NULL,
  `Id_Usuario` int NOT NULL,
  `Id_Tipo_Prob` int NOT NULL,
  PRIMARY KEY (`Id_Resolutor`,`Id_Usuario`),
  KEY `Id_Usuario_PK_idx` (`Id_Usuario`),
  KEY `Id_Tipo_Prob_idx` (`Id_Tipo_Prob`),
  CONSTRAINT `Id_Tipo_Prob` FOREIGN KEY (`Id_Tipo_Prob`) REFERENCES `tipo_problemas` (`Id_Tipo_Prob`),
  CONSTRAINT `Id_Usuario_PK` FOREIGN KEY (`Id_Usuario`) REFERENCES `usuarios` (`Id_Usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resolutores`
--

LOCK TABLES `resolutores` WRITE;
/*!40000 ALTER TABLE `resolutores` DISABLE KEYS */;
INSERT INTO `resolutores` VALUES (1,7,1),(2,8,2);
/*!40000 ALTER TABLE `resolutores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `soluciones`
--

DROP TABLE IF EXISTS `soluciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `soluciones` (
  `Id_Solucion` int NOT NULL,
  `Desc_Corta` varchar(30) NOT NULL,
  `Desc_Larga` varchar(250) NOT NULL,
  `Id_Resolutor` int NOT NULL,
  `Fecha` date NOT NULL,
  `Anexo` longblob,
  `Id_Error` int NOT NULL,
  `Tipo_Usuario` tinyint(1) NOT NULL,
  `Desc_Path` varchar(100) DEFAULT NULL,
  `Permisos` varchar(250) DEFAULT NULL,
  `Prerequisitos` varchar(250) DEFAULT NULL,
  `Script` varchar(250) DEFAULT NULL,
  `Herramienta` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id_Solucion`),
  KEY `Id_Resolutor_FK_idx` (`Id_Resolutor`),
  KEY `Id_Error_FK_idx` (`Id_Error`),
  CONSTRAINT `Id_Error_FK` FOREIGN KEY (`Id_Error`) REFERENCES `errores` (`Id_Error`),
  CONSTRAINT `Id_Resolutor_FK` FOREIGN KEY (`Id_Resolutor`) REFERENCES `resolutores` (`Id_Resolutor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `soluciones`
--

LOCK TABLES `soluciones` WRITE;
/*!40000 ALTER TABLE `soluciones` DISABLE KEYS */;
INSERT INTO `soluciones` VALUES (1,'Se agregó código de banco. ','Se accede por la configuración de bancos y se agrega código de banco válido.',1,'2024-05-11',NULL,3,1,'Configuración > Entidades > Bancos','Acceso a Modulo de Configuración',NULL,NULL,NULL),(2,'Se quitaron simbolos. ','Se accede por la configuración de sucursal de bancos y se quitan los simbolos especiales.',1,'2024-05-26',NULL,3,1,'Configuración > Entidades > Bancos > sucursal Bancaria','Acceso a Modulo de Configuración',NULL,NULL,NULL),(3,'Se ejecuta update','Se ejecuta update en tabla vendors_all',1,'2024-05-27',NULL,1,2,NULL,NULL,NULL,'update vendors_all set vendor_name = ltrim(vendor_name) where vendor_id = 122; ','SQL DEVELOPER'),(4,'Se ejecuta update','Se ejecuta update en tabla vendors_all',1,'2024-05-27',NULL,1,2,NULL,NULL,NULL,'update vendors_all set vendor_number = to_number(vendor_number) where vendor_id = 122; ','SQL DEVELOPER');
/*!40000 ALTER TABLE `soluciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_problemas`
--

DROP TABLE IF EXISTS `tipo_problemas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_problemas` (
  `Id_Tipo_Prob` int NOT NULL,
  `Desc_Tipo_Prob` varchar(45) NOT NULL,
  PRIMARY KEY (`Id_Tipo_Prob`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_problemas`
--

LOCK TABLES `tipo_problemas` WRITE;
/*!40000 ALTER TABLE `tipo_problemas` DISABLE KEYS */;
INSERT INTO `tipo_problemas` VALUES (1,'Sistemas'),(2,'IT');
/*!40000 ALTER TABLE `tipo_problemas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `Id_Usuario` int NOT NULL,
  `Usuario` varchar(45) NOT NULL,
  `Clave` varchar(45) NOT NULL,
  `Estado` varchar(45) NOT NULL,
  `mail` varchar(45) NOT NULL,
  `Legajo` int NOT NULL,
  `Tipo_Usuario` int NOT NULL,
  PRIMARY KEY (`Id_Usuario`),
  KEY `Legajo_idx` (`Legajo`),
  CONSTRAINT `Legajo` FOREIGN KEY (`Legajo`) REFERENCES `empleados` (`Legajo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'EBIONDINI','123','Activo','ebiondini@gmail.com',1001,1),(2,'SGOMEZ','123','Activo','sgomez@gmail.com',1002,1),(3,'JSAOMERO','123','Activo','jsaomero@gmail.com',1003,1),(4,'EROMERO','123','Activo','eromero@gmail.com',1004,1),(5,'JSOLER','123','Activo','jsolr@gmail.com',1005,1),(6,'EDONATI','123','Activo','edonati@gmail.com',1006,1),(7,'GLAZARINI','123','Activo','glazarini@gmail.com',1007,2),(8,'EMURUA','123','Actvo','emurua@gmail.com',1008,2);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-09 13:01:28
