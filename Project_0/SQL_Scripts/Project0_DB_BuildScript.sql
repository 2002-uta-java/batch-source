/************************************
 * Project0 Database - Version 0.01
 * Script Project0_DB_BuildScript.sql
 * Description: Creates a simple banking database
 * DB Server: Local PostGreSQL
 * Author: Christian Randle
************************************/

--NEED TO ADD UNIQUE CONSTRAINTS
drop function if exists accounts_by_userid("users".userid %type);
drop table if exists transactions;
drop table if exists "authorization";
drop table if exists Accounts;
drop table if exists users;


create table Users
(
	UserID bigserial,
	UserEmail VARCHAR (180) unique not null,
	UserName VARCHAR(180) not null,
	Password VARCHAR(180) not null,
	constraint PK_Users primary key (UserID)
);

create table Accounts
(
	AccountID bigserial unique not null,
	Balance bigint not null,
	"type" varchar(180) not null,
	constraint PK_Accounts primary key (AccountID) 
);

create table "authorization"
(
	UserID bigserial  REFERENCES Users (UserID) on delete cascade,
	AccountID bigserial REFERENCES Accounts (AccountID) on delete cascade,
	TransactionID bigserial unique,
	constraint Pk_Authorization primary key (TransactionID)
);


create table Transactions(
	TransactionID bigserial references "authorization" (TransactionID) on delete cascade,
	Date timestamp not null,
	Actions varChar(180),
	tranasctionAmount double precision,
	constraint Pk_Transactions primary key (TransactionID,Date) 
);



-- create user
create or replace function create_user(un users.username %type, ue users.useremail %type, pw users."password" %type ) returns bigint  as $$
	insert into users (username, useremail ,"password" )
	values (un,ue,pw)
	returning userid
$$ language sql;

create or replace function add_account(i users.userid %type, bl accounts.balance  %type, ty accounts."type" %type) returns void  as $$
	with acd1 as (
		insert into accounts (balance, "type")
		values (bl,ty) 
		returning accountid
	)
	insert into "authorization" (userid,accountid) values (i,(select * from acd1))
$$ language sql;

 -- returns the accounst matching the given userid  
create or replace function accounts_by_userid(id users.userid %type) returns setof accounts as $$
	select * 
	from accounts  a3 
	where accountid  in
		(
			select accountid 
			from users  u1
			join "authorization"  au2 
			on (u1.userid  = au2.userid and u1.userid = id)
		)  
$$ language sql;
--indexes?