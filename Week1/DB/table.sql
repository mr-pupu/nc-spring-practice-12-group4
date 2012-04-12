DROP TABLE roledep;
DROP TABLE trfstate;
DROP TABLE trf;
DROP TABLE employee;
DROP TABLE department;
DROP TABLE destination;
DROP TABLE customer;
DROP TABLE office;
DROP TABLE occupation;
DROP TABLE city;
DROP TABLE country;
DROP TABLE role;
DROP SEQUENCE office_id_seq;
DROP SEQUENCE destination_id_seq;
DROP SEQUENCE customer_id_seq;
DROP SEQUENCE trf_id_seq;
DROP SEQUENCE trfstate_id_seq;
DROP SEQUENCE employee_id_seq;
DROP SEQUENCE department_id_seq;
DROP SEQUENCE occupation_id_seq;
DROP SEQUENCE city_id_seq;
DROP SEQUENCE country_id_seq;
DROP SEQUENCE role_id_seq;

CREATE TABLE country(
  id NUMERIC(10),
  country_name VARCHAR2(20),
  CONSTRAINT country_id_pk PRIMARY KEY(id));

CREATE TABLE city(
  id NUMERIC(10),
  city_name VARCHAR2(20),
  country_id NUMERIC(10),
  CONSTRAINT city_id_pk PRIMARY KEY(id),
  CONSTRAINT country_country_id_fk FOREIGN KEY(country_id) REFERENCES country(id));

CREATE TABLE office(
id numeric(10),
  city_id NUMERIC(10),
CONSTRAINT office_id_pk PRIMARY KEY(id),
  CONSTRAINT office_city_id_fk FOREIGN KEY(city_id) REFERENCES city(id));

CREATE TABLE occupation (
id numeric(10),
pos_name varchar2(25),
CONSTRAINT occupation_id_pk PRIMARY KEY(id));

CREATE TABLE role(
  id NUMERIC(10),
  role_name VARCHAR2(200),
  CONSTRAINT role_id_pk PRIMARY KEY(id));

CREATE TABLE department(
id numeric(10),
dep_name varchar2(40),
parent_id numeric(10),
  manager_id NUMERIC(10),
CONSTRAINT department_id_pk PRIMARY KEY(id),
CONSTRAINT department_parent_id FOREIGN KEY(parent_id) REFERENCES department(id));

CREATE TABLE roledep(
  role_id NUMERIC(10),
  dep_id NUMERIC(10),
  CONSTRAINT roledep_role_id_fk FOREIGN KEY(role_id) REFERENCES role(id),
  CONSTRAINT roledep_dep_id_fk FOREIGN KEY(dep_id) REFERENCES department(id));

CREATE TABLE employee(
id numeric(10),
first_name varchar2(20),
second_name varchar2(20),
email varchar2(256),
login varchar2(20),
password varchar2(30),
dep_id numeric(10),
position_id numeric(10),
  office_id NUMERIC(10),
CONSTRAINT employee_id_pk PRIMARY KEY(id),
CONSTRAINT employee_dep_id_fk FOREIGN KEY(dep_id) REFERENCES department(id),
CONSTRAINT employee_position_id_fk FOREIGN KEY(position_id) REFERENCES occupation(id),
  CONSTRAINT employee_office_id_fk FOREIGN KEY(office_id) REFERENCES office(id));

CREATE TABLE destination(
id numeric(10),
  dest_city_id NUMERIC(10),
CONSTRAINT destination_id_pk PRIMARY KEY(id),
  CONSTRAINT destination_dest_city_id_fk FOREIGN KEY(dest_city_id) REFERENCES city(id));

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
hotelname varchar2(30),
hotelsite varchar2(100),
car_rental numeric(1),
car_payment numeric(1),
  cur_state NUMERIC(1),
CONSTRAINT trf_id_pk PRIMARY KEY(id),
CONSTRAINT trf_destination_id_fk FOREIGN KEY (destination_id) REFERENCES destination(id),
CONSTRAINT trf_customer_id_fk FOREIGN KEY (customer_id) REFERENCES customer(id),
CONSTRAINT trf_emp_id_fk FOREIGN KEY (emp_id) REFERENCES employee(id));

CREATE TABLE trfstate
(id numeric(10),
trf_id numeric(10),
commentary varchar2(1000),
change_date date,
status numeric(1),
CONSTRAINT trfstate_id_pk PRIMARY KEY(id),
CONSTRAINT trfstate_trf_id_fk FOREIGN KEY(trf_id) REFERENCES trf(id)
);

CREATE SEQUENCE department_id_seq
INCREMENT BY 1
START WITH 1;

CREATE SEQUENCE occupation_id_seq
INCREMENT BY 1
START WITH 1;

CREATE SEQUENCE employee_id_seq
INCREMENT BY 1
START WITH 1;

CREATE SEQUENCE office_id_seq
INCREMENT BY 1
START WITH 1;


CREATE SEQUENCE destination_id_seq
INCREMENT BY 1
START WITH 1;

CREATE SEQUENCE customer_id_seq
INCREMENT BY 1
START WITH 1;

CREATE SEQUENCE trf_id_seq
INCREMENT BY 1
START WITH 1;

CREATE SEQUENCE trfstate_id_seq
INCREMENT BY 1
START WITH 1;


CREATE SEQUENCE role_id_seq
INCREMENT BY 1
START WITH 1;

CREATE SEQUENCE city_id_seq
INCREMENT BY 1
START WITH 1;

CREATE SEQUENCE country_id_seq
INCREMENT BY 1
START WITH 1;

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

CREATE OR REPLACE TRIGGER "OCCUPATION_ID_TRIGGER"
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

CREATE OR REPLACE TRIGGER "TRFSTATE_ID_TRIGGER"
    BEFORE INSERT ON "TRFSTATE"
    FOR EACH ROW
BEGIN
    IF :NEW.ID IS NULL THEN
        SELECT "TRFSTATE_ID_SEQ".NEXTVAL INTO :NEW.ID
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

CREATE OR REPLACE TRIGGER "COUNTRY_ID_TRIGGER"
    BEFORE INSERT ON "COUNTRY"
    FOR EACH ROW
BEGIN
    IF :NEW.ID IS NULL THEN
        SELECT "COUNTRY_ID_SEQ".NEXTVAL INTO :NEW.ID
        FROM DUAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER "CITY_ID_TRIGGER"
    BEFORE INSERT ON "CITY"
    FOR EACH ROW
BEGIN
    IF :NEW.ID IS NULL THEN
        SELECT "CITY_ID_SEQ".NEXTVAL INTO :NEW.ID
        FROM DUAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER "ROLE_ID_TRIGGER"
    BEFORE INSERT ON "ROLE"
    FOR EACH ROW
BEGIN
    IF :NEW.ID IS NULL THEN
        SELECT "ROLE_ID_SEQ".NEXTVAL INTO :NEW.ID
        FROM DUAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER "MANAGER_TRIGGER"
BEFORE INSERT OR UPDATE OF "MANAGER_ID" ON "DEPARTMENT"
FOR EACH ROW
DECLARE
dummy INTEGER;
managers_exist EXCEPTION;
no_managers_exist EXCEPTION;
  empid NUMBER;
olddep NUMBER;
CURSOR dummy_cursor (dep NUMBER) IS
SELECT id FROM employee WHERE id=dep;

BEGIN
  empid := :NEW.MANAGER_ID;
--if we delete, or remove the manager by updating, check if there are managers left
--if not - rollback
  IF NOT(:NEW.MANAGER_ID IS NULL) THEN
OPEN dummy_cursor(empid);
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
  WHEN no_managers_exist THEN
  CLOSE dummy_cursor;
  Raise_application_error(-20034, 'Error! Trying to set nonexistent employee as manager');
END;
/