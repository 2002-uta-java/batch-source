insert into users 
	values (00001, 'Bob', 'bob@something.com', '1234bob')
on conflict do nothing;

insert into accounts 
	values
		(00002, 003.0, 'Checkings'),
		(00003, 003.0, 'Savings')
on conflict do nothing;

insert into "authorization" 
	values 
		(00001,00002,12004),
		(00001,00003,12005)
on conflict do nothing;





create or replace function accounts_by_userid(id "users"."userid" %type) returns setof accounts as $$
	select * 
	from accounts  a3 
	where accountid  in
		(
			select accountid 
			from users  u1
			join "authorization"  au2 
			on (u1.userid  = au2.userid and u1.userid = id)
		)  
$$ language sql;

select accounts_by_userid(00001);

-- view acount