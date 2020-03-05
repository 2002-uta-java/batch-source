create table reimburse.account (
	id serial constraint pk_acct_id primary key,
	date_created timestamp not null,
	name varchar not null,
	email varchar unique not null,
	password varchar not null,
	type varchar not null,
	manager_id int references reimburse.account(id)
);

create table reimburse.request (
	id serial constraint pk_req_id primary key,
	date_submitted timestamp not null,
	date_reviewed timestamp,
	status varchar not null default 'pending',
	empl_id int references reimburse.account(id),
	reimbursement_date timestamp not null,
	category varchar not null,
	amount numeric not null,
	description varchar,
	image_url varchar
);

insert into reimburse.account 
	(name, email, password, type, date_created) 
	values ('test_manager', 'manager@managing.com', 'password', 'MANAGER', now());
	
insert into reimburse.account 
	(name, email, password, manager_id, type, date_created) 
	values ('test_emp', 'employee@working.com', 'password', 1, 'EMPLOYEE', now());
	
GRANT USAGE ON SCHEMA reimburse TO app_user;

select * from reimburse.account a join reimburse.request r on a.id = r.empl_id;