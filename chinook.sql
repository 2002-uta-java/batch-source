-------2.0 queries and DML----------

--2.1 select-------------------
--a. Select all records from the Employee table.
-- select all columns from employee table
select * from "Employee";

--b. Select all records from the Employee table where last name is King.
-- select all columns from eployee table were the employee last name is King
select *
from "Employee"
where "LastName" = 'King';

--c. Select all albums in Album table and sort result set in descending order by title.
-- select all columns from album table and sort the table based on column title by desc order
select *
from "Album" a
order by a."Title" desc;

--d. Select first name from Customer and sort result set in ascending order by city.
-- select firstname column from customer and sort the result set in ascending order of city 
select "FirstName"
from "Customer" c
order by c."City" asc;

--e. Select all invoices with a billing address like “T%”.
-- select all columns from invoice with a billing address starting with letter T
select *
from "Invoice" i 
where i."BillingAddress" like 'T%';

--f. Select the name of the longest track.
-- select the column name from Track get the longest track in miliseconds to result set
select "Name"
from "Track"
where "Milliseconds" =
(select max("Milliseconds") from "Track");

--g. Find the average invoice total.
-- using avg function to calculate the average of total from invoice
select avg("Total")
from "Invoice";

--h. Find the total number of employees in each position.
-- using count function to count title and return columns title and count title as result set and group by title
select "Title" , count("Title")
from "Employee" e
group by e."Title";


--2.2 INSERT INTO
--a. Insert two new records into Genre table
-- insert into genre wit the id as 6 and name as KPop
insert into "Genre" ("GenreId", "Name") values (26, N'KPop');	

--b. Insert two new records into Employee table
-- insert a new records of employee with values associate with all the columns in employee table
insert into "Employee" ("EmployeeId", "LastName", "FirstName", "Title", "BirthDate", "HireDate", "Address", "City", "State", "Country", 
						"PostalCode", "Phone", "Fax", "Email") 
values (9, N'Nguyen', N'Lee', N'IT Staff', '1992/2/20', '2004/8/14', N'1940 J J Pearce', N'Richardson', N'TX', N'United States', N'75041', 
		N'+1 (469) 435-9492', N'+1 (972) 411-7191', N'leenguyen@gmail.com');
	
insert into "Employee" ("EmployeeId", "LastName", "FirstName", "Title", "BirthDate", "HireDate", "Address", "City", "State", "Country", 
						"PostalCode", "Phone", "Fax", "Email") 
values (10, N'Nguyen', N'Ly', N'IT Staff', '1992/2/20', '2004/8/14', N'1940 J J Pearce', N'Richardson', N'TX', N'United States', N'75041', 
		N'+1 (469) 415-9492', N'+1 (972) 471-7191', N'lynguyen@gmail.com');
	
--c. Insert two new records into Customer table 
-- insert a new records of customer into customer table with values associate with all the columns in customer table
insert into "Customer" ("CustomerId", "FirstName", "LastName", "Company", "Address", "City", "State", "Country", "PostalCode", 
						"Phone", "Fax", "Email", "SupportRepId") 
values (60, N'Lee', N'Nguyen', N'Revature', N'1940 J J Pearce', N'Richardson', N'TX', N'United States', N'75041', 
			N'+1 (469) 435-9492', N'+1 (972) 411-7191', N'leenguyen@gmail.com', 3);
		
insert into "Customer" ("CustomerId", "FirstName", "LastName", "Company", "Address", "City", "State", "Country", "PostalCode", 
						"Phone", "Fax", "Email", "SupportRepId") 
values (61, N'Ly', N'Nguyen', N'Revature', N'1940 J J Pearce', N'Richardson', N'TX', N'United States', N'75041', 
			N'+1 (469) 415-9492', N'+1 (972) 471-7191', N'lynguyen@gmail.com', 2);


--2.3 UPDATE
--a. Update Aaron Mitchell in Customer table to Robert Walter
-- update customer table with the column firstname aaron and last name mitchel and replace that row with first name Robert and lastname Walter 
update "Customer" c
set "FirstName" = 'Robert', "LastName" = 'Walter'
where c."LastName" = 'Mitchell' and c."FirstName" = 'Aaron';
		
--b. Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
-- update a Name Creedence Clearwater Revival in table Artist to Name CCR
update "Artist" a
set "Name" = 'CCR'
where a."Name" = 'Creedence Clearwater Revival';


--3.0 Joins
--In this section you will be working with combining various tables through the use of joins. You will work with outer, inner, right, left, cross, and self joins.
--3.1 INNER
--a. Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
-- join table Customer and Invoice on customerId from customer table and invoice table and return result set matching customerId 
-- from both Customer and Invoice and with column FirstName LastName InvoiceId
select "FirstName" , "LastName" , "InvoiceId"
from "Customer" c
inner join "Invoice" i 
on c."CustomerId" = i."CustomerId";

--3.2 OUTER
--a. Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
-- using full outer join to return every row customer or invoice table
-- join customer table and invoice on customerid of both table
-- return result set columns customerId, firstname, lastname, invoiceid, total
select c."CustomerId", "FirstName", "LastName", "InvoiceId", "Total"
from "Customer" c
full outer join "Invoice" i 
on c."CustomerId" = i."CustomerId";

--3.3 RIGHT
--a. Create a right join that joins album and artist specifying artist name and title.
-- using right join to return all the rows from the right table, which is Artist
-- right join album and artist on artistId from both table
-- return result set columns name and title
select "Name", "Title"
from "Album" al 
right join "Artist" ar
on ar."ArtistId" = al."ArtistId"; 

--3.4 CROSS
--a. Create a cross join that joins album and artist and sorts by artist name in ascending order.
-- cross join just combination of every rows from two table
-- cross join album and artist table and result set of all columns  order by Name and in asc order
select *
from "Album" al 
cross join "Artist" ar
order by ar."Name", al."Title" asc; 

--3.5 SELF
--a. Perform a self-join on the employee table, joining on the reportsto column.
-- selft join employee table on reportto column
-- self join is joining a table to itself based on a column
select *
from "Employee" e 
inner join "Employee" e1 
on e."ReportsTo" = e1."ReportsTo";

--3.6 Joined Queries
--a. Create a query that shows the customer first name and last name as FULL_NAME (you can use || to concatenate two strings) 
-----with the total amount of money they have spent as TOTAL.
-- result set is concat firstname and lastname as fullname and sum total from Invoice
-- group by customerId
-- join table customer and invoice on customerId
select c."FirstName" ||' ' || c."LastName" as "FULL_NAME", sum(i."Total")
from "Customer" c 
inner join "Invoice" i
on c."CustomerId" = i."CustomerId"
group by c."CustomerId";


--b. Create a query that shows the employee that has made the highest total value of sales (total of all invoices).
-- join table employee and customer table on supportRepId and 
-- join table Invoice on CustomerId = customerId, this way I join 3 table together
-- limit by 1 to get max sum total
-- result set columns first name and max sum total
select e."FirstName", sum(i."Total") as sumtotal
from "Employee" e 
inner join "Customer" c
on e."EmployeeId" = c."SupportRepId"
inner join "Invoice" i 
on i."CustomerId" = c."CustomerId"
group by e."FirstName" 
limit 1;


--c. Create a query which shows the number of purchases per each genre. Display the name of each genre and number of purchases. Show the most popular genre first.
-- full outer join table to show all track purchase including null purchases
-- full counter join track, invoiceline, and genre
-- group by genre Name and order by purchases desc order
select g."Name", sum(il."Quantity" ) as purchases
from "Genre" g 
full outer join "Track" t
on g."GenreId" = t."GenreId" 
full outer join "InvoiceLine" il
on t."TrackId" = il."TrackId"
group by g."Name" 
order by purchases desc nulls last;

--4.0 User Defined Functions
--a. Create a function that returns the average total of all invoices.
-- create a function to calculate average invoice and return numeric datatype for average
create function daAvgFunct() 
returns numeric as $$ 
select avg("Total") 
from "Invoice";
$$ language sql;

--select daAvgFunct();

--b. Create a function that returns all employees who are born after 1968.
-- create function to return eployee that born after 1968
create or replace function bornAfter1968()
returns table (names text) as $$
select "FirstName" || ' ' || "LastName"
from "Employee" e 
where e."BirthDate" > '12/31/1968';
$$ language sql;

--select bornAfter1968();

--c. Create a function that returns the manager of an employee, given the id of the employee.
-- create function mangerOfEmployee that take in an employeeID and return that employeeID manager name
create or replace function managerOfEmp(empId int)
returns text as $$
select "FirstName" || ' ' || "LastName"
from "Employee" e 
where e."EmployeeId" =
(
select "ReportsTo" 
from "Employee" e2 
where e2."EmployeeId" = empId);
$$ language sql;

--select managerOfEmp(3);

--d. Create a function that returns the price of a particular playlist, given the id for that playlist.
-- join track and playlistTrack on trackId to get the track associate to the playlist id
-- sum all the track associate to the given track id
-- return the total sum price of a particular playlist given by the id
create or replace function priceOfPlaylist(playId int)
returns numeric 
as $$
select sum(t."UnitPrice")
from "Track" t 
join "PlaylistTrack" pt
on t."TrackId" = pt."TrackId" 
where pt."PlaylistId" = playId;
$$ language sql;

select priceOfPlaylist(1);
