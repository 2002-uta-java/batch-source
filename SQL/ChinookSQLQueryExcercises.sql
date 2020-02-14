/*
*2.0 Queries and DML
*---------------
*/
 
-- 2.1 SELECT --
-- a. Select all records from the Employee table. 
select * 
from "Employee";

-- b. Select all records from the Employee table where last name is King. 
select *
from "Employee"
where "LastName"='King';

-- c. Select all albums in Album table and sort result set in descending order by title.
select *
from "Album" a
order by a."Title" desc;

-- d. Select first name from Customer and sort result set in ascending order by city.
select "FirstName" from "Customer" c 
order by c."City" asc;

-- e. Select all invoices with a billing address like "T%".
select * from "Invoice" i 
where i."BillingAddress" like 'T%'

-- f. Select the name of the longest track.
select "Name"
from "Track"
where "Milliseconds" = 
	(select max(t."Milliseconds") as Longest 
		from "Track" t);

-- g. Find the average invoice total.
select  to_char(AVG ("Total"),'99.99')    
   AS average_invoice_total
from "Invoice";

-- h. Find the total number of employees in each position.
select "Title", COUNT(*)  
from "Employee" group by "Title";

-- 2.2 INSERT INTO --
-- a. Insert two new records into Genre table
insert into "Genre" ("GenreId", "Name" ) values (26, N'Disco');
insert into "Genre" ("GenreId", "Name" ) values (27, N'Orchestra');

-- b. Insert two new records into Employee table
INSERT INTO "Employee" ("EmployeeId", "LastName", "FirstName", "Title", "ReportsTo", "BirthDate", "HireDate", "Address", "City", "State", "Country", "PostalCode", "Phone", "Fax", "Email") VALUES (9, N'Breena', N'Adamsen', N'IT Staff', 6, '2019/3/7', '2003/10/17', N'6 Old Shore Crossing', N'Simi Valley', N'CA', N'United States', N'93094', N'1 (780) 836-9987', N'1 (805) 116-6205', N'badamsen3@webmd.com');
INSERT INTO "Employee" ("EmployeeId", "LastName", "FirstName", "Title", "ReportsTo", "BirthDate", "HireDate", "Address", "City", "State", "Country", "PostalCode", "Phone", "Fax", "Email") VALUES (10, N'Leandra', N'Croston', N'IT Staff', 6, '2019/4/9', '2019/5/19', N'4 Debs Park', N'Jersey City', N'NJ', N'United States', N'7305', N'1 (201) 495-7400', N'1 (212) 202-3724', N'Icroston0@desdev.cn');

-- c. Insert two new records into Customer table 
INSERT INTO "Customer" ("CustomerId", "FirstName", "LastName", "Address", "City", "Country", "PostalCode", "Phone", "Email", "SupportRepId") VALUES (60, N'Lola', N'Wheeler', N'412 Main Road', N'Bielefield', N'Germany', N'70174', N'+49 0711 8459359', N'lolawheeler@surfeu.de', 5);
INSERT INTO "Customer" ("CustomerId", "FirstName", "LastName", "Address", "City", "State", "Country", "PostalCode", "Phone", "Email", "SupportRepId") VALUES (61, N'Francis', N'Trumble', N'1498 Oak Street Blvd', N'Montreal', N'QC', N'Canada', N'458754', N'+1 (312) 656-4545', N'ftrumble@gmail.com', 3);

-- 2.3 UPDATE --
-- a. Update Aaron Mitchell in Customer table to Robert Walter
update "Customer" 
set "FirstName" = 'Robert', "LastName" = 'Walter'
where "FirstName" = 'Aaron' and  "LastName" = 'Mitchell';

-- b. Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
update "Artist"  
set "Name" = 'CCR'
where "Name" = 'Creedence Clearwater Revival'

/*
* 3.0 Joins
*----------
*/
-- 3.1 INNER --
-- a. Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
select c. "FirstName" || c. "LastName" as Name, i."InvoiceId" 
from "Customer" c  
join "Invoice" i on c."CustomerId" = i."InvoiceId";

-- 3.2 OUTER --
-- a. Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
select c."CustomerId", c."FirstName" , c."LastName", i."InvoiceId", i."Total" 
from "Customer" c  
full outer join "Invoice" i
on c."CustomerId" = i."InvoiceId";

-- 3.3 RIGHT
-- a. Create a right join that joins album and artist specifying artist name and title.
select "Album"."Title", "Artist"."Name" 
from "Artist" 
right outer join "Album"
on "Album"."ArtistId" = "Artist"."ArtistId" 

--3.4 CROSS
-- a. Create a cross join that joins album and artist and sorts by artist name in ascending order.
select *
from "Album"
	cross join "Artist"
order by "Artist"."Name" asc;

-- 3.5 SELF
-- a. Perform a self-join on the employee table, joining on the reportsto column.	
select *
from "Employee" e 
join "Employee" e1 on e."ReportsTo" = e1."ReportsTo";

--3.6 Joined Queries 
-- a. Create a query that shows the customer first name and last name as FULL_NAME (you can use || to concatenate two strings) with the total amount of money they have spent as TOTAL.
select c."FirstName" || ' ' || c."LastName" as "FULL_NAME",
sum(i."Total")
from "Customer" c
join "Invoice" i on c."CustomerId" = i."CustomerId"
group by c."CustomerId";

-- b. Create a query that shows the employee that has made the highest total value of sales (total of all invoices).
-- study views
create view employee_totalSales as
select e."FirstName" || ' ' ||e."LastName" as "FULL_NAME",
SUM(i."Total") as "Total Sales"
from "Employee" e  
join "Customer" c on (e."EmployeeId" = c."SupportRepId")
join "Invoice" i on (c."CustomerId" = i."CustomerId")
group by e."EmployeeId";

select * from employee_totalSales;

-- et."FULL_NAME", max("Total Sales")

--where "Milliseconds" = 
--	(select max(t."Milliseconds") as Longest 
--		from "Track" t);

select * -- Give the row where 833.04 is equal to the max 
from employee_totalsales et
-- 1. In the table employee_totalsales it looks at the total sales column and 2. excecutes the max function in the row and retrieves the name and the total_sales amount
where "Total Sales" = 
	(select max("Total Sales") from employee_totalsales);


--select e."FirstName" , max("Total") as "Total"
--from "Employee" as e
--	join "Customer" as c on e."EmployeeId" = c."CustomerId" 
--	join "Invoice" as i on i."CustomerId" = c."CustomerId" 
--group by e."EmployeeId";


-- c. Create a query which shows the number of purchases per each genre. Display the name of each genre and number of purchases. Show the most popular genre first.
-- select genre name and total amount of sales in genre 
select gr."Name",
sum(il."Quantity") as "sold"
from "InvoiceLine" il
left join "Track" tr on il."TrackId" = tr."TrackId"
right join "Genre" gr on tr."GenreId" = gr."GenreId"
group by gr."GenreId"
order by sold desc nulls last;

/*
*4.0 User Defined Functions 
*--------------------------
*/

-- a. Create a function that returns the average total of all invoices.
create function averagetotalofallinvoices()
returns numeric as $$
select avg(i."Total")
from "Invoice" i;
$$ language sql;

select averagetotalofallinvoices();

-- b. Create a function that returns all employees who are born after 1968.
create function bornafter1968()
returns table (names text) as $$
select e."FirstName" || ' ' || e."LastName"
from "Employee" e
where e."BirthDate" > '12/31/1968';
$$ language sql;

select bornafter1968();

-- c. Create a function that returns the manager of an employee, given the id of the employee.
create function employeemanager(n int)
returns text as $$
select e."FirstName" || ' ' || e."LastName"
from "Employee" e
where e."EmployeeId" = (
	select e2."ReportsTo" 
	from "Employee" e2 
	where e2."EmployeeId" = n
	);
$$ language sql;

select employeemanager(9);

-- d. Create a function that returns the price of a particular playlist, given the id for that playlist.
create function priceofplaylist(plid int)
returns numeric as $$
select sum(t."UnitPrice")
from "PlaylistTrack" pt
join "Track" t on t."TrackId" = pt."TrackId"
where pt."PlaylistId" = plid;
$$ language sql;

select priceofplaylist(1);
