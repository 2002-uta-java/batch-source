-- user table for log-in verfication and storage
create table client(
	client_id integer references employee(empl_id),
    client_email varchar(50) constraint pk_client_email primary key,
    client_password varchar(50),
    client_permission int
);
-- employee table with employee information // builds internal relationship of data usage
-- sonar cloud
-- sonar cube
-- soner lent
create table employee (
	empl_id serial constraint pk_empl_id primary key,
	empl_firstname varchar(50),
	empl_lastname varchar(50),
	empl_email varchar(50),
	empl_title varchar(50),
	manager_id int,
    reports_to int
);

alter table employee 
add constraint reports_to references employee(manager_id);

-- reimbursement table tracks types, dates, status, source, assignment // builds the statement and report
create table reimbursement (
	rem_id serial constraint pk_rem_id primary key,
	rem_type varchar(50),
	rem_requested_amount double precision,
	rem_date_requested date,
	rem_reciept_url varchar(200),
	rem_notes varchar(250),
	empl_id int references employee(empl_id),
	assigned_manager int,
	rem_date_approved date,
	rem_amount_approved double precision,
	rem_comment varchar(250),
	rem_status varchar(50)
); 