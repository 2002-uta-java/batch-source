


 select create_user ('billy', 'bob@something.com', 'gruff');
 select add_account (1::bigint,32.0::money;'Checking');
 select add_account (1::bigint,3882.0::money;'Savings');
--
--update account where accountid  in
--		(
--			select accountid from users  u1
--			join Authorizations  au2 
--			on (u1.userid  = au2.userid and u1.userid = id)
--		)  


select * 
from users u1
full outer join (
	select * 
	from Authorizations as au1
	full outer join accounts a1
	on a1.accountid = au1.accountid 
) as aau1
on u1.userid = aau1.userid;

select delete_user(1);



select * 
from users u1
full outer join (
	select * 
	from Authorizations as au1
	full outer join accounts a1
	on a1.accountid = au1.accountid 
) as aau1
on u1.userid = aau1.userid;

alter sequence users_userid_seq restart with 1;
drop function if exists delete_accounts(ai accounts.accountid %type);
drop function if exists delete_user(ui users.userid %type);
drop function if exists accounts_by_userid(users.userid %type);
drop function  if exists add_account(i users.userid %type, bl accounts.balance  %type, ty accounts."type" %type);
drop function if exists create_user(un users.username %type, ue users.useremail %type, pw users."password" %type );
-- view acount