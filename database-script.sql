CREATE DATABASE employee;

USE employee;

CREATE TABLE department (
    department_id INT PRIMARY KEY,
    department_name VARCHAR(100)
);


CREATE TABLE employee (
    employee_id INT PRIMARY KEY,
    employee_name VARCHAR(100),
    salary DECIMAL(10, 2),
    department_id INT,
    FOREIGN KEY (department_id) REFERENCES department(department_id)
);

INSERT INTO department (department_id, department_name) VALUES
(1, 'Human Resources'),
(2, 'Finance'),
(3, 'Engineering'),
(4, 'Marketing');

INSERT INTO employee (employee_id, employee_name, salary, department_id) VALUES
(101, 'Abhishek', 60000.00, 1),
(102, 'Divyata', 75000.00, 2),
(103, 'Dev', 80000.00, 3),
(104, 'Prince', 85000.00, 3),
(105, 'Ragini', 50000.00, 4),
(106, 'Frank', 70000.00, 2);


SELECT 
    e.employee_id, 
    e.employee_name, 
    d.department_name
FROM 
    employee e
JOIN 
    department d 
ON 
    e.department_id = d.department_id;
    
    
SELECT 
    employee_id, 
    employee_name, 
    salary
FROM 
    employee
ORDER BY 
    salary DESC;
    
    
SELECT 
    d.department_name, 
    AVG(e.salary) AS average_salary
FROM 
    employee e
JOIN 
    department d 
ON 
    e.department_id = d.department_id
GROUP BY 
    d.department_name;