# buskings
busking's web service  

#DataBase  

- DataBase schema

~~~
  CREATE DATABASE `buskingsroad` /*!40100 DEFAULT CHARACTER SET utf8 */;
~~~~
 
 - Create Table
 
~~~~
CREATE TABLE `buskerpool` (
  `pool_key` int(11) NOT NULL AUTO_INCREMENT,  
  `category` varchar(45) DEFAULT NULL,  
  `customer_cus_key` int(11) NOT NULL,  
  `reg_date` date DEFAULT NULL,  
  PRIMARY KEY (`pool_key`,`customer_cus_key`),  
  KEY `fk_buskerpool_customer1_idx` (`customer_cus_key`),  
  CONSTRAINT `fk_buskerpool_customer1` FOREIGN KEY (`customer_cus_key`) REFERENCES `customer` (`cus_key`) ON DELETE NO ACTION ON UPDATE NO ACTION  
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
~~~~
  pool_key  가 기본키로 설정되어있다.
~~~~
CREATE TABLE `busking` (
  `busking_key` int(11) NOT NULL AUTO_INCREMENT,
  `reg_date` date DEFAULT NULL,
  `busking_date` date DEFAULT NULL,
  `latitude` varchar(30) DEFAULT NULL,
  `longtitude` varchar(30) DEFAULT NULL,
  `hit` int(11) DEFAULT NULL,
  `buskerpool_pool_key` int(11) NOT NULL,
  `buskerpool_customer_cus_key` int(11) NOT NULL,
  PRIMARY KEY (`busking_key`),
  KEY `fk_busking_buskerpool2_idx` (`buskerpool_pool_key`,`buskerpool_customer_cus_key`),
  CONSTRAINT `fk_busking_buskerpool2` FOREIGN KEY (`buskerpool_pool_key`, `buskerpool_customer_cus_key`) REFERENCES `buskerpool` (`pool_key`, `customer_cus_key`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
~~~~

~~~~
CREATE TABLE `busking_contents` (
  `busking_key` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `content` varchar(200) DEFAULT NULL,
  `reg_date` date DEFAULT NULL,
  `buskerpool_pool_key` int(11) NOT NULL,
  `busking_category` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`busking_key`),
  KEY `fk_busking_buskerpool1_idx` (`buskerpool_pool_key`),
  CONSTRAINT `fk_busking_buskerpool1` FOREIGN KEY (`buskerpool_pool_key`) REFERENCES `buskerpool` (`pool_key`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
~~~~

~~~~
CREATE TABLE `busking_reple` (
  `reple_key` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(45) DEFAULT NULL,
  `content` varchar(100) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `busking_busking_key` int(11) NOT NULL,
  PRIMARY KEY (`reple_key`),
  KEY `fk_busking_reple_busking2_idx` (`busking_busking_key`),
  CONSTRAINT `fk_busking_reple_busking2` FOREIGN KEY (`busking_busking_key`) REFERENCES `busking` (`busking_key`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
~~~~

~~~~
CREATE TABLE `contents_reple` (
  `reple_key` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(45) DEFAULT NULL,
  `content` varchar(100) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `busking_busking_key` int(11) NOT NULL,
  PRIMARY KEY (`reple_key`),
  KEY `fk_busking_reple_busking1_idx` (`busking_busking_key`),
  CONSTRAINT `fk_busking_reple_busking1` FOREIGN KEY (`busking_busking_key`) REFERENCES `busking_contents` (`busking_key`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
~~~~

~~~~
CREATE TABLE `customer` (
  `cus_key` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(50) DEFAULT NULL,
  `pass` varchar(100) DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  `join_date` date DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cus_key`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
~~~~

~~~~
CREATE TABLE `customer_has_customer` (
  `customer_cus_key` int(11) NOT NULL,
  `customer_cus_key1` int(11) NOT NULL,
  `follow_key` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`follow_key`),
  KEY `fk_customer_has_customer_customer1_idx` (`customer_cus_key1`),
  KEY `fk_customer_has_customer_customer_idx` (`customer_cus_key`),
  CONSTRAINT `fk_customer_has_customer_customer` FOREIGN KEY (`customer_cus_key`) REFERENCES `customer` (`cus_key`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_customer_has_customer_customer1` FOREIGN KEY (`customer_cus_key1`) REFERENCES `customer` (`cus_key`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
~~~~

