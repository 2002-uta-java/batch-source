-- 2.1 SELECT
-- a. Select all records from the Employee table.
select *
from "Employee";
-- b. Select all records from the Employee table where the last name is King.
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
-- e. Select all invoices with a billing address like "T%".
select *
from "Invoice"
where "BillingAddress" like 'T%';
-- f. Select the name of the longest track.
select "Name", "Milliseconds"
from "Track"
where "Milliseconds" =
	(select min("Milliseconds")
	from "Track");
-- g. Find the average invoice total.
select avg("Total")
from "Invoice";
-- h. Find the total number of emplyees in each position.
select count("EmployeeId")
from "Employee";

-- 2.2 INSERT INTO
-- Insert two new records into Genre table
insert into "Genre" values (26, 'Modern');
insert into "Genre" values (27, 'Dubstep');
-- Insert two new records into Employee table
insert into "Employee" values (9, 'Tang', 'Michael', 'COO', 1, '1/1/2000', '1/2/2000', '123 Fake St', 'Fake City', 'Florida', 'USA', '33072', '9548349259', 'Fax', 'fake@gmail.com');
insert into "Employee" values (10, 'Junior', 'Morton', 'COV', 1, '6/6/1996', '6/6/2006', '666 Fire St', 'Fire City', 'Virginia', 'USA', '66651', '6661800412', 'Fax2', 'fake2@gmail.com');
-- Insert two new records into Customer table
select *
from "Customer";
insert into "Customer" values (60, 'King', 'Martin', null, '345 Fake St', 'Fake City 2', 'Washington', 'USA', '43071', '9548349259', null, 'fake2@gmail.com');
insert into "Customer" values (61, 'Queen', 'May', null, '456 Fake St', 'Fake City 3', 'Nevada', 'USA', '40123', '9504219002', null, 'fake3@gmail.com');

-- 2.3 UPDATE
-- a. Update Aaron Mitchell in Customer table to Robert Walter.
update "Customer" 
set "FirstName" = 'Robert', "LastName" = 'Walter'
where "FirstName" = 'Aaron' and "LastName" = 'Mitchell';
-- b. Update name of artist in the Artist table "Creedence Clearwater Revival" to "CCR".
update "Artist"
set "Name" = 'CCR'
where "Name" = 'Creedence Clearwater Revival';

-- 3.1 Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
select c."FirstName", c."LastName", i."InvoiceId"
from "Customer" c
inner join "Invoice" i
on c."CustomerId" = i."CustomerId";

-- 3.2 Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
select c."CustomerId", c."FirstName", c."LastName", i."InvoiceId", i."Total"
from "Customer" c
full outer join "Invoice" i
on c."CustomerId" = i."CustomerId";

-- 3.3 Create a right join that joins album and arist specifying artist name and title.
select a2."Name", a1."Title" 
from "Album" a1
right join "Artist" a2
on a1."ArtistId" = a2."ArtistId";

-- 3.4 Create a cross join that joins album and artist and sorts by artist name in ascending order.
select *
from "Album" a1
cross join "Artist" a2
order by a2."Name" asc;

-- 3.5 Perform a self-join on the employee table, joining on the reportsto column.
select *
from "Employee" e1
inner join "Employee" e2
on e1."ReportsTo" = e2."ReportsTo";

-- 3.6 JOINED QUERIES
-- a. Create a query that shows the customer first name and last name as FULL_NAME (you can use|| to concatenate two strings) with the total amount of money they have spent as TOTAL.
select c."FirstName" || c."LastName" "FULL_NAME", sum(i."Total") "TOTAL"
from "Customer" c
inner join "Invoice" i
on c."CustomerId" = i."CustomerId"
group by c."CustomerId";

-- b. Create a query that shows the employee that has made the highest total value of sales (total of all invoices).
select sum(i."Total") "Total_Spending", c."FirstName" || c."LastName" "Name"
from "Customer" c
inner join "Invoice" i
on i."CustomerId" = c."CustomerId"
where i."CustomerId" = 
	(select i."CustomerId" as "Max_Id"
	from "Invoice" i
	group by i."CustomerId"
	order by sum(i."Total" ) desc
	limit 1)
group by c."CustomerId"
limit 1;

-- c. Create a query which shows the number of purchases per each genre. Display the name of eachgenre and number of purchases. Show the most popular genre first
select g."Name", sum(i."Quantity") "Total_Sold"
from "InvoiceLine" i
inner join "Genre" g
on i."InvoiceId" = g."GenreId"
group by g."GenreId"
order by sum(i."Quantity") desc;

-- 4.0 USER DEFINED FUNCTIONS
-- a. Create a function that returns the average total of all invoices.
create or replace function return_avg()
returns numeric(7, 2)
language plpgsql
as $$
declare
	avg_sal numeric(7, 2);
begin
	select round(avg("Total"), 2) into avg_sal
    from "Invoice";
    return avg_sal;
end
$$

-- b. Create a function that returns all employees who are born after 1968.
create or replace function zoomers()
returns setof "Employee"
language plpgsql
as $$
declare
begin 
	return query 
	select *
	from "Employee"
	where "BirthDate" >= timestamp '1969-1-1 00:00:00';
end
$$

-- c. Create a function that returns the manager of an employee, given the id of the employee.
create or replace function get_manager(empl "Employee"."EmployeeId"%type)
returns setof "Employee"."FirstName"%type
language plpgsql
as $$
begin
	return query
	select e2."FirstName"
	from "Employee" e
	inner join "Employee" e2
	on e."ReportsTo" = e2."EmployeeId"
	where empl = e."EmployeeId";
end
$$

-- d. Create a function that returns the price of a particular playlist, given the id for that playlist.

















