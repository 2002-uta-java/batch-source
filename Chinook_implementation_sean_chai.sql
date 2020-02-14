--2.1 SELECT

--1.Select all records from the Employee table.
select* from "Employee";
--2.Select all records from the Employee table where last name is King.
select* from "Employee" where "LastName"= 'King';
--3.Select all albums in Album table and sort result set in descending order by title.
select* from "Album" order by "Title" desc;
--4.Select first name from Customer and sort result set in ascending order by city.
select "FirstName" from "Customer" order by "City" asc;
--5.Select all invoices with a billing address like “T%”.
select "BillingAddress" from "Invoice" where "BillingAddress" like 'T%';
--6.Select the name of the longest track.
select "Name" from "Track" where "Milliseconds" = (select max("Milliseconds") from "Track");
--7.Find the average invoice total.
select avg("Total") from "Invoice";
--8.Find the total number of employees in each position.
select "Title", count("Title") from "Employee" group by "Title";

--2.2 INSERT INTO
--Insert two new records into Genre table
INSERT into "Genre" ("GenreId","Name") values (26,'Mongolian Trip Hop'),(27,'Swedish Downtempo');
--Insert two new records into Customer table
INSERT INTO "Customer" ("CustomerId", 
						"FirstName", 
						"LastName", 
						"Company", 
						"Address", 
						"City", 
						"State", 
						"Country", 
						"PostalCode", 
						"Phone", 
						"Fax", 
						"Email", 
						"SupportRepId") VALUES(60, 'Joe', 'Dirt', Null, '455 Intway', 'Rome', 'New Hampshire', 'USA', '34040', '233323233232', '23233223233223', '@woeijfweijf.mail', 4),
											  (61, 'Joe', 'Dirt Jr', Null, '455 Intway', 'Rome', 'New Hampshire', 'USA', '34040', '233323233232', '23233223233223', '@woeiffweijf.mail', 5);
--Insert two new records into Employee table 
INSERT INTO "Employee" ("EmployeeId", "LastName", "FirstName", 
						"Title", "ReportsTo", "BirthDate", 
						"HireDate", "Address", "City", 
						"State", "Country", "PostalCode", "Phone", "Fax", "Email") VALUES(9,'Edwards','Betty','Sales Manager',1,'1958-12-08 00:00:00.000','2002-05-01 00:00:00.000','825 8 Ave SW','Calgary','AB','Canada','T2P 2T3','+1 (403) 262-3443','+1 (403) 262-3322','nancy@chinookcorp.com'),
																						 (10,'Edwards','Sara','Sales Manager',1,'1958-12-08 00:00:00.000','2002-05-01 00:00:00.000','825 8 Ave SW','Calgary','AB','Canada','T2P 2T3','+1 (403) 262-3443','+1 (403) 262-3322','nancy@chinookcorp.com');
--2.3 UPDATE
--Update Aaron Mitchell in Customer table to Robert Walter
UPDATE "Customer" 
SET "FirstName" = 'Robert', "LastName" = 'Walter'
WHERE "FirstName"= 'Aaron' and "LastName" = 'Mitchell';
--Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
UPDATE "Artist" SET "Name"='CCR' WHERE "Name"='Creedence Clearwater Revival';
--3.0 Joins
--In this section you will be working with combining various tables through the use of joins. You will work with outer, inner, right, left, cross, and self joins.
--3.1 INNER
--Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
select c."FirstName" , c."LastName", i."InvoiceId"
from public."Customer" as c
join public."Invoice" as i
on i."CustomerId" = c."CustomerId";
--3.2 OUTER
--Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
SELECT c."CustomerId", c."FirstName", c."LastName", i."InvoiceId", i."Total"
FROM "Customer" as c
FULL OUTER JOIN "Invoice" as i
ON i."CustomerId" = c."CustomerId";
--3.3 RIGHT
--Create a right join that joins album and artist specifying artist name and title.
SELECT Al."Title", Ar."Name"
FROM "Artist" as Ar
RIGHT JOIN "Album" as Al
ON Ar."ArtistId" = Al."ArtistId";
--3.4 CROSS
--Create a cross join that joins album and artist and sorts by artist name in ascending order.
SELECT * 
FROM "Album" 
CROSS JOIN "Artist" as art
ORDER by art."Name" asc;
--3.5 SELF
--Perform a self-join on the employee table, joining on the reportsto column.
select *
FROM "Employee" e
INNER join "Employee" m on e."ReportsTo"=m."EmployeeId";
--3.6 Joined Queries
--Create a query that shows the customer first name and last name as FULL_NAME (you can use || to concatenate two strings) with the total amount of money they have spent as TOTAL.
select c."FirstName"  c."LastName" as FULL_NAME, SUM(i."Total") as TOTAL --need to debug
from "Customer" c 
join "Invoice" i
on c."CustomerId" = i."CustomerId";
--Create a query that shows the employee that has made the highest total value of sales (total of all invoices).
select *, max(i."Total")  --need to debug
(select e.*, sum(i."Total") as Total
from "Employee" as e, 
	join "Customer" as c on e."EmployeeId" = c."SupportRepId"
	join "Invoice" as i on i."CustomerId" = c."CustomerId"
group by e."EmployeeId") as Emp;

--Create a query which shows the number of purchases per each genre. Display the name of each genre and number of purchases. Show the most popular genre first.
--4.0 User Defined Functions
--Create a function that returns the average total of all invoices.
create or replace function average_of_all_invoices()
returns numeric (10,2)
language plpgsql
as $$
declare 
	avg_invoice numeric(10,2);
begin 
	select round(avg(i."Total"),2) into avg_invoice 
	from "Invoice" as i;
	return avg_invoice;
end 
$$
	
--Create a function that returns all employees who are born after 1968.
create or replace function employees_after_1968()
returns setof public."Employee"
language plpgsql
as $$
begin
	return query select* 
	from "Employee" as e
	where e."BirthDate" > '1968-01-01 00:00:00';
end
$$

--Create a function that returns the manager of an employee, given the id of the employee.
create or replace function manager_of_employee(employee_id_input "Employee"."EmployeeId"%type)
returns setof public."Employee"
language plpgsql
as $$
begin 
	return query 
	select* 
	from "Employee" as e 
	where e."EmployeeId" = 
			(select e."ReportsTo" 
			 from "Employee" as e
			 where e."EmployeeId"= employee_id_input);
end 
$$

--Create a function that returns the price of a particular playlist, given the id for that playlist.
create or replace function playlist_price(id_input "Playlist"."PlaylistId"%type)
returns numeric (10,2)
language plpgsql
as $$
declare 
	total_price numeric(10,2);
begin 
	select round(total(t."UnitPrice"),2) into total_price
	from "Track" as t
	where t."TrackId" = id_input;
	return total_price; 
end
$$
