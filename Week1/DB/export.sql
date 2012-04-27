--------------------------------------------------------
--  File created - Tuesday-April-17-2012   
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
Insert into CITY (ID,CITY_NAME,COUNTRY_ID) values (4,'Trinidad',2);
Insert into CITY (ID,CITY_NAME,COUNTRY_ID) values (5,'Santiago',2);
REM INSERTING into CUSTOMER
SET DEFINE OFF;
Insert into CUSTOMER (ID,CUST_NAME) values (1,'Intrade Corp');
Insert into CUSTOMER (ID,CUST_NAME) values (2,'Fury Industrials');
Insert into CUSTOMER (ID,CUST_NAME) values (3,'Borowind.com');
REM INSERTING into OCCUPATION
SET DEFINE OFF;
Insert into OCCUPATION (ID,POS_NAME) values (123,'boss');
Insert into OCCUPATION (ID,POS_NAME) values (1,'Department Chief');
Insert into OCCUPATION (ID,POS_NAME) values (2,'Team Lead');
Insert into OCCUPATION (ID,POS_NAME) values (3,'Senior Developer');
Insert into OCCUPATION (ID,POS_NAME) values (4,'Developer');
Insert into OCCUPATION (ID,POS_NAME) values (5,'Sales Manager');
Insert into OCCUPATION (ID,POS_NAME) values (6,'Travel Manager');
REM INSERTING into OFFICE
SET DEFINE OFF;
Insert into OFFICE (ID,CITY_ID) values (1,1);
Insert into OFFICE (ID,CITY_ID) values (2,2);
Insert into OFFICE (ID,CITY_ID) values (3,3);
Insert into OFFICE (ID,CITY_ID) values (4,4);
Insert into OFFICE (ID,CITY_ID) values (5,5);
REM INSERTING into DEPARTMENT
SET DEFINE OFF;
Insert into DEPARTMENT (ID,DEP_NAME,PARENT_ID) values (1,'Main Santiago Chili',null);
Insert into DEPARTMENT (ID,DEP_NAME,PARENT_ID) values (2,'IT Talca, Chili',1);
Insert into DEPARTMENT (ID,DEP_NAME,PARENT_ID) values (3,'Perform Talca, Chili',1);
Insert into DEPARTMENT (ID,DEP_NAME,PARENT_ID) values (4,'Main Habana, Cuba',3);
Insert into DEPARTMENT (ID,DEP_NAME,PARENT_ID) values (5,'Travel Santiago, Cuba',4);
Insert into DEPARTMENT (ID,DEP_NAME,PARENT_ID) values (6,'IT Trinidad, Cuba',4);
Insert into DEPARTMENT (ID,DEP_NAME,PARENT_ID) values (7,'Perform Trinidad, Cuba',4);
REM INSERTING into EMPLOYEE
SET DEFINE OFF;
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (1,'John','Smith','j.smith@gmail.com','John','ijohn',1,1,1);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (2,'Jack','White','j.white@gmail.com','Jack','ijack',1,2,1);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (4,'Andy','Yorke','a.yorke@gmail.com','Andy','iandy',1,4,3);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (5,'Brook','Jiel','b.jiel@gmail.com','Brook','ibrook',1,5,4);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (6,'Jesie','Lee','j.lee@gmail.com','Jesie','ijesie',1,6,2);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (7,'Van','Gomez','v.gomez@gmail.com','Van','ivan',1,4,3);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (8,'Steven','Duck','s.duck@gmail.com','Steven','isteven',1,5,1);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (9,'Barny','Stock','b.stock@gmail.com','Barny','ibarny',2,1,4);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (10,'Ted','Winston','t.winston@gmail.com','Ted','ited',2,2,1);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (11,'Lock','Born','l.born@gmail.com','Lock','ilock',2,3,2);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (12,'Rowen','Bun','r.bun@gmail.com','Rowen','irowen',2,4,3);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (13,'Mick','Jocker','m.jocker@gmail.com','Mick','imick',2,4,4);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (14,'Viki','Krusty','v.krusty@gmail.com','Viki','iviki',3,1,1);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (15,'Karen','Shot','k.shot@gmail.com','Karen','ikaren',3,5,2);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (16,'Nick','Wizard','n.wizard@gmail.com','Nick','inick',3,5,3);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (17,'Beck','Voon','b.voon@gmail.com','Beck','ibeck',4,1,4);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (18,'Harry','Berg','h.berg@gmail.com','Harry','iharry',4,2,1);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (19,'Lusy','Vanish','l.vanish@gmail.com','Lusy','ilusy',4,3,2);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (20,'Perez','Hoock','p.hoock@gmail.com','Perez','iperez',4,4,3);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (21,'Edgar','Moon','e.moon@gmail.com','Edgar','iedgar',4,5,4);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (22,'Greek','Clinc','g.clinc@gmail.com','Greek','igreek',4,6,1);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (23,'Kent','Buck','k.buck@gmail.com','Kent','ikent',4,4,2);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (24,'Den','Fisher','d.fisher@gmail.com','Den','iden',4,5,3);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (25,'Julia','Hizi','j.hizi@gmail.com','Julia','ijulia',5,1,3);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (26,'Antoni','Wisdom','a.wisdom@gmail.com','Antoni','iantoni',5,6,4);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (27,'James','Cook','j.cool@gmail.com','James','ijames',5,6,1);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (28,'Lily','Week','l.week@gmail.com','Lily','ilily',6,1,2);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (29,'Martin','Getz','m.getz@gmail.com','Martin','imartin',6,2,3);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (30,'Tom','Crug','t.crug@gmail.ru','Tom','itom',6,3,4);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (31,'Erick','Fitch','e.fitch@gmail.com','Erick','ierick',6,4,2);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (32,'Pod','Flinstone','p.flinstone@gmail.com','Pod','ipod',6,4,3);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (33,'Ben','Slim','b.slim@gmail.com','Ben','iben',7,1,1);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (34,'Maria','Clemens','m.clemens@gmail.com','Maria','imaria',7,5,2);
Insert into EMPLOYEE (ID,FIRST_NAME,SECOND_NAME,EMAIL,LOGIN,PASSWORD,DEP_ID,POSITION_ID,OFFICE_ID) values (35,'Kira','Noock','k.noock@gmail.com','Kira','ikira',7,5,3);
REM INSERTING into DESTINATION
SET DEFINE OFF;
Insert into DESTINATION (ID,CITY_ID,HOTELNAME,HOTELSITE) values (1,1,'Hayat','hayat.com');
Insert into DESTINATION (ID,CITY_ID,HOTELNAME,HOTELSITE) values (2,2,'Hilton','hilton.com');
Insert into DESTINATION (ID,CITY_ID,HOTELNAME,HOTELSITE) values (3,3,'Downtown','downtown.com');
Insert into DESTINATION (ID,CITY_ID,HOTELNAME,HOTELSITE) values (4,5,'Hilton','hilton.com');
REM INSERTING into ROLE
SET DEFINE OFF;
Insert into ROLE (ID,ROLE_NAME) values (1,'Common Department');
Insert into ROLE (ID,ROLE_NAME) values (2,'Travel Department');
Insert into ROLE (ID,ROLE_NAME) values (3,'IT Department');
REM INSERTING into ROLEDEP
SET DEFINE OFF;
INSERT INTO roledep (dep_id, role_id) VALUES (1,1);
INSERT INTO roledep (dep_id, role_id) VALUES (1,2);
INSERT INTO roledep (dep_id, role_id) VALUES (1,3);
INSERT INTO roledep (dep_id, role_id) VALUES (2,1);
INSERT INTO roledep (dep_id, role_id) VALUES (3,1);
INSERT INTO roledep (dep_id, role_id) VALUES (3,2);
INSERT INTO roledep (dep_id, role_id) VALUES (4,1);
INSERT INTO roledep (dep_id, role_id) VALUES (4,2);
INSERT INTO roledep (dep_id, role_id) VALUES (5,1);
INSERT INTO roledep (dep_id, role_id) VALUES (6,1);
INSERT INTO roledep (dep_id, role_id) VALUES (7,1);
REM INSERTING into TRF
SET DEFINE OFF;
Insert into TRF (ID,DESTINATION_ID,CUSTOMER_ID,EMP_ID,BEGIN_DATE,END_DATE,CAR_RENTAL,CAR_PAYMENT,CUR_STATE) values (1,1,1,1,to_date('13-MAR-90','DD-MON-RR'),to_date('23-MAR-90','DD-MON-RR'),0,1,1);
Insert into TRF (ID,DESTINATION_ID,CUSTOMER_ID,EMP_ID,BEGIN_DATE,END_DATE,CAR_RENTAL,CAR_PAYMENT,CUR_STATE) values (2,2,2,2,to_date('27-JUN-12','DD-MON-RR'),to_date('03-JUL-12','DD-MON-RR'),1,0,3);
Insert into TRF (ID,DESTINATION_ID,CUSTOMER_ID,EMP_ID,BEGIN_DATE,END_DATE,CAR_RENTAL,CAR_PAYMENT,CUR_STATE) values (3,3,3,5,to_date('20-FEB-10','DD-MON-RR'),to_date('21-FEB-10','DD-MON-RR'),1,1,2);
REM INSERTING into TRFSTATE
SET DEFINE OFF;
Insert into TRFSTATE (ID,TRF_ID,COMMENTARY,CHANGE_DATE,STATUS,CHANGER) values (1,1,'rejected: wrong date',to_date('17-APR-12','DD-MON-RR'),2,23);
Insert into TRFSTATE (ID,TRF_ID,COMMENTARY,CHANGE_DATE,STATUS,CHANGER) values (2,1,'aaccepted: ok',to_date('02-APR-12','DD-MON-RR'),3,4);
Insert into TRFSTATE (ID,TRF_ID,COMMENTARY,CHANGE_DATE,STATUS,CHANGER) values (3,2,'canceled: employee fired',to_date('17-OCT-12','DD-MON-RR'),0,7);

UPDATE department SET manager_id=4 where id=1;
UPDATE department SET manager_id=11 where id=2;
UPDATE department SET manager_id=15 where id=3;
UPDATE department SET manager_id=19 where id=4;
UPDATE department SET manager_id=26 where id=5;
UPDATE department SET manager_id=29 where id=6;
UPDATE department SET manager_id=35 where id=7;
