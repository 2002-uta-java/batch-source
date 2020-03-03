package com.hylicmerit.service;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.hylicmerit.dao.EmployeeDaoImpl;
import com.hylicmerit.dao.EmployeeDao;
import com.hylicmerit.models.Employee;

public class EmployeeService {

	private EmployeeDao ed = new EmployeeDaoImpl();

	//returns all of the employees in the database
	public List<Employee> getAllEmployees() {
		return (List<Employee>) ed.getAll();
	}
	
	public Employee getEmployeeById(String email) {
		return ed.getById(email);
	}
	
	public boolean updateEmployee(Employee e) throws JsonParseException, JsonMappingException, IOException {
		if(ed.update(e) != 0) {
			return true;
		} else {
			return false;
		}
	}

}
