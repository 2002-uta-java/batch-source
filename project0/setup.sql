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