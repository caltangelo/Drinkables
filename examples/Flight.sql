DROP DATABASE IF EXISTS `crs_flight`;

CREATE DATABASE `crs_flight`;

USE `crs_flight`;

CREATE TABLE `Airplane` (
  `RegistrationNumber` CHAR(8) NOT NULL,
  `ModelNumber` CHAR(8),
  `Capacity` SMALLINT,
  PRIMARY KEY (`RegistrationNumber`)
);

CREATE TABLE `Flight` (
  `FlightNumber` CHAR(8) NOT NULL,
  `From` CHAR(20),
  `To` CHAR(20),
  `DepartureDate` DATE,
  `DepartureTime` TIME,
  `ArrivalDate` DATE,
  `ArrivalTime` TIME,
  `RegistrationNumber` CHAR(10),
  PRIMARY KEY (`FlightNumber`)
);

CREATE TABLE `Passenger` (
  `GivenNames` CHAR(40),
  `Surname` CHAR(40),
  `EmailAddress` CHAR(60) NOT NULL,
  PRIMARY KEY (`EmailAddress`)
);

CREATE TABLE `Booking` (
  `FlightNumber` CHAR(8) NOT NULL,
  `EmailAddress` CHAR(60) NOT NULL,
  PRIMARY KEY (`FlightNumber`, `EmailAddress`)
);
