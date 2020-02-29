drop table if exists Employee cascade;
drop table if exists Reimbursment cascade;

create table Employee (
id serial primary key,
firstname varchar,
lastname  varchar,
email     varchar unique,
phone     varchar,
password  varchar,
isManager boolean);

create table Reimbursment (
id serial primary key,
amount int,
stage varchar,
"timestamp" timestamp,
employee_id int references Employee);


insert into Employee (firstname , lastname , email , phone , "password" , ismanager) values ('Jon', 'Snow', 'fireandice@gmail.com', '6158999988', '1234', false);
insert into Employee (firstname , lastname , email , phone , "password" , ismanager) values ('Danny', 'Stormborn', 'deathtoall@gmail.com', '6158898889', '1234', true);
insert into Employee (firstname , lastname , email , phone , "password" , ismanager) values ('Tim', 'Tebone', 'timmytrex@gmail.com', '6155475541', 'thoaster', false);
insert into Employee (firstname , lastname , email , phone , "password" , ismanager) values ('David', 'Tran', 'dtran@gmail.com', '6158989877', 'transter67', true);

insert into Reimbursment (amount, stage , "timestamp" , employee_id) values (50, 'Processing', '2019-2-14 3:30:06', 4);
insert into Reimbursment (amount, stage , "timestamp" , employee_id) values (350, 'Processing', '2019-2-14 8:36:33', 6);
insert into Reimbursment (amount, stage , "timestamp" , employee_id) values (10, 'Processing', '2019-2-18 12:40:20', 5);
insert into Reimbursment (amount, stage , "timestamp" , employee_id) values (5, 'Processing', '2019-2-20 18:55:59', 7);
