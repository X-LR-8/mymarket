CREATE TABLE `item` (
                        `id` int unsigned NOT NULL AUTO_INCREMENT,
                        `name` varchar(45) NOT NULL,
                        `price` int unsigned NOT NULL,
                        `description` varchar(999) NOT NULL,
                        `submittiontime` varchar(45) NOT NULL,
                        `photo` varchar(45) DEFAULT NULL,
                        `username` varchar(45) DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `name_UNIQUE` (`name`),
                        KEY `username_idx` (`username`),
                        CONSTRAINT `username` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci