create table bank_account
(
	bank_id serial,
	balance numeric(11, 2),
	primary key (bank_id)
);

create table user_account
(
	username VARCHAR(25) not null,
	user_password VARCHAR not null,
	bank_id serial references bank_account (bank_id),
	primary key (username)
);

create or replace function create_account (un user_account.username%type, up user_account.user_password%type)
returns setof user_account 
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
$$ language plpgsql;

select create_account('user01', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8');
select create_account('user02', 'password2');
select create_account('user03', 'password3');
select create_account('user04', 'password4');
select create_account('user05', 'password5');
select create_account('user06', 'password6');
select create_account('user07', 'password7');
select create_account('user08', 'password8');
select create_account('user09', 'password9');
update bank_account set balance = 50.00 where bank_id = 1;
update bank_account set balance = 20000000 where bank_id = 2;
update bank_account set balance = 10.50 where bank_id = 5;
update bank_account set balance = 100.00 where bank_id = 6;