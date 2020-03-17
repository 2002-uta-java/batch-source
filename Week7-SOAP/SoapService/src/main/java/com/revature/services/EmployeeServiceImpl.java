package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Employee;

public class EmployeeServiceImpl implements EmployeeService {
	// Service implementing bean
	
	private List<Employee> employees = new ArrayList<>();
	
	public EmployeeServiceImpl() {
		super();
		employees.add(new Employee(1, "Sally Jenkins", "HR Rep"));
		employees.add(new Employee(2, "Joe Smith", "Sales Rep"));
	}

	@Override
	public List<Employee> getAllEmployees() {
		return new ArrayList<>(employees);
	}

	@Override
	public String addEmployee(Employee e) {
		employees.add(e);
		return "Added: "+e.getName();
	}

}
