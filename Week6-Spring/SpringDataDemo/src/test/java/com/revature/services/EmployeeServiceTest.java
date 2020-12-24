package com.revature.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.models.Employee;
import com.revature.repositories.EmployeeRepository;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {
	
	@InjectMocks
	private EmployeeServiceImpl employeeService;
	
	@Mock
	private EmployeeRepository employeeDao;
	
	@Before
	public void init() {
	    MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getEmployeeByValidId() {
		when(employeeDao.getOne(5)).thenReturn(new Employee(5, "Joe", "Intern"));

		Employee expected = new Employee(5, "Joe", "Intern");
		assertEquals(expected, employeeService.getEmployeeById(5));
	}
	
	@Test
	public void getEmployeeByInvalidId() {
		when(employeeDao.getOne(0)).thenReturn(null);
		assertNull(employeeService.getEmployeeById(0));
	}
	
	@Test
	public void testSuccessfulCreation() {
		Employee newEmployee = new Employee(8, "Sally", "CEO");
		when(employeeDao.save(newEmployee)).thenReturn(newEmployee);
		assertNotNull(employeeService.addEmployee(newEmployee));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testUnsuccessfulCreation() {
		Employee newEmployee = null;
		doThrow(IllegalArgumentException.class).when(employeeDao).save(null);
		employeeService.addEmployee(newEmployee);
	}

}
