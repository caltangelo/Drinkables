DROP DATABASE IF EXISTS `crs_university`;

CREATE DATABASE `crs_university`;


USE `crs_university`;


CREATE TABLE `Student` (
  `GivenNames` CHAR(40),
  `Surname` CHAR(45),
  `Student_ID` CHAR(8),
  `DateOfBirth` DATE,
  `YearEnrolled` YEAR,
  `Program_ID` CHAR(8),
  PRIMARY KEY (`Student_ID`)
);

CREATE TABLE `Program` (
  `Name` CHAR(40),
  `Program_ID` CHAR(8),
  `CreditPoints` SMALLINT,
  `YearCommenced` YEAR,
  PRIMARY KEY (`Program_ID`)
);

CREATE TABLE `Course` (
  `Name` CHAR(40),
  `Course_ID` CHAR(8),
  `CreditPoints` SMALLINT,
  `YearCommenced` YEAR,
  PRIMARY KEY (`Course_ID`)
);

CREATE TABLE `Attempts` (
  `Year` YEAR,
  `Semester` CHAR(2),
  `Mark` SMALLINT,
  `Grade` CHAR(4),
  `Student_ID` CHAR(8),
  `Courses_ID` CHAR(8),
  PRIMARY KEY (`Semester`, `Year`)
);

CREATE TABLE `EnrolsIn` (
  `Student_ID` CHAR(8),
  `Program_ID` CHAR(8),
  PRIMARY KEY (`Student_ID`, `Program_ID`)
);

CREATE TABLE `Contains` (
  `Semester` CHAR(2),
  `Year` YEAR,
  `Course_ID` CHAR(8),
  `Program_ID` CHAR(8),
  PRIMARY KEY (`Course_ID`, `Program_ID`)
);
