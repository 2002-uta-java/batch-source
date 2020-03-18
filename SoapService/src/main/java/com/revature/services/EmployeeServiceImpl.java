package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Employee;

public class EmployeeServiceImpl implements EmployeeService {

	// service implementing bean

	private static final long serialVersionUID = 1L;

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
	public String addEmployee(Employee employee) {
		employees.add(employee);

		return "Added: " + employee.getName();
	}

}
