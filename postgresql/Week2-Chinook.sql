-- selecting all data from the employee table
select * from "Employee";

-- selecting all employees where last name is King
select * from "Employee" where "LastName"='King';

-- selecting all albums sorting by title in descending order
select "Title" from "Album" order by "Title" desc;

-- selecting first name of customers sorted by city in ascending order
select "FirstName" from "Customer" order by "City" asc;

-- selecting all invoices with a billing address like "T%"
select * from "Invoice" where "BillingAddress" like 'T%';

-- getting name of longest track
select "Name" from "Track" where "Milliseconds" = (select max("Milliseconds") from "Track");

-- getting name of longest track using descending order
select "Name" from "Track" order by "Milliseconds" desc limit 1;

-- get average invoice total
select avg("Total") from "Invoice";

-- total number of employees in each position
select "Title", count("Title") from "Employee" group by "Title";

-- inserting into genre table
insert into "Genre" ("GenreId", "Name") values (26, 'Pop Punk');
insert into "Genre" values (27, 'Folk Rock');
insert into "Genre" ("GenreId") values (28);
-- inserting two records into employee table

-- myself, title is "Trainee" and I report to Laura (id 8), born 02/27/1983, hired on 02/03/2020, 
insert into "Employee" values (9, 'Bennatt', 'Jared', 'Trainee', 8, timestamp '1983-02-27 00:00:00', timestamp '2020-02-03 00:00:00', '1683 Otto Rd', 'Inez', 'TX', 'USA', '77968', '+1 (361) 781-4929', null, 'bennattj@gmail.com');
-- inserting my wife as a "Sales Support Agent" reporting to Nancy (id 2)
insert into "Employee" values (10, 'Bennatt', 'Jamie', 'Sales Support Agent', 2, timestamp '1984-10-18 00:00:00', '2020-02-03 00:00:00', '1683 Otto Rd', 'Inez', 'TX', 'USA', '77968', '+1 (361) 781-4378', null, 'jamiebennatt@gmail.com');

-- 2.2c
insert into "Customer" values (60, 'Joe', 'Swanson', 'QPD', '1200 Handicap Ln', 'Quahog', 'RI', 'USA', '55555', '(555) 555-1234', null, 'jswanson@familyguy.tv', 3);
insert into "Customer" values (61, 'Peter', 'Griffen', 'Pawtucket Brewery', '1201 Handicap Ln', 'Quahog', 'RI', 'USA', '55555', '(555) 555-1235', null, 'pgriffin@familyguy.tv', 4);

--2.3a
update "Customer"
set "FirstName"='Robert', "LastName"='Walter'
where "FirstName"='Aaron' and "LastName"='Mitchell';

-- 2.3b
update "Artist"
set "Name"='CCR'
where "Name"='Creedence Clearwater Revival';

-- 3.1a
select c."FirstName", c."LastName", i."InvoiceId"
from "Customer" c
inner join
"Invoice" i 
on c."CustomerId" = i."CustomerId"
order by c."LastName", i."InvoiceId";

-- 3.2a
select c."CustomerId", c."FirstName", c."LastName", i."InvoiceId", i."Total" 
from "Customer" c
full outer join
"Invoice" i
on c."CustomerId" = i."CustomerId" 
order by c."LastName", i."Total";

-- 3.3a
select artist."Name" , album."Title" 
from "Artist" artist
right outer join
"Album" album
on artist."ArtistId" = album."ArtistId"
order by artist."Name";

-- 3.4a
select *
from "Artist" artist
cross join
"Album"
order by artist."Name";

--3.5a
select e1."LastName", e1."FirstName", e1."Title", e2."LastName", e2."FirstName", e2."Title"
from "Employee" e1
inner join
"Employee" e2
on e1."ReportsTo"  = e2."EmployeeId" 
order by e1."LastName";

-- 3.6a
select concat(c."FirstName", ' ', c."LastName") as "FULL_NAME", sum(i."Total") as "TOTAL"
from "Customer" c 
inner join
"Invoice" i
on c."CustomerId" = i."CustomerId" 
group by c."CustomerId";

-- 3.6b
-- not really enough information given to answer this so I'm assuming that supportRepId is who (exclusively) sells to a customer
-- I chose to just order the results and limit one instead of having to run the join twice to get a max(total) and find who's total
--  equaled the max (like I did above)
select  e."FirstName", e."LastName", e_totals.total
from "Employee" e 
inner join
(select c."SupportRepId" as e_id, sum(i."Total") as total
from "Customer" c 
inner join
"Invoice" i
on c."CustomerId" = i."CustomerId" 
group by c."SupportRepId") as e_totals
on e."EmployeeId" = e_totals.e_id
order by e_totals.total limit 1;

-- 3.6c
select g."Name", track_inv.num_p as "Purchases"
from "Genre" g 
inner join
(select track."GenreId" as g_id, sum(invoice."Quantity") as num_p
from "Track" track 
inner join
"InvoiceLine" invoice
on track."TrackId" = invoice."TrackId"
group by track."GenreId") as track_inv
on g."GenreId" = track_inv.g_id
order by "Purchases" desc;

-- 4.0a
create or replace function avg_invoice()
returns numeric(3,2)
language plpgsql
as $$
declare
    rtrn numeric(3,2);
begin
    select avg("Total") into rtrn
    from "Invoice";
    return rtrn;
end
$$;

select avg_invoice();

-- 4.0b
create or replace function empl_younger(birth_yr integer)
returns setof "Employee"
language plpgsql
as $$
begin
    return query select * 
    from "Employee" 
    where extract(year from "BirthDate") > birth_yr;
end
$$;

select empl_younger(1968);

-- 4.0c
create or replace function get_manager(empl_id "Employee"."EmployeeId"%type)
returns setof "Employee"
language plpgsql
as $$
declare
    man_id "Employee"."EmployeeId"%type;
begin
    select "ReportsTo" into man_id
    from "Employee"
    where "EmployeeId" = empl_id;
    return query select *
    from "Employee"
    where "EmployeeId" = man_id;
end
$$;

select get_manager(2);
select get_manager(1);
select get_manager(9);

-- 4.0d
create or replace function get_playlist_price(playlist_id "Playlist"."PlaylistId"%type)
returns "Track"."UnitPrice"%type
language plpgsql
as $$
declare
    price "Track"."UnitPrice"%type;
begin
    select sum(track."UnitPrice") into price
    from ((select "TrackId" as track_id
    from "PlaylistTrack"
    where "PlaylistId" = playlist_id) as playlist
    inner join
    "Track" track
    on track."TrackId" = playlist.track_id);
    return price;
end
$$;

select get_playlist_price(1);