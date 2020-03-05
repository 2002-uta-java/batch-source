/************************************
 * Project 1 Database - Version 0.01
 * Script Project_1_DB_BuildScript.sql
 * Description: Creates a simple reimbursement database
 * DB Server: Local PostGreSQL
 * Author: Christian Randle
************************************/
DROP SCHEMA public CASCADE;
CREATE SCHEMA public;
--drop table if exists Requests;
--drop table if exists Accounts;
--drop table if exists Users;


create table Users
(
	UserID bigserial unique not null, 
	firstName varchar(180) not null,
	lastName varchar(180) not null,
	userName varchar(180) unique not null,
	email varchar (180) unique not null,
	"role" varchar (180) not null default 'Employee',
	"password" varchar(180) not null,
	constraint Users_PK primary key (UserID,userName, "password"),
	supervisor bigint default 0
);

create table Accounts
(
	AccountID bigSerial unique not null,
	UserID bigserial references Users (UserID) on delete cascade,
	Balance numeric constraint account_postive check (balance>0.0),
	constraint Accounts_PK primary key (AccountID)
);


create table Reimbursements
(
	requestID bigserial unique not null,
	sourceID bigserial references Users (UserID) on delete cascade,
	superID bigserial references Users (UserID) on delete cascade,
	status varchar(180),
	amount numeric,
	constraint PK_Reimbursements primary key (requestID)
);

create or replace function user_by_id(uid users.userid %type) returns setof Users as $$
	select * from users u2 where u2.userid = uid
$$ language sql;

--create or replace function get_subordinates(uid users.userid %type) returns setof Users as $$
--	select * from users u2 where u2.supervisor = uid
--$$ language sql;


create or replace function 
create_user(
	fn users.firstName %type,ln users.lastName %type,
	ue users.email %type, un users.username %type, pw users."password" %type
) returns bigint  as $$
	insert into users (firstName ,lastName, email,username ,"password" )
	values (fn,ln,un,ue,pw)
	returning userid
$$ language sql;


create or replace function delete_user(ui users.userid %type) returns void as $$
	delete from accounts a2 where a2.userid=ui;
	delete from users where userid = ui;
$$ language sql;


/* -----------------------------------------------------
*			Set Up test data
-----------------------------------------------------*/ 


select create_user('Bobby', 'brown','beebee','bb@google.com', 'yuppers');
select create_user('Billy', 'brown','tubular','radical@google.com', 'yuppers');
select create_user('Ted', 'brown','radical','tubular@google.com', 'yuppers');

update users u 
set supervisor = 1
where u.username !='beebee';
	select * from users order by userid;
--select get_subordinates(1);
--select delete_user(2);
--select get_subordinates(1);
--select * from users;


update users u
	SET 
		firstName = 'test',
		lastName = 'case',
		email = 'test@tn.edu', 
		"role" = 'Manager', 
		"password"= 'yuppers', 
		supervisor = 0
where (u.username = 'beebee' and u."password" = 'yuppers');
	select * from users order by userid ;
