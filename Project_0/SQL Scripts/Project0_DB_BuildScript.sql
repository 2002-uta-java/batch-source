/************************************
 * Project0 Database - Version 0.01
 * Script Project0_DB_BuildScript.sql
 * Description: Creates a simple banking database
 * DB Server: Local PostGreSQL
 * Author: Christian Randle
************************************/

--NEED TO ADD UNIQUE CONSTRAINTS

create table "Users"
(
	"UserID" bigint not null,
	"UserEmail" VARCHAR (180),
	"UserName" VARCHAR(180) not null,
	"Password" VARCHAR(180) not null,
	constraint "PK_Users" primary key ("UserID")
);

create table "Authorization"
(
	"UserID" bigint not null,
	"AccountID" bigint not null,
	"TransactionID" bigint unique not null,
	constraint "Pk_Authorization" primary key ("UserID","AccountID")
);

create table "Accounts"
(
	"AccountID" bigint unique not null,
	"balance" bigint not null,
	"Type" varchar(180) not null,
	constraint "PK_Accounts" primary key ("AccountID")
);

create table "Transactions"(
	"TransactionID" bigint not null,
	"Date" timestamp not null,
	"Actions" varChar(180),
	"tranasctionAmount" double precision,
	constraint "Pk_Transactions" primary key ("TransactionID","Date")
);


ALTER TABLE "Authorization" ADD CONSTRAINT "FK_AuthorizationU"
    FOREIGN KEY ("UserID") REFERENCES "Users" ("UserID") ON DELETE CASCADE ON UPDATE CASCADE;
   
ALTER TABLE "Authorization" ADD CONSTRAINT "FK_AuthorizationA"
    FOREIGN KEY ("AccountID") REFERENCES "Accounts" ("AccountID") ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "Transactions" ADD CONSTRAINT "FK_Transactions"
    FOREIGN KEY ("TransactionID") REFERENCES "Authorization" ("TransactionID") ON DELETE CASCADE ON UPDATE CASCADE;

--indexes?