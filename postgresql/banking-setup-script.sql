drop table accounts;
drop table user_accounts;
drop table users;

-- table of accounts, only holds account number (primary key) and account balance. No values can be null
 create table accounts (account_no varchar(64) constraint pk_account_id primary key,
balance varchar(64) not null);

-- creates user tables. This only holds information for the users (not information connecting to accounts).
--  the tax_id is the primary key but user names must also be unique. The username and password can be null,
--  signifying that this person does not have a login (or account for that matter) with this bank.
 create table users (tax_id varchar(64) constraint pk_user_id primary key,
firstname varchar(64) not null,
lastname varchar(64) not null,
username varchar(64) unique,
password varchar(64));

-- this is my junction table to link users and accounts. Neither column needs to be unique (and I expect multiples)
 create table user_accounts (tax_id varchar(64),
account_no varchar(64));

select * from users;