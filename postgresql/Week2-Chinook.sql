-- selecting all data from the employee table
select * from "Employee";

-- selecting all employees where last name is King
select * from "Employee" where "LastName"='King';

-- selecting all albums sorting by title in descending order
select "Title" from "Album" order by "Title" desc;

-- selecting first name of customers sorted by city in ascending order
select "FirstName" from "Customer" order by "City" asc;

-- selecting all invoices with a billing address like "T%"
select * from "Invoice" where "BillingAddress" like 'T%';

-- getting name of longest track
select "Name" from "Track" where "Milliseconds" = (select max("Milliseconds") from "Track");

-- getting name of longest track using descending order
select "Name" from "Track" order by "Milliseconds" desc limit 1;

-- get average invoice total
select avg("Total") from "Invoice";

-- total number of employees in each position
select "Title", count("Title") from "Employee" group by "Title";

-- inserting into genre table
insert into "Genre" ("GenreId", "Name") values (26, 'Pop Punk');
insert into "Genre" values (27, 'Folk Rock');

-- inserting two records into employee table

-- myself, title is "Trainee" and I report to Laura (id 8), born 02/27/1983, hired on 02/03/2020, 
insert into "Employee" values (9, 'Bennatt', 'Jared', 'Trainee', 8, timestamp '1983-02-27 00:00:00', timestamp '2020-02-03 00:00:00', '1683 Otto Rd', 'Inez', 'TX', 'USA', '77968', '+1 (361) 781-4929', null, 'bennattj@gmail.com');
-- inserting my wife as a "Sales Support Agent" reporting to Nancy (id 2)
insert into "Employee" values (10, 'Bennatt', 'Jamie', 'Sales Support Agent', 2, timestamp '1984-10-18 00:00:00', '2020-02-03 00:00:00', '1683 Otto Rd', 'Inez', 'TX', 'USA', '77968', '+1 (361) 781-4378', null, 'jamiebennatt@gmail.com');