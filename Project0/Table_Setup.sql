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

select create_account('user01', 'password1');
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

-- max88 -> password

select *
from bank_account;
select *
from user_account;

drop function create_account;
drop table user_account;
drop table bank_account;

update user_account set username = 'aaronw' where bank_id = 1;
update user_account set username = 'alexR' where bank_id = 2;
update user_account set username = 'tesla2002' where bank_id = 3;
update user_account set username = 'RyanM' where bank_id = 4;
update user_account set username = 'antSpearow' where bank_id = 5;
update user_account set username = 'Nista12' where bank_id = 6;
update user_account set username = 'iMaxwell' where bank_id = 7;
update user_account set username = 'billgates' where bank_id = 8;
update user_account set username = 'mrSteele' where bank_id = 9;
update user_account set username = 'max88' where bank_id = 10;
update user_account set user_password = 'a441b15fe9a3cf56661190a0b93b9dec7d04127288cc87250967cf3b52894d11' where bank_id = 1;
update user_account set user_password = 'a441b15fe9a3cf56661190a0b93b9dec7d04127288cc87250967cf3b52894d11' where bank_id = 1;
update user_account set user_password = 'd4536f2555836b0b1bdc536c56e6f7245a2e89dd20ff8df68ade3cf0e7f39a65' where bank_id = 2;
update user_account set user_password = '688787d8ff144c502c7f5cffaafe2cc588d86079f9de88304c26b0cb99ce91c6' where bank_id = 3;
update user_account set user_password = 'f6e0a1e2ac41945a9aa7ff8a8aaa0cebc12a3bcc981a929ad5cf810a090e11ae' where bank_id = 4;
update user_account set user_password = '4e07408562bedb8b60ce05c1decfe3ad16b72230967de01f640b7e4729b49fce' where bank_id = 5;
update user_account set user_password = '7a61b53701befdae0eeeffaecc73f14e20b537bb0f8b91ad7c2936dc63562b25' where bank_id = 6;
update user_account set user_password = '8b940be7fb78aaa6b6567dd7a3987996947460df1c668e698eb92ca77e425349' where bank_id = 7;
update user_account set user_password = 'a829c72c42755e384141ad8f163e4965ef5c9f8f0e07378c1d05a7222af0dd80' where bank_id = 8;
update user_account set user_password = '64daa44ad493ff28a96effab6e77f1732a3d97d83241581b37dbd70a7a4900fe' where bank_id = 9;