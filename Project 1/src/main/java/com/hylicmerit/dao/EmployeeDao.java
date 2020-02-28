package com.hylicmerit.dao;

import java.util.List;

import com.hylicmerit.models.Employee;

public interface EmployeeDao {

	List<Employee> getAll();
	
	Employee getById(String email);
	
	int create(Employee e);
	
	int update(Employee e);
	
	int delete(Employee e);
}
