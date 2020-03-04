package com.revature.dao;

import java.util.List;

import com.revature.model.Employee;

public interface EmployeeDao {

	public List<Employee> getEmployee();
	public int getEmployeeId(String email);
	public String getFirstName(int id);
	public String getLastname(int id);
	public String getEmail(String email);
	public int getManagerId(int id);
	public String getTitle(int id);
	public int getReportsTo(int id);
	public Employee createEmployeeByFunction(Employee e1);
	public Employee updateEmployeeManagerByFunction(Employee e1);
    public int deleteEmployee(int id);
    
	
}
