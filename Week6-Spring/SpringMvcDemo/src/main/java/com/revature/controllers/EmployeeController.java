package com.revature.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.revature.models.Employee;
import com.revature.services.EmployeeService;

@Controller //@RestController //@CrossOrigin
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;

	@GetMapping
	@ResponseBody
	public List<Employee> getAllEmployees(@RequestParam(name="position", required=false)String position){
		if(position!=null) {
			return employeeService.getByPosition(position);
		}
		return employeeService.getAll();
	}
	
	// GET /employees/id
	@GetMapping("/{id}")
	@ResponseBody
	public Employee getEmployeeById(@PathVariable("id")int id) {
		return employeeService.getById(id);
	}

	/*
	@PostMapping("/employees")
	@ResponseBody
	@ResponseStatus(value=HttpStatus.CREATED)
	public String addEmployee(@RequestParam("id")int id, @RequestParam("name")String name, @RequestParam("position")String position) {
		Employee e = new Employee(id, name, position);
		employeeService.create(e);
		return "added employee "+e.getName();
	}
	
	
	@PostMapping("/employees")
	@ResponseBody
	public String addEmployee(HttpServletRequest request, HttpServletResponse response) {
		// access params in request and send status code with response
		return "added employee";
	}
	*/
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<String> addEmployee(@RequestBody Employee e) {
		employeeService.create(e);
		return new ResponseEntity<>("added employee "+e.getName(),HttpStatus.CREATED);
	}
	
	
}
