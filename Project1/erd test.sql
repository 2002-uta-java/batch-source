select employee_entry ('jimmy', 'bob', 'jimmybob23@gmail.com', 'tech manager', 1, 6);
select new_client (2,'jimmybob23@gmail.com', 'dovekiss', 1);
select update_client (2, 'password');
select update_employee_manager (2, 5);
select r_entry ('travel', 200.00,'2020-02-14','none','none',2,5,'2020-02-14',200.00,'none','pending');
select update_r (2, '2020-02-15',250.00, 'approved');
select delete_r(2);