create table employee (
	username varchar constraint pk_username primary key,
	email varchar not null constraint employee_email_key unique,
	first_name varchar not null,
	last_name varchar not null,
	pwd varchar not null
);

create table manager (
	username varchar constraint pk_manager_username primary key,
	email varchar not null constraint manager_email_key unique,
	first_name varchar not null,
	last_name varchar not null,
	pwd varchar not null
);

create table reimbursement (
	id numeric serial constraint pk_id primary key,
	manager_username varchar references manager(username),
	employee_username varchar references employee(username),
	status varchar default 'Pending',
	amount numeric(20,2) not null,
	description varchar
);

