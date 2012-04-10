DROP TABLE trf;
DROP TABLE employee;
DROP TABLE department;
DROP TABLE destination;
DROP TABLE customer;
DROP TABLE office;
DROP TABLE position;
DROP SEQUENCE office_id_seq;
DROP SEQUENCE destination_id_seq;
DROP SEQUENCE customer_id_seq;
DROP SEQUENCE trf_id_seq;
DROP SEQUENCE employee_id_seq;
DROP SEQUENCE department_id_seq;
DROP SEQUENCE position_id_seq;


CREATE TABLE office(
id numeric(10),
oname varchar2(20),
ocountry varchar2(15),
CONSTRAINT office_id_pk PRIMARY KEY(id));

CREATE TABLE position (
id numeric(10),
pos_name varchar2(25),
CONSTRAINT position_id_pk PRIMARY KEY(id));

CREATE TABLE department(
id numeric(10),
dep_name varchar2(40),
parent_id numeric(10),
role numeric(1),
office_id numeric(10),
CONSTRAINT department_id_pk PRIMARY KEY(id),
CONSTRAINT employee_office_id_fk FOREIGN KEY (office_id) REFERENCES office(id),
CONSTRAINT department_parent_id FOREIGN KEY(parent_id) REFERENCES department(id));


CREATE TABLE employee(
id numeric(10),
first_name varchar2(20),
second_name varchar2(20),
email varchar2(256),
login varchar2(20),
password varchar2(30),
dep_id numeric(10),
position_id numeric(10),
is_manager numeric(1),
CONSTRAINT employee_id_pk PRIMARY KEY(id),
CONSTRAINT employee_dep_id_fk FOREIGN KEY(dep_id) REFERENCES department(id),
CONSTRAINT employee_position_id_fk FOREIGN KEY(position_id) REFERENCES position(id));

CREATE TABLE destination(
id numeric(10),
dname varchar2(20),
dcountry varchar2(15),
CONSTRAINT destination_id_pk PRIMARY KEY(id));

CREATE TABLE customer(
id numeric(10),
cust_name varchar2(20),
CONSTRAINT customer_id_pk PRIMARY KEY(id));


CREATE TABLE trf(
id numeric(10),
destination_id numeric(10),
customer_id numeric(10),
emp_id numeric(10),
begin_date date,
end_date date,
status numeric(1),
hotelname varchar2(30),
hotelsite varchar2(100),
car_rental numeric(1),
car_payment numeric(1),
commentary varchar2(1000),
CONSTRAINT trf_id_pk PRIMARY KEY(id),
CONSTRAINT trf_destination_id_fk FOREIGN KEY (destination_id) REFERENCES destination(id),
CONSTRAINT trf_customer_id_fk FOREIGN KEY (customer_id) REFERENCES customer(id),
CONSTRAINT trf_emp_id_fk FOREIGN KEY (emp_id) REFERENCES employee(id));



CREATE SEQUENCE department_id_seq
INCREMENT BY 1
START WITH 1
NOCYCLE;

CREATE SEQUENCE position_id_seq
INCREMENT BY 1
START WITH 1
NOCYCLE;

CREATE SEQUENCE employee_id_seq
INCREMENT BY 1
START WITH 1
NOCYCLE;

CREATE SEQUENCE office_id_seq
INCREMENT BY 1
START WITH 1
NOCYCLE;


CREATE SEQUENCE destination_id_seq
INCREMENT BY 1
START WITH 1
NOCYCLE;

CREATE SEQUENCE customer_id_seq
INCREMENT BY 1
START WITH 1
NOCYCLE;

CREATE SEQUENCE trf_id_seq
INCREMENT BY 1
START WITH 1
NOCYCLE;

CREATE OR REPLACE TRIGGER "DEPARTMENT_ID_TRIGGER"
BEFORE INSERT ON "DEPARTMENT"
FOR EACH ROW
BEGIN
IF :NEW.ID IS NULL THEN
SELECT "DEPARTMENT_ID_SEQ".NEXTVAL INTO :NEW.ID
FROM DUAL;
END IF;
END;
/

CREATE OR REPLACE TRIGGER "POSITION_ID_TRIGGER"
    BEFORE INSERT ON "POSITION"
    FOR EACH ROW
BEGIN
    IF :NEW.ID IS NULL THEN
        SELECT "POSITION_ID_SEQ".NEXTVAL INTO :NEW.ID
        FROM DUAL;
    END IF;
END;
/
    
CREATE OR REPLACE TRIGGER "EMPLOYEE_ID_TRIGGER"
    BEFORE INSERT ON "EMPLOYEE"
    FOR EACH ROW
BEGIN
    IF :NEW.ID IS NULL THEN
        SELECT "EMPLOYEE_ID_SEQ".NEXTVAL INTO :NEW.ID
        FROM DUAL;
    END IF;
END;
/
    
CREATE OR REPLACE TRIGGER "OFFICE_ID_TRIGGER"
    BEFORE INSERT ON "OFFICE"
    FOR EACH ROW
BEGIN
    IF :NEW.ID IS NULL THEN
        SELECT "OFFICE_ID_SEQ".NEXTVAL INTO :NEW.ID
        FROM DUAL;
    END IF;
END;
/
    
CREATE OR REPLACE TRIGGER "DESTINATION_ID_TRIGGER"
    BEFORE INSERT ON "DESTINATION"
    FOR EACH ROW
BEGIN
    IF :NEW.ID IS NULL THEN
        SELECT "DESTINATION_ID_SEQ".NEXTVAL INTO :NEW.ID
        FROM DUAL;
    END IF;
END;
/
    
CREATE OR REPLACE TRIGGER "CUSTOMER_ID_TRIGGER"
    BEFORE INSERT ON "CUSTOMER"
    FOR EACH ROW
BEGIN
    IF :NEW.ID IS NULL THEN
        SELECT "CUSTOMER_ID_SEQ".NEXTVAL INTO :NEW.ID
        FROM DUAL;
    END IF;
END;
/
    
CREATE OR REPLACE TRIGGER "TRF_ID_TRIGGER"
    BEFORE INSERT ON "TRF"
    FOR EACH ROW
BEGIN
    IF :NEW.ID IS NULL THEN
        SELECT "TRF_ID_SEQ".NEXTVAL INTO :NEW.ID
        FROM DUAL;
    END IF;
END;
/

CREATE VIEW managers 
AS SELECT id, dep_id, is_manager
FROM employee;


CREATE OR REPLACE TRIGGER "MANAGER_TRIGGER"
BEFORE INSERT ON "EMPLOYEE"
FOR EACH ROW
DECLARE
dummy INTEGER;
managers_exist EXCEPTION;
no_managers_exist EXCEPTION;
  depn NUMBER;
  empid NUMBER;
CURSOR dummy_cursor (dep NUMBER) IS
SELECT id FROM manager WHERE is_manager = 1 AND dep_id = dep;

BEGIN
--check if there are no other managers if we update or insert
--if there are - remove them
  depn := :NEW.dep_id;
IF :NEW.IS_MANAGER = 1 THEN
OPEN dummy_cursor(depn);
FETCH dummy_cursor INTO dummy;
IF dummy_cursor%FOUND THEN
RAISE managers_exist;
ELSE
RAISE no_managers_exist;
END IF;
CLOSE dummy_cursor;
END IF;
EXCEPTION
  WHEN managers_exist THEN
  CLOSE dummy_cursor;
  Raise_application_error(-20034, 'Cannot add another manager');
  WHEN no_managers_exist THEN
  CLOSE dummy_cursor;
  
END;
/