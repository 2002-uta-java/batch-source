package com.revature.daos;

import java.util.List;

import com.revature.model.Employee;

public interface EmployeeDao {
	public List<Employee> getEmployees();

	public Employee getEmployeeById(final int id);

	public void setDepartmentDao(final DepartmentDao dd);

	public int createEmployee(final Employee e);
}
