package com.hylicmerit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.hylicmerit.models.Employee;
import com.hylicmerit.service.EmployeeService;

public class EmployeeServiceTest {
	EmployeeService es = new EmployeeService();
	@Test
	public void getEmployeeByIdBlankEmail() {
		Employee expected = null;
		String email = "";
		assertEquals(expected, es.getEmployeeById(email));
	}
	
	@Test
	public void getEmployeeByIdInvalidEmail() {
		Employee expected = null;
		String email = "none@gmail.com";
		assertEquals(expected, es.getEmployeeById(email));
	}
	
	@Test
	public void getEmployeeByIdValid() {
		Employee expected = new Employee(
				"saul@gmail.com", "1234", "Saul Quintero", 
				null, "11/05/1998", "employee", "mila@gmail.com");
		String email = "saul@gmail.com";
		assertEquals(expected, es.getEmployeeById(email));
	}
}
