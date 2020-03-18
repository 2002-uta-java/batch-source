package com.revature.services;

import java.io.Serializable;
import java.util.List;

import javax.jws.WebService;

import com.revature.models.Employee;

@WebService
public interface EmployeeService extends Serializable {
	// represents our service endpoint interface

	public List<Employee> getAllEmployees();

	public String addEmployee(Employee employee);
}
