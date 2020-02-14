-- table of accounts, only holds account number (primary key) and account balance. No values can be null
create table accounts (account_no varchar(10) constraint pk_account_id primary key,
balance double precision not null);

-- creates user tables. This only holds information for the users (not information connecting to accounts).
-- the tax_id is the primary key but user names must also be unique. No values can be null.
create table users (tax_id varchar(10) constraint pk_user_id primary key,
firstname varchar(20) not null,
lastname varchar(20) not null,
username varchar(16) not null unique,
password varchar(16) not null);

-- this is my junction table to link users and accounts. Neither column needs to be unique (and I expect multiples)
create table user_accounts (tax_id varchar(10), account_no varchar(10));