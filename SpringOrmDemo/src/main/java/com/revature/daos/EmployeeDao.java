package com.revature.daos;

import java.io.Serializable;
import java.util.List;

import com.revature.models.Employee;

public interface EmployeeDao extends Serializable {
	public List<Employee> getAll();

	public Employee getById(int id);
}
