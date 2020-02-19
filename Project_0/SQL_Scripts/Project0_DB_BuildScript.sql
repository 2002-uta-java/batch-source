/************************************
 * Project0 Database - Version 0.01
 * Script Project0_DB_BuildScript.sql
 * Description: Creates a simple banking database
 * DB Server: Local PostGreSQL
 * Author: Christian Randle
************************************/

--NEED TO ADD UNIQUE CONSTRAINTS
drop function if exists delete_account(ai accounts.accountid %type);
drop function if exists delete_user(ui users.userid %type);
drop function if exists accounts_by_userid(users.userid %type);
drop table if exists transactions;
drop table if exists Authorizations;
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
	Balance money not null,
	"type" varchar(180) not null,
	constraint PK_Accounts primary key (AccountID) 
);


create table Authorizations
(
	UserID bigserial  REFERENCES Users (UserID) on delete cascade,
	AccountID bigserial REFERENCES Accounts (AccountID) on delete cascade,
	TransactionID bigserial unique,
	constraint Pk_Authorization primary key (TransactionID)
);


create table Transactions(
	TransactionID bigserial references Authorizations (TransactionID) on delete cascade,
	Date timestamp not null,
	Actions varChar(180),
	tranasctionAmount money,
	constraint Pk_Transactions primary key (TransactionID,Date) 
);



-- create user
create or replace function create_user(un users.username %type, ue users.useremail %type, pw users."password" %type ) returns bigint  as $$
	insert into users (username, useremail ,"password" )
	values (un,ue,pw)
	returning userid
$$ language sql;

create or replace function add_account(i users.userid %type, bl accounts.balance  %type, ty accounts."type" %type) returns setof accounts.accountid %type  as $$
	with acd1 as (
		insert into accounts (balance, "type")
		values (bl,ty) 
		returning accountid
	)
	insert into Authorizations (userid,accountid) values (i,(select * from acd1)) returning accountid 
$$ language sql;

 -- returns the accounst matching the given userid  
create or replace function accounts_by_userid(id users.userid %type) returns setof accounts as $$
	select * 
	from accounts  a3 
	where accountid  in
		(
			select accountid from users  u1
			join Authorizations  au2 
			on (u1.userid  = au2.userid and u1.userid = id)
		)  
$$ language sql;

create or replace function delete_accounts(ui users.userid %type) returns void as $$
	delete from accounts where accountid  in (
		select accountid from authorizations au1
		inner join users u1
		on (u1.userid = au1.userid and u1.userid =ui)
	)
$$ language sql;

create or replace function delete_user(ui users.userid %type) returns void as $$
	select delete_accounts(ui);
	delete from users where userid = ui;
$$ language sql;;

