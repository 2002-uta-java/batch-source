package com.revature.services;

import java.util.List;

import com.revature.models.Employee;

public interface EmployeeService {
	
	public List<Employee> getAllEmployees();
	public Employee getEmployeeById(int id);
	public Employee addEmployee(Employee e);
	public Employee updateEmployee(Employee e);
	public Employee deleteEmployee(Employee e);
	public List<Employee> findEmployeeByPosition(String position);
	
}
