--SQL Lab answers

--2.1
--a
select * 
from "Employee";

--b
select * 
from "Employee"
where "LastName" = 'King';

--c
select *
from "Album"
order by "Title" desc;

--d
select "FirstName" 
from "Customer"
order by "City" asc;

--e
select * 
from "Invoice"
where "BillingAddress"
like 'T%';

--f
select "Name"
from "Track"
where "Milliseconds" = (select max("Milliseconds") from "Track");

--g
select avg("Total") 
from "Invoice";

--h
select "count("Employee") from "Employee";


--2.2
--a
insert into "Genre" 
values (26, 'Black Metal');
insert into "Genre" 
values (27, 'Jungle Beats');

--b
insert into "Employee" 
values (9, 'Bateman', 'Patrick', 'Executive Sales', 1, '1958-12-08 00:00:00',	'2002-05-01 00:00:00',	'825 8 Ave SW',	'Calgary',	'AB',	'Canada',	'T2P 2T3',	'+1 (403) 262-3443', '+1 (403) 262-3322', 'nancy@chinookcorp.com');
insert into "Employee" 
values (10, 'Allen', 'Paul', 'Executive Sales', 1, '1958-12-08 00:00:00',	'2002-05-01 00:00:00',	'825 8 Ave SW',	'Calgary',	'AB',	'Canada',	'T2P 2T3',	'+1 (403) 262-3443', '+1 (403) 262-3322', 'nancy@chinookcorp.com');

--c
insert into "Customer"
values (60,	'Puja',	'Srivastava', null,		'3,Raj Bhavan Road',	'Bangalore', null,		'India',	'560001',	'+91 080 22289999', null,		'puja_srivastava@yahoo.in',	3);
insert into "Customer"
values (61,	'Puja',	'Srivastava', null,		'3,Raj Bhavan Road',	'Bangalore', null,		'India',	'560001',	'+91 080 22289999', null,		'puja_srivastava@yahoo.in',	3);

--2.3
--a
update "Customer"
set "FirstName" = 'Robert', "LastName" = 'Walter'
where "FirstName" = 'Aaron' and "LastName" = 'Mitchell';
--b
update "Artist"
set "Name" = 'CCR'
where "Name" = 'Creedance Clearwater Revival';

--3.1
--a
select 
	"Customer"."FirstName", 
	"Customer"."LastName",
	"Invoice"."InvoiceId"
from 
	"Customer"
inner join 
	"Invoice"
on 
	"Invoice"."CustomerId" = "Customer"."CustomerId";

--3.2
--a
select
	"Customer"."CustomerId",
	"FirstName",
	"LastName",
	"InvoiceId",
	"Total"
from 
	"Customer"
full outer join
	"Invoice"
on 
	"Customer"."CustomerId" = "Invoice"."InvoiceId";

--3.3
--a
select
	"Artist"."Name",
	"Album"."Title"
from 
	"Artist"
right outer join
	"Album"
on 
	"Artist"."ArtistId" = "Album"."ArtistId";

--3.4
--a
select
	"Artist"."Name" 
from
	"Artist"
cross join
	"Album"
order by 
	"Artist"."Name"
asc;

--3.5
--a
select employee."FirstName" ||  '' || employee."LastName" as "employee_name",
overlord."FirstName" ||  '' || overlord."LastName" as "overlord_name"
from "Employee" employee
join "Employee" overlord
on employee."EmployeeId" = overlord."ReportsTo";

--3.6
--a
select "Customer"."FirstName" ||  '' || "Customer"."LastName" as "customer_name", "Invoice"."Total" as "TOTAL"
from "Customer"
join "Invoice"
on "Customer"."CustomerId" = "Invoice"."CustomerId";

--b
select e."FirstName" || e."LastName" h
as "Employee Name", sum(i."Total") as "sum_of_total" 
from "Employee" e 
inner join "Customer" c 
on e."EmployeeId" = c."SupportRepId" 
inner join "Invoice" i 
on c."CustomerId" = i."CustomerId"
group by e."EmployeeId" 
order by "sum_of_total" desc limit 1;

--c
select "Genre"."Name", count("InvoiceLine"."InvoiceLineId") as "purchases"
from "Genre"
join "Track"
on "Track"."GenreId" = "Genre"."GenreId" 
join "InvoiceLine" on "InvoiceLine"."TrackId" = "Track"."TrackId"
group by "Genre"."GenreId"
order by "purchases" desc;

--4.0
--a
create or replace function get_average_total()
returns numeric(6,1)
language plpgsql
as $$
declare 
	average_total numeric(6,1);
begin
	select avg("Invoice"."Total") 
	into average_total 
	from "Invoice";
	return average_total;
end
$$

--b
create or replace function get_after_68()
returns setof "Employee"
language plpgsql
as $$
begin
	return query 
	select * 
	from "Employee"
	where "BirthDate" >= timestamp '1968-01-01 00:00:00';
end
$$

--c
create or replace function get_manager(employeeId "Employee."EmployeeId"%type)
returns setof "Employee"
language plpgsql
as $$
declare 
	managerId "Employee"."EmployeeId"%type;
begin
	select manager."EmployeeId" into managerId
	from "Employee" as employee
	join "Employee" as manager
	on employee."ReportsTo" = manager."EmployeeId"
	where employee."EmployeeId" = employeeId;
	select * from "Employee" into result
	where "Employee."EmployeeId" = managerId;
	return result;
end

--d
create or replace function get_playlist_price(playlistId "Playlist"."PlaylistId"%type)
returns "Track"."UnitPrice"%type
language plpgsql
as $$
declare
	price "Track"."UnitPrice"%type;
begin
	select "Track"."UnitPrice" into price
	from "Playlist"
	join "PlaylistTrack"
	on "Playlist"."PlaylistId" = "PlaylistTrack"."PlaylistId" 
	join "Track"
	on "Track"."TrackId" = "PlaylistTrack"."TrackId"
	where "Playlist"."PlaylistId" = playlistId;
	return price;
end
$$










