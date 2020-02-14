/**
 * Eric Pacheco
 * 2/11/20
 * 
 */
--selecting all from employee table
 select
	*
from
	"Employee";
----seclting all employees where lastname is king
 select
	*
from
	"Employee"
where
	"LastName" = 'King';
---Select all albums in Album table and sort result set in descending order by title.
 select
	*
from
	"Album"
order by
	"Title" asc;
--order by a."Title" Title 
 select
	*
from
	"Album" a
order by
	a."Title" asc;
--Select first name from Customer and sort result set in ascending order by city.
 select
	"FirstName"
from
	"Customer"
order by
	"City" asc;
--Select all invoices with a billing address like “T%”.
 select
	*
from
	"Invoice"
where
	"BillingAddress" like 'T%';
--Select the name of the longest track.
 select
	"Name"
from
	"Track"
where
	"Milliseconds" = (
	select
		max("Milliseconds")
	from
		"Track");
--Find the average invoice total.
 select
	avg("Total")
from
	"Invoice";
--Find the total number of employees in each position.

--select count(distinct "Title") as position from "Employee"; 
 select
	"Title",
	count(*)
from
	"Employee"
group by
	"Title";
--Insert two new records into Genre table
 insert
	into
	"Genre" ("GenreId",
	"Name")
values ('26',
'Synth Wave'),
('27',
'Metal');
--Insert two new records into Employee table
 insert
	into
	"Employee"( "EmployeeId",
	"LastName" ,
	"FirstName" ,
	"Title" ,
	"ReportsTo" ,
	"BirthDate" ,
	"HireDate" ,
	"Address" ,
	"City" ,
	"State" ,
	"Country" ,
	"PostalCode" ,
	"Phone" ,
	"Fax" ,
	"Email" )
values ( '9',
'Pacheco',
'Eric',
'Drinks',
'1',
'1988-02-18 00:00:00',
'2002-04-01 00:00:00',
'7727B 41 Ave',
'Arlington',
'TX',
'US',
'T5K 2N1',
'+1 (951) 973-9270',
'+1 (780) 428-3457',
'eric@chinookcorp.com' ),
( '10',
'Chai',
'Sean',
'Data Base',
'1',
'1995-02-18 00:00:00',
'2002-04-01 00:00:00',
'7727B 41 Ave',
'Arlington',
'TX',
'US',
'T5K 2N1',
'+1 (898) 764-9270',
'+1 (780) 428-3457',
'sean@chinookcorp.com' );
--Insert two new records into Customer table 
 insert
	into
	public."Customer" ("CustomerId",
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
	"SupportRepId")
values(61,
'Eric',
'Pacheco',
'nose',
'4, Rue Milton',
'Arlington',
'TX',
'United States',
'75009',
'(951) 787-4534',
'NULL',
'eric@fun.com',
3),
(60,
'Sean',
'Chai',
'nose',
'4, Rue Milton',
'Arlington',
'TX',
'United States',
'75009',
'(878) 787-4534',
'NULL',
'sean@fun.com',
3);
--Update Aaron Mitchell in Customer table to Robert Walter
 update
	public."Customer"
set
	"FirstName" = 'Robert',
	"LastName" = 'Walter'
where
	"FirstName" = 'Aaron'
	and "LastName" = 'Mitchell';
--Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
 update
	public."Artist"
set
	"Name" = 'CCR'
where
	"Name" = 'Creedence Clearwater Revival';
--Inner join

--Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
 select
	"FirstName",
	"LastName",
	"InvoiceId"
from
	"Customer" c
inner join "Invoice" i on
	c."CustomerId" = i."CustomerId";
--Create an outer join that joins the customer and invoice table, 

--specifying the CustomerId, firstname, lastname, invoiceId, and total.
 select
	c."CustomerId",
	"FirstName",
	"LastName",
	"InvoiceId",
	"Total"
from
	"Customer" c
full outer join "Invoice" i on
	c."CustomerId" = i."CustomerId";
--Create a right join that joins album and artist specifying artist name and title.
 select
	a2."ArtistId" ,
	a2."Name"
from
	"Artist" a2
right join "Album" a3 on
	a2."ArtistId" = a3."ArtistId";
--Create a cross join that joins album and artist and sorts by artist name in ascending order.
 select
	a2."Title",
	a."Name"
from
	"Album" a2
cross join "Artist" a
order by
	a."Name" asc;
--Perform a self-join on the employee table, joining on the reportsto column.	
select *
from "Employee" e2 
inner join "Employee" e3 
on e2."ReportsTo" = e3."ReportsTo" ;

--Create a query that shows the customer first name and last name as FULL_NAME
-- (you can use || to concatenate two strings) with the total amount of money they have spent as TOTAL.
select (
	select sum(i."Total" ) as total from "Invoice" i
	where i."CustomerId" = c."CustomerId"
), concat(c."FirstName", ' ',c."LastName") as full_name
from "Customer" c , "Invoice" i
where i."CustomerId" = c."CustomerId"
group by i."CustomerId", c."CustomerId" ;

--Create a query that shows the employee that has made the highest total value of sales (total of all invoices).
select (
	select "Quantity"
	from "InvoiceLine" il 
	inner join "Employee" e
	on e."EmployeeId" = il."InvoiceId";
), concat(c."FirstName", ' ',c."LastName") as employeeName
from "Employee" c
order by "Quantity" asc;
--Create a query which shows the number of purchases per each genre.
-- Display the name of each genre and number of purchases. Show the most popular genre first.

select sum(il."Quantity") as Quantity, g."Name" 
from "InvoiceLine" il, "Track" t 
inner join "Genre" g
on t."GenreId" = g."GenreId" 
group by g."GenreId", t."GenreId" 
order by quantity asc;

--Create a function that returns the average total of all invoices.
create function average()
returns float
language plpgsql
as $function$
	begin
		return (select avg(i."total") from "Invoice" i );
	end
	$function$
--Create a function that returns all employees who are born after 1968.
create function employee_after_68()
returns table
language plpgsql
as $function$
	begin
		return (
		select * 
		from "Employee" e2 
		where e2."BirthDate" >= '1968-01-01 00:00:00';
	);
	end
	$function$
--Create a function that returns the manager of an employee, given the id of the employee.
create function manager(empId employee.empl_id%type)
returns int4
language plpgsql
as $function$
	begin
		return (select "Manager" from employee e3 where empId = e3.empl_id );
	end;
	$function$
--Create a function that returns the price of a particular playlist, given the id for that playlist
create function playlistPrice(playId Playlist.PlaylistId%type)
returns numeric(10,2)
language plpgsql
as $function$
	begin
		return (
		select "UnitPrice"
		from "PlaylistTrack" pt 
		inner join "InvoiceLine" 
		on pt."TrackId" = "InvoiceLine"."TrackId" 

		);
	end;
	$function$
