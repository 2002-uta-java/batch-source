package com.project1.services;

import java.util.List;

import com.project1.daos.EmployeeDao;
import com.project1.daos.EmployeeDaoImpl;
import com.project1.models.Employee;

public class EmployeeService {
	private EmployeeDao empDao = new EmployeeDaoImpl();
	
	// all employee check through credential
	public Employee credential(String identifier, String password) {
		return empDao.credential(identifier, password);
	}
	
	// get employee by id
	public Employee getEmployeeById(int id) {
		return empDao.getEmployeeById(id);
	}
	
	// update employee
	public boolean updateEmployee(Employee e) {
		int empUpdated = empDao.updateEmployee(e);
		
		if(empUpdated != 0) {
			return true;
		}
		
		return false;
	}
	
	// Manager view all employee beside manager
	public List<Employee> getAllEmployee(String position){
		return empDao.getAllEmployee(position);
	}
}
