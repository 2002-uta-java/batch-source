package com.revature.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeeDaoImpl;
import com.revature.models.Employee;

public class EmployeeService {
	private EmployeeDao ed = new EmployeeDaoImpl();

	private List<Employee> employees = new ArrayList<>();

	// returns all of the employees in the database
	public List<Employee> getAllEmployees() { 
		return (List<Employee>) ed.getAllEmployees();
	}

	// returns specified employee requested by the id
	public Employee viewEmployeeDetails(String employeeUserName) {
		return ed.viewEmployeeDetails(employeeUserName);
	}
	
	// returns specified employee requested by the id
		public Employee getEmployeeDetailsById(int emplid) {
			return ed.getEmployeeDetailsById(emplid);
		}
	
	//returns an object with updated employee information 
	public boolean updateEmployee(Employee updateEmployee) {
		return ed.updateEmployee(updateEmployee);
	}

	public boolean addEmployee(Employee e) {
		return ed.createEmployee(e);
	}

}
