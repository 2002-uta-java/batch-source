
/************************************
 * Project 1 Database 
 * Script Project1(ERS).sql
 * Description: Creates a simple reimbursement database
 * DB Server: Local PostGreSQL
 * Author: Gayathri Addagada 
************************************/

/****************************
	Create Tables 
****************************/
DROP SCHEMA ers CASCADE;

-- Creates the Employee Reimbursement schema if it doesn't exist
CREATE SCHEMA IF NOT EXISTS ERS;
	
-- Creates the ERS Role table
create table ers."Role"(
RoleId serial primary key, 
Role_name varchar(10) not null
);

-- Creates the ERS Employee table
create table ers."Employee"(
EmployeeId serial primary key,
FirstName varchar(40) not null, 
LastName varchar(40) not null,
Title varchar (40) not null,
Username text not null unique,
Password text not null,
Phone varchar(16) unique, 
Email varchar(60) unique, 
Address varchar(70),
City varchar(40), 
State varchar(40), 
Country varchar(40),
PostalCode varchar(10),
role_id int not null
);

-- Creates the ERS Reimbursement table
create table ers."Reimbursement"(
ReimbursementId serial primary key,
Date Timestamp not null, 
Description varchar(100) not null,
Category varchar(40) not null, 
Cost varchar(16) not null,
status varchar(40) not null,
comments text, 
employee_id int not null

);


--CREATE TABLE note(
  --  note_id serial PRIMARY KEY,
    --message varchar(255) NOT NULL,
    --created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
--);


-- Creates the ERS Reimbursement_Status table
--create table ers."Reimbursement_Status"(
--StatusId serial primary key, 
--Status text not null, 
--Comments varchar(256)
--);



--Junction table Employee role and Employee
create table ers."Employee_Role"(
  EmployeeId   int REFERENCES BANK.CUSTOMER (CUSTOMER_ID) ON UPDATE CASCADE ON DELETE cascade,
  RoleId int REFERENCES BANK.ACCOUNT (ACC_ID) ON UPDATE CASCADE ON DELETE cascade,
  CONSTRAINT Customer_Account_pkey PRIMARY KEY (EmployeeId, RoleId)  -- explicit pk
);

/****************************
	Create Foreign Keys
****************************/
/*alter table ers.Employee add constraint FK_EmployeeRoleId 
foreign key Role_Id references "Role".RoleId 
--on delete no action 
--on update no action;
FOREIGN KEY (Role_Id) REFERENCES Role (RoleId)
*/


ALTER TABLE ers."Employee"
ADD CONSTRAINT fk_employee_role FOREIGN KEY (role_id) REFERENCES ers."Role" (RoleId);


ALTER TABLE ers."Reimbursement"
ADD CONSTRAINT fk_employee_reimbursement FOREIGN KEY (employee_id) REFERENCES ers."Employee" (EmployeeId);


--on delete no action 
--on update no action;


--drop table ERS
--drop schema ERS cascade;


/****************************
	Populate Tables
****************************/
-- inserts data into "ers.Employee" table
INSERT INTO ers."Employee" (FirstName, LastName, Title, Username, Password, Phone, Email, Address, City, State, Country, PostalCode, role_id) 
                     Values('Gayathri', 'Addagada', 'Associate', 'gadddagada1', 'abc123', '214-875-9643', 'gaddagada1@yahoo.com', '10307 Sandbar Dr',
                            'Irving', 'TX', 'United States', '75063', 1); 
INSERT INTO ers."Employee" (FirstName, LastName, Title, Username, Password, Phone, Email, Address, City, State, Country, PostalCode,role_id) 
                     Values('Carolyn', 'Rehm', 'Manager', 'cRehm1', 'abc123', '408-518-6497', 'cRehm1@revature.com', '76 Marquette Circle', 'San Jose', 'CA', 'United States', '095150', 2); 
INSERT INTO ers."Employee" (FirstName, LastName, Title, Username, Password, Phone, Email, Address, City, State, Country, PostalCode,role_id) 
                     Values('Christopher', 'Gaugh', 'Manager', 'cGaugh1', 'abc123', '907-842-4590', 'cGaugh1@revature.com', '3 4th Lane', 'Fairbanks', 'AK', 'United States', '99790', 3); 
INSERT INTO ers."Employee" (FirstName, LastName, Title, Username, Password, Phone, Email, Address, City, State, Country, PostalCode,role_id) 
                     Values('Christopher', 'Thompson', 'Director', 'cThompson1', 'abc123', '512-937-3089', 'cThompson1@revature.com', '8 Glendale Trail', 'Austin', 'TX', 'United States', '78759', 4);                    

insert into ers."Employee" (FirstName, LastName,Title, Username, Password, Phone, Email, Address, City, State, Country, PostalCode, Role_Id) 
                            values('Zachary','Pieper','Auditer','zPieper1','abc123','972-260-7636','zPieper@revature.com','7 Sloan Junction','Dallas','TX','United States','75226',1)
                    
                    
-- inserts data into "ers.Reimbursement" table 
insert into ers."Reimbursement" (date, description, category, cost, status, comments, employee_id) 
						values( now(),'relocation expenses', 'travel', 150, 'submitted', 'intial submission of expense', 1 );
insert into ers."Reimbursement" (date, description, category, cost, status, comments, employee_id) 
						values( now(),'relocation expenses', 'travel', 250, 'In Review', 'intial submission of expense', 2 );
insert into ers."Reimbursement" (date, description, category, cost, status, comments, employee_id) 
						values( now(),'office supplies', 'office', 250, 'In Review', 'Ran out of office supplies', 3);
--inserts prepopulated data into Reimbursement_Status  table 
--insert into Reimbursement_Status (Status, Comments) values('Draft','');
--insert into Reimbursement_Status (Status, Comments) values('Submitted','');
--insert into Reimbursement_Status (Status, Comments) values('In Review','');
--insert into Reimbursement_Status (Status, Comments) values('Approved','');
--insert into Reimbursement_Status (Status, Comments) values('Resubmitted','');
--insert into Reimbursement_Status (Status, Comments) values('Denied','');
--insert into Reimbursement_Status (Status, Comments) values('Closed','');

--inserts prepopulated data ers."Role" table 
insert into ers."Role" (Role_Name) values('Employee');
insert into ers."Role" (Role_Name) values('Manager');
insert into ers."Role" (Role_Name) values('Director');
insert into ers."Role" (Role_Name) values('Auditer');

-- Adds the status and comments column to the Reimbursement table in the schema
--alter table ers."Reimbursement" add status text not null;
--alter table ers."Reimbursement" add comments text not null;


/****************************
	Querying Tables
****************************/
select * from ers."Role";
select * from reimbursement_status;
select * from ers."Employee";
select * from ers."Reimbursement";

insert into ers."Reimbursement" (date, description, category, cost, status, comments, employee_id, reimbursement_id) values (?,?,?,?,?,?,?,?);


--delete from ers."Role" ;

delete from ers."Reimbursement"; 

-- deletes employee where employee id = ?
--delete from ers."Employee" where employeeid=?;

-- Update Employee information 
update ers."Employee" 
set username = 'cRehm1'
where employeeid=2;

update ers.employee set FirstName = ?, LastName=?, Title=?, Username=?, 
Password=?, Phone=?, Email=?, Address=?, City=?, State=?, Country=?, 
PostalCode=?, Role_Id=?  where username = ?


update ers."Reimbursement" set Date=now(), Description ='office supplies', Category ='office' Cost=250, status='Denied', comments='Ran out off office supplies', employee_id =3 where reimbursementid =3

-- Update Reimbursement information 
update ers."Reimbursement" 
set status = 'Approved'
where reimbursementid = 3;

update ers."Reimbursement" set Date=?, Description =?, 
Category =? Cost=?, status=?, comments=?, employee_id =? where status =? 

commit;

	SELECT username
	FROM ers."Employee" 
	WHERE 

select * ers."Employee" where employeeid=1;


SELECT *
FROM ers."Reimbursement" 
WHERE reimbursementid = 1

commit;

-- This is what my query ws getting in prepared statement in eclipse 
update ers."Reimbursement" set Date='2020-03-03 -06', Description ='office supplies', 
Category='office', Cost='250', status='Denied', comments='Ran out of office supplies', employee_id =3 where reimbursementid=null

SELECT * FROM ers."Employee" WHERE  username ='gaddagada1';
