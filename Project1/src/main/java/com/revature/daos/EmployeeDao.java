package com.revature.daos;

import java.util.List;

import com.revature.models.Employee;

public interface EmployeeDao {

	public List<Employee> getEmployees();
	public Employee getEmployeeById(int id);
	public Employee getEmployeeByEmailAndPassword(String email, String password);
	public void createEmployee(Employee e);
	public void updateEmployee(Employee e);
	
}
