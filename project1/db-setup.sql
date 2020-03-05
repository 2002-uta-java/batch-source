--drop table if exists transaction;
--drop table if exists ownership;
--drop table if exists account;
drop table if exists reimbursements;
drop table if exists manager;
drop table if exists person;


create table person (
	id serial primary key,
	username text unique,
	password text
);

--create extension pgcrypto;

insert into person (id, username, password) values 
	(1, 'swanson', crypt('meat', gen_salt('bf'))),
	(2, 'leslie', crypt('waffles', gen_salt('bf'))),
	(3, 'april', crypt('nothing', gen_salt('bf'))),
	(4, 'tom', crypt('snakejuice', gen_salt('bf')))
;

SELECT pg_catalog.setval('public.person_id_seq', 5, false);

create table manager (
	id serial primary key,
	person_id int references person(id)
);
insert into manager (id, person_id) values
	(7, 1),
	(12, 2);
select pg_catalog.setval('public.manager_id_seq', 20, false);

create table reimbursements (
	id serial primary key,
	description text,
	status text,
	amount numeric,
	user_id int references person(id),
	manager_id int references manager(id)
);
insert into reimbursements (id, description, status, amount, user_id, manager_id) values
	(1, 'New suit', 'pending', 362.85, 4, null),
	(2, 'Hot tub limo', 'denied', 1726.54, 4, 12),
	(3, 'Mouse Rat CD', 'approved', 20, 3, 7);

select pg_catalog.setval('public.reimbursements_id_seq', 20, false);

select r.id, description, status, amount, p.username from reimbursements r left join person p on r.user_id = p.id;

--select m.id as id from manager m left join person p on m.person_id = p.id where p.id = 2;
