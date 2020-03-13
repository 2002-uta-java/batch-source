package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Employee;
import com.revature.services.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService empService;

	@GetMapping
	public List<Employee> getAll(@RequestParam(value = "position)", required = false) final String position) {
		if (position == null) {
			return empService.getAllEmployees();
		} else {
			return empService.findEmployeesByPosition(position);
		}
	}

	@GetMapping("/{id}")
	public Employee getById(@PathVariable("id") final int id) {
		return empService.getEmployeeById(id);
	}

	@PostMapping
	public ResponseEntity<Employee> addEmployee(@RequestBody final Employee employee) {
		return new ResponseEntity<>(empService.addEmployee(employee), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") final int id,
			@RequestBody final Employee employee) {
		employee.setId(id);
		return new ResponseEntity<>(empService.updateEmployee(employee), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable final int id) {
		final Employee employee = new Employee(id);
		return new ResponseEntity<>(empService.deleteEmployee(employee), HttpStatus.OK);
	}

	@GetMapping("/{position}")
	public List<Employee> getEmployeesByPosition(@PathVariable final String position) {
		System.out.println("accessed string param method with: " + position);
		return empService.findEmployeesByPosition(position);
	}
}
