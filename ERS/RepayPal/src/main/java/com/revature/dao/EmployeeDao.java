package com.revature.dao;

import java.util.List;

import com.revature.model.Employee;

public interface EmployeeDao {

	public List<Employee> getEmployees();
	public Employee getEmployeeByUsername(String username);
	public int createEmployee(Employee e);
	public int updateEmployee(Employee e);
	public int deleteEmployee(Employee e);
}
