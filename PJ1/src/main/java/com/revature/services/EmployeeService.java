package com.revature.services;

import java.util.List;
import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeeDaoImpl;
import com.revature.models.Employee;

/**
 * the service layer for our employees
 * @author Eric Pacheco
 *
 */
public class EmployeeService {
	EmployeeDao employeeDao = new EmployeeDaoImpl();
	
	public Employee getEmpByName(String name) {
		return employeeDao.getEmployeeByName(name);
	}
	public List<Employee> getEmployees() {
		return employeeDao.getAllEmployees();
	}
	
	public Employee getEmpByRequest(String requestId) {
		return employeeDao.getEmployeeByRequest(requestId);
	}
	public Employee getEmpLogin(String name, String passWord) {
		return employeeDao.getEmployeeNameAndPassword(name, passWord);
	}
public int updateEmployee(String name, int id) {
		
		return employeeDao.updateEmployee(name, id);
	}
	
}
