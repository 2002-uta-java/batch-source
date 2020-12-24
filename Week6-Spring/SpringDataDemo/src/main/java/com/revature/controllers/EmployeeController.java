package com.revature.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping
	public List<Employee> getAll(@RequestParam(value="position", required=false)String position){
		if(position == null) {
			return employeeService.getAllEmployees();
		} else {
			return employeeService.findEmployeeByPosition(position);
		}
	}
	
	@GetMapping("/{id}")
	public Employee getById(@PathVariable("id")int id) {
		return employeeService.getEmployeeById(id);
	}
	
	/* - this leads to an IllegalStateException as it is an ambigious handler method
	@GetMapping("/{var}")
	public Employee getById(@PathVariable("var")String str) {
		System.out.println("accessed string param method with: "+ str);
		return null;
	}
	*/

	@PostMapping
	public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee){
		System.out.println(employee);
		return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id")int id, @RequestBody Employee employee){
		employee.setId(id);
		return new ResponseEntity<>(employeeService.updateEmployee(employee), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") int id){
		return new ResponseEntity<>(employeeService.deleteEmployee(new Employee(id)), HttpStatus.OK);
	}
	
}
