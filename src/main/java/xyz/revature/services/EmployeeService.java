package xyz.revature.services;

import java.util.List;

import xyz.revature.daos.EmployeeDao;
import xyz.revature.daos.EmployeeDaoImpl;
import xyz.revature.models.Employee;

public class EmployeeService {
	private EmployeeDao empDao = new EmployeeDaoImpl();
	
	public List<Employee> getAllEmployees(){
		return empDao.getAllEmployees();
	}
	public Employee getEmployee(int id) {
		return empDao.getEmployee(id);
	}
}
