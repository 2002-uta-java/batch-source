package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Employee;
import com.revature.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private EmployeeRepository empRepo;

	@Override
	public List<Employee> getAllEmployees() {
		return empRepo.findAll();
	}

	@Override
	public Employee getEmployeeById(int id) {
		return empRepo.getOne(id);
	}

	@Override
	public Employee addEmployee(Employee employee) {
		return empRepo.save(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return empRepo.save(employee);
	}

	@Override
	public Employee deleteEmployee(Employee employee) {
		empRepo.delete(employee);
		return employee;
	}

	@Override
	public List<Employee> findEmployeesByPosition(String position) {
		return empRepo.findEmployeeByPosition(position);
	}

}
