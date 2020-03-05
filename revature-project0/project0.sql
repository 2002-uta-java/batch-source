-- CREATE-------------------------------------------------------------------------------------
-- create table for customer
create table customer(
	cust_id serial constraint pk_cust_id primary key,
	user_name varchar(50) not NULL unique,
	email varchar(50) not null unique,
	passwd varchar(50)not null
);

-- create table for bank account
create table account(
	acc_id serial constraint pk_acc_id primary key,
	cust_id integer not null,
	acc_type varchar(10) not null,
	balance float,
	foreign key (cust_id) references customer(cust_id)
);

-- create transaction table
create table account_transaction(
	trans_id serial constraint pk_trans_id primary key,
	acc_id integer not null,
	transaction_type varchar(10) not null,
	amount numeric(7,2) not null,
	foreign key (acc_id) references account(acc_id)
);


-- DROP TABLE-------------------------------------------------------------------------------------
-- delete table
drop function add_customer;
drop function add_account;
drop table account_transaction;
drop table account; 
drop table customer;

-- FUNCTION---------------------------------------------------------------------------
-- create a function to insert new customer
create or replace function add_customer(user_name_input customer.user_name%type, email_input customer.email%type, passwd_input customer.passwd%type)
returns setof customer
language plpgsql
as $$
declare 	
	user_id integer;
begin 
	insert into customer (user_name, email, passwd) values (user_name_input, email_input, passwd_input);

	select last_value into user_id
	from customer_cust_id_seq;

	return query select * from customer where cust_id = user_id;
end
$$

-- function to insert new account
create or replace function add_account(cust_id_input account.cust_id%type, acc_type_input account.acc_type%type, balance_input account.balance%type)
returns setof account
language plpgsql
as $$
declare 	
	user_acc_id integer;
begin 
	insert into account (cust_id, acc_type, balance) values (cust_id_input, acc_type_input, balance_input);

	select last_value into user_acc_id
	from account_acc_id_seq;

	return query select * from account where acc_id = user_acc_id;
end
$$




-- INSERT------------------------------------------------------------------------------------------
-- insert into customer with user_name, email, password
insert into customer(user_name, email, passwd) 
values('hoang1', 'hoang@revature.com', 'hoangpassword');

-- fail because user_name is unique
--insert into customer(user_name, email, passwd) values('hoang1', 'hoang@revature.com', 'hoangpassword');

-- fail because email is unique
--insert into customer(user_name, email, passwd) values('hoang123', 'hoang@revature.com', 'hoangpassword');

insert into customer(user_name, email, passwd) 
values('brian23', 'brian@revature.com', 'brianpassword');

insert into customer(user_name, email, passwd) 
values('luis45', 'luis@revature.com', 'luispassword');

-- insert into account with cust_id, acc_type, balance
insert into account(cust_id, acc_type, balance) values('1', 'checking', '1000');

insert into account(cust_id, acc_type, balance) values('2', 'checking', '3000');

insert into account(cust_id, acc_type, balance) values('3', 'checking', '5000');

insert into account(cust_id, acc_type, balance) values('4', 'checking', '5090');

insert into account(cust_id, acc_type, balance) values('15', 'checking', '5400');



-- insert into customer with function
select add_customer('hanuh', 'hanuh@gmail.com', 'hanuhpassword');


-- insert into acount_transaction with acc_id, transaction_type, amount

-- add customer and account using function
select add_customer('abc123', 'acb@revature.com', 'abcpassword');
select add_account('6', 'checking', '500.50');
select add_customer('josue123', 'josue123@revature.com', 'josuepassword');
select add_account('7', 'checking', '700.59');


-- UPDATE----------------------------------------------------------------------------------------
-- deposit money
update account
set balance = balance + 500
where acc_id = 1;

-- withdraw money
update account 
set balance = balance - 200
where acc_id = 4;


-- QUERY-------------------------------------------------------------------------------------------

select * from customer;
select * from account;
--select * from account_transaction;


select c.user_name, a.acc_type , a.balance 
from account a 
join customer c
on c.cust_id = a.cust_id
where c.cust_id = 4;

select * from  customer where (user_name = 'hoansssg1' and passwd = 'hoangpassword') or (email = '' and passwd = 'asdas');
