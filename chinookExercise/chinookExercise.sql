show search_path;
set search_path to chinook;
--2.1.a Select all records from the Employee table.
select * from "Employee";

--2.1.b Select all records from the Employee table where last name is King.
select * from "Employee" where "LastName" = 'King';

--2.1.c Select all albums in Album table and sort result set in descending order by title.
select * from "Album" order by "Title" desc;

--2.1.d Select first name from Customer and sort result set in ascending order by city.
select "FirstName" from "Customer" order by "City";

--2.1.e Select all invoices with a billing address like “T%”.
select * from "Invoice" where "BillingAddress" like 'T%';

--2.1.f Select the name of the longest track.
select "Name" from "Track" order by "Milliseconds" desc limit 1;

--2.1.g Find the average invoice total.
select AVG("Total") from "Invoice";

--2.1.h Find the total number of employees in each position.
select COUNT("EmployeeId"), "Title" from "Employee" group by "Title";

--2.2.a Insert two new records into Genre table
insert into "Genre" ("Name", "GenreId") values ('我最棒', '26');
insert into "Genre" ("Name", "GenreId") values ('你傻逼', '27');

--2.2.b Insert two new records into Employee table
INSERT INTO "Employee" ("EmployeeId", "LastName", "FirstName", "Title", "BirthDate", "HireDate", "Address", "City", "State", "Country", "PostalCode", "Phone", "Fax", "Email") VALUES (9, N'Adams', N'Andrew', N'General Manager', '1962/2/18', '2002/8/14', N'11120 Jasper Ave NW', N'Edmonton', N'AB', N'Canada', N'T5K 2N1', N'+1 (780) 428-9482', N'+1 (780) 428-3457', N'andrew@chinookcorp.com');
INSERT INTO "Employee" ("EmployeeId", "LastName", "FirstName", "Title", "BirthDate", "HireDate", "Address", "City", "State", "Country", "PostalCode", "Phone", "Fax", "Email") VALUES (10, N'Adams', N'Andrew', N'General Manager', '1962/2/18', '2002/8/14', N'11120 Jasper Ave NW', N'Edmonton', N'AB', N'Canada', N'T5K 2N1', N'+1 (780) 428-9482', N'+1 (780) 428-3457', N'andrew@chinookcorp.com');

--2.2.c Insert two new records into Customer table 
INSERT INTO "Customer" ("CustomerId", "FirstName", "LastName", "Company", "Address", "City", "State", "Country", "PostalCode", "Phone", "Fax", "Email", "SupportRepId") VALUES (60, N'Lu�s', N'Gon�alves', N'Embraer - Empresa Brasileira de Aeron�utica S.A.', N'Av. Brigadeiro Faria Lima, 2170', N'S�o Jos� dos Campos', N'SP', N'Brazil', N'12227-000', N'+55 (12) 3923-5555', N'+55 (12) 3923-5566', N'luisg@embraer.com.br', 3);
INSERT INTO "Customer" ("CustomerId", "FirstName", "LastName", "Company", "Address", "City", "State", "Country", "PostalCode", "Phone", "Fax", "Email", "SupportRepId") VALUES (61, N'Lu�s', N'Gon�alves', N'Embraer - Empresa Brasileira de Aeron�utica S.A.', N'Av. Brigadeiro Faria Lima, 2170', N'S�o Jos� dos Campos', N'SP', N'Brazil', N'12227-000', N'+55 (12) 3923-5555', N'+55 (12) 3923-5566', N'luisg@embraer.com.br', 3);

--2.3.a Update Aaron Mitchell in Customer table to Robert Walter
update "Customer" set "FirstName" = 'Robert', "LastName" = 'Walter' where "FirstName" = 'Aaron' and "LastName" = 'Mitchell';

--2.3.b Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
update "Artist" set "Name" = 'CCR' where "Name" = 'Creedence Clearwater Revial';

--3.1.a Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
select "LastName", "FirstName", "InvoiceId" from "Customer" join "Invoice" on "Invoice"."CustomerId" = "Customer"."CustomerId";

--3.2.a Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
select "Customer"."CustomerId", "LastName", "FirstName", "InvoiceId", "Total" from "Customer" full join "Invoice" on "Invoice"."CustomerId" = "Customer"."CustomerId";

--3.3.a Create a right join that joins album and artist specifying artist name and title.
select "Artist"."Name", "Album"."Title" from "Album" right join "Artist" on "Album"."ArtistId" = "Artist"."ArtistId";

--3.4.a Create a cross join that joins album and artist and sorts by artist name in ascending order.
select * from "Album" cross join "Artist" order by "Artist"."Name";

--3.5.a Perform a self-join on the employee table, joining on the reportsto column.	
select * from "Employee" as "Employee" join "Employee" as "Manager" on "Employee"."ReportsTo" = "Manager"."EmployeeId";

--3.6.a Create a query that shows the customer first name and last name as FULL_NAME (you can use || to concatenate two strings) with the total amount of money they have spent as TOTAL.
select "FirstName" || ' ' || "LastName" as "FULL_NAME", sum("Total") as "TOTAL" from "Customer" join "Invoice" on "Invoice"."CustomerId" = "Customer"."CustomerId" group by "Customer"."CustomerId";

--3.6.b Create a query that shows the employee that has made the highest total value of sales (total of all invoices).
select "Employee"."FirstName" || ' ' || "Employee"."LastName" as "FULL_NAME", sum("Invoice"."Total") as "TOTAL_SALES" from "Employee" join "Customer" on "Customer"."SupportRepId" = "Employee"."EmployeeId" 









