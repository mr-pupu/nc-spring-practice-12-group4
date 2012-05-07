--------------------------------------------------------
--  File created - Sunday-April-29-2012   
--------------------------------------------------------
REM INSERTING into COUNTRY
SET DEFINE OFF;
Insert into COUNTRY (ID,COUNTRY_NAME) values (1,'Chili');
Insert into COUNTRY (ID,COUNTRY_NAME) values (2,'Cuba');
REM INSERTING into CITY
SET DEFINE OFF;
Insert into CITY (ID,CITY_NAME,COUNTRY_ID) values (1,'Santiago',1);
Insert into CITY (ID,CITY_NAME,COUNTRY_ID) values (2,'Talca',1);
Insert into CITY (ID,CITY_NAME,COUNTRY_ID) values (3,'Habana',2);
Insert into CITY (ID,CITY_NAME,COUNTRY_ID) values (4,'Santiago',2);
Insert into CITY (ID,CITY_NAME,COUNTRY_ID) values (5,'Trinidad',2);
REM INSERTING into CUSTOMER
SET DEFINE OFF;
Insert into CUSTOMER (ID,CUST_NAME) values (1,'Intrade Corp');
Insert into CUSTOMER (ID,CUST_NAME) values (2,'Fury Industrials');
Insert into CUSTOMER (ID,CUST_NAME) values (3,'Borowind.com');
REM INSERTING into OCCUPATION
SET DEFINE OFF;
Insert into OCCUPATION (ID,POS_NAME) values (1,'Department Chief');
Insert into OCCUPATION (ID,POS_NAME) values (2,'Team Lead');
Insert into OCCUPATION (ID,POS_NAME) values (3,'Senior Developer');
Insert into OCCUPATION (ID,POS_NAME) values (4,'Developer');
Insert into OCCUPATION (ID,POS_NAME) values (5,'Sales Manager');
Insert into OCCUPATION (ID,POS_NAME) values (6,'Travel Manager');
REM INSERTING into OFFICE
SET DEFINE OFF;
Insert into OFFICE (ID,OFFICE_NAME, CITY_ID) values (1,'Santiago(Chili) office',1);
Insert into OFFICE (ID,OFFICE_NAME, CITY_ID) values (2,'Talca office',2);
Insert into OFFICE (ID,OFFICE_NAME, CITY_ID) values (3,'Habana office',3);
Insert into OFFICE (ID,OFFICE_NAME, CITY_ID) values (4,'Santiago(Cuba) office',4);
Insert into OFFICE (ID,OFFICE_NAME, CITY_ID) values (5,'Trinidad office',5);
REM INSERTING into DEPARTMENT
SET DEFINE OFF;
--Chili departments:
Insert into DEPARTMENT (ID,DEP_NAME,PARENT_ID) values (1,'Main department, Santiago, Chili',null);
Insert into DEPARTMENT (ID,DEP_NAME,PARENT_ID) values (2,'Travel, Santiago, Chili',1);
Insert into DEPARTMENT (ID,DEP_NAME,PARENT_ID) values (3,'Travel, Talca, Chili',1);
Insert into DEPARTMENT (ID,DEP_NAME,PARENT_ID) values (4,'IT, Santiago, Chili',1);
Insert into DEPARTMENT (ID,DEP_NAME,PARENT_ID) values (5,'IT, Talca, Chili',1);
Insert into DEPARTMENT (ID,DEP_NAME,PARENT_ID) values (6,'Sales department, Santiago, Chili',1);
Insert into DEPARTMENT (ID,DEP_NAME,PARENT_ID) values (7,'Software sales, Santiago, Chili',6);
Insert into DEPARTMENT (ID,DEP_NAME,PARENT_ID) values (8,'Hardware sales, Santiago, Chili',6);
Insert into DEPARTMENT (ID,DEP_NAME,PARENT_ID) values (9,'Development, Talca, Chili',1);
Insert into DEPARTMENT (ID,DEP_NAME,PARENT_ID) values (10,'Software dev, Talca, Chili',9);
Insert into DEPARTMENT (ID,DEP_NAME,PARENT_ID) values (11,'Hardware dev, Talca, Chili',9);
--Cuba departments:
Insert into DEPARTMENT (ID,DEP_NAME,PARENT_ID) values (12,'Main department, Habana, Cuba',null);
Insert into DEPARTMENT (ID,DEP_NAME,PARENT_ID) values (13,'Travel, Habana, Cuba',12);
Insert into DEPARTMENT (ID,DEP_NAME,PARENT_ID) values (14,'Travel, Santiago, Cuba',12);
Insert into DEPARTMENT (ID,DEP_NAME,PARENT_ID) values (15,'Travel, Trinidad, Cuba',12);
Insert into DEPARTMENT (ID,DEP_NAME,PARENT_ID) values (16,'IT, Habana, Cuba',12);
Insert into DEPARTMENT (ID,DEP_NAME,PARENT_ID) values (17,'IT, Santiago, Cuba',12);
Insert into DEPARTMENT (ID,DEP_NAME,PARENT_ID) values (18,'IT, Trinidad, Cuba',12);
Insert into DEPARTMENT (ID,DEP_NAME,PARENT_ID) values (19,'Sales department, Habana, Cuba',12);
Insert into DEPARTMENT (ID,DEP_NAME,PARENT_ID) values (20,'Software sales, Habana, Cuba',19);
Insert into DEPARTMENT (ID,DEP_NAME,PARENT_ID) values (21,'Hardware sales, Habana, Cuba',19);
Insert into DEPARTMENT (ID,DEP_NAME,PARENT_ID) values (22,'Development, Santiago, Cuba',12);
Insert into DEPARTMENT (ID,DEP_NAME,PARENT_ID) values (23,'Software dev, Santiago, Cuba',22);
Insert into DEPARTMENT (ID,DEP_NAME,PARENT_ID) values (24,'Hardware dev, Santiago, Cuba',22);
Insert into DEPARTMENT (ID,DEP_NAME,PARENT_ID) values (25,'Development, Trinidad, Cuba',12);
Insert into DEPARTMENT (ID,DEP_NAME,PARENT_ID) values (26,'Software dev, Trinidad, Cuba',25);
Insert into DEPARTMENT (ID,DEP_NAME,PARENT_ID) values (27,'Hardware dev, Trinidad, Cuba',25);
REM INSERTING into EMPLOYEE
SET DEFINE OFF;
--Santiago(Chili) employees:
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (1,'John','Smith','j.smith@gmail.com','John','ijohn',1,1,1); --with trf
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (2,'Jack','White','j.white@gmail.com','Jack','ijack',1,2,1);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (3,'Steven','Duck','s.duck@gmail.com','Steven','isteven',1,3,1);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (4,'Ted','Winston','t.winston@gmail.com','Ted','ited',2,1,1); --travel, with trf
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (5,'Andrew','Wilkinson','a.wilkinson@gmail.com','Andy','iandy',2,6,1); --travel, with trf
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (6,'Indigo','Viloto','i.viloto@gmail.com','Indigo','iindigo',2,6,1); --travel
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (7,'Viki','Krusty','v.krusty@gmail.com','Viki','iviki',4,1,1); --admin, with trf
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (8,'Harry','Berg','h.berg@gmail.com','Harry','iharry',4,3,1); --admin
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (9,'Greek','Clinc','g.clinc@gmail.com','Greek','igreek',4,4,1); --admin
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (10,'James','Cook','j.cool@gmail.com','James','ijames',6,1,1); --with trf
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (11,'Ben','Slim','b.slim@gmail.com','Ben','iben',6,2,1);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (12,'Bobby','Bobson','b.slim@gmail.com','Bob','ibob',6,5,1);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (13,'Sara','Oconnor','b.slim@gmail.com','Sara','isara',7,1,1); -- with trf
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (14,'Richard','Mardow','b.slim@gmail.com','richard','irichard',7,2,1);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (15,'Boris','Giranin','b.slim@gmail.com','Boris','iboris',7,5,1);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (16,'Monica','Belucci','b.slim@gmail.com','Monic','imonic',8,1,1);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (17,'Allan','Prinston','b.slim@gmail.com','Allan','iallan',8,2,1);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (18,'Garold','Slim','b.slim@gmail.com','Garold','igarold',8,5,1);
--Talca(Chili) employees:
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (19,'Jesie','Lee','j.lee@gmail.com','Jesie','ijesie',3,1,2); --travel
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (20,'Lock','Born','l.born@gmail.com','Lock','ilock',3,6,2); --travel
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (21,'Karen','Shot','k.shot@gmail.com','Karen','ikaren',3,6,2); --travel
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (22,'Lusy','Vanish','l.vanish@gmail.com','Lusy','ilusy',5,1,2); --admin
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (23,'Kent','Buck','k.buck@gmail.com','Kent','ikent',5,3,2); --admin
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (24,'Lily','Week','l.week@gmail.com','Lily','ilily',5,4,2); --admin
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (25,'Erick','Fitch','e.fitch@gmail.com','Erick','ierick',9,1,2);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (26,'Maria','Clemens','m.clemens@gmail.com','Maria','imaria',9,2,2);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (27,'Elia','Clemens','m.clemens@gmail.com','Elia','ielia',9,3,2);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (28,'Bobby','Fitch','b.fitch@gmail.com','bobson','ibobson',10,1,2);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (29,'Lara','Bonita','l.bonita@gmail.com','lara','ilara',10,3,2);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (30,'Marry','Trugirl','m.trugirl@gmail.com','Marry','imarry',10,4,2);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (31,'Chuck','Chukson','c.chuckson@gmail.com','Chuck','ichuck',11,1,2);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (32,'Marge','Simpson','m.simpson@gmail.com','Marge','imarge',11,2,2);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (33,'Homer','Simpson','h.simpson@gmail.com','Hommy','ihommy',11,4,2);
--Habana(Cuba) employees:
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (34,'Gandy','Yorke','g.yorke@gmail.com','Gandy','igandy',12,1,3);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (35,'Van','Gomez','v.gomez@gmail.com','Van','ivan',12,2,3);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (36,'Rowen','Bun','r.bun@gmail.com','Rowen','irowen',12,3,3);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (37,'Nick','Wizard','n.wizard@gmail.com','Nick','inick',13,1,3); --travel
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (38,'Perez','Hoock','p.hoock@gmail.com','Perez','iperez',13,6,3); --travel
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (39,'Den','Fisher','d.fisher@gmail.com','Den','iden',13,6,3); --travel
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values
(40,'Leroy','Brown','l.brown@gmail.com','Leroy','ileroy',16,1,3); --admin
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (41,'Samuel','Jackson','s.hackson@gmail.com','Samuel','isamuel',16,2,3); --admin
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (42,'Inna','Ferini','i.ferini@gmail.com','Inna','iinna',16,3,3); --admin
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (43,'Julia','Hizi','j.hizi@gmail.com','Julia','ijulia',19,1,3);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (44,'Martin','Getz','m.getz@gmail.com','Martin','imartin',19,2,3);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (45,'Pod','Flinstone','p.flinstone@gmail.com','Pod','ipod',19,5,3);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (46,'Kira','Noock','k.noock@gmail.com','Kira','ikira',20,1,3);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (47,'Michelle','Firestone','m.firestone@gmail.com','Michelle','imichelle',20,5,3);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (48,'Wandy','Mercedes','w.mercedes@gmail.com','Wandy','iwandy',20,5,3);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (49,'Garry','Garrison','k.@gmail.com','Garry','igarry',21,1,3);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (50,'Chandler','Bing','c.bing@gmail.com','Chandler','ichandler',21,2,3);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (51,'Juliet','Testaburger','j.testaburger@gmail.com','Juliet','ijuliet',21,5,3);
--Santiago(Cuba) employees:
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (52,'Brook','Jiel','b.jiel@gmail.com','Brook','ibrook',14,1,4); --travel
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (53,'Barny','Stock','b.stock@gmail.com','Barny','ibarny',14,6,4); --travel
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (54,'Mick','Jocker','m.jocker@gmail.com','Mick','imick',14,6,4); --travel
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (55,'Beck','Voon','b.voon@gmail.com','Beck','ibeck',17,1,4); --admin
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (56,'Edgar','Moon','e.moon@gmail.com','Edgar','iedgar',17,2,4); --admin
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (57,'Antoni','Wisdom','a.wisdom@gmail.com','Antoni','iantoni',17,3,4); --admin
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (58,'Asten','Astenson','a.astenson@gmail.ru','Asten','iasten',22,1,4);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (59,'Sirano','Morger','s.morger@gmail.ru','Sirano','isirano',22,2,4);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (60,'Anny','Liro','a.liro@gmail.ru','Anny','ianny',22,3,4);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (61,'Panda','Kungfu','p.kungfu@gmail.ru','Panda','ipanda',23,1,4);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (62,'Ella','Belissimo','e.bellisimo@gmail.ru','Ella','iella',23,3,4);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (63,'Frank','Gurinochi','f.gurinochi@gmail.ru','Frank','ifrank',23,4,4);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (64,'Niro','Yamazaki','n.yamazaki@gmail.ru','Niro','iniro',24,1,4);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values
(65,'Lu','Chan','l.chan@gmail.ru','Lu','ilu',24,2,4);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values 
(66,'Chi','Po','c.po@gmail.ru','Chi','ichi',24,4,4);
--Trinidad(Cuba) employees:
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (67,'Lui','Defunes','l.defunes@gmail.com','Lui','ilui',15,1,4); --travel
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (68,'Shack','Engark','s.engark@gmail.com','Shack','ishack',15,6,4); --travel
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (69,'Polly','Furinotti','p.furinote@gmail.com','Polly','ipolly',15,6,4); --travel
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values 
(70,'Cho','Keng','c.keng@gmail.com','Cho','icho',18,1,4); --admin
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (71,'Bruce','Wayne','b.wayne@gmail.com','Bruce','ibruce',18,2,4); --admin
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values 
(72,'Chang','Pang','c.pang@gmail.com','Chang','ichang',18,3,4); --admin
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (73,'Undada','Bambudu','u.bambudu@gmail.ru','Undada','iundada',25,1,4);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (74,'Abhay','Jivani','a.jivani@gmail.ru','Abhay','iabhay',25,2,4);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (75,'Adjit','Bandi','a.bandi@gmail.ru','Adjit','iadjit',25,3,4);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (76,'Amar','Hinam','a.hinam@gmail.ru','Amar','iamar',26,1,4);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (77,'Halim','Farur','h.farur@gmail.ru','Halim','ihalim',26,2,4);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (78,'Tomiko','Li','t.li@gmail.ru','Tomiko','itomiko',26,4,4);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (79,'Aniko','Amini','a.amini@gmail.ru','Aniko','ianiko',27,1,4);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (80,'Gerrar','Deger','g.deger@gmail.ru','Gerrar','igerrar',27,3,4);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (81,'Francisko','Luccini','f.luccini@gmail.ru','Francisko','ifrancisko',27,4,4);


REM INSERTING into DESTINATION
SET DEFINE OFF;
Insert into DESTINATION (ID,CITY_ID,HOTELNAME,HOTELSITE,IS_APPROVED) values (1,1,'Hayat','hayat.com',1);
Insert into DESTINATION (ID,CITY_ID,HOTELNAME,HOTELSITE,IS_APPROVED) values (2,2,'Hilton','hilton.com',1);
Insert into DESTINATION (ID,CITY_ID,HOTELNAME,HOTELSITE,IS_APPROVED) values (3,3,'Downtown','downtown.com',1);
Insert into DESTINATION (ID,CITY_ID,HOTELNAME,HOTELSITE,IS_APPROVED) values (4,5,'Brown','brown.com',0);
REM INSERTING into DEPROLE
SET DEFINE OFF;
Insert into DEPROLE (ID,ROLE_NAME) values (1,'Common Department');
Insert into DEPROLE (ID,ROLE_NAME) values (2,'Travel Department');
Insert into DEPROLE (ID,ROLE_NAME) values (3,'IT Department');
REM INSERTING into ROLEDEP
SET DEFINE OFF;
INSERT INTO roledep (dep_id, role_id) VALUES (1,1);
INSERT INTO roledep (dep_id, role_id) VALUES (2,1);
INSERT INTO roledep (dep_id, role_id) VALUES (2,2);
INSERT INTO roledep (dep_id, role_id) VALUES (3,1);
INSERT INTO roledep (dep_id, role_id) VALUES (3,2);
INSERT INTO roledep (dep_id, role_id) VALUES (4,1);
INSERT INTO roledep (dep_id, role_id) VALUES (4,3);
INSERT INTO roledep (dep_id, role_id) VALUES (5,1);
INSERT INTO roledep (dep_id, role_id) VALUES (5,3);
INSERT INTO roledep (dep_id, role_id) VALUES (6,1);
INSERT INTO roledep (dep_id, role_id) VALUES (7,1);
INSERT INTO roledep (dep_id, role_id) VALUES (8,1);
INSERT INTO roledep (dep_id, role_id) VALUES (9,1);
INSERT INTO roledep (dep_id, role_id) VALUES (10,1);
INSERT INTO roledep (dep_id, role_id) VALUES (11,1);
INSERT INTO roledep (dep_id, role_id) VALUES (12,1);
INSERT INTO roledep (dep_id, role_id) VALUES (13,1);
INSERT INTO roledep (dep_id, role_id) VALUES (13,2);
INSERT INTO roledep (dep_id, role_id) VALUES (14,1);
INSERT INTO roledep (dep_id, role_id) VALUES (14,2);
INSERT INTO roledep (dep_id, role_id) VALUES (15,1);
INSERT INTO roledep (dep_id, role_id) VALUES (15,2);
INSERT INTO roledep (dep_id, role_id) VALUES (16,1);
INSERT INTO roledep (dep_id, role_id) VALUES (16,3);
INSERT INTO roledep (dep_id, role_id) VALUES (17,1);
INSERT INTO roledep (dep_id, role_id) VALUES (17,3);
INSERT INTO roledep (dep_id, role_id) VALUES (18,1);
INSERT INTO roledep (dep_id, role_id) VALUES (18,3);
INSERT INTO roledep (dep_id, role_id) VALUES (19,1);
INSERT INTO roledep (dep_id, role_id) VALUES (20,1);
INSERT INTO roledep (dep_id, role_id) VALUES (21,1);
INSERT INTO roledep (dep_id, role_id) VALUES (22,1);
INSERT INTO roledep (dep_id, role_id) VALUES (23,1);
INSERT INTO roledep (dep_id, role_id) VALUES (24,1);
INSERT INTO roledep (dep_id, role_id) VALUES (25,1);
INSERT INTO roledep (dep_id, role_id) VALUES (26,1);
INSERT INTO roledep (dep_id, role_id) VALUES (27,1);

REM INSERTING into TRF
SET DEFINE OFF;
--trf states: 0 — entering, 1 — rejected, 2 — cancelled, 3 — ready, 4 - completed
Insert into TRF (ID,DESTINATION_ID,CUSTOMER_ID,EMP_ID,BEGIN_DATE,END_DATE,CAR_RENTAL,PAY_BY_CASH,CUR_STATE, PROJECT_MANAGER) 
values (1,1,1,1,to_date('13-MAR-11','DD-MON-RR'),to_date('23-MAR-11','DD-MON-RR'),'0','1',0, 1);
Insert into TRF (ID,DESTINATION_ID,CUSTOMER_ID,EMP_ID,BEGIN_DATE,END_DATE,CAR_RENTAL,PAY_BY_CASH,CUR_STATE, PROJECT_MANAGER) 
values (2,2,2,4,to_date('27-JUN-12','DD-MON-RR'),to_date('30-JUL-12','DD-MON-RR'),'1','0',1, 4);
Insert into TRF (ID,DESTINATION_ID,CUSTOMER_ID,EMP_ID,BEGIN_DATE,END_DATE,CAR_RENTAL,PAY_BY_CASH,CUR_STATE, PROJECT_MANAGER) 
values (3,3,3,7,to_date('20-FEB-11','DD-MON-RR'),to_date('21-FEB-11','DD-MON-RR'),'1','1',2, 7);
Insert into TRF (ID,DESTINATION_ID,CUSTOMER_ID,EMP_ID,BEGIN_DATE,END_DATE,CAR_RENTAL,PAY_BY_CASH,CUR_STATE, PROJECT_MANAGER) 
values (4,4,1,10,to_date('13-MAR-11','DD-MON-RR'),to_date('23-MAR-11','DD-MON-RR'),'0','1',3, 10);
Insert into TRF (ID,DESTINATION_ID,CUSTOMER_ID,EMP_ID,BEGIN_DATE,END_DATE,CAR_RENTAL,PAY_BY_CASH,CUR_STATE, PROJECT_MANAGER) 
values (5,1,1,13,to_date('27-JUN-12','DD-MON-RR'),to_date('03-JUL-12','DD-MON-RR'),'1','0',4, 13);


REM INSERTING into TRFSTATE
SET DEFINE OFF;
Insert into TRFSTATE (ID,TRF_ID,COMMENTARY,CHANGE_DATE,STATUS,CHANGER) 
values (1,1,'not finished',to_date('17-FEB-11','DD-MON-RR'),0,1);
Insert into TRFSTATE (ID,TRF_ID,COMMENTARY,CHANGE_DATE,STATUS,CHANGER) 
values (2,2,'not finished',to_date('02-JUN-12','DD-MON-RR'),0,4);
Insert into TRFSTATE (ID,TRF_ID,COMMENTARY,CHANGE_DATE,STATUS,CHANGER) 
values (3,2,'Talca trip',to_date('03-JUN-12','DD-MON-RR'),3,4);
Insert into TRFSTATE (ID,TRF_ID,COMMENTARY,CHANGE_DATE,STATUS,CHANGER) 
values (4,2,'rejected: wrong end date',to_date('03-JUN-12','DD-MON-RR'),1,5);
Insert into TRFSTATE (ID,TRF_ID,COMMENTARY,CHANGE_DATE,STATUS,CHANGER) 
values (5,3,'not finished',to_date('01-FEB-11','DD-MON-RR'),0,7);
Insert into TRFSTATE (ID,TRF_ID,COMMENTARY,CHANGE_DATE,STATUS,CHANGER) 
values (6,3,'canceled: family problem',to_date('02-FEB-11','DD-MON-RR'),2,7);
Insert into TRFSTATE (ID,TRF_ID,COMMENTARY,CHANGE_DATE,STATUS,CHANGER) 
values (7,4,'not finished',to_date('01-MAR-12','DD-MON-RR'),0,10);
Insert into TRFSTATE (ID,TRF_ID,COMMENTARY,CHANGE_DATE,STATUS,CHANGER) 
values (8,4,'Trinidad trip',to_date('01-MAR-12','DD-MON-RR'),3,10);
Insert into TRFSTATE (ID,TRF_ID,COMMENTARY,CHANGE_DATE,STATUS,CHANGER) 
values (9,5,'not finished',to_date('02-JUN-12','DD-MON-RR'),0,13);
Insert into TRFSTATE (ID,TRF_ID,COMMENTARY,CHANGE_DATE,STATUS,CHANGER) 
values (10,5,'Talca trip',to_date('03-JUN-12','DD-MON-RR'),3,13);
Insert into TRFSTATE (ID,TRF_ID,COMMENTARY,CHANGE_DATE,STATUS,CHANGER) 
values (11,5,'completed: ok',to_date('04-JUN-12','DD-MON-RR'),4,5);



UPDATE department SET manager_id=1 where id=1;
UPDATE department SET manager_id=4 where id=2;
UPDATE department SET manager_id=19 where id=3;
UPDATE department SET manager_id=7 where id=4;
UPDATE department SET manager_id=22 where id=5;
UPDATE department SET manager_id=10 where id=6;
UPDATE department SET manager_id=13 where id=7;
UPDATE department SET manager_id=16 where id=8;
UPDATE department SET manager_id=25 where id=9;
UPDATE department SET manager_id=28 where id=10;
UPDATE department SET manager_id=31 where id=11;
UPDATE department SET manager_id=34 where id=12;
UPDATE department SET manager_id=37 where id=13;
UPDATE department SET manager_id=52 where id=14;
UPDATE department SET manager_id=67 where id=15;
UPDATE department SET manager_id=40 where id=16;
UPDATE department SET manager_id=55 where id=17;
UPDATE department SET manager_id=70 where id=18;
UPDATE department SET manager_id=43 where id=19;
UPDATE department SET manager_id=46 where id=20;
UPDATE department SET manager_id=49 where id=21;
UPDATE department SET manager_id=58 where id=22;
UPDATE department SET manager_id=61 where id=23;
UPDATE department SET manager_id=64 where id=24;
UPDATE department SET manager_id=73 where id=25;
UPDATE department SET manager_id=76 where id=26;
UPDATE department SET manager_id=79 where id=27;
