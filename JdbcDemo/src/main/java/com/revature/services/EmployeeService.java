package com.revature.services;

import java.util.List;

import com.revature.daos.EmployeeDao;
import com.revature.model.Employee;

public class EmployeeService {
	private EmployeeDao eDao = null;

//	public EmployeeService() {
//		super();
//	}

	public EmployeeService(final EmployeeDao eDao) {
		setDao(eDao);
	}

	public void setDao(EmployeeDao eDao) {
		this.eDao = eDao;
	}

	public Employee getEmployeeById(final int id) {
		return eDao.getEmployeeById(id);
	}

	public List<Employee> getEmployees() {
		return eDao.getEmployees();
	}

	public boolean createEmployee(final Employee e) {
		final int empCreated = eDao.createEmployee(e);
		if (empCreated != 0)
			return true;
		// else
		return false;
	}
}
