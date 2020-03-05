-- create tables
create table employee
(
	id serial,
	email VARCHAR unique,
	job_position VARCHAR,
	first_name VARCHAR,
	last_name VARCHAR,
	gender VARCHAR,
	user_password VARCHAR,
	primary key (id)
);

create table reimbursement
(
	id serial,
	purpose VARCHAR,
	amount numeric(6, 2), -- max of 9999.99
	id_employee serial references employee (id),
	id_manager serial references employee (id),
	status VARCHAR,
	primary key (id)
);

-- add dummy manager, should be impossible to log in.
insert into employee (id, email, job_position, first_name, last_name, gender, user_password)
values (1, null, 'manager', null, null, null, null);

-- add sample employees
insert into employee (id, email, job_position, first_name, last_name, gender, user_password)
values (2, 'sam@gmail.com', 'manager', 'Sam', 'Adams', 'male', 'password');
insert into employee (id, email, job_position, first_name, last_name, gender, user_password)
values (3, 'sarah@gmail.com', 'employee', 'Sarah', 'Connor', 'female', 'password');
insert into employee (id, email, job_position, first_name, last_name, gender, user_password)
values (4, 'adam@yahoo.com', 'employee', 'Adam', 'Pear', 'male', 'password');

-- add sample reimbursements
insert into reimbursement (id, purpose, amount, id_employee, id_manager, status)
values (1, 'Travel', 249.99, 3, 1, 'pending');
insert into reimbursement (id, purpose, amount, id_employee, id_manager, status)
values (2, 'Travel', 50.00, 3, 2, 'rejected');
insert into reimbursement (id, purpose, amount, id_employee, id_manager, status)
values (3, 'Travel', 500.00, 3, 1, 'pending');
insert into reimbursement (id, purpose, amount, id_employee, id_manager, status)
values (4, 'Travel', 99.99, 4, 2, 'approved');
insert into reimbursement (id, purpose, amount, id_employee, id_manager, status)
values (5, 'Travel', 199.99, 4, 1, 'pending');