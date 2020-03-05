package com.revature.daos;

import java.util.List;

import com.revature.models.Employee;

public interface EmployeeDao {
	public Employee getEmployeeByName(String name);
	public Employee getEmployeeByRequest(String reqeustId);
	public Employee getEmployeeNameAndPassword(String name, String password);
	public List<Employee> getAllEmployees();
	public int updateEmployee(String name, int id);
}
