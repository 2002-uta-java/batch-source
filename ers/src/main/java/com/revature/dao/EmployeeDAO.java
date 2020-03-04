package com.revature.dao;

import java.util.List;

import com.revature.model.Employee;

public interface EmployeeDAO {
	List<Employee> getAllEmployees();	
	Employee getEmployee(int id);
	Employee getEmployeeByUsername(String uName);
	void addEmployee(Employee employee) throws Exception;
	void deleteEmployee(int id) throws Exception;
	void updateEmployee(Employee employee) throws Exception;
}
