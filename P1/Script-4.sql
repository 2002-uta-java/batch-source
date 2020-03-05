create table app_user(
	user_id serial not null primary key,
	user_manager boolean,
	user_email varchar(100) not null unique,
	user_pass varchar(50) not null,
	user_firstname varchar(50),
	user_lastname varchar(50)
);

--drop table app_user cascade;
--truncate table app_user;


insert into app_user (user_id, user_manager, user_email, user_pass, user_firstname, user_lastname) values (1, false, 'mclynman0@smugmug.com', '6Nk9lo', 'Mortimer', 'Clynman');
insert into app_user (user_id, user_manager, user_email, user_pass, user_firstname, user_lastname) values (2, true, 'dgalloway1@discovery.com', 'gKdsD9c0n', 'Deanne', 'Galloway');
insert into app_user (user_id, user_manager, user_email, user_pass, user_firstname, user_lastname) values (3, true, 'rtivnan2@amazon.co.jp', 'te4fhDzs', 'Randa', 'Tivnan');
insert into app_user (user_id, user_manager, user_email, user_pass, user_firstname, user_lastname) values (4, false, 'asewards3@boston.com', 'cpDNqcX', 'Arthur', 'Sewards');
insert into app_user (user_id, user_manager, user_email, user_pass, user_firstname, user_lastname) values (5, false, 'dchrispin4@delicious.com', 'HxpsjJ816x', 'Dorothea', 'Chrispin');
insert into app_user (user_id, user_manager, user_email, user_pass, user_firstname, user_lastname) values (6, false, 'lmcgucken5@dmoz.org', 'jgYToow', 'Lorrie', 'McGucken');
insert into app_user (user_id, user_manager, user_email, user_pass, user_firstname, user_lastname) values (7, true, 'gjovasevic6@goodreads.com', 'X88Js8', 'Genvieve', 'Jovasevic');
insert into app_user (user_id, user_manager, user_email, user_pass, user_firstname, user_lastname) values (8, true, 'adanit7@addthis.com', 'HbwWKg6SU', 'Auberta', 'Danit');
insert into app_user (user_id, user_manager, user_email, user_pass, user_firstname, user_lastname) values (9, true, 'abaldassi8@mit.edu', 'fJzkeWX5ZM6', 'Abeu', 'Baldassi');
insert into app_user (user_id, user_manager, user_email, user_pass, user_firstname, user_lastname) values (10, true, 'hgherardesci9@wp.com', 'ZA8UaMA', 'Haskel', 'Gherardesci');

insert into app_user (user_id, user_manager, user_email, user_pass, user_firstname, user_lastname) values (11, true, 'manager@gmail.com', 'password', 'Mana', 'Ger');

-- for easy login testing on login page
insert into app_user (user_id, user_manager, user_email, user_pass, user_firstname, user_lastname) values (12, false, 'employee@gmail.com', 'password', 'Emplo', 'Yee');

delete from app_user where user_id = 11;



-- creating reimbursement request table --

create table request(
	req_id serial not null primary key,
	user_email varchar(50) not null references app_user(user_email),
	req_amount varchar(64) not null,
	req_reason varchar(500),
	req_status int check (req_status > 0 and req_status < 4) default 1,
	req_manager varchar(50)
);

--alter table request alter column req_status set default 1;

--drop table request;
--truncate table request;

--testing inserts
insert into request (req_id, user_email, req_amount, req_reason, req_status) values (8, 'rtivnan2@amazon.co.jp', '1200', 'somereason', 3);
insert into request (user_email, req_amount, req_reason) values ('manager@gmail.com', '250', 'relocation');

insert into request (user_email, req_amount, req_reason) values ('employee@gmail.com', '500', 'business purchase');

insert into request (user_email, req_amount, req_reason) values ('adanit7@addthis.com', '500', 'moving out of state costs');

insert into request (user_email, req_amount, req_reason) values ('employee@gmail.com', '250', 'another reason');



