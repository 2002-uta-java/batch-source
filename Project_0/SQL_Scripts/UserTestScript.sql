create or replace function create_user(un users.username %type, ue users.useremail %type, pw users."password" %type ) returns bigint  as $$
	insert into users (username, useremail ,"password" )
	values (un,ue,pw)
	returning userid
$$ language sql;

create or replace function add_account(i users.userid %type, bl accounts.balance  %type, ty accounts."type" %type) returns void  as $$
	with acd1 as (
		insert into accounts (balance, "type")
		values (bl,ty) 
		returning accountid
	)
	insert into "authorization" (userid,accountid) values (i,(select * from acd1))
$$ language sql;

 -- returns the accounst matching the given userid  
create or replace function accounts_by_userid(id users.userid %type) returns setof accounts as $$
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


 select create_user ('Bob', 'bob@something.com', '1234bob');
 select add_account (1::bigint,32.0::bigint,'Checking');

select * 
from users u1
full outer join (
	select * 
	from "authorization" au1
	full outer join accounts a1
	on a1.accountid = au1.accountid 
) as aau1
on u1.userid = aau1.userid;

select * from "authorization" a;

delete  from users where userid = 1;

select * 
from users u1
full outer join (
	select * 
	from "authorization" au1
	full outer join accounts a1
	on a1.accountid = au1.accountid 
) as aau1
on u1.userid = aau1.userid;

alter sequence users_userid_seq restart with 1;
drop function accounts_by_userid(users.userid %type);
drop function add_account(i users.userid %type, bl accounts.balance  %type, ty accounts."type" %type);
drop function create_user(un users.username %type, ue users.useremail %type, pw users."password" %type );
-- view acount