select *
from "Employee";

select *
from "Employee"
where "LastName" = 'King';

select *
from "Album"
order by "Title" desc;

select "FirstName"
from "Customer"
order by "City" asc;

select *
from "Invoice"
where "BillingAddress" like 'T%';

select "Name"
from "Track"
where "Milliseconds" = (select max("Milliseconds") from "Track" )
group by "Name" ;

/*
select *
from "Track"
order by "Milliseconds" desc; */

select avg("Total") as average_total
from "Invoice" i ;

select count("FirstName"), "Title"
from "Employee" e 
group by "Title";

insert into "Genre" values (26, 'JPop');
insert into "Genre" values (27, 'Classical');

INSERT INTO public."Employee" ("EmployeeId","LastName","FirstName","Title","ReportsTo","BirthDate","HireDate","Address","City","State","Country","PostalCode","Phone","Fax","Email") VALUES 
(9,'Adams','Andrew','General Manager',NULL,'1962-02-18 00:00:00.000','2002-08-14 00:00:00.000','11120 Jasper Ave NW','Edmonton','AB','Canada','T5K 2N1','+1 (780) 428-9482','+1 (780) 428-3457','andrew@chinookcorp.com');
INSERT INTO public."Employee" ("EmployeeId","LastName","FirstName","Title","ReportsTo","BirthDate","HireDate","Address","City","State","Country","PostalCode","Phone","Fax","Email") VALUES 
(10,'Adams','Andrew','General Manager',NULL,'1962-02-18 00:00:00.000','2002-08-14 00:00:00.000','11120 Jasper Ave NW','Edmonton','AB','Canada','T5K 2N1','+1 (780) 428-9482','+1 (780) 428-3457','andrew@chinookcorp.com');

INSERT INTO public."Customer" ("CustomerId","FirstName","LastName","Company","Address","City","State","Country","PostalCode","Phone","Fax","Email","SupportRepId") VALUES 
(60,'Joakim','Johansson',NULL,'Celsiusg. 9','Stockholm',NULL,'Sweden','11230','+46 08-651 52 52',NULL,'joakim.johansson@yahoo.se',5)
,(61,'Puja','Srivastava',NULL,'3,Raj Bhavan Road','Bangalore',NULL,'India','560001','+91 080 22289999',NULL,'puja_srivastava@yahoo.in',3)
;

update "Customer"
set "FirstName"='Robert',
	"LastName"= 'Walter'
where "FirstName" = 'Aaron' and 
	"LastName" = 'Mitchell';
	
update "Artist"
set "Name" = 'CCR'
where "Name" = 'Creedence Clearwater Revival';

select c."FirstName", c."LastName", i."InvoiceId" 
from "Customer" c inner join "Invoice" i on c."CustomerId" = i."CustomerId";

select c."CustomerId", c."FirstName", c."LastName", i."InvoiceId", i."Total" 
from "Customer" c full outer join "Invoice" i on c."CustomerId" = i."CustomerId";

select a."Name", b."Title"
from "Artist" a right join "Album" b on a."ArtistId" = b."ArtistId";

select *
from "Artist" a cross join "Album" b
order by a."Name";

select *
from "Employee" e join "Employee" b on e."ReportsTo" = b."ReportsTo" ;

select "FirstName" || ' ' || "LastName" as "FULL_NAME", sum(i."Total") as "TOTAL"
from "Customer" c join "Invoice" i on c."CustomerId" = i."CustomerId"
group by "FULL_NAME";

select e."FirstName" || ' ' || e."LastName" as "FULL_NAME", sum(i."Total") as "TOTAL"
from "Employee" e join "Customer" c on e."EmployeeId" = c."SupportRepId"
	join "Invoice" i on c."CustomerId" = i."CustomerId"
group by "FULL_NAME"
order by "TOTAL" desc;

select g."Name", sum(i2."Quantity") as "Purchases"
from "Customer" c join "Invoice" i1 on c."CustomerId" = i1."CustomerId" 
	join "InvoiceLine" i2 on i1."InvoiceId" = i2."InvoiceId"
	join "Track" t on i2."TrackId" = t."TrackId" 
	join "Genre" g on g."GenreId" = t."GenreId" 
group by g."Name"
order by "Purchases" desc;

create or replace function 
avgInvoice() returns numeric as $$
	select avg("Total") as AverageTotal
	from "Invoice" i; 
$$ language sql;

select avgInvoice();

-- drop function after1986();

create or replace function 
after1968() returns setof public."Employee" as $$
	select * 
	from "Employee"
	where "BirthDate" > '1968-01-01'; 
$$ language sql;

select after1968();

-- DROP FUNCTION getmanager(integer);

create or replace function 
getManager(emp_id int) returns "Employee" as $$
	select * 
	from "Employee" e2 
	where e2."EmployeeId" = (
		select "ReportsTo"
		from "Employee" e
		where e."EmployeeId" = emp_id
	);
$$ language sql;

select getManager(2);

create or replace function 
getPlaylistPrice(play_id int) returns numeric as $$
	select sum(il."UnitPrice") 
	from "InvoiceLine" il 
		join "Track" t on il."TrackId" = t."TrackId"
		join "PlaylistTrack" pt on t."TrackId" = pt."TrackId"
	where pt."PlaylistId" = play_id
$$ language sql;

select getPlaylistPrice(1);