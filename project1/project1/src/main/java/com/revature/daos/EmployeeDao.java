package com.revature.daos;

import java.util.List;

import com.revature.models.Employee;

public interface EmployeeDao {

	public List<Employee> getAllEmployees();
	public int createEmployee(String login, String pw, String title, String firstName, String lastName, String email);
	public Employee getEmployeeById(int eid);
	public Employee getEmployeeByLogin(String login, String password);
	public int updateEmployee(Employee e);
	public int deleteEmployee(Employee e);
}
