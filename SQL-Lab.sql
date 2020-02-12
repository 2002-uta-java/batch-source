--2.0 Queries and DML
--2.1 SELECT
-- a. Select all records from the Employee table.
select * from "Employee";

-- b. Select all records from the Employee table where last name is King.
select * from "Employee" e where "LastName" = 'King';

-- c. Select all albums in Album table and sort result set in descending order by title.
select * from "Album" a2 order by "Title" desc;

-- d. Select first name from Customer and sort result set in ascending order by city.
select "FirstName" from "Customer" c order by "City" ;

-- e. Select all invoices with a billing address like “T%”.
select * from "Invoice" where "BillingAddress" like 'T%';

-- f. Select the name of the longest track.
select "Name" from "Track" order by "Milliseconds" desc limit 1;
select "Name" from "Track" where "Milliseconds" = (select max("Milliseconds" ) from "Track");

-- g. Find the average invoice total.
select avg("Total" ) from "Invoice" i;

-- h. Find the total number of employees in each position.
select "Title", count("EmployeeId" ) from "Employee" e group by "Title";

--2.2 INSERT INTO
-- a. Insert two new records into Genre table
insert into "Genre" ("GenreId", "Name" ) values (300, 'Indie');
insert into "Genre" ("GenreId", "Name" ) values (301, 'Black Metal');

-- b. Insert two new records into Employee table
insert into "Employee" ("EmployeeId", "FirstName", "LastName") values (250, 'Dave', 'Thomas');
insert into "Employee" ("EmployeeId", "FirstName", "LastName") values (251, 'Bob', 'Smith');

-- c. Insert two new records into Customer table
insert into "Customer" ("CustomerId","FirstName","LastName","Email")
	values (100, 'Leslie', 'Knope', 'poehler@nbc.com');
insert into "Customer" ("CustomerId","FirstName","LastName","Email")
	values (101, 'Ann', 'Perkins', 'jones@nbc.com');

--2.3 UPDATE
-- a. Update Aaron Mitchell in Customer table to Robert Walter
update "Customer" set ("FirstName", "LastName") = ('Robert', 'Walter') 
	where "LastName" = 'Mitchell' and "FirstName" = 'Aaron';

-- b. Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
update "Artist" set "Name" = 'CCR' where "Name" = 'Creedence Clearwater Revival';

--3.0 Joins
--In this section you will be working with combining various tables through the use of joins. 
--You will work with outer, inner, right, left, cross, and self joins.
--3.1 INNER
--Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
select c."LastName" , c."FirstName", i."InvoiceId" 
	from "Customer" c 
	inner join "Invoice" i 
	on c."CustomerId" = i."CustomerId" ;

--3.2 OUTER
--Create an outer join that joins the customer and invoice table, 
--specifying the CustomerId, firstname, lastname, invoiceId, and total.
select c."CustomerId", c."FirstName", c."LastName", i."InvoiceId", i."Total" 
	from "Customer" c 
	full outer join "Invoice" i 
	on c."CustomerId" = i."CustomerId" ;

--3.3 RIGHT
--Create a right join that joins album and artist specifying artist name and title.
select ar."Name" , al."Title" 
	from "Album" al 
	right join "Artist" ar 
	on al."ArtistId" = ar."ArtistId" ;

--3.4 CROSS
--Create a cross join that joins album and artist and sorts by artist name in ascending order.
select ar."Name"
	from "Album" al 
	cross join "Artist" ar 
	--where al."ArtistId" = Ar."ArtistId" 
	order by ar."Name";

--3.5 SELF
--Perform a self-join on the employee table, joining on the reportsto column.
select e."FirstName" || e."LastName" as "employee", r."FirstName" || r."LastName" as "manager"
	from "Employee" e 
	join "Employee" r 
	on e."ReportsTo" = r."EmployeeId" ;

--3.6 Joined Queries

-- a. Create a query that shows the customer first name and last name as FULL_NAME (you can use || to concatenate 
-- two strings) with the total amount of money they have spent as TOTAL.
select c."FirstName" || ' ' || c."LastName" as FULL_NAME , sum(i."Total") 
	from "Customer" c left join "Invoice" i on c."CustomerId" = i."CustomerId"
	group by c."CustomerId" ;

-- b. Create a query that shows the employee that has made the highest total value of sales (total of all invoices).
select e."FirstName" , e."LastName" , sum(i."Total" ) as t from "Invoice" i
left join "Customer" c on i."CustomerId" = c."CustomerId"
left join "Employee" e on c."SupportRepId" = e."EmployeeId" 
	group by e."EmployeeId" order by t desc limit 1;

	
-- c. Create a query which shows the number of purchases per each genre. Display the name of each genre and number of purchases. Show the most popular genre first.
select g."Name", sum(il."Quantity") as n from "InvoiceLine" il join "Track" t on il."TrackId" = t."TrackId" 
	join "Genre" g on t."GenreId" = g."GenreId" 
	group by g."GenreId" 
	order by n desc;

--4.0 User Defined Functions
-- a. Create a function that returns the average total of all invoices.
-- b. Create a function that returns all employees who are born after 1968.
-- c. Create a function that returns the manager of an employee, given the id of the employee.
-- d. Create a function that returns the price of a particular playlist, given the id for that playlist.





	

