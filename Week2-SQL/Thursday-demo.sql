/*
 * functions
 * 
 * create (or replace) function [function name]([params])
 * returns [type]
 * language [lang]
 * as $function$
 * declare
 * 	-- additional variables, if any 
 * begin 
 * 	-- function code
 * end
 * $function$
 * 
 */

create or replace function return_num()
returns integer
language plpgsql
as $function$
begin 
	return 5;
end
$function$

select return_num();

drop function returnNum;

select * 
from employee 
where empl_id = return_num();


create or replace function add(num1 integer, num2 integer)
returns integer 
language plpgsql
as $$
declare 
	sum_result integer;
begin 
	sum_result = num1 + num2;
	return sum_result;
end
$$

select add(92,4);

create or replace function add_dept(dept_name_input department.dept_name%type, budget_input department.monthly_budget%type)
returns void
language plpgsql
as $$
begin 
	insert into department (dept_name, monthly_budget) values (dept_name_input, budget_input);
end
$$

select add_dept('new department',800);

drop function add_dept;

create or replace function add_dept(dept_name_input department.dept_name%type, budget_input department.monthly_budget%type)
returns setof department
language plpgsql
as $$
declare
	new_id integer;
begin
	insert into department (dept_name, monthly_budget) values (dept_name_input, budget_input);

	select last_value into new_id
	from department_dept_id_seq;

	return query select * from department where dept_id = new_id;
end
$$

select add_dept('another new department',900);

create or replace function find_avg_sal()
returns numeric(7,2)
language plpgsql
as $$
declare
	avg_sal numeric(7,2);
begin
	select round(avg(monthly_salary),2) into avg_sal
	from employee;
	return avg_sal;
end
$$

select find_avg_sal();

select *
from employee 
where monthly_salary > find_avg_sal();




