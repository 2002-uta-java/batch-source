package com.revature;

import java.util.List;

import com.revature.daos.EmployeeDaoImpl;
import com.revature.model.Employee;
import com.revature.services.EmployeeService;

public class EmployeeDriver {
	public static void main(String[] args) {
		final EmployeeService eService = new EmployeeService(new EmployeeDaoImpl());

		final List<Employee> employees = eService.getEmployees();

		for (final Employee employee : employees)
			System.out.println(employee);
	}
}
