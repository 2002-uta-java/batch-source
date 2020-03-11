package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.models.Employee;
import com.revature.services.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employees")
	@ResponseBody
	public List<Employee> getAllEmployees(){
		return employeeService.getAll();
	}
	
	// GET /employees/id
	@GetMapping("/employees/{id}")
	@ResponseBody
	public Employee getEmployeeById(@PathVariable("id")int id) {
		return employeeService.getById(id);
	}
}
