package com.revature.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.daos.EmployeeDao;
import com.revature.models.Department;
import com.revature.models.Employee;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

	@InjectMocks
	private EmployeeService es;
	
	@Mock
	private EmployeeDao ed;
	
	@Test
	public void getEmployeeByValidId() {
		when(ed.getEmployeeById(5)).thenReturn(new Employee(5, "Joe", 500, "Intern", 2, new Department(2)));

		Employee expected = new Employee(5, "Joe", 500, "Intern", 2, new Department(2));
		assertEquals(expected, es.getEmployeeById(5));
	}
	
	@Test
	public void getEmployeeByInvalidId() {
		when(ed.getEmployeeById(0)).thenReturn(null);
		assertNull(es.getEmployeeById(0));
	}
	
	@Test
	public void testSuccessfulCreation() {
		Employee newEmployee = new Employee(5, "Joe", 500, "Intern", 2, new Department(2));
		when(ed.createEmployee(newEmployee)).thenReturn(1);
		assertTrue(es.createEmployee(newEmployee));
	}
	
	@Test
	public void testUnsuccessfulCreation() {
		Employee newEmployee = null;
		when(ed.createEmployee(newEmployee)).thenReturn(0);
		assertFalse(es.createEmployee(newEmployee));
	}
	
}
