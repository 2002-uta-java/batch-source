drop table if exists holder;
CREATE TABLE holder (
    holderUserName varchar not null unique,
    holderPassword varchar not null,
    balance numeric (10,2) default 0.00
);


insert into holder(holderUserName, holderPassword, balance)
values ('pinkerton','default',999.0);

