DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL DEFAULT '0000-01-01 00:00:00',
  `update_at` datetime NOT NULL DEFAULT '0000-01-01 00:00:00',
  `telphone` varchar(40) NOT NULL,
  `password` varchar(200) NOT NULL,
  `nick_name` varchar(40) NOT NULL,
  `gender` int(5) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `telphone_unique_index` (`telphone`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;