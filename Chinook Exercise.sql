-----------
--select
-----------
-- 2.1a
select * 
from "Employee";

-- 2.1b
select *
from "Employee"
where "LastName" = 'King';

-- 2.1c
select *
from "Album"
order by "Title" desc;

--2.1d
select "FirstName" 
from "Customer"
order by "City";

-- 2.1e
select *
from "Invoice"
where "BillingAddress" like 'T%';

-- 2.1f
select *
from "Track" t1
where t1."Milliseconds" in (select max(t2."Milliseconds") from "Track" t2);

-- 2.1g
select avg("Total")
from "Invoice" i;

-- 2.1h
select "Title" , count(*)
from "Employee" e 
group by "Title";

--------
--insert into
--------
-- 2.2a
insert into "Genre" values(26, 'JPOP');
insert into "Genre" values(27, 'KPOP');

-- 2.2b
insert into "Employee" ("EmployeeId", "FirstName" ,"LastName" ) values(9,'Mike', 'Tyson');
insert into "Employee" ("EmployeeId", "FirstName" ,"LastName" ) values(10,'Yung', 'Hee');

-- 2.2c
insert into "Customer" ("CustomerId" , "FirstName" ,"LastName" , "Email" ) values(60,'Pigeon', 'Pecker', 'ppecker@gmail.com');
insert into "Customer" ("CustomerId" , "FirstName" ,"LastName" , "Email" ) values(61,'Kobe', 'Bryant', 'bballer@gmail.com');

----------
--update
----------
-- 2.3a
update "Customer" 
set "FirstName" = 'Robert', "LastName" = 'Walter'
where "FirstName" = 'Aaron' and "LastName" = 'Mitchell';
-- 2.3b
update "Artist" 
set "Name" = 'CCR'
where "Name" = 'Creedence Clearwater Revival';

---------
--join
-----------
-- 3.1a
select c."FirstName" , c."LastName" , i."InvoiceId" 
from "Customer" c inner join "Invoice" i on c."CustomerId" = i."CustomerId";

-- 3.2a
select c."CustomerId", c."FirstName", c."LastName" ,i."InvoiceId" ,i."Total" 
from "Customer" c full join "Invoice" i on c."CustomerId" = i."CustomerId";

-- 3.3a
select a2."Name", a1."Title"
from "Album" a1 right outer join "Artist" a2 on a1."ArtistId" = a2."ArtistId";

-- 3.4a
select *
from "Album" a1 cross join "Artist" a2
order by a2."Name";

-- 3.5a
select *
from "Employee" e1 inner join "Employee" e2 on e1."ReportsTo" = e2."ReportsTo";

-- 3.6a
select "FirstName" || "LastName" as Full_Name, "Total"
from "Customer" c inner join "Invoice" i on c."CustomerId" = i."CustomerId";

-- 3.6b
select e."FirstName" || ' ' || e."LastName" as full_name, sum(i."Total") as i_total
from "Employee" e inner join "Customer" c on e."EmployeeId" = c."SupportRepId"
	inner join "Invoice" i on c."CustomerId" = i."CustomerId"
group by full_name
order by i_total desc limit 1;

-- 3.6c
select g."Name", sum(i."Quantity") as sales
from "Genre" g inner join "Track" t on g."GenreId" = t."GenreId"
	inner join "InvoiceLine" i on t."TrackId" = i."TrackId"
group by g."GenreId"
order by sales desc;

------------------
--user defined functions
------------------
-- 4.0a
create or replace function avg_total_invoice()
returns numeric(10,2)
language plpgsql
as $$
declare
	average numeric(10,2);
begin
	select avg(i."Total") into average from "Invoice" i;
	return average;
end;
$$;

-- 4.0b
create or replace function born_after(year_born integer)
returns setof "Employee"
language plpgsql
as $$
begin
	return query select * from "Employee"
	where "BirthDate" >= to_timestamp(year_born||'-01-01', 'yyyy-mm-dd');
end
$$;
select born_after(1969);

-- 4.0c
create or replace function get_manager(empl_id integer)
returns varchar(20)
language plpgsql
as $$
declare 
	manager_name varchar(20);
begin 
	select m."FirstName" || ' ' || m."LastName" into manager_name
	from "Employee" e join "Employee" m
	on e."ReportsTo" = m."EmployeeId" 
	where e."EmployeeId" = empl_id;

	return manager_name;
end
$$;

-- 4.0d
create or replace function get_playlist_price(playlist_id integer)
returns numeric(10,2)
language plpgsql
as $$
declare 
	playlist_price numeric(10,2);
begin
	select sum(t."UnitPrice") into playlist_price
	from "Playlist" pl join "PlaylistTrack" pt on pl."PlaylistId" = pt."PlaylistId"
		join "Track" t on pt."TrackId" = t."TrackId"
	where pl."PlaylistId" = playlist_id
	group by pl."PlaylistId";
	return playlist_price;
end
$$;