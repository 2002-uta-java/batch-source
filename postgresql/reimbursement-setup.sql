create table employees (empl_email varchar(40) constraint p_empl_key primary key,
password varchar(64),
empl_first_name varchar(20),
empl_last_name varchar(20),
session_id varchar(64) unique);

drop table employees;

insert into employees (empl_email, password, empl_first_name, empl_last_name) values ('bennattj@gmail.com', 'mWkusj8M+Yr4jum4ue0h+O4DyaP2hK4jdW8iPu+MxA0gZDNz268rFu2zxfFClWql', 'Jared', 'Bennatt');