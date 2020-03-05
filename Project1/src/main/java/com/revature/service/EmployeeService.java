package com.revature.service;

import java.util.List;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.model.Employee;


public class EmployeeService {
	
	private EmployeeDao employeeDao = new EmployeeDaoImpl();

	public EmployeeService() {
		super();
	}
     
	public List<Employee> getEmployee() {
		return employeeDao.getEmployee();
		} 
	
	public int getEmployeeId(String email) {
		return employeeDao.getEmployeeId(email);
	}
	
	public String getFirstName(int id) {
		return employeeDao.getFirstName(id);
	}
	
	public String getLastname(int id) {
		return employeeDao.getLastname(id);
	}
	
	public String getEmail(String email) {
		return employeeDao.getEmail(email);
	}
	
	public int getManagerId(int id) {
		return employeeDao.getManagerId(id);
	}
	
	public String getTitle(int id) {
		return employeeDao.getTitle(id);
	}
	
	public int getReportsTo(int id) {
		return employeeDao.getReportsTo(id);
	}
	
	public Employee createEmployeeByFunction(Employee e1) {
		return employeeDao.createEmployeeByFunction(e1);
	}
	
	public Employee updateEmployeeManagerByFunction(Employee e1) {
		return employeeDao.updateEmployeeManagerByFunction(e1);
	}
	
    public int deleteEmployee(int id) {
    	return employeeDao.deleteEmployee(id);
    }
    
    public List<Employee> employeeById(int id) {
    	return employeeDao.employeeById(id);
    }
    
	public Employee information(int id) {
		List<Employee> newList = employeeDao.getEmployee();
		for(Employee e : newList) {
			if(e.getEmployeeId() !=0 && e.getEmployeeId() == (id)) {
				return e;
				} 
			} return null;
	}
	public String updateEfirstName(String a, int id) {
		return employeeDao.updateFirstName(a,id);
	}
		
	public String updateElastName(String a, int id) {
		return employeeDao.updateLastName(a, id);
	}
		
	public String updateTitle(String a, int id) {
		return employeeDao.updateTitle(a, id);
	}
}
