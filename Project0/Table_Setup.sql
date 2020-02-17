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

create or replace function create_account (un user_account.username%type, up user_account.user_password%type)
returns setof user_account 
language plpgsql
as $$
begin 
	insert into bank_account (balance)
	values (0.00);
	insert into user_account (username, user_password)
	values (un, up);
	return query select *
				from user_account
				where user_account.username = un;
end
$$

select create_account('javafix2', '2password');

select *
from bank_account;
select *
from user_account;

drop function create_account;