package com.revature.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.models.Employee;

@Service
public interface EmployeeService extends Serializable {
	public List<Employee> getAllEmployees();

	public Employee getEmployeeById(int id);

	public Employee addEmployee(Employee employee);

	public Employee updateEmployee(Employee employee);

	public Employee deleteEmployee(Employee employee);

	public List<Employee> findEmployeesByPosition(String position);
}
