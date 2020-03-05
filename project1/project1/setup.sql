drop table if exists employee cascade;
create table employee (
	eid serial constraint pk_employee_eid primary key,
	login varchar unique not null,
	pw varchar not null,
	title varchar default 'employee',
	first_name varchar default '',
	last_name varchar default '',
	email varchar default ''
);

insert into employee(login, pw, title, first_name, last_name, email) values('login', 'pw', 'manager', 'santa', 'clause', 'email');
insert into employee(login, pw, first_name, last_name, email) values('lolo', 'pw', 'jimmy', 'johns', 'jj@email.com');
insert into employee(login, pw, first_name, last_name, email) values('jack', 'bequick', 'jack', 'black', 'jb@gmail.com');

drop table if exists reimbursement cascade;
create table reimbursement (
	rid serial constraint pk_reimbursement_id primary key,
	eid integer references employee(eid),
	status varchar default 'pending',
	expense numeric(15,2) default 0,
	resolver integer references employee(eid),
	rtype varchar default 'general'
);

insert into reimbursement(eid, status, expense, resolver, rtype) values(2, 'approved', 18, 1, 'cookies');
insert into reimbursement(eid, expense, rtype) values(2, 300, 'pizza');
insert into reimbursement(eid, expense) values(2, 32);
insert into reimbursement(eid, expense) values(3, 60);
insert into reimbursement(eid, status, expense, resolver, rtype) values(2, 'approved', 12, 1, 'bacon');
insert into reimbursement(eid, status, expense, resolver, rtype) values(3, 'denied', 1, 1, 'iced tea');
