create table employees (empl_id serial constraint p_empl_id primary key,
empl_email varchar(40) unique not null,
password char(64) not null,
empl_first_name varchar(20) not null,
empl_last_name varchar(20) not null,
session_token char(64) unique,
is_manager boolean not null);

drop table employees;

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