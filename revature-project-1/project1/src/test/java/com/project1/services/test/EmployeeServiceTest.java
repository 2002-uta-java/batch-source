package com.project1.services.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.project1.models.Employee;
import com.project1.models.Reimbursement;
import com.project1.services.EmployeeService;

public class EmployeeServiceTest {
	private static final EmployeeService eService = new EmployeeService();
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void testCorrectCredentialValidation() {
		Employee expected = new Employee();
		expected = eService.getEmployeeById(2);
		assertEquals(expected, eService.credential("hoang1", "hoang1pass"));
	}
	
	@Test
	public void testGetEmployeeById() {
		Employee expected = new Employee();
		expected = eService.credential("hoang1", "hoang1pass");
		assertEquals(expected, eService.getEmployeeById(2));
	}
	
	@Test
	public void testupdateReimbursement() {
		Employee expected = eService.getEmployeeById(1);
		Employee r = eService.getEmployeeById(1);
		eService.updateEmployee(r);
		assertEquals(expected, r);
	}
	
	@Test
	public void testGetAllEmployees() {
		Employee emp = eService.getEmployeeById(5);
		List<Employee> expected = eService.getAllEmployee("Manager");
		List<Employee> eList = eService.getAllEmployee("Manager");
		assertEquals(expected, eList);
	}
	
}
