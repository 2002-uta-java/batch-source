select count(empl_id) as "Total Employees"
from employee;

select count(empl_id) as "Number of Employees", dept_id as "Department"
from employee
group by dept_id;

-- aggregate avg method finds the aggregation of the data set, round finds the rounded value of that singular result
select round(avg(monthly_salary),2) avg_sal, dept_id dept
from employee
group by dept_id
order by avg_sal desc;

-- using a subquery to find the highest paid employee
select *
from employee
where monthly_salary =
	(select max(monthly_salary)
	from employee);

------------------------------
-- set operations
------------------------------
select *
from employee 
where empl_id < 8
union 
select * 
from employee 
where dept_id = 1;

select *
from employee 
where empl_id < 8
union 
select * 
from employee 
where dept_id = 1;

select *
from employee 
where empl_id < 8
intersect 
select * 
from employee 
where dept_id = 1;

select *
from employee 
where empl_id < 8
except 
select * 
from employee 
where dept_id = 1;

/*
 *  select [columns]
 *  from [left table]
 *  (full/left/right) join [right table]
 *  on [join condition]
 */

insert into department (dept_name, monthly_budget) values ('Accounting', 6800);
insert into employee (empl_name, monthly_salary, empl_position) values ('Billy Bob', 3000, 'CEO');

-- inner join using (explicit) column alias
select employee.empl_name as name, department.dept_name as department 
from employee 
join department
on employee.dept_id = department.dept_id;

-- inner join using column and table alias
select e.empl_name "name", d.dept_name "department"
from employee e
join department d 
on e.dept_id = d.dept_id;

-- outer join
select * 
from employee e 
full outer join department d
on e.dept_id = d.dept_id;

-- left 
select e.empl_name, d.dept_name 
from employee e 
left outer join department d
on e.dept_id = d.dept_id;

-- right 
select e.empl_name, d.dept_name 
from employee e 
right outer join department d
on e.dept_id = d.dept_id;

-- self join (inner join on the same table)
select e.empl_name "Employee", m.empl_name "Manager"
from employee e 
join employee m 
on e.manager_id = m.empl_id;

select e.empl_name "Employee", m.empl_name "Manager"
from employee e 
join employee m 
on e.manager_id = m.empl_id
where e.dept_id = 2;

-- return department which contains the lowest paid employee
select d.*
from department d 
join employee e
on d.dept_id = e.dept_id 
where e.monthly_salary = 
	(select min(monthly_salary)
	from employee);

-- average employee salary in each department, displayed with that department name
select round(avg(e.monthly_salary),2) "average salary", d.dept_name "department"
from department d
join employee e
on e.dept_id = d.dept_id 
group by d.dept_name;

select d.dept_name "department", d.monthly_budget "budget", sum(e.monthly_salary) "total spending"
from department d 
join employee e 
on e.dept_id = d.dept_id 
group by d.dept_name, d.monthly_budget;

/*
 * Views
 * - saved queries 
 * - result set from a view is not physically stored in the database
 * - instead, the criteria to access that information is
 * 
 * create view [view name] as [query]
 * 
 */

create view department_spending as 
select d.dept_name "department", d.monthly_budget "budget", sum(e.monthly_salary) "total spending"
from department d 
join employee e 
on e.dept_id = d.dept_id 
group by d.dept_name, d.monthly_budget;

select * from department_spending;


/*
 * Materialized Views
 * - a materialized view, unlike a normal view, actually stores the results in memory
 * - the results are not going to automatically be updated when the information the 
 *    materialized view is based on is updated
 * - the materialized view must be refreshed to see those changes
 * 
 */ 

create materialized view department_spending_2 as 
select d.dept_name "department", d.monthly_budget "budget", sum(e.monthly_salary) "total spending"
from department d 
join employee e 
on e.dept_id = d.dept_id 
group by d.dept_name, d.monthly_budget;

select * from department_spending;

/* basically the equivalent of:
    select * from (
		select d.dept_name "department", d.monthly_budget "budget", sum(e.monthly_salary) "total spending"
		from department d 
		join employee e 
		on e.dept_id = d.dept_id 
		group by d.dept_name, d.monthly_budget	
	) as "department_spending"
 */
select * from department_spending_2;

update department 
set monthly_budget = 22000
where dept_id = 1;

update employee 
set monthly_salary = 3000
where empl_id = 10;

refresh materialized view department_spending_2;


