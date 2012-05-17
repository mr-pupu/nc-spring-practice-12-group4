--Author Gangbang34

--This view represents the department table joined to itself via the foreign key parent_id
CREATE OR replace VIEW department_selfjoin
  AS
SELECT sub.id, sub.dep_name, sub.parent_id, sub.manager_id, sup.dep_name supname
FROM department sub, department sup
  WHERE sub.parent_id = sup.id;

--this view contains a triple employee-department-employee join that allows to quickly locate manager names
  --for a given employee (provided he has one)
CREATE OR replace VIEW employee_managers_department
  AS
SELECT emp.id, emp.first_name, emp.login, emp.second_name, emp.dep_id, sup.id supid, manager.first_name manname, manager.second_name mansurname
FROM employee emp INNER JOIN department sub ON emp.dep_id = sub.id, department sup INNER JOIN employee manager
  ON sup.manager_id = manager.id
  WHERE sub.parent_id = sup.id;

--this view is a quadruple employee-department-department-employee join that is designed for the purpose of retrieving managers
  --of upper level departments (provided they exist)
CREATE OR replace VIEW employee_managers
  AS
SELECT emp.id, emp.first_name, emp.login, emp.second_name, dep.dep_name, man.first_name manname, man.second_name mansurname, man.login manlogin
  FROM employee emp INNER JOIN department dep ON emp.dep_id=dep.id
  INNER JOIN employee man ON dep.manager_id = man.id;

--this view simply joins departments and employees
CREATE OR replace VIEW department_employee
  AS
SELECT emp.first_name, emp.second_name, emp.id empid, dep.id, dep.parent_id, dep.dep_name, dep.manager_id, occupation.pos_name
  FROM employee emp INNER JOIN department dep ON emp.dep_id = dep.id
  INNER JOIN occupation ON emp.position_id = occupation.id;

--this view joins trf, employee, office, city and country tables
CREATE OR replace VIEW trf_office
  AS
  SELECT tr.*, cty.city_name, ctry.country_name, emp.id empid
  FROM trf tr INNER JOIN employee emp ON tr.emp_id = emp.id
  INNER JOIN office o ON emp.office_id = o.id
  INNER JOIN city cty ON cty.id = o.city_id
  INNER JOIN country ctry ON ctry.id = cty.country_id;

--this view joins trf, employee and department
CREATE OR replace VIEW trf_department
  AS
  SELECT tr.*, dep.dep_name
FROM trf tr INNER JOIN employee emp ON tr.emp_id = emp.id
  INNER JOIN department dep ON emp.dep_id = dep.id;

--this view joins trf, employee, office, city and country tables
CREATE OR replace VIEW trf_department_office
  AS
SELECT tr.*, cty.city_name, ctry.country_name, dep.dep_name
  FROM trf tr INNER JOIN employee emp ON tr.emp_id = emp.id
  INNER JOIN office o ON emp.office_id = o.id
  INNER JOIN city cty ON cty.id = o.city_id
  INNER JOIN country ctry ON ctry.id = cty.country_id
  INNER JOIN department dep ON emp.dep_id = dep.id;

--this view joins employee, city and country tables
CREATE OR replace VIEW emp_office
  AS
SELECT ctry.country_name, emp.id, emp.login, cty.city_name
FROM employee emp INNER JOIN office o ON emp.office_id = o.id
  INNER JOIN city cty ON cty.id = o.city_id
  INNER JOIN country ctry ON ctry.id = cty.country_id;

--this view joins cities and countries
CREATE OR replace VIEW city_country
  AS
SELECT cty.city_name, ctry.country_name
FROM country ctry INNER JOIN city cty ON ctry.id = cty.country_id;

--this view joins trf, employee, office, trf_state, city and country tables
CREATE OR replace VIEW trf_state_office
  AS
  SELECT max(tst.change_date) maxdate, tr.*, cty.city_name, ctry.country_name, emp.id empid 
  FROM trf tr INNER JOIN employee emp ON tr.emp_id = emp.id
  INNER JOIN office o ON emp.office_id = o.id
  INNER JOIN city cty ON cty.id = o.city_id
  INNER JOIN country ctry ON ctry.id = cty.country_id
  INNER JOIN trfstate tst ON tst.trf_id = tr.id
GROUP BY tr.id, tr.emp_id, tr.begin_date, tr.end_date, tr.car_rental, tr.pay_by_cash, tr.cur_state,
  tr.customer_id, tr.project_manager, tr.destination_id, cty.city_name, ctry.country_name, emp.id;

--this view joins trf, employee, office, department, trf_state, city and country tables
CREATE OR replace VIEW trf_state_department
  AS
  SELECT max(tst.change_date) maxdate, tr.*, cty.city_name, ctry.country_name, emp.id empid, dep.dep_name
  FROM trf tr INNER JOIN employee emp ON tr.emp_id = emp.id
  INNER JOIN office o ON emp.office_id = o.id
  INNER JOIN city cty ON cty.id = o.city_id
  INNER JOIN country ctry ON ctry.id = cty.country_id
  INNER JOIN department dep ON dep.id = emp.dep_id
  INNER JOIN trfstate tst ON tst.trf_id = tr.id
GROUP BY tr.id, tr.emp_id, tr.begin_date, tr.end_date, tr.car_rental, tr.pay_by_cash, tr.cur_state,
  tr.customer_id, tr.destination_id, cty.city_name, tr.project_manager, ctry.country_name, emp.id, dep.dep_name;

--this view represents employees and their roles depending on departments
CREATE OR replace VIEW emp_role
  AS
SELECT deprole.*, emp.id empid, emp.login, emp.password
FROM employee emp INNER JOIN department dep ON emp.dep_id = dep.id
  INNER JOIN roledep ON dep.id = roledep.dep_id 
INNER JOIN deprole ON deprole.id = roledep.role_id;


--Author Sitner and Poluhovich
CREATE OR REPLACE VIEW EMPLOYEE_TRFS
AS
  SELECT trf.id,
    trf.emp_id,
    employee.login,
    trf.begin_date,
    trf.end_date,
    trf.cur_state,
    trfstate.commentary,
    city.city_name,
    country.country_name
  FROM trf
  INNER JOIN destination
  ON destination.id=trf.destination_id
  INNER JOIN city
  ON city.id=destination.city_id
  INNER JOIN country
  ON country.id=city.country_id
  INNER JOIN employee
  ON trf.emp_id=employee.id
  INNER JOIN trfstate
  ON trf.id          = trfstate.trf_id
  WHERE trfstate.id IN
    ( SELECT DISTINCT (MAX (ID)) FROM trfstate GROUP BY trf_id
);

--Author Sitner and Poluhovich
CREATE OR REPLACE VIEW TRFS_REPORT
AS
 SELECT tr.ID,
    emp.first_name,
    emp.second_name,
    tr.begin_date,
    tr.end_date,
    cty.city_name      AS office_city,
    ctry.country_name  AS office_country,
    cty2.city_name     AS dest_city,
    ctry2.country_name AS dest_country,
    dep.dep_name,
    tr.cur_state,
    commentary
  FROM trf tr
  INNER JOIN employee emp
  ON tr.emp_id = emp.id
  INNER JOIN destination de
  ON de.id = tr.destination_id
  INNER JOIN office o
  ON emp.office_id = o.id
  INNER JOIN city cty
  ON cty.id = o.city_id
  INNER JOIN country ctry
  ON ctry.id = cty.country_id
  INNER JOIN city cty2
  ON cty2.id = de.city_id
  INNER JOIN country ctry2
  ON ctry2.id = cty2.country_id
  INNER JOIN department dep
  ON emp.dep_id = dep.id
  INNER JOIN trfstate_lastdate tstate
  ON tstate.trf_id = tr.id; 


--Author Sitner and Poluhovich
 CREATE OR REPLACE VIEW TRF_STATE_DEPARTMENT
 AS
  SELECT MAX(tst.change_date) maxdate,
    tr."ID",
    tr."DESTINATION_ID",
    tr."CUSTOMER_ID",
    tr."EMP_ID",
    tr."BEGIN_DATE",
    tr."END_DATE",
    tr."CAR_RENTAL",
    tr."PAY_BY_CASH",
    tr."CUR_STATE",
    tr."PROJECT_MANAGER",
    cty.city_name,
    ctry.country_name,
    emp.id empid,
    dep.dep_name
  FROM trf tr
  INNER JOIN employee emp
  ON tr.emp_id = emp.id
  INNER JOIN office o
  ON emp.office_id = o.id
  INNER JOIN city cty
  ON cty.id = o.city_id
  INNER JOIN country ctry
  ON ctry.id = cty.country_id
  INNER JOIN department dep
  ON dep.id = emp.dep_id
  INNER JOIN trfstate tst
  ON tst.trf_id = tr.id
  GROUP BY tr.id,
    tr.emp_id,
    tr.begin_date,
    tr.end_date,
    tr.car_rental,
    tr.pay_by_cash,
    tr.cur_state,
    tr.customer_id,
    tr.destination_id,
    cty.city_name,
    tr.project_manager,
    ctry.country_name,
    emp.id,
    dep.dep_name;

--Author Allan
CREATE OR REPLACE VIEW trfstate_lastdate 
as
Select  trf_id, commentary
From trfstate tstate1
Where change_date=(select max(change_date) from trfstate tstate2 where tstate1.trf_id=tstate2.trf_id);
