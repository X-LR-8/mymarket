CREATE TABLE `user` (
                        `id` int unsigned NOT NULL AUTO_INCREMENT,
                        `username` varchar(45) NOT NULL,
                        `email` varchar(45) NOT NULL,
                        `password` varchar(45) NOT NULL,
                        `instantbirthday` varchar(45) NOT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci