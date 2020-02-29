create table employees (empl_email varchar(40) constraint p_empl_key primary key,
password varchar(64),
empl_first_name varchar(20) NOT null,
empl_last_name varchar(20) NOT null,
session_id varchar(64) UNIQUE,
is_manager boolean NOT null);

drop table employees;

insert into employees (empl_email, password, empl_first_name, empl_last_name, is_manager) values ('bennattj@gmail.com', 'mWkusj8M+Yr4jum4ue0h+O4DyaP2hK4jdW8iPu+MxA0gZDNz268rFu2zxfFClWql', 'Jared', 'Bennatt', false);
insert into employees (empl_email, password, empl_first_name, empl_last_name, is_manager) values ('jamiebennatt', 'JiF6V62R7kl0g9NGsKk1b3spr/DmGZJuuVIwd7H2BzHy+7De3jeuDaUY8MZOpSSa', 'Jamie', 'Bennatt', true);