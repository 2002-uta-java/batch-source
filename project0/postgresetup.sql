create table user_account(
	uaid serial constraint pk_user_id primary key,
	username varchar(50) default '',
	email varchar(50) default '',
	pw varchar(50) default ''
);

insert into user_account(uaid, username, email, pw) values(1, 'username', 'email', 'pw');
insert into user_account(uaid, username, email, pw) values(2, 'apple', 'fruits@apple.com', 'worm');
insert into user_account(uaid, username, email, pw) values(3, 'bobobomb', 'gumbo@gmail.com', '8675309');
insert into user_account(uaid, username, email, pw) values(4, 'gordon', 'chefzeff@yahoo.com', '4bananas');

create or replace function add_user_account(username_input character varying, email_input character varying, pw_input character varying)
returns setof user_account
language plpgsql 
as $$
declare 
	new_id integer;
begin 
	insert into user_account(username, email, pw) values(username_input, email_input, pw_input);

	select last_value into new_id 
	from user_account_uaid_seq;

	return query select * from user_account where uaid = new_id;
end
$$;

create or replace function check_uaid_uniqueness()
returns trigger 
language plpgsql
as $$
declare 
	id_count integer;
begin
	loop
		select count(uaid) into id_count
		from user_account
		where uaid = new.uaid;

		if id_count > 0 then 
			new.uaid = nextval('user_account_uaid_seq');
		end if;
		exit when id_count = 0;
	end loop;
	return new;
end
$$;

create trigger assure_unique_uaid
before insert on user_account
for each row 
execute procedure check_uaid_uniqueness();

create table bank_account(
	baid serial constraint pk_bank_id primary key,
	uaid integer references user_account(uaid),
	account_type varchar(20) default 'checking',
	balance numeric(15,2) default 0
);

insert into bank_account(baid, uaid, account_type, balance) values(1, 1, 'checking', 1000);
insert into bank_account(baid, uaid, account_type, balance) values(2, 1, 'savings', 260.87);
insert into bank_account(baid, uaid, account_type, balance) values(3, 2, 'checking', 100000);
insert into bank_account(baid, uaid, account_type, balance) values(4, 3, 'checking', 0);
insert into bank_account(baid, uaid, account_type, balance) values(5, 3, 'savings', 0);
insert into bank_account(baid, uaid, account_type, balance) values(6, 1, 'checking', 984781234.00);

create or replace function add_bank_account(uaid_input integer, account_type_input character varying)
returns setof bank_account
language plpgsql
as $$
declare 
	new_id integer;
begin
	insert into bank_account(uaid, account_type) values(uaid_input, account_type_input);

	select last_value into new_id 
	from bank_account_baid_seq;

	return query select * from bank_account where baid = new_id;
end
$$;

create or replace function check_baid_uniqueness()
returns trigger
language plpgsql
as $$
declare 
	id_count integer;
begin 
	loop
		select count(baid) into id_count 
		from bank_account
		where baid = new.baid;

		if id_count > 0 then
			new.baid = nextval('bank_account_baid_seq');
		end if;
		exit when id_count = 0;
	end loop;
	return new;
end
$$;

create trigger assure_unique_baid
before insert on bank_account
for each row 
execute procedure check_baid_uniqueness();