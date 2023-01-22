-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: supplychainmanagement
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `price` decimal(8,2) NOT NULL,
  `stock` int NOT NULL,
  `description` varchar(1000) NOT NULL,
  `supplier_id` int NOT NULL,
  `discount` decimal(3,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`product_id`),
  FULLTEXT KEY `IDX_Product_Name` (`name`),
  CONSTRAINT `ChkStock` CHECK ((`stock` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Lenovo Ideapad 3 Dizüstü Bilgisayar',8999.00,3,'Hafif ve taşıması kolay IdeaPad 3 dizüstü bilgisayarın muhteşem 15,6\" FHD ekranını, sahip olduğu geniş görüntüleme oranı sayesinde, birden fazla kişiye neredeyse her açıdan gösterebilirsiniz. AMD Ryzen Mobil İşlemci, nereye giderseniz gidin iş istasyonu benzeri bir kapasiteyi de yanınızda götürmenizi mümkün kılar.\r\nKristal netliğinde 720p HD web kamerası ve diğer cihazlarla sorunsuz entegrasyon sağlayan tam işlevli bir USB-C bağlantı noktası dahil birçok özelliğe sahip IdeaPad 3 dizüstü bilgisayarınızı istediğiniz yere götürebilirsiniz.',1,0.12),(2,'Lenovo Ideapad Gaming 3 Dizüstü Bilgisayar',18980.00,11,'IdeaPad Gaming 3 dizüstü bilgisayar, hızlı refleksleri barındıran bir ekranla zaferi sunar. 15,6\" FHD ekran, görselleri net ve yırtılmadan tutar, böylece birden fazla oyun içi hedefi edinebilir.\r\nIdeaPad Gaming 3\'ün geniş klavyesi ve her türlü aydınlatma durumunda önemli görünürlüğe yardımcı olmak için arka aydınlatma ile oyuncular için tasarlanmıştır.',1,0.00),(3,'Logitech K380 Çoklu Cihaz Bluetooth Klavye',648.35,80,'Bilgisayarda, telefonda, tablette ve diğer cihazlarda yazı yazmak için ideal, kolayca bluetooth ile bağlanabilen, ultra ince tasarımlı Çoklu Cihaz klavyesidir.\r\nPembe, Beyaz ve Siyah renk seçenekleriyle, minimalist ve modern tasarımıyla, tarzınıza ve masa düzeninize uyum sağlar. Ayrıca hafif ve kompakt olduğu için kolayca taşınabilir.\r\nHarici klavye desteği olan tüm Bluetooth kablosuz aygıtlara bağlanma özelliğiyle Windows, macOS, iPadOS, Chrome OS, Android, iOS ve hatta Apple TV üzerinden kolaylıkla çalışmanıza olanak tanır.',2,0.00),(4,'Lenovo Tab P11 Tablet',7991.00,0,'İşlemci: Qualcomm Snapdragon 662, 2 GHz\r\nİşletim Sistemi: Android 11\r\nRAM: 4 GB\r\nEkran Boyutu: 11\'\' 2K\r\nÜrün ile ilgili diğer tüm özelliklere Ürün kılavuzları ve Belgeleri alanındaki ekli ürün belgesi üzerinden ulaşabilirsiniz',1,0.00),(5,'MSI KATANA GF76 11UC-638TR Dizüstü Bilgisayar',36899.00,0,'Ekran Boyutu: 17.3\" inç FHD\r\nİşletim Sistemi: Intel Core i7-11800H\r\nÇözünürlük: 1920 x 1080 pixel',3,0.00);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-22 20:57:40
