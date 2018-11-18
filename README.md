#Customer application for Propellerhead interview 

This is a CRUD application to create Customers

What you'll need
----------------
JDK 1.8 or later
Maven 3 or later
Spring MVC 4.2.6.RELEASE 
Tomcat 9.0 

Build
-----
mvn clean install    

Database Scripts for MySql database
-----------------------------------
CREATE TABLE IF NOT EXISTS `customer` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(50) NOT NULL,
  `STATUS` varchar(25) NOT NULL,
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `NAME` varchar(250) NOT NULL,
  `ADDRESS` varchar(500) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

CREATE TABLE IF NOT EXISTS `notes` (
  `ID` int(10) NOT NULL,
  `CUSTOMER_ID` int(10) NOT NULL,
  `NOTE` varchar(500) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

