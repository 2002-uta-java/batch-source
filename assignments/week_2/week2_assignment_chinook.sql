/* Josue Ortiz
 * 02/12/2020
 * chinook assignment
 * 
 * 
 * 2.0 Queries and DML
 * 
 */

/*
 * 2.1 SELECT
 */

-- a. Select all records from the Employee table. Using * to select all the coloumns in the table.
select *
from "Employee";

-- b. Select all records from the Employee table where last name is King. Using e as an alias for table Employee
select *
from "Employee" e
where e."LastName" = 'King';

-- c. Select all albums in Album table and sort result set in descending order by title. Used desc to order in decending order
select *
from "Album" a
order by a."Title" desc;

-- d. Select first name from Customer and sort result set in ascending order by city. Specified which coloumn we wanted after select. Used asc for acending ordering
select "FirstName"
from "Customer" c
order by c."City" asc;

-- e. Select all invoices with a billing address like “T%”. Used like to find in Invoice.BillingAddress adresses that are like 'T%'. The % works as a wildcard so that anything can follow T
select *
from "Invoice" i
where i."BillingAddress" like 'T%';

-- f. Select the name of the longest track. In the where clause, I used a subquery to find the highest value in the Milliseconds coloumn. I then used that value to find a match in the main query.
select "Name"
from "Track" t
where t."Milliseconds" = (select max("Milliseconds") from "Track");

-- g. Find the average invoice total. Used the avg aggregate function to take the average of the values in the Total coloumn
select avg("Total")
from "Invoice" i;

-- h. Find the total number of employees in each position. Used the count function in conjunction with the group by clause to count the number of times that a certain title was found.
-- that way the titles only show once, but the count function keeps track of each instance of the title. Changes the coloumn name with the 'as' keyword
select "Title",
count("Title") as "number of employees"
from "Employee" e
group by e."Title";

/*
 * 2.2 INSERT INTO
 */

-- a. Insert two new records into Genre table. Used individual insert into statements to add genres. I specified the id to use.
insert into "Genre" ("GenreId","Name") values (26, 'Gospel');
insert into "Genre" ("GenreId","Name") values (27, 'Bluegrass');

-- b. Insert two new records into Employee table. Same as in step 2.2 - a.
insert into "Employee" ("EmployeeId", "LastName", "FirstName", "Title", "BirthDate", "HireDate", "Address", "City", "State", "Country", "PostalCode", "Phone", "Fax", "Email") values (9, 'Smith', 'John', 'IT Staff', '1988/4/25','2005/3/1', '1523 A Preston Rd.', 'Dallas', 'TX', 'USA', '70543', '(555) 555-5555', '(555) 555-0000', 'john@chinookcorp.com');
insert into "Employee" ("EmployeeId", "LastName", "FirstName", "Title", "BirthDate", "HireDate", "Address", "City", "State", "Country", "PostalCode", "Phone", "Fax", "Email") values (10, 'Fortinbras', 'Blueford', 'Grounds', '1985/7/7','2007/4/1', '444 Shiloh Rd.', 'Addison', 'TX', 'USA', '70533', '(555) 555-5555', '(555) 555-0000', 'blueford@chinookcorp.com');

-- c. Insert two new records into Customer table. Same as in step 2.2 - a.
insert into "Customer" ("CustomerId", "FirstName", "LastName", "Company", "Address", "City", "State", "Country", "PostalCode", "Phone", "Fax", "Email", "SupportRepId") values (60, 'James', 'Cooley', 'Revature Music', '456 N Green St.', 'Sachse', 'TX', 'USA', '75048', '(555) 550-0000', '(555) 550-0001', 'jc@revmusic.com', 5);
insert into "Customer" ("CustomerId", "FirstName", "LastName", "Company", "Address", "City", "State", "Country", "PostalCode", "Phone", "Fax", "Email", "SupportRepId") values (61, 'Sandra', 'Nightingale', 'Water Music', '2B N i-10', 'San Antonio', 'TX', 'USA', '73048', '(555) 550-0005', '(555) 550-0041', 'sandra.nightingale@watermusic.com', 5);

/*
 * 2.3 UPDATE
 */

-- a. Update Aaron Mitchell in Customer table to Robert Walter. Used an update .. set statement to change the coloumn values. The where clause species which entry to change by matching the first and last name
update "Customer"
set "FirstName" = 'Robert', "LastName" = 'Walter'
where "FirstName" = 'Aaron' and "LastName" = 'Mitchell';


-- b. Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”. Used same procedure as 2.3 - a
update "Artist"
set "Name" = 'CCR'
where "Name" = 'Creedence Clearwater Revival'



/*
 * 
 * 3.0 Joins
 * 
 */


/*
 * 3.1 INNER
 */

-- a. Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId. Concated customer name using ||. Joined tables using the customer id.
select c."FirstName" || ' ' || c."LastName" as "customer",
i."InvoiceId" 
from "Customer" c
join "Invoice" i on c."CustomerId" = i."CustomerId";


/*
 * 3.2 OUTER
 */

-- a. Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
select c."CustomerId" ,
c."FirstName" ,
c."LastName" ,
i."InvoiceId" ,
i."Total" 
from "Customer" c 
left join "Invoice" i on c."CustomerId" = i."CustomerId"


/*
 * 3.3 RIGHT
 */

-- a. Create a right join that joins album and artist specifying artist name and title.
select a2."Name" ,
a1."Title" 
from "Album" a1
join "Artist" a2 on a1."ArtistId" = a2."ArtistId";


/*
 * 3.4 CROSS
 */

-- a. Create a cross join that joins album and artist and sorts by artist name in ascending order.
select *
from "Album" a1
cross join "Artist" a2
order by a2."Name" asc;

/*
 * 3.5 SELF
 */

-- a. Perform a self-join on the employee table, joining on the reportsto column.
select *
from "Employee" e
join "Employee" e1 on e."ReportsTo" = e1."ReportsTo" ;


/*
 * 3.6 Joined Queries
 */

-- a. Create a query that shows the customer first name and last name as FULL_NAME (you can use || to concatenate two strings) with the total amount of money they have spent as TOTAL.
select c."FirstName" || ' ' || c."LastName" as "FULL_NAME",
sum(i."Total")
from "Customer" c
join "Invoice" i on c."CustomerId" = i."CustomerId"
group by c."CustomerId";


-- b. Create a query that shows the employee that has made the highest total value of sales (total of all invoices).

select *
from (select e."FirstName" || ' ' ||	e."LastName" as Employee_Name, 
		sum(i."Total") as total_sales
		from "Customer" c
		join "Invoice" i on i."CustomerId" = c."CustomerId"
		join "Employee" e on e."EmployeeId" = c."SupportRepId"
		group by e."EmployeeId"
	) as first_query
where first_query.total_sales =
	(select max(second_query.total_sales)
		from (select sum(i."Total") as total_sales
		from "Customer" c
		join "Invoice" i on i."CustomerId" = c."CustomerId"
		join "Employee" e on e."EmployeeId" = c."SupportRepId"
		group by e."EmployeeId") second_query
	);

create view yo as
select e."FirstName" || ' ' ||	e."LastName" as Employee_Name, 
		sum(i."Total") as total_sales
		from "Customer" c
		join "Invoice" i on i."CustomerId" = c."CustomerId"
		join "Employee" e on e."EmployeeId" = c."SupportRepId"
		group by e."EmployeeId";


-- c. Create a query which shows the number of purchases per each genre. Display the name of each genre and number of purchases. Show the most popular genre first.
select gr."Name",
sum(il."Quantity") as "sold"
from "InvoiceLine" il
left join "Track" tr on il."TrackId" = tr."TrackId"
right join "Genre" gr on tr."GenreId" = gr."GenreId"
group by gr."GenreId"
order by sold desc nulls last;



/*
 * 
 * 4.0 User Defined Functions
 *
 */

-- a. Create a function that returns the average total of all invoices.
create function averagetotalofallinvoices()
returns numeric as $$
select avg(i."Total")
from "Invoice" i;
$$ language sql;

select averagetotalofallinvoices();

-- b. Create a function that returns all employees who are born after 1968.
create function bornafter1968()
returns table (names text) as $$
select e."FirstName" || ' ' || e."LastName"
from "Employee" e
where e."BirthDate" > '12/31/1968';
$$ language sql;

select bornafter1968();

-- c. Create a function that returns the manager of an employee, given the id of the employee.
create function employeemanager(n int)
returns text as $$
select e."FirstName" || ' ' || e."LastName"
from "Employee" e
where e."EmployeeId" = (
	select e2."ReportsTo" 
	from "Employee" e2 
	where e2."EmployeeId" = n
	);
$$ language sql;

select employeemanager(9);

-- d. Create a function that returns the price of a particular playlist, given the id for that playlist.
create function priceofplaylist(plid int)
returns numeric as $$
select sum(t."UnitPrice")
from "PlaylistTrack" pt
join "Track" t on t."TrackId" = pt."TrackId"
where pt."PlaylistId" = plid;
$$ language sql;

select priceofplaylist(1);