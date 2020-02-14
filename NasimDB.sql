
---------------a----------------

select * from "Employee" e ;

--------------b------------------------------
select *from "Employee" e where e."LastName" = 'King';

-----------------c-----------------------------------

select *from "Album" a order by "Title" desc ;

-----------------d--------------------------------

select "FirstName" from "Customer" c order by "City" asc ;

---------------e---------------------------------

select * from "Invoice" i
where i."BillingAddress" like 'T%' ;

-----------------f-----------------------------------

select "Name" from "Track" t 
where "Milliseconds" = (select max("Milliseconds") from "Track" );

--------------g-----------------------------------------

select avg("Total" ) from "Invoice" i ;

--------------h----------------------
select * from "Employee" e2 ;
select count(employee) 

-----------------2_2 ---------------------------------------
------------------a--------------------------

insert into "Genre" ("GenreId" ,"Name") values (26,'persian');
insert into "Genre" ("GenreId" ,"Name") values (27 ,'theater');

------------------b----------------------------------------


INSERT INTO "Employee" 
("EmployeeId", "LastName", "FirstName", "Title", "BirthDate", "HireDate", "Address", "City", "State", "Country", "PostalCode", "Phone", "Fax", "Email")
VALUES (9, N'Nasim', N'Heidarian', N'General Manager', '1962/2/18', '2002/8/14', N'13021,legendary dr', N'austin', N'tx', N'usa', N'78727', N'+1 (512) 817-9611', N'+1 (512) 817-9611', N'nasim.heidarian@gmail.com');

INSERT INTO "Employee" 
("EmployeeId", "LastName", "FirstName", "Title", "BirthDate", "HireDate", "Address", "City", "State", "Country", "PostalCode", "Phone", "Fax", "Email")
VALUES (10, N'Mohammad', N'Tayyebi', N'General Manager', '1962/2/18', '2002/8/14', N'13021,legendary dr', N'austin', N'tx', N'usa', N'78727', N'+1 (512) 713-6825', N'+1 (512) 713-6825', N'nasim.heidarian@gmail.com');

-------------------c---------------------------------------------------------

INSERT INTO "Customer" 
("CustomerId", "FirstName", "LastName", "Company", "Address", "City", "State", "Country", "PostalCode", "Phone", "Fax", "Email", "SupportRepId")
VALUES (60, N'lynn', N'hug', N'Engineer Austin', N'1813,georgtown,austin,tx', N'Austins', N'tx', N'usa', N'78727', N'=1 ', N'+1 (512) 817-9611', N'engineer@gmail.com', 3);

INSERT INTO "Customer" 
("CustomerId", "FirstName", "LastName", "Company", "Address", "City", "State", "Country", "PostalCode", "Phone", "Fax", "Email", "SupportRepId")
VALUES (61, N'niky', N'tay', N'best dental', N'1821,georgtown,austin,tx', N'Austins', N'tx', N'usa', N'78727', N'=1 ', N'+1 (765) 532-2710', N'niky@gmail.com', 4);

-------------------2-3------------------------------------------------
--------------------a-------------------------------------------------
update "Customer" 
set
"FirstName"='Robert',"LastName"='Walter'
where 
"FirstName"='Aaron'and "LastName"='Mitchell';
----------------------b-------------------------------------------------
update "Artist" 
set 
"Name" = 'CCR'where "Name" ='Creedence Clearwater Revival';
-------------------3.1-----------------------------------------------
select c."FirstName", c."LastName",i."InvoiceId" from "Customer" c 
inner join "Invoice" i
on c."CustomerId" = i."CustomerId" ;

------------------3.2-----------------------------------------------

select c."FirstName" ,c."LastName", c."CustomerId",i."InvoiceId" ,i."Total" from "Customer" c 
outer join "Invoice" i
on c."CustomerId" = i."CustomerId" ;
--------------------------3.3--------------------------
select a."Title",a1."Name",a1."ArtistId" from "Album" a 
right outer join "Artist" a1
on a."ArtistId" =a1."ArtistId" ;

---------------------------3.4------------------------------
select a."Title", a1."Name" from "Album" a 
cross join "Artist"a1 
order by a1."Name" asc ;
----------------------------3.5----------------------
select * from "Employee" e 
join "Employee" e1
on e."ReportsTo" = e1."ReportsTo";

-----------------------------3.6-------------------------
----------------------------a----------------------------

select (c."FirstName" || c."LastName" ) as "FULL_NAME", sum(i."Total" ) as "TOTAL"
from "Customer" c
inner join "Invoice" i
on c."CustomerId" = i."CustomerId"
group by c."CustomerId";


------------------------------b----------------------------------
select (e."FirstName" || e."LastName" ) as "FULL_NAME", sum(i."Total") as "TOTAL"
from "Employee" e 
join "Customer" c
on e."EmployeeId" = c."SupportRepId" 
join "Invoice" i
on c."CustomerId" = i."CustomerId"
group by "EmployeeId" ;

create view employeehighest as select (e."FirstName" || e."LastName" ) as "FULL_NAME", sum(i."Total") as "TOTAL"
from "Employee" e 
join "Customer" c
on e."EmployeeId" = c."SupportRepId" 
join "Invoice" i
on c."CustomerId" = i."CustomerId"
group by "EmployeeId" ;

select * from employeehighest e
where e."TOTAL"= (select Max(e2."TOTAL") from employeehighest e2) ;
--------------------------------c----------------------------------------------------

select g."Name" from "Genre" g ,count(t."TrackId")
join "Track" t on g."GenreId" = t."GenreId" 
order by t."GenreId" ;

--------------------------------------------4-------------------------------------
--------------------------------------a---------------------------------

create or replace function find_avg_invo()
returns numeric(7,2)
language plpgsql
as $$
declare
	avg_invo numeric(7,2);
begin
	select round(avg(i."Total"),2) into avg_invo 
	from "Invoice" i ;
	return avg_invo ;
end
$$

select find_avg_invo();

----------------------------------------b---------------------------------------






