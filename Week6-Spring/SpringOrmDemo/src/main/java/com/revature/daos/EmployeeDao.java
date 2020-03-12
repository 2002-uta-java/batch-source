package com.revature.daos;

import java.util.List;

import com.revature.models.Employee;

public interface EmployeeDao {
	
	public List<Employee> getAll();
	public Employee getById(int id);
	// other crud methods

}
