package com.revature;

import java.util.List;

import com.revature.services.Employee;
import com.revature.services.EmployeeService;
import com.revature.services.EmployeeServiceImplService;

public class Driver {
	public static void main(String[] args) {
		final EmployeeServiceImplService employeeServiceImpl = new EmployeeServiceImplService();

		final EmployeeService employeeService = employeeServiceImpl.getEmployeeServiceImplPort();

		final List<Employee> employees = employeeService.getAllEmployees();

		for (final Employee e : employees)
			System.out.println(e);
	}
}
