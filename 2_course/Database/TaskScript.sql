USE hr;
set SQL_SAFE_UPDATES = 0;
set FOREIGN_KEY_CHECKS = 0;
/* 1 */
TRUNCATE TABLE locations;
INSERT INTO locations(street_address,postal_code,city,state_province,country_id)
SELECT DISTINCT street_address,postal_code,city,state_province,country_id
FROM report
WHERE city IS NOT NULL AND country_id IS NOT NULL;
/* 2 */
TRUNCATE TABLE departments;
INSERT INTO departments(department_name) 
SELECT DISTINCT department_name
FROM report
WHERE department_name IS NOT NULL;
/* 3 */
TRUNCATE TABLE employees;
INSERT INTO employees(first_name,last_name,email,phone_number,hire_date,job_id,salary,commission_pct)
SELECT DISTINCT first_name,last_name,'email',phone_number,hire_date,job_id,salary,commission_pct
FROM report;
    
set SQL_SAFE_UPDATES = 0;
set FOREIGN_KEY_CHECKS = 0;

/* 4 */
UPDATE employees AS e
SET  e.department_id = (SELECT DISTINCT d.department_id
                        FROM report AS r
	                    INNER JOIN departments AS d ON r.department_name = d.department_name
	                    WHERE r.first_name = e.first_name AND r.last_name = e.last_name),
     e.manager_id = (SELECT DISTINCT d.manager_id
	                 FROM report AS r
	                 INNER JOIN departments AS d ON r.department_name = d.department_name
					 WHERE r.first_name = e.first_name AND r.last_name = e.last_name);
                     
/* 5 */
UPDATE departments AS d
SET manager_id = (SELECT DISTINCT e.employee_id
                  FROM report AS r
                  INNER JOIN employees AS e ON (
coalesce(r.department_manager_first_name , r.manager_first_name) = e.first_name
AND coalesce( r.department_manager_last_name, r.manager_last_name) = e.last_name )
WHERE r.department_name = d.department_name);

/* 6 */
UPDATE employees AS e
SET e.salary = (e.salary + 100)
WHERE e.employee_id NOT IN (SELECT d.manager_id
							FROM departments AS d);
/* 7 */
UPDATE employees AS e
SET e.email = CONCAT(SUBSTRING(e.first_name,1,5), SUBSTRING(e.last_name,1,5));

/* 8 */
CREATE TABLE fordep(department_id INT NOT NULL PRIMARY KEY);

INSERT INTO fordep
SELECT d.department_id
FROM departments AS d
INNER JOIN employees AS e ON e.department_id = d.department_id
GROUP BY d.department_id
HAVING COUNT(e.employee_id) < 10;

UPDATE employees AS e 
SET e.salary = e.salary*1.1
WHERE e.department_id IN (SELECT department_id
						  FROM fordep);

DROP TABLE fordep;