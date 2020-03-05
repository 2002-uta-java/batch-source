package com.revature.services;

import java.util.List;

import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeeDaoImpl;
import com.revature.models.Employee;

public class EmployeeService {

	EmployeeDao ed = new EmployeeDaoImpl();
	
	public List<Employee> getAllEmployees() {
		return ed.getAllEmployees();
	}
	
	public Employee login(String login, String password) {
		return ed.getEmployeeByLogin(login, password);
	}
	
	public Employee myInfo(int eid) {
		return ed.getEmployeeById(eid);
	}
	
	public int updateInfo(int eid, String login, String password, String firstName, String lastName, String email) {
		Employee e = new Employee(eid, login, password, firstName, lastName, email);
		return ed.updateEmployee(e);
	}
	
}
