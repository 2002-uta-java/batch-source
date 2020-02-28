package com.revature.daos;

import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Reimbursement;

public interface EmployeeDao {

	public List<Employee> getEmployees();
	public Employee getEmployeeById(int id);
	
	public void createEmployee(Employee e);
	
	public void updateEmployee(Employee e);
	
}
