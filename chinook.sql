select *
from "Employee";
select *
from "Employee"
where "LastName" = 'King';
select *
from "Album"
order by "AlbumId" desc;
select "FirstName"
from "Customer"
order by "City" asc;
select *
from "Invoice"
where ("BillingAddress" like 'T%');
select "Name"
from "Track"
where "Milliseconds" = (select max("Milliseconds")
from "Track");
select avg("Total")
from "Invoice";
select "Title", count(*)
from "Employee"
group by "Title";

insert into "Employee"
    ("EmployeeId" ,"LastName" , "FirstName" , "Title" , "ReportsTo" , "BirthDate" , "HireDate" , "Address" , "City" , "State" , "Country" , "PostalCode" , "Phone" , "Fax" , "Email" )
values
    (25, 'Klimavicius', 'Vytautas', 'Manager', 1, '1983-03-01', '2002-03-12', '550 street', 'New york', 'New york', 'USA', '11104', '9293019100', '929345678', 'vyklimavicius@gmail.com');
insert into "Employee"
    ("EmployeeId" ,"LastName" , "FirstName" , "Title" , "ReportsTo" , "BirthDate" , "HireDate" , "Address" , "City" , "State" , "Country" , "PostalCode" , "Phone" , "Fax" , "Email" )
values
    (29, 'Molina', 'Richard', 'VP', 3, '1984-03-01', '2004-03-12', '657 street', 'New york', 'New york', 'USA', '11104', '9293019100', '929345678', 'molina@gmail.com');

insert into "Customer"
    ("CustomerId" ,"FirstName" , "LastName" , "Company" , "Address" , "City" , "State" , "Country" , "PostalCode" , "Phone" , "Fax" , "Email", "SupportRepId" )
values
    (120, 'Marston', 'John', 'KlimaCorp', '550 street', 'New york', 'New york', 'USA', '11104', '9293019100', '929345678', 'vyklimavicius@gmail.com', 5);
insert into "Customer"
    ("CustomerId" ,"FirstName" , "LastName" , "Company" , "Address" , "City" , "State" , "Country" , "PostalCode" , "Phone" , "Fax" , "Email", "SupportRepId" )
values
    (121, 'Fox', 'John', 'KlimaCorp', '550 street', 'New york', 'New york', 'USA', '11104', '9293019100', '929345678', 'fox@gmail.com', 5);

update "Customer" set "FirstName" = 'Robert', "LastName" = 'Walter' where "CustomerId" = 32;
update "Artist" set "Name" = 'CCR' where "Name" = 'Creedence Clearwater Revival';

select "Invoice"."InvoiceId", "Customer"."FirstName"
from "Invoice" inner join "Customer" on "Invoice"."CustomerId" = "Customer"."CustomerId";
select "Customer"."FirstName", "Customer"."LastName", "Invoice"."InvoiceId"
from "Customer" full outer join "Invoice" on "Customer"."CustomerId" = "Invoice"."CustomerId"
order by "Invoice"."Total";

select "Album"."Title", "Artist"."Name"
from "Album" right join "Artist" on "Album"."ArtistId" = "Artist"."ArtistId";


select *
from "Album" cross join "Artist"
order by "Artist"."Name";


select *
from "Employee" as "Employee" join "Employee" as "Manager" on "Employee"."ReportsTo" = "Manager"."EmployeeId";


select "FirstName" || ' ' || "LastName" as "FULL_NAME", sum("Total") as "TOTAL"
from "Customer" join "Invoice" on "Invoice"."CustomerId" = "Customer"."CustomerId"
group by "Customer"."CustomerId";


select "Employee"."FirstName" || ' ' || "Employee"."LastName" as "EMPLOYEE_NAME", sum("Invoice"."Total") as "TOTAL_SALES"
from "Employee" join "Customer" on "Customer"."SupportRepId" = "Employee"."EmployeeId" join "Invoice" on "Invoice"."CustomerId" = "Customer"."CustomerId"
group by "Employee"."EmployeeId"
order by "TOTAL_SALES" desc limit 1;

select "Genre"
."Name", count
("InvoiceLine"."InvoiceLineId") as "NUM_PURCHASES" from "Genre" join "Track" on "Track"."GenreId" = "Genre"."GenreId" join "InvoiceLine" on "InvoiceLine"."TrackId" = "Track"."TrackId" group by "Genre"."GenreId" order by "NUM_PURCHASES" desc;


create function calculateAvgTotalInvoices()
returns numeric(7,2)
language plpgsql
as $$
declare
	sum_total numeric
(7,2);
	num_invoices numeric
(7,2);
begin
    select sum("Invoice"."Total")
    into sum_total
    from "Invoice";

    select count("Invoice"."InvoiceId")
    into num_invoices
    from "Invoice";

    return sum_total / num_invoices;

end
$$


create or replace function empBornAfter1968
()
returns setof "Employee"
language plpgsql
as $$
begin
    return query
    select *
    from "Employee"
    where "BirthDate" > timestamp
    '1968-12-31 00:00:00';
end
$$

create or replace function getManager
(employee_id_input "Employee"."EmployeeId"%type)
returns setof "Employee"
language plpgsql
as $$
declare 
	manager_id "Employee"."EmployeeId"%type;
begin
    select m."EmployeeId"
    into manager_id
    from "Employee" as e
        join "Employee" as m
        on e."ReportsTo" = m."EmployeeId"
    where e."EmployeeId" = employee_id_input;

    return query
    select *
    from "Employee"
    where "Employee"."EmployeeId" = manager_id;
end
$$


create or replace function getPriceOfPlaylist
(playlist_id_input "Playlist"."PlaylistId"%type)
returns "Track"."UnitPrice"%type
language plpgsql
as $$
declare
	price "Track"."UnitPrice"%type;
begin
    select "Track"."UnitPrice"
    into price
    from "Playlist"
        join "PlaylistTrack"
        on "Playlist"."PlaylistId" = "PlaylistTrack"."PlaylistId"
        join "Track"
        on "Track"."TrackId" = "PlaylistTrack"."TrackId"
    where "Playlist"."PlaylistId" = playlist_id_input;
    return price;
end
$$