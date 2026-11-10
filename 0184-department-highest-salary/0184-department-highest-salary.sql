# Write your MySQL query statement below
select d.name AS department,
       e.name AS employee,
       e.salary AS salary
from employee e
JOIN department d ON e.departmentId = d.id
where e.salary =(
    select MAX(salary)
    from employee
    where departmentId = e.departmentId 
);