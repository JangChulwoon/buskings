# buskings
busking's web service  

#DataBase  

- DataBase schema

~~~
  CREATE DATABASE `buskingsroad` /*!40100 DEFAULT CHARACTER SET utf8 */;
~~~~
 
 - Create Table
 
 #buskerpool

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
  pool_key  : 기본키.  자동적으로 순차 등록되게 설정하였다.

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

 #customer
  -회원 정보를 담고있는 테이블

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

cus_key : 기본키  AUTO_INCREMENT 설정. int(11)  
	id : 회원의 id 를 저장. varchar(50)  
	pass : 회원의 password를 저장 varchar(100) 해쉬코드를 저장하기에 100으로 설정.   
	name : 회원의 이름 varchar(45)  
	category : 공연자의 category를 저장 할 수 있다. (랩 , 클래식 , ...) varchar(45)  
	join_date : 회원의 가입 날짜. date  

 #customer_has_customer
  -회원간의 관계를 저장하고있는 테이블.

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

follow_key : 테이블의 기본키   
customer_cus_key , customer_cus_key1 : follow 관계를 맺고잇는 사용자의 key    
이 두 속성은 외래키로 customer 테이블에서 가져옴.    
