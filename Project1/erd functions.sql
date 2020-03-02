-- create new client
create or replace function new_client (id employee.empl_id%type, logon employee.empl_email%type, credential client.client_password%type, auth client.client_permission%type)
returns setof client 
language plpgsql
as $$
begin 
	insert into client (client_id, client_email, client_password, client_permission) values (id, logon, credential, auth);
	return query select * from client where client_id = (select empl_id from employee);
end
$$

-- update client
create or replace function update_client (id client.client_id%type, credential client.client_password%type)
returns setof client 
language plpgsql
as $$
begin 
update client 
set client_password = credential
where client_id = id;
return query select * from client where client_id = id;
end
$$
-- create employee
create or replace function employee_entry (fname employee.empl_firstname%type, lname employee.empl_lastname%type, email employee.empl_email%type, title employee.empl_title%type, manager employee.manager_id%type, reports employee.reports_to%type)
returns setof employee
language plpgsql
as $$
declare 
	new_id integer;
begin 
	insert into employee (empl_firstname, empl_lastname, empl_email, empl_title, manager_id, reports_to) values (fname, lname, email, title, manager, reports);
	select last_value into new_id
	from employee_empl_id_seq;
	return query select * from employee where empl_id = new_id;
end
$$

-- update employee manager
create or replace function update_employee_manager (id employee.empl_id%type, reports employee.reports_to%type)
returns setof employee
language plpgsql
as $$
begin
update employee 
set reports_to = reports
where empl_id = id;
return query select * from employee where empl_id = id; 
end
$$

-- create a reimbursment
create or replace function r_entry (rtype reimbursement.rem_type%type, ramount reimbursement.rem_requested_amount%type, rdate reimbursement.rem_date_requested%type, reciept reimbursement.rem_reciept_url%type, notes reimbursement.rem_notes%type,
id reimbursement.empl_id%type, mid reimbursement.assigned_manager%type, adate reimbursement.rem_date_approved%type, amount reimbursement.rem_amount_approved%type, mcomment reimbursement.rem_comment%type, status reimbursement.rem_status%type)
returns setof reimbursement 
language plpgsql
as $$
declare 
	new_id integer;
begin 
	insert into reimbursement (rem_type, rem_requested_amount, rem_date_requested, rem_reciept_url, rem_notes, empl_id, assigned_manager, rem_date_approved, rem_amount_approved, rem_comment, rem_status) 
values (rtype, ramount, rdate, reciept, notes, id, mid, adate, amount, mcomment, status);
	select last_value into new_id
	from reimbursement_rem_id_seq;
	return query select * from reimbursement where rem_id = new_id;
end
$$

-- update reimbursment manager 
create or replace function update_r (id reimbursement.rem_id%type, adate reimbursement.rem_date_approved%type, amount reimbursement.rem_amount_approved%type, rcomment reimbursement.rem_comment%type, status reimbursement.rem_status%type)
returns setof reimbursement 
language plpgsql
as $$
begin
update reimbursement 
set rem_date_approved = adate,
 rem_amount_approved = amount,
 rem_comment = rcomment,
 rem_status = status
where rem_id = id;
return query select * from reimbursement where rem_id = id;
end
$$
-- delete reimbursment request
create or replace function delete_r (id reimbursement.rem_id%type)
returns setof reimbursement 
language plpgsql
as $$
begin
delete from reimbursement 
where rem_id = id;
return query select * from reimbursement where rem_id = id;
end
$$

-- generate report by Employee
create or replace function employee_report (id employee.empl_id%type)
returns setof reimbursement
language plpgsql
as $$
begin 
	select *
	from reimbursement 
	where empl_id = id;
return a query select * from reimbursement where empl_id = id;
end 
$$
end

-- generate report by Reiembursment
create or replace function employee_report (rtype reimbursement.rem_type%type)
returns setof reimbursement
language plpgsql
as $$
begin 
	select *
	from reimbursement 
	where rem_type = rtype;
return a query select * from reimbursement where rem_type = rtype; 
end 
$$
end
