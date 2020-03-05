create table employees (empl_id serial constraint p_empl_id primary key,
empl_email varchar(40) unique not null,
password char(64) not null,
empl_first_name varchar(20) not null,
empl_last_name varchar(20) not null,
session_token char(64) unique,
is_manager boolean not null);

create table reimbursements (reimb_id serial constraint p_reimb_id primary key,
empl_id int4 references employees (empl_id) not null,
description varchar(140) not null,
amount money not null,
reimb_date date not null,
submit_date date not null,
reply_date date,
reimb_status int4 check(reimb_status > 0
and reimb_status < 4) not null,
reimb_man_id int4 references employees (empl_id));


create or replace function get_reimbs_by_empl_id(fn_empl_id employees.empl_id%type)
returns table (reimb_id int4, reimb_status int4,
	submit_date date,
	amount money,
	description varchar(140),
	reimb_date date,
	reply_date date,
	man_first_name varchar(20),
	man_last_name varchar(20)
)
language plpgsql
as $$
begin
	return query select reimbs.reimb_id, reimbs.reimb_status, reimbs.submit_date, reimbs.amount, reimbs.description, reimbs.reimb_date, reimbs.reply_date, emp.empl_first_name, emp.empl_last_name 
	from (select * from reimbursements r2 where r2.empl_id = fn_empl_id and r2.reimb_status != 1) as reimbs
	inner join 
	employees as emp 
	on reimbs.reimb_man_id = emp.empl_id;
end
$$;

create or replace function get_all_processed()
returns table (	empl_first_name varchar(20), 
	empl_last_name varchar(20), 
	reimb_status int4,
	submit_date date,
	amount money,
	description varchar(140),
	reimb_date date,
	reply_date date,
	man_first_name varchar(20),
	man_last_name varchar(20)
)
language plpgsql
as $$
begin
	return query select emp.empl_first_name, emp.empl_last_name, processed.reimb_status, processed.submit_date, processed.amount, processed.description, processed.reimb_date, processed.reply_date, processed.man_first as man_first_name, processed.man_last as man_last_name
	from (select reimbs.empl_id as r_empl_id, reimbs.reimb_status, reimbs.submit_date, reimbs.amount, reimbs.description, reimbs.reimb_date, reimbs.reply_date, man.empl_first_name as man_first, man.empl_last_name as man_last
	from (select * from reimbursements r2 where r2.reimb_status != 1) as reimbs
	inner join 
	employees as man 
	on reimbs.reimb_man_id = man.empl_id) as processed
	inner join
	employees as emp
	on processed.r_empl_id = emp.empl_id;
end
$$;

select * from get_reimbs_by_empl_id(1);
select * from get_all_processed()

drop table employees;

drop table reimbursements;

drop function get_reimbs_by_empl_id;
drop function get_all_processed;

insert
	into
	employees (empl_id,
	empl_email,
	empl_first_name,
	empl_last_name,
	session_token,
	is_manager,
	password)
values (1,
'ecisneros0@guardian.co.uk',
'Erika',
'Cisneros',
'8i72G30cj1EyX45G71n1w779W0113r29pPo97l6I7792901U8r0a1Hk1Ek666Q15',
false,
'sM2ORYlC92eF34I5MctTYaYGghDzNajSz5xAMx19dpZDeFplHysAgIsMpJrNbcty');

insert
	into
	employees (empl_id,
	empl_email,
	empl_first_name,
	empl_last_name,
	session_token,
	is_manager,
	password)
values (2,
'jdeath1@gravatar.com',
'Julius',
'De''Ath',
null,
false,
'hQkxlvJ97mUa2JZS+/kjiv/bnMlyUt1UyM8mL0p0rhGRRGAHbNmsn9zuv3h5ID/D');

insert
	into
	employees (empl_id,
	empl_email,
	empl_first_name,
	empl_last_name,
	session_token,
	is_manager,
	password)
values (3,
'dcalabry2@craigslist.org',
'Dorita',
'Calabry',
null,
false,
'Wbylluqq9wpuEhjOHoNdtw4T9sNh71QcfN7ZpZUUZwl3y0rb6sgquPAauD4DW1De');

insert
	into
	employees (empl_id,
	empl_email,
	empl_first_name,
	empl_last_name,
	session_token,
	is_manager,
	password)
values (4,
'tandre3@seattletimes.com',
'Tana',
'Andre',
null,
false,
'zQPWwDIbzXuhy/ra2k+2rfe29fgfvkgqbiKpYZoRii6nE//cMDSIuH1zBp2F7IQz');

insert
	into
	employees (empl_id,
	empl_email,
	empl_first_name,
	empl_last_name,
	session_token,
	is_manager,
	password)
values (5,
'jwillmont4@timesonline.co.uk',
'Johnathon',
'Willmont',
'fm6916688B53cL297zuuI7Ke4zJ102dOg9LA27QyG965iesM51B05771Y29447d6',
true,
'uCejZgdHT/vjrZRXHJEvJIL+x6zEgdCpR2nCOV67Q+8WZjiOf7adKeG4V9s9ISvM');