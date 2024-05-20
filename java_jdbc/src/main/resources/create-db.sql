CREATE TABLE `products` (
    `id` int unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(45) DEFAULT NULL,
    `price` int unsigned DEFAULT NULL,
    `register_date` datetime DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
