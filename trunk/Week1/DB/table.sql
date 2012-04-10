
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