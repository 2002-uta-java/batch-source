package com.revature;

import java.util.List;

import com.revature.services.Employee;
import com.revature.services.EmployeeService;
import com.revature.services.EmployeeServiceImplService;

public class Driver {

	public static void main(String[] args) {
		EmployeeServiceImplService employeeServiceImplService = new EmployeeServiceImplService();
		EmployeeService employeeService = employeeServiceImplService.getEmployeeServiceImplPort();
		
		List<Employee> employees = employeeService.getAllEmployees();
		System.out.println(employees);
	}

}
