package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Employee;
import com.revature.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(int id) {
		return employeeRepository.getOne(id);
	}

	@Override
	public Employee addEmployee(Employee e) {
		return employeeRepository.save(e);
	}

	@Override
	public Employee updateEmployee(Employee e) {
		return employeeRepository.save(e);
	}

	@Override
	public Employee deleteEmployee(Employee e) {
		employeeRepository.delete(e);
		return e;
	}

	@Override
	public List<Employee> findEmployeeByPosition(String position) {
		return employeeRepository.findEmployeeByPosition(position);
	}

}
