--------------------------------
-- working with ddl
--------------------------------

create table department(
	dept_id serial constraint pk_dept_id primary key,
	dept_name varchar(50),
	monthly_budget numeric(6,2) -- 9999.99
);

-- this is a comment
/*
 * we also have multiline comments
 */
drop table department;

alter table department
alter column monthly_budget type numeric(7,2);

--------------------------------
-- working with dml
--------------------------------
insert into department (dept_name, monthly_budget) values ('Human Resources', 9000);

select *
from department
order by dept_name desc;

select dept_name
from department
where dept_id=1;

create table employee(
	empl_id serial constraint pk_empl_id primary key,
	empl_name varchar(50),
	monthly_salary numeric(7,2),
	empl_position varchar(25),
	manager_id integer,
	dept_id integer references department(dept_id) 
)

alter table employee
add constraint fk_manager_employee
foreign key (manager_id) references employee(empl_id);

insert into department (dept_name, monthly_budget) values ('Marketing', 1325);
insert into department (dept_name, monthly_budget) values ('Information Technology', 1280);
insert into department (dept_name, monthly_budget) values ('Sales', 9200);



--------------------------------
-- Populating Employee Table
--------------------------------
insert into employee (empl_name, monthly_salary, empl_position, manager_id, dept_id) values ('Lola Larkin', 3823, 'HR Director', null, 1);
insert into employee (empl_name, monthly_salary, empl_position, manager_id, dept_id) values ('Patrick Bateman', 3823, 'Marketing Director', null, 2);
insert into employee (empl_name, monthly_salary, empl_position, manager_id, dept_id) values ('Aleta Gasgarth', 4052, 'HR Director', null, 1);
insert into employee (empl_name, monthly_salary, empl_position, manager_id, dept_id) values ('Ulrike Hector', 3823, 'HR Assist Dir', 3, 1);
insert into employee (empl_name, monthly_salary, empl_position, manager_id, dept_id) values ('Charis Fike', 3930, 'MKT Rep', 2, 2);
insert into employee (empl_name, monthly_salary, empl_position, manager_id, dept_id) values ('Jemie Duffin', 3484, 'MKT Rep', 2, 2);
insert into employee (empl_name, monthly_salary, empl_position, manager_id, dept_id) values ('Mildrid Legerwood',  3863, 'IT Manager', null, 3);
insert into employee (empl_name, monthly_salary, empl_position, manager_id, dept_id) values ('Huey Fasson',  2869, 'IT Assist Manager', 7, 3);
insert into employee (empl_name, monthly_salary, empl_position, manager_id, dept_id) values ('Baxie Dalgleish',  3523, 'IT Rep', 8, 3);
insert into employee (empl_name, monthly_salary, empl_position, manager_id, dept_id) values ('Giacomo Ren', 2863, 'IT Rep', 8, 3);
insert into employee (empl_name, monthly_salary, empl_position, manager_id, dept_id) values ('Billi Bisset',  2430, 'Sales Director', null, 4);
insert into employee (empl_name, monthly_salary, empl_position, manager_id, dept_id) values ('Kirby Burgoine',  2687, 'Sales Rep', 11, 4);
insert into employee (empl_name, monthly_salary, empl_position, manager_id, dept_id) values ('Huey Cathee', 3434, 'HR Rep', 4, 1);
insert into employee (empl_name, monthly_salary, empl_position, manager_id, dept_id) values ('Lenora Craister', 4224, 'HR Rep', 4, 1);


select * 
from employee;

select *
from employee
where dept_id = 3;

select empl_name
from employee
where empl_position = 'HR Rep';

select *
from employee 
order by monthly_salary desc;

select empl_name, dept_id 
from employee
where dept_id=4 or dept_id=3;

select *
from department
where monthly_budget < 3000;

update department 
set monthly_budget = 10038
where dept_id = 2;

update department
set monthly_budget = 12305
where dept_id=3;

delete from department 
where dept_id=3; -- we can't do this because it violates referential integrity 

select empl_name 
from employee 
where empl_name like 'L%';

select empl_name 
from employee 
where empl_name like 'Huey%';

truncate table employee;
