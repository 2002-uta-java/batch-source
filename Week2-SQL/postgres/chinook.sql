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

-- Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
select c."FirstName", i."InvoiceId" 
from "Customer" c 
inner join 
"Invoice" i
on c."CustomerId" = i."CustomerId";

-- Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
select c."FirstName", c."LastName", i."InvoiceId", i."Total" 
from "Customer" c 
full outer join 
"Invoice" i
on c."CustomerId" = i."CustomerId";

-- Create a right join that joins album and artist specifying artist name and title.
select artist."Name" , album."Title" 
from "Album" album 
right join "Artist" artist 
on album."ArtistId"  = artist."ArtistId" ;

-- Create a cross join that joins album and artist and sorts by artist name in ascending order.
select *
from "Album" album
cross join "Artist" artist
order by artist."Name" asc;

-- Perform a self-join on the employee table, joining on the reportsto column.	
select *
from "Employee" employee
join "Employee" reportsto 
on employee."EmployeeId" = reportsto."EmployeeId";

-- Create a query that shows the customer first name and last name as FULL_NAME (you can use || to concatenate two strings) with the total amount of money they have spent as TOTAL.
select (c."FirstName" || c."LastName") FULL_NAME , i."Total" TOTAL
from "Customer" c
join "Invoice" i
on c."CustomerId" = i."CustomerId";

-- Create a query that shows the employee that has made the highest total value of sales (total of all invoices).
select e."FirstName", sum(i."Total") total_sales
from "Employee" e
join "Customer" c
on e."EmployeeId" = c."SupportRepId" 
join "Invoice" i
on i."CustomerId" = c."CustomerId" 
group by e."EmployeeId" 
order by total_sales desc limit 1;


-- Create a query which shows the number of purchases per each genre. Display the name of each genre and number of purchases. Show the most popular genre first.
select g."Name", sum(il."Quantity") total_purchases
from "Track" t 
join "InvoiceLine" iL 
on t."TrackId" = iL."TrackId" 
join "Genre" g
on g."GenreId" = t."GenreId" 
group by g."Name"
order by total_purchases desc;

-- Create a function that returns the average total of all invoices.
create function get_avg_invoices()
returns numeric(4,2)
language plpgsql
as $$
	declare invoice_total_avg numeric(4,2);
begin
	select round(avg(i."Total")) into invoice_total_avg
	from "Invoice" i;
	return invoice_total_avg;
end
$$

select get_avg_invoices();


-- Create a function that returns all employees who are born after 1968.
create or replace function get_employees_born_after_1968()
returns integer
language plpgsql
as $$
declare
	employees_born_after_1968 integer;
begin
	select * into employees_born_after_1968
	from "Employee" e
	where e."BirthDate" >= timestamp '1969-01-01 00:00:00';
	return employees_born_after_1968;
end
$$

select get_employees_born_after_1968();

-- Create a function that returns the manager of an employee, given the id of the employee.
create or replace function get_manager(empl_id_input "Employee"."EmployeeId"%type)
returns varchar(20)
language plpgsql
as $$
declare
	manager_of_empl varchar(20);
begin
	select (m."FirstName" || m."LastName") into manager_of_empl
	from "Employee" e
	join "Employee" m
	on  m."EmployeeId" = e."ReportsTo"
	where empl_id_input = e."EmployeeId";
	return manager_of_empl;
end
$$

select get_manager(2);



-- Create a function that returns the price of a particular playlist, given the id for that playlist.

create function get_playlist_price(playlist_id "Playlist"."PlaylistId"%type)
returns numeric(10,2)
language plpgsql
as $$
declare 
	playlist_price numeric(10,2);
begin
	select sum(t."UnitPrice") into playlist_price
	from "Playlist" p
	inner join "PlaylistTrack" pt 
		on p."PlaylistId" = pt."PlaylistId" 
	inner join "Track" t 
		on t."TrackId" = pt."TrackId" 
	where p."PlaylistId" = playlist_id;
	return playlist_price;
	
end
$$

select get_playlist_price(1);


