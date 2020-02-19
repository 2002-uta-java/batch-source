CREATE OR REPLACE procedure add_holder (username varchar, pass varchar)
language plpgsql
as $$
begin 
	insert into holder values(username, pass);
end;
$$