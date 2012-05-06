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
DROP SEQUENCE trf_id_seq;
DROP SEQUENCE trfstate_id_seq;

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
);

CREATE TABLE office(
  id numeric(10),
  office_name varchar2(50),
  city_id NUMERIC(10),
 CONSTRAINT office_id_pk PRIMARY KEY(id),
 CONSTRAINT office_city_id_fk FOREIGN KEY(city_id) REFERENCES city(id)
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
 CONSTRAINT department_parent_id FOREIGN KEY(parent_id) REFERENCES department(id) on delete CASCADE,
 CONSTRAINT department_manager_id_uk UNIQUE(manager_id)
);

CREATE TABLE roledep(
  role_id NUMERIC(10),
  dep_id NUMERIC(10),
 CONSTRAINT roledep_role_id_fk FOREIGN KEY(role_id) REFERENCES deprole(id),
 CONSTRAINT roledep_dep_id_fk FOREIGN KEY(dep_id) REFERENCES department(id)
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
 CONSTRAINT employee_dep_id_fk FOREIGN KEY(dep_id) REFERENCES department(id),
 CONSTRAINT employee_position_id_fk FOREIGN KEY(position_id) REFERENCES occupation(id),
 CONSTRAINT employee_office_id_fk FOREIGN KEY(office_id) REFERENCES office(id),
 CONSTRAINT employee_login_uk UNIQUE(login)
);

CREATE TABLE destination(
  id numeric(10),
  city_id NUMERIC(10),
  hotelname varchar2(30),
  hotelsite varchar2(100),
 CONSTRAINT destination_id_pk PRIMARY KEY(id),
 CONSTRAINT destination_dest_city_id_fk FOREIGN KEY(city_id) REFERENCES city(id)
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
 CONSTRAINT trf_destination_id_fk FOREIGN KEY (destination_id) REFERENCES destination(id),
 CONSTRAINT trf_customer_id_fk FOREIGN KEY (customer_id) REFERENCES customer(id),
 CONSTRAINT trf_emp_id_fk FOREIGN KEY (emp_id) REFERENCES employee(id),
 CONSTRAINT trf_project_manager FOREIGN KEY(project_manager) references employee(id)
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
);


--Creating sequences:
--//start with = (number of inserted rows in export2.sql)+1;
CREATE SEQUENCE trf_id_seq
INCREMENT BY 1
START WITH 6;

CREATE SEQUENCE trfstate_id_seq
INCREMENT BY 1
START WITH 12;


--Creating triggers:
CREATE OR REPLACE TRIGGER "DEPARTMENT_ID_TRIGGER"
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
/

CREATE OR REPLACE TRIGGER "OCCUPATION_ID_TRIGGER"
    BEFORE INSERT ON "OCCUPATION"
    FOR EACH ROW
    DECLARE
        dummy INTEGER;
        CURSOR dummy_cursor (checked_id NUMBER) IS
        SELECT id FROM occupation
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
        :NEW.ID :=dummy;
    END IF;
END;
/
    
CREATE OR REPLACE TRIGGER "EMPLOYEE_ID_TRIGGER"
    BEFORE INSERT ON "EMPLOYEE"
    FOR EACH ROW
    DECLARE
        dummy INTEGER;
        CURSOR dummy_cursor (checked_id NUMBER) IS
        SELECT id FROM employee
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
        :NEW.ID :=dummy;
    END IF;
END;
/
    
CREATE OR REPLACE TRIGGER "OFFICE_ID_TRIGGER"
    BEFORE INSERT ON "OFFICE"
    FOR EACH ROW
    DECLARE
        dummy INTEGER;
        CURSOR dummy_cursor (checked_id NUMBER) IS
        SELECT id FROM office
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
        :NEW.ID :=dummy;
    END IF;
END;
/
    
CREATE OR REPLACE TRIGGER "DESTINATION_ID_TRIGGER"
    BEFORE INSERT ON "DESTINATION"
    FOR EACH ROW
    DECLARE
        dummy INTEGER;
        CURSOR dummy_cursor (checked_id NUMBER) IS
        SELECT id FROM destination
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
        :NEW.ID :=dummy;
    END IF;
END;
/
    
CREATE OR REPLACE TRIGGER "CUSTOMER_ID_TRIGGER"
    BEFORE INSERT ON "CUSTOMER"
    FOR EACH ROW
    DECLARE
        dummy INTEGER;
        CURSOR dummy_cursor (checked_id NUMBER) IS
        SELECT id FROM customer
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
        :NEW.ID :=dummy;
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
    DECLARE
        dummy INTEGER;
        CURSOR dummy_cursor (checked_id NUMBER) IS
        SELECT id FROM country
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
        :NEW.ID :=dummy;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER "CITY_ID_TRIGGER"
    BEFORE INSERT ON "CITY"
    FOR EACH ROW
    DECLARE
        dummy INTEGER;
        CURSOR dummy_cursor (checked_id NUMBER) IS
        SELECT id FROM city
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
        :NEW.ID :=dummy;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER "DEPROLE_ID_TRIGGER"
    BEFORE INSERT ON "DEPROLE"
    FOR EACH ROW
    DECLARE
        dummy INTEGER;
        CURSOR dummy_cursor (checked_id NUMBER) IS
        SELECT id FROM deprole
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
        :NEW.ID :=dummy;
    END IF;
END;
/

--manager trigger by Oleg Lunin
CREATE OR REPLACE TRIGGER "MANAGER_TRIGGER"
    BEFORE INSERT OR UPDATE OF "MANAGER_ID" ON "DEPARTMENT"
    FOR EACH ROW
    DECLARE
        dummy INTEGER;
        managers_exist EXCEPTION;
        no_managers_exist EXCEPTION;
        empid NUMBER;
        olddep NUMBER;
        CURSOR dummy_cursor (dep NUMBER, depnum NUMBER) IS
        SELECT id FROM employee WHERE id=dep AND dep_id=depnum;
BEGIN
    empid := :NEW.MANAGER_ID;
    olddep := :NEW.ID;
--if we delete, or remove the manager by updating, check if there are managers left
--if not - rollback
    IF NOT(:NEW.MANAGER_ID IS NULL) THEN
        OPEN dummy_cursor(empid, olddep);
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
        Raise_application_error(-20034, 'Error! The employee you are appointing as manager 
        is either nonexistent or belonging to another department');
END;
/

--changer trigger by Oleg Lunin
CREATE OR REPLACE TRIGGER "CHANGER_TRIGGER"
    BEFORE INSERT OR UPDATE OF "CHANGER" ON "TRFSTATE"
    FOR EACH ROW
    DECLARE
        dummy INTEGER;
        managers_exist EXCEPTION;
        no_managers_exist EXCEPTION;
        empid NUMBER;
        CURSOR dummy_cursor (dep NUMBER) IS
        SELECT id FROM employee WHERE id=dep;
BEGIN
    empid := :NEW.CHANGER;
--if we delete, or remove the manager by updating, check if there are managers left
--if not - rollback
    IF NOT(:NEW.CHANGER IS NULL) THEN
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
        Raise_application_error(-20034, 'Error! Trying to set nonexistent employee trf editor');
END;
/

--password hashing trigger by Allan Farfur
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


