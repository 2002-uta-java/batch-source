/************************************
 * Project 1 Database - Version 0.01
 * Script Project_1_DB_BuildScript.sql
 * Description: Creates a simple reimbursement database
 * DB Server: Local PostGreSQL
 * Author: Christian Randle
************************************/

drop table if exists Requests;
drop table if exists Accounts;
drop table if exists Users;


create table Users
(
	UserID bigserial unique not null, 
	firstName varchar(180),
	lastName varchar(180),
	userName varchar(180)unique not null,
	email varchar (180) unique not null,
	"password" varchar(180)unique not null,
	constraint Users_PK primary key (UserID,userName, "password"),
	supervisor bigserial 
);

create table Accounts
(
	AccountID bigSerial unique not null,
	UserID bigserial references Users (UserID) on delete cascade,
	Balance numeric constraint account_postive check (balance>0.0),
	constraint Accounts_PK primary key (AccountID)
);


create table Requests
(
	requestID bigserial unique not null,
	sourceID bigserial references Users (UserID) on delete cascade,
	fromID bigserial references Users (UserID) on delete cascade,
	status varchar(180),
	amount numeric,
	constraint PK_Requests primary key (requestID)
);


