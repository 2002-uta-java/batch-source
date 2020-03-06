package com.revature.services;

import java.util.List;

import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeeDaoImpl;
import com.revature.models.Employee;

public class EmployeeService {
	
private EmployeeDao ed = new EmployeeDaoImpl();
	
	public List<Employee> getAllEmployee(){
		return ed.getAllEmployee();
	}
	
	public Employee getEmployeeByUsername(String username, String passwrd) {
		return ed.getEmployeeByUsername(username, passwrd);
	}
	
		public boolean updateEmployee(Employee e) {
			if(ed.updateEmployee(e) != 0) {
				return true;
			} else {
				return false;
		}
	}

	public Employee getEmployeeById(int id) {
		return ed.getEmployeeById(id);
	}

}