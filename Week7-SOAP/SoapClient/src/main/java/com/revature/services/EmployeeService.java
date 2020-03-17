package com.revature.services;

import java.util.List;

import javax.jws.WebService;

import com.revature.models.Employee;

@WebService
public interface EmployeeService {
	// Service Endpoint Interface
	
	public List<Employee> getAllEmployees();
	public String addEmployee(Employee e);
	
	
}
