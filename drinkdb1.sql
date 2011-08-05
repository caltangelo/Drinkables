DROP DATABASE IF EXISTS alpha_drinks;
CREATE DATABASE alpha_drinks;
USE alpha_drinks;

CREATE TABLE drinks (
  drink_id SMALLINT(5) NOT NULL DEFAULT 0,
  drink_name CHAR(128) DEFAULT NULL,
  base CHAR(128) DEFAULT NULL,
  PRIMARY KEY  (drink_id)
);

CREATE TABLE ingredients(
	drink_id SMALLINT(5) NOT NULL DEFAULT 0,
	components CHAR(128) DEFAULT NULL,
	PRIMARY KEY (drink_id,components)
);