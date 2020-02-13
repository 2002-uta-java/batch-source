/*
 * 02.12.2020
 * Christian Randle
 * 
 * Chinnook Postgre Exercises
 * 
 */

/*----------------------------------------------------------------------------------------------------------
 * 						2.0 QUERIES AND DML
 ----------------------------------------------------------------------------------------------------------*/


/*----------------------------------------------------- 
 * 				2.1 SELECT
 -----------------------------------------------------*/
--		2.1-A Select all records from the Employee table.
	select * from "Employee";

--		2.1-B Select all records from the Employee table where last name is King.
	select * from "Employee" where "LastName" like 'King';

--		2.1-C Select all albums in Album table and sort result set in descending order by title.
	select "Title" , "ArtistId" ,"AlbumId" from "Album" order by "Title" DESC;

--		2.1-D Select first name from Customer and sort result set in ascending order by city.
	select "FirstName" from "Customer" order by "City" ASC ; 

--		2.1-E Select all invoices with a billing address like “T%”.
	select * from "Invoice" where "BillingAddress" like 'T%';

--		2.1-F Select the name of the longest track.
	select "Name" from "Track" where "Milliseconds"  = (select max("Milliseconds" ) from "Track" t ); 

--		2.1-G Find the average invoice total.
	select avg("Total" ) from "Invoice" i2;

--		2.1-H Find the total number of employees in each position.
	select  "Title" , count(*) from "Employee" group by "Title";
 
/* -----------------------------------------------------
*			 2.2 INSERT INTO
-----------------------------------------------------*/ 

--		2.2-A Insert two new records into Genre table
	insert  into "Genre" ("Name","GenreId") 
	values 
		('Bluegrass',26),
		('Ska',27);
	--select * from "Genre";
		
--		2.2-B Insert two new records into Employee table
	insert  into "Employee" ("EmployeeId" ,"LastName" ,"FirstName" ,"Title" ,"ReportsTo",
							"BirthDate" ,"HireDate" ,"Address" ,"City" ,"State" ,"Country","PostalCode","Phone" ,"Fax" ,"Email" )
	values 
		(9 ,'Bobman' ,'Bob' ,'IT Staff' ,6,'07-12-1908' ,'07-12-2008' ,'1223 Origin Drive' ,'Santa Cruz' ,'CA' ,'United States',
							'916655','111-222-3333' ,'111-222-4444' ,'notReal87@chinookcorp.com' ),
							
		(10 ,'Stanners' ,'Stanley' ,'IT Staff' ,6,'07-12-1907' ,'07-12-2008' ,'1223 Origin Drive' ,'Santa Cruz' ,'CA' ,'United States',
							'916655','111-222-3333' ,'111-222-4444' ,'notReal77@chinookcorp.com' );
							
	--select * from "Employee";

--		2.2-C Insert two new records into Customer table
	insert  into "Customer" ("CustomerId","FirstName","LastName","Address",
							"City","State","Country","PostalCode","Phone","Fax","Email","SupportRepId")
	values 
		(60, 'Luna','Lucia','Noon Drive 12','Redwood City','CA','United States','121245','222-333-888','222-555-999','lunLuciaa@ca.gov',3),
		(61, 'Sol','Sully','Brightplace way 15','Minneapolis','MN','United States','122245','111-323-888','222-555-222','sunnyboi@mn.edu',3);
							
	--select * from "Customer";


/* -----------------------------------------------------
*			 2.3 UPDATE
-----------------------------------------------------*/ 
-- 2.3-A Update Aaron Mitchell in Customer table to Robert Walter
	update "Customer" 
	SET	
		"FirstName" = 'Robert',
		"LastName" = 'Walter'
	where 
		("FirstName" = 'Aaron' and "LastName" = 'Mitchell');
	--select * from "Customer" order by "FirstName"  ASC;

-- 2.3-B Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
	update "Artist" 
	SET	
		"Name" = 'CCR'
	where 
		("Name" = 'Creedence Clearwater Revival');
	--select * from "Artist" where "ArtistId" = 76;


/*----------------------------------------------------------------------------------------------------------
 *						3.0 Joins
 ----------------------------------------------------------------------------------------------------------*/

	
/* -----------------------------------------------------
*			 3.1 INNER
-----------------------------------------------------*/ 
--	3.1-A Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
	select 
		c1."FirstName"||' '||c1."LastName" as Name ,i1."InvoiceId" 
	from 
		"Customer" c1
	inner join "Invoice" i1 on c1."CustomerId" = i1."CustomerId";

/* -----------------------------------------------------
*			 3.2 OUTER
-----------------------------------------------------*/  
-- 3.2-A Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
	select 
		c1."CustomerId" ,c1."FirstName",c1."LastName",i1."InvoiceId",i1."Total" 
	from 
		"Customer" c1
	join "Invoice" i1 on c1."CustomerId" = i1."CustomerId";

/* -----------------------------------------------------
*			 3.3 RIGHT
-----------------------------------------------------*/  
-- 3.3-A Create a right join that joins album and artist specifying artist name and title.
	select 
		ar."Name",al."Title" 
	from 
		"Artist" ar
	right join "Album" al on ar."ArtistId" = al."ArtistId" ;

/* -----------------------------------------------------
*			 3.4 CROSS
-----------------------------------------------------*/  
-- 3.4-A Create a cross join that joins album and artist and sorts by artist name in ascending order.
	select 
		*
	from 
		"Artist" ar
	cross join "Album" al order by ar."Name" ASC;

/* -----------------------------------------------------
*			 3.5 SELF
-----------------------------------------------------*/ 
--3.5-A Perform a self-join on the employee table, joining on the reportsto column.
	select 
		*
	from 
		"Employee" e1 
	inner join "Employee" e2 on e2."ReportsTo" = e1."ReportsTo";
	
/* -----------------------------------------------------
*			 3.6 JOINED QUERIES
-----------------------------------------------------*/	
-- 3.6-A Create a query that shows the customer first name and last name as FULL_NAME (you can use || to concatenate two strings) with the total amount of money they have spent as TOTAL.
	select 
		c1."FirstName"||' '||c1."LastName" as "FULL_NAME", TOTAL as "TOTAL",c1."CustomerId" 
	from 
		"Customer" c1
	join 
		(select i."CustomerId" ,sum("Total") as TOTAL from "Invoice" i group by "CustomerId") x 
	on
		x."CustomerId"= c1."CustomerId" ;
	
-- 3.6-B Create a query that shows the employee that has made the highest total value of sales (total of all invoices).
	select *
	from  "Employee" e2
	join ( select "SupportRepId", sum("Total") as sales from 
			( 
				select c2."SupportRepId","Total"
				from  "Customer" c2 
				join "Invoice" i
				on i."CustomerId"= c2."CustomerId"  
				order by c2."SupportRepId" 
			) repSales group by "SupportRepId"
		) totalSales
	on totalSales."SupportRepId" = e2."EmployeeId" order by sales desc
	limit 1;

-- 3.6-C Create a query which shows the number of purchases per each genre. Display the name of each genre and number of purchases. Show the most popular genre first.
/*
 * using:
 * invoiceLine: TrackID, InvoiceLineId
 * Track: GenreID
 * Genre: Name
 * */
	select "Name" ,sum("Quantity") as purchases 
	from 
	(
		select "TrackId" ,g2."Name" 
		from "Track" t2 
		   join "Genre" g2 
		on t2."GenreId"  = g2."GenreId" 
	) as genreTrack
	    join "InvoiceLine" i2
	on i2."TrackId"  = genretrack."TrackId" 
	group by "Name" 
	order by purchases desc;
	
	
/*----------------------------------------------------------------------------------------------------------
 *						4.0 USER DEFINED FUNCTIONS
 ----------------------------------------------------------------------------------------------------------*/
--4.0-A Create a function that returns the average total of all invoices.
create or replace  function total_average_invoices() returns setof numeric as $$
 	select avg("Total" ) from "Invoice" i ;
 $$ language sql;
	--select * from total_average_invoices();


--4.0-B Create a function that returns all employees who are born after 1968.
 create or replace  function born_after_68() returns setof "Employee" as $$
 	select * from "Employee" e  where  "BirthDate" > make_timestamp(1968, 12, 31, 11, 59, 59.00);
 $$ language sql;
	--select * from born_after_68();



--4.0-C Create a function that returns the manager of an employee, given the id of the employee.
create or replace  function manager_of( id numeric) returns setof "Employee" as $$
 select * from "Employee" e2 where e2."EmployeeId" = 
 	(
	 select e."ReportsTo"  from "Employee" e  where "EmployeeId" = id
	) 
 $$ language sql;
	--select * from manager_of();



--4.0-D	Create a function that returns the price of a particular playlist, given the id for that playlist.
create or replace  function playlist_price( id numeric) returns numeric as $$
 	select sum("UnitPrice" )
 	from "Track" t2 
 	right join(
 		select * 
 		from "PlaylistTrack"
 		where "PlaylistId" = id
 	)  list_Ids
 	on  list_Ids."TrackId" = t2."TrackId" 

 $$ language sql;
	-- select * from playlist_price(1);
	
/*----------------------------------------------------------------------------------------------------------
 *						Function to reset chinnook to initial state for testing ( not run by default)
 ----------------------------------------------------------------------------------------------------------*/
create or replace function reset_chinook() returns void as $$
	drop function playlist_price("numeric");
	drop function born_after_68();
	drop function total_average_invoices() ;
	drop function manager_of("numeric") ;
	delete  from "Customer" where "CustomerId"  = 60;
	delete  from "Customer" where "CustomerId"  = 61;
	delete  from "Employee" where "EmployeeId"  = 9;
	delete  from "Employee" where "EmployeeId"  = 10;
	delete  from "Genre"  where "GenreId" >= 26;

	update "Customer" 
	SET	
		"FirstName" = 'Aaron',
		"LastName" = 'Mitchell'
	where ("FirstName" = 'Robert' and "LastName" = 'Walter');

	update "Artist" 
	SET	"Name" = 'Creedence Clearwater Revival'
	where "Name" = 'CCR';

 $$language sql;	

select reset_chinook();
drop function reset_chinook() ;
