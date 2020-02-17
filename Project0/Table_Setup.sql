create table user_account
(
	username VARCHAR(25) not null,
	user_password VARCHAR(25) not null,
	bank_id serial references bank_account (bank_id),
	primary key (username)
);

create table bank_account
(
	bank_id serial,
	balance numeric(11, 2),
	primary key (bank_id)
);

create or replace procedure create_account (username user_account.username%type, user_password user_account.user_password%type)
language plpgsql
as $$
begin 
	insert into bank_account (balance)
	values (0.00);
	insert into user_account (username, user_password)
	values (username, user_password);
end
$$

call create_account('hitman', 'password');

select *
from bank_account;
select *
from user_account;

