package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.revature.models.Employee;

@Component
public class EmployeeService {

	private final List<Employee> employees = new ArrayList<Employee>();

	public EmployeeService() {
		employees.add(new Employee(1, "Gloria"))
	}

	public List<Employee> getAll() {
		return new ArrayList<Employee>();
	}

	public Employee getById(int id) {
		for (final Employee employee : employees) {

		}
	}

}
