package com.revature.daos;

import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Reimbursement;

public interface EmployeeDao {

	public List<Employee> getEmployees();
	public Employee getEmployeeByEmail(String email);
	public List<Reimbursement> getReimbursementsAll();
	public List<Reimbursement> getReimbursementsByEmployee(Employee e);
	
	public void createEmployee(Employee e);
	public void createReimbursement(Reimbursement r);
	
	public void updateEmployee(Employee e);
	public void updateReimbursement(Reimbursement r);
}
