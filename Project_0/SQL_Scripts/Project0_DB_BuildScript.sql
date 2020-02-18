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
drop sequence if exists user_id_seq;
drop sequence if exists accounts_id_seq;
drop sequence if exists transactions_id_seq;



create table Users
(
	UserID bigserial,
	UserEmail VARCHAR (180),
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

create table Transactions(
	TransactionID bigserial unique not null,
	Date timestamp not null,
	Actions varChar(180),
	tranasctionAmount double precision,
	constraint Pk_Transactions primary key (TransactionID,Date) 
);

create table "authorization"
(
	UserID bigserial  REFERENCES Users (UserID) ON DELETE CASCADE ON UPDATE CASCADE,
	AccountID bigserial REFERENCES Accounts (AccountID) ON DELETE CASCADE ON UPDATE cascade,
	TransactionID bigserial references Transactions (TransactionID) on delete cascade on update cascade,
	constraint Pk_Authorization primary key (UserID,AccountID)
);



   
 -- returns the accounst matching the given userid  
create or replace function accounts_by_userid(id "users"."userid" %type) returns setof accounts as $$
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