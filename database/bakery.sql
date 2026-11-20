-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.4.3 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for sincere_bakery
CREATE DATABASE IF NOT EXISTS `sincere_bakery` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sincere_bakery`;

-- Dumping structure for table sincere_bakery.daily_reports
CREATE TABLE IF NOT EXISTS `daily_reports` (
  `report_id` int NOT NULL AUTO_INCREMENT,
  `report_date` date NOT NULL,
  `total_income` decimal(14,2) NOT NULL DEFAULT '0.00',
  `total_transactions` int NOT NULL DEFAULT '0',
  `closed_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`report_id`),
  UNIQUE KEY `report_date` (`report_date`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table sincere_bakery.daily_reports: ~1 rows (approximately)
INSERT INTO `daily_reports` (`report_id`, `report_date`, `total_income`, `total_transactions`, `closed_at`) VALUES
	(1, '2026-11-20', 0.00, 0, '2026-11-20 08:21:47');

-- Dumping structure for table sincere_bakery.products
CREATE TABLE IF NOT EXISTS `products` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `product_name` varchar(100) NOT NULL,
  `category` varchar(50) DEFAULT NULL,
  `price` decimal(12,2) NOT NULL,
  `stock` int NOT NULL DEFAULT '0',
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`product_id`),
  CONSTRAINT `products_chk_1` CHECK ((`price` >= 0)),
  CONSTRAINT `products_chk_2` CHECK ((`stock` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table sincere_bakery.products: ~5 rows (approximately)
INSERT INTO `products` (`product_id`, `product_name`, `category`, `price`, `stock`, `is_active`) VALUES
	(1, 'Roti Cokelat', 'Roti', 12000.00, 29, 1),
	(2, 'Croissant', 'Pastry', 18000.00, 18, 1),
	(3, 'Donat Gula', 'Donat', 8000.00, 40, 1),
	(4, 'Donat Keju', 'Donat', 15000.00, 20, 1),
	(5, 'Roti Stoberi', 'Roti', 12000.00, 100, 0);

-- Dumping structure for table sincere_bakery.transactions
CREATE TABLE IF NOT EXISTS `transactions` (
  `transaction_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `report_id` int DEFAULT NULL,
  `transaction_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `total_price` decimal(14,2) NOT NULL,
  `payment_type` varchar(30) NOT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `fk_transaction_user` (`user_id`),
  KEY `fk_transaction_report` (`report_id`),
  CONSTRAINT `fk_transaction_report` FOREIGN KEY (`report_id`) REFERENCES `daily_reports` (`report_id`),
  CONSTRAINT `fk_transaction_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table sincere_bakery.transactions: ~1 rows (approximately)
INSERT INTO `transactions` (`transaction_id`, `user_id`, `report_id`, `transaction_date`, `total_price`, `payment_type`) VALUES
	(1, 1, NULL, '2026-11-20 10:54:43', 48000.00, 'Tunai');

-- Dumping structure for table sincere_bakery.transaction_details
CREATE TABLE IF NOT EXISTS `transaction_details` (
  `detail_id` bigint NOT NULL AUTO_INCREMENT,
  `transaction_id` bigint NOT NULL,
  `product_id` int NOT NULL,
  `product_name` varchar(100) NOT NULL,
  `quantity` int NOT NULL,
  `unit_price` decimal(12,2) NOT NULL,
  `subtotal` decimal(14,2) NOT NULL,
  PRIMARY KEY (`detail_id`),
  KEY `fk_detail_transaction` (`transaction_id`),
  KEY `fk_detail_product` (`product_id`),
  CONSTRAINT `fk_detail_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`),
  CONSTRAINT `fk_detail_transaction` FOREIGN KEY (`transaction_id`) REFERENCES `transactions` (`transaction_id`),
  CONSTRAINT `transaction_details_chk_1` CHECK ((`quantity` > 0))
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table sincere_bakery.transaction_details: ~2 rows (approximately)
INSERT INTO `transaction_details` (`detail_id`, `transaction_id`, `product_id`, `product_name`, `quantity`, `unit_price`, `subtotal`) VALUES
	(1, 1, 1, 'Roti Cokelat', 1, 12000.00, 12000.00),
	(2, 1, 2, 'Croissant', 2, 18000.00, 36000.00);

-- Dumping structure for table sincere_bakery.users
CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `role` enum('CASHIER','STOCK_MANAGER') NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table sincere_bakery.users: ~2 rows (approximately)
INSERT INTO `users` (`user_id`, `username`, `password`, `full_name`, `role`, `created_at`) VALUES
	(1, 'cashier', 'cashier123', 'Cashier Demo', 'CASHIER', '2026-11-13 11:19:03'),
	(2, 'stock', 'stock123', 'Stock Manager Demo', 'STOCK_MANAGER', '2026-11-13 11:19:03');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
