package com.revature.services;

import java.util.List;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.models.Employee;

public class EmployeeService {
	
	private EmployeeDao eo = new EmployeeDaoImpl();
	
	// call dao to create employee in database. Returns Employee object
	public Employee createEmployee(Employee e){
		
		return eo.createEmployee(e);
		}
	
	// call dao to find an employee by id. Returns Employee object
	public Employee getEmployee(Employee e) {
		return eo.getEmployee(e);
	}
	
	// call dao to update employee information. Returns int to check success
	public int updateEmployee(Employee e) {
		return eo.updateEmployee(e);
	}
	
	// call dao to retrieve a list of all employees
	public List<Employee> getAllEmployees() {
		return eo.getAllEmployees();
	}

}
