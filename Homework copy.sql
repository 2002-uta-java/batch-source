/*
2.0 Queries and DML 
2.1 SELECT
*/

-- a. Select all records from the Employee table.
select * 
from "Employee";

-- b. Select all records from the Employee table where last name is King.
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
where "Milliseconds" = (select Max("Milliseconds") from "Track");

-- g. Find the average invoice total.
select avg("Total") 
from "Invoice";

-- h. Find the total number of employees in each position.
select "Title", count("Title")
from "Employee" 
group by "Title";


/*
 2.2 INSERT INTO
 */

-- a. Insert two new records into Genre table
insert into "Genre" ("GenreId", "Name") values (26, 'Guler');
insert into "Genre" ("GenreId", "Name") values (27, 'Rose');

-- b. Insert two new records into Employee table
insert into "Employee" ("EmployeeId", "LastName", "FirstName", "Title", "BirthDate", "HireDate", "Address", "City", 
            "State", "Country", "PostalCode", "Phone", "Fax", "Email") values (9, N'John', N'Smith', N'Software Engineer', 
            '1980/6/22', '2015/8/23', N'12400 South 1st Street NW', N'Edmonton', N'AB', N'Canada', N'T5K 6Y3', N'+1 (780) 234-4578',
             N'+1 (780) 400-2086', N'jsmith@chinookcorp.com');
            
insert into "Employee" ("EmployeeId", "LastName", "FirstName", "Title", "BirthDate", "HireDate", "Address", "City", 
            "State", "Country", "PostalCode", "Phone", "Fax", "Email") values (10, N'Amy', N'Johnson', N'Automation Engineer', 
            '1979/5/13', '2015/8/23', N'10001 Amherst Drive', N'Edmonton', N'AB', N'Canada', N'Y4K 9Z8', N'+1 (403) 769-3942',
             N'+1 (403) 446-1036', N'ajohnson@chinookcorp.com');


-- b. Insert two new records into Customer table 
insert into "Customer" ("CustomerId", "FirstName", "LastName", "Address", "City", "Country", "PostalCode", "Phone", "Email", 
            "SupportRepId") values (60, N'Clara', N'Linvelle', N'598 Kirkland Ln', N'Austin', N'Texas', N'78964', 
             N'+1(512)765-8904', N'clara_linvelle@gmail.com', 4);

insert into "Customer" ("CustomerId", "FirstName", "LastName", "Address", "City", "Country", "PostalCode", "Phone", "Email", 
            "SupportRepId") values (61, N'Robert', N'Jackson', N'395 S Cooper', N'Dallas', N'Texas', N'78763', 
             N'+1(832)234-6582', N'robert.jackson@gmail.com', 6);

/*
 2.3 UPDATE
 */
            
-- a. Update Aaron Mitchell in Customer table to Robert Walter
update "Customer"
set "FirstName" = 'Robert', "LastName" = 'Walter'
where "FirstName" = 'Aaron' and "LastName" = 'Mitchell'; 


-- b. Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
update "Artist" 
set "Name" = 'CCR'
where "Name" = 'Creedence Clearwater Revival'

/*
 * 3.0 Joins
 * 
 * 3.1 INNER
 */

-- Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
select "FirstName" as name, "InvoiceId" as id
from "Customer" c 
join "Invoice" i
on c."CustomerId" = i."CustomerId";

/*
 * 3.2 OUTER
 */

-- Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, 
-- invoiceId, and total.
select c."CustomerId" as id, c."FirstName" as name, c."LastName" as lastname, i."Total" as total 
from "Customer" c
full outer join "Invoice" i
on c."CustomerId" = i."CustomerId";

/*
 * 3.3 RIGHT
 */

-- Create a right join that joins album and artist specifying artist name and title.
select art."Name" as name, alb."Title" as title 
from "Album" alb
right outer join "Artist" art
on alb."ArtistId" = art."ArtistId";

/*
 * 3.4 CROSS
 */

-- Create a cross join that joins album and artist and sorts by artist name in ascending order.
select *
from "Album" alb
cross join "Artist" art 
order by art."Name" asc;

/*
 * 3.5 SELF
 */

-- Perform a self-join on the employee table, joining on the reportsto column.	
select *
from "Employee" e 
join "Employee" e2 on e."ReportsTo" = e2."ReportsTo" 

/*
 * 3.6 Joined Queries
 */
-- Create a query that shows the customer first name and last name as FULL_NAME 
-- (you can use || to concatenate two strings) with the total amount of money they have spent as TOTAL.

select c."FirstName" ||' '|| c."LastName" as FULL_NAME, i."Total" as TOTAL
from "Customer" c 
join "Invoice" i
on c."CustomerId" = i."CustomerId"; 

-- Create a query that shows the employee that has made the highest total value of sales (total of all invoices).
select max(i."Total") 
from "Employee" e 
cross join "Invoice" i

-- Create a query which shows the number of purchases per each genre. Display the name of each genre and number
-- of purchases. Show the most popular genre first.
select g."Name", il."Quantity" 
from "Genre" g 
cross join "InvoiceLine" il

/*
 * 4.0 User Defined Functions
 */
-- Create a function that returns the average total of all invoices.
create or replace function find_avg_total()
returns numeric
language plpgsql
as $$
declare 
	avg_total numeric;
begin
	select avg("Total") into avg_total
	from "Invoice";
	return avg_total;
end
$$

select find_avg_total();

-- Create a function that returns all employees who were born after 1968.
create or replace function find_employees_born_after()
returns table (names text)
language plpgsql
as $$
begin
	return query select "FirstName" ||' '|| "LastName" as name from "Employee" where "BirthDate" >= '01-01-1969';
end
$$

select find_employees_born_after();

-- Create a function that returns the manager of an employee, given the id of the employee.
create or replace function find_employees(empl_id numeric)
returns table (names text)
language plpgsql
as $$
begin
	return query select "FirstName" ||' '|| "LastName" as name from "Employee" e where e."EmployeeId" = (select "ReportsTo" from "Employee" e2 where e2."EmployeeId" = empl_id);
end
$$

select find_employees(6);

-- Create a function that returns the price	 of a particular playlist, given the id for that playlist.
create function find_particular_playlist(playlist_id numeric)
returns numeric
language plpgsql
as $$
declare
playlist_id numeric;
begin
	select sum(tr."UnitPrice"), pt."PlaylistId" 
	from "PlaylistTrack" pt 
	join "Track" tr
	on pt."TrackId" = tr."TrackId"
	group by pt."PlaylistId"; 
return playlist_id; 
end
$$

select find_particular_playlist(5);
