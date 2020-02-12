-- Selects all records from Employee table
select *
from "Employee";

-- Selects all records from the Employee table where last name is King 
select *
from "Employee"
where "LastName" = 'King';

-- Select all albums in Album table and sort result set in descending order by title
select *
from "Album"
order by "Title" desc;

-- Select first name from Customer and sort result set in ascending order by city.
select "FirstName"
from "Customer"
order by "City" asc;

-- Select all invoices with a billing address like “T%”
select *
from "Invoice"
where "BillingAddress" like 'T%';

-- Select the name of the longest track.
select "Name"
from "Track"
where "Milliseconds" = (select max("Milliseconds") from "Track" )
group by "Name" ;

-- Alt. implementation
/*
select *
from "Track"
order by "Milliseconds" desc; */

-- Find the average invoice total.
select avg("Total") as average_total
from "Invoice" i ;

-- Find the total number of employees in each position.
select count("FirstName"), "Title"
from "Employee" e 
group by "Title";

-----------------------------------------------

-- Insert two new records into Genre table
insert into "Genre" values (26, 'JPop');
insert into "Genre" values (27, 'Classical');

-- Insert two new records into Employee table
INSERT INTO public."Employee" ("EmployeeId","LastName","FirstName","Title","ReportsTo","BirthDate","HireDate","Address","City","State","Country","PostalCode","Phone","Fax","Email") VALUES 
(9,'Adams','Andrew','General Manager',NULL,'1962-02-18 00:00:00.000','2002-08-14 00:00:00.000','11120 Jasper Ave NW','Edmonton','AB','Canada','T5K 2N1','+1 (780) 428-9482','+1 (780) 428-3457','andrew@chinookcorp.com');
INSERT INTO public."Employee" ("EmployeeId","LastName","FirstName","Title","ReportsTo","BirthDate","HireDate","Address","City","State","Country","PostalCode","Phone","Fax","Email") VALUES 
(10,'Adams','Andrew','General Manager',NULL,'1962-02-18 00:00:00.000','2002-08-14 00:00:00.000','11120 Jasper Ave NW','Edmonton','AB','Canada','T5K 2N1','+1 (780) 428-9482','+1 (780) 428-3457','andrew@chinookcorp.com');

-- Insert two new records into Customer table 
INSERT INTO public."Customer" ("CustomerId","FirstName","LastName","Company","Address","City","State","Country","PostalCode","Phone","Fax","Email","SupportRepId") VALUES 
(60,'Joakim','Johansson',NULL,'Celsiusg. 9','Stockholm',NULL,'Sweden','11230','+46 08-651 52 52',NULL,'joakim.johansson@yahoo.se',5)
,(61,'Puja','Srivastava',NULL,'3,Raj Bhavan Road','Bangalore',NULL,'India','560001','+91 080 22289999',NULL,'puja_srivastava@yahoo.in',3)
;

-- Update Aaron Mitchell in Customer table to Robert Walter
update "Customer"
set "FirstName"='Robert',
	"LastName"= 'Walter'
where "FirstName" = 'Aaron' and 
	"LastName" = 'Mitchell';

-- Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
update "Artist"
set "Name" = 'CCR'
where "Name" = 'Creedence Clearwater Revival';

--------------------------------------------------------

-- Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
select c."FirstName", c."LastName", i."InvoiceId" 
from "Customer" c inner join "Invoice" i on c."CustomerId" = i."CustomerId";

-- Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
select c."CustomerId", c."FirstName", c."LastName", i."InvoiceId", i."Total" 
from "Customer" c full outer join "Invoice" i on c."CustomerId" = i."CustomerId";

-- Create a right join that joins album and artist specifying artist name and title.
select a."Name", b."Title"
from "Artist" a right join "Album" b on a."ArtistId" = b."ArtistId";

-- Create a cross join that joins album and artist and sorts by artist name in ascending order.
select *
from "Artist" a cross join "Album" b
order by a."Name";

-- Perform a self-join on the employee table, joining on the reportsto column.	
select *
from "Employee" e join "Employee" b on e."ReportsTo" = b."ReportsTo" ;

-- Create a query that shows the customer first name and last name as FULL_NAME with the total amount of money they have spent as TOTAL.
select "FirstName" || ' ' || "LastName" as "FULL_NAME", sum(i."Total") as "TOTAL"
from "Customer" c join "Invoice" i on c."CustomerId" = i."CustomerId"
group by "FULL_NAME";

-- Create a query that shows the employee that has made the highest total value of sales (total of all invoices).
select e."FirstName" || ' ' || e."LastName" as "FULL_NAME", sum(i."Total") as "TOTAL"
from "Employee" e join "Customer" c on e."EmployeeId" = c."SupportRepId"
	join "Invoice" i on c."CustomerId" = i."CustomerId"
group by "FULL_NAME"
order by "TOTAL" desc limit 1;

-- Create a query which shows the number of purchases per each genre. Display the name of each genre and number of purchases. Show the most popular genre first.
select g."Name", sum(i2."Quantity") as "Purchases"
from "Customer" c join "Invoice" i1 on c."CustomerId" = i1."CustomerId" 
	join "InvoiceLine" i2 on i1."InvoiceId" = i2."InvoiceId"
	join "Track" t on i2."TrackId" = t."TrackId" 
	join "Genre" g on g."GenreId" = t."GenreId" 
group by g."Name"
order by "Purchases" desc;

-------------------------------------------------------------------

-- Create a function that returns the average total of all invoices.
create or replace function 
avgInvoice() returns numeric as $$
	select avg("Total") as AverageTotal
	from "Invoice" i; 
$$ language sql;

select avgInvoice();

-- Create a function that returns all employees who are born after 1968.
-- drop function after1986();
create or replace function 
after1968() returns setof public."Employee" as $$
	select * 
	from "Employee"
	where "BirthDate" > '1968-01-01'; 
$$ language sql;

select after1968();

-- Create a function that returns the manager of an employee, given the id of the employee.
-- DROP FUNCTION getmanager(integer);
create or replace function 
getManager(emp_id int) returns "Employee" as $$
	select * 
	from "Employee" e2 
	where e2."EmployeeId" = (
		select "ReportsTo"
		from "Employee" e
		where e."EmployeeId" = emp_id
	);
$$ language sql;

select getManager(2);

-- Create a function that returns the price of a particular playlist, given the id for that playlist.
create or replace function 
getPlaylistPrice(play_id int) returns numeric as $$
	select sum(il."UnitPrice") 
	from "InvoiceLine" il 
		join "Track" t on il."TrackId" = t."TrackId"
		right join "PlaylistTrack" pt on t."TrackId" = pt."TrackId"
	where pt."PlaylistId" = play_id
$$ language sql;

select getPlaylistPrice(5);