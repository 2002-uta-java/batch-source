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


create function apply_tax(pre_tax employee.monthly_salary%type)
returns numeric(7,2)
language plpgsql
as $$
begin 
	return (.80)*pre_tax;
end;
$$


select 
	empl_name "Employee", 
	monthly_salary "Pre tax income", 
	apply_tax(monthly_salary) "Post tax income"
from employee;

create function apply_tax_2(pre_tax employee.monthly_salary%type)
returns numeric(7,2)
language plpgsql
as $$
declare
	post_tax numeric(7,2);
begin
	if pre_tax>3800 then 
		post_tax = (.75)*pre_tax;
	elseif pre_tax>3000 then 
		post_tax = (.80)*pre_tax;
	else 
		post_tax = (.82)*pre_tax;
	end if;
	return post_tax;
end
$$

select 
	empl_name "Employee", 
	monthly_salary "Pre tax income", 
	apply_tax(monthly_salary) "Post tax income"
from employee;

select 
	empl_name "Employee", 
	monthly_salary "Pre tax income", 
	apply_tax_2(monthly_salary) "Post tax (2) income"
from employee;


select * from department_spending;

/*
 * update department
 * set monthly_budget = monthly_budget + [increase]
 * where dept_id = [id]
 * 
 */

create function increase_budget(increase_amount department.monthly_budget%type, dept_id_input department.dept_id%type)
returns void
language plpgsql
as $$
begin 
	update department 
	set monthly_budget = monthly_budget + increase_amount
	where dept_id = dept_id_input;
end;
$$


select increase_budget(3000,2);
select increase_budget(3800,3);

/*
 * We'll create a function which attempts to give an employee a raise.
 * If the employee's department budget allows, the raise will be applied 
 *   to their monthly salary.
 * Otherwise, the employee's salary will remain the same.
 */


create or replace function assess_raise(empl_id_input employee.empl_id%type, raise_amount employee.monthly_salary%type)
return numeric
language plpgsql
as $$
declare 
	empl_dept_id employee.dept_id%type;
	dept_budget department.monthly_budget%type;
	budget_used department.monthly_budget%type;
	current_salary employee.monthly_salary%type;
begin 
	-- get the department we're working with
	select dept_id into empl_dept_id
	from employee
	where empl_id = empl_id_input;
	
	-- get the department budget based on the department
	select monthly_budget into dept_budget
	from department
	where dept_id = empl_dept_id;
	
	-- get the amount of the budget that's been used in that department (everyone's salary)
	select sum(monthly_salary) into budget_used
	from employee
	where dept_id = empl_dept_id;
	
	-- get the current salary of employee 
	select monthly_salary into current_salary
	from employee
	where empl_id = empl_id_input;

	-- compare budget with department spending + raise amount to determine raise outcome
	if (budget_used+raise_amount)>dept_budget then
		return current_salary;
	else
		update employee
		set monthly_salary = monthly_salary + raise_amount
		where empl_id = empl_id_input;
		return current_salary+raise_amount;
	end if;
end;
$$
/

