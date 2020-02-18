create or replace function delete_user( id users.userid %type ) returns void  as $$
	delete
	from accounts  a3 
	where accountid  in
		(
			select accountid 
			from users  u1
			join "authorization"  au2 
			on (u1.userid  = au2.userid and u1.userid = id)
		)  ;

	delete from "authorization"  where userid = id ;
	delete from users where userid = id;
$$ language sql;

create or replace function create_user(un users.username %type, ue users.useremail %type, pw users."password" %type ) returns bigint  as $$
	insert into users (username, useremail ,"password" )
	values (un,ue,pw)
	returning userid
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

select * 
from users u1
full outer join (
	select * 
	from "authorization" au1
	full outer join accounts a1
	on a1.accountid = au1.accountid 
) as aau1
on u1.userid = aau1.userid;



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
-- view acount