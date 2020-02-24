------------- 2.1 SELECT--------------------
-- a. select all records from the Employee table
select *
from "Employee";

-- b. select all records from Employee table where last name is King
select *
from "Employee"
where "LastName" = 'King';

-- c. Select all albums in Album table and sort result set in descending order by title.
select *
from "Album"
order by "Title" desc;

-- d. Select first name from Customer and sort result set in ascending order by city.
select "FirstName"
from "Customer"
order by "City" asc;

-- e. Select all invoices with a billing address like “T%”.
select *
from "Invoice"
where "BillingAddress" like 'T%';

-- f. Select the name of the longest track.
select "Name"
from "Track"
order by "Milliseconds" desc
limit 1;

-- g. Find the average invoice total.
select
	avg("Total")
from "Invoice";

-- h. Find the total number of employees in each position.
select
	count(*)
from "Employee"
group by "Title";

------------2.2 INSERT INTO -----------------------
-- a. Insert two new records into Genre table
insert into "Genre" values (26, 'EDM');
insert into "Genre" values (27, 'Rap');


-- b. Insert two new records into Employee table
insert into "Employee" values (9, 'Evans', 'Zayn', 'CEO', null, '1995-12-04', '2020-02-03', '123 Main Street', 'Dallas', 'TX', 'United States', 'T4Z 3K4', '+1 (403) 495-2894', '+1 (403) 495-2859', 'zevans@chinookcorp.com');
insert into "Employee" values (10, 'Jones', 'Bob', 'Secretary', 1, '1967-11-18', '2020-02-03', '523 S Monroe Street', 'Dallas', 'TX', 'United States', 'T4J 4P8', '+1 (403) 495-8319', '+1 (403) 495-8394', 'bjones@chinookcorp.com');

-- c. Insert two new records into Customer table
insert into "Customer" ("CustomerId", "FirstName", "LastName", "Email") values (60, 'Jack', 'Doss', 'titanic@chinookcorp.com');
insert into "Customer" ("CustomerId", "FirstName", "LastName", "Email") values (61, 'Jimbo', 'Slice', 'slicenator@chinookcorp.com');

--------------2.3 UPDATE-----------------------------
-- a. Update Aaron Mitchell in Customer table to Robert Walter
update "Customer"
set "FirstName" = 'Robert', "LastName" = 'Walter'
where "CustomerId" = 32;

-- b. Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
update "Artist"
set "Name" = 'CCR'
where "Name" = 'Creedence Clearwater Revival';

----------------JOINS-------------------------------------
-------------3.1 INNER------------------------------------
-- a. Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
select c."FirstName", c."LastName", i."InvoiceId"
from "Customer" c
inner join "Invoice" i
on c."CustomerId" = i."CustomerId";

----------------3.2 OUTER-----------------------------------
-- a. Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
select c."CustomerId", c."FirstName", c."LastName", i."InvoiceId", i."Total"
from "Customer" c
full outer join "Invoice" i
on c."CustomerId" = i."CustomerId";

------------------3.3 RIGHT---------------------------------------
-- a. Create a right join that joins album and artist specifying artist name and title.
select a."Title", ar."Name"
from "Album" a
right join "Artist" ar
on a."ArtistId" = ar."ArtistId";

-------------------3.4 CROSS---------------------------------------
-- a. Create a cross join that joins album and artist and sorts by artist name in ascending order.
select *
from "Album"
cross join "Artist" a
order by a."Name" asc;

--------------------3.5 SELF-------------------------------------------
-- a. Perform a self-join on the employee table, joining on the reportsto column
select *
from "Employee" e
join "Employee" f
on e."ReportsTo" = f."ReportsTo";

-------------------3.6 JOINED QUERIES-----------------------------------
-- a. Create a query that shows the customer first name and last name as FULL_NAME (you can use || to concatenate two strings) with the total amount of money they have spent as TOTAL.
select "FirstName" || "LastName" as "FULL_NAME", "Total" as "TOTAL"
from "Customer" c
join "Invoice" i
on c."CustomerId" = i."CustomerId";


-- b. Create a query that shows the employee that has made the highest total value of sales (total of all invoices).
select e."FirstName" || ' ' || e."LastName" as "Employee Name", sum("Total") as "Total Value of Sales"
from "Invoice" inv
     join "Customer" c on c."CustomerId" = inv."CustomerId"
     join "Employee" e on c."SupportRepId" = e."EmployeeId"
group by "Employee Name" order by "Total Value of Sales" desc limit 1;

-- c. Create a query which shows the number of purchases per each genre. Display the name of each genre and number of purchases. Show the most popular genre first.
select g."Name", sum("Quantity") as "Number_Of_Purchases"
from "InvoiceLine" i
join "Track" t on i."TrackId" = t."TrackId"
join "Genre" g on g."GenreId" = t."GenreId"
group by g."GenreId" order by "Number_Of_Purchases" desc;

-------------------4.0 USER DEFINED FUNCTIONS------------------------------
-- a. Create a function that returns the average total of all invoices.
create or replace function get_avg_total_invoice()
returns numeric(4,2)
language plpgsql
as $$
declare 
	avg_total_invoice numeric(4,2);
begin 
	select round(avg(i."Total")) into avg_total_invoice
	from "Invoice" i;
	return avg_total_invoice;
end
$$

select get_avg_total_invoice();

-- b. Create a function that returns all employees who were born after 1968.
create or replace function get_empls_born_after_1968()
returns integer
language plpgsql
as $$
declare 
	empls_born_after_1968 integer;
begin
	select * into empls_born_after_1968
	from "Employee" e
	where e."BirthDate" > '1968-01-01';
	return empls_born_after_1968;
end
$$

select get_empls_born_after_1968();

-- c. Create a function that returns the manager of an employee, given the id of the employee.
create or replace function get_manager(empl_id int)
returns text
language plpgsql
as $$
begin
	select "FirstName" || ' ' || "LastName"
	from "Employee"
	where "EmployeeId" = (
		select "ReportsTo"
		from "Employee"
		where "EmployeeId" = empl_id)
end
$$

select get_manager(5);

-- d. Create a function that returns the price of a particular playlist, given the id for that playlist.
create or replace function get_price_playlist(playlist_id int)
returns numeric
language plpgsql
as $$
begin
	select sum("UnitPrice")
	from "PlaylistTrack" p
	join "Track" t
	on p."TrackId" = t."TrackId"
	where "PlaylistId" = playlist_id);
end
$$

select get_price_playlist(5);
