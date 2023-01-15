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
  `retailer_id` int NOT NULL,
  `discount` decimal(3,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`product_id`),
  KEY `FK_Retailer_Retailer_Id_idx` (`retailer_id`),
  FULLTEXT KEY `IDX_Product_Name` (`name`),
  CONSTRAINT `FK_Retailer_Retailer_Id` FOREIGN KEY (`retailer_id`) REFERENCES `retailer` (`retailer_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ChkStock` CHECK ((`stock` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (23,'Lenovo Ideapad Gaming 3 DizÃ¼stÃ¼ Bilgisayar',19978.99,10,'IdeaPad Gaming 3 dizÃ¼stÃ¼ bilgisayar, hÄ±zlÄ± refleksleri barÄ±ndÄ±ran bir ekranla zaferi sunar. 15,6\" FHD ekran, gÃ¶rselleri net ve yÄ±rtÄ±lmadan tutar, bÃ¶ylece birden fazla oyun iÃ§i hedefi edinebilir.\r\nIdeaPad Gaming 3\'Ã¼n geniÅŸ klavyesi ve her tÃ¼rlÃ¼ aydÄ±nlatma durumunda Ã¶nemli gÃ¶rÃ¼nÃ¼rlÃ¼ÄŸe yardÄ±mcÄ± olmak iÃ§in arka aydÄ±nlatma ile oyuncular iÃ§in tasarlanmÄ±ÅŸtÄ±r.',4,0.00),(24,'Lenovo IdeaPad Gaming M100 RGB Mouse',78.92,100,'800 ila 3200 DPI arasÄ±nda ayarlanabilir Ã§Ã¶zÃ¼nÃ¼rlÃ¼ÄŸe sahip hassas optik sensÃ¶r.\r\nSaÄŸ elle kullanÄ±m iÃ§in 2 yan dÃ¼ÄŸmeli ergonomik tasarÄ±m',4,0.00),(25,'Asus Vivobook 15 Bilgisayar',10499.00,25,'Ä°ÅŸlemci Modeli : AMD Ryzen 5 4600H, Ä°ÅŸlemci HÄ±zÄ± : 3.00 GHz, Ä°ÅŸlemci : AMD Ryzen 5, Ä°ÅŸletim Sistemi : FreeDOS, Ekran Ã–zelliÄŸi : 15.6\", Ekran KartÄ± : AMD Radeon Graphics, Ekran KartÄ± HafÄ±zasÄ± : PaylaÅŸÄ±mlÄ±, Ekran Ã‡Ã¶zÃ¼nÃ¼rlÃ¼ÄŸÃ¼ : 1920 x 1080, Sistem BelleÄŸi : 8 GB, Kapasite : 256 GB SSD, Optik SÃ¼rÃ¼cÃ¼ : Yok, Klavye : SayÄ±sal TuÅŸ TakÄ±mlÄ±, Kablosuz HaberleÅŸme : Var, Bluetooth Ã–zelliÄŸi : Var, HDMI : Var , Garanti : 24 Ay Asus TÃ¼rkiye Garantili, VGA : Yok, Ethernet (LAN) : Yok, Ekran : Full HD, AÄŸÄ±rlÄ±k : 1.70 kg, SSD : 256 GB, SSD Slotu : M.2 NVMe PCIe 3.0, Ã‡ekirdek SayÄ±sÄ± : AltÄ± Ã‡ekirdekli Ä°ÅŸlemci, Ekran KartÄ± Bellek Tipi : DDR4, USB Port : 1x USB 2.0 Type-A / 2x USB 2.0 Type-A / 2x USB 3.2 Gen 1 Type-C, Model Kodu : M1502IA-EJ132, Marka : Asus',5,0.00),(26,'ASUS ROG STRIX G15 Bilgisayar',40619.00,1,'ASUS ROG STRIX G15 AMD RYZEN 7-6800H 16GB DDR5 1TB SSD 8GB RTX3070TI 15.6\" FHD 300Hz FREEDOS',5,0.00);
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

-- Dump completed on 2023-01-15 23:32:50
