package com.revature.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.*;
import java.util.List;

import org.junit.Test;

import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeeDaoImpl;
import com.revature.models.Employee;

public class EmployeeDaoTest {
	private int employeeId;  
	
	@Test    
	public void testGetAllEmployees() {

		EmployeeDaoImpl emplImpl = new EmployeeDaoImpl();
		List result = emplImpl.getAllEmployees();
		System.out.println("Number of Employees in ERS system  are: " + result.size());

		assertEquals(6, result.size());
	}

	@Test
	public void testViewReimbursementByDetails() {
		EmployeeDaoImpl emplImpl = new EmployeeDaoImpl();
		Employee employeeDetail = emplImpl.viewEmployeeDetails("gaddagada1");

		assertEquals("gaddagada2@gmail.com", employeeDetail.getEmail());
	}

	@Test
	public void testAuthenticate() {
		EmployeeDaoImpl authEmpl = new EmployeeDaoImpl();
		Employee emplAuth = authEmpl.getEmployeeByUsernameAndPassword("gaddagada1", "abc123");

		assertEquals("gaddagada1", emplAuth.getUsername());
		assertEquals("abc123", emplAuth.getPassword());
	}

//	@Test
//	public void testCreateEmployee() {
//		EmployeeDaoImpl emplImpl = new EmployeeDaoImpl();
//		Employee newEmpl = new Employee("Anvitha", "Sagireddy", "Associate", "aSagireddy1", "abc123", "412-901-8591",
//				"aSagireddy@yahoo.com", "94 Dixon Center", "Pittsburg", "PA", "United States", "15240", 1);
//		try {
//			String result = emplImpl.createEmployee(newEmpl);
//			System.out.println("Created new employee : " + result);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    
//	}
          
//	@Test
//	public void testUpdateEmployee() {
//		EmployeeDaoImpl emplImpl = new EmployeeDaoImpl();
//		Employee updateEmpl = new Employee("Christopher", "Thompson", "Director", "cThompson1", "abc123", "512-937-3089",
//				"cThompson1@revature.com", "8 Glendale Trail", "Austin", "TX", "United States", "78759", 1);
//		String result = emplImpl.updateEmployee(updateEmpl);
//		System.out.println("Updated existing employee : " + result);
//		assertEquals("Success", result);
//	}
}
