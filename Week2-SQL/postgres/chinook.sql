-- Select all records from the Employee table.
select *
from "Employee";

-- Select all records from the Employee table where last name is King.
select *
from "Employee"
where "LastName" = 'King';

-- Select all albums in Album table and sort result set in descending order by title.
select *
from "Album"
order by "Title" desc;

-- Select first name from Customer and sort result set in ascending order by city.
select "FirstName"
from "Customer"
order by "City" asc;

-- Select all invoices with a billing address like “T%”.
select *
from "Invoice"
where "BillingAddress" like 'T%';

-- Select the name of the longest track.
select "Name" 
from "Track"
order by "Milliseconds" desc limit 1;


-- Find the average invoice total.
select avg("Total") 
from "Invoice";

-- Find the total number of employees in each position.
select count("EmployeeId"), "Title"
from "Employee"
group by "Title";

-- Insert two new records into Genre table
insert into "Genre" values (26, 'chinese');
insert into "Genre" values (27, 'r&b');

-- Insert two new records into Employee table
INSERT INTO "Employee" ("EmployeeId", "LastName", "FirstName", "Title", "BirthDate", "HireDate", "Address", "City", "State", "Country", "PostalCode", "Phone", "Fax", "Email") VALUES (9, N'Adams', N'Andrew', N'General Manager', '1962/2/18', '2002/8/14', N'11120 Jasper Ave NW', N'Edmonton', N'AB', N'Canada', N'T5K 2N1', N'+1 (780) 428-9482', N'+1 (780) 428-3457', N'andrew@chinookcorp.com');
INSERT INTO "Employee" ("EmployeeId", "LastName", "FirstName", "Title", "BirthDate", "HireDate", "Address", "City", "State", "Country", "PostalCode", "Phone", "Fax", "Email") VALUES (10, N'Adams', N'Andrew', N'General Manager', '1962/2/18', '2002/8/14', N'11120 Jasper Ave NW', N'Edmonton', N'AB', N'Canada', N'T5K 2N1', N'+1 (780) 428-9482', N'+1 (780) 428-3457', N'andrew@chinookcorp.com');

-- Insert two new records into Customer table 
INSERT INTO "Customer" ("CustomerId", "FirstName", "LastName", "Address", "City", "Country", "PostalCode", "Phone", "Email", "SupportRepId") VALUES (60, N'Leonie', N'K�hler', N'Theodor-Heuss-Stra�e 34', N'Stuttgart', N'Germany', N'70174', N'+49 0711 2842222', N'leonekohler@surfeu.de', 5);
INSERT INTO "Customer" ("CustomerId", "FirstName", "LastName", "Address", "City", "Country", "PostalCode", "Phone", "Email", "SupportRepId") VALUES (61, N'Leonie', N'K�hler', N'Theodor-Heuss-Stra�e 34', N'Stuttgart', N'Germany', N'70174', N'+49 0711 2842222', N'leonekohler@surfeu.de', 5);

-- Update Aaron Mitchell in Customer table to Robert Walter
update "Customer" 
set "FirstName" = 'Robert', "LastName" = 'Walter'
where "FirstName" = 'Aaron' and "LastName" ='Mitchell';

-- Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
update "Artist" 
set "Name" = 'CCR'
where "Name" = 'Creedence Clearwater Revival';

