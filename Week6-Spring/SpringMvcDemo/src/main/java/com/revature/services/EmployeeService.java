package com.revature.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.revature.exceptions.EmployeeNotFoundException;
import com.revature.models.Employee;

@Service
public class EmployeeService {
	
	
	private List<Employee> employees = new ArrayList<>();
	
	public EmployeeService() {
		super();
		employees.add(new Employee(1,"Lola Jenkins", "HR Director"));
		employees.add(new Employee(2,"Paul Larkin", "IT Rep"));
		employees.add(new Employee(3,"Gloria Smith", "IT Rep"));
	}
	
	public List<Employee> getAll(){
		return new ArrayList<Employee>(employees);
	}
	
	public Employee getById(int id) {
		for(Employee e: employees) {
			if(e.getId()==id) {
				return e;
			}
		}
		throw new EmployeeNotFoundException();
	}
	
	public List<Employee> getByPosition(String position){
		List<Employee> currentEmployees = new ArrayList<>();
		for(Employee e : employees) {
			if(position.equals(e.getPosition())) {
				currentEmployees.add(e);
			}
		}
		return currentEmployees;
		
	}
	
	public void create(Employee e) {
		employees.add(e);
	}
	

}
