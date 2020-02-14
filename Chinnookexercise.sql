----------------------------------------------------------------------------
-- Queries and DML
select *
from "Employee"; -- This command queries all employees in Employee table

select "LastName" 
from "Employee"
where "LastName" = 'King'; -- Returns specific data due to condition

select "Title" 
from "Album" 
order by "Title" desc; -- Orders descending by title

select "FirstName"
from "Customer" 
order by "City" asc;  -- selected first names and ordered descending by city

select "BillingAddress"
from "Invoice" 
where "BillingAddress" like 'T%'; -- Billing address starting with T

select "Name"
from "Track"
where "Milliseconds" = (
select max("Milliseconds" )
from  "Track");                  -- selecting longest track

select avg("Total")
from "Invoice";                  -- average total in invoice

select "Title",                  -- count the number of employees
count (*) as numberofemployees
from "Employee" 
group by "Title";

select *
from "Employee";

select *
from "Customer";
-----------------------------------------
-- Insert into
insert into "Genre" ("GenreId" ,"Name")
values (28,'Lazy Rock'),(27, 'Crazy Opera');  -- insert into two records

insert into "Employee" ("EmployeeId", "LastName","FirstName")
values (9,'Stevens', 'Grace'),
       (10,'Pizza','Nancy');

insert into "Customer" ("CustomerId", "FirstName", "LastName","Email")
values (60, 'John', 'Smith','toasty@gmail.com'), (61, 'Troy','Keen','pizza@gmail.com');
---------------------------------------------
-- Update
update "Customer"
set ("LastName", "FirstName" ) = ('Walter','Robert') -- update of infrmation in the specific table
where "CustomerId" = 32;

update "Artist" 
set "Name" = 'CCR'
where "ArtistId" = 76;
----------------------------------------------
-- Join
select c."LastName", "FirstName", i."InvoiceId"  -- join or innerjoin of two tables
from "Customer" c
join
"Invoice" i 
on (c."CustomerId" ) = i."CustomerId" ;

select c."LastName", "FirstName", i."InvoiceId",i."Total" -- leftjoin in which customer table information is displayed first
from "Customer" c
left join
"Invoice" i
on c."CustomerId" = i."CustomerId";

select t."Name", a."Title"                                -- rightjoin of information  
from "Album" a 
right join
"Artist" t
on t."ArtistId" = a."ArtistId"; 

select *                                                     -- cross join of two tables and presenting information ordered
from "Album" 
cross join
"Artist" a
order by a."Name" desc; 

select e."FirstName" as employee, m."EmployeeId" as manager  -- self join on table
from "Employee" e
join "Employee" m ON e."ReportsTo" = m."ReportsTo";  

select concat(c2."FirstName",' ',c2."LastName" ) as full_name, i."Total"   -- query displaying full name
from "Customer" c2  
join 
"Invoice" i on c2."CustomerId" = i."CustomerId";

select e."FirstName", sum(i."Total") total_sales           ----- multiple where eith joins
from "Employee" e
join "Customer" c
on e."EmployeeId" = c."SupportRepId" 
join "Invoice" i
on i."CustomerId" = c."CustomerId" 
group by e."EmployeeId" 
order by total_sales desc limit 1;

select g."Name", sum(il."Quantity") total_purchases      -- group by with joins
from "Track" t 
join "InvoiceLine" iL 
on t."TrackId" = iL."TrackId" 
join "Genre" g
on g."GenreId" = t."GenreId" 
group by g."Name"
order by total_purchases desc;

-- View(does not store) and Materialized views (stores)


  --------  user defined functions ---------------
create function avg_total_invoice()                              -- finds the avg invoice total
returns numeric (7,2)
language plpgsql
as $$
declare 
avg_total_invoice numeric (7,2);
begin 
	select round(avg("Total"),2) into avg_total_invoice
	from "Invoice";
	return avg_total_invoice;
end
$$

select avg_total_invoice();

create or replace function born_date()                              -- finds the birth date after 1968
returns setof "Employee"
language plpgsql
as $$ 
declare 
begin 
	return query
	select*    
	from "Employee" 
    where "BirthDate" > '1968-12-31';
	
end;
$$

select born_date();

create or replace function empl_manager(empl_id "Employee"."EmployeeId"%type)  -- finds whom the employee reports to
returns setof "Employee"
language plpgsql
as $$
declare
    manager_id "Employee"."EmployeeId"%type;
begin
    select "ReportsTo" into manager_id
    from "Employee"
    where "EmployeeId" = empl_id;
    return query select *
    from "Employee"
    where "EmployeeId" = manager_id;
end
$$;

select empl_manager(2);

create or replace function playlist_price(playlist_id "Playlist"."PlaylistId"%type)  -- finds price of playlist
returns "Track"."UnitPrice"%type
language plpgsql
as $$
declare
    cost_per_track "Track"."UnitPrice"%type;
begin
    select sum(t."UnitPrice") into cost_per_track 
    from "Track" t 
    where t."TrackId" = (
    select pt."PlaylistId" 
    from "PlaylistTrack" pt 
    join
    "Playlist" p
    on (pt."PlaylistId") = p."PlaylistId");
    return cost_per_track;
end
$$;

select playlist_price(2);