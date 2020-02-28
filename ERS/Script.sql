create table employee (
	username varchar constraint pk_username primary key,
	first_name varchar not null,
	last_name varchar not null,
	pwd varchar not null,
	isManager numeric default 0
);


create table reimbursement (
	id serial constraint pk_id primary key,
	username varchar references employee(username),
	status varchar default 'Pending',
	amount numeric(20,2) not null,
	description varchar
);

