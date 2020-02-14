-- Select all records from the Employee table.
select * from "Employee";

-- Select all records from the Employee table where last name is King.
select * from "Employee"
where "LastName" = 'King';

-- Select all albums in Album table and sort result set in descending order by title.
select * from "Album"
order by "Title" desc;

-- Select first name from Customer and sort result set in ascending order by city.
Select "FirstName" from "Customer"
order by "City" asc;

-- Select all invoices with a billing address like “T%”.
Select * from "Invoice"
Where "BillingAddress" like 'T%';

-- Select the name of the longest track.
select "Name" from "Track"
order by length("Name") desc
limit 1;

-- Find the average invoice total.
select avg("Total") from "Invoice";

-- Find the total number of employees in each position.
select "Title",count(*) from "Employee"
group by "Title";

-- Insert two new records into Genre table
insert into "Genre" values (26, 'Edm');
insert into "Genre" values (27, 'Indie');

-- Insert two new records into Employee table
insert into "Employee" values (9, 'David', 'Tran', 'IT Staff', 2, '1991-11-25 00:00:00', '2000-11-25 00:00:00', '123 Fake St', 'Houston', 'TX', 'USA', '77043', '+1 (832) 270-5148', '+1 (832) 277-5148', 'david@chinookcorp.com' );
insert into "Employee" values (10, 'Michelle', 'Tran', 'IT Staff', 5, '1991-11-25 00:00:00', '2000-11-25 00:00:00', '123 Fake St', 'Houston', 'TX', 'USA', '77043', '+1 (832) 270-5148', '+1 (832) 277-5148', 'david@chinookcorp.com' );

-- Insert two new records into Customer table 
insert into "Customer" values (60, 'Callie', 'Ryde', 'Gigashots', '21 Walton Road', 'Allentown', 'Pennsylvania', 'United States', '18105', '+1 (610) 777-4178', '+1 (608) 890-0092', 'cryde0@themeforest.net', 5);
insert into "Customer" values (61, 'Richie', 'Vaulkhard', 'Lajo', '87253 Maywood Parkway', 'Van Nuys', 'California', 'United States', '91406', '+1 (323) 784-9966', '+1 (410) 408-7263', 'rvaulkhard1@barnesandnoble.com', 6);

-- Update Aaron Mitchell in Customer table to Robert Walter
update "Customer" set "FirstName" = 'Robert', "LastName" = 'Walter'
where "FirstName" = 'Aaron' and "LastName" = 'Mitchell';

-- Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
update "Artist" set "Name" = 'CCR'
where "Name" = 'Creedence Clearwater Revival';

-- Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
select c."FirstName", c."LastName", i."InvoiceId" from "Customer" c
inner join "Invoice" i on c."CustomerId" = i."CustomerId";

-- Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
select c."CustomerId" as "Id", c."FirstName", c."LastName", i."InvoiceId", i."Total" from "Customer" c
full outer join "Invoice" i on c."CustomerId" = i."CustomerId";

-- Create a right join that joins album and artist specifying artist name and title.
select alb."Title", art."Name" from "Album" alb
right join "Artist" art on alb."ArtistId" = art."ArtistId";


-- Create a cross join that joins album and artist and sorts by artist name in ascending order.
select alb."Title", art."Name" from "Album" alb
cross join "Artist" art
order by art."Name";

-- Perform a self-join on the employee table, joining on the reportsto column.
select * from "Employee" a, "Employee" b
where a."ReportsTo" = b."EmployeeId";

-- Create a query that shows the customer first name and last name as FULL_NAME (you can use || to concatenate two strings) with the total amount of money they have spent as TOTAL.
select concat(c."FirstName" || ' ' || c."LastName") as "FULL_NAME", i."Total" as "TOTAL" from "Customer" c
join "Invoice" i on c."CustomerId" = i."CustomerId";

-- Create a query that shows the employee that has made the highest total value of sales (total of all invoices).
select c.*, sum(i."Total") from "Customer" c
join "Invoice" i on c."CustomerId" = i."CustomerId"
group by c."CustomerId"
order by sum(i."Total") desc
limit 1;

-- Create a query which shows the number of purchases per each genre. Display the name of each genre and number of purchases. Show the most popular genre first.
select g."Name" "Genre", count(t."GenreId") "Number_Of_Purchases" from "Genre" g
join "Track" t on g."GenreId" = t."GenreId" 
group by g."GenreId"
order by count(g."GenreId") desc;

-- Create a function that returns the average total of all invoices.
create or replace function getAvgInvoiceTotal() 
returns integer 
language plpgsql 
as $avgTotal$
declare
	avgTotal integer;
begin
	select round(sum("Total")/count("Total"), 2) into avgTotal from "Invoice";
	return avgTotal;
end; 
$avgTotal$

select getAvgInvoiceTotal() as invoice_total;

-- Create a function that returns all employees who are born after 1968.
create or replace function getEmployeesAfter1968()
returns setof "Employee"
language plpgsql
as $$
begin
	return query select * from "Employee" where "BirthDate" > timestamp '1968-01-01 00:00:00';
end;
$$

select getEmployeesAfter1968();

drop function getManagerbyemployeeid;
-- Create a function that returns the manager of an employee, given the id of the employee.
create or replace function getManagerbyEmployeeId(emp_id "Employee"."EmployeeId"%type)
returns setof "Employee"
language plpgsql
as $$
declare
	manager_id integer;
begin
	select "ReportsTo" into manager_id from "Employee" where "EmployeeId" = emp_id;
	return query select * from "Employee" where "EmployeeId" = manager_id;
end; 
$$

select getManagerByEmployeeId(6)

-- Create a function that returns the price of a particular playlist, given the id for that playlist.
create or replace function getPlaylistPrice(playlist_id "Playlist"."PlaylistId"%type)
returns integer
language plpgsql
as $$
declare
price integer;
begin
	select sum(t."UnitPrice") into price from "Playlist" p
	join "PlaylistTrack" pt on p."PlaylistId" = pt."PlaylistId"
	join "Track" t on pt."TrackId" = t."TrackId"
	where p."PlaylistId" = playlist_id;
	return price;
end; 
$$

select getPlaylistPrice(1);

