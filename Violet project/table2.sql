--Author Gangbang34 (edited by Allan)

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
DROP TABLE deprole;
DROP SEQUENCE department_id_seq;
DROP SEQUENCE occupation_id_seq;
DROP SEQUENCE employee_id_seq;
DROP SEQUENCE office_id_seq;
DROP SEQUENCE destination_id_seq;
DROP SEQUENCE customer_id_seq;
DROP SEQUENCE trf_id_seq;
DROP SEQUENCE trfstate_id_seq;
DROP SEQUENCE country_id_seq;
DROP SEQUENCE city_id_seq;
DROP SEQUENCE deprole_id_seq;

--Creating tables:
CREATE TABLE country(
  id NUMERIC(10),
  country_name VARCHAR2(20),
 CONSTRAINT country_id_pk PRIMARY KEY(id)
);

CREATE TABLE city(
  id NUMERIC(10),
  city_name VARCHAR2(20),
  country_id NUMERIC(10),
 CONSTRAINT city_id_pk PRIMARY KEY(id),
 CONSTRAINT country_country_id_fk FOREIGN KEY(country_id) REFERENCES country(id)
 ON DELETE SET NULL
);

CREATE TABLE office(
  id numeric(10),
  office_name varchar2(50),
  city_id NUMERIC(10),
 CONSTRAINT office_id_pk PRIMARY KEY(id),
 CONSTRAINT office_city_id_fk FOREIGN KEY(city_id) REFERENCES city(id)
 ON DELETE SET NULL
);

CREATE TABLE occupation(
  id numeric(10),
  pos_name varchar2(25),
 CONSTRAINT occupation_id_pk PRIMARY KEY(id)
);

CREATE TABLE deprole(
  id NUMERIC(10),
  role_name VARCHAR2(100),
 CONSTRAINT role_id_pk PRIMARY KEY(id)
);

CREATE TABLE department(
  id numeric(10),
  dep_name varchar2(40),
  parent_id numeric(10),
  manager_id NUMERIC(10),
 CONSTRAINT department_id_pk PRIMARY KEY(id),
 CONSTRAINT department_parent_id FOREIGN KEY(parent_id) REFERENCES department(id),
 CONSTRAINT department_manager_id_uk UNIQUE(manager_id)
);

CREATE TABLE roledep(
  role_id NUMERIC(10),
  dep_id NUMERIC(10),
 CONSTRAINT roledep_role_id_fk FOREIGN KEY(role_id) REFERENCES deprole(id)
 ON DELETE CASCADE,
 CONSTRAINT roledep_dep_id_fk FOREIGN KEY(dep_id) REFERENCES department(id) ON DELETE CASCADE
);

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
 CONSTRAINT employee_dep_id_fk FOREIGN KEY(dep_id) REFERENCES department(id)
 ON DELETE SET NULL,
 CONSTRAINT employee_position_id_fk FOREIGN KEY(position_id) REFERENCES occupation(id)
 ON DELETE SET NULL,
 CONSTRAINT employee_office_id_fk FOREIGN KEY(office_id) REFERENCES office(id)
 ON DELETE SET NULL,
 CONSTRAINT employee_login_uk UNIQUE(login)
);

CREATE TABLE destination(
  id numeric(10),
  city_id NUMERIC(10),
  hotelname varchar2(30),
  hotelsite varchar2(100),
  is_approved char check (is_approved in (0,1)),
 CONSTRAINT destination_id_pk PRIMARY KEY(id),
 CONSTRAINT destination_dest_city_id_fk FOREIGN KEY(city_id) REFERENCES city(id)
 ON DELETE CASCADE
);

CREATE TABLE customer(
  id numeric(10),
  cust_name varchar2(20),
 CONSTRAINT customer_id_pk PRIMARY KEY(id)
);

CREATE TABLE trf(
  id numeric(10),
  destination_id numeric(10),
  customer_id numeric(10),
  emp_id numeric(10),
  begin_date date,
  end_date date,
  car_rental char check (car_rental in (0,1)),
  pay_by_cash char check (pay_by_cash in (0,1)),
  cur_state NUMERIC(1),
  project_manager NUMERIC(10),
 CONSTRAINT trf_id_pk PRIMARY KEY(id),
 CONSTRAINT trf_destination_id_fk FOREIGN KEY (destination_id) REFERENCES destination(id)
 ON DELETE SET NULL,
 CONSTRAINT trf_customer_id_fk FOREIGN KEY (customer_id) REFERENCES customer(id)
 ON DELETE SET NULL,
 CONSTRAINT trf_emp_id_fk FOREIGN KEY (emp_id) REFERENCES employee(id)
 ON DELETE CASCADE,
 CONSTRAINT trf_project_manager FOREIGN KEY(project_manager) references employee(id)
 ON DELETE SET NULL
);

CREATE TABLE trfstate(
  id numeric(10),
  trf_id numeric(10),
  commentary varchar2(1000),
  change_date date,
  status numeric(1),
  changer numeric(10),
 CONSTRAINT trfstate_id_pk PRIMARY KEY(id),
 CONSTRAINT trfstate_trf_id_fk FOREIGN KEY(trf_id) REFERENCES trf(id)
 ON DELETE CASCADE
);


--Creating sequences:
--//start with = (number of inserted rows in export2.sql)+1;
CREATE SEQUENCE department_id_seq
INCREMENT BY 1
START WITH 28;

CREATE SEQUENCE occupation_id_seq
INCREMENT BY 1
START WITH 7;

CREATE SEQUENCE employee_id_seq
INCREMENT BY 1
START WITH 82;

CREATE SEQUENCE office_id_seq
INCREMENT BY 1
START WITH 6;

CREATE SEQUENCE destination_id_seq
INCREMENT BY 1
START WITH 5;

CREATE SEQUENCE customer_id_seq
INCREMENT BY 1
START WITH 4;

CREATE SEQUENCE trf_id_seq
INCREMENT BY 1
START WITH 19;

CREATE SEQUENCE trfstate_id_seq
INCREMENT BY 1
START WITH 28;

CREATE SEQUENCE country_id_seq
INCREMENT BY 1
START WITH 3;

CREATE SEQUENCE city_id_seq
INCREMENT BY 1
START WITH 6;

CREATE SEQUENCE deprole_id_seq
INCREMENT BY 1
START WITH 4;


--Creating triggers:

--index trigger by Allan
/*CREATE OR REPLACE TRIGGER "DEPARTMENT_ID_TRIGGER"
    BEFORE INSERT ON "DEPARTMENT"
    FOR EACH ROW
    DECLARE
        dummy INTEGER;
        CURSOR dummy_cursor (checked_id NUMBER) IS
        SELECT id FROM department
        where id=checked_id;
BEGIN
    dummy := 1;
    open dummy_cursor (dummy);
    fetch dummy_cursor into dummy;
    while (dummy_cursor%FOUND) loop
        dummy:=dummy+1;
        close dummy_cursor;
        open dummy_cursor (dummy);
        fetch dummy_cursor into dummy;
    end loop;
    
    IF :NEW.ID IS NULL THEN
        :NEW.ID := dummy;
    END IF;
END;
/*/

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
    BEFORE INSERT ON "OCCUPATION"
    FOR EACH ROW
    BEGIN
    IF :NEW.ID IS NULL THEN
        SELECT "OCCUPATION_ID_SEQ".NEXTVAL INTO :NEW.ID
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

CREATE OR REPLACE TRIGGER "DEPROLE_ID_TRIGGER"
    BEFORE INSERT ON "DEPROLE"
    FOR EACH ROW
    BEGIN
    IF :NEW.ID IS NULL THEN
        SELECT "DEPROLE_ID_SEQ".NEXTVAL INTO :NEW.ID
        FROM DUAL;
    END IF;
END;
/




--password hashing trigger by Allan
CREATE OR REPLACE TRIGGER "PASSWORD_HASH_TRIGGER"
    BEFORE INSERT OR UPDATE OF "PASSWORD" ON "EMPLOYEE"
    FOR EACH ROW
    DECLARE
        password_null EXCEPTION;
BEGIN
    IF (:NEW.PASSWORD IS NULL) THEN
        RAISE password_null;
    ELSE
        --:NEW.PASSWORD := dbms_obfuscation_toolkit.MD5(input_string => :NEW.PASSWORD); 
        --if don't have oracle XE 11g server: 
        :NEW.PASSWORD := dbms_utility.get_hash_value(:NEW.PASSWORD, 0, 4096); 
    END IF;
    EXCEPTION
        WHEN password_null THEN
        Raise_application_error(-20034, 'Error! Trying to have empty password');
END;
/


--Creating indexes:
CREATE INDEX department_dep_name_idx
  ON department(dep_name);

CREATE INDEX city_city_name_idx
  ON city(city_name);

CREATE INDEX country_country_name_idx
  ON country(country_name);


