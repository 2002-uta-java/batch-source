package com.revature.service;

import java.util.List;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImplementation;
import com.revature.model.Employee;

public class EmployeeService {

	private EmployeeDao employeeDao = new EmployeeDaoImplementation();
	
	public Employee getUserByUsername(String username) {
		return employeeDao.getEmployeeByUsername(username);
	}

	public Employee validateUser(String username, String password) {
		Employee employee = employeeDao.getEmployeeByUsername(username);
		if(employee != null && !(password.equals("")||password.equals(" "))) {
			if(password.equals(employee.getPwd()))
				return employee;
			else {
				System.out.println("\nInvalid username or password.\n");
				return null;
			}
		}
		System.out.println("Username not found\n");
		return null;
	}
	
	
	public boolean createUser(Employee e) {
		if(e == null)
			return false;
		List<Employee> employees = employeeDao.getEmployees();
		for(Employee employee: employees) {
			if(e.getUsername().equals(employee.getUsername()) && e.getPwd().equals(employee.getPwd())) {
				System.out.println("User already exists, please sign in...");
				return false;
			}	
		}
		int userCreated = employeeDao.createEmployee(e);
		if(userCreated != 0) {
			return true;
		}
		return false;
	}
	
	public boolean updateUser(Employee e) {
		int userUpdated = employeeDao.updateEmployee(e);
		if(userUpdated != 0) {
			return true;
		}
		return false;
	}

	public boolean deleteUser(Employee e) {
		int deleteCreated = employeeDao.deleteEmployee(e);
		if(deleteCreated != 0) {
			return true;
		}
		return false;
	}
}
