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



