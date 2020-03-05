package com.dean.daos;

import java.util.List;

import com.dean.models.Employee;

public interface EmployeeDao {
	
	int createEmployee(Employee employee);
	Employee getEmployeeByUsername(String username); 
	Employee getEmployeeById(int id);
	Employee getEmployeeByName(String name);
	List<Employee> getAllEmployees();
	int updateEmployeeById(int id, String name, String username, String position);
	int updateEmployeeByName(String name);
	
}
