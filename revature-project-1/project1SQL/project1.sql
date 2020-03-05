-- Project-1-sql----------
-- CREATE-------------------------------------------------------------------------------------

-- create table for employee
create table employee(
	emp_id serial constraint pk_employee_id primary key,
	emp_position varchar(50) not null,
	report_to integer,
	emp_name varchar(50) not null,
	age integer,
	emp_user_name varchar(50) not null unique,
	email varchar(50) not null unique,
	passwd varchar(50)not null
);

-- create reimbursement table
create table reimbursement(
	reimb_id serial constraint pk_reimb_id primary key,
	emp_id integer,
	amount float not null,
	purpose varchar(100),
	approve_by integer default '0',
	reimb_status varchar(10) default 'Pending',
	foreign key (emp_id) references employee(emp_id)
);

-- drop table ------------------------------------------------------------------
drop table employee;
drop table reimbursement;
drop function add_reimbursement;

---- Insert ------------------------------------------------------------------
-- insert with function
-- function to insert new reimbursement
create or replace function add_reimbursement(emp_id_input reimbursement.emp_id%type,amount_input reimbursement.amount%type, purpose_input reimbursement.purpose%type)
returns setof reimbursement 
language plpgsql
as $$
declare 	
	reimbursement_id integer;
begin 
	insert into reimbursement (emp_id, amount, purpose) values (emp_id_input, amount_input, purpose_input);

	select last_value into reimbursement_id
	from reimbursement_reimb_id_seq;

	return query select * from reimbursement where reimb_id = reimbursement_id;
end
$$


-- insert into employeee -----------------------------------------------------
-- insert manager --
insert into employee(emp_position, emp_name, emp_user_name, email, passwd)
	values('Manager', 'Carolyn', 'carolyn1', 'carolyn1@revature.com', 'carolyn1pass');

-- insert employee --
insert into employee(emp_position, report_to, emp_name, emp_user_name, email, passwd)
	values('Employee', '1', 'Hoang', 'hoang1', 'hoang1@revature.com', 'hoang1pass');

insert into employee(emp_position, report_to, emp_name, emp_user_name, email, passwd)
	values('Employee', '1', 'Josue', 'josue1', 'josue1@revature.com', 'josue1pass');

insert into employee(emp_position, report_to, emp_name, emp_user_name, email, passwd)
	values('Employee', '1', 'Jack', 'jack1', 'jack1@revature.com', 'jack1pass');



-- insert into reimbursement -------------------------------------------------
insert into reimbursement(emp_id, amount, purpose)
	values('2', '500', 'Relocation');

-- insert into reimbursement with function
select add_reimbursement('3', '250', 'Travel');


-- update-------------------------------------------------------------------------

update employee
set age = '27'
where emp_id = '1';

update employee
set age = '23'
where emp_id = '3';

update employee
set age = '24'
where emp_id = '4';

-- update reimb_status---------------------------------------------------------
update reimbursement 
set approve_by = '1', reimb_status = 'Approve'
where reimb_id = '1';

delete from reimbursement 
where reimb_id = 4;

----select----------------------------------------------------------------------

select * from employee;
select * from reimbursement;





