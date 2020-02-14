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
import com.revature.model.Department;
import com.revature.model.Employee;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

	// this is where we're going to "inject" a mock/s into (e.g. EmloyeeService
	// depends on EmployeeDao so that mock needs to be put inside of
	// EmployeeService)
	@InjectMocks
	private EmployeeService es;

	// this is what we'll be mocking
	@Mock
	private EmployeeDao ed;

	@Test
	public void getEmployeeByValidId() {
		final Employee expected = new Employee(5, "Joe", 500, "Intern", 2, new Department(2));
		when(ed.getEmployeeById(5)).thenReturn(new Employee(5, "Joe", 500, "Intern", 2, new Department(2)));
		assertEquals(expected, es.getEmployeeById(5));
	}

	@Test
	public void getEmployeeByInvalidId() {
		when(ed.getEmployeeById(0)).thenReturn(null);
		assertNull(es.getEmployeeById(0));
	}

	@Test
	public void testSuccessfulCreation() {
		when(ed.createEmployee(new Employee(5, "Joe", 500, "Intern", 2, new Department(2)))).thenReturn(1);
		assertTrue(es.createEmployee(new Employee(5, "Joe", 500, "Intern", 2, new Department(2))));
	}

	public void testUnsuccesfulCreation() {
		Employee newEmployee = null;
		when(ed.createEmployee(null)).thenReturn(0);
		assertFalse(es.createEmployee(newEmployee));
	}
}
