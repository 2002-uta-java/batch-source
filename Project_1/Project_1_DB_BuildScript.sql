/************************************
 * Project 1 Database - Version 0.01
 * Script Project_1_DB_BuildScript.sql
 * Description: Creates a simple reimbursement database
 * DB Server: Local PostGreSQL
 * Author: Christian Randle
************************************/

drop table if exists Requests;
drop table if exists Accounts;


create table Accounts
(
	AccountID bigserial unique not null, 
	firstName varchar(180),
	lastName varchar(180),
	userName varchar(180)unique not null,
	email varchar (180) unique not null,
	"password" varchar(180)unique not null,
	constraint Accounts_PK primary key (AccountID,userName, "password"),
	supervisor bigserial 
);

create table Requests
(
	requestID bigserial unique not null,
	sourceid bigserial references Accounts (AccountID) on delete cascade,
	overseer bigserial references Accounts (AccountID) on delete cascade,
	status varchar(180),
	amount money,
	constraint PK_Requests primary key (requestID)
);


