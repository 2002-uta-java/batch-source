select * from "Employee"; 

-- employees with the last name King
select * from "Employee" where "LastName"='King';

-- order the album table by title in descending order
select * from "Album" order by "Title" desc;

-- get the customer's first name and order results by City in ascending order
select "FirstName" from "Customer" order by "City" asc;

-- get invoice billing address where the address is similar to T
select * from "Invoice" where "BillingAddress" like 'T%';

-- get the longest song in the track table
select "Name" from "Track" where "Milliseconds"=(select
	MAX("Milliseconds") from "Track"
);

-- get the average total from the invoice table
select AVG("Total") as "Average Invoice" from "Invoice";

-- get total number of employees in each position
select "Title",count(*) from "Employee" group by "Title";

select * from "Genre";

-- add two new genres to genre table
insert into "Genre" values(26,'Minimal Synth');
insert into "Genre" values(27,'Industrial Techno');

select * from "Employee";

-- add two new employees to employee table
insert into "Employee" values(9,'Quintero','Milagro','IT Staff',null,'1996-11-11 11:11:00','2020-02-11 11:00:00',null,'Austin','TX','USA','78758',null,null,'mila@gmail.com');
insert into "Employee" values(10,'Cline','Talulah','IT Staff',null,'1998-08-05 08:15:00','2020-02-11 11:00:00',null,'Austin','TX','USA','78758',null,'lula@gmail.com');

select * from "Customer";

--add two new customers to customer table
INSERT INTO "Customer" ("CustomerId", "FirstName", "LastName", "Company", "Address", "City", "State", "Country", 
"PostalCode","Email") 
VALUES (60, N'Saul', N'Quintero', N'Hylic Merit', null, 
N'Waco', N'Texas', N'USA', N'78626','saul@gmail.com'); 

INSERT INTO "Customer" ("CustomerId", "FirstName", "LastName", "Company", "Address", "City", "State", "Country", 
"PostalCode","Email") 
VALUES (61, N'Kamilo', N'Kami', N'Bala Club', null, 
N'Brixton', N'England', N'United Kingdom', N'77777','kingkami@gmail.com'); 

-- update Aaron Mitchell's name to Robert Walter in customer table
update "Customer" set "FirstName"='Robert',"LastName"='Walter' where "FirstName"='Aaron' and "LastName"='Mitchell';

-- update Creedence Clearwater Revival to CCR in artist table
update "Artist" set "Name"='CCR' where "Name"='Creedence Clearwater Revival';

-- join customer and invoice where both table's customerid columns match and show first name, last name and invoice id
select "FirstName","LastName","InvoiceId"
from "Customer" inner join "Invoice"
on "Customer"."CustomerId"="Invoice"."CustomerId";

/* 
 * join the entire customer and invoice where both table's customerid and non matches
 * show the customer first and last name, and the invoice id and total
*/
select "Customer"."CustomerId","Customer"."FirstName","Customer"."LastName","Invoice"."InvoiceId","Invoice"."Total"
from "Customer" full outer join "Invoice"
on "Customer"."CustomerId"="Invoice"."CustomerId";

-- join the artist and album table when the artist id's match (show results for albums with no match)
-- show album title and artist name 
select "Artist"."Name","Album"."Title"
from "Artist" right outer join "Album"
on "Artist"."ArtistId"="Album"."ArtistId";

-- cross join album and artist table and order by artist name in ascending order
select *
from "Album"
cross join "Artist" order by "Artist"."Name" asc;

-- self join of employee table when reportto columns match in value
select *
from "Employee" "A","Employee" "B"
where "A"."ReportsTo"="B"."ReportsTo";

-- join customer and invoice when customer id's match
-- show first and last name as FullName and sum of their purchases
select concat("Customer"."FirstName","Customer"."LastName") as "Full Name",
sum("Invoice"."Total") as "Total"
from "Customer" inner join "Invoice"
on "Customer"."CustomerId"="Invoice"."CustomerId"
group by "Customer"."CustomerId","Invoice"."CustomerId";

/*Creating view for total each customer has spent along with their id and their support rep's id*/
create view "Sales" as
select "Employee"."FirstName",sum("Invoice"."Total") as "Total"
from "Customer" 
join "Invoice"
on "Customer"."CustomerId"="Invoice"."CustomerId"
join "Employee"
on "Customer"."SupportRepId"="Employee"."EmployeeId"
group by "Employee"."FirstName";

drop view "Sales";

select * from "Sales";

-- select the employee who has made the most sales
select *
from "Sales"
where (select max("Total") from "Sales")="Total";


select * from "InvoiceLine";

select g."Name" genre_name,
sum(il."Quantity") sold
from "InvoiceLine" il
full outer join "Track" t
on il."TrackId"= t."TrackId"
full outer join "Genre" g
on g."GenreId"= t."GenreId"
group by genre_name
order by sold desc
nulls last;


