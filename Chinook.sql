-- Gets all of the entries from Employee
select * from "Employee" e;
-- Gets all columns from entry with 'KING' as last name
select * from "Employee" e where "LastName" = 'King';
-- Gets all entries from album and sorts it in descending title order
select * from "Album" a order by "Title" DESC;
-- Gets first name from customer and sorts by ascending city
select "FirstName" from "Customer" c order by "City" asc;
-- Gets all from invoices where billing address has a T
select * from "Invoice" i where "BillingAddress" like 'T%';
-- Gets the name of the longest track
select "Name" from "Track" t where "Milliseconds"  = (select max("Milliseconds") from "Track" t2);
-- Gets the average invoice total
select avg("Total") from "Invoice" i;
-- Gets total employees in each position
select "Title", count("EmployeeId") from "Employee" e group by "Title";
-- insert new records into Genre table
INSERT INTO "Genre" ("GenreId", "Name") VALUES (26, N'EDM');
INSERT INTO "Genre" ("GenreId", "Name") VALUES (27, N'House');
-- insert new records into Employee table
INSERT INTO "Employee" ("EmployeeId", "LastName", "FirstName", "Title", "BirthDate", "HireDate", "Address", "City", "State", "Country", "PostalCode", "Phone", "Fax", "Email") 
VALUES (9, N'Tigers', N'Nigel', N'Associate Sales', '1970/4/18', '2010/8/20', N'1892 Seaworth Ln', N'Edmonton', N'AB', N'Canada', N'T5K 2N1', N'+1 (780) 428-9485',
N'+1 (780) 428-3483', N'nigelT@chinookcorp.com');
INSERT INTO "Employee" ("EmployeeId", "LastName", "FirstName", "Title", "BirthDate", "HireDate", "Address", "City", "State", "Country", "PostalCode", "Phone", "Fax", "Email") 
VALUES (10, N'Woods', N'Richard', N'Janitor', '1932/11/22', '2015/10/14', N'115221 Hearthenhall LN', N'Edmonton', N'AB', N'Canada', N'T5K 2N1', N'+1 (780) 428-9592',
N'+1 (780) 428-4546', N'richardWood@chinookcorp.com');
-- insert new records into Customer
INSERT INTO "Customer" ("CustomerId", "FirstName", "LastName", "Address", "City", "State", "Country", "PostalCode", "Phone", "Email", "SupportRepId") 
VALUES (60, N'John', N'Snow', N'621 Mans Rader Ct', N'Mountain View', N'CA', N'USA', N'94040-111', N'+1 (650) 644-3375', N'dragonRider@comcast.com', 3);
INSERT INTO "Customer" ("CustomerId", "FirstName", "LastName", "Address", "City", "State", "Country", "PostalCode", "Phone", "Email", "SupportRepId") 
VALUES (61, N'Daenerys', N'Stormborn', N'871 Targaryen Rd', N'Mountain View', N'CA', N'USA', N'94040-111', N'+1 (650) 644-4848', N'IAMFIREIAMDEATH@comcast.com', 5);
-- update Customer table Aaron Mitchell to Robert Walter
update "Customer" set "FirstName" = 'Robert', "LastName" = 'Walter' where "FirstName" = 'Aaron';
-- update Creedence Clearwater Revival to CCR
update "Artist" set "Name" = 'CCR' where "Name" = 'Creedence Clearwater Revival';
-- outer join of Customer and Invoice giving CustomerID, firstname, lastname, invoiceID, and total
