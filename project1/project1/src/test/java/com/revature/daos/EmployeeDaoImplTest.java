package com.revature.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.h2.tools.RunScript;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeeDaoImpl;
import com.revature.models.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeeDaoImplTest {

	private static EmployeeDao ed = new EmployeeDaoImpl();
	
	@BeforeClass
	public static void setUp() throws SQLException, FileNotFoundException {
		try(Connection c = ConnectionUtil.getConnection()){
			RunScript.execute(c, new FileReader("setup.sql"));
		}
	}
	
	@Test
	public void getAllEmployeesTest() {
		List<Employee> expected = new ArrayList<>();
		expected.add(new Employee(2, "lolo", "pw", "employee", "jimmy", "johns", "jj@email.com"));
		expected.add(new Employee(3, "jack", "bequick", "employee", "jack", "black", "jb@gmail.com"));
		assertEquals(expected, ed.getAllEmployees());
	}
	
	@Test
	public void createEmployeeSuccessTest() {
		int expected = 1;
		assertEquals(expected, ed.createEmployee("test", "ttest", "tester", "tommy", "tuple", "testing@tmail.com"));
	}
	
	@Test
	public void createEmployeeFailureTest() {
		int expected = 0;
		assertEquals(expected, ed.createEmployee("login", "ttest", "tester", "tommy", "tuple", "testing@tmail.com"));
	}
	
	@Test
	public void getEmployeeByIdSuccessTest() {
		Employee expected = new Employee(1, "login", "pw", "manager", "santa", "clause", "email");
		assertEquals(expected, ed.getEmployeeById(1));
	}
	
	@Test
	public void getEmployeeByIdFailureTest() {
		assertNull(ed.getEmployeeById(4));
	}
	
	@Test
	public void getEmployeeByLoginSuccessTest() {
		Employee expected = new Employee(1, "login", "pw", "manager", "santa", "clause", "email");
		assertEquals(expected, ed.getEmployeeByLogin("login", "pw"));
	}
	
	@Test
	public void getEmployeeByLoginFailureTest() {
		assertNull(ed.getEmployeeByLogin("login", "password"));
	}
	
	@Test
	public void updateEmployeeSuccessTest() {
		int expected = 1;
		assertEquals(expected, ed.updateEmployee(new Employee(3, "jack", "bequick", "employee", "jack", "daniels", "google")));
	}
	
	@Test
	public void updateEmployeeFailureTest() {
		int expected = 0;
		assertEquals(expected, ed.updateEmployee(new Employee(5, "jack", "bequick", "employee", "jack", "daniels", "google")));
	}
	
	@AfterClass
	public static void tearDown() throws SQLException, FileNotFoundException {
		try(Connection c = ConnectionUtil.getConnection()){
			RunScript.execute(c, new FileReader("teardown.sql"));
		}
	}
}
