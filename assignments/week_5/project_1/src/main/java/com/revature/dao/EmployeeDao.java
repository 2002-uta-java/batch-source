package com.revature.dao;

import java.util.List;

import com.revature.models.Employee;

public interface EmployeeDao {
	
	// create a new employee in the database. Returns Employee object
	public Employee createEmployee(Employee e);
	
	// find an employee in the database and return its info. Returns Employee object
	public Employee getEmployee(Employee e);
	
	// update employee info in the database and return a success int
	public int updateEmployee(Employee e);
	
	// get a list off all the employees
	public List<Employee> getAllEmployees();

}