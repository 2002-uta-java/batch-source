create table _user(
	id serial constraint pk_bank_user_id primary key,
	username varchar(50),
	email varchar(50),
	pass varchar(50)
);

insert into _user (id, username, email, pass) values (1, 'jkinchley0', 'clabadini0@earthlink.net', 'xVimYu');
insert into _user (id, username, email, pass) values (2, 'xleadbitter1', 'ekell1@bbc.co.uk', 'C2ibhR8');
insert into _user (id, username, email, pass) values (3, 'alehrer2', 'bwadhams2@pbs.org', 'Zpjl1Ew9Wqv');
insert into _user (id, username, email, pass) values (4, 'pecclestone3', 'jabramow3@usgs.gov', 'Z4JPaB');
insert into _user (id, username, email, pass) values (5, 'pstudders4', 'rsydney4@slate.com', '6l9wfNZCY4NU');
insert into _user (id, username, email, pass) values (6, 'psincock5', 'gkornyshev5@theglobeandmail.com', 'tgkvKfHtk6c');
insert into _user (id, username, email, pass) values (7, 'aridge6', 'bskeen6@apache.org', 'ILyekAva');
insert into _user (id, username, email, pass) values (8, 'dhuygens7', 'cfluger7@geocities.com', 'edVWuBi');
insert into _user (id, username, email, pass) values (9, 'hyakunkin8', 'pstreat8@jigsy.com', 'qUxFnWbWEqAX');
insert into _user (id, username, email, pass) values (10, 'njinkins9', 'rstquenin9@godaddy.com', 'iSAY3FTnII7');
insert into _user (id, username, email, pass) values (11, 'tmuntona', 'ahachea@imdb.com', 'QQTnb1fGu');
insert into _user (id, username, email, pass) values (12, 'lketchb', 'vblinmanb@ucsd.edu', 'p3y15o');
insert into _user (id, username, email, pass) values (13, 'rcleaverc', 'rposselc@elpais.com', '5MXnpH5PnH');
insert into _user (id, username, email, pass) values (14, 'hmallabyd', 'mmaried@nih.gov', 'hhU5O9V');
insert into _user (id, username, email, pass) values (15, 'mparsande', 'owakefielde@live.com', 'r4WZumZNSp6');
insert into _user (id, username, email, pass) values (16, 'sattrief', 'hbrammerf@booking.com', '2RC8zk9rqKeT');
insert into _user (id, username, email, pass) values (17, 'pmaffionig', 'csadgroveg@google.co.jp', 'blLwSta');
insert into _user (id, username, email, pass) values (18, 'vdillawayh', 'sbeelbyh@nsw.gov.au', 'NfNBpf6g2');
insert into _user (id, username, email, pass) values (19, 'lsherreardi', 'kferrelii@nasa.gov', '9k2TmkiIV3C');
insert into _user (id, username, email, pass) values (20, 'mslatenj', 'drumboldj@zimbio.com', 'M5VVh6B27o');

create table account(
	id serial constraint pk_bank_account_id primary key,
	account_type varchar(50),
	balance numeric
);

insert into account (id, account_type, balance) values (1, 'savings', 4381);
insert into account (id, account_type, balance) values (2, 'savings', 5101);
insert into account (id, account_type, balance) values (3, 'savings', 7610);
insert into account (id, account_type, balance) values (4, 'checking', 1923);
insert into account (id, account_type, balance) values (5, 'checking', 9744);
insert into account (id, account_type, balance) values (6, 'savings', 1546);
insert into account (id, account_type, balance) values (7, 'savings', 2124);
insert into account (id, account_type, balance) values (8, 'checking', 5800);
insert into account (id, account_type, balance) values (9, 'savings', 8509);
insert into account (id, account_type, balance) values (10, 'checking', 3456);
insert into account (id, account_type, balance) values (11, 'checking', 2105);
insert into account (id, account_type, balance) values (12, 'checking', 298);
insert into account (id, account_type, balance) values (13, 'checking', 4833);
insert into account (id, account_type, balance) values (14, 'checking', 9672);
insert into account (id, account_type, balance) values (15, 'savings', 9052);
insert into account (id, account_type, balance) values (16, 'checking', 1829);
insert into account (id, account_type, balance) values (17, 'savings', 6163);
insert into account (id, account_type, balance) values (18, 'savings', 8208);
insert into account (id, account_type, balance) values (19, 'savings', 3119);
insert into account (id, account_type, balance) values (20, 'checking', 4471);
insert into account (id, account_type, balance) values (21, 'checking', 123);
insert into account (id, account_type, balance) values (22, 'checking', 5159);
insert into account (id, account_type, balance) values (23, 'savings', 3085);
insert into account (id, account_type, balance) values (24, 'checking', 684);
insert into account (id, account_type, balance) values (25, 'savings', 3302);
insert into account (id, account_type, balance) values (26, 'checking', 9123);
insert into account (id, account_type, balance) values (27, 'checking', 5328);
insert into account (id, account_type, balance) values (28, 'checking', 3075);
insert into account (id, account_type, balance) values (29, 'savings', 7149);
insert into account (id, account_type, balance) values (30, 'checking', 5689);

create table transact(
	id serial constraint pk_bank_tran_id primary key,
	tran_type varchar(50),
	account_id int4 references account(id),
	amount numeric,
	note varchar
);

insert into transact (id, tran_type, amount, account_id, note) values (1, 'deposit', 759, 2, 'Quisque porta volutpat erat.');
insert into transact (id, tran_type, amount, account_id, note) values (2, 'deposit', 963, 7, 'Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc.');
insert into transact (id, tran_type, amount, account_id, note) values (3, 'transfer', 828, 24, 'Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem. Integer tincidunt ante vel ipsum.');
insert into transact (id, tran_type, amount, account_id, note) values (4, 'transfer', 672, 30, 'Proin at turpis a pede posuere nonummy.');
insert into transact (id, tran_type, amount, account_id, note) values (5, 'deposit', 246, 30, 'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec pharetra, magna vestibulum aliquet ultrices, erat tortor sollicitudin mi, sit amet lobortis sapien sapien non mi. Integer ac neque.');
insert into transact (id, tran_type, amount, account_id, note) values (6, 'deposit', 150, 7, 'Donec ut dolor.');
insert into transact (id, tran_type, amount, account_id, note) values (7, 'deposit', 150, 5, 'Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum.');
insert into transact (id, tran_type, amount, account_id, note) values (8, 'withdrawal', 810, 30, 'Vestibulum ac est lacinia nisi venenatis tristique. Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue.');
insert into transact (id, tran_type, amount, account_id, note) values (9, 'deposit', 800, 19, 'In eleifend quam a odio. In hac habitasse platea dictumst.');
insert into transact (id, tran_type, amount, account_id, note) values (10, 'withdrawal', 683, 21, 'Curabitur convallis.');
insert into transact (id, tran_type, amount, account_id, note) values (11, 'deposit', 652, 6, 'Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci. Mauris lacinia sapien quis libero.');
insert into transact (id, tran_type, amount, account_id, note) values (12, 'deposit', 65, 21, 'Fusce consequat.');
insert into transact (id, tran_type, amount, account_id, note) values (13, 'deposit', 276, 21, 'Phasellus in felis.');
insert into transact (id, tran_type, amount, account_id, note) values (14, 'transfer', 960, 9, 'Nulla justo. Aliquam quis turpis eget elit sodales scelerisque.');
insert into transact (id, tran_type, amount, account_id, note) values (15, 'withdrawal', 845, 27, 'Integer a nibh. In quis justo.');
insert into transact (id, tran_type, amount, account_id, note) values (16, 'deposit', 420, 16, 'Nam nulla.');
insert into transact (id, tran_type, amount, account_id, note) values (17, 'withdrawal', 754, 1, 'Suspendisse potenti.');
insert into transact (id, tran_type, amount, account_id, note) values (18, 'withdrawal', 717, 12, 'Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Etiam vel augue.');
insert into transact (id, tran_type, amount, account_id, note) values (19, 'deposit', 739, 18, 'Nullam molestie nibh in lectus.');
insert into transact (id, tran_type, amount, account_id, note) values (20, 'transfer', 62, 26, 'In sagittis dui vel nisl.');
insert into transact (id, tran_type, amount, account_id, note) values (21, 'transfer', 826, 17, 'Nam nulla.');
insert into transact (id, tran_type, amount, account_id, note) values (22, 'transfer', 287, 16, 'Nullam porttitor lacus at turpis. Donec posuere metus vitae ipsum.');
insert into transact (id, tran_type, amount, account_id, note) values (23, 'transfer', 490, 27, 'Ut at dolor quis odio consequat varius.');
insert into transact (id, tran_type, amount, account_id, note) values (24, 'deposit', 467, 17, 'Suspendisse accumsan tortor quis turpis. Sed ante.');
insert into transact (id, tran_type, amount, account_id, note) values (25, 'transfer', 326, 13, 'Duis ac nibh. Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus.');
insert into transact (id, tran_type, amount, account_id, note) values (26, 'withdrawal', 723, 23, 'Nulla mollis molestie lorem.');
insert into transact (id, tran_type, amount, account_id, note) values (27, 'transfer', 328, 3, 'Etiam vel augue.');
insert into transact (id, tran_type, amount, account_id, note) values (28, 'deposit', 341, 12, 'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam. Suspendisse potenti.');
insert into transact (id, tran_type, amount, account_id, note) values (29, 'deposit', 19, 2, 'Curabitur gravida nisi at nibh. In hac habitasse platea dictumst.');
insert into transact (id, tran_type, amount, account_id, note) values (30, 'withdrawal', 165, 26, 'Sed ante.');

-- Run Last
create table user_account(
	user_id int4 references _user(id),
	account_id int4 references account(id)
);

insert into user_account (user_id, account_id) values (14, 5);
insert into user_account (user_id, account_id) values (5, 19);
insert into user_account (user_id, account_id) values (14, 8);
insert into user_account (user_id, account_id) values (14, 28);
insert into user_account (user_id, account_id) values (14, 16);
insert into user_account (user_id, account_id) values (18, 21);
insert into user_account (user_id, account_id) values (16, 12);
insert into user_account (user_id, account_id) values (1, 30);
insert into user_account (user_id, account_id) values (1, 20);
insert into user_account (user_id, account_id) values (5, 14);
insert into user_account (user_id, account_id) values (20, 20);
insert into user_account (user_id, account_id) values (9, 27);
insert into user_account (user_id, account_id) values (19, 16);
insert into user_account (user_id, account_id) values (17, 28);
insert into user_account (user_id, account_id) values (4, 11);
insert into user_account (user_id, account_id) values (1, 20);
insert into user_account (user_id, account_id) values (10, 10);
insert into user_account (user_id, account_id) values (5, 7);
insert into user_account (user_id, account_id) values (12, 25);
insert into user_account (user_id, account_id) values (14, 16);
insert into user_account (user_id, account_id) values (11, 5);
insert into user_account (user_id, account_id) values (12, 10);
insert into user_account (user_id, account_id) values (4, 11);
insert into user_account (user_id, account_id) values (4, 6);
insert into user_account (user_id, account_id) values (2, 9);
insert into user_account (user_id, account_id) values (12, 9);
insert into user_account (user_id, account_id) values (15, 21);
insert into user_account (user_id, account_id) values (9, 28);
insert into user_account (user_id, account_id) values (10, 18);
insert into user_account (user_id, account_id) values (18, 28);