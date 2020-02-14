-- 2.1 SELECT
-- a. Select all records from the Employee table.
select *
from "Employee";
-- b. Select all records from the Employee table where the last name is King.
select *
from "Employee"
where "LastName" = 'King';
-- c. Select all albums in Album table and sort result set in descending order by title.
select *
from "Album"
order by "Title" desc;
-- d. Select first name from Customer and sort result set in ascending order by city.
select "FirstName"
from "Customer"
order by "City" asc;
-- e. Select all invoices with a billing address like "T%".
select *
from "Invoice"
where "BillingAddress" like 'T%';
-- f. Select the name of the longest track.
select "Name", "Milliseconds"
from "Track"
where "Milliseconds" =
	(select min("Milliseconds")
	from "Track");
-- g. Find the average invoice total.
select avg("Total")
from "Invoice";
-- h. Find the total number of emplyees in each position.
select count("EmployeeId")
from "Employee";

-- 2.2 INSERT INTO
-- Insert two new records into Genre table

-- Insert two new records into Employee table

-- Insert two new records into Customer table
 
-- 2.3 UPDATE
-- a. Update Aaron Mitchell in Customer table to Robert Walter.

-- b. Update name of artist in the Artist table "Creedence Clearwater Revival" to "CCR".

-- 3.1 Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.

-- 3.2 Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.

-- 3.3 Create a right join that joins album and arist specifying arist name and title.

-- 3.4 Create a cross join that joins album and artist and sorts by artist name in ascending order.

-- 3.5 Perform a self-join on the employee table, joining on the reportsto column.

-- 3.6 JOINED QUERIES
-- a. Create a query that shows the customer first name and last name as FULL_NAME (you can use|| to concatenate two strings) with the total amount of money they have spent as TOTAL.

-- b. Create a query that shows the employee that has made the highest total value of sales (total ofall invoices).

-- c. Create a query which shows the number of purchases per each genre. Display the name of eachgenre and number of purchases. Show the most popular genre first

-- 4.0 USER DEFINED FUNCTIONS
-- a. Create a function that returns the average total of all invoices.

-- b. Create a function that returns all employees who are born after 1968.

-- c. Create a function that returns the manager of an employee, given the id of the employee.

-- d. Create a function that returns the price of a particular playlist, given the id for that playlist.

