CREATE OR replace VIEW department_selfjoin
  AS
SELECT sub.id, sub.dep_name, sub.parent_id, sub.manager_id, sup.dep_name supname
FROM department sub, department sup
  WHERE sub.parent_id = sup.id;

CREATE OR replace VIEW department_employee
  AS
SELECT emp.first_name, emp.second_name, dep.id, dep.parent_id, dep.dep_name
  FROM employee emp INNER JOIN department dep ON emp.dep_id = dep.id;

CREATE OR replace VIEW trf_office
  AS
  SELECT tr.*, cty.city_name, ctry.country_name, emp.id empid
  FROM trf tr INNER JOIN employee emp ON tr.emp_id = emp.id
  INNER JOIN office o ON emp.office_id = o.id
  INNER JOIN city cty ON cty.id = o.city_id
  INNER JOIN country ctry ON ctry.id = cty.country_id;

CREATE OR replace VIEW trf_department
  AS
  SELECT tr.*, dep.dep_name
FROM trf tr INNER JOIN employee emp ON tr.emp_id = emp.id
  INNER JOIN department dep ON emp.dep_id = dep.id;

CREATE OR replace VIEW trf_department_office
  AS
SELECT tr.*, cty.city_name, ctry.country_name, dep.dep_name
  FROM trf tr INNER JOIN employee emp ON tr.emp_id = emp.id
  INNER JOIN office o ON emp.office_id = o.id
  INNER JOIN city cty ON cty.id = o.city_id
  INNER JOIN country ctry ON ctry.id = cty.country_id
  INNER JOIN department dep ON emp.dep_id = dep.id;

CREATE OR replace VIEW emp_office
  AS
SELECT ctry.country_name, emp.id
FROM employee emp INNER JOIN office o ON emp.office_id = o.id
  INNER JOIN city cty ON cty.id = o.city_id
  INNER JOIN country ctry ON ctry.id = cty.country_id;