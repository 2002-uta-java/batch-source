package com.revature.daos;

import java.sql.Connection;
import java.util.List;

import com.revature.models.Employee;

public interface EmployeeDao {
	public List<Employee> getAllEmployee();
	public Employee getEmployeeByUsername(String username, String passwrd);
	public int updateEmployee (Employee e);
	public  int save(Employee e);
	public Employee getEmployeeById(int id);
}
