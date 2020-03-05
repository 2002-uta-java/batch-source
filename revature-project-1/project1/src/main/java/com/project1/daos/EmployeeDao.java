package com.project1.daos;

import java.util.List;

import com.project1.models.Employee;

public interface EmployeeDao {
	// all employee
	public Employee credential(String identifier, String password);
	
	// get employee by id
	public Employee getEmployeeById(int id);
	
	// update employee
	public int updateEmployee(Employee e);
	
	// Manager view all employee beside manager
	public List<Employee> getAllEmployee(String position);
	
	
}
