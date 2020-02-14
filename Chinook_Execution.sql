-----------------2.0 Queries and DML --------------------
------------------------ 2.1.a. -------------------------
select * -- selects all fields in the Employee table
from "Employee";

------------------------ 2.1.b. -------------------------
select "LastName" -- return a specifc data point from the Employee table
from "Employee"
where "LastName" = 'King';

------------------------ 2.1.c. -------------------------
select * -- sort the Album table by Title in descending order
from "Album" 
order by "Title" desc;

------------------------ 2.1.d. -------------------------
select "FirstName" -- sort the Album table by Title in ascending order
from "Customer" 
order by "City" asc;

------------------------ 2.1.e. -------------------------
select "BillingAddress" -- select all invoices with a billing address like “T%”
from "Invoice" 
where "BillingAddress" like 'T%'

------------------------ 2.1.f. -------------------------
select "Name" -- returns the names of the longest track
from "Track" 
where "Milliseconds" = (   
select max("Milliseconds")  -- creates an expression to find longest track
from "Track");

------------------------ 2.1.g. -------------------------
select avg("Total") as InvoiceAverage -- avg keyword to return Invoice Average from Invoice
from "Invoice";

------------------------ 2.1.h. -------------------------
select "Title", -- find the amount of employees in each position
count (*) as numberofemployees -- use count to find iterations
from "Employee" 
group by "Title";

----------- 2.2 INSERT INTO ------------------------
------------------------ 2.2.a. -------------------------
insert into "Genre" values (26, 'Romance'); -- inserts 2 new albums into the genres table
insert into "Genre" values (27, 'Live Action');

------------------------ 2.2.b. -------------------------
insert into "Employee" ("EmployeeId", "LastName", "FirstName") values (9, 'James', 'Lebron'); -- inserts 2 new albums into the Employee table
insert into "Employee" ("EmployeeId", "LastName", "FirstName", "Title") values (10, 'Anis', 'Faysal', 'CEO');

------------------------ 2.2.c. -------------------------
insert into "Customer" ("CustomerId", "LastName", "FirstName", "Country", "Email") values (60, 'Harden', 'James', 'Canada', 'jamesharden@yahoo.com'); -- inserts 2 new albums into the Customer table
insert into "Customer" ("CustomerId", "LastName", "FirstName", "Country", "Email") values (61, 'Doncic', 'Luka', 'Canada', 'lukadagoat@gmail.com');

-------- 2.3 UPDATE -----------------------------
------------------------ 2.3.a. -------------------------
update "Customer"
set ("FirstName", "LastName") = ('Robert', 'Walter') -- updates that data point 
where ("FirstName", "LastName") = ('Aaron', 'Mitchell'); -- finds the condition in the table that has the specific data

------------------------ 2.3.b. -------------------------
update "Artist"  
set "Name" = 'CCR' -- changes the name of the data point
where "Name" = 'Creedence Clearwater Revival'; -- finds the specific data point

-------- 3.0 JOINS ------------------------------
------------------------ 3.1.a. -------------------------
select i."InvoiceId", c."FirstName", c."LastName" -- creates new aliases for the joined table
from "Customer" c 
join "Invoice" i -- inner join
on c."CustomerId" = i."CustomerId"; -- based off the shared data information

------------------------ 3.2.a. -------------------------
select i."CustomerId", c."FirstName", c."LastName", i."InvoiceId", i."Total" -- creates new aliases for the joined table
from "Customer" c 
full join "Invoice" i -- outer join
on c."CustomerId" = i."CustomerId"; -- based off the shared data information

------------------------ 3.3.a. -------------------------
select ar."Name", al."Title" -- creates new aliases for the joined table
from "Album" al
right join "Artist" ar -- Artist is right joined from the shared data information
on al."ArtistId" = ar."ArtistId";

------------------------ 3.4.a. -------------------------
select * -- selects all the data from the tables
from "Album" al
cross join "Artist" ar -- Artist is right joined from the shared data information
order by ar."Name" asc; -- orders specific column by name ascending 

------------------------ 3.5.a. -------------------------
select * -- selects all the data from the tables
from "Employee" e 
join "Employee" r 
on e."EmployeeId" = r."ReportsTo"; -- self join both employee table and its self ReportsTo 

------------------------ 3.6.a. -------------------------
select c."FirstName" || ' ' || c."LastName" as "Full_Name", i."Total" as "TOTAL" -- concatenate first and last name with a space
from "Customer" c 
join "Invoice" i -- inner join for 2 tables to return only like values
on c."CustomerId" = i."CustomerId"; -- combines at common point

------------------------ 3.6.b. -------------------------
select e."FirstName" || ' ' || e."LastName" as "Employee Name", sum("Total") as "Total Value of Sales"
from "Invoice" inv -- 3 way join for tables
     join "Customer" c on c."CustomerId" = inv."CustomerId"  
     join "Employee" e on c."SupportRepId" = e."EmployeeId"
group by "Employee Name" order by "Total Value of Sales" desc limit 1; -- set limit to 1 and descending order to get the most sales

------------------------ 3.6.c. -------------------------
select g."Name", sum("Quantity") as "Number of Purchases" -- provides name of genre and amount sold
from "InvoiceLine" il -- 3 way join for relevant tables
	join "Track" t on il."TrackId" = t."TrackId" -- merge at like points
	join "Genre" g on g."GenreId" = t."GenreId"
group by g."GenreId" order by "Number of Purchases" desc; -- shows the genres with number of purchases descending
-------------- 4.0 USER DEFINED FUNCTIONS -----------------------
------------------------ 4.0.a. -------------------------
create or replace function average_total()
returns numeric 
language plpgsql
as $$
begin
	return avg("Total")  -- calculates the average value as a numeric from the total column in the invoice table
	from "Invoice";
 end
$$ 

select average_total() as "Invoice Average"; -- runs that specific function with no input argument 

------------------------ 4.0.b. -------------------------
create or replace function born_after68()
returns setof "Employee" 
language plpgsql
as $$
begin
    return query (select * -- selects all of the columns in the table employee
  				  from "Employee"  
  				  where "BirthDate" > '1968-12-31'); -- condition that is returned for the birthdate column
end;
$$ 

select born_after68(); -- runs that specific function with no input argument

------------------------ 4.0.c. -------------------------
create or replace function get_manager(employ_id int)
returns text 
language plpgsql
as $$
begin
    return (select "FirstName" || ' ' || "LastName" -- returns the full name of the manager 
    	    from "Employee" -- returns the name from the Employee table
    	    where "EmployeeId" = ( -- sets the ReportsTo value as the new name
                select "ReportsTo"  -- first finds the ReportsTo value from the user input of the employee id
                from "Employee" 
                where "EmployeeId" = employ_id));
end;
$$ 

select get_manager(6) as "Manager Name"; -- runs that specific function with input argument as the employee id

------------------------ 4.0.d. -------------------------
create or replace function get_price(track_id int)	
returns numeric
language plpgsql
as $$
begin
	return (select sum("UnitPrice") -- return the total cost of playlist
	       from "PlaylistTrack" pt
	       join "Track" t -- join the 2 tables on the common column
	       on pt."TrackId" = t."TrackId"
      	   where "PlaylistId" = track_id); -- condition where playlistid is the same as the trackid
end;
$$

select get_price(1) as "Total Cost of Playlist";
select get_price("PlaylistId") from "Playlist"; -- test all the returned values for playlist costs

			