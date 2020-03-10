package com.revature.daos;

import java.util.List;

import com.revature.models.Employee;

public interface EmployeeDao {
	public boolean createEmployee(Employee newEmployee);

	public Employee viewEmployeeDetails(String userName);
	public Employee getEmployeeDetailsById(int emplID);
	public boolean updateEmployee(Employee updateEmployee);

	public String deleteEmployee(int employeeId);

	public Employee getEmployeeByUsernameAndPassword(String username, String password);

	public List<Employee> getAllEmployees();

	
}
