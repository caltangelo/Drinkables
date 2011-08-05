DROP DATABASE IF EXISTS music;
CREATE DATABASE music;
USE music;

CREATE TABLE album (
  artist_id SMALLINT(5) NOT NULL DEFAULT 0,
  album_id SMALLINT(4) NOT NULL DEFAULT 0,
  album_name CHAR(128) DEFAULT NULL,
  PRIMARY KEY  (artist_id,album_id)
);

CREATE TABLE artist (
  artist_id SMALLINT(5) NOT NULL DEFAULT 0,
  artist_name CHAR(128) DEFAULT NULL,
  PRIMARY KEY  (artist_id)
);

CREATE TABLE played (
  artist_id SMALLINT(5) NOT NULL DEFAULT 0,
  album_id SMALLINT(4) NOT NULL DEFAULT 0,
  track_id SMALLINT(3) NOT NULL DEFAULT 0,
  played TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (artist_id,album_id,track_id,played)
);

CREATE TABLE track (
  track_id SMALLINT(3) NOT NULL DEFAULT 0,
  track_name CHAR(128) DEFAULT NULL,
  artist_id SMALLINT(5) NOT NULL DEFAULT 0,
  album_id SMALLINT(4) NOT NULL DEFAULT 0,
  time DECIMAL(5,2) DEFAULT NULL,
  PRIMARY KEY  (artist_id,album_id,track_id)
);


INSERT INTO played VALUES (1, 3, 0, "20060814102103");
INSERT INTO played VALUES (1, 3, 1, "20060814102522");
INSERT INTO played VALUES (1, 3, 2, "20060814103025");
INSERT INTO played VALUES (1, 3, 3, "20060814103654");
INSERT INTO played VALUES (1, 3, 4, "20060814104143");
INSERT INTO played VALUES (1, 3, 5, "20060814104337");
INSERT INTO played VALUES (1, 3, 6, "20060814104721");
INSERT INTO played VALUES (1, 3, 7, "20060814105402");
INSERT INTO played VALUES (3, 1, 0, "20060815140003");
INSERT INTO played VALUES (3, 1, 1, "20060815142612");
INSERT INTO played VALUES (3, 1, 2, "20060815143357");

INSERT INTO artist VALUES (1, "New Order");
INSERT INTO artist VALUES (2, "Nick Cave & The Bad Seeds");
INSERT INTO artist VALUES (3, "Miles Davis");
INSERT INTO artist VALUES (4, "The Rolling Stones");
INSERT INTO artist VALUES (5, "The Stone Roses");
INSERT INTO artist VALUES (6, "Kylie Minogue");

INSERT INTO album VALUES (2, 1, "Let Love In");
INSERT INTO album VALUES (1, 1, "Retro - John McCready FAN");
INSERT INTO album VALUES (1, 2, "Substance (Disc 2)");
INSERT INTO album VALUES (1, 3, "Retro - Miranda Sawyer POP");
INSERT INTO album VALUES (1, 4, "Retro - New Order / Bobby Gillespie LIVE");
INSERT INTO album VALUES (3, 1, "Live Around The World");
INSERT INTO album VALUES (3, 2, "In A Silent Way");
INSERT INTO album VALUES (1, 5, "Power, Corruption & Lies");
INSERT INTO album VALUES (4, 1, "Exile On Main Street");
INSERT INTO album VALUES (1, 6, "Substance 1987 (Disc 1)");
INSERT INTO album VALUES (5, 1, "Second Coming");
INSERT INTO album VALUES (6, 1, "Light Years");
INSERT INTO album VALUES (1, 7, "Brotherhood");

INSERT INTO track VALUES (0,'Do You Love Me?',2,1,'5.95');
INSERT INTO track VALUES (1,'Nobody\'s Baby Now',2,1,'3.87');
INSERT INTO track VALUES (2,'Loverman',2,1,'6.37');
INSERT INTO track VALUES (3,'Jangling Jack',2,1,'2.78');
INSERT INTO track VALUES (4,'Red Right Hand',2,1,'6.18');
INSERT INTO track VALUES (5,'I Let Love In',2,1,'4.25');
INSERT INTO track VALUES (6,'Thirsty Dog',2,1,'3.81');
INSERT INTO track VALUES (7,'Ain\'t Gonna Rain Anymore',2,1,'3.77');
INSERT INTO track VALUES (8,'Lay Me Low',2,1,'5.15');
INSERT INTO track VALUES (9,'Do You Love Me? (Part Two)',2,1,'6.23');
INSERT INTO track VALUES (0,'Elegia',1,1,'4.93');
INSERT INTO track VALUES (1,'In A Lonely Place',1,1,'6.26');
INSERT INTO track VALUES (2,'Procession',1,1,'4.47');
INSERT INTO track VALUES (3,'Your Silent Face',1,1,'5.99');
INSERT INTO track VALUES (4,'Sunrise',1,1,'6.01');
INSERT INTO track VALUES (5,'Let\'s Go',1,1,'3.90');
INSERT INTO track VALUES (6,'Broken Promise',1,1,'3.76');
INSERT INTO track VALUES (7,'Dreams Never End',1,1,'3.20');
INSERT INTO track VALUES (8,'Cries And Whispers',1,1,'3.42');
INSERT INTO track VALUES (9,'All Day Long',1,1,'5.18');
INSERT INTO track VALUES (10,'Sooner Than You Think',1,1,'5.21');
INSERT INTO track VALUES (11,'Leave Me Alone',1,1,'4.67');
INSERT INTO track VALUES (12,'Lonesome Tonight',1,1,'5.19');
INSERT INTO track VALUES (13,'Every Little Counts',1,1,'4.47');
INSERT INTO track VALUES (14,'Run Wild',1,1,'3.95');
INSERT INTO track VALUES (0,'In A Lonely Place',1,2,'6.30');
INSERT INTO track VALUES (1,'Procession',1,2,'4.46');
INSERT INTO track VALUES (2,'Mesh',1,2,'3.44');
INSERT INTO track VALUES (3,'Hurt',1,2,'6.98');
INSERT INTO track VALUES (4,'The Beach',1,2,'7.32');
INSERT INTO track VALUES (5,'Confusion',1,2,'7.64');
INSERT INTO track VALUES (6,'Lonesome Tonight',1,2,'5.20');
INSERT INTO track VALUES (7,'Murder',1,2,'3.93');
INSERT INTO track VALUES (8,'Thieves Like Us',1,2,'6.95');
INSERT INTO track VALUES (9,'Kiss Of Death',1,2,'7.05');
INSERT INTO track VALUES (10,'Shame Of The Nation',1,2,'7.91');
INSERT INTO track VALUES (11,'1963',1,2,'5.63');
INSERT INTO track VALUES (0,'Fine Time',1,3,'4.71');
INSERT INTO track VALUES (1,'Temptation',1,3,'8.71');
INSERT INTO track VALUES (2,'True Faith',1,3,'5.88');
INSERT INTO track VALUES (3,'The Perfect Kiss',1,3,'4.83');
INSERT INTO track VALUES (4,'Ceremony',1,3,'4.41');
INSERT INTO track VALUES (5,'Regret',1,3,'4.14');
INSERT INTO track VALUES (6,'Crystal',1,3,'6.83');
INSERT INTO track VALUES (7,'Bizarre Love Triangle',1,3,'4.35');
INSERT INTO track VALUES (8,'Confusion',1,3,'8.22');
INSERT INTO track VALUES (9,'Round And Round',1,3,'4.52');
INSERT INTO track VALUES (10,'Blue Monday',1,3,'7.48');
INSERT INTO track VALUES (11,'Brutal',1,3,'4.83');
INSERT INTO track VALUES (12,'Slow Jam',1,3,'4.88');
INSERT INTO track VALUES (13,'Everyone Everywhere',1,3,'4.43');
INSERT INTO track VALUES (0,'Ceremony [Studio 54, Barcelona 7/7/84]',1,4,'4.82');
INSERT INTO track VALUES (1,'Procession [Polytechnic of Central London, London 6/12/85]',1,4,'3.57');
INSERT INTO track VALUES (2,'Everything\'s Gone Green [Tolworth Recreation Centre, London 12/3/85]',1,4,'5.25');
INSERT INTO track VALUES (3,'In A Lonely Place [Glastonbury Festival 20/6/81]',1,4,'5.55');
INSERT INTO track VALUES (4,'Age Of Consent [Spectrum Arena, Warrington 1/3/86]',1,4,'5.04');
INSERT INTO track VALUES (5,'Elegia [Glastonbury Festival 19/6/87]',1,4,'4.77');
INSERT INTO track VALUES (6,'The Perfect Kiss [Glastonbury Festival 19/6/87]',1,4,'9.73');
INSERT INTO track VALUES (7,'Fine Time [Popular Creek Music Theatre, Chicago 30/6/89]',1,4,'5.04');
INSERT INTO track VALUES (8,'World [Starplex Amphitheatre, Dallas 21/7/93]',1,4,'4.81');
INSERT INTO track VALUES (9,'Regret [Reading Festival 29/8/93]',1,4,'4.03');
INSERT INTO track VALUES (10,'As It Is When It Was [Reading Festival 29/8/93]',1,4,'3.80');
INSERT INTO track VALUES (11,'Intermission By Alan Wise [Olympia, Paris 12/11/01]',1,4,'1.34');
INSERT INTO track VALUES (12,'Crystal [Big Day Out, Gold Coast 20/1/02]',1,4,'6.86');
INSERT INTO track VALUES (13,'Turn My Way [Olympia, Liverpool 18/7/01]',1,4,'4.96');
INSERT INTO track VALUES (14,'Temptation [Big Day Out, Gold Coast 20/1/02]',1,4,'7.79');
INSERT INTO track VALUES (0,'In A Silent Way',3,1,'1.81');
INSERT INTO track VALUES (1,'Intruder',3,1,'4.87');
INSERT INTO track VALUES (2,'New Blues',3,1,'5.58');
INSERT INTO track VALUES (3,'Human Nature',3,1,'12.80');
INSERT INTO track VALUES (4,'Mr. Pastorius',3,1,'3.54');
INSERT INTO track VALUES (5,'Amandla',3,1,'5.87');
INSERT INTO track VALUES (6,'Wrinkle',3,1,'7.28');
INSERT INTO track VALUES (7,'Tutu',3,1,'8.89');
INSERT INTO track VALUES (8,'Full Nelson',3,1,'2.81');
INSERT INTO track VALUES (9,'Time After Time',3,1,'9.98');
INSERT INTO track VALUES (10,'Hannibal',3,1,'7.37');
INSERT INTO track VALUES (0,'Shhh/Peaceful',3,2,'16.67');
INSERT INTO track VALUES (1,'In A Silent Way/It\'s About That Time',3,2,'16.67');
INSERT INTO track VALUES (0,'Age Of Consent',1,5,'5.26');
INSERT INTO track VALUES (1,'We All Stand',1,5,'5.24');
INSERT INTO track VALUES (2,'The Village',1,5,'4.62');
INSERT INTO track VALUES (3,'5 8 6',1,5,'7.52');
INSERT INTO track VALUES (4,'Your Silent Face',1,5,'6.00');
INSERT INTO track VALUES (5,'Ultraviolence',1,5,'4.87');
INSERT INTO track VALUES (6,'Ecstasy',1,5,'4.42');
INSERT INTO track VALUES (7,'Leave Me Alone',1,5,'4.69');
INSERT INTO track VALUES (0,'Rocks Off',4,1,'4.54');
INSERT INTO track VALUES (1,'Rip This Joint',4,1,'2.38');
INSERT INTO track VALUES (2,'Shake Your Hips',4,1,'3.00');
INSERT INTO track VALUES (3,'Casino Boogie',4,1,'3.57');
INSERT INTO track VALUES (4,'Tumbling Dice',4,1,'3.79');
INSERT INTO track VALUES (5,'Sweet Virginia',4,1,'4.44');
INSERT INTO track VALUES (6,'Torn & Frayed',4,1,'4.30');
INSERT INTO track VALUES (7,'Sweet Black Angel',4,1,'2.97');
INSERT INTO track VALUES (8,'Loving Cup',4,1,'4.43');
INSERT INTO track VALUES (9,'Happy',4,1,'3.08');
INSERT INTO track VALUES (10,'Turd On The Run',4,1,'2.64');
INSERT INTO track VALUES (11,'Ventilator Blues',4,1,'3.40');
INSERT INTO track VALUES (12,'I Just Want To See His Face',4,1,'2.90');
INSERT INTO track VALUES (13,'Let It Loose',4,1,'5.31');
INSERT INTO track VALUES (14,'All Down The Line',4,1,'3.84');
INSERT INTO track VALUES (15,'Stop Breaking Down',4,1,'4.57');
INSERT INTO track VALUES (16,'Shine A Light',4,1,'4.28');
INSERT INTO track VALUES (17,'Soul Survivor',4,1,'3.82');
INSERT INTO track VALUES (0,'Ceremony',1,6,'4.42');
INSERT INTO track VALUES (1,'Everything\'s Gone Green',1,6,'5.51');
INSERT INTO track VALUES (2,'Temptation',1,6,'6.99');
INSERT INTO track VALUES (3,'Blue Monday',1,6,'7.49');
INSERT INTO track VALUES (4,'Confusion',1,6,'4.72');
INSERT INTO track VALUES (5,'Thieves Like Us',1,6,'6.61');
INSERT INTO track VALUES (6,'Perfect Kiss',1,6,'8.04');
INSERT INTO track VALUES (7,'Subculture',1,6,'4.80');
INSERT INTO track VALUES (8,'Shellshock',1,6,'6.48');
INSERT INTO track VALUES (9,'State of the Nation',1,6,'6.54');
INSERT INTO track VALUES (10,'Bizarre Love Triangle',1,6,'6.74');
INSERT INTO track VALUES (11,'True Faith',1,6,'5.93');
INSERT INTO track VALUES (0,'Breaking Into Heaven',5,1,'11.37');
INSERT INTO track VALUES (1,'Driving South',5,1,'5.17');
INSERT INTO track VALUES (2,'Ten Storey Love Song',5,1,'4.50');
INSERT INTO track VALUES (3,'Daybreak',5,1,'6.56');
INSERT INTO track VALUES (4,'Your Star Will Shine',5,1,'2.99');
INSERT INTO track VALUES (5,'Straight To The Man',5,1,'3.26');
INSERT INTO track VALUES (6,'Begging You',5,1,'4.94');
INSERT INTO track VALUES (7,'Tightrope',5,1,'4.45');
INSERT INTO track VALUES (8,'Good Times',5,1,'5.67');
INSERT INTO track VALUES (9,'Tears',5,1,'6.84');
INSERT INTO track VALUES (10,'How Do You Sleep',5,1,'4.99');
INSERT INTO track VALUES (11,'Love Spreads',5,1,'5.79');
INSERT INTO track VALUES (12,'Untitled',5,1,'6.43');
INSERT INTO track VALUES (0,'Spinning Around',6,1,'3.46');
INSERT INTO track VALUES (1,'On A Night Like This',6,1,'3.55');
INSERT INTO track VALUES (2,'So Now Goodbye',6,1,'3.62');
INSERT INTO track VALUES (3,'Disco Down',6,1,'3.96');
INSERT INTO track VALUES (4,'Loveboat',6,1,'4.18');
INSERT INTO track VALUES (5,'Koocachoo',6,1,'4.00');
INSERT INTO track VALUES (6,'Your Disco Needs You',6,1,'3.56');
INSERT INTO track VALUES (7,'Please Stay',6,1,'4.14');
INSERT INTO track VALUES (8,'Bittersweet Goodbye',6,1,'3.72');
INSERT INTO track VALUES (9,'Butterfly',6,1,'4.16');
INSERT INTO track VALUES (10,'Under The Influence Of Love',6,1,'3.40');
INSERT INTO track VALUES (11,'I\'m So High',6,1,'3.55');
INSERT INTO track VALUES (12,'Kids',6,1,'4.34');
INSERT INTO track VALUES (0,'State of the Nation',1,7,'6.56');
INSERT INTO track VALUES (1,'Every Little Counts',1,7,'4.48');
INSERT INTO track VALUES (2,'Angel Dust',1,7,'3.73');
INSERT INTO track VALUES (3,'All Day Long',1,7,'5.21');
INSERT INTO track VALUES (4,'Bizarre Love Triangle',1,7,'4.37');
INSERT INTO track VALUES (5,'Way of Life',1,7,'4.11');
INSERT INTO track VALUES (6,'Broken Promise',1,7,'3.80');
INSERT INTO track VALUES (7,'As It Is When It Was',1,7,'3.77');
INSERT INTO track VALUES (8,'Weirdo',1,7,'3.89');
INSERT INTO track VALUES (9,'Paradise',1,7,'3.86');
