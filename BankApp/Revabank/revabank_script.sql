create table bank_user(
	user_id serial constraint user_id primary key,
	username varchar constraint username unique,
	pwd varchar
);
alter table bank_user alter column pwd set not null;
drop table bank_user 

create table account(
	account_id serial constraint account_id primary key,
	user_id integer references bank_user(user_id),
	balance numeric(8,4)
);

insert into bank_user (username, pwd) values ('israel', 'mypassword');
select * from bank_user where username = 'israel';
update bank_user set pwd = 'MyNewPassword' where user_id = 1;
insert into bank_user (username, pwd) values ('man', 'mp');
select * from bank_user;
delete from bank_user where user_id = 3;

insert into account (user_id, balance) values (1,2000.00);
insert into account (user_id, balance) values (2,2300.00);
select * from account;
update account set balance = 500 where user_id = 3;
delete from account where user_id = 3;

select * from bank_user b
inner join account a
on b.user_id = a.user_id ;

update bank_user set pwd = '75ddab9def3520534f5254183476b666d210ae3d' where user_id = 2;
update bank_user set pwd = '48346fe92ce9f17f99666a9760715d5cfcac31fd' where user_id = 1;
insert into account (user_id , balance ) values(4,0);
