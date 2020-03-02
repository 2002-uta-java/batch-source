create table employees (empl_id serial constraint p_empl_id primary key, empl_email varchar(40) unique not null,
password char(64) not null,
empl_first_name varchar(20) not null,
empl_last_name varchar(20) not null,
session_token char(64) unique,
is_manager boolean not null);

drop table employees;

insert into employees (empl_email, password, empl_first_name, empl_last_name, is_manager) values ('bennattj@gmail.com', 'mWkusj8M+Yr4jum4ue0h+O4DyaP2hK4jdW8iPu+MxA0gZDNz268rFu2zxfFClWql', 'Jared', 'Bennatt', false);
insert into employees (empl_email, password, empl_first_name, empl_last_name, is_manager) values ('jamiebennatt', 'JiF6V62R7kl0g9NGsKk1b3spr/DmGZJuuVIwd7H2BzHy+7De3jeuDaUY8MZOpSSa', 'Jamie', 'Bennatt', true);